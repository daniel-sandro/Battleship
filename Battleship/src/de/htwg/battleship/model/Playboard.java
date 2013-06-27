package de.htwg.battleship.model;

/**
 * @author Sandro, Julian
 * The Playboard class.
 */
public class Playboard {
	private int size;
	private Field[][] field;

	/**
	 * Constructor for a new Playboard. Creates x (size) fields, which are the
	 * Playboard.
	 * 
	 * @param size
	 */
	public Playboard(int size) {
		this.size = size;
		field = new Field[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				field[i][j] = new Field();
			}
		}
	}

	/**
	 * Getter for the contained fields.
	 * 
	 * @return a matrix of the fields
	 */
	public Field[][] getField() {
		return field;
	}

	/**
	 * SetShip sets the state of a field to ship a at the position stored in a.
	 * 
	 * @param s
	 */
	public void setShip(Ships s) {
		int[] posi = s.getPosition();
		if (s.getAlignment()) {
			for (int i = 0; i < s.getSize(); i++) {
				this.field[posi[1] + i][posi[0]].setShip(s);
			}
		} else {
			for (int i = 0; i < s.getSize(); i++) {
				this.field[posi[1]][posi[0] + i].setShip(s);
			}
		}
	}

	/**
	 * Getter for the Playboard size.
	 * 
	 * @return the size of the Playboard
	 */
	public int getSize() {
		return size;
	}
}
