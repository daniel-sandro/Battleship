package de.htwg.battleship.model;

import java.util.Random;

public interface IBot {

	public void initPlayboard(int size);
	
	int initRandomNumber();
	
	public int[] shoot(Playboard board);
	
	public void setShip(Ships s);
	
	boolean check(int x, int y, Ships s);
	
	public boolean vertical(); 
}
