package de.htwg.battleship.model;

import de.htwg.battleship.controller.BotController;

public class Bot extends JavaBattleshipPlayer {

	public Bot(int playboardSize) {
		super(playboardSize);
		super.controller = new BotController(this);
	}
}
