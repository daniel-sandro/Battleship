package de.htwg.battleship.model;

import de.htwg.battleship.controller.BattleshipController;
import de.htwg.battleship.controller.HumanController;

public class Human extends JavaBattleshipPlayer<HumanController> {

    public Human(BattleshipController controller) {
        super.controller = new HumanController(this, controller);
    }
}
