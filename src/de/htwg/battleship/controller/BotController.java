package de.htwg.battleship.controller;

import de.htwg.battleship.model.Bot;
import de.htwg.battleship.model.Playboard;
import de.htwg.battleship.model.Position;
import de.htwg.battleship.model.Ship;
import de.htwg.battleship.model.ship.Destructor;
import de.htwg.battleship.model.ship.Flattop;
import de.htwg.battleship.model.ship.Rowboat;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class BotController implements PlayerController {
    private Bot bot;
    private Random rnd;
    private Playboard opponentPlayboard;

    public BotController(Bot bot) {
        this.bot = bot;
        this.rnd = new Random();
        this.opponentPlayboard = new Playboard(bot.getPlayboard().getSize());
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
        // TODO: implement
        int playboardSize = bot.getPlayboard().getSize();
        Position p = new Position(rnd.nextInt(playboardSize), rnd.nextInt(playboardSize));
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
