import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class ChameleonHash {
	private BigInteger y;
	private BigInteger x;
	private BigInteger p;
	private BigInteger q;
	private BigInteger alpha;
	private BigInteger alpha2;
	private BigInteger beta;
	private BigInteger beta2;
	private BigInteger k2;
	private int g = 2;
	private int bit = 32;
	private BigInteger u;
	private BigInteger e;
	private BigInteger e2;
	private String chameleonHash;
	
	public ChameleonHash(String msg) throws NoSuchAlgorithmException {
		calcQ();
		calcP();
		calcX();
		calcY();
		calcAlpha();
		calcBeta();
		chameleonHashing(msg, alpha, beta);
		calcK2();
		calcAlpha2();
		calcE2("blablabal");
		calcBeta2();
		check();
	}
	
	public BigInteger calcP() {
		u = new BigInteger (bit, new Random());
		p = u.multiply(q).add(new BigInteger("1"));
		while(p.isProbablePrime(-1) || p != u.multiply(q).add(new BigInteger("1"))) {
			p = new BigInteger (bit, new Random());
			p = u.multiply(q).add(new BigInteger("1"));
		}
		return p;
	}
	
	public BigInteger calcQ() {
		q = new BigInteger (bit, new Random());
		while(q.isProbablePrime(-1)) {
			q = new BigInteger (bit, new Random());
		}
		return q;
	}
	
	public void calcX() {
		x = randomBigIntegerLessThan(q);
	}
	
	public void calcY() {
		BigInteger g1 = BigInteger.valueOf(this.g);
		y = pow(g1, this.x);	
	}
	
	public void calcAlpha() {
		this.alpha = randomBigIntegerLessThan(q);
	}
	
	public void calcBeta() {
		this.beta = randomBigIntegerLessThan(q);
	}
	
	public void hashMsgToFixedLength (String msg) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(msg.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String a = bigInt.toString(16);
		e = toBigInteger(a);	
	}
	
	public void hashMsgToFixedLength2 (String msg2) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(msg2.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String a = bigInt.toString(16);
		e2 = toBigInteger(a);	
	}
	
	public String chameleonHashing(String msg, BigInteger alpha, BigInteger beta) {
		BigInteger b1;
		BigInteger g1 = BigInteger.valueOf(this.g);
		BigInteger b2 = pow (y, e);
		BigInteger b3 = pow (g1, beta);
		BigInteger b4 = (b2.multiply(b3).mod(p)).mod(q);
		b1 = alpha.subtract(b4);
		this.chameleonHash = b1.toString(16);
		return chameleonHash;
	}
	
	public BigInteger pow(BigInteger base, BigInteger exponent) {
		  BigInteger result = BigInteger.ONE;
		  while (exponent.signum() > 0) {
		    if (exponent.testBit(0)) result = result.multiply(base);
		    base = base.multiply(base);
		    exponent = exponent.shiftRight(1);
		  }
		  return result;
		}
	
	public BigInteger toBigInteger(String foo) {
	    return new BigInteger(foo.getBytes());
	}

	public String fromBigInteger(BigInteger foo) {
	    return new String(foo.toByteArray());
	}
	
	public BigInteger randomBigIntegerLessThan(BigInteger n) {
	    Random rand = new Random();
	    BigInteger result = new BigInteger(n.bitLength(), rand);
	    while( result.compareTo(n) >= 0 || result.compareTo(BigInteger.valueOf(0)) == 0 ) {
	        result = new BigInteger(n.bitLength(), rand);
	    }
	    return result;
	}
	
	public void calcK2() {
		k2 = randomBigIntegerLessThan(q);
	}
	
	public void calcAlpha2() {
		BigInteger g1 = BigInteger.valueOf(this.g);
		BigInteger b = pow (g1, k2);
		BigInteger b2 = b.mod(p).mod(q);
		BigInteger b3 = toBigInteger(chameleonHash).add(b2);	
	}
	
	public void calcE2(String msg2) throws NoSuchAlgorithmException {
		hashMsgToFixedLength2(msg2);
	}
	
	
	public void calcBeta2() {
		this.beta2 = k2.subtract(e2.multiply(x).mod(q));
	}
	
	public boolean check() {
		BigInteger g1 = BigInteger.valueOf(this.g);
		BigInteger b = pow(y, e2).multiply(pow(g1, beta2));
		BigInteger b2 = b.mod(p).mod(q);
		BigInteger b3 = alpha2.subtract(b2);
		
		BigInteger b4 = toBigInteger(chameleonHash);
		BigInteger b5 = pow(g1, k2).mod(p);
		BigInteger b6 = x.multiply(e2);
		BigInteger b7 = pow(g1, b6).multiply(pow(g1, beta2)).mod(p);
		BigInteger b8 = b7.mod(q);
		BigInteger b9 = b4.add(b5).subtract(b8);
		
		return(b9 == b4 && b3 == b4);	
		

	}
	
}
