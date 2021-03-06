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
	
//	@Test
//	public void TestHashTree() {
//		try {
//			List<String> l = new ArrayList<String>();
//			l.add("a");
//			l.add("b");
//			HashTree h = new HashTree(l);
//		    String s = h.getRoot().getValue();
//		    assertEquals(s, "32560655549305688865853317129809488800");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
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
	
	@Test
	public void TestHashTreeGetReqNodes1() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			l.add("a");
			l.add("b");
			l.add("a");
			l.add("b");
			l.add("a");
			l.add("b");
			l.add("a");
			l.add("b");
			l2.add(0);
			l2.add(1);
			l2.add(2);
			l2.add(3);
			l2.add(4);
			l2.add(5);
			l2.add(6);
			l2.add(7);
			HashTree t = new HashTree(l);
			List<Node> n = t.getReqNodes(l2);
		    assertEquals(t.getRoot(), n.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestHashTreeGetReqNodes2() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			l.add("a");
			l.add("b");
			l.add("a");
			l.add("b");
			l.add("a");
			l.add("b");
			l.add("a");
			l.add("b");
			l2.add(1);
			l2.add(2);
			l2.add(3);
			l2.add(4);
			l2.add(5);
			l2.add(6);
			l2.add(7);
			HashTree t = new HashTree(l);
			List<Node> n = t.getReqNodes(l2);
		    assertEquals(3, n.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestHashTreeGetReqNodes3() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			l.add("a");
			l.add("b");
			l.add("a");
			l.add("b");
			l.add("a");
			l.add("b");
			l.add("a");
			l.add("b");
			l2.add(1);
			l2.add(2);
			l2.add(3);
			HashTree t = new HashTree(l);
			List<Node> n = t.getReqNodes(l2);
			boolean b;
			b = n.get(0).getIndex().equals(t.getRoot().getLeft().getLeft().getRight().getIndex());
		    assertEquals(b, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestHashTree1() {
		try {
			List<String> l = new ArrayList<String>();
			l.add("a");
			l.add("b");
			l.add("c");
			l.add("d");
			HashTree h = new HashTree(l);
			List<CommitmentSet> setList = h.getSetList();
			HashTree h2 = new HashTree(l, setList);
		    String s = h.getRoot().getValue();
		    String s2 = h2.getRoot().getValue();
		    assertEquals(s, s2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestHashTree2() {
		try {
			List<String> l = new ArrayList<String>();
			l.add("a");
			l.add("b");
			l.add("c");
			l.add("d");
			l.add("2");
			HashTree h = new HashTree(l);
			List<CommitmentSet> setList = h.getSetList();
			HashTree h2 = new HashTree(l, setList);
		    String s = h.getRoot().getValue();
		    String s2 = h2.getRoot().getValue();
		    assertEquals(s, s2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestHashTree3() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			List<String> l3 = new ArrayList<String>();
			l.add("aa");
			l.add("bb");
			l.add("c");
			l.add("d");
			l2.add(0);
			l2.add(1);
			l3.add("a");
			l3.add("b");
			l3.add("c");
			l3.add("d");
			SignatureCheck s = new SignatureCheck(l2, l, l3);
			s.firstSignature(l);
			s.secSignature(l3, l2);
			boolean b = s.check();
		    assertEquals(true , b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestHashTree4() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			List<String> l3 = new ArrayList<String>();
			l.add("aa");
			l.add("b");
			l.add("c");
			l.add("d");
			l2.add(0);
			l2.add(1);
			l3.add("a");
			l3.add("b");
			l3.add("c");
			l3.add("d");
			SignatureCheck s = new SignatureCheck(l2, l, l3);
			s.firstSignature(l);
			s.secSignature(l3, l2);
			boolean b = s.check();
		    assertEquals(true , b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void TestHashTree6() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			List<String> l3 = new ArrayList<String>();
			l.add("aa");
			l.add("bb");
			l.add("cc");
			l.add("d");
			l2.add(0);
			l2.add(1);
			l3.add("a");
			l3.add("b");
			l3.add("c");
			SignatureCheck s = new SignatureCheck(l2, l, l3);
			s.firstSignature(l);
			s.secSignature(l3, l2);
			boolean b = s.check();
		    assertEquals(false , b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode verifiziert Nachrichten von HtEditor
	 */
	@Test
	public void TestHtEditor() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			List<String> l3 = new ArrayList<String>();
			l.add("a");
			l.add("b");
			l.add("c");
			l.add("d");
			l2.add(0);
			l2.add(1);
			l3.add("aa");
			l3.add("b");
			l3.add("c");
			l3.add("d");
			HtSigner s = new HtSigner(l2, l);
			HtEditor e = new HtEditor(s, l3);
			e.setChangeableIndex(s.getChangableIndex());
			String combine = s.combineInfos();
			String signature = s.sign(combine);
			String combine2 = e.combineInfos();
			boolean b = s.getRsaSig().verify(combine2, signature, s.getPublicKey());
		    assertEquals(true , b);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode verifiziert Nachrichten von HtVerifier
	 */
	@Test
	public void TestHtVerifier() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			List<String> l3 = new ArrayList<String>();
			l.add("a");
			l.add("b");
			l.add("c");
			l.add("d");
			l2.add(0);
			l2.add(1);
			l3.add("aa");
			l3.add("bb");
			l3.add("c");
			l3.add("d");
			HtSigner s = new HtSigner(l2, l);
			HtEditor e = new HtEditor(s, l3);
			HtVerifier v = new HtVerifier(e);
			e.setChangeableIndex(s.getChangableIndex());
			v.setChangeableIndex(e.getChangeableIndex());
			String combine = s.combineInfos();
			String signature = s.sign(combine);
			String combine2 = v.combineInfos();
			boolean b = s.getRsaSig().verify(combine2, signature, s.getPublicKey());
		    assertEquals(true , b);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestVerifierMsG() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			List<String> l3 = new ArrayList<String>();
			l.add("a");
			l.add("b");
			l.add("c");
			l.add("d");
			l2.add(0);
			l2.add(1);
			l3.add("");
			l3.add("b");
			l3.add("c");
			l3.add("d");
			HtSigner s = new HtSigner(l2, l);
			HtEditor e = new HtEditor(s, l3);
			HtVerifier v = new HtVerifier(e);
			List<String> l4 = v.getMsg();
		    assertEquals(l4 , l3);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode ueberprueft ob die Listen von Signer und Editor 
	 * nach dem Hashing gleich sind
	 */
	@Test
	public void TestChameleonEditor() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			List<String> l3 = new ArrayList<String>();
			l.add("a");
			l.add("b");
			l.add("c");
			l.add("d");
			l2.add(0);
			l2.add(1);
			l2.add(2);
			l3.add("aa");
			l3.add("b");
			l3.add("c");
			l3.add("d");
			Chameleon ch = new Chameleon();
			ChameleonSigner cs = new ChameleonSigner(l2, l, ch);
			cs.setListWithChamHash();
			ChameleonEditor ce = new ChameleonEditor(l2, l3, ch, cs.getId());
			ce.setItemList(l);
			ce.calcChangedIndex();
			ce.setChRandom(cs.getChRandom());
			ce.setItemList4(cs.getItemList3());
			ce.setListWithNewRandom(ce.getChanges());
			ce.setListWithChamHash();
		    assertEquals(cs.getItemList3() , ce.getItemList5());
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode verifiziert Nachrichten von Editor
	 */
	@Test
	public void TestChameleonEditor2() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			List<String> l3 = new ArrayList<String>();
			l.add("a");
			l.add("b");
			l.add("c");
			l.add("d");
			l2.add(0);
			l2.add(1);
			l2.add(2);
			l3.add("aa");
			l3.add("b");
			l3.add("c");
			l3.add("d");
			Chameleon ch = new Chameleon();
			ChameleonSigner cs = new ChameleonSigner(l2, l, ch);
			cs.setListWithChamHash();
			ChameleonEditor ce = new ChameleonEditor(l2, l3, ch, cs.getId());
			ce.setItemList(l);
			ce.calcChangedIndex();
			ce.setChRandom(cs.getChRandom());
			ce.setItemList4(cs.getItemList3());
			ce.setListWithNewRandom(ce.getChanges());
			ce.setListWithChamHash();
			String combine = cs.combineInfos();
			String combine2 = ce.combineInfos();
			String signature = cs.sign(combine);
			boolean b = cs.getRsaSig().verify(combine2, signature, cs.getPublicKey());
		    assertEquals(b , true);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode verifiziert Nachrichten von Verifier
	 */
	@Test
	public void TestChameleonVerifier() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			List<String> l3 = new ArrayList<String>();
			l.add("a");
			l.add("b");
			l.add("c");
			l.add("d");
			l2.add(0);
			l2.add(1);
			l2.add(2);
			l3.add("aa");
			l3.add("b");
			l3.add("c");
			l3.add("d");
			Chameleon ch = new Chameleon();
			ChameleonSigner cs = new ChameleonSigner(l2, l, ch);
			cs.setListWithChamHash();
			ChameleonEditor ce = new ChameleonEditor(l2, l3, ch, cs.getId());
			ce.setItemList(l);
			ce.calcChangedIndex();
			ce.setChRandom(cs.getChRandom());
			ce.setItemList4(cs.getItemList3());
			ce.setListWithNewRandom(ce.getChanges());
			ce.setListWithChamHash();
			String combine = cs.combineInfos();
			String signature = cs.sign(combine);
			ChameleonVerifier cv = new ChameleonVerifier(ce.getItemList2(), ch);
			cv.setChangeableIndex(ce.getChangeableIndex());
			cv.setId(ce.getId());
			cv.setChRandom(ce.getChRandom());
			cv.addInfoToMsg();
			cv.setListWithChamHash();
			String combine2 = cv.combineInfos();
			boolean b = cs.getRsaSig().verify(combine2, signature, cs.getPublicKey());
		    assertEquals(b , true);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode verifiziert Nachrichten von Verifier
	 * sollte false ausgeben, da die stell geaendert ist,
	 * die nicht geaendert werden kann
	 */
	@Test
	public void TestChameleonVerifier2() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			List<String> l3 = new ArrayList<String>();
			l.add("a");
			l.add("b");
			l.add("c");
			l.add("d");
			l2.add(0);
			l2.add(1);
			l2.add(2);
			l3.add("aa");
			l3.add("b");
			l3.add("c");
			l3.add("dd");
			Chameleon ch = new Chameleon();
			ChameleonSigner cs = new ChameleonSigner(l2, l, ch);
			cs.setListWithChamHash();
			ChameleonEditor ce = new ChameleonEditor(l2, l3, ch, cs.getId());
			ce.setItemList(l);
			ce.calcChangedIndex();
			ce.setChRandom(cs.getChRandom());
			ce.setItemList4(cs.getItemList3());
			ce.setListWithNewRandom(ce.getChanges());
			ce.setListWithChamHash();
			String combine = cs.combineInfos();
			String signature = cs.sign(combine);
			ChameleonVerifier cv = new ChameleonVerifier(ce.getItemList2(), ch);
			cv.setChangeableIndex(ce.getChangeableIndex());
			cv.setId(ce.getId());
			cv.setChRandom(ce.getChRandom());
			cv.addInfoToMsg();
			cv.setListWithChamHash();
			String combine2 = cv.combineInfos();
			boolean b = cs.getRsaSig().verify(combine2, signature, cs.getPublicKey());
		    assertEquals(b , false);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode verifiziert Nachrichten von Verifier
	 * sollte true ausgeben, da nichts geaendert ist
	 */
	@Test
	public void TestChameleonVerifier3() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			List<String> l3 = new ArrayList<String>();
			l.add("a");
			l.add("b");
			l.add("c");
			l.add("d");
			l2.add(0);
			l2.add(1);
			l2.add(2);
			l3.add("a");
			l3.add("b");
			l3.add("c");
			l3.add("d");
			Chameleon ch = new Chameleon();
			ChameleonSigner cs = new ChameleonSigner(l2, l, ch);
			cs.setListWithChamHash();
			ChameleonEditor ce = new ChameleonEditor(l2, l3, ch, cs.getId());
			ce.setItemList(l);
			ce.calcChangedIndex();
			ce.setChRandom(cs.getChRandom());
			ce.setItemList4(cs.getItemList3());
			ce.setListWithNewRandom(ce.getChanges());
			ce.setListWithChamHash();
			String combine = cs.combineInfos();
			String signature = cs.sign(combine);
			ChameleonVerifier cv = new ChameleonVerifier(ce.getItemList2(), ch);
			cv.setChangeableIndex(ce.getChangeableIndex());
			cv.setId(ce.getId());
			cv.setChRandom(ce.getChRandom());
			cv.addInfoToMsg();
			cv.setListWithChamHash();
			String combine2 = cv.combineInfos();
			boolean b = cs.getRsaSig().verify(combine2, signature, cs.getPublicKey());
		    assertEquals(b , true);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode verifiziert Nachrichten von Verifier
	 * alle aenderbare Daten sind geaendert, sollte true ausgeben
	 */
	@Test
	public void TestChameleonVerifier4() {
		try {
			List<String> l = new ArrayList<String>();
			List<Integer> l2 = new ArrayList<Integer>();
			List<String> l3 = new ArrayList<String>();
			l.add("a");
			l.add("b");
			l.add("c");
			l.add("d");
			l2.add(0);
			l2.add(1);
			l2.add(2);
			l3.add("ads");
			l3.add("bdff");
			l3.add("cssdfs");
			l3.add("d");
			Chameleon ch = new Chameleon();
			ChameleonSigner cs = new ChameleonSigner(l2, l, ch);
			cs.setListWithChamHash();
			ChameleonEditor ce = new ChameleonEditor(l2, l3, ch, cs.getId());
			ce.setItemList(l);
			ce.calcChangedIndex();
			ce.setChRandom(cs.getChRandom());
			ce.setItemList4(cs.getItemList3());
			ce.setListWithNewRandom(ce.getChanges());
			ce.setListWithChamHash();
			String combine = cs.combineInfos();
			String signature = cs.sign(combine);
			ChameleonVerifier cv = new ChameleonVerifier(ce.getItemList2(), ch);
			cv.setChangeableIndex(ce.getChangeableIndex());
			cv.setId(ce.getId());
			cv.setChRandom(ce.getChRandom());
			cv.addInfoToMsg();
			cv.setListWithChamHash();
			String combine2 = cv.combineInfos();
			boolean b = cs.getRsaSig().verify(combine2, signature, cs.getPublicKey());
		    assertEquals(b , true);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
