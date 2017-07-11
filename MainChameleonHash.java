import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class MainChameleonHash {
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		ChameleonHash ch = new ChameleonHash("jklasf");
		System.out.println(ch.getChameleonStringHash());
	}
}
