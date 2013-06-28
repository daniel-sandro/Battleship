package de.htwg.battleship;

import com.google.inject.AbstractModule;

import de.htwg.battleship.controller.Controller;
import de.htwg.battleship.controller.IController;

public class BattleshipModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IController.class).to(Controller.class);
	}
}
