package de.htwg.battleship.observer;

public interface IObserver {

	/**
	 * Called when an event is triggered by an Observable object.
	 * @param e the raised event.
	 */
	void onNotifyObservers(Event e);
}