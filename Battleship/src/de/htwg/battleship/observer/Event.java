package de.htwg.battleship.observer;

public class Event {

	private EventType temp;
	
	public enum EventType {
		setFieldsize, setRowboat, setDestructor, setFlattop, showMenu, onAction, showPlayersField,
		showBotsField, onStatus, shootBot, cheat, gameOver, won, botShoots, onRepaint, correctPosition
	}
	
	public Event(EventType t) {
		this.temp = t;
	}
	
	public EventType getEventType() {
		return this.temp;
	}
}