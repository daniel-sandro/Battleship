package model;

/**
 * @author Sandro, Julian
 * Flattop Class. Subclass of Ships.
 */
public class Flattop extends Ships {
	
	private static final int SIZE = 5;
	
	/**
	 * Constructor for the bot's Flattop.
	 * @param vertikal
	 * @param horizontal
	 */
	public Flattop(boolean vertikal, boolean horizontal){
		this.setSize(SIZE);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
	}
	
	/**
	 * Constructor for the human's Flattop.
	 * @param row
	 * @param col
	 * @param vertikal
	 * @param horizontal
	 */
	public Flattop(int row, int col, boolean vertikal, boolean horizontal) {
		this.setSize(SIZE);
		setPosition(row, col);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
	}
	
}
