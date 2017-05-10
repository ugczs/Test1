import java.util.ArrayList;
import java.util.List;

/**
 * Bodypart einer Email
 * @author Yuguan
 */
public class Body {
	private List<String> bodyList = new ArrayList<String>();
	
	public Body(List<String> bodyList) {
		this.bodyList = bodyList;
	}
	
	public List<String> getBodyList() {
		return bodyList;
	}
	
	/**
	 * Diese Methode liefert Strings an der i. Stelle von der Liste
	 * @param i übergebene Stelle von der Liste
	 */
	public String getContent(int i) {
		if (i < bodyList.size()) {
			return bodyList.get(i).trim() + '\n';
		}
		else {
			System.out.println("Invalid Number!!");
			return null;
		}
	}
	
	/**
	 * Diese Methode liefert die Anzahl der Teile von Body
	 */
	public int getBodySize() {
		return bodyList.size();	
	}
	
	/**
	 * Diese Methode gibt den gesamten Body aus.
	 */
	public String printBody() {
		String s = "";
		for(String i : bodyList) {
			s = s + i;
			}
		return s;
	}
}
