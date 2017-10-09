import java.util.List;

public class SignatureCheck {
	private HashTree t;
	private HashTree t2;
	private String s;
	private String s2;
	private List<String> itemList;
	private List<String> itemList2;
	private List<Integer> changableIndex;
	
	public SignatureCheck(List<Integer> changableIndex, List<String> itemList, List<String> itemList2) {
		this.itemList = itemList;
		this.itemList2 = itemList2;
		this.changableIndex = changableIndex;
	}
	
	/**
	 * Diese Methode ueberprueft, ob Liste von aenderbaren Stellen laenger
	 * als urspruengliche Nachrichtenliste ist
	 * @return true, wenn die Liste nicht laenger ist.
	 */
	public boolean preCheck(List<Integer> changableIndex, List<String> itemList, List<String> itemList2) {
		if(changableIndex.size() > itemList.size() || itemList.size() != itemList2.size() ) {
			return false;
		}
		return true;
	}
	
	public String firstSignature(List<String> itemList) {
		try {
			this.t = new HashTree(itemList);
			this.s = t.getRoot().getValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;	
	}
	
	public String secSignature(List<String> itemList, List<Integer> changableIndex) {
		try {
			this.t2 = new HashTree(itemList, t.getSetList());
			List<Node> nodeList2 = t2.getNodeList();
			for(int i = 0; i < changableIndex.size(); i++) {
				int index = changableIndex.get(i);
				nodeList2.set(index , t.getNodeList().get(index));
			}
			t2.setNodeList(nodeList2);
			t2.calcHashTree();
			this.s2 = t2.getRoot().getValue();
		} catch (Exception e) {
			System.err.println("Index invalid!");
		}
		return s2;	
	}
	
	public boolean check() {
		 boolean b = s.equals(s2) && preCheck(changableIndex, itemList, itemList2);
		 return b;
	}
}
