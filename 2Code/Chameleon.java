import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Diese Klasse berechnet Chameleon Hash
 * @author Yuguan Zhao
 */
public class Chameleon {
	/*Teil des oeffentlichen Schluessels*/
	private BigInteger y;
	/*Geheimschluessel*/
	private BigInteger x;
	/*Primzahl einer zyklischen Gruppe mit dem Verhaeltnis p = 2q + 1*/
	private BigInteger p;
	/*Primzahl einer zyklischen Gruppe mit dem Verhaeltnis p = 2q + 1*/
	private BigInteger q;
	/*die erste Zufallszahl von Chameleon Hash*/
	private BigInteger alpha;
	/*die erste Zufallszahl von Chameleon Kollision*/
	private BigInteger alpha2;
	/*die zweite Zufallszahl von Chameleon Hash*/
	private BigInteger beta;
	/*die zweite Zufallszahl von Chameleon Kollision*/
	private BigInteger beta2;
	/*Zufallszahl von Chameleon Kollosion, die am Anfang gezogen wird*/
	private BigInteger k2;
	/*Erzeuger der Untergruppe mit der Ordnung q*/
	private int g = 4;
	/*Bitlaenge*/
	private int bit = 256;
	/*gehashte Nachricht fester Laenge von Chameleon Hash*/
	private BigInteger e;
	/*gehashte Nachricht fester Laenge von Chameleon Kollision*/
	private BigInteger e2;
	/*Chalemeon Hash einer Nachricht in String*/
	private String chameleonHash;
	
	public Chameleon()  {
		try {
			calcQAndP();
			calcX();
			calcY();
		}
		catch(Exception e) {
			System.err.println("Wrong algorithm");
		}
	}
	/**
	 * Diese Methode berechnet den String-Wert von Chameleonhash-Collision
	 * mit der Eingabe msg
	 */
	public String calcCollision(String msg) {
		try {
			calcK2();
			calcAlpha2();
			calcE2(msg);
			calcBeta2();
			String s = fromBigInteger(check1());
			return s;
		}
		catch(Exception e) {
			System.err.println("Collion function error!");
		}
		return null;
	}
	
	public String calcCollision2(String msg, BigInteger alpha2, BigInteger beta2) {
		try {
			String s = fromBigInteger(check(msg, alpha2, beta2));
			return s;
		}
		catch(Exception e) {
			System.err.println("Collion function error!");
		}
		return null;
	}
	
	public BigInteger check(String msg, BigInteger alpha2, BigInteger beta2) throws NoSuchAlgorithmException {
		BigInteger e = calcE2(msg, alpha2);
		BigInteger g1 = BigInteger.valueOf(this.g);
		BigInteger b = y.modPow(e, p).multiply(g1.modPow(beta2, p));
		BigInteger b2 = b.mod(p);
		BigInteger b3 = alpha2.subtract(b2).mod(q);
		return b3;
	}
	
	/**
	 * Diese Methode berechnet den String-Wert von Chameleonhash
	 * mit der Eingabe msg
	 */
	public String calcChameleon(String msg)  {
		try {
			calcAlpha();
			calcBeta();
			return chameleonHashing2(msg, alpha, beta);
		}
		catch(Exception e) {
			System.err.println("Wrong algorithm 2");
		}
		return null;
	}
	
	public String calcChameleon(String msg, BigInteger alpha, BigInteger beta)  {
		try {
			return chameleonHashing2(msg, alpha, beta);
		}
		catch(Exception e) {
			System.err.println("Wrong algorithm 2");
		}
		return null;
	}
	
	
	/**
	 * Diese Methode waehlt q zufaellig mit Bitlaenge bit
	 * Diese Methode berechnet p mit p = 2q + 1
	 */
	public void calcQAndP() {
		BigInteger u2 = BigInteger.valueOf(2);
		q = new BigInteger (bit, 5, new SecureRandom());
		p = u2.multiply(q).add(new BigInteger("1"));
		while(!q.isProbablePrime(5) || !p.isProbablePrime(5)) {
			q = new BigInteger (bit, 5, new SecureRandom());
			p = u2.multiply(q).add(new BigInteger("1"));
		}
	}
	
