package controller;

import java.io.ObjectInputStream.GetField;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import view.TUI;

import model.Bot;
import model.Destructor;
import model.Field;
import model.Field.state;
import model.Flattop;
import model.Human;
import model.Player;
import model.Rowboat;

/**
 * @author Sandro, Julian Our Controller Class!
 */
public class Controller extends Observable {
	
	private static final int HEX = 65;
	private static final int LINELEN = 9;
	private static final int THREE = 3;
	private static final int MAXFIELDSIZE = 26;
	private static final int WAIT = 2000;
	private static final String SEP = " | ";
	private static int fieldsize;
	
	private static StringBuilder sb = new StringBuilder();
	private static Scanner scanner = new Scanner(System.in);
	
	private static Human player;
	private Bot bot;
	private String statusLine = "Willkommen bei Battleship!";
	
	public String getStatus() {
		return this.statusLine;
	}
	
	public void setStatus(String s) {
		this.statusLine = s;
	}
	
	public void setFieldsize(int x) {
		this.fieldsize = x;
	}
	
	public int getFieldsize() {
		return fieldsize;
	}
	

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
	private Controller() {
	}
	
	public Controller(int x) {
	}
	
	public void initPlayers(int fieldsize) {
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
				&& bot.getPlayboard().getField()[row][col].getShip().getSize() == 0) {
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
		if(f.getStat() == state.hit) {
			return true;
		}
		return false;
	}
	
	public void howManyShipsBot() {
		setBotRowboat(true, false);
		if (fieldsize >= 3) {
			if (bot.vertical()) {
				setBotDestructor(true, false);
			} else {
				setBotDestructor(false, true);
			}
		} 
		if (fieldsize >= 8) {
			if (bot.vertical()) {
				setBotFlattop(true, false);
			} else {
				setBotFlattop(false, true);
			}
		}
	}
	
	public void howManyShipsHuman() {	
		setRowboat();
		if(fieldsize >= 3) {
			setDestructor();
		} 
		if (fieldsize >= 8) {
			setFlattop();
		}
	}
	
	public void setRowboat() {
		int x, y, nextBool;
		setStatus("Bitte das Ruderboot setzen: (X/Y)");
		this.notifyObservers();
		x = scanner.nextInt();
		y = scanner.nextInt();
		
		setStatus("Das Ruderboot vertikal (1) oder horizontal (2) setzen?");
		this.notifyObservers();
		
		while (true) {
			nextBool = scanner.nextInt();
			if (nextBool == 1) {
				setHumanRowboat(x, y, true, false);
				break;
			} else if (nextBool == 2) {
				setHumanRowboat(x, y, false, true);
				break;
			} else {
				setStatus("Falsche Eingabe! Bitte 1 (vertikal) oder 2 (horizontal) eingeben!");
				continue;
			}
		}
	}
	
	public void setDestructor() {
		int x, y, nextBool;
		setStatus("Bitte den Zerstörer setzen: (X/Y)");
		this.notifyObservers();
		x = scanner.nextInt();
		y = scanner.nextInt();
		
		setStatus("Das Zerstörer vertikal (1) oder horizontal (2) setzen?");
		this.notifyObservers();
		
		while (true) {
			nextBool = scanner.nextInt();
			if (nextBool == 1) {
				setHumanRowboat(x, y, true, false);
				break;
			} else if (nextBool == 2) {
				setHumanRowboat(x, y, false, true);
				break;
			} else {
				setStatus("Falsche Eingabe! Bitte 1 (vertikal) oder 2 (horizontal) eingeben!");
				continue;
			}
		}
	}
	
	public void setFlattop() {
		int x, y, nextBool;
		setStatus("Bitte den Flugzeugträger setzen: (X/Y)");
		this.notifyObservers();
		x = scanner.nextInt();
		y = scanner.nextInt();
		
		setStatus("Das Flugzeugträger vertikal (1) oder horizontal (2) setzen?");
		this.notifyObservers();
		
		while (true) {
			nextBool = scanner.nextInt();
			if (nextBool == 1) {
				setHumanRowboat(x, y, true, false);
				break;
			} else if (nextBool == 2) {
				setHumanRowboat(x, y, false, true);
				break;
			} else {
				setStatus("Falsche Eingabe! Bitte 1 (vertikal) oder 2 (horizontal) eingeben!");
				continue;
			}
		}
	}

	
	public void gameLoop() {
		setStatus("Bitte Feldgröße angeben:");
		this.notifyObservers(1);
		// TODO Events schicken die dann in der View oben ausgewertet werden!
		// z.b. sende eins (notify...(1)) um die begrüßung zu printen
		// der string wird oben in der tui komplett gebaut, also hier das setStatus weg machen!
		scanner.nextInt();
		
		howManyShipsBot();
		howManyShipsHuman();
		
		while (true) {
			this.notifyObservers();
			
			if (player.getNumberShips() == 0) {
				break;
			} else if (bot.getNumberShips() == 0) {
				break;
			}
		}
	}
}


