package view.tui;

import java.util.Scanner;

import model.Field.state;
import observer.Event;
import observer.IObserver;
import controller.Controller;

/**
 * @author Sandro, Julian
 * 
 */
public final class TUI implements IObserver {

	private static final int MAXFIELDSIZE = 26;
	private static final int HEX = 65;
	private static final int LINELEN = 9;
	private static final String SEP = " | ";

	private Controller controller;
	private Scanner scanner = new Scanner(System.in);
	private static StringBuilder sb = new StringBuilder();
	boolean alignment;
	int x, y;

	/**
	 * private Contructor, just for jenkins...
	 */
	@SuppressWarnings("unused")
	private TUI() {}

	public TUI(Controller controller) {
		this.controller = controller;
		controller.addObserver(this);
		print(controller.getStatus());
		print("\n");
	}

	public void onNotifyObservers(Event t) {
		switch (t.getEventType()) {
		case setRowboat:
			onSetRowboat();
			break;
		case setDestructor:
			onSetDestructor();
			break;
		case setFlattop:
			onSetFlattop();
			break;
		case showBotsField:
			onShowBotsField();
			break;
		case showPlayersField:
			onShowPlayersField();
			break;
		case gameOver:
			onGameOver();
			break;
		case won:
			onWon();
			break;
		case showMenu:
			onShowMenu();
			break;
		case shootBot:
			onShootOnBot();
			break;
		case onStatus:
			onStatus();
			break;
		case onAction:
			onAction();
			break;
		case cheat:
			onCheat();
			break;
		default:
			break;
		}
	}

	/**
	 * Sleeps the Tui to wait for gui input, or own input.
	 */
	public void sleep() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * checks if the someone else made some input.
	 */
	public void checkForInput() {
		while (!controller.isInput() && !scanner.hasNextInt()) {
			sleep();
		}
		if (controller.isInput()) {
			return;
		}
	}

	public void onSetFieldsize() {}

	public void onSetRowboat() {
		checkForInput();
		controller.setHumanRowboat(setXPos(), setYPos());
	}

	public void onSetDestructor() {
		setShip();
		while (!checkShipPos(1, x, y, alignment)) {
			onSetDestructor();
			return;
		}
		controller.setHumanDestructor(x, y, alignment);
	}

	public void onSetFlattop() {
		setShip();
		while (!checkShipPos(2, x, y, alignment)) {
			onSetFlattop();
			return;
		}
		controller.setHumanFlattop(x, x, alignment);
	}

	/**
	 * Lets the player set hit ship
	 */
	private void setShip() {
		checkForInput();
		x = setXPos();
		y = setYPos();
		alignment = setAlignment();
		controller.setInput(true);
	}

	/**
	 * Checks if the given values are valid. they are, as long as they don't
	 * rech the limit of the fieldsize.
	 * 
	 * @param ship
	 *            0:rowboat, 1:destructor, 2:flattop
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 * @param alignment
	 *            true if vertical, false if horizontal
	 * @return true if the position is valid, false if not
	 */
	private boolean checkShipPos(int ship, int x, int y, boolean alignment) {
		int t;
		if ((t = controller.checkSetShipPosition(ship, x, y, alignment)) != 0) {
			print("Das Schiff bitte ");
			print(String.valueOf(t));
			if (alignment) {
				print(" weiter oben setzen!\n");
			} else {
				print(" weiter links setzen!\n");
			}
			return false;
		}
		return true;
	}

	/**
	 * Gets the input for x-coordinate of the ship.
	 * 
	 * @return true if it was valid
	 */
	public int setXPos() {
		int x;
		while (true) {
			x = scanner.nextInt();
			if (x < 0 || x > controller.getFieldsize()) {
				print("Die X-Koordinate muss zwischen 0 und ");
				print(String.valueOf(controller.getFieldsize()));
				print("liegen!\nBitte erneut eingeben!\n");
				continue;
			}
			break;
		}
		return x;
	}

