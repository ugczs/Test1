import static org.junit.Assert.*;

import java.math.BigInteger;

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
	

}
