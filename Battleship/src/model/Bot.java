package model;

import model.Field.state;
import java.util.Random;

/**
 * @author Sandro, Julian Bot-Class. Subclass of Player. The AI.
 */
public class Bot extends Player {

	/**
	 * Constructor for the bot. Needs the fieldsize.
	 * 
	 * @param fieldsize
	 */
	public Bot(int fieldsize) {
		setPlayboard(new Playboard(fieldsize));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Player#initPlayboard(int)
	 */
	@Override
	public void initPlayboard(int size) {
		setPlayboard(new Playboard(size));
	}

	/**
	 * Creates a random number in the range [0, fieldsize].
	 * 
	 * @return the generated random number
	 */
	protected int initRandomNumber() {
		Random random = new Random();
		return random.nextInt(getPlayboard().getSize());
	}

	/**
	 * Shots at the given playboard board.
	 * 
	 * @param board
	 * @return a field of size 2 with the shotpositions
	 */
	public int[] shoot(Playboard board) {
		int[] shot = { 0, 0 };
		do {
			shot[0] = initRandomNumber();
			shot[1] = initRandomNumber();
		} while (board.getField()[shot[0]][shot[1]].getStat() == state.hit
				|| board.getField()[shot[0]][shot[1]].getStat() == state.emptyhit);

		board.getField()[shot[0]][shot[1]].shoot();
		return shot;
	}

	/**
	 * Sets a ship s on the bot's playboard.
	 * 
	 * @param s
	 */
	public void setShip(Ships s) {
		int[] posi = { 0, 0 };
		do {
			if (!s.getAlignment()) {
				do {
					posi[0] = initRandomNumber(); // x-pos
					posi[1] = initRandomNumber(); // y-pos
				} while (posi[0] + s.getSize() > getPlayboard().getSize());
			} else { // vert
				do {
					posi[0] = initRandomNumber(); // x-pos
					posi[1] = initRandomNumber();
				} while (posi[1] + s.getSize() > getPlayboard().getSize());
			}
		} while (!check(posi[0], posi[1], s)); // nochmal
		s.setPosition(posi[0], posi[1]);
		getPlayboard().setShip(s);
	}

	/**
	 * Checks if there is a ship on the given range (x + shipsize)
	 * 
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 * @param s
	 *            the ship
	 * @return false if there was a ship.
	 */
	private boolean check(int x, int y, Ships s) {
		if (!s.getAlignment()) { // horizontal
			for (int i = 0; i < s.getSize(); i++) {
				if (getPlayboard().getField()[x + i][y].getStat() == state.ship) {
					return false;
				}
			}
		} else {
			for (int i = 0; i < s.getSize(); i++) {
				if (getPlayboard().getField()[x][y + i].getStat() == state.ship) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Generates a random boolean for the bot to set the ship horizontal or
	 * vertical.
	 * 
	 * @return true if vertical, false if horizontal
	 */
	public boolean vertical() {
		if (initRandomNumber() <= (getPlayboard().getSize() / 2)) {
			return true;
		}
		return false;
	}
}
