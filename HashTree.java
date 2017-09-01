import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Berechnung von HashTreeRoot
 * @author Yuguan
 */
public class HashTree {
	private String root;
    private List<String> itemList;
    
    public HashTree(List<String> itemList) {
    	this.itemList = itemList;
    }
    
    /**
     * @param temp Eine Liste von Elemente
     * @return Gibt die Liste von Elternknoten zurueck
     * @throws NoSuchAlgorithmException 
     */
    private List<String> getNextLvlList(List<String> temp) throws NoSuchAlgorithmException {
    	int i = 0;
    	List<String> nextLvl = new ArrayList<String>();
    	while (i < temp.size()) {
    		String left = temp.get(i);
    		i++;
    		String right = "";
    		if (i != temp.size()) {
    			right = temp.get(i);
        }
        String hash = calcHash(left + right);
        nextLvl.add(hash);
        i++;
        }
    	return nextLvl;
    }

    /**
     * berechnet root von HashTree
     * @throws NoSuchAlgorithmException 
     */
    public void calcHashTree() throws NoSuchAlgorithmException {
    	List<String> temp = new ArrayList<String>();
    	for (int i = 0; i < itemList.size(); i++){
    		temp.add(itemList.get(i));
    	}
    	List<String> nextLvl = getNextLvlList(temp);
    	while (nextLvl.size() > 1) {
    		nextLvl = getNextLvlList(nextLvl);
    	}
    	root = nextLvl.get(0);
    }
    
    /**
     * berechnet Hashwert von einem String
     * @return gibt String-Hashwert zurueck
     */
    public String calcHash(String msg) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(msg.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		return new String(bigInt.toString());
	}
    
    /**
     * Getter von root
     * @return gibt Wert von root zurueck
     */
    public String getRoot(){
      return root;
    }

  }