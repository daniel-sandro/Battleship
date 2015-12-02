package de.htwg.battleship.observer;

public interface IObservable {

	/**
	 * adds observer to observable
	 * @param s
	 */
	void addObserver(IObserver s);

	/**
	 * removes observer 
	 * @param s
	 */
	void removeObserver(IObserver s);

	/**
	 * removes all the observers
	 */
	void removeAllObservers();

	/**
	 * sends signal to obsevers for them to perform specific actions
	 * @param e
	 */
	void notifyObservers(Event e);
	
	/**
	 * signal für die Fieldsize
	 */
	void notifyOnSetFieldsize();

	/**
	 * Signale für das Setzen eines Rowboats
	 */
	void notifyOnSetRowboat();
	
	/**
	 * Signale für das Setzen eines Destrutors
	 */
	void notifyOnSetDestructor();
	
	/**
	 * Signale für das Setzen eiens Flattops
	 */
	void notifyOnSetFlattop();
	
	/**
	 * Signal für das Zeigen eines Menüs
	 */
	void notifyOnShowMenu();
	
	/**
	 * setzt die Action für die Gui, damit diese weiü was sie machen soll
	 */
	void notifyOnAction();
	
	/**
	 * Signal zum zeigen des eigenen Spielfelds
	 */
	void notifyOnShowPlayersField();
	
	/**
	 * Signal zum zeigen des Spielfeldes ses Bots
	 */
	void notifyOnShowBotsField();
	
	/**
	 * Signal zum zeigen des cheats
	 */
	void notifyCheat();
	
	/**
	 * Signal zum schieüen auf den bot
	 */
	void notifyOnShootOnBot();
	
	/**
	 * Signal -> Bot schieüt auf Human
	 */
	void notifyOnBotShoots();
	
	/**
	 * Signal zum übermitteln eines Status
	 */
	void notifyOnStatus();
	
	/**
	 * Signal für ein Spielende
	 */
	void notifyOnGameOver();
	
	/**
	 * Signal für ein gewonnenes Spiel
	 */
	void notifyOnWon();
	
	/**
	 * Signal zum neuzeichnen der GUI
	 */
	void notifyOnRepaint();
}