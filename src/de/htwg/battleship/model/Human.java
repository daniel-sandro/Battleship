package de.htwg.battleship.model;

import com.google.inject.Inject;
import de.htwg.battleship.controller.BattleshipController;
import de.htwg.battleship.controller.HumanController;

public class Human extends JavaBattleshipPlayer {

    @Inject
    public Human(int playboardSize, BattleshipController controller) {
        super(playboardSize);
        super.controller = new HumanController(this, controller);
    }

    @Override
    public HumanController getController() {
        return (HumanController) controller;
    }
}
