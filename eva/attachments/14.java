package de.chessbrawl.gameengine;

/**
 * Diese Klasse modelliert einen Spieler
 *
 */
public class Player {
	
	/**
	 * Spielername
	 */
	private String playerName;
	
	/**
	 * SpielerId
	 */
	private int playerId;
	
	/**
	 * Konstrutor
	 * @param playerId
	 * @param playerName
	 */
	public Player(int playerId, String playerName) {
		this.playerId = playerId;
		this.playerName = playerName;
	}
	
	/**
	 * Getter für Namen
	 * @return
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	/**
	 * Getter für Id
	 * @return
	 */
	public int getPlayerId() {
		return playerId;
	}

}
