package de.htwg.battleship.model;

/**
 * @author Sandro, Julian 
 * The Human Class. Subclass of Player. Supports the Human's operands.
 */
public class Human extends Player {

	/**
	 * Constructor to create a Human-Object. Initializes a new Playboard with
	 * fieldsize size.
	 * 
	 * @param size
	 */
	public Human(int size) {
		setPlayboard(new Playboard(size));
	}

	/**
	 * Shoots onto a playboards field.
	 * 
	 * @param field
	 */
	public void shoot(Field field) {
		field.shoot();
	}

	@Override
	public void initPlayboard(int size) {
		setPlayboard(new Playboard(size));
	}
}
