package de.htwg.battleship;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.controller.BattleshipController;
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
		Injector injector = Guice.createInjector();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		BattleshipSetFieldsize s = injector.getInstance(BattleshipSetFieldsize.class);
		
		synchronized (s) {
			try {
				s.wait();
			} catch (InterruptedException e) {
			}
		}

		@SuppressWarnings("unused")
		BattleshipGUI gui = injector.getInstance(BattleshipGUI.class);
		@SuppressWarnings("unused")
		TUI tui = injector.getInstance(TUI.class);

		Human human = new Human(fieldSize);
		Bot bot = new Bot(fieldSize);
		BattleshipController controller = new JavaBattleshipController(human, bot);
		controller.startGame();
		
		/*boolean finished = false;
		while (!finished) {
			finished = controller.input(scanner.nextLine());
		}
		System.exit(0);*/
	}
}
