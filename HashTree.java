import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Berechnung von HashTreeRoot
 * @author Yuguan
 */
public class HashTree {
	private Node root;
    private List<String> itemList;
    private List<Node> nodeList = new ArrayList<Node>();
    
    public HashTree(List<String> itemList) throws Exception {
    	this.itemList = itemList;
    	calcNodeList();
    	calcHashTree();
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
    
    public List<String> getItemList() {
		return itemList;
	}

	/**
     * Berechnet Commitment von einem String
     */
    public String commit(String value) {
		return value;	
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

  }