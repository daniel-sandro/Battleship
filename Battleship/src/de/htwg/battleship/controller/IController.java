package de.htwg.battleship.controller;

import de.htwg.battleship.model.Bot;
import de.htwg.battleship.model.Field;
import de.htwg.battleship.model.Human;
import de.htwg.battleship.model.Field.state;
import de.htwg.battleship.observer.IObservable;

public interface IController extends IObservable {

	/**
	 * Getter for the local "Status" of the game.
	 * 
	 * @return a String - the status.
	 */
	String getStatus();
	
	/**
	 * Setter for the local "Status" of the game.
	 * 
	 * @param s
	 *            is the Status-string
	 */
	void setStatus(String s);
	
	/**
	 * Setter for the game's fieldsize.
	 * 
	 * @param x
	 *            an integer - the fieldsize
	 */
	void setFieldsize(int x);
	
	/**
	 * Getter for the game's fieldsize.
	 * 
	 * @return an integer for the fieldsize
	 */
	int getFieldsize();
	
	/**
	 * Getter for the user input.
	 * 
	 * @return returns an integer for the user's input
	 */
	int getInput();
	
	/**
	 * Getter for the Player-Object.
	 * 
	 * @return the player
	 */
	Human getPlayer();
	
	/**
	 * Setter for the Player.
	 * 
	 * @param player
	 */
	void setPlayer(Human player);
	
	/**
	 * Getter for the Bot-Object.
	 * 
	 * @return the Bot
	 */
	Bot getBot();
	
	/**
	 * Setter for the Bot.
	 * 
	 * @param bot
	 */
	void setBot(Bot bot);
	
	/**
	 * Inits the Players. A bot and a human.
	 * 
	 * @param fieldsize
	 *            - the fieldsize of the playboard
	 */
	void initPlayers(int fieldsize);
	
	/**
	 * ShootBot shoots at the Bot's Playboard.
	 * 
	 * @param row
	 * @param col
	 */
	boolean shootBot(int row, int col);
	
	/**
	 * ShootHuman shoots at the Human's Playboard.
	 */
	void shootHuman();
	
	/**
	 * Getter for the last shot of the bot.
	 * 
	 * @return an int-array of the shots where [0]:x-coordinate,
	 *         [1]:y-coordinate
	 */
	int[] getLastBotShot();
	
	/**
	 * Sets a Rowboat onto the Human's Playboard.
	 * 
	 * @param row
	 *            the Row
	 * @param col
	 *            the Column
	 * @param alignment
	 *            horizontal or vertical
	 */
	void setHumanRowboat(int col, int row);
	
	/**
	 * Sets a Flattop onto the Human's Playboard.
	 * 
	 * @param row
	 *            the Row
	 * @param col
	 *            the Column
	 * @param alignment
	 *            horizontal or vertical
	 */
	void setHumanFlattop(int col, int row, boolean alignment);
	
	/**
	 * * Sets a Destructor onto the Human's Playboard.
	 * 
	 * @param row
	 *            the Row
	 * @param col
	 *            the Column
	 * @param alignment
	 *            horizontal or vertical
	 */
	void setHumanDestructor(int col, int row, boolean alignment);
	
	/**
	 * Sets a Rowboat onto the Bot's Playboard.
	 */
	void setBotRowboat();
	
	/**
	 * Sets a Flattop onto the Bot's Playboard.
	 * 
	 * @param alignment
	 *            horizontal or vertical
	 */
	void setBotFlattop(boolean alignment);
	
	/**
	 * Sets a Destructor onto the Bot's Playboard.
	 * 
	 * @param alignment
	 *            horizontal or vertical
	 */
	void setBotDestructor(boolean alignment);
	
	/**
	 * Checks if the shot hit a field or nor
	 * 
	 * @param f
	 *            the field to be checked
	 * @return true if hit, false if not
	 */
	boolean hit(Field f);
	
	/**
	 * Getter for the state of a field.
	 * 
	 * @param f
	 *            the field
	 * @return the state of the field
	 */
	state getState(Field f);
	
	
	/**
	 * Sets the bot's ships - gets randomly horizontal or vertical and sets it.
	 */
	void setShipsBot();
	
	
	/**
	 * Checks whether the given position of the ship to set is valid.
	 * 
	 * @param shiptype
	 *            0: rowboat, 1: destroyer 2: flattop
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @param alignment
	 *            false if horizontal, true if vertical
	 * @return 0 if ok, x for the fields to adjust
	 */
	int checkSetShipPosition(int shiptype, int x, int y, boolean alignment);
	
	
	/**
	 * Checks if the Bot or the Player has won
	 * 
	 * @return 0 if nobody won, 1 if player, 2 if bot
	 */
	int isGameOver();
	
	
	/**
	 * Lets the thread sleep.
	 * 
	 * @param timeInMS
	 *            the time to sleep
	 */
	void sleep(int timeInMS);
	
	/**
	 * Checks if the bot or the human has won.
	 */
	boolean gameOver();
	
	/**
	 * Starts the Game
	 */
	void start();
	
	/**
	 * Validates the given input.
	 * 
	 * @param s
	 *            the input string.
	 * @return true if valid.
	 */
	boolean validateInput(String s);
	
	/**
	 * checks which input (s) is given and reacts accordingly
	 * @param s
	 * @return
	 */
	boolean input(String s);
	
	/**
	 * @return
	 * returns the correct position
	 */
	int getCorrectPos();

	/**
	 * @param correctPos
	 * sets the correct position
	 */
	void setCorrectPos(int correctPos);

	/**
	 * @return
	 * returns correct alignment
	 */
	boolean isCorrectAl();

	/**
	 * @param correctAl
	 * sets the correct alignment
	 */
	void setCorrectAl(boolean correctAl);
	
	public int getStep();

	public void setStep(int step);
}
