
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Diese Utility Klasse liest eine Email mit MIME über einen Url ein
 * @author Yuguan
 */
public class MailReader {
	private String url;
	private String mail;
	
	/**
	 * liest Text über einen Url ein und speichert
	 * diesen im Parameter mail
	 */
	public MailReader(String url)throws Exception{
		FileReader file = new FileReader(url);
		BufferedReader reader = new BufferedReader(file);
		String line = reader.readLine();
		StringBuilder sb = new StringBuilder();
		while (line != null) {
			sb.append(line);
            sb.append("\n");
			line = reader.readLine();
		}
		reader.close();
		this.mail = sb.toString();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	

}
