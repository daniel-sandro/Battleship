package de.htwg.battleship.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Observable implements IObservable {

	private List<IObserver> subscribers = new ArrayList<IObserver>(2);

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
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnSetFieldsize()
	 * signalisiert auf die änderung der fieldsize
	 */
	public void notifyOnSetFieldsize() {
		notifyObservers(new Event(Event.EventType.setFieldsize));
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnSetRowboat()
	 * berichtet die observer darüber dass ein rowboat gesetzt wurde
	 */
	public void notifyOnSetRowboat() {
		notifyObservers(new Event(Event.EventType.setRowboat));
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnSetDestructor()
	 * berichtet die observer darüber dass ein destructor gesetzt wurde
	 */
	public void notifyOnSetDestructor() {
		notifyObservers(new Event(Event.EventType.setDestructor));
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnSetFlattop()
	 * berichtet die observer darüber dass ein flattop gesetzt wurde
	 */
	public void notifyOnSetFlattop() {
		notifyObservers(new Event(Event.EventType.setFlattop));
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnShowMenu()
	 * signalisiert dass das menü aufgerufen werden soll
	 */
	public void notifyOnShowMenu() {
		notifyObservers(new Event(Event.EventType.showMenu));
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnAction()
	 * signalisiert dass ein event bearbeitet werden soll
	 */
	public void notifyOnAction() {
		notifyObservers(new Event(Event.EventType.onAction));
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnShowPlayersField()
	 * signalisiert, dass das Feld des Spielers ausgegeben werden soll
	 */
	public void notifyOnShowPlayersField() {
		notifyObservers(new Event(Event.EventType.showPlayersField));
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnShowBotsField()
	 * signalisiert dass das Feld des Bots ausgegeben werden soll
	 */
	public void notifyOnShowBotsField() {
		notifyObservers(new Event(Event.EventType.showBotsField));
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnShootOnBot()
	 * signalisiert dass auf den bot geschossen wurde
	 */
	public void notifyOnShootOnBot() {
		notifyObservers(new Event(Event.EventType.shootBot));
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnStatus()
	 * signalisiert eine statusänderung
	 */
	public void notifyOnStatus() {
		notifyObservers(new Event(Event.EventType.onStatus));
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyCheat()
	 * signalisiert dass das komplette feld des bots ausgegeben werden soll
	 */
	public void notifyCheat() {
		notifyObservers(new Event(Event.EventType.cheat));
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnGameOver()
	 * signalisiert das ende eines Spiels
	 */
	public void notifyOnGameOver() {
		notifyObservers(new Event(Event.EventType.gameOver));
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnWon()
	 * signalisiert dass der spieler gewonnen hat
	 */
	public void notifyOnWon() {
		notifyObservers(new Event(Event.EventType.won));
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnBotShoots()
	 * signalisiert dass der bot schießt
	 */
	public void notifyOnBotShoots() {
		notifyObservers(new Event(Event.EventType.botShoots));
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObservable#notifyOnRepaint()
	 * signalisiert dass die GUI neu gezeichnet werden soll.
	 */
	public void notifyOnRepaint() {
		notifyObservers(new Event(Event.EventType.onRepaint));
	}
}