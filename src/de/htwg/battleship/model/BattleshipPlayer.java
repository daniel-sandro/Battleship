package de.htwg.battleship.model;

import javafx.util.Pair;

import java.util.Set;

public interface BattleshipPlayer {
    public Set<Pair<Ship, Pair<Position, Boolean>>> initialState();

    public Position nextShot();
}
