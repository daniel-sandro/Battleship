package de.htwg.battleship.view.tui;

import com.google.inject.Inject;
import de.htwg.battleship.controller.JavaBattleshipController;
import de.htwg.battleship.model.Playboard;
import de.htwg.battleship.observer.Event;
import de.htwg.battleship.observer.IObserver;
import org.apache.log4j.Logger;

/**
 * @author Sandro, Julian
 * 
 */
public final class TUI implements IObserver {

	private static final int HEX = 65;
	private static final int LINELEN = 9;
	private static final String SEP = " | ";

	private JavaBattleshipController controller;
	private static StringBuilder sb = new StringBuilder();
	private static Logger logger = Logger.getLogger(TUI.class.getName());

	/**
	 * @param controller
	 * constructor
	 */
	@Inject
	public TUI(JavaBattleshipController controller) {
		this.controller = controller;
		controller.addObserver(this);
		print(controller.getStatus());
		print("\n");
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onNotifyObservers(de.htwg.battleship.observer.Event)
	 * reaction on event to notify the observer
	 */
	public void onNotifyObservers(Event e) {
		switch (e) {
		case SHOW_BOTS_FIELD:
			onShowBotsField();
			break;
		case SHOW_PLAYERS_FIELD:
			onShowPlayersField();
			break;
		case GAME_OVER:
			onGameOver();
			break;
		case WON:
			onWon();
			break;
		case SHOW_MENU:
			onShowMenu();
			break;
		case ON_STATUS:
			onStatus();
			break;
		case CHEAT:
			onCheat();
			break;
		}
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onSetFieldsize()
	 * empty implementation
	 */
	public void onSetFieldsize() {}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#ON_STATUS()
	 * reaction on event to give out the status
	 */
	public void onStatus() {
		print(controller.getStatus());
		print("\n");
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onShowMenu()
	 * giving out the options of the player
	 */
	public void onShowMenu() {
		print("\n");
		print("Deine Optionen im Spiel sind:\n");
		print("(1) EIGENES FELD ANZEIGEN\n");
		print("(2) AUF DAS SPIELFELD DES BOTS SCHIESSEN\n");
		print("(3) SPIEL BEENDEN\n");
		print("\n");
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onCheat()
	 * reaction on event to print out CHEAT
	 */
	public void onCheat() {
		print(showField(true, true).toString());
		sb.setLength(0);
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onShowPlayersField()
	 * reaction on event to show the players field
	 */
	public void onShowPlayersField() {
		print(showField(false, false).toString());
		sb.setLength(0);
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onShowPlayersField()
	 * reaction on event to show the bots field
	 */
	public void onShowBotsField() {
		print(showField(true, false).toString());
		sb.setLength(0);
	}

	/**
	 * Prints the playboard
	 * 
	 * @param bot
	 *            true if to show the bot's playboard
	 * @param ship
	 *            true if to show bot's field with ships
	 * @return a stringbuilder object
	 */
	public StringBuilder showField(boolean bot, boolean ship) {
		printHeader(bot, ship);
		for (int i = 0; i < controller.getFieldSize(); i++) {
			printPattern(i);
			for (int j = 0; j < controller.getFieldSize(); j++) {
				if (bot) {
					checkStateBot(i, j, ship);
				} else {
					checkStateHuman(i, j);
				}
			}
			sb.append("\n");
		}
		return sb;
	}

	/**
	 * Prints the header of the playboard.
	 * 
	 * @param bot
	 * @param ship
	 */
	private void printHeader(boolean bot, boolean ship) {
		if (bot) {
			sb.append("##### Spielfeld des Bots ");
			if (ship) {
				sb.append("(mit Schiffen) ");
			}
			sb.append("#####").append("\n");
		} else {
			sb.append("##### DEIN SPIELFELD #####\n");
		}
	}

	/**
	 * prints the pattern of the playboard.
	 * 
	 * @param i
	 */
	private void printPattern(int i) {
		if (i == 0) {
			sb.append(" ");
			for (int k = HEX; k < controller.getFieldSize() + HEX; k++) {
				sb.append(SEP).append((char) k);
			}
			sb.append("\n");
		}
		if (i <= LINELEN) {
			sb.append(i).append(SEP);
		} else {
			sb.append(i).append("| ");
		}
	}

	/**
	 * Checks the bot's playboard, if there is a ship etc and prints it.
	 * 
	 * @param i
	 *            the x-coordinate
	 * @param j
	 *            the y-coordinate
	 * @param ship
	 *            if ships shall be shown
	 */
	private void checkStateBot(int i, int j, boolean ship) {
		Playboard playboard = controller.getBot().getPlayboard();
		if (playboard.getField(i, j).isEmpty()) {
			sb.append("_ | ");
		} else if (playboard.getField(i, j).isNotHit() && !ship) {
			sb.append("_ | ");
		} else if (playboard.getField(i, j).isMissed()) {
			sb.append("O | ");
		} else if (playboard.getField(i, j).isHit()) {
			sb.append("X | ");
		} else if (playboard.getField(i, j).isNotHit() && ship) {
			sb.append("S | ");
		}
	}

	/**
	 * Checks the player's playboard, if there is a ship etc and prints it.
	 * 
	 * @param i
	 *            the x-coordinate
	 * @param j
	 *            the y-coordinate
	 */
	private void checkStateHuman(int i, int j) {
		Playboard playboard = controller.getHuman().getPlayboard();
		if (playboard.getField(i, j).isEmpty()) {
			sb.append("~ | ");
		} else if (playboard.getField(i, j).isMissed()) {
			sb.append("O | ");
		} else if (playboard.getField(i, j).isHit()) {
			sb.append("X | ");
		} else if (playboard.getField(i, j).isNotHit()) {
			sb.append("S | ");
		}
	}

	/**
	 * print method for jenkins.
	 * 
	 * @param string
	 *            the string to print
	 */
	private static void print(String string) {
		logger.info(string);
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onGameOver()
	 * reaction on event to exit the game
	 */
	public void onGameOver() {
		System.exit(0);
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onWon()
	 * reaction on event that player has WON
	 */
	public void onWon() {
		System.exit(0);
	}

}
