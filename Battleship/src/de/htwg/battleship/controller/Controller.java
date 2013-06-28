package de.htwg.battleship.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.htwg.battleship.model.Bot;
import de.htwg.battleship.model.Destructor;
import de.htwg.battleship.model.Field;
import de.htwg.battleship.model.Flattop;
import de.htwg.battleship.model.Human;
import de.htwg.battleship.model.Rowboat;
import de.htwg.battleship.model.Ships;
import de.htwg.battleship.model.Field.state;
import de.htwg.battleship.observer.Event;
import de.htwg.battleship.observer.Observable;
import de.htwg.battleship.observer.Event.EventType;

/**
 * @author Sandro, Julian Our Controller Class!
 */
@Singleton
public class Controller extends Observable implements IController {

	private int fieldsize;
	private int input;
	private int[] lastBotShot;
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int EIGHT = 8;
	private static final int EXIT = 500;
	private int step;

	private int x = ZERO;
	private int y = ZERO;
	private boolean alignment = false;
	private int correctPos;
	private boolean correctAl;

	private Human player;
	private Bot bot;
	private String statusLine = "Willkommen bei Battleship!";

	/**
	 * Constructor for a new Controller. Needs fieldsize to create new Humans
	 * and Bots.
	 * 
	 * @param fieldsize
	 */
	@Inject
	public Controller() {
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.controller.IController#getStep()
	 */
	public int getStep() {
		return step;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.controller.IController#setStep(int)
	 */
	public void setStep(int step) {
		this.step = step;
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
		if (bot.getPlayboard().getField()[row][col].getStat() == state.hit) {
			if (bot.getPlayboard().getField()[row][col].getShip().getSize() == 0) {
				bot.setNumberShips(bot.getNumberShips() - 1);
			}
			setStatus("TREFFER!");
		} else {
			setStatus("Leider nichts getroffen...");
		}
		return hit(bot.getPlayboard().getField()[row][col]);
	}

	/**
	 * ShootHuman shoots at the Human's Playboard.
	 */
	public void shootHuman() {
		int[] botshot = bot.shoot(player.getPlayboard());
		if (player.getPlayboard().getField()[botshot[0]][botshot[1]].getShip() != null && 
				player.getPlayboard().getField()[botshot[0]][botshot[1]].getShip().getSize() == 0) {
				player.setNumberShips(player.getNumberShips() - 1);
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
		if (fieldsize >= THREE) {
			setBotDestructor(bot.vertical());
			if (fieldsize >= EIGHT) {
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
			if (!alignment) { 
				if (x + 2 > f) {
					return x + 2 - f;
				}
			} else {
				if (y + 2 > f) {
					return y + 2 - f;
				}
			}
		} else if (shiptype == 2) {
			if (!alignment) {
				if (x + FOUR > f) {
					return x + FOUR - f;
				}
			} else {
				if (y + FOUR > f) {
					return y + FOUR - f;
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
	public boolean gameOver() {
		int i = isGameOver();
		if (i == 2) {
			this.notifyObservers(new Event(EventType.gameOver));
			return true;
		} else if (i == 1) {
			this.notifyObservers(new Event(EventType.won));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets the status of the Controller to "Der Bot setzt seine Schiffe..."
	 * and notifys the observers about how to treat a mouseaction
	 * step is then THREE
	 */
	private void botTurn() {
		setStatus("Der Bot setzt seine Schiffe...");
		notifyObservers(new Event(Event.EventType.onAction));
		setStatus("Du bist am Zug! Schieße auf den Bot! (X/Y)");
		step = THREE;
	}

	/**
	 * Starts the Game
	 */
	public void start() {
		initPlayers(getFieldsize());
		setShipsBot();
		setStatus("Bitte das Ruderboot setzen!");
		step = ZERO;
		this.notifyObservers(new Event(Event.EventType.setRowboat));
	}

	/**
	 * Validates the given input.
	 * 
	 * @param s
	 *            the input string.
	 * @return true if valid.
	 */
	public boolean validateInput(String s) {
		String[] split = s.split(" ");
		switch (step) {
			case ZERO: 
			case FOUR:
			case THREE:
				if (split.length != TWO) {
					setStatus("Falsche eingabe");
					return false;
				}
				y = Integer.valueOf(split[1]);
				break;
			case ONE:
			case TWO:
				if (split.length != THREE) {
					setStatus("Falsche eingabe");
					return false;
				}
				y = Integer.valueOf(split[1]);
				int z = Integer.valueOf(split[2]);
				if (z == 1) {
					alignment = false;
				}
				break;
			default: 
				if (split.length == TWO) {
					step = THREE;
					y = Integer.valueOf(split[1]);
				}
		}
		x = Integer.valueOf(split[0]);
		return true;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.controller.IController#input(java.lang.String)
	 * checks which input is given and reacts accordingly
	 */
	public boolean input(String s) {
		validateInput(s);

		switch (step) {
			case ZERO:
				setHumanRowboat(x, y);
				if (fieldsize >= THREE) {
					setStatus("Bitte den Zerstörer setzen! (X Y \"alignment\")");
					notifyObservers(new Event(Event.EventType.setDestructor));
					step++;
				} else {
					botTurn();
				}
				break;
			case ONE:
				int t = checkSetShipPosition(1, x, y, alignment);
				if (t != 0) {
					correctAl = alignment;
					correctPos = t;
					notifyObservers(new Event(EventType.correctPosition));
					return true;
				}
				setHumanDestructor(x, y, alignment);
				if (fieldsize >= EIGHT) {
					setStatus("Bitte den Flugzeugträger setzen! (X Y \"alignment\")");
					notifyObservers(new Event(Event.EventType.setFlattop));
					step++;
				} else {
					botTurn();
				}
				break;
			case TWO: 
				setHumanFlattop(x, y, alignment);
				botTurn();
				break;
			case THREE:
				shootBot(y, x);
				step++;
				gameOver();
				input("0 0"); 
				break;
			case FOUR: 
				setStatus("Der Bot ist am Zug!");
				shootHuman();
				notifyObservers(new Event(EventType.showMenu));
				step++;
				gameOver();
				break;
			case FIVE: 
				if (!five()) {
					return false;
				}
		}
		notifyObservers(new Event(EventType.onRepaint));
		if (!gameOver()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks the input if the step is "on menu".
	 * @return false if to exit, true if regular 
	 */
	private boolean five() {
		if (x == ONE) {
			notifyObservers(new Event(EventType.showMenu));
		} else if (x == TWO) {
			setStatus("Du bist am Zug! Schieße auf den Bot! (X/Y)");
			step = THREE;
		} else if (x == THREE) {
			setStatus("Danke für's Spielen! Bis bald!");
			sleep(EXIT);
			return false;
		} else if (x == FOUR) {
			notifyObservers(new Event(EventType.cheat));
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.controller.IController#getCorrectPos()
	 * returns the correct position
	 */
	public int getCorrectPos() {
		return correctPos;
	}

	public void setCorrectPos(int correctPos) {
		this.correctPos = correctPos;
	}

	public boolean isCorrectAl() {
		return correctAl;
	}

	public void setCorrectAl(boolean correctAl) {
		this.correctAl = correctAl;
	}
}
