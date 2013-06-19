package model;

/**
 * @author Sandro, Julian 
 * Flattop Class. Subclass of Ships.
 */
public class Flattop extends Ships {

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
	public Flattop(int row, int col, boolean alignment) {
		this.setSize(SIZE);
		setPosition(row, col);
		this.setAlignment(alignment);
	}

}
