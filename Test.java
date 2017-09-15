import java.util.ArrayList;
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
		
		String s  = "101011110";
		String[] stringArray = s.split("");
		int[][] intArray = new int[stringArray.length][1];
		for (int i = 0; i < stringArray.length; i++) {
			intArray[i][0] = Integer.parseInt(stringArray[i]);
		}
		for (int i = 0; i < intArray.length; i++) {
			for (int j = 0; j < intArray[i].length; j++) {
		        System.out.print(intArray[i][j] + " ");
		    }
		    System.out.println();
		}
		
		BitwiseCalculation bc = new BitwiseCalculation();
		System.out.println(bc.add(0, 0));
		System.out.println("take care");
		int[][] a1 = {{1, 0}, {0, 1}, {1, 1}};
		int[][] b = {{1}, {1}};
		int[][] sol= bc.multiplyMatrix(a1, b);
		for (int i = 0; i < sol.length; i++) {
			for (int j = 0; j < sol[i].length; j++) {
		        System.out.print(sol[i][j] + " ");
		    }
		    System.out.println();
		}
		System.out.println(a1[0].length); //spalte
		System.out.println(a1.length);
		System.out.println("take care2");
		int[][] b1 = {{1}, {0}, {1}, {1},{0}};
		String sa = arrayToString(b1);
		System.out.println(sa);
		System.out.println("take care3");
		ToeplitzCommitment tc = new ToeplitzCommitment("1");
		int[][] aaa = {{1}, {0}, {1}, {1}, {0}};
		String sss = tc.intArrayToBitString(aaa);
		System.out.println(tc.getZ());
		
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

}		

