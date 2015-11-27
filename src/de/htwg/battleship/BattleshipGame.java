package de.htwg.battleship;

import de.htwg.battleship.controller.JavaBattleshipController;
import de.htwg.battleship.model.Bot;
import de.htwg.battleship.model.Human;
import de.htwg.battleship.view.gui.BattleshipGUI;
import de.htwg.battleship.view.gui.BattleshipSetFieldsize;
import de.htwg.battleship.view.tui.TUI;
import org.apache.log4j.PropertyConfigurator;

import java.util.Scanner;

/**
 * @author Sandro, Julian
 * The Game Class. starts a controller, gui, tui and runs the game-loop
 *
 */
public final class BattleshipGame {
	private static int fieldSize;
	
	private BattleshipGame() {}

	public static void setFieldSize(int fieldSize) {
		BattleshipGame.fieldSize = fieldSize;
	}

	/**
	 * Main for the Battleship. Runs the game.
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		PropertyConfigurator.configure("log4j.properties");
		Scanner scanner = new Scanner(System.in);

		BattleshipSetFieldsize sfs = new BattleshipSetFieldsize();
		sfs.setVisible(true);

		synchronized (sfs) {
			try {
				sfs.wait();
			} catch (InterruptedException e) {
			}
		}

		Human human = new Human(fieldSize);
		Bot bot = new Bot(fieldSize);
		JavaBattleshipController controller = new JavaBattleshipController(human, bot);

		BattleshipGUI gui = new BattleshipGUI(controller);
		TUI tui = new TUI(controller);

		controller.startGame();
		gui.setVisible(true);
		
		/*boolean finished = false;
		while (!finished) {
			finished = controller.input(scanner.nextLine());
		}
		System.exit(0);*/
	}
}
