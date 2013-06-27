package controller;

import model.Bot;
import model.Destructor;
import model.Field;
import model.Field.state;
import model.Flattop;
import model.Human;
import model.Rowboat;
import model.Ships;
import observer.Event;
import observer.Observable;
import observer.Event.EventType;

/**
 * @author Sandro, Julian Our Controller Class!
 */
public class Controller extends Observable {

	private int fieldsize;
	private int input;
	private int[] lastBotShot;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int WAIT = 2000;

	private Human player;
	private Bot bot;
	private String statusLine = "Willkommen bei Battleship!";
	// Kam schon ein Input? true = ja, false = nein
	private boolean inp = false;

	public boolean isInput() {
		return inp;
	}

	@SuppressWarnings("unused")
	private Controller() {
	}

	/**
	 * Constructor for a new Controller. Needs fieldsize to create new Humans
	 * and Bots.
	 * 
	 * @param fieldsize
	 */
	public Controller(int x) {
	}

	/**
	 * Getter for the local "Status" of the game.
	 * 
	 * @return a String - the status.
	 */
	public String getStatus() {
		return this.statusLine;
	}

	/**
	 * Setter for the local "Status" of the game.
	 * 
	 * @param s
	 *            is the Status-string
	 */
	public void setStatus(String s) {
		this.statusLine = s;
		this.notifyObservers(new Event(EventType.onStatus));
	}

	/**
	 * Setter for the game's fieldsize.
	 * 
	 * @param x
	 *            an integer - the fieldsize
	 */
	public void setFieldsize(int x) {
		this.fieldsize = x;
	}

	/**
	 * Getter for the game's fieldsize.
	 * 
	 * @return an integer for the fieldsize
	 */
	public int getFieldsize() {
		return fieldsize;
	}

	/**
	 * Getter for the user input.
	 * 
	 * @return returns an integer for the user's input
	 */
	public int getInput() {
		return this.input;
	}

