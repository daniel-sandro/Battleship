package observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Observable implements IObservable {

	private List<IObserver> subscribers = new ArrayList<IObserver>(2);

	public void addObserver(IObserver s) {
		subscribers.add(s);
	}

	public void removeObserver(IObserver s) {
		subscribers.remove(s);
	}

	public void removeAllObservers() {
		subscribers.clear();
	}

	public void notifyObservers(Event t) {
		for (Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			observer.onNotifyObservers(t);
		}
	}
	
	public void notifyOnSetFieldsize() {
		notifyObservers(new Event(Event.EventType.setFieldsize));
	}

	public void notifyOnSetRowboat() {
		notifyObservers(new Event(Event.EventType.setRowboat));
	}

	public void notifyOnSetDestructor() {
		notifyObservers(new Event(Event.EventType.setDestructor));
	}

	public void notifyOnSetFlattop() {
		notifyObservers(new Event(Event.EventType.setFlattop));
	}
	
	public void notifyOnShowMenu() {
		notifyObservers(new Event(Event.EventType.showMenu));
	}
	
	public void notifyOnAction() {
		notifyObservers(new Event(Event.EventType.onAction));
	}

	public void notifyOnShowPlayersField() {
		notifyObservers(new Event(Event.EventType.showPlayersField));
	}
	
	public void notifyOnShowBotsField(boolean withShip) {
		for (Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			observer.onShowBotsField(withShip);
		}
	}
	
	public void notifyOnShootOnBot() {
		notifyObservers(new Event(Event.EventType.shootBot));
	}
	
	public void notifyOnStatus() {
		notifyObservers(new Event(Event.EventType.onStatus));
	}
}