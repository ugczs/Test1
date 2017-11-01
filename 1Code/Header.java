/**
 * Headpart einer Email
 * @author Yuguan
 */

public class Header {
	private String from;
	private String to;
	private String date;
	private String type;
	private String headerContent;
	private String boundary;
	
	public Header(String headerContent) {
			this.headerContent = headerContent;
			getHeaderInfo();
	}
	
	public void getHeaderInfo() {
		this.from = returnTag(headerContent, "From");
		this.to = returnTag(headerContent, "To");
		this.date = returnTag(headerContent, "Date");
		this.type = returnTag(headerContent, "Content-Type");	
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
	
	
	public String getHeaderContent() {
		return headerContent;
	}

	/**
	 * Diese Methode liefert die Adresse des Absenders
	 */
	public String getFrom() {
		return from.replaceAll("From"+":", "").trim();
	}
	
	/**
	 * Diese Methode liefert die Adresse des Empfängers
	 */
	public String getTo() {
		return to.replaceAll("To"+":", "").trim();
	}
	
	/**
	 * Diese Methode liefert Datum in String
	 */
	public String getDate() {
		return date.replaceAll("Date"+":", "").trim();
	}
	
	/**
	 * Diese Methode liefert den Typ von Email
	 */
	public String getType() {
		return type.replaceAll("Content-Type"+":", "").trim();
	}
	
	/**
	 * Diese Methode liefert die Trennlinie für Bodypart
	 */
//	public String getBoundaryLine() {
//		String boundary = "";
//		String[] temp = this.type.split(";");
//		for(int i = 0; i < temp.length; i++) {
//			if(temp[i].contains("boundary"+"=")) {
//				boundary = temp[i].replaceAll("boundary"+"=", "").replaceAll("\\s", "").replaceAll("\"", "");
//			}
//		}
//		return boundary;
//	}
	
	/**
	 * Diese Methode liefert die Trennlinie für Bodypart
	 */
	public String getBoundaryLine() {
		String boundary = null;
		String[] temp = this.headerContent.split("\n");
		for(int i = 0; i < temp.length; i++) {
			if(temp[i].contains("\t" + "boundary" + "=")) {
				boundary = temp[i].replaceAll("boundary"+"=", "").replaceAll("\\s", "").replaceAll("\"", "");
			}
		}
		this.boundary = boundary;
		return boundary;
	}
	
	public boolean anyBoundaryLine() {
		if(this.boundary == null) {
			return true;
		}
		return false;
	}
	
}
