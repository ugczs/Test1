import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class ChameleonVerifier {
	private String id;
	/*motifizierte Nachrichten*/
	private List<String> itemList;
	/*motifizierte Nachrichten mit Indizies*/
	private List<String> itemList2;
	/*Chameleonhashes auf motifizierten Nachrichten*/
	private List<String> itemList3;
	private List<Integer> changeableIndex;
	private Chameleon ch;
	private List<ChameleonRandomness> chRandom;
	
	public ChameleonVerifier(List<String> itemList, Chameleon chameleon) {
		this.ch = chameleon;
		this.itemList = itemList;
	}
	
	/**
	 * Berechnet Chameleon Hashes auf Liste mit Indizies
	 */
	public void setListWithChamHash() {
		itemList3 = new ArrayList<String>(itemList2);
		for(int i = 0; i < changeableIndex.size(); i++) {
			int index = changeableIndex.get(i);
			itemList3.set(index , ch.calcCollision2(itemList2.get(index), chRandom.get(i).getAlpha(),
					chRandom.get(i).getBeta()));
		}
	}
	
	public void addInfoToMsg() {
		itemList2 = new ArrayList<String>(itemList);
		for(int i = 0; i < changeableIndex.size(); i++) {
			int index = changeableIndex.get(i);
			String s = Integer.toString(index);
			String s1 = padLeftZeros(s, 5);
			String s2 = id + s1 + itemList2.get(index);
			itemList2.set(index , s2);
		}
			List<Integer> notChangeable = calcNotChangeable();
			for(int i = 0; i < notChangeable.size(); i++) {
				int index = notChangeable.get(i);
				String s = Integer.toString(index);
				String s1 = padLeftZeros(s, 5);
				String s2 = itemList2.get(index) + s1;
				itemList2.set(index , s2);
			}
	}

	public List<String> getItemList() {
		return itemList;
	}

	public void setItemList(List<String> itemList) {
		this.itemList = itemList;
	}

	public List<String> getItemList2() {
		return itemList2;
	}

	public void setItemList2(List<String> itemList2) {
		this.itemList2 = itemList2;
	}

	public List<ChameleonRandomness> getChRandom() {
		return chRandom;
	}

	public void setChRandom(List<ChameleonRandomness> chRandom) {
		this.chRandom = chRandom;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setChangeableIndex(List<Integer> changeableIndex) {
		this.changeableIndex = changeableIndex;
	}
	
	/**
     * Füllt Links von String s mit 0
     * Die max. Laenge betraegt i
     */
	public String padLeftZeros(String s, int i) {
		String str = String.format("%1$" + i + "s", s).replace(' ', '0');
		return str;
	}
	
	/**
	 * Diese Methode berechnet Indizies, die nicht geaendert werden können
	 */
	public List<Integer> calcNotChangeable() {
		List<Integer> l = new ArrayList<Integer>(changeableIndex);
		List<Integer> notChangeable = new ArrayList<Integer>();
		int size = itemList.size();
		for(int i = 0; i < size; i++) {
			notChangeable.add(i);
		}
		notChangeable.removeAll(l);
		return notChangeable;
	}

	public List<String> getItemList3() {
		return itemList3;
	}
	
	public String combineInfos() {
		String s = this.id;
		String size = Integer.toString(calcBlocks());
		String s2 =  padLeftZeros(size, 5);
		String s3 = fromBigInteger(ch.getY());
		String s4 = cocateAll();
		String s5 = s + s2 + s3 + s4;
		return s5;
	}

/**
	 * Diese Methode verwandelt einen BigInteger zu String
	 * @param foo ist ein BigInteger
	 */
	public String fromBigInteger(BigInteger foo) {
	    return new String(foo.toString());
	}

	public String cocateAll() {
		String s = "";
		List<String> l = new ArrayList<String>(itemList3);
		for(String i : l) {
			s = s + i;
		}
		String s2 = id + s;
		return s2;
	}

	public int calcBlocks() {
		int i = itemList.size();
		return i;
	}
	
	public void calcHashWithInfoFromEditor(ChameleonEditor editor) {
		this.setChangeableIndex(editor.getChangeableIndex());
				this.setId(editor.getId());
				this.setChRandom(editor.getChRandom());
				this.addInfoToMsg();
				this.setListWithChamHash();
	}

}