	/**
	 * Gets the input for y-coordinate of the ship.
	 * 
	 * @return true if it was valid
	 */
	public int setYPos() {
		int y;
		while (true) {
			y = scanner.nextInt();
			if (y < 0 || y > controller.getFieldsize()) {
				print("Die Y-Koordinate muss zwischen 0 und ");
				sb.append("Die Y-Koordinate muss zwischen 0 und ")
						.append(controller.getFieldsize())
						.append("liegen!\nBitte erneut eingeben!\n");
				print(sb.toString());
				sb.setLength(0);
				continue;
			}
			break;
		}
		return y;
	}

	/**
	 * Gets the alignment of the ship from user.
	 * 
	 * @return true if ship has be set horizontal, false if vertical
	 */
	public boolean setAlignment() {
		int nextBool;
		while (true) {
			print("Horizontal (1) oder vertikal (2) setzen?\n");
			nextBool = scanner.nextInt();
			if (nextBool == 1) {
				return false;
			} else if (nextBool == 2) {
				return true;
			} else {
				print("Falsche Eingabe!\n");
				continue;
			}
		}
	}

	public void onStatus() {
		print(controller.getStatus());
		print("\n");
	}

	public void onShowMenu() {
		print("\n");
		print("Deine Optionen im Spiel sind:\n");
		print("(1) EIGENES FELD ANZEIGEN\n");
		print("(2) AUF DAS SPIELFELD DES BOTS SCHIESSEN\n");
		print("(3) SPIEL BEENDEN\n");
		print("\n");
	}

	public void onAction() {
		while (!controller.isInput() && !scanner.hasNextInt()) {
			sleep();
		}
		if (controller.isInput()) {
			return;
		}
		controller.setInput(scanner.nextInt());
	}

	public void onCheat() {
		print(showField(true, true).toString());
		sb.setLength(0);
	}

	public void onShowPlayersField() {
		print(showField(false, false).toString());
		sb.setLength(0);
	}

	public void onShowBotsField() {
		print(showField(true, false).toString());
		sb.setLength(0);
	}

	public void onShootOnBot() {
		print("Nenne die Position auf die geschossen werden soll: ([X/Y])\n");

		int x = scanner.nextInt();
		int y = scanner.nextInt();
		if (controller.shootBot(y, x) == true) {
			print("\n");
			print("TREFFER!!\n");
		} else {
			print("Leider nichts getroffen!\n");
		}
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
		for (int i = 0; i < controller.getFieldsize(); i++) {
			printPattern(i);
			for (int j = 0; j < controller.getFieldsize(); j++) {
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
			for (int k = HEX; k < controller.getFieldsize() + HEX; k++) {
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
		if (controller.getBot().getPlayboard().getField()[i][j].getStat() == state.empty) {
			sb.append("_ | ");
		} else if (controller.getBot().getPlayboard().getField()[i][j]
				.getStat() == state.ship && !ship) {
			sb.append("_ | ");
		} else if (controller.getBot().getPlayboard().getField()[i][j]
				.getStat() == state.emptyhit) {
			sb.append("O | ");
		} else if (controller.getBot().getPlayboard().getField()[i][j]
				.getStat() == state.hit) {
			sb.append("X | ");
		} else if (controller.getBot().getPlayboard().getField()[i][j]
				.getStat() == state.ship && ship) {
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
		if (controller.getPlayer().getPlayboard().getField()[i][j].getStat() == state.empty) {
			sb.append("~ | ");
		} else if (controller.getPlayer().getPlayboard().getField()[i][j]
				.getStat() == state.emptyhit) {
			sb.append("O | ");
		} else if (controller.getPlayer().getPlayboard().getField()[i][j]
				.getStat() == state.hit) {
			sb.append("X | ");
		} else if (controller.getPlayer().getPlayboard().getField()[i][j]
				.getStat() == state.ship) {
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
		System.out.printf(string);
	}

	public void onGameOver() {
		System.exit(0);
	}

	public void onWon() {
		System.exit(0);
	}

	public void onBotShoots() {
	}
}