	/**
	 * Sets the global variable to the given input.
	 * 
	 * @param input
	 *            is the user's input
	 */
	public void setInput(int input) {
		this.input = input;
		inp = true;
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
	 * Inits the Players. A bot and a human.
	 * 
	 * @param fieldsize
	 *            - the fieldsize of the playboard
	 */
	public void initPlayers(int fieldsize) {
		player = new Human(fieldsize);
		bot = new Bot(fieldsize);
	}

	/**
	 * ShootBot shoots at the Bot's Playboard.
	 * 
	 * @param row
	 * @param col
	 */
	public boolean shootBot(int row, int col) {
		player.shoot(bot.getPlayboard().getField()[row][col]);
		if (bot.getPlayboard().getField()[row][col].getStat() == state.hit
				&& bot.getPlayboard().getField()[row][col].getShip().getSize() == 0) {
			bot.setNumberShips(bot.getNumberShips() - 1);
			setStatus("TREFFER!");
		}
		setStatus("Leider nichts getroffen...");
		inp = true;
		return hit(bot.getPlayboard().getField()[row][col]);
	}

	/**
	 * ShootHuman shoots at the Human's Playboard.
	 */
	public void shootHuman() {
		int[] botshot = bot.shoot(player.getPlayboard());
		if (player.getPlayboard().getField()[botshot[0]][botshot[1]].getShip() != null) {
			if (player.getPlayboard().getField()[botshot[0]][botshot[1]]
					.getShip().getSize() == 0) {
				player.setNumberShips(player.getNumberShips() - 1);
			}
		}
		lastBotShot = botshot;
	}

	/**
	 * Getter for the last shot of the bot.
	 * 
	 * @return an int-array of the shots where [0]:x-coordinate,
	 *         [1]:y-coordinate
	 */
	public int[] getLastBotShot() {
		return this.lastBotShot;
	}

	// Methods for setting the ships via the controller
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
	public void setHumanRowboat(int col, int row) {
		player.getPlayboard().setShip(new Rowboat(col, row));
		player.setNumberShips(player.getNumberShips() + 1);
		inp = true;
	}

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
	public void setHumanFlattop(int col, int row, boolean alignment) {
		player.getPlayboard().setShip(new Flattop(col, row, alignment));
		player.setNumberShips(player.getNumberShips() + 1);
		inp = true;
	}

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
	public void setHumanDestructor(int col, int row, boolean alignment) {
		player.getPlayboard().setShip(new Destructor(col, row, alignment));
		player.setNumberShips(player.getNumberShips() + 1);
		inp = true;
	}

	/**
	 * Sets a Rowboat onto the Bot's Playboard.
	 */
	public void setBotRowboat() {
		Ships r = new Rowboat();
		bot.setShip(r);
		bot.setNumberShips(bot.getNumberShips() + 1);
	}

	/**
	 * Sets a Flattop onto the Bot's Playboard.
	 * 
	 * @param alignment
	 *            horizontal or vertical
	 */
	public void setBotFlattop(boolean alignment) {
		bot.setShip(new Flattop(alignment));
		bot.setNumberShips(bot.getNumberShips() + 1);
	}

	/**
	 * Sets a Destructor onto the Bot's Playboard.
	 * 
	 * @param alignment
	 *            horizontal or vertical
	 */
	public void setBotDestructor(boolean alignment) {
		bot.setShip(new Destructor(alignment));
		bot.setNumberShips(bot.getNumberShips() + 1);
	}

	/**
	 * Checks if the shot hit a field or nor
	 * 
	 * @param f
	 *            the field to be checked
	 * @return true if hit, false if not
	 */
	public boolean hit(Field f) {
		if (f.getStat() == state.hit) {
			return true;
		}
		return false;
	}

	/**
	 * Setter for the input. If the input is done, inp will be set true.
	 * 
	 * @param in
	 *            - true if input is done, false if input is pending.
	 */
	public void setInput(boolean in) {
		this.inp = in;
	}

	/**
	 * Getter for the state of a field.
	 * 
	 * @param f
	 *            the field
	 * @return the state of the field
	 */
	public state getState(Field f) {
		return f.getStat();
	}

	/**
	 * Sets the bot's ships - gets randomly horizontal or vertical and sets it.
	 */
	public void setShipsBot() {
		setBotRowboat();
		if (fieldsize >= 3) {
			setBotDestructor(bot.vertical());
			if (fieldsize >= 8) {
				setBotFlattop(bot.vertical());
			}
		}
	}

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
	public int checkSetShipPosition(int shiptype, int x, int y,
			boolean alignment) {
		int f = player.getPlayboard().getSize() - 1;
		if (shiptype == 1) {
			if (!alignment) { // horizontal
				if (x + 2 > f + 1) {
					return x + 2 - f;
				}
			} else {
				if (y + 2 > f + 1) {
					return y + 2 - f;
				}
			}
		} else if (shiptype == 2) {
			if (!alignment) { // horizontal
				if (x + 4 > f + 1) {
					return x + 4 - f;
				}
			} else {
				if (y + 4 > f + 1) {
					return y + 4 - f;
				}
			}
		}
		return 0;
	}

	/**
	 * Checks if the Bot or the Player has won
	 * 
	 * @return 0 if nobody won, 1 if player, 2 if bot
	 */
	public int isGameOver() {
		if (player.getNumberShips() == 0) {
			setStatus("Game Over! Der Bot hat gewonnen!");
			return 2;
		} else if (bot.getNumberShips() == 0) {
			setStatus("Glückwunsch, du hast gewonnen!");
			return 1;
		}
		return 0;
	}

	/**
	 * Lets the thread sleep.
	 * 
	 * @param timeInMS
	 *            the time to sleep
	 */
	public void sleep(int timeInMS) {
		try {
			Thread.sleep(timeInMS);
		} catch (Exception e) {
		}
	}

	/**
	 * Checks if the bot or the human has won.
	 */
	public void gameOver() {
		int i = isGameOver();
		if (i == 2) {
			this.notifyObservers(new Event(EventType.gameOver));
		} else if (i == 1) {
			this.notifyObservers(new Event(EventType.won));
		} else {
			return;
		}
	}

	/**
	 * Wait function to wait for pending inputs.
	 * 
	 * @throws InterruptedException
	 */
	public void waitForInput() throws InterruptedException {
		while (!inp) {
			Thread.sleep(50);
		}
		inp = false;
	}

	/**
	 * The "game-Loop" function. Starts the playable game.
	 * 
	 * @throws InterruptedException
	 */
	public void gameLoop() throws InterruptedException {
		int turn = 0;
		waitForInput();
		this.notifyObservers(new Event(Event.EventType.setFieldsize));
		initPlayers(getFieldsize());
		setStatus("Bitte das Ruderboot setzen!");
		this.notifyObservers(new Event(Event.EventType.setRowboat));
		waitForInput();
		if (fieldsize >= 3) {
			setStatus("Bitte den Zerstörer setzen!");
			this.notifyObservers(new Event(Event.EventType.setDestructor));
			waitForInput();
			if (fieldsize >= 8) {
				setStatus("Bitte den Flugzeugträger setzen!");
				this.notifyObservers(new Event(Event.EventType.setFlattop));
				waitForInput();
			}
		}
		// set the bot's ships
		setStatus("Der Bot setzt seine Schiffe...");
		setShipsBot();
		sleep(2000);
		setStatus("\nOkay, los geht's!");

		while (true) {
			if (turn == 0) {
				setStatus("Du bist am Zug! Schieße auf den Bot!");
				this.notifyOnShowMenu();
				this.notifyOnAction();
				if (input == ONE) {
					System.out.println("Input 1");
					this.notifyOnShowPlayersField();
					continue;
				} else if (input == TWO) {
					System.out.println("Input 2");
					this.notifyOnShootOnBot();
					gameOver();
					this.notifyOnShowBotsField();
				} else if (input == THREE) {
					System.out.println("Input 3");
					setStatus("Vielen Dank für's Spielen! Bis Bald!\n");
					break;
				} else if (input == FOUR) {
					System.out.println("Input 4");
					this.notifyCheat();
					continue;
				} else if (input == 0) {
					System.out.println("Input 0!");
					turn = 1;
					gameOver();
					continue;
				}
				input = 0;
				turn = 1;
			} else {
				setStatus("Der Bot ist am Zug!");
				shootHuman();
				sleep(WAIT);
				this.notifyOnBotShoots();
				turn = 0;
				gameOver();
			}
		}
	}
}
