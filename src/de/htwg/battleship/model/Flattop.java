package de.htwg.battleship.model;

/**
 * @author Sandro, Julian 
 * Flattop Class. Subclass of Ship.
 */
public class Flattop extends Ship {

	private static final int SIZE = 5;

	/**
	 * Constructor for the bot's Flattop.
	 * 
	 * @param alignment
	 */
	public Flattop(boolean alignment) {
		this.setSize(SIZE);
		this.setAlignment(alignment);
	}

	/**
	 * Constructor for the human's Flattop.
	 * 
	 * @param row
	 * @param col
	 * @param alignment
	 */
	public Flattop(int col, int row, boolean alignment) {
		this.setSize(SIZE);
		setPosition(col, row);
		this.setAlignment(alignment);
	}

}
