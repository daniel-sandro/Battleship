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

	/*
	public void notifyObservers() {
		notifyObservers(null);
	}
	*/
	
	public void notifyOnSetFieldsize() {
		for ( Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			if (observer.onSetFieldsize()) {
				break;
			}
		}
	}

	public void notifyOnSetRowboat() {
		for ( Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			if (observer.onSetRowboat()) {
				break;
			}
		}
	}

	public void notifyOnSetDestructor() {
		for ( Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			if (observer.onSetDestructor()) {
				break;
			}
		}
	}

	public void notifyOnSetFlattop() {
		for ( Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			if (observer.onSetFlattop()) {
				break;
			}
		}
	}	
}