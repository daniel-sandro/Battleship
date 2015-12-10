package de.htwg.battleship;

import de.htwg.battleship.view.gui.BattleshipGUI;
import de.htwg.battleship.view.gui.BattleshipSetFieldsize;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.controller.JavaBattleshipController;
import de.htwg.battleship.view.tui.TUI;
import org.apache.log4j.PropertyConfigurator;

import java.util.Scanner;

/**
 * @author Sandro, Julian
 * The Game Class. starts a controller, gui, tui and runs the game-loop
 *
 */
public final class BattleshipGame {
	
	private BattleshipGame() {}

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

		JavaBattleshipController controller = injector.getInstance(JavaBattleshipController.class);

		BattleshipSetFieldsize s = injector.getInstance(BattleshipSetFieldsize.class);
		// TODO: create players here and inject them into the controller
		
		synchronized (s) {
			try {
				s.wait();
			} catch (InterruptedException e) {
			}
		}

		BattleshipGUI gui = injector.getInstance(BattleshipGUI.class);
		// TODO: fix TUI
		TUI tui = injector.getInstance(TUI.class);

		controller.startGame();
		
	}
}
