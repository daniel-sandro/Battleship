package de.htwg.battleship.model;

import de.htwg.battleship.controller.PlayerController;
import javafx.util.Pair;

import java.util.Queue;

public abstract class JavaBattleshipPlayer <K extends PlayerController> implements BattleshipPlayer {
    protected K controller;
    private Playboard playboard;

    @Override
    public void setFieldSize(int fieldSize) {
        playboard = new Playboard(fieldSize);
    }

    @Override
    public Playboard getPlayboard() {
        return playboard;
    }

    @Override
    public Queue<Pair<Ship, Pair<Position, Boolean>>> initialState() {
        return controller.getInitialState();
    }

    @Override
    public Position nextShot() {
        return controller.getNextShot();
    }

    @Override
    public K getController() {
        return controller;
    }

    @Override
    public void setController(PlayerController controller) {
        this.controller = (K) controller;
    }
}