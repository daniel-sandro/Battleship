package de.htwg.battleship.model;

import de.htwg.battleship.controller.PlayerController;
import javafx.util.Pair;

import java.util.Queue;

public interface BattleshipPlayer {
    public void setFieldSize(int fieldSize);

    public Playboard getPlayboard();

    public Queue<Pair<Ship, Pair<Position, Boolean>>> initialState();

    public Position nextShot();

    public PlayerController getController();
}
