package de.htwg.battleship.observer;

public interface IObservable {

	/**
	 * adds observer to observable
	 * @param s
	 */
	void addObserver(IObserver s);

	/**
	 * removes observer 
	 * @param s
	 */
	void removeObserver(IObserver s);

	/**
	 * removes all the observers
	 */
	void removeAllObservers();

	/**
	 * sends signal to obsevers for them to perform specific actions
	 * @param e
	 */
	void notifyObservers(Event e);
	
}