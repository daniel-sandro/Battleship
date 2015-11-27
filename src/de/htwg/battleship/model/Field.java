package de.htwg.battleship.model;

public class Field {

	public enum State {
		EMPTY, SHIP, HIT, EMPTYHIT
	}

	private Ship ship;
	private State state;

	public Field() {
		this.state = State.EMPTY;
	}

	public boolean isEmpty() {
		return state == State.EMPTY;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
		this.state = State.SHIP;
	}

	public void setState(State newState) {
		this.state = newState;
	}
}
