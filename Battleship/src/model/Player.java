package model;

/**
 * @author Sandro
 * 
 */
public abstract class Player {

	private int numberShips = 0;
	private Playboard playboard;

	/**
	 * Initializer for a new playboard
	 * 
	 * @param size
	 *            the size of the new playboard
	 */
	public abstract void initPlayboard(int size);

	/**
	 * Getter for the player's number of remaining ships.
	 * 
	 * @return the number of ships
	 */
	public int getNumberShips() {
		return numberShips;
	}

	/**
	 * Setter for the player's remaining ships.
	 * 
	 * @param numberShips
	 *            the new value for the remaining ships
	 */
	public void setNumberShips(int numberShips) {
		this.numberShips = numberShips;
	}

	/**
	 * Getter for the player's playboard.
	 * 
	 * @return the players playboard
	 */
	public Playboard getPlayboard() {
		return playboard;
	}

	/**
	 * Setter for the player's playboard
	 * 
	 * @param playboard
	 *            the new playboard
	 */
	public void setPlayboard(Playboard playboard) {
		this.playboard = playboard;
	}
}
