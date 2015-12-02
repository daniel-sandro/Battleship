package de.htwg.battleship.model;

import de.htwg.battleship.controller.BattleshipController;
import de.htwg.battleship.controller.BotController;

public class Bot extends JavaBattleshipPlayer<BotController> {

	public Bot(BattleshipController controller) {
		super.controller = new BotController(controller);
	}
}
