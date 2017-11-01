import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


public class ChameleonSigner {
	
	/*Liste mit Nachrichtenteilen*/
	private List<String> itemList;
	/*Liste mit Nachrichtenteilen konkateniert mit indizies*/
	private List<String> itemList2;
	/*Hashes auf Nachrichtenteilen konkateniert mit indizies*/
	private List<String> itemList3;
	/*Liste von Indizies, die geaendert werden koennen*/
	private List<Integer> changableIndex;
	/*ID von Nachricht*/
	private String id;
	/*Der Zensor*/
	private Chameleon chameleon;
	/*Liste mit Chameleonrandonissen*/
	private List<ChameleonRandomness> chRandom;
	private RsaSig rsaSig;
	private KeyPair pair;
	
	public ChameleonSigner(List<Integer> changeableIndex, List<String> itemList,  Chameleon chameleon) throws Exception {
		calcID();
		this.itemList = itemList;
		this.changableIndex = changeableIndex;
		this.chameleon = chameleon;
		this.chRandom = new ArrayList<ChameleonRandomness>();
		addIndexToMsg(itemList, changeableIndex);
		this.rsaSig = new RsaSig();
		this.pair = rsaSig.generateKeyPair();
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
	
	/**
	 * Diese Methode berechnet Indizies, die nicht geaendert werden können
	 */
	public List<Integer> calcNotChanged() {
		List<Integer> l = new ArrayList<Integer>(changableIndex);
		List<Integer> notChanged = new ArrayList<Integer>();
		int size = itemList.size();
		for(int i = 0; i < size; i++) {
			notChanged.add(i);
		}
		notChanged.removeAll(l);
		return notChanged;
	}
	
	/**
	 * Diese Methode nimmt eine Liste von Elementen und konkateniert Indizex 
	 * am Ende des jeweiligen nichtaenderbaren Elements.
	 * Fuer aenderbare Elemente konkateniert diese Methode id + Index am Kopf 
	 */
	public void addIndexToMsg(List<String> itemList, List<Integer> changableIndex) {
		this.itemList2 = new ArrayList<String>(itemList);
		for(int i = 0; i < changableIndex.size(); i++) {
			int index = changableIndex.get(i);
			String s = Integer.toString(index);
			String s1 = padLeftZeros(s, 5);
			String s2 = id + s1 + itemList2.get(index);
			itemList2.set(index , s2);
		}
			List<Integer> notChanged = calcNotChanged();
			for(int i = 0; i < notChanged.size(); i++) {
				int index = notChanged.get(i);
				String s = Integer.toString(index);
				String s1 = padLeftZeros(s, 5);
				String s2 = itemList2.get(index) + s1;
				itemList2.set(index , s2);
			}
	}
	
	/**
	 * Berechnet Chameleon Hashes in Liste2
	 */
	public void setListWithChamHash() {
		itemList3 = new ArrayList<String>(itemList2);
		for(int i = 0; i < changableIndex.size(); i++) {
			int index = changableIndex.get(i);
			itemList3.set(index , chameleon.calcChameleon(itemList2.get(index)));
			chRandom.add(new ChameleonRandomness(chameleon.getAlpha(), chameleon.getBeta()));
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
	
	
	public String cocateAll() {
		String s = "";
		List<String> l = new ArrayList<String>(itemList3);
		for(String i : l) {
			s = s + i;
		}
		String s2 = id + s;
		return s2;
	}
	
	/**
	 * Diese Methode verwandelt einen BigInteger zu String
	 * @param foo ist ein BigInteger
	 */
	public String fromBigInteger(BigInteger foo) {
	    return new String(foo.toString());
	}

	public List<String> getItemList() {
		return itemList;
	}

	public List<String> getItemList2() {
		return itemList2;
	}
	
	public List<ChameleonRandomness> getChRandom() {
		return this.chRandom;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Integer> getChangableIndex() {
		return changableIndex;
	}

	public List<String> getItemList3() {
		return itemList3;
	}
	
	public String combineInfos() {
		String s = this.id;
		String size = Integer.toString(calcBlocks());
		String s2 =  padLeftZeros(size, 5);
		String s3 = fromBigInteger(chameleon.getY());
		String s4 = cocateAll();
		String s5 = s + s2 + s3 + s4;
		return s5;
	}
	
	public String sign(String text) throws Exception {
		String signature = rsaSig.sign(text, pair.getPrivate());
		return signature;
	}
	
	public PublicKey getPublicKey(){
		return this.pair.getPublic();
	}

	public RsaSig getRsaSig() {
		return rsaSig;
	}
	
}
