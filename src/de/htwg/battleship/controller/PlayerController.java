package de.htwg.battleship.controller;

import de.htwg.battleship.model.Position;
import de.htwg.battleship.model.Ship;
import javafx.util.Pair;

import java.util.Set;

public interface PlayerController {

    public Set<Pair<Ship, Pair<Position, Boolean>>> generateInitialState();

    public Position generateNextShot();
}
