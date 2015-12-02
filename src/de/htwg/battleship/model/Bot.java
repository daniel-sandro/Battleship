package de.htwg.battleship.model;

import com.google.inject.Inject;
import de.htwg.battleship.controller.BattleshipController;
import de.htwg.battleship.controller.BotController;

public class Bot extends JavaBattleshipPlayer<BotController> {

	@Inject
	public Bot(int playboardSize, BattleshipController controller) {
		super(playboardSize);
		super.controller = new BotController(this, controller);
	}
}
