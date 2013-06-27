package de.htwg.battleship.model;

/**
 * @author Sandro, Julian 
 * Field Class. A Playboard is build of x (Fieldsize) Fields.
 */
public class Field {

	public enum state {
		empty, ship, hit, emptyhit, rowboat
	}

	private Ships ship;
	private state stat;

	/**
	 * Standard constructor for the Field class. Initializes a new field with
	 * state.empty.
	 */
	public Field() {
		this.stat = state.empty;
	}
	
	/**
	 * Getter for the state of the field.
	 * 
	 * @return state of the field
	 */
	public state getStat() {
		return stat;
	}

	/**
	 * Changes the state of the field if got shot.
	 * 
	 * @return the new state of the field
	 */
	public state shoot() {
		switch (this.stat) {
		case empty:
			this.stat = state.emptyhit;
			return state.emptyhit;
		case ship:
			this.ship.setSize(ship.getSize() - 1);
			this.stat = state.hit;
			return state.hit;
		default:
			return null;
		}
	}

	/**
	 * Getter for the containing Ship.
	 * 
	 * @return the containing Ship
	 */
	public Ships getShip() {
		return ship;
	}

	/**
	 * Changes the state to state.ship if the human/bot places a Ship on the
	 * field.
	 * 
	 * @param ship
	 */
	public void setShip(Ships ship) {
		this.ship = ship;
		this.stat = state.ship;
	}
}
