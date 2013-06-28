package de.htwg.battleship.observer;

public class Event {

	/**
	 * Implementation of Events
	 * 
	 * @author muellerj, satonon
	 */
	private EventType temp;

	/**
	 * The EventType - for notifications
	 */
	public enum EventType {
		setFieldsize, setRowboat, setDestructor, setFlattop, showMenu, onAction, showPlayersField, showBotsField, onStatus, shootBot, cheat, gameOver, won, botShoots, 
		onRepaint, correctPosition
	}

	/**
	 * Constructor for a new event.
	 * 
	 * @param t
	 */
	public Event(EventType t) {
		this.temp = t;
	}

	/**
	 * Getter for the Eventtype of the Event object.
	 * 
	 * @return the eventtype of the event-object
	 */
	public EventType getEventType() {
		return this.temp;
	}
}