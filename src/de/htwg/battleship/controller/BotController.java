package de.htwg.battleship.controller;

import de.htwg.battleship.model.Position;
import de.htwg.battleship.model.Ship;
import javafx.util.Pair;

import java.util.Random;
import java.util.Set;

public class BotController implements PlayerController {
    private Random rnd = new Random();

    public Set<Pair<Ship, Pair<Position, Boolean>>> generateInitialState() {
        return null;
    }

    public Position generateNextShot() {
        return null;
    }
}
