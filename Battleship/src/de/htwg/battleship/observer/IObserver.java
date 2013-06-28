package de.htwg.battleship.observer;

public interface IObserver {

	/**
	 * Reaktion auf das notifyonSetFieldsize
	 */
	void onSetFieldsize();
	
	/**
	 * Reaktion auf den notifyonsetrowboat
	 */
	void onSetRowboat();
	
	/**
	 * Reaktion auf den notifyonsetdestructor
	 */
	void onSetDestructor();
	
	/**
	 * Reaktion auf den notifyonsetflattop
	 */
	void onSetFlattop();
	
	/**
	 * Reaktion auf den notifyonshowmenu
	 */
	void onShowMenu();
	
	/**
	 * Reaktion auf den notifyonation
	 */
	void onAction();
	
	/**
	 * Reaktion auf den notifyonshowpplayersfield
	 */
	void onShowPlayersField();
	
	/**
	 * Reaktion auf den notifyonshowbotsfield
	 */
	void onShowBotsField();
	
	/**
	 * Reaktion auf den notifyonshootbot
	 */
	void onShootOnBot();
	
	/**
	 * Reaktion auf den notifyonstatus
	 */
	void onStatus();
	
	/**
	 * Reaktion auf den onnotifyobservers
	 * @param t
	 */
	void onNotifyObservers(Event t);
	
	/**
	 * Reaktion auf den notifyoncheat
	 */
	void onCheat();
	
	/**
	 * Reaktion auf den notifyongameover
	 */
	void onGameOver();
	
	/**
	 * Reaktion auf den notifyonwon
	 */
	void onWon();
	
	/**
	 * Reaktion auf den notifyonbotshoots
	 */
	void onBotShoots();
	
	/**
	 * Reaktion auf den notifyonrepaint
	 */
	void onRepaint();
}