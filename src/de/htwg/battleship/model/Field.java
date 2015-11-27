package de.htwg.battleship.model;

public class Field {

	private enum State {
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

	public boolean isHit() {
		return state == State.HIT;
	}

	public boolean isMissed() {
		return state == State.EMPTYHIT;
	}

	public boolean isNotHit() {
		return state == State.SHIP;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
		this.state = State.SHIP;
	}

	public void setMissed() {
		state = State.EMPTYHIT;
	}

	public void setHit() {
		state = State.HIT;
	}

}
