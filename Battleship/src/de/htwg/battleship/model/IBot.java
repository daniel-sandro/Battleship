package de.htwg.battleship.model;

public interface IBot {

	void initPlayboard(int size);
	
	int initRandomNumber();
	
	int[] shoot(Playboard board);
	
	void setShip(Ships s);
	
	boolean check(int x, int y, Ships s);
	
	boolean vertical(); 
}
