package de.htwg.battleship.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Observable implements IObservable {

	private List<IObserver> subscribers = new ArrayList<>();

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#addObserver(de.htwg.battleship.observer.IObserver)
	 * adds an observer
	 */
	public void addObserver(IObserver s) {
		subscribers.add(s);
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#removeObserver(de.htwg.battleship.observer.IObserver)
	 * removes an observer
	 */
	public void removeObserver(IObserver s) {
		subscribers.remove(s);
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#removeAllObservers()
	 * removes all observers
	 */
	public void removeAllObservers() {
		subscribers.clear();
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyObservers(de.htwg.battleship.observer.Event)
	 * signalisiert allen observern ein Event
	 */
	public void notifyObservers(Event t) {
		for (Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			observer.onNotifyObservers(t);
		}
	}

}