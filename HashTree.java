import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Berechnung von HashTreeRoot
 * @author Yuguan
 */
public class HashTree {
	private Node root;
    private List<String> itemList;
    private List<Node> nodeList;
    private List<CommitmentSet> setList;
    
    public HashTree(List<String> itemList) throws Exception {
    	this.itemList = itemList;
    	this.nodeList = new ArrayList<Node>();
    	this.setList = new ArrayList<CommitmentSet>();
    	calcNodeList();
    	calcHashTree();
    	setRootIndex();
    	setSubTreeIndex(this.root);
    }
    
    /**
     * Berechnet den Hash-Tree mit vorgegebenen Toeplitzkomponenten
     */
    public HashTree(List<String> itemList, List<CommitmentSet> setList) throws Exception {
    	this.setList = setList;
    	this.nodeList = new ArrayList<Node>();
    	this.itemList = itemList;
    	calcNodeList2();
    	calcHashTree();
    	setRootIndex();
    	setSubTreeIndex(this.root);
    }
    
    public void setRootIndex() {
    	List<Byte> l = new ArrayList<Byte>();
    	l.add((byte)0);
    	this.root.setIndex(l);
    }
    
    public void setSubTreeIndex(Node n) {
    	if(n.hasLeft()) {
    		n.setLeftIndex();
    		setSubTreeIndex(n.getLeft());
    	}
    	if(n.hasRight()) {
    		n.setRightIndex();
    		setSubTreeIndex(n.getRight());
    	}
    }
    
    /**
     * Sucht einen Knoten anhand von Index, ersetzt dessen Wert und 
     * loeschen seine Kinder
     * @param index Nummer von Knoten
     * @param newNode der Neue Knoten
     * @param startNode Startknoten
     */
    public void replaceNode(List<Byte> index, Node newNode, Node startNode) {
    	Node n = searchNode(index, startNode);
    	n.setValue(newNode.getValue());
    	n.setMarked(newNode.isMarked());
    	n.clearChildren();
    }
    
    /**
     * Diese Methode ersetzt die alten Knoten mit den neuen Knoten von
     * reqNodes mit den gleichen Indizies.
     * @param reqNodes neue Knotenliste
     */
    public void replaceWithNewNodes(List<Node> reqNodes) {
    	try{
    		for(int i = 0; i < reqNodes.size(); i++) {
        		replaceNode(reqNodes.get(i).getIndex(), reqNodes.get(i), this.root);
        	}
    	}
    	catch(Exception e) {
    		System.err.println("Cant replace the Node!");
    	}
    }
    
    
    /**
     * Sucht einen Knoten anhand von Index
     * @param index Nummer von Knoten
     * @param node Startknoten
     */
    public Node searchNode(List<Byte> index, Node node){
        if(node != null){
            if(node.getIndex().equals(index)){
            	return node;
            } 
            else {
                Node foundNode = searchNode(index, node.getLeft());
                if(foundNode == null) {
                    foundNode = searchNode(index, node.getRight());
                }
                return foundNode;
             }
        } 
        else {
            return null;
        }
    }
    
    public List<Node> getNodeList() {
		return nodeList;
	}

	/**
     * Berechnet die Blaetter.
     */
    public void calcNodeList() {
    	for (int i = 0; i < itemList.size(); i++) {
    		String s = commit(itemList.get(i));
    		this.nodeList.add(new Node(s));
    	}
    }
    
    public void calcNodeList2() {
    	for (int i = 0; i < itemList.size(); i++) {
    		String s = commit2(itemList.get(i), setList.get(i));
    		this.nodeList.add(new Node(s));
    	}
    }
    
    public List<String> getItemList() {
		return itemList;
	}

	/**
     * Berechnet Commitment von einem String
     * und feugt Komponente von Toeplitzmatrix in setList ein
     */
    public String commit(String value) {
    	ToeplitzCommitment tc = new ToeplitzCommitment(value);
    	String commitment = tc.getCommitmentValue();
    	int[] row = tc.getRow();
    	int[] column = tc.getColumn();
    	int[][] randomVektor = tc.getRandomVektor();
    	this.setList.add(new CommitmentSet(row, column, randomVektor));
		return commitment;	
    }
    
    /**
     * Berechnet Commitment von einem String
     * verwendet Commitmentset von voher
     */
    public String commit2(String value, CommitmentSet set) {
    	int[] row = set.getRow();
    	int[] column = set.getColumn();
    	int[][] randomVektor = set.getRandomVektor();
    	ToeplitzCommitment tc = new ToeplitzCommitment(value, row, column, randomVektor);
    	String commitment = tc.getCommitmentValue();
    	this.setList.add(new CommitmentSet(row, column, randomVektor));
		return commitment;	
    }
    
