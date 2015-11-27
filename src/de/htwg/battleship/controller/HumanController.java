package de.htwg.battleship.controller;

import de.htwg.battleship.model.Human;
import de.htwg.battleship.model.Position;
import de.htwg.battleship.model.Ship;
import javafx.util.Pair;

import java.util.Set;

public class HumanController implements PlayerController {
    private Human human;

    public HumanController(Human human) {
        this.human = human;
    }

    @Override
    public Set<Pair<Ship, Pair<Position, Boolean>>> generateInitialState() {
        // TODO: implement
        return null;
    }

    @Override
    public Position generateNextShot() {
        // TODO: implement
        return null;
    }
}
