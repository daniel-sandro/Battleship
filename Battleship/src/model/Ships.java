package model;

/**
 * @author Sandro
 * 
 */
public abstract class Ships {

	private int size;
	private int[] position = new int[2];
	// Alignment false = horizontal, true = vertical
	private boolean alignment = false;

	/**
	 * Getter for the ship's alignment. True if vertical, false if horizontal.
	 * 
	 * @return the alignment
	 */
	public boolean getAlignment() {
		return alignment;
	}

	/**
	 * Setter for the ship's alignment
	 * 
	 * @param a
	 *            the alignment. True if vertical, false if horizontal
	 */
	public void setAlignment(boolean a) {
		this.alignment = a;
	}

	/**
	 * Getter for the ship's size.
	 * 
	 * @return the size of the ship
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Getter for the ship's starting position.
	 * 
	 * @return int-array with [0]:x-position, [1]:y-position
	 */
	public int[] getPosition() {
		return this.position;
	}

	/**
	 * Setter for the ship's position.
	 * 
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 */
	public void setPosition(int x, int y) {
		this.position[0] = x;
		this.position[1] = y;
	}

	/**
	 * Setter for the ship's size.
	 * 
	 * @param a
	 *            the new size of the ship
	 */
	public void setSize(int a) {
		this.size = a;
	}
}
