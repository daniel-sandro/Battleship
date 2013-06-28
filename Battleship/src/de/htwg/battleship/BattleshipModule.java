package de.htwg.battleship;

import com.google.inject.AbstractModule;

import de.htwg.battleship.controller.Controller;
import de.htwg.battleship.controller.IController;

/**
 * @author Sandro, Julian
 *
 */
public class BattleshipModule extends AbstractModule {

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(IController.class).to(Controller.class);
	}
}
