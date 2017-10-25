import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class Test {

	public static void main(String[] args) throws Exception {
//		// Eine Email wird eingelesen
//		//MailReader mr = new MailReader("C:/Users/yu/Desktop/testmail.txt");
//		//MailReader mr = new MailReader("C:/Users/yu/Desktop/email test/test1.eml");
//		//MailReader mr = new MailReader("C:/Users/yu/Desktop/email test/test2.eml");
//		//MailReader mr = new MailReader("C:/Users/yu/Desktop/email test/test3.eml");
//		MailReader mr = new MailReader("C:/Users/yu/Desktop/email test/test4.txt");
//		String s = mr.getMail();
//		
//		// gibt die Strings von testmail.txt aus
//		print("test 1: \n" + s);
//		
//		
//		MailParser mp = new MailParser(mr);
//		// teilt Email in Head- und Body-Parts
//		mp.splitMail(mr);
//		mp.setHeader();
//		
//		//gibt Header aus
//		print("test 1.1: \n" + mp.getHeader().getHeaderContent());
//		// gibt Absender aus
//		print("test 2: \n" + mp.getHeader().getFrom());
//		// gibt Empfänger aus
//		print("test 3: \n" + mp.getHeader().getTo());
//		// gibt Datum in String aus
//		print("test 4: \n" + mp.getHeader().getDate());
//		// gibt Content-Type von Email aus
//		print("test 5: \n" + mp.getHeader().getType());
//		// gibt die Trennlinie für Bodypart aus
//		print("test 6: \n" + mp.getHeader().getBoundaryLine());
//		
//		mp.setBody();
//		//gibt Größe der BodyListe aus(sollte 3 sein)
//		print("test 7: \n" + mp.getBody().getBodySize());
//		//Inhalt von Bodyteil 0
//		print("test 8: \n" + mp.getBody().getContent(0));
//		//Inhalt von Bodyteil 1
//		print("test 9: \n" + mp.getBody().getContent(1));
//		//Inhalt von Bodyteil 2
//		print("test 10: \n" + mp.getBody().getContent(2));
//		//Inhalt von Bodyteil 3
//		print("test 11: \n" + mp.getBody().getContent(3));
//		//Inhalt vom gesamten Body
//		print("test 12: \n" + mp.getBody().printBody());
//		print ("test 99: \n" + mp.combineParts());
//	}
//	
//	public static void print(Object o) {
//		System.out.println(o);
//		System.out.println("jajaja");
//		List<String> l = new ArrayList<String>();
//		l.add("a");
//		l.add("b");
//		l.add("c");
//		l.add("d");
//		HashTree t = new HashTree(l);
//		String s = t.getRoot().getValue();
//		System.out.println(s);
		
		ToeplitzMatrix a = new ToeplitzMatrix(8,10);
		for (int i = 0; i < a.getRowLength(); i++) {
		    for (int j = 0; j < a.getColumnLength(); j++) {
		        System.out.print(a.getToeplitzMatrix()[i][j]+ " ");
		 }
		    System.out.println();
		}
		
//		ToeplitzCommitment tc = new ToeplitzCommitment("asdfsdfdf");
//		int[][] v = tc.getRandomVektor();
//		System.out.println(tc.getBitStringMsg());
//		for (int i = 0; i < v.length; i++) {
//			for (int j = 0; j < v[i].length; j++) {
//		        System.out.print(v[i][j] + " ");
//		    }
//		    System.out.println();
//		}
		
//		String s  = "101011110";
//		String[] stringArray = s.split("");
//		int[][] intArray = new int[stringArray.length][1];
//		for (int i = 0; i < stringArray.length; i++) {
//			intArray[i][0] = Integer.parseInt(stringArray[i]);
//		}
//		for (int i = 0; i < intArray.length; i++) {
//			for (int j = 0; j < intArray[i].length; j++) {
//		        System.out.print(intArray[i][j] + " ");
//		    }
//		    System.out.println();
//		}
//		
//		BitwiseCalculation bc = new BitwiseCalculation();
//		System.out.println(bc.add(0, 0));
//		System.out.println("take care");
//		int[][] a1 = {{1, 0}, {0, 1}, {1, 1}};
//		int[][] b = {{1}, {1}};
//		int[][] sol= bc.multiplyMatrix(a1, b);
//		for (int i = 0; i < sol.length; i++) {
//			for (int j = 0; j < sol[i].length; j++) {
//		        System.out.print(sol[i][j] + " ");
//		    }
//		    System.out.println();
//		}
//		System.out.println(a1[0].length); //spalte
//		System.out.println(a1.length);
//		System.out.println("take care2");
		int[][] b1 = {{1, 2}, {0, 2}, {1, 2}, {1, 2},{0, 2}};
//		String sa = arrayToString(b1);
//		System.out.println(sa);
//		System.out.println("take care3");
//		String sss = calcHash("1jsldfjkwjfkwfjjfaskdfjsadf");
//		String ssss = toB(sss);
//		System.out.println(sss.length());
//		System.out.println(sss);
//		System.out.println(ssss.length());
//		System.out.println(ssss);
//		ToeplitzCommitment tc = new ToeplitzCommitment("hahaha");
//		System.out.println(tc.getBitStringMsg());
//		System.out.println(tc.getBitStringMsg().length());
//		
//		int[] a2 = {1,2,3,4};
//		int[] a3 = {1,5,6,7};
//		ToeplitzMatrix tm = new ToeplitzMatrix(a2, a3);
//		int[][] a4 = tm.getToeplitzMatrix();
//		printMatrix(a4);
//		System.out.println("asdfdfasdfasd");
//		printMatrix2(tm.getRow());
//		printMatrix2(tm.getColumn());
//		
	
//		ToeplitzCommitment tcc = new ToeplitzCommitment("1");
//		String mts = matrixToString(tcc.getToeplitzMatrix());
//		printMatrix(tcc.getToeplitzMatrix());
//		System.out.println(mts.length());
//		System.out.println(mts);
		
//		ArrayList al = new ArrayList<String>();
//	    al.add("1");
//	    
//	    HashTree ht = new HashTree(al);
//	    String root = ht.getRoot().getValue();
//	    System.out.println(root);
//	    
//	    String s7 = calcHash("1");
//	    String s8 = calcHash(s7);
//	    System.out.println(s8);
		printMatrix(b1);
		
//		List<String> l = new ArrayList<String>();
//		l.add("a");
//		l.add("b");
//		l.add("c");
//		l.add("d");
//		HashTree h = new HashTree(l);
//		List<CommitmentSet> setList = h.getSetList();
//		HashTree h2 = new HashTree(l, setList);
//	    String s = h.getRoot().getValue();
//	    String s2 = h2.getRoot().getValue();
//	    print(s);
//	    print(s2);
//	    
//	    List<String> l = new ArrayList<String>();
//		List<Integer> l2 = new ArrayList<Integer>();
//		List<String> l3 = new ArrayList<String>();
//		l.add("aa");
//		l.add("b");
//		l.add("c");
//		l.add("d");
//		l2.add(0);
//		l2.add(1);
//		l3.add("a");
//		l3.add("b");
//		l3.add("c");
//		l3.add("d");
//		for(int i = 0; i < l2.size(); i++) {
//			int index = l2.get(i);
//			l3.set(index , l.get(index));
//		}
//		print(l3.get(2));
//		SignatureCheck s = new SignatureCheck(l2, l, l3);
//		String ss1 = s.firstSignature(l);
//		String ss2 = s.secSignature(l3, l2);
//	    print(ss1);
//	    print(ss2);
//	    
//	    
//	    String i = padLeftZeros("123", 5);
//		print(i);
//		
//		addIndex(l);
//		print(l);
		
		List<String> l = new ArrayList<String>();
		List<Integer> l2 = new ArrayList<Integer>();
		List<String> l3 = new ArrayList<String>();
		List<String> l4 = new ArrayList<String>();
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
		
		String s= ch.calcChameleon("asdf");
//		String ss = ch.calcChameleon("asdf", ch.getAlpha(), ch.getBeta());
//		String s2 = ch.calcCollision("afassadf");
		String s3 = ch.calcCollision2("asdf", ch.getAlpha(), ch.getBeta());
		print(s);
		
		print(s3);
		
		
		ChameleonSigner cs = new ChameleonSigner(l2, l, ch);
		cs.setListWithChamHash();
		
		ChameleonEditor ce = new ChameleonEditor(l2, l3, ch, cs.getId());
		ce.setItemList(l);
		ce.calcChangedIndex();
		ce.setChRandom(cs.getChRandom());
		ce.setItemList4(cs.getItemList3());
		ce.setListWithNewRandom(ce.getChanges());
		ce.setListWithChamHash();
		
		print(ce.getItemList4());
		print(ce.getItemList5());
		
		print(cs.getChRandom().get(0).getAlpha());
		print(ce.getChRandom().get(0).getAlpha());
		print(cs.getChRandom().get(1).getAlpha());
		print(ce.getChRandom().get(1).getAlpha());
		print(cs.getItemList());
		print(ce.getItemList4());
		print(ce.getItemList5());
		
		
		
	}
	
	
	public static void addIndexToMsg(List<String> itemList, List<Integer> changableIndex) {
		List<String> itemList2 = new ArrayList<String>();
		itemList2 = itemList;
		for(int i = 0; i < changableIndex.size(); i++) {
			int index = changableIndex.get(i);
			String s = Integer.toString(index);
			String s1 = padLeftZeros(s, 5);
			String s2 = "hahaha"+ s1 + itemList2.get(index);
			itemList2.set(index , s2);
		}
			List<Integer> l = changableIndex;
			List<Integer> notChanged = new ArrayList<Integer>();
			int size = itemList.size();
			for(int i = 0; i < size; i++) {
				notChanged.add(i);
			}
			notChanged.removeAll(l);
			for(int i = 0; i < notChanged.size(); i++) {
				int index = notChanged.get(i);
				String s = Integer.toString(index);
				String s1 = padLeftZeros(s, 5);
				String s2 = itemList2.get(index) + s1;
				itemList2.set(index , s2);
			}
	}
	
	
	public static List<Integer> calcChangedIndex (List<String> l, List<String> l2) {
		List<Integer> l3 = new ArrayList<Integer>();
		if(l.size() != l2.size()) {
			System.err.println("Not the same size");
		}
		for(int i = 0; i < l.size(); i++) {
			if(!l.get(i).equals(l2.get(i))) {
				l3.add(i);
			}
		}
		return l3;
	}
	
	public static void addIndex(List<String> itemList) {
		for(int i = 0; i < itemList.size(); i++) {
			String s = Integer.toString(i);
			String s1 = padLeftZeros(s, 5);
			String s2 = itemList.get(i) + s1;
			itemList.set(i , s2);
		}
	}
	
	public static String matrixToString(int[][] intArray){
		String result = "";
		for (int i = 0; i < intArray.length; i++) {
			for (int j = 0; j < intArray[i].length; j++) {
				result += "" + intArray[i][j] ;
		    }
		}
		return result;
	}
	
	public static void printMatrix(int[][] sol) {
		for (int i = 0; i < sol.length; i++) {
			for (int j = 0; j < sol[i].length; j++) {
		        System.out.print(sol[i][j] + " ");
		    }
		    System.out.println();
		}
	}
	
	public static void printMatrix2(int[] sol) {
		for (int i = 0; i < sol.length; i++) {
		        System.out.print(sol[i] + " ");
		    }
		    System.out.println();
		}

	
	
	public static String padLeftZeros(String s, int i) {
		String str = String.format("%1$" + i + "s", s).replace(' ', '0');
		return str;
	}
	
	 private static String arrayToString(int[][] a){
	    	String s = "";
	    	for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					s = s + String.valueOf(a[i][j]);
			    }
			}
			return s;
	 }
	 
	 public static String calcHash(String msg) throws NoSuchAlgorithmException {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(msg.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String s = bigInt.toString(16);
			return s;
		}
	 
	 public static String toB(String hexString) {
		  String binaryString = new BigInteger(hexString, 16).toString(2);
		  return binaryString;
	 }
	 
	 public static void print(Object s) {
		 System.out.println(s);
	 }
	 

	}

		

