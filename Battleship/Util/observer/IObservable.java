package observer;

public interface IObservable {

	void addObserver(IObserver s);

	void removeObserver(IObserver s);

	void removeAllObservers();

	//void notifyObservers();

	void notifyObservers(Event e);
	
	void notifyOnSetFieldsize();

	void notifyOnSetRowboat();
	
	void notifyOnSetDestructor();
	
	void notifyOnSetFlattop();
	
	void notifyOnShowMenu();
	
	void notifyOnAction();
	
	void notifyOnShowPlayersField();
	
	void notifyOnShowBotsField(boolean withShip);
	
	void notifyOnShootOnBot();
	
	void notifyOnStatus();
}