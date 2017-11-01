import java.util.ArrayList;
import java.util.List;


public class HtEditor {
	private HashTree t;
	private String s;
	/*urspruengliche Nachricht*/
	private List<String> itemList;
	/*modifizierte Nachricht*/
	private List<String> itemList2;
	/*geaenderte Indizies*/
	private List<Integer> changes;
	/*aenderbare Indizies*/
	private List<Integer> changeableIndex;
	/*Setliste mit Commitment-Komponenten*/
	private List<CommitmentSet> setList;
	private List<Node> reqNodeList;
	
	public HtEditor(List<String> itemList, List<String> itemList2, List<Integer> changeableIndex, 
			List<CommitmentSet> setList) {
		this.itemList = itemList;
		this.itemList2 = itemList2;
		this.changeableIndex = changeableIndex;
		this.setList = setList;
		calcChangedIndex();
		calcOriginalRootValue();
		this.reqNodeList = setReqNodeList();
	}
	
	public void calcChangedIndex() {
		changes = new ArrayList<Integer>();
		if(this.itemList.size() != itemList2.size()) {
			System.err.println("Not the same size");
		}
		for(int i = 0; i < this.itemList.size(); i++) {
			if(!this.itemList.get(i).equals(itemList2.get(i))) {
				changes.add(i);
			}
		}
	}
	
	/**
	 * Diese Methode ueberprueft, ob die zu aendernde Stellen aenderbar sind.
	 */
	public boolean preCheck() {
		if(!this.changeableIndex.containsAll(this.changes)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Diese Methode berechnet einen neuen Baum mit
	 * der alten Nachricht und dem Commitmentset
	 */
	public String calcOriginalRootValue() {
		try {
			if(preCheck()) {
				this.t = new HashTree(this.itemList, this.setList);
				this.s = t.getRoot().getValue();
			}
			else {
				System.err.println("Too many changable indices!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;	
	}
	
	/**
	 * Diese Methode berechnet die Knoten,
	 * die zur Berechnung des urspruenglichen
	 * Baums benoetigt werden. Spaeter werden sie an
	 * Verifier geschickt
	 */
	public List<Node> setReqNodeList() {
		try {
			List<Node> reqNodeList = new ArrayList<Node>();
			reqNodeList = this.t.getReqNodes(this.changes);
			return reqNodeList;
		}
		catch(Exception e) {
			System.err.println("More than excepted Nodes changed!");
		}
		return null;
	}
	
	public List<String> sendMsg() {
		try {
			List<String> l = this.itemList2;
			for(int i : changes) {
				l.set(i, "");
		}
			return l;
		}
		catch(Exception e) {
			System.err.println("Index not changeable!");
		}
		return null;
	}

	public String getS() {
		return s;
	}

	public List<Integer> getChanges() {
		return changes;
	}

	public List<String> getItemList2() {
		return itemList2;
	}

	public HashTree getT() {
		return t;
	}
	
	public String combineInfos() {
		String s = cocateAll();
		String size = Integer.toString(calcBlocks());
		String s2 =  padLeftZeros(size, 5);
		String s3 = this.s;
		String s4 = s + s2 + s3;
		return s4;
	}
	
	public String cocateAll() {
		String s = "";
		List<Integer> l = new ArrayList<Integer>(this.changeableIndex);
		for(int i : l) {
			s = s + i;
		}
		return s;
	}

	public int calcBlocks() {
		int i = itemList2.size();
		return i;
	}
	
	/**
     * Füllt Links von String s mit 0
     * Die max. Laenge betraegt i
     */
	public String padLeftZeros(String s, int i) {
		String str = String.format("%1$" + i + "s", s).replace(' ', '0');
		return str;
	}

	public List<Integer> getChangeableIndex() {
		return changeableIndex;
	}

	public void setChangeableIndex(List<Integer> changeableIndex) {
		this.changeableIndex = changeableIndex;
	}

	public List<CommitmentSet> getSetList() {
		return setList;
	}

	public void setSetList(List<CommitmentSet> setList) {
		this.setList = setList;
	}

	public List<String> getItemList() {
		return itemList;
	}

	public List<Node> getReqNodesList() {
		return reqNodeList;
	}
	
	
}
