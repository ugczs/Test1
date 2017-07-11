import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;


public class MainChameleonHash {
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		ChameleonHash ch = new ChameleonHash("jklasf");
		System.out.println(ch.getQ());
		System.out.println(ch.getP());
		System.out.println(ch.getX());
		System.out.println(ch.getY());
		System.out.println(ch.getAlpha());
		System.out.println(ch.getBeta());
		System.out.println(ch.getE());
		//System.out.println(ch.getChameleonStringHash());
	}
}
