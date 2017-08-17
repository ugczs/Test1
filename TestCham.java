import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.util.Random;



public class TestCham {
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
	BigInteger x;
	BigInteger y = new BigInteger("1232312");
	System.out.println(y.isProbablePrime(5));
	

	String plaintext = "y";
	MessageDigest m = MessageDigest.getInstance("MD5");
	m.reset();
	m.update(plaintext.getBytes());
	byte[] digest = m.digest();
	BigInteger bigInt = new BigInteger(1,digest);
	String hashtext = bigInt.toString(16);
	System.out.println(hashtext);
	
	System.out.println(pow(BigInteger.valueOf(4444),BigInteger.valueOf(2)));
	System.out.println(nextRandomBigInteger(BigInteger.valueOf(3)));
	System.out.println(FromNullToN(BigInteger.valueOf(3)));
	String a1 = "aa";
	String a2 = "bb";
	System.out.println(a1+a2);
	int a = 2; 
	BigInteger bi = BigInteger.valueOf(3);
	BigInteger bi2 = new BigInteger("123");
	BigInteger bg1 = new BigInteger (5, new Random());
	BigInteger bg2 = bi.multiply(bg1);
	System.out.println(bg1);
	System.out.println(bg2);
	System.out.println(bi);
	
	System.out.println(bi2);
	String foo = "lllllljkdjfk123123";
	System.out.println((toBigInteger(foo)));
	System.out.println(calcAlpha()== calcAlpha2());
	
	}
	static BigInteger calcAlpha() {
		BigInteger p = BigInteger.valueOf(2);
		BigInteger q = BigInteger.valueOf(5);
		BigInteger g1 = BigInteger.valueOf(2);
		BigInteger b = pow (g1, BigInteger.valueOf(3));
		BigInteger b2 = b.mod(p).mod(q);
		return b2;
	}
	
	static BigInteger calcAlpha2() {
		BigInteger p = BigInteger.valueOf(2);
		BigInteger q = BigInteger.valueOf(5);
		BigInteger g1 = BigInteger.valueOf(2);
		BigInteger b = g1.modPow(BigInteger.valueOf(3), p);
		BigInteger b2 = b.mod(q);
		return b2;
	}
	
	static BigInteger pow(BigInteger base, BigInteger exponent) {
		  BigInteger result = BigInteger.ONE;
		  while (exponent.signum() > 0) {
		    if (exponent.testBit(0)) result = result.multiply(base);
		    base = base.multiply(base);
		    exponent = exponent.shiftRight(1);
		  }
		  return result;
		}
	
	static BigInteger nextRandomBigInteger(BigInteger n) {
	    Random rand = new Random();
	    BigInteger result = new BigInteger(n.bitLength(), rand);
	    while( result.compareTo(n) >= 0 || result.compareTo(BigInteger.valueOf(0)) == 0 ) {
	        result = new BigInteger(n.bitLength(), rand);
	    }
	    return result;
	}
	
	/**
	 * Diese Methode liefert einen BigInteger von 0 bis n zufällig
	 * @param n grösste Zahl
	 */
	static BigInteger FromNullToN(BigInteger n) {
	    Random rand = new Random();
	    BigInteger result = new BigInteger(n.bitLength(), rand);
	    while( result.compareTo(n) == 1) {
	        result = new BigInteger(n.bitLength(), rand);
	    }
	    return result;
	}
	
	/**
	 * Diese Methode verwandelt einen String zu BigInteger
	 * @param foo ist ein String
	 * @throws UnsupportedEncodingException 
	 */
	public static BigInteger toBigInteger(String foo) throws UnsupportedEncodingException {
	    return new BigInteger(foo.getBytes());
	}
	
	/**
	 * Diese Methode verwandelt einen BigInteger zu String
	 * @param foo ist ein BigInteger
	 */
	public static String fromBigInteger(BigInteger foo) {
	    return new String(foo.toByteArray());
	}
}
