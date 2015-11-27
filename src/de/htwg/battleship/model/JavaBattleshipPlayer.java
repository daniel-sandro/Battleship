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

    @Override
    public Playboard getPlayboard() {
        return playboard;
    }

    @Override
    public Set<Pair<Ship, Pair<Position, Boolean>>> initialState() {
        return controller.generateInitialState();
    }

    @Override
    public Position nextShot() {
        return controller.generateNextShot();
    }

    @Override
    public PlayerController getController() {
        return controller;
    }
}
