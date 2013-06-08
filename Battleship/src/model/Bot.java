package model;

import model.Field.state;

public class Bot extends Player {

	/**
	 * Constructor for the bot. Needs the fieldsize.
	 * 
	 * @param fieldsize
	 */
	public Bot(int fieldsize) {
		setPlayboard(new Playboard(fieldsize));
	}

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
		return (int) (Math.random() * getPlayboard().getSize());
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
			do {
				posi[0] = initRandomNumber();
			} while (posi[0] + s.getSize() > getPlayboard().getSize());

			do {
				posi[1] = initRandomNumber();
			} while (posi[1] + s.getSize() > getPlayboard().getSize());
		} while (getPlayboard().getField()[posi[0]][posi[1]].getStat() == state.ship);
		s.setPosition(posi[0], posi[1]);
		getPlayboard().setShip(s);
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
