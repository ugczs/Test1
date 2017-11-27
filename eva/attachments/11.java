package Figures;

/**
 * Diese Klasse repräsentiert einen Bauplan für eine Figur
 * @author stern
 *
 */
public class FigureModel {
	
	/**
	 * Id dieses Models
	 */
	private int id;
	
	/**
	 * Die Id der Textur dieses Models
	 */
	private int textureId;
	
	/**
	 * Das Laufmuster dieses Models
	 */
	private Walkpattern pattern;
	
	/**
	 * Konstrukto setzt die Attribute
	 * @param id
	 * @param textureId
	 * @param pattern
	 */
	public FigureModel(int id, int textureId, Walkpattern pattern) {
		this.id = id;
		this.textureId = textureId;
		this.pattern = pattern;
	}
	
	/**
	 * Getter für modelId
	 * @return
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Getter für TexturId 
	 * @return
	 */
	public int getTextureId() {
		return this.id;
	}
	
	/**
	 * Getter für Laufmuster
	 * @return
	 */
	public Walkpattern getWalkPattern() {
		return this.pattern;
	}
}
