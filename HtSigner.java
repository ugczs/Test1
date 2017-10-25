import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;


public class HtSigner {
	private HashTree t;
	private String s;
	private List<String> itemList;
	private List<Integer> changableIndex;
	private RsaSig rsaSig;
	private KeyPair pair;
	
	public HtSigner(List<Integer> changableIndex, List<String> itemList) throws Exception {
		this.itemList = itemList;
		this.changableIndex = changableIndex;
		calcRootValue(itemList);
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
	
	public String calcRootValue(List<String> itemList) {
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
		List<Integer> l = new ArrayList<Integer>(changableIndex);
		for(int i : l) {
			s = s + i;
		}
		return s;
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
	

}
