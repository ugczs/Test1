import java.security.SecureRandom;
import java.util.List;


public class ChameleonSignatureCheck {
	private List<String> itemList;
	private List<String> itemList2;
	private List<Integer> changableIndex;
	private String id;
	private ChameleonHash ch = new ChameleonHash("");
	
	public void addIndex(List<String> itemList) {
		this.itemList2 = itemList;
		for(int i = 0; i < itemList2.size(); i++) {
			String s = Integer.toString(i);
			String s1 = padLeftZeros(s, 5);
			String s2 = itemList2.get(i) + s1;
			itemList2.set(i , s2);
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
	
}
