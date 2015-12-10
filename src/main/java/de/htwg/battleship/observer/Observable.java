package de.htwg.battleship.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Observable implements IObservable {

	private List<IObserver> subscribers = new ArrayList<>();

	/* (non-Javadoc)
	 * @see IObservable#addObserver(IObserver)
	 * adds an observer
	 */
	public void addObserver(IObserver s) {
		subscribers.add(s);
	}

	/* (non-Javadoc)
	 * @see IObservable#removeObserver(IObserver)
	 * removes an observer
	 */
	public void removeObserver(IObserver s) {
		subscribers.remove(s);
	}

	/* (non-Javadoc)
	 * @see IObservable#removeAllObservers()
	 * removes all observers
	 */
	public void removeAllObservers() {
		subscribers.clear();
	}

	/* (non-Javadoc)
	 * @see IObservable#notifyObservers(Event)
	 * signalisiert allen observern ein Event
	 */
	public void notifyObservers(Event t) {
		for (Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			observer.onNotifyObservers(t);
		}
	}

}