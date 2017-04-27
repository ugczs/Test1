/**
 * Headpart einer Email
 * @author Yuguan
 */

public class Header {
	private String from;
	private String to;
	private String date;
	private String type;
	
	public Header(String from, String to, String date, String type) {
		this.from = from;
		this.to= to;
		this.date = date;
		this.type = type;	
	}
	
	/**
	 * Diese Methode liefert die Adresse des Absenders
	 */
	public String getFrom() {
		return from.replaceAll("From"+":", "").replaceAll("\\s", "");
	}
	
	/**
	 * Diese Methode liefert die Adresse des Empfängers
	 */
	public String getTo() {
		return to.replaceAll("To"+":", "").replaceAll("\\s", "");
	}
	
	/**
	 * Diese Methode liefert Datum in String
	 */
	public String getDate() {
		return date.replaceAll("Date"+":", "").replaceAll("\\s", "");
	}
	
	/**
	 * Diese Methode liefert den Typ von Email
	 */
	public String getType() {
		return type.replaceAll("Content-Type"+":", "").replaceAll("\\s", "");
	}
	
	/**
	 * Diese Methode liefert die Trennlinie für Bodypart
	 */
	public String getBoundaryLine() {
		String boundary = "";
		String[] temp = this.type.split(";");
		for(int i = 0; i < temp.length; i++) {
			if(temp[i].contains("boundary"+"=")) {
				boundary = temp[i].replaceAll("boundary"+"=", "").replaceAll("\\s", "").replaceAll("\"", "");
			}
		}
		return boundary;
	}
}
