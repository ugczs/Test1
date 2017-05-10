import static org.junit.Assert.*;

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

}
