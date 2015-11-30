package de.htwg.battleship.model;

import com.google.inject.Inject;
import de.htwg.battleship.controller.BotController;

public class Bot extends JavaBattleshipPlayer {

	@Inject
	public Bot(int playboardSize) {
		super(playboardSize);
		super.controller = new BotController(this);
	}

	@Override
	public BotController getController() {
		return (BotController) controller;
	}
}
