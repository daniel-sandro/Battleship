package de.htwg.battleship.controller;

import de.htwg.battleship.model.Bot;
import de.htwg.battleship.model.Field;
import de.htwg.battleship.model.Human;
import de.htwg.battleship.model.Field.state;
import de.htwg.battleship.observer.IObservable;

public interface IController extends IObservable {

	String getStatus();
	
	void setStatus(String s);
	
	void setFieldsize(int x);
	
	int getFieldsize();
	
	int getInput();
	
	Human getPlayer();
	
	void setPlayer(Human player);
	
	Bot getBot();
	
	void setBot(Bot bot);
	
	void initPlayers(int fieldsize);
	
	boolean shootBot(int row, int col);
	
	void shootHuman();
	
	int[] getLastBotShot();
	
	void setHumanRowboat(int col, int row);
	
	void setHumanFlattop(int col, int row, boolean alignment);
	
	void setHumanDestructor(int col, int row, boolean alignment);
	
	void setBotRowboat();
	
	void setBotFlattop(boolean alignment);
	
	void setBotDestructor(boolean alignment);
	
	boolean hit(Field f);
	
	state getState(Field f);
	
	void setShipsBot();
	
	int checkSetShipPosition(int shiptype, int x, int y, boolean alignment);
	
	int isGameOver();
	
	void sleep(int timeInMS);
	
	boolean gameOver();
	
	void start();
	
	boolean validateInput(String s);
	
	boolean input(String s);
	
	int getCorrectPos();

	void setCorrectPos(int correctPos);

	boolean isCorrectAl();

	void setCorrectAl(boolean correctAl);
}
