import java.util.ArrayList;
import java.util.List;


public class HtEditor {
	private HtSigner htSigner;
	private HashTree t2;
	private String s2;
	private List<String> itemList2;
	private List<Integer> changes;
	
	public HtEditor(HtSigner htSigner, List<String> itemList2) {
		this.htSigner = htSigner;
		this.itemList2 = itemList2;
		calcChangedIndex();
		secSignature(itemList2, changes);
	}
	
	public void calcChangedIndex() {
		changes = new ArrayList<Integer>();
		if(htSigner.getItemList().size() != itemList2.size()) {
			System.err.println("Not the same size");
		}
		for(int i = 0; i < htSigner.getItemList().size(); i++) {
			if(!htSigner.getItemList().get(i).equals(itemList2.get(i))) {
				changes.add(i);
			}
		}
	}
	
	/**
	 * Diese Methode ueberprueft, ob die zu aendernde Stellen aenderbar sind.
	 */
	public boolean preCheck(List<Integer> changes, HtSigner htSigner) {
		if(!htSigner.getChangableIndex().containsAll(changes)) {
			return false;
		}
		return true;
	}
	
	
	public String secSignature(List<String> itemList, List<Integer> changes) {
		try {
			if(preCheck(this.changes, this.htSigner)) {
				this.t2 = new HashTree(itemList, htSigner.getT().getSetList());
				List<Node> nodeList2 = t2.getNodeList();
				for(int i = 0; i < changes.size(); i++) {
					int index = changes.get(i);
					nodeList2.set(index , htSigner.getT().getNodeList().get(index));
				}
				t2.setNodeList(nodeList2);
				t2.calcHashTree();
				this.s2 = t2.getRoot().getValue();
			}
			else {
				System.err.println("Changes not correct!");
			}
		} catch (Exception e) {
			System.err.println("Index invalid!");
		}
		return s2;	
	}
	
	public List<String> sendMsg() {
		List<String> l = this.itemList2;
		for(int i : changes) {
			l.set(i, "");
		}
		return l;
	}
	
	public List<String> sendMsgToVerifier() {
		List<String> l = this.itemList2;
		List<Node> n = this.t2.getNodeList();
		for(int i : changes) {
			l.set(i, n.get(i).getValue());
		}
		return l;
	}
	

	public String getS2() {
		return s2;
	}

	public List<Integer> getChanges() {
		return changes;
	}

	public List<String> getItemList2() {
		return itemList2;
	}

	public HashTree getT2() {
		return t2;
	}
	
	
	

}
