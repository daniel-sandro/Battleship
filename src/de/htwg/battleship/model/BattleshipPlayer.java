package de.htwg.battleship.model;

import de.htwg.battleship.controller.PlayerController;
import javafx.util.Pair;

import java.util.Set;

public interface BattleshipPlayer {
    public Playboard getPlayboard();

    public Set<Pair<Ship, Pair<Position, Boolean>>> initialState();

    public Position nextShot();

    public PlayerController getController();
}
