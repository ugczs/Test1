import java.util.List;


public class HtSigner {
	private HashTree t;
	private String s;
	private List<String> itemList;
	private List<Integer> changableIndex;
	
	public HtSigner(List<Integer> changableIndex, List<String> itemList) {
		this.itemList = itemList;
		this.changableIndex = changableIndex;
		firstSignature(itemList);
	}
	
	/**
	 * Diese Methode ueberprueft, ob Liste von aenderbaren Stellen laenger
	 * als urspruengliche Nachrichtenliste ist
	 * @return true, wenn die Liste nicht laenger ist.
	 */
	public boolean preCheck(List<Integer> changableIndex, List<String> itemList) {
		if(changableIndex.size() > itemList.size()) {
			return false;
		}
		for(int i: changableIndex) {
			if (i > itemList.size()) {
				return false;
			}
		}
		return true;
	}
	
	public String firstSignature(List<String> itemList) {
		try {
			if(preCheck(this.changableIndex, itemList)) {
				this.t = new HashTree(itemList);
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

	public String getS() {
		return s;
	}

	public List<String> getItemList() {
		return itemList;
	}

	public List<Integer> getChangableIndex() {
		return changableIndex;
	}

	public HashTree getT() {
		return t;
	}	
}
