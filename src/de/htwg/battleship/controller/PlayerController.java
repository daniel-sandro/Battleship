package de.htwg.battleship.controller;

import de.htwg.battleship.model.Position;
import de.htwg.battleship.model.Ship;
import javafx.util.Pair;

import java.util.Queue;

public interface PlayerController {

    /**
     * Generates the board's initial state.
     * @return A set of ships, positions and directions (horizontal/vertical) conforming the initial state of the board.
     */
    public Queue<Pair<Ship, Pair<Position, Boolean>>> getInitialState();

    /**
     * Generates the next shot to fire.
     * @return The position to shoot.
     */
    public Position getNextShot();

}
