package controller;

import model.Bot;
import model.Destructor;
import model.Field;
import model.Field.state;
import model.Flattop;
import model.Human;
import model.Rowboat;

/**
 * @author Sandro, Julian Our Controller Class!
 */
public class Controller {

	private Human player;
	private Bot bot;

	/**
	 * Getter for the Player-Object.
	 * 
	 * @return the player
	 */
	public Human getPlayer() {
		return player;
	}

	/**
	 * Setter for the Player.
	 * 
	 * @param player
	 */
	public void setPlayer(Human player) {
		this.player = player;
	}

	/**
	 * Getter for the Bot-Object.
	 * 
	 * @return the Bot
	 */
	public Bot getBot() {
		return bot;
	}

	/**
	 * Setter for the Bot.
	 * 
	 * @param bot
	 */
	public void setBot(Bot bot) {
		this.bot = bot;
	}

	/**
	 * Constructor for a new Controller. Needs fieldsize to create new Humans
	 * and Bots.
	 * 
	 * @param fieldsize
	 */
	public Controller(int fieldsize) {
		player = new Human(fieldsize);
		bot = new Bot(fieldsize);
	}

	/**
	 * ShootBot shoots at the Bot's Playboard.
	 * @param row
	 * @param col
	 */
	public boolean shootBot(int row, int col) {
		player.shoot(bot.getPlayboard().getField()[row][col]);
		if (bot.getPlayboard().getField()[row][col].getStat() == state.hit
				&& bot.getPlayboard().getField()[row][col].getShip().getSize() == 0)
		{
			bot.setNumberShips(bot.getNumberShips() - 1);
		}
		return hit(bot.getPlayboard().getField()[row][col]);
	}

	/**
	 * ShootHuman shoots at the Human's Playboard.
	 */
	public void shootHuman() {
		int[] botshot = bot.shoot(player.getPlayboard());
		if (player.getPlayboard().getField()[botshot[0]][botshot[1]].getShip() != null) {
			player.setNumberShips(player.getNumberShips() - 1);
		}
	}

	// Methods for setting the ships via the controller
	/**
	 * Sets a Rowboat onto the Human's Playboard.
	 * 
	 * @param row the Row
	 * @param col the Column
	 * @param vertikal whether the ship shall be set vertical
	 * @param horizontal whether the ship shall be set horizontal
	 */
	public void setHumanRowboat(int row, int col, boolean vertikal,
			boolean horizontal) {
		player.getPlayboard().setShip(
				new Rowboat(row, col, vertikal, horizontal));
		player.setNumberShips(player.getNumberShips() + 1);
	}

	/**
	  * Sets a Flattop onto the Human's Playboard.
	 * 
	 * @param row the Row
	 * @param col the Column
	 * @param vertikal whether the ship shall be set vertical
	 * @param horizontal whether the ship shall be set horizontal
	 */
	public void setHumanFlattop(int row, int col, boolean vertikal,
			boolean horizontal) {
		player.getPlayboard().setShip(
				new Flattop(row, col, vertikal, horizontal));
		player.setNumberShips(player.getNumberShips() + 1);
	}

	/**
	 * * Sets a Destructor onto the Human's Playboard.
	 * 
	 * @param row the Row
	 * @param col the Column
	 * @param vertikal whether the ship shall be set vertical
	 * @param horizontal whether the ship shall be set horizontal
	 */
	public void setHumanDestructor(int row, int col, boolean vertikal,
			boolean horizontal) {
		player.getPlayboard().setShip(
				new Destructor(row, col, vertikal, horizontal));
		player.setNumberShips(player.getNumberShips() + 1);
	}

	/**
	 * Sets a Rowboat onto the Bot's Playboard.
	 * 
	 * @param vertikal whether the ship shall be set vertical
	 * @param horizontal whether the ship shall be set horizontal
	 */
	public void setBotRowboat(boolean vertikal, boolean horizontal) {
		bot.setShip(new Rowboat(vertikal, horizontal));
		bot.setNumberShips(bot.getNumberShips() + 1);
	}

	/**
	 * Sets a Flattop onto the Bot's Playboard.
	 * 
	 * @param vertikal whether the ship shall be set vertical
	 * @param horizontal whether the ship shall be set horizontal
	 */
	public void setBotFlattop(boolean vertikal, boolean horizontal) {
		bot.setShip(new Flattop(vertikal, horizontal));
		bot.setNumberShips(bot.getNumberShips() + 1);
	}

	/**
	 * Sets a Destructor onto the Bot's Playboard.
	 * 
	 * @param vertikal whether the ship shall be set vertical
	 * @param horizontal whether the ship shall be set horizontal
	 */
	public void setBotDestructor(boolean vertikal, boolean horizontal) {
		bot.setShip(new Destructor(vertikal, horizontal));
		bot.setNumberShips(bot.getNumberShips() + 1);
	}
	
	public boolean hit(Field f)
	{
		if(f.getStat() == state.hit)
		{
			return true;
		}
		return false;
	}
}


