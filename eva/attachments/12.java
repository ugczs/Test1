package de.chessbrawl.setup;

import java.util.LinkedList;

import Figures.Figure;
import de.chessbrawl.gameengine.Player;

/**
 * Diese Klasse modelliert ein Setup für eine Party
 * @author stern
 *
 */
public class GameEngineSetup {
	
	/**
	 * Spieler 1 und Sieler 2
	 */
	private Player playerOne, playerTwo;
	
	/**
	 * Die Figuren 
	 */
	private LinkedList<Figure> figures;
	/**
	 * Die quadratische Größe des Spielfelds
	 */
	private int size;
	
	/**
	 * Konstruktor setzt die Attribute
	 * @param one
	 * @param two
	 * @param figures
	 * @param size
	 */
	public GameEngineSetup(Player one, Player two,LinkedList<Figure> figures, int size) {
		this.playerOne = one;
		this.playerTwo = two;
		this.figures = figures;
		this.size = size;
	}
	
	/**
	 * Getter für Spieler1
	 * @return
	 */
	public Player getPlayerOne() {
		return playerOne;
	}
	/**
	 * Getter für Spieler2
	 * @return
	 */
	public Player getPlayerTwo() {
		return playerTwo;
	}
	
	/**
	 * Getter für die Figuren
	 * @return
	 */
	public LinkedList<Figure> getFigures() {
		return this.figures;
	}
	
	/**
	 * get für die Anzahl der Figuren
	 * @return
	 */
	public int getFigureSize() {
		return this.figures.size();
	}
	
	/**
	 * Getter für Spielfeldgröße
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
}
