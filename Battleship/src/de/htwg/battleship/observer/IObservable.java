package de.htwg.battleship.observer;

public interface IObservable {

	void addObserver(IObserver s);

	void removeObserver(IObserver s);

	void removeAllObservers();

	void notifyObservers(Event e);
	
	void notifyOnSetFieldsize();

	void notifyOnSetRowboat();
	
	void notifyOnSetDestructor();
	
	void notifyOnSetFlattop();
	
	void notifyOnShowMenu();
	
	void notifyOnAction();
	
	void notifyOnShowPlayersField();
	
	void notifyOnShowBotsField();
	
	void notifyCheat();
	
	void notifyOnShootOnBot();
	
	void notifyOnBotShoots();
	
	void notifyOnStatus();
	
	void notifyOnGameOver();
	
	void notifyOnWon();
	
	void notifyOnRepaint();
}