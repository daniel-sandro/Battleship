package de.htwg.battleship.controller;

import de.htwg.battleship.model.Bot;
import de.htwg.battleship.model.Playboard;
import de.htwg.battleship.model.Position;
import de.htwg.battleship.model.Ship;
import de.htwg.battleship.model.ship.Destructor;
import de.htwg.battleship.model.ship.Flattop;
import de.htwg.battleship.model.ship.Rowboat;
import javafx.util.Pair;

import java.util.*;

public class BotController implements PlayerController {
    private Bot bot;
    private Random rnd;
    private Playboard opponentPlayboard;
    private Vector<Position> notTargetedPositions;

    public BotController(Bot bot) {
        this.bot = bot;
        this.rnd = new Random();
        this.opponentPlayboard = new Playboard(bot.getPlayboard().getSize());
        int playboardSize = bot.getPlayboard().getSize();
        this.notTargetedPositions = new Vector<>(playboardSize * playboardSize);
        for (int i = 0; i < playboardSize; i++) {
            for (int j = 0; j < playboardSize; j++) {
                Position p = new Position(i, j);
                notTargetedPositions.add(p);
            }
        }
    }

    @Override
    public Queue<Pair<Ship, Pair<Position, Boolean>>> getInitialState() {
        Queue<Pair<Ship, Pair<Position, Boolean>>> ships = new ArrayDeque<>();
        int playboardSize = bot.getPlayboard().getSize();
        if (playboardSize < 3) {
            // Place one rowboat
            Ship rowboat = new Rowboat();
            ships.add(new Pair<>(rowboat, randomPosition(rowboat, playboardSize)));
        } else if (playboardSize < 8) {
            // Place one rowboat and one destructor
            Ship rowboat = new Rowboat();
            ships.add(new Pair<>(rowboat, randomPosition(rowboat, playboardSize)));
            Ship destructor = new Destructor();
            ships.add(new Pair<>(destructor, randomPosition(destructor, playboardSize)));
        } else {
            // Place one rowboat, one destructor and one flattop
            Ship rowboat = new Rowboat();
            ships.add(new Pair<>(rowboat, randomPosition(rowboat, playboardSize)));
            Ship destructor = new Destructor();
            ships.add(new Pair<>(destructor, randomPosition(destructor, playboardSize)));
            Ship flattop = new Flattop();
            ships.add(new Pair<>(flattop, randomPosition(flattop, playboardSize)));
        }
        return ships;
    }

    @Override
    public Position getNextShot() {
        // TODO: subscribe to events to know the result of the shooting
        Position p = notTargetedPositions.remove(rnd.nextInt(notTargetedPositions.size()));
        return p;
    }

    @Override
    public boolean isAutonomous() {
        return true;
    }

    private Pair<Position, Boolean> randomPosition(Ship ship, int playboardSize) {
        boolean horizontal = rnd.nextBoolean();
        int row = horizontal ? rnd.nextInt(playboardSize) : rnd.nextInt(playboardSize - ship.getLength());
        int col = horizontal ? rnd.nextInt(playboardSize - ship.getLength()) : rnd.nextInt(playboardSize);
        Position p = new Position(row, col);
        return new Pair<>(p, horizontal);
    }
}
