import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;


public class MainChameleonHash {
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		ChameleonHash ch = new ChameleonHash("jklasf");
		print("q ist: " + ch.getQ());
		print("p ist: " + ch.getP());
		print("x ist: " + ch.getX());
		print("y ist: " + ch.getY());
		print("alpha ist: " + ch.getAlpha());
		print("beta ist: " + ch.getBeta());
		print("e ist: " + ch.getE());
		//System.out.println(ch.getChameleonStringHash());
	}
	
	public static void print(Object o) {
		System.out.println(o);
	}
}
