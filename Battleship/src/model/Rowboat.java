package model;

public class Rowboat extends Ships {

	/**
	 * @author Sandro, Julian 
	 * Rowboat Class. Subclass of Ships.
	 */
	private static final int SIZE = 1;
	
	/**
	 * Constructor for the bot's Rowboat.
	 * 
	 * @param alignment
	 */
	public Rowboat(boolean alignment){
		this.setSize(SIZE);
		this.setAlignment(alignment);	
	}
	
	/**
	 * Constructor for the Human's Rowboat.
	 * 
	 * @param row
	 * @param col
	 * @param alignment
	 */
	public Rowboat(int row, int col, boolean alignment) {
		this.setSize(SIZE);
		setPosition(row, col);
		this.setAlignment(alignment);
	}
}
