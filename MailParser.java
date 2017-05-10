
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Diese Utility Klasse Parst einem die Strings, die über MailReader eingelesen werden
 * @author Yuguan
 */
public class MailParser {
	private MailReader mr;
	private Header header;
	private Body body;
	private String[] mailParts;
	
	public MailParser(MailReader mr) {
		this.mr = mr;
	}
	
	
	public MailReader getRm() {
		return mr;
	}

	public void setRm(MailReader mr) {
		this.mr = mr;
	}

	public Header getHeader() {
		return header;
	}
	
	/**
	 * Header von Email wird gesetzt
	 */
//	public void setHeader() {
//		try {
//			String s = this.mailParts[0];
//			String from = returnTag(s, "From");
//			String to = returnTag(s, "To");
//			String date = returnTag(s, "Date");
//			String type = returnTag(s, "Content-Type");
//			this.header = new Header (from, to, date, type);
//		} 
//		catch (Exception e) {
//			System.out.println("Not an Email!");
//		}
//	}
	
	public void setHeader() {
		try {
			String s = this.mailParts[0];
			this.header = new Header (s);
		} 
		catch (Exception e) {
			System.out.println("Not an Email!");
		}
	}
	
	/**
	 * Diese Methode liefert den Wert eines Tags zurück
	 * @param s übergebene String
	 * @param tag übergebener Tag
	 * @return Wert von Tag
	 */
	public String returnTag(String s, String tag) {
		s.replaceAll(" ", "");
		String[] temp = s.split("\n");
		for(int i = 0; i < temp.length; i++) {
			if(temp[i].contains(tag+":")) {
				return temp[i];
			}
		}
		return null;
	}
	
	/**
	 * Diese Methode teilt die Strings, die über MailReader eingelesen worden sind
	 * in Header- und Body-Parts 
	 * @param mr eingelesene Strings
	 */
	public void splitMail (MailReader mr) {
		try {
			String[] mailParts = mr.getMail().split("\n\n", 2);
			this.mailParts = mailParts;
		} 
		catch (Exception e) {
			System.out.println("An Email has two parts!");
		}
	}

	public Body getBody() {
		return body;
	}
	
	/**
	 * Body von Email wird gesetzt
	 */
	public void setBody() {
		try {
			String s = this.mailParts[1];
			String boundary = this.header.getBoundaryLine();
			if(boundary == null) {
				List<String> bodyParts = new ArrayList<String>();
				bodyParts.add(s);
				this.body = new Body(bodyParts);
			}
			else {
				ArrayList<String> bodyParts = new ArrayList<>(Arrays.asList(s.split("(?=--"+boundary+")")));
				this.body = new Body(bodyParts);
			}
		}
		catch(Exception e){
			System.out.println("Not a Body!");
		}
		
	}
	
	public String combineParts() {
		try{
			String s  = "";
			String s1 = header.getHeaderContent();
			String s2 = body.printBody();
			s = s1 + "\n\n" + s2;
			return s;
		}
		catch(Exception e) {
			System.out.println("No Header or Body!");
		}
		return null;
	}
	
}
