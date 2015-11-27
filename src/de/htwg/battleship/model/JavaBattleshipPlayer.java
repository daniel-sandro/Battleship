package de.htwg.battleship.model;

import de.htwg.battleship.controller.PlayerController;
import javafx.util.Pair;

import java.util.Set;

public abstract class JavaBattleshipPlayer implements BattleshipPlayer {
    protected PlayerController controller;
    private Playboard playboard;

    public JavaBattleshipPlayer(int playboardSize) {
        playboard = new Playboard(playboardSize);
    }

    public Playboard getPlayboard() {
        return playboard;
    }

    public Set<Pair<Ship, Pair<Position, Boolean>>> initialState() {
        return controller.generateInitialState();
    }

    public Position nextShot() {
        return controller.generateNextShot();
    }
}