    /**
     * @param child Eine Liste von Kinderknoten
     * @return Gibt die Liste von Elternknoten zurueck
     * @throws NoSuchAlgorithmException 
     */
    private List<Node> getPreLvlList(List<Node> child) throws NoSuchAlgorithmException {
    	int i = 0;
    	List<Node> preLvl = new ArrayList<Node>();
    	while (i < child.size()) {
    		Node left = child.get(i);
    		i++;
    		Node right = new Node("");
    		if (i != child.size()) {
    			right = child.get(i);
    		}
    		String hash = calcHash(left.getValue() + right.getValue());
    		Node n = new Node(hash);
    		left.setParentNode(n);
    		right.setParentNode(n);
    		n.setLeft(left);
    		n.setRight(right);
    		preLvl.add(n);
    		i++;
        }
    	return preLvl;
    }

    /**
     * berechnet root von HashTree
     * @throws NoSuchAlgorithmException 
     */
    public void calcHashTree() throws NoSuchAlgorithmException {
    	List<Node> temp = new ArrayList<Node>();
    	for (int i = 0; i < nodeList.size(); i++){
    		temp.add(nodeList.get(i));
    	}
    	List<Node> preLvl = getPreLvlList(temp);
    	while (preLvl.size() > 1) {
    		preLvl = getPreLvlList(preLvl);
    	}
    	root = preLvl.get(0);
    }
    
    /**
     * berechnet Hashwert von einem String
     * @return gibt String-Hashwert zurueck
     */
    public String calcHash(String msg) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(msg.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		return new String(bigInt.toString());
	}
    
    /**
     * Getter von root
     * @return gibt Wert von root zurueck
     */
    public Node getRoot(){
      return root;
    }

	public List<CommitmentSet> getSetList() {
		return setList;
	}

	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}
	
	/**
     * markiert geaenderte Blaetterknoten
     */
	public void markNodes(List<Integer> changeableIndex) {
		for(int i = 0; i < changeableIndex.size(); i++) {
			int index = changeableIndex.get(i);
			this.getNodeList().get(index).setMarked(true);
		}
	}
	
	/**
     * markiert einen Elternknoten, wenn beide Kinder geaendert sind
     */
	public void markParent(Node n) {
		if(n.getParentNode().getLeft().isMarked() && n.getParentNode().getRight().isMarked() 
				&& n.getParentNode() != null ) {
			n.getParentNode().setMarked(true);
			n.getParentNode().getLeft().setMarked(false);
			n.getParentNode().getRight().setMarked(false);
		}
	}
	
	/**
	 * @param currentNodeList aktuelle Knotenliste
     *@param reqNodes nimmt alle zur Rekonstruktion des Baums 
     *benoetigte Knoten von currentNodeList auf
     */
	public void addReqNodes(List<Node> currentNodeList, List<Node> reqNodes) {
		for(Node n: currentNodeList) {
			if(n.isMarked()) {
				markParent(n);
			}
		}
		for(Node n: currentNodeList) {
			if(n.isMarked()) {
				reqNodes.add(n);
			}
		}
	}
	
	public List<Node> getParentNodeList(List<Node> childrenList) {
		List<Node> parentList = new ArrayList<Node>();
		for(Node n: childrenList) {
			parentList.add(n.getParentNode());
		}
		Set<Node> hs = new LinkedHashSet<>();
		hs.addAll(parentList);
		parentList.clear();
		parentList.addAll(hs);
		return parentList;	
	}
	
	/**
     * Diese Methode gibt die benoetige Knoten für
     * die Rekonstruktion des Baums zurueck
     */
	public List<Node> getReqNodes(List<Integer> changeableIndex) {
		List<Node> reqNodeList = new ArrayList<Node>();
		if(changeableIndex.size() == 0) {
			return this.nodeList;
		}
		else {
			markNodes(changeableIndex);
			addReqNodes(this.getNodeList(), reqNodeList);
			List<Node> p = getParentNodeList(this.getNodeList());
			while(p.size() > 1) {
				addReqNodes(p, reqNodeList);
				p = getParentNodeList(p);
			}
			if(p.get(0).isMarked()) {
				reqNodeList.add(p.get(0));
			}
			else {
				return reqNodeList;
			}
			return reqNodeList;
		}
	}
	
	public String calcRootWithNewNodes() {
		String s = null;
		if(root.isMarked()) {
			s = root.getValue();
			return s;
		}
		return s;
	}
	

	
	public void calcNewParentValue(Node n) throws NoSuchAlgorithmException {
		if(n.hasLeft() && n.hasRight() && !n.getLeft().hasChildren() && !n.getRight().hasChildren()) {
			n.setValue(calcHash(n.getLeft().getValue() + n.getRight().getValue()));
			n.clearChildren();
		}
		else if(n.hasLeft() && n.hasRight() && n.getLeft().hasChildren() && !n.getRight().hasChildren()) {
			calcNewParentValue(n.getLeft());
		}
		else if(n.hasLeft() && n.hasRight() && !n.getLeft().hasChildren() && n.getRight().hasChildren()) {
			calcNewParentValue(n.getRight());
		}
		else if(n.hasLeft() && n.hasRight() && n.getLeft().hasChildren() && n.getRight().hasChildren()) {
			calcNewParentValue(n.getLeft());
		}
	}
	
	public void calcNewRootValue(Node n) throws NoSuchAlgorithmException {
		while(n.hasChildren()) {
			calcNewParentValue(n);
		}
	}
  }