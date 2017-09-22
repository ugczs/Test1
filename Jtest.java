import static org.junit.Assert.*;

import java.math.BigInteger;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class Jtest {

	@Test
	public void testFrom() {
		try {
			MailReader mr = new MailReader("C:/Users/yu/Desktop/email test/test4.txt");
			MailParser mp = new MailParser(mr);
			mp.splitMail(mr);
			mp.setHeader();
			String s2 = mp.getHeader().getFrom();
			assertEquals(s2, "a");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testTo() {
		try {
			MailReader mr = new MailReader("test4.txt");
			MailParser mp = new MailParser(mr);
			mp.splitMail(mr);
			mp.setHeader();
			String s2 = mp.getHeader().getTo();
			assertEquals(s2, "b");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testDate() {
		try {
			MailReader mr = new MailReader("test4.txt");
			MailParser mp = new MailParser(mr);
			mp.splitMail(mr);
			mp.setHeader();
			String s2 = mp.getHeader().getDate();
			assertEquals(s2, "11 11.11 2017");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testType() {
		try {
			MailReader mr = new MailReader("test4.txt");
			MailParser mp = new MailParser(mr);
			mp.splitMail(mr);
			mp.setHeader();
			String s2 = mp.getHeader().getType();
			assertEquals(s2, "multipart/mixed;");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testLine() {
		try {
			MailReader mr = new MailReader("test4.txt");
			MailParser mp = new MailParser(mr);
			mp.splitMail(mr);
			mp.setHeader();
			String s2 = mp.getHeader().getBoundaryLine();
			assertEquals(s2, "_003_DB5PR06MB17651F558F60A32D43342ED6C0EF0DB5PR06MB1765eurp_");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBodysize() {
		try {
			MailReader mr = new MailReader("test4.txt");
			MailParser mp = new MailParser(mr);
			mp.splitMail(mr);
			mp.setHeader();
			mp.setBody();
			int s2 = mp.getBody().getBodySize();
			assertEquals(s2, 4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCombine() {
		try {
			MailReader mr = new MailReader("test4.txt");
			String s = mr.getMail();
			MailParser mp = new MailParser(mr);
			mp.splitMail(mr);
			mp.setHeader();
			mp.setBody();
			String s2 = mp.combineParts();
			assertEquals(s, s2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCombine2() {
		try {
			MailReader mr = new MailReader("test1.eml");
			String s = mr.getMail();
			MailParser mp = new MailParser(mr);
			mp.splitMail(mr);
			mp.setHeader();
			mp.setBody();
			String s2 = mp.combineParts();
			assertEquals(s, s2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void pAndQRelation() {
		try {
			ChameleonHash ch = new ChameleonHash("testTest");
			BigInteger u1 = BigInteger.valueOf(1);
			BigInteger u2 = BigInteger.valueOf(2);
			BigInteger b1 = ch.getP();
			BigInteger b2 = ch.getQ();
			BigInteger b3 = (b1.subtract(u1)).divide(u2);
			assertEquals(b3, b2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void pAndQPrime() {
		try {
			ChameleonHash ch = new ChameleonHash("testTest");
			boolean b = ch.getP().isProbablePrime(5);
			boolean b1 = ch.getQ().isProbablePrime(5);
			assertEquals(true, b);
			assertEquals(true, b1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void calcAlpha() {
		try {
			ChameleonHash ch = new ChameleonHash("testTest");
			boolean b = (ch.getAlpha().compareTo(ch.getQ()) == 1);
			boolean b1 =(ch.getAlpha().compareTo(BigInteger.ZERO) == -1 );
			assertEquals(false, b);
			assertEquals(false, b1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void calcBeta() {
		try {
			ChameleonHash ch = new ChameleonHash("testTest");
			boolean b = (ch.getBeta().compareTo(ch.getQ()) == 1);
			boolean b1 =(ch.getBeta().compareTo(BigInteger.ZERO) == -1 );
			assertEquals(false, b);
			assertEquals(false, b1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void fromBigInteger() {
		try {
			ChameleonHash ch = new ChameleonHash("testTest");
			String s = ch.fromBigInteger(BigInteger.TEN);
		    assertEquals("10", s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void toBigInteger() {
		try {
			ChameleonHash ch = new ChameleonHash("testTest");
			String s = "10";
			BigInteger b1 = ch.toBigInteger(s);
			boolean b = (b1.compareTo(BigInteger.TEN) == 0);
		    assertEquals(true, b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void calcE() {
		try {
			ChameleonHash ch = new ChameleonHash("testTest");
			BigInteger b = ch.hashMsgToFixedLength("testTest", BigInteger.ONE);
		    String s = b.toString(16);
		    assertEquals("715ecd91a3ca7569e7b94bf72542504e", s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void calcE2() {
		try {
			ChameleonHash ch = new ChameleonHash("123");
			BigInteger b = ch.hashMsgToFixedLength("123", BigInteger.TEN);
		    String s = b.toString(16);
		    assertEquals("ca810d7772367ff95527e3f9ab5e6814", s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void compareBigIntAndStringValueOfChameleonHash() {
		try {
			ChameleonHash ch = new ChameleonHash("testTest");
			String s = ch.getChameleonStringHash();
			BigInteger b = ch.bigIntChameleonHash();
			String s1 = ch.fromBigInteger(b);
		    assertEquals(s1, s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void calcK2() {
		try {
			ChameleonHash ch = new ChameleonHash("testTest");
			boolean b = (ch.getK2().compareTo(ch.getQ().subtract(BigInteger.ONE)) == 1);
			boolean b1 =(ch.getK2().compareTo(BigInteger.ONE) == -1 );
			assertEquals(false, b);
			assertEquals(false, b1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void Testcheck() {
		try {
			ChameleonHash ch = new ChameleonHash("testTest");
			boolean b = (ch.check1().compareTo(ch.check2()) == 0);
			assertEquals(true, b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void Testcheck2() {
		try {
			ChameleonHash ch = new ChameleonHash("testTest");
			boolean b = (ch.check3().compareTo(ch.check2()) == 0);
			assertEquals(true, b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestHashTree() {
		try {
			List<String> l = new ArrayList<String>();
			l.add("a");
			l.add("b");
			HashTree h = new HashTree(l);
		    String s = h.getRoot().getValue();
		    assertEquals(s, "32560655549305688865853317129809488800");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void TestaddVektor() {
		try {
			BitwiseCalculation bc = new BitwiseCalculation();
			int[][] a = {{0, 0}, {1, 1}, {1, 0}, {0, 1}};
			int[][] b = {{1, 1}, {1, 1}, {1, 1}, {1, 1}};
			int[][] c = {{1, 1}, {0, 0}, {0, 1}, {1, 0}};
			int[][] sol= bc.addVektor(a, b);
			assertArrayEquals(c, sol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestsubstractVektor() {
		try {
			BitwiseCalculation bc = new BitwiseCalculation();
			int[][] a = {{0, 0}, {1, 1}, {1, 0}, {0, 1}};
			int[][] b = {{1, 1}, {1, 1}, {1, 1}, {1, 1}};
			int[][] c = {{1, 1}, {0, 0}, {0, 1}, {1, 0}};
			int[][] sol= bc.substractVektor(c, b);
			assertArrayEquals(a, sol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestmultiplyMatrix() {
		try {
			BitwiseCalculation bc = new BitwiseCalculation();
			int[][] a = {{1, 0}, {0, 1}, {1, 1}};
			int[][] b = {{1}, {1}};
			int[][] c = {{1}, {1}, {0}};
			int[][] sol= bc.multiplyMatrix(a, b);
			assertArrayEquals(c, sol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestintArrayToBitString() {
		try {
			ToeplitzCommitment tc = new ToeplitzCommitment("1");
			int[][] a = {{1}, {0}, {1}, {1}, {0}};
			String s = tc.intArrayToBitString(a);
			assertEquals(s, "10110" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestbitStringToIntArray() {
		try {
			ToeplitzCommitment tc = new ToeplitzCommitment("1");
			String s = "101101";
			int[][] a = {{1}, {0}, {1}, {1}, {0}, {1}};
			int[][] b = tc.bitStringToIntArray(s);
			assertArrayEquals(a, b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestrandomVektorlength() {
		try {
			ToeplitzCommitment tc = new ToeplitzCommitment("1");
			int a = tc.getRowLength();
			int b = tc.getRandomVektor().length;
			assertEquals(a, b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestcalcMsgLength() {
		try {
			ToeplitzCommitment tc = new ToeplitzCommitment("1");
			String s = "101101111";
			int a = tc.calcMsgLength(s);
			assertEquals(9, a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestpadLeftZeros() {
		try {
			ToeplitzCommitment tc = new ToeplitzCommitment("1");
			String a = tc.padLeftZeros("test", 5);
			String b = "0test";
			assertEquals(a, b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void TestpadLeftZeros2() {
		try {
			ToeplitzCommitment tc = new ToeplitzCommitment("1");
			String a = tc.padLeftZeros("test", 4);
			String b = "test";
			assertEquals(a, b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestlengthBitStringMsg() {
		try {
			ToeplitzCommitment tc = new ToeplitzCommitment("1");
			int a = tc.getBitStringMsg().length();
			int b = tc.getBit();
			assertEquals(a, b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestZLength() {
		try {
			ToeplitzCommitment tc = new ToeplitzCommitment("1");
			int a = tc.getZ().length();
			int b = tc.getBit();
			assertEquals(a, b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestRsaSignature() {
		try {
			RsaSig r = new RsaSig();
			KeyPair pair = r.generateKeyPair();
			String signature = r.sign("text", pair.getPrivate());
			boolean correct = r.verify("text", signature, pair.getPublic());
			assertEquals(true, correct);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestmatrixToString() {
		try {
			ToeplitzCommitment tc = new ToeplitzCommitment("1");
			int[][] a2 = {{1, 0, 1}, {0, 1, 0},{0, 1, 1}};
			String a = tc.matrixToString(a2);
			String b = "101010011";
			assertEquals(a, b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestarrayToString() {
		try {
			ToeplitzCommitment tc = new ToeplitzCommitment("1");
			int[] a2 = {1, 0, 1, 1 ,1};
			String a = tc.arrayToString(a2);
			String b = "10111";
			assertEquals(a, b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestfilltoeplitzMatrix() {
		try {
			int[] a = {1,2,3,4};
			int[] b = {1,5,6,7};
			int[][] c = {{1,2,3,4},{5,1,2,3 }, {6,5,1,2}, {7,6,5,1}};
			ToeplitzMatrix tm = new ToeplitzMatrix(a, b);
			int[][] d = tm.getToeplitzMatrix();
			assertArrayEquals(c, d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestgetRowToeplitz() {
		try {
			int[] a = {1,2,3,4};
			int[] b = {1,5,6,7};
			ToeplitzMatrix tm = new ToeplitzMatrix(a, b);
			int[] c = tm.getRow();
			int[] d = tm.getColumn();
			assertArrayEquals(c, a);
			assertArrayEquals(b, d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestRsaSignature2() {
		try {
			RsaSig r = new RsaSig();
			KeyPair pair = r.generateKeyPair();
			String signature = r.sign("text", pair.getPrivate());
			boolean correct = r.verify("textt", signature, pair.getPublic());
			assertEquals(false, correct);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
