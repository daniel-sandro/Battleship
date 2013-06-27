package observer;

public class Event {

	private EventType temp;
	
	public enum EventType {
		setFieldsize, setRowboat, setDestructor, setFlattop, showMenu, onAction, showPlayersField,
		showBotsField, onStatus, shootBot, cheat, gameOver, won, botShoots
	}
	
	public Event(EventType t) {
		this.temp = t;
	}
	
	public EventType getEventType() {
		return this.temp;
	}
}