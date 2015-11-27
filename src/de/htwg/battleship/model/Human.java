package de.htwg.battleship.model;

import de.htwg.battleship.controller.HumanController;

public class Human extends JavaBattleshipPlayer {

    public Human(int playboardSize) {
        super(playboardSize);
        super.controller = new HumanController(this);
    }
}
