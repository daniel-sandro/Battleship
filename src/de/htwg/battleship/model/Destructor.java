package de.htwg.battleship.model;

/**
 * @author Sandro, Julian 
 * Destructor Class. Subclass of Ships.
 */
public class Destructor extends Ships {

	private static final int SIZE = 3;

	/**
	 * Constructor for the bot's Destructor.
	 * 
	 * @param alignment
	 */
	public Destructor(boolean alignment) {
		this.setSize(SIZE);
		this.setAlignment(alignment);
	}

	/**
	 * Constructor for the Human's Destructor.
	 * 
	 * @param row
	 * @param col
	 * @param alignment
	 */
	public Destructor(int col, int row, boolean alignment) {
		this.setSize(SIZE);
		this.setAlignment(alignment);
		setPosition(col, row);
	}
}
