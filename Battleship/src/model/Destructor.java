package model;

/**
 * @author Sandro, Julian Destructor Class. Subclass of Ships.
 */
public class Destructor extends Ships {

	private static final int SIZE = 3;

	/**
	 * Constructor for the bot's Destructor.
	 * 
	 * @param vertikal
	 * @param horizontal
	 */
	public Destructor(boolean vertikal, boolean horizontal) {
		this.setSize(SIZE);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
	}

	/**
	 * Constructor for the Human's Destructor.
	 * 
	 * @param row
	 * @param col
	 * @param vertikal
	 * @param horizontal
	 */
	public Destructor(int row, int col, boolean vertikal, boolean horizontal) {
		this.setSize(SIZE);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
		setPosition(row, col);
	}
}
