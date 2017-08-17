
public class Test {

	public static void main(String[] args) throws Exception {
		// Eine Email wird eingelesen
		//MailReader mr = new MailReader("C:/Users/yu/Desktop/testmail.txt");
		//MailReader mr = new MailReader("C:/Users/yu/Desktop/email test/test1.eml");
		//MailReader mr = new MailReader("C:/Users/yu/Desktop/email test/test2.eml");
		//MailReader mr = new MailReader("C:/Users/yu/Desktop/email test/test3.eml");
		MailReader mr = new MailReader("C:/Users/yu/Desktop/email test/test4.txt");
		String s = mr.getMail();
		
		// gibt die Strings von testmail.txt aus
		print("test 1: \n" + s);
		
		
		MailParser mp = new MailParser(mr);
		// teilt Email in Head- und Body-Parts
		mp.splitMail(mr);
		mp.setHeader();
		
		//gibt Header aus
		print("test 1.1: \n" + mp.getHeader().getHeaderContent());
		// gibt Absender aus
		print("test 2: \n" + mp.getHeader().getFrom());
		// gibt Empfänger aus
		print("test 3: \n" + mp.getHeader().getTo());
		// gibt Datum in String aus
		print("test 4: \n" + mp.getHeader().getDate());
		// gibt Content-Type von Email aus
		print("test 5: \n" + mp.getHeader().getType());
		// gibt die Trennlinie für Bodypart aus
		print("test 6: \n" + mp.getHeader().getBoundaryLine());
		
		mp.setBody();
		//gibt Größe der BodyListe aus(sollte 3 sein)
		print("test 7: \n" + mp.getBody().getBodySize());
		//Inhalt von Bodyteil 0
		print("test 8: \n" + mp.getBody().getContent(0));
		//Inhalt von Bodyteil 1
		print("test 9: \n" + mp.getBody().getContent(1));
		//Inhalt von Bodyteil 2
		print("test 10: \n" + mp.getBody().getContent(2));
		//Inhalt von Bodyteil 3
		print("test 11: \n" + mp.getBody().getContent(3));
		//Inhalt vom gesamten Body
		print("test 12: \n" + mp.getBody().printBody());
		print ("test 99: \n" + mp.combineParts());
	}
	
	public static void print(Object o) {
		System.out.println(o);
	}

}
