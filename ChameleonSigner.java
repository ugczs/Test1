import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


public class ChameleonSigner {
	private String s;
	private List<String> itemList;
	private List<String> itemList2;
	private List<Integer> changableIndex;
	private String id;
	private ChameleonHash ch = new ChameleonHash("");
	
	/**
	 * Diese Methode nimmt eine Liste von Elementen und konkateniert Indizex 
	 * am Ende des jeweiligen nichtaenderbaren Elements.
	 * Fuer aenderbare Elemente konkateniert diese Methode id + Index am Kopf 
	 */
	public void addIndexToMsg(List<String> itemList, List<Integer> changableIndex) {
		this.itemList2 = itemList;
		for(int i = 0; i < changableIndex.size(); i++) {
			int index = changableIndex.get(i);
			String s = Integer.toString(index);
			String s1 = padLeftZeros(s, 5);
			String s2 = itemList2.get(index) + s1;
			itemList2.set(index , s2);
		}
			List<Integer> l = changableIndex;
			List<Integer> notChanged = new ArrayList<Integer>();
			int size = itemList.size();
			for(int i = 0; i < size; i++) {
				notChanged.add(i);
			}
			notChanged.removeAll(l);
			for(int i = 0; i < notChanged.size(); i++) {
				int index = notChanged.get(i);
				String s = Integer.toString(index);
				String s1 = padLeftZeros(s, 5);
				String s2 = id + s1 + itemList2.get(index);
				itemList2.set(index , s2);
			}
	}
	
	
	public void setListWithChamHash(List<Integer> changableIndex) {
		for(int i = 0; i < changableIndex.size(); i++) {
			int index = changableIndex.get(i);
			itemList2.set(index , calcChameleonHash(itemList.get(index)));
		}
	}
	
	/**
	 * Diese Methode berechnet eine fuenfstellige ID
	 */
	public void calcID() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		String s = String.format("%05d", num);
		this.id = s;
	}
	
	public int calcBlocks() {
		int i = itemList.size();
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
	
	public String calcChameleonHash(String s) {
		String foo =  ch.chameleonHashing(s, ch.getAlpha(), ch.getBeta());
		return foo;
	}
	
	public String cocateAll() {
		String s = "";
		List<String> l = itemList2;
		for(String i : l) {
			s = s + i;
		}
		String s2 = id + s;
		return s2;
	}

}
