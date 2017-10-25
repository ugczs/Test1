import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class ChameleonEditor {
	private String id;
	private String s2;
	/*urspruengliche Nachrichten*/
	private List<String> itemList;
	/*motifizierte Nachrichten*/
	private List<String> itemList2;
	/*Liste mit motifizierten Nachrichtenteilen konkateniert mit indizies*/
	private List<String> itemList3;
	/*Chameleonhashes auf urspruengliche Nachrichten*/
	private List<String> itemList4;
	/*Chameleonhashes auf motifizierten Nachrichten*/
	private List<String> itemList5;
	private List<Integer> changes;
	private List<Integer> changeableIndex;
	private Chameleon ch;
	private List<ChameleonRandomness> chRandom;
	
	public ChameleonEditor(List<Integer> changeableIndex, List<String> itemList2, Chameleon chameleon, String id) {
		this.ch = chameleon;
		this.itemList2 = itemList2;
		this.changeableIndex = changeableIndex;
		this.id = id;
		addIndexToMsg(itemList2, changeableIndex);
	}
	/**
	 * Berechnet Chameleon Hashes auf Liste 3
	 */
	public void setListWithChamHash() {
		itemList5 = new ArrayList(itemList3);
		for(int i = 0; i < changeableIndex.size(); i++) {
			int index = changeableIndex.get(i);
			itemList5.set(index , ch.calcCollision2(itemList3.get(index), chRandom.get(i).getAlpha(),
					chRandom.get(i).getBeta()));
		}
	}
	
	/**
	 * Berechnet Chameleon Collision und ersetzt
	 * alten Zufallszahlen mit neuen
	 */
	public void setListWithNewRandom(List<Integer> changes) {
		for(int i = 0; i < changes.size(); i++) {
			int index = changes.get(i);
			ch.setChameleonHash(itemList4.get(index));
			ch.calcCollision(itemList3.get(index));
			chRandom.set(index , new ChameleonRandomness(ch.getAlpha2(), ch.getBeta2()));
		}
	}
	
	/**
	 * Berechnet Chameleonhash mit festen Werten
	 */
	public String calcChameleonWithFixedRandom(String msg, BigInteger alpha, BigInteger beta) {
		String s = ch.calcChameleon(msg, alpha, beta);
		return s;
	}
	
	/**
	 * Berechnet neue Hash-Liste mit festen Zahlen als Zufall
	 */
	public List<String> calcNewHashList() {
		List<String> l = new ArrayList(itemList3);
		for(int i = 0; i < changeableIndex.size(); i++) {
			int index = changeableIndex.get(i);
			l.set(index, calcChameleonWithFixedRandom(l.get(index), chRandom.get(i).getAlpha(), chRandom.get(i).getBeta()));
		}
		return l;
	}
	
	public void addIndexToMsg(List<String> itemList2, List<Integer> changeableIndex) {
		this.itemList3 = new ArrayList(itemList2);
		for(int i = 0; i < changeableIndex.size(); i++) {
			int index = changeableIndex.get(i);
			String s = Integer.toString(index);
			String s1 = padLeftZeros(s, 5);
			String s2 = id + s1 + itemList3.get(index);
			itemList3.set(index , s2);
		}
			List<Integer> notChangeable = calcNotChangeable();
			for(int i = 0; i < notChangeable.size(); i++) {
				int index = notChangeable.get(i);
				String s = Integer.toString(index);
				String s1 = padLeftZeros(s, 5);
				String s2 = itemList3.get(index) + s1;
				itemList3.set(index , s2);
			}
	}
	
	
	
	public void calcChangedIndex() {
		changes = new ArrayList<Integer>();
		if(itemList.size() != itemList2.size()) {
			System.err.println("Not the same size");
		}
		else {
			for(int i = 0; i < itemList.size(); i++) {
				if(!itemList.get(i).equals(itemList2.get(i))) {
					changes.add(i);
				}
			}
		}
	}
	
	public String calcChameleonHash(String msg) {
		String s = ch.calcChameleon(msg);
		return s;
	}
	
	public String calcChameleonCollison(String msg) {
		String s = ch.calcCollision(msg);
		return s;
	}



	public List<ChameleonRandomness> getChRandom() {
		return chRandom;
	}

	public void setChRandom(List<ChameleonRandomness> chRandom) {
		this.chRandom = new ArrayList(chRandom);
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
     * Füllt Links von String s mit 0
     * Die max. Laenge betraegt i
     */
	public String padLeftZeros(String s, int i) {
		String str = String.format("%1$" + i + "s", s).replace(' ', '0');
		return str;
	}
	
	public void setChangableIndex(List<Integer> changeableIndex) {
		this.changeableIndex = changeableIndex;
	}
	
	public void setItemList(List<String> itemList) {
		this.itemList = new ArrayList(itemList);
	}
	
	/**
	 * Diese Methode berechnet Indizies, die nicht geaendert werden können
	 */
	public List<Integer> calcNotChangeable() {
		List<Integer> l = new ArrayList(changeableIndex);
		List<Integer> notChangeable = new ArrayList<Integer>();
		int size = itemList2.size();
		for(int i = 0; i < size; i++) {
			notChangeable.add(i);
		}
		notChangeable.removeAll(l);
		return notChangeable;
	}

	public void setItemList4(List<String> itemList4) {
		this.itemList4 = new ArrayList(itemList4);
	}

	public List<Integer> getChanges() {
		return changes;
	}

	public List<String> getItemList() {
		return itemList;
	}

	public List<String> getItemList2() {
		return itemList2;
	}

	public List<String> getItemList3() {
		return itemList3;
	}

	public List<Integer> getChangeableIndex() {
		return changeableIndex;
	}

	public List<String> getItemList4() {
		return itemList4;
	}
	public List<String> getItemList5() {
		return itemList5;
	}
}
