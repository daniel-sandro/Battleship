package de.htwg.battleship.model;

/**
 * @author Sandro, Julian
 * 
 */
public abstract class Player implements IPlayer {

	private int numberShips = 0;
	private Playboard playboard;

	/* (non-Javadoc)
	 * @see de.htwg.battleship.model.IPlayer#initPlayboard(int)
	 */
	public abstract void initPlayboard(int size);

	/* (non-Javadoc)
	 * @see de.htwg.battleship.model.IPlayer#getNumberShips()
	 */
	public int getNumberShips() {
		return numberShips;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.model.IPlayer#setNumberShips(int)
	 */
	public void setNumberShips(int numberShips) {
		this.numberShips = numberShips;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.model.IPlayer#getPlayboard()
	 */
	public Playboard getPlayboard() {
		return playboard;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.model.IPlayer#setPlayboard(de.htwg.battleship.model.Playboard)
	 */
	public void setPlayboard(Playboard playboard) {
		this.playboard = playboard;
	}
}
