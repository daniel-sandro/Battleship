package de.htwg.battleship.model;

public class Rowboat extends Ships {

	/**
	 * @author Sandro, Julian 
	 * Rowboat Class. Subclass of Ships.
	 */
	private static final int SIZE = 1;
	private static final boolean ALIGNMENT = true;
	
	/**
	 * Constructor for the bot's Rowboat.
	 * 
	 */
	public Rowboat(){
		this.setSize(SIZE);	
		this.setAlignment(ALIGNMENT);
	}
	
	/**
	 * Constructor for the Human's Rowboat.
	 * 
	 * @param row
	 * @param col
	 */
	public Rowboat(int col, int row) {
		this.setSize(SIZE);
		this.setAlignment(ALIGNMENT);
		setPosition(col, row);
	}
}
