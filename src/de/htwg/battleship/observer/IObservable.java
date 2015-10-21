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
	 * signal f�r die Fieldsize
	 */
	void notifyOnSetFieldsize();

	/**
	 * Signale f�r das Setzen eines Rowboats
	 */
	void notifyOnSetRowboat();
	
	/**
	 * Signale f�r das Setzen eines Destrutors
	 */
	void notifyOnSetDestructor();
	
	/**
	 * Signale f�r das Setzen eiens Flattops
	 */
	void notifyOnSetFlattop();
	
	/**
	 * Signal f�r das Zeigen eines Men�s
	 */
	void notifyOnShowMenu();
	
	/**
	 * setzt die Action f�r die Gui, damit diese wei� was sie machen soll
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
	 * Signal zum schie�en auf den bot
	 */
	void notifyOnShootOnBot();
	
	/**
	 * Signal -> Bot schie�t auf Human
	 */
	void notifyOnBotShoots();
	
	/**
	 * Signal zum �bermitteln eines Status
	 */
	void notifyOnStatus();
	
	/**
	 * Signal f�r ein Spielende
	 */
	void notifyOnGameOver();
	
	/**
	 * Signal f�r ein gewonnenes Spiel
	 */
	void notifyOnWon();
	
	/**
	 * Signal zum neuzeichnen der GUI
	 */
	void notifyOnRepaint();
}