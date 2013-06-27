package de.htwg.battleship;

import java.util.Scanner;
import de.htwg.battleship.controller.Controller;
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

		Scanner scanner = new Scanner(System.in);
		Controller controller = new Controller(0);
		
		BattleshipSetFieldsize s = new BattleshipSetFieldsize(controller);
		
		synchronized (s) {
			try {
				s.wait();
			} catch (InterruptedException e) {

			}
		}

		@SuppressWarnings("unused")
		BattleshipGUI gui = new BattleshipGUI(controller);
		@SuppressWarnings("unused")
		TUI tui = new TUI(controller);

		controller.start();
		
		boolean finished = false;
		while (!finished) {
			finished = controller.input(scanner.nextLine());
		}
		System.exit(0);
	}
}
