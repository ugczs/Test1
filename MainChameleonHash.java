import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;


public class MainChameleonHash {
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		ChameleonHash ch = new ChameleonHash("bla");
		print("q ist: " + ch.getQ());
		print("p ist: " + ch.getP());
		print("x ist: " + ch.getX());
		print("y ist: " + ch.getY());
		print("alpha ist: " + ch.getAlpha());
		print("beta ist: " + ch.getBeta());
		print("e ist: " + ch.getE());
		print("e2 ist: " + ch.getE2());
		print("alpha2 ist: " + ch.getAlpha2());
		print("beta2 ist: " + ch.getBeta2());
		print("k2 ist: " + ch.getK2());
		print("String-Wert von ChamHash: " + ch.getChameleonStringHash());
		print("BigInteger-Wert von ChamHash: " +ch.bigIntChameleonHash());
		print(ch.check1());
		print(ch.check2());
		print(ch.check3());
	}
	
	public static void print(Object o) {
		System.out.println(o);
	}
}
