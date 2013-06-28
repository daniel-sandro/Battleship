package de.htwg.battleship.observer;

public interface IObserver {

	public void onSetFieldsize();
	
	public void onSetRowboat();
	
	public void onSetDestructor();
	
	public void onSetFlattop();
	
	public void onShowMenu();
	
	public void onAction();
	
	public void onShowPlayersField();
	
	public void onShowBotsField();
	
	public void onShootOnBot();
	
	public void onStatus();
	
	public void onNotifyObservers(Event t);
	
	public void onCheat();
	
	public void onGameOver();
	
	public void onWon();
	
	public void onBotShoots();
	
	public void onRepaint();
}