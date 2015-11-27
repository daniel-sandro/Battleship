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
		SET_FIELDSIZE,
		SET_ROWBOAT,
		SET_DESTRUCTOR,
		SET_FLATTOP,
		SHOW_MENU,
		ON_ACTION,
		SHOW_PLAYERS_FIELD,
		SHOW_BOTS_FIELD,
		ON_STATUS,
		SHOOT_BOT,
		CHEAT,
		GAME_OVER,
		WON,
		BOT_SHOOTS,
		ON_REPAINT,
		CORRECT_POSITION
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