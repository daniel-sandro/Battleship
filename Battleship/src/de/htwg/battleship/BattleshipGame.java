package de.htwg.battleship;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.battleship.controller.Controller;
import de.htwg.battleship.controller.IController;
import de.htwg.battleship.view.gui.BattleshipGUI;
import de.htwg.battleship.view.gui.BattleshipSetFieldsize;
import de.htwg.battleship.view.tui.TUI;

public class BattleshipGame {

	/**
	 * Main for the Battleship. Runs the game.
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		PropertyConfigurator.configure("log4j.properties");
		Injector injector = Guice.createInjector(new BattleshipModule());
		Scanner scanner = new Scanner(System.in);
		
		IController controller = injector.getInstance(IController.class);
		
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

		controller.start();
		
		boolean finished = false;
		while (!finished) {
			finished = controller.input(scanner.nextLine());
		}
		System.exit(0);
	}
}
