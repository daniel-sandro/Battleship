package de.htwg.battleship.observer;

public interface IObserver {

	void onSetFieldsize();
	
	void onSetRowboat();
	
	void onSetDestructor();
	
	void onSetFlattop();
	
	void onShowMenu();
	
	void onAction();
	
	void onShowPlayersField();
	
	void onShowBotsField();
	
	void onShootOnBot();
	
	void onStatus();
	
	void onNotifyObservers(Event t);
	
	void onCheat();
	
	void onGameOver();
	
	void onWon();
	
	void onBotShoots();
	
	void onRepaint();
}