	/**
	 * Diese Methode waehlt x zufaellig zwischen 1 und q-1
	 */
	public void calcX() {
		x = randomBigIntegerLessThan(q);
	}
	
	/**
	 * Diese Methode berechnet y anhand x und g
	 */
	public void calcY() {
		BigInteger g1 = BigInteger.valueOf(this.g);
		y = g1.modPow(x, p);
	}
	
	/**
	 * Diese Methode waehlt alpha zufaellig zwischen 0 und q
	 */
	public void calcAlpha() {
		this.alpha = randomBigIntegerFromZeroTo(q);
	}
	
	/**
	 * Diese Methode waehlt beta zufaellig zwischen 0 und q
	 */
	public void calcBeta() {
		this.beta = randomBigIntegerFromZeroTo(q);
	}
	
	/**
	 * Diese Methode verwandelt eine Nachricht zu einer Nachricht bestimmter Laenge
	 * @param msg ist die urspruengliche Nachricht
	 * @param random ist eine Zufallszahl bzw. alpha
	 */
	public BigInteger hashMsgToFixedLength (String msg, BigInteger random) throws NoSuchAlgorithmException {
		String random1 = fromBigInteger(random);
		String msg1 = random1 + msg;
		MessageDigest m = MessageDigest.getInstance("SHA-256");
		m.reset();
		m.update(msg1.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		return bigInt;
	}
	
	/**
	 * Diese Methode berechnet e
	 * e hat eine feste Laenge von Typ BigInteger
	 */
	public void calcE(String msg) throws NoSuchAlgorithmException {
		e = hashMsgToFixedLength(msg, alpha);
	}
	
	/**
	 * Diese Methode berechnet e mit einem festem Zufall
	 * e hat eine feste Laenge von Typ BigInteger
	 * @return 
	 * 
	 */
	public BigInteger calcE2(String msg, BigInteger alpha2) throws NoSuchAlgorithmException {
		BigInteger ee = hashMsgToFixedLength(msg, alpha2);
		return ee;
	}
	
	/**
	 * Diese Methode berechnet e2
	 * e2 hat eine feste Laenge von Typ BigInteger
	 */
	public void calcE2(String msg) throws NoSuchAlgorithmException {
		e2 = hashMsgToFixedLength(msg, alpha2);
	}
	
	/**
	 * Diese Methode berecht das Chameleon-Hasch
	 * @param msg ist die Nachricht
	 * @param alpha ist eine zufaellige Zahl in [0,q]
	 * @param beta ist eine zufaellige Zahl in [0,q]
	 * @throws NoSuchAlgorithmException 
	 */
	public String chameleonHashing(String msg, BigInteger alpha, BigInteger beta) {
		BigInteger b1;
		BigInteger g1 = BigInteger.valueOf(this.g);
		BigInteger b2 = y.modPow (e, p);
		BigInteger b3 = g1.modPow(beta, p);
		BigInteger b4 = (b2.multiply(b3).mod(p));
		b1 = alpha.subtract(b4).mod(q);
		this.chameleonHash = fromBigInteger(b1);
		return chameleonHash;
	}
	
	public String chameleonHashing2(String msg, BigInteger alpha, BigInteger beta) throws NoSuchAlgorithmException {
		BigInteger e = calcE2(msg, alpha);
		BigInteger b1;
		BigInteger g1 = BigInteger.valueOf(this.g);
		BigInteger b2 = y.modPow (e, p);
		BigInteger b3 = g1.modPow(beta, p);
		BigInteger b4 = (b2.multiply(b3).mod(p));
		b1 = alpha.subtract(b4).mod(q);
		this.chameleonHash = fromBigInteger(b1);
		return chameleonHash;
	}
	
	public BigInteger bigIntChameleonHash() {
		return toBigInteger(chameleonHash);
	}
	

	/**
	 * Diese Methode nimmt einen BigInt als Basis und einen BigInt als Exponent
	 * und berecht das Ergebnis
	 * @param base ist Basis
	 * @param exponent ist exponent
	 */
	public BigInteger pow(BigInteger base, BigInteger exponent) {
		  BigInteger result = BigInteger.ONE;
		  while (exponent.signum() > 0) {
		    if (exponent.testBit(0)) result = result.multiply(base);
		    base = base.multiply(base);
		    exponent = exponent.shiftRight(1);
		  }
		  return result;
		}
	
	/**
	 * Diese Methode verwandelt einen String zu BigInteger
	 * @param foo ist ein String
	 */
	public BigInteger toBigInteger(String foo) {
	    return new BigInteger(foo);
	}
	
	/**
	 * Diese Methode verwandelt einen BigInteger zu String
	 * @param foo ist ein BigInteger
	 */
	public String fromBigInteger(BigInteger foo) {
	    return new String(foo.toString());
	}
	
	/**
	 * Diese Methode liefert einen BigInteger von 1 bis n-1 zufaellig
	 * @param n-1 ist die groesste Zahl
	 */
	public BigInteger randomBigIntegerLessThan(BigInteger n) {
	    Random rand = new SecureRandom();
	    BigInteger result = new BigInteger(n.bitLength(), rand);
	    while( result.compareTo(n) >= 0 || result.compareTo(BigInteger.valueOf(0)) == 0 ) {
	        result = new BigInteger(n.bitLength(), rand);
	    }
	    return result;
	}
	
	/**
	 * Diese Methode liefert einen BigInteger von 0 bis n zufaellig
	 * @param n ist die groesste Zahl
	 */
	public BigInteger randomBigIntegerFromZeroTo(BigInteger n) {
	    Random rand = new SecureRandom();
	    BigInteger result = new BigInteger(n.bitLength(), rand);
	    while( result.compareTo(n) == 1) {
	        result = new BigInteger(n.bitLength(), rand);
	    }
	    return result;
	}
	
	/**
	 * Diese Methode waehlt k2 zufaellig zwischen 1 und q-1
	 */
	public void calcK2() {
		k2 = randomBigIntegerLessThan(q);
	}
	
	/**
	 * Diese Methode berechnet alpha2
	 */
	public void calcAlpha2() {
		BigInteger g1 = BigInteger.valueOf(this.g);
		BigInteger b = g1.modPow (k2, p);
		BigInteger b2 = b;
		BigInteger b3 = toBigInteger(chameleonHash).add(b2).mod(q);
		alpha2 = b3;
	}
	
	
	public void calcBeta2() {
		this.beta2 = k2.subtract(e2.multiply(x).mod(q)).mod(q);
	}
	
	
	public BigInteger check1() {
		BigInteger g1 = BigInteger.valueOf(this.g);
		BigInteger b = y.modPow(e2, p).multiply(g1.modPow(beta2, p));
		BigInteger b2 = b.mod(p);
		BigInteger b3 = alpha2.subtract(b2).mod(q);
		return b3;
	}
	
	public BigInteger check2() {
		BigInteger g1 = BigInteger.valueOf(this.g);
		BigInteger b4 = toBigInteger(chameleonHash);
		BigInteger b5 = g1.modPow(k2, p);
		BigInteger b6 = x.multiply(e2).mod(q);
		BigInteger b7 = g1.modPow(b6, p).multiply(g1.modPow(beta2, p)).mod(p);
		BigInteger b8 = b7;
		BigInteger b9 = b4.add(b5).subtract(b8).mod(q);
		return b9;
	}
	
	public BigInteger check3() {
		BigInteger b4 = toBigInteger(chameleonHash);
		return b4;
	}
	
	public String getChameleonStringHash() {
		return chameleonHash;
	}

	public BigInteger getY() {
		return y;
	}

	public BigInteger getX() {
		return x;
	}

	public BigInteger getP() {
		return p;
	}

	public BigInteger getQ() {
		return q;
	}

	public BigInteger getAlpha() {
		return alpha;
	}

	public BigInteger getBeta() {
		return beta;
	}
	
	public BigInteger getE() {
		return e;
	}
	
	public BigInteger getE2() {
		return e2;
	}
	
	public BigInteger getK2() {
		return k2;
	}
	
	public BigInteger getAlpha2() {
		return alpha2;
	}

	public BigInteger getBeta2() {
		return beta2;
	}
	
	public void setChameleonHash(String chameleonHash) {
		this.chameleonHash = chameleonHash;
	}
	
	
	
}
