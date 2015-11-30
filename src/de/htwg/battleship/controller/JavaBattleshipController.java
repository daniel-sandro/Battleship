package de.htwg.battleship.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.htwg.battleship.model.*;
import de.htwg.battleship.observer.Event;
import de.htwg.battleship.observer.Observable;
import javafx.util.Pair;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

@Singleton
public class JavaBattleshipController extends Observable implements BattleshipController {
    private Human player1;
    private Bot player2;
    private BattleshipPlayer turn;
    private BattleshipPlayer winner;
    private String status;
    private Semaphore s;

    @Inject
    public JavaBattleshipController() {
        s = new Semaphore(0);
    }

    public JavaBattleshipController(Human player1, Bot player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = player1;
    }

    @Override
    public void setFieldSize(int fieldSize) {
        player1 = new Human(fieldSize);
        player2 = new Bot(fieldSize);
        turn = player1;
    }

    @Override
    public JavaBattleshipPlayer[] getPlayers() {
        return new JavaBattleshipPlayer[]{ player1, player2 };
    }

    @Override
    public BattleshipPlayer getTurn() {
        return turn;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public int getFieldSize() {
        return player1.getPlayboard().getSize();
    }

    @Override
    public BattleshipPlayer startGame() {
        // TODO: check events that must be triggered
        notifyObservers(new Event(Event.EventType.SET_ROWBOAT));
        initializeBoard(player1);
        initializeBoard(player2);
        while ((turn == player2 && !hasWon(player1)) || (turn == player1 && !hasWon(player2))) {
            if (turn == player1) {
                Position p = player1.getController().getNextShot();
                shoot(player2.getPlayboard(), p);
                turn = player2;
            } else if (turn == player2) {
                Position p = player2.getController().getNextShot();
                shoot(player1.getPlayboard(), p);
                turn = player1;
            }
        }
        return getWinner();
    }

    @Override
    public boolean isGameFinished() {
        return winner != null;
    }

    @Override
    public BattleshipPlayer getWinner() {
        return winner;
    }

    @Override
    public boolean placeShip(Playboard playboard, Ship ship, Position p, boolean horizontal) {
        // TODO: check events that must be triggered
        // Check coordinates are within the playboard boundaries
        if (!playboard.validPosition(p)) {
            return false;
        }

        boolean placed = true;
        if (horizontal) {
            // Check that the ship fits in the chosen position and there's
            // nothing in its way
            if (p.getCol() + ship.getLength() >= playboard.getSize()) {
                notifyObservers(new Event(Event.EventType.CORRECT_POSITION));
                return false;
            }
            for (int i = 0; i < ship.getLength() && placed; i++) {
                placed = playboard.getField(p.getRow(), p.getCol() + i).isEmpty();
            }
            if (placed) {
                // Place the ship
                for (int i = 0; i < ship.getLength() && placed; i++) {
                    playboard.getField(p.getRow(), p.getCol() + i).setShip(ship);
                }
            } else {
                notifyObservers(new Event(Event.EventType.CORRECT_POSITION));
            }
        } else {
            // Check that the ship fits in the chosen position and there's
            // nothing in its way
            if (p.getRow() + ship.getLength() > playboard.getSize()) {
                notifyObservers(new Event(Event.EventType.CORRECT_POSITION));
                return false;
            }
            for (int i = 0; i < ship.getLength() && placed; i++) {
                placed = playboard.getField(p.getRow() + i, p.getCol()).isEmpty();
            }
            if (placed) {
                // Place the ship
                for (int i = 0; i < ship.getLength() && placed; i++) {
                    playboard.getField(p.getRow() + i, p.getCol()).setShip(ship);
                }
            } else {
                notifyObservers(new Event(Event.EventType.CORRECT_POSITION));
            }
        }
        notifyObservers(new Event(Event.EventType.ON_REPAINT));
        return placed;
    }

    @Override
    public boolean shoot(Playboard playboard, Position p) {
        // TODO: check events that must be triggered
        if (!playboard.validPosition(p)) {
            notifyObservers(new Event(Event.EventType.CORRECT_POSITION));
            return false;
        } else {
            Field f = playboard.getField(p);
            if (f.isEmpty() || f.isMissed()) {
                f.setMissed();
            } else if (f.isNotHit() || f.isHit()) {
                f.setHit();
            }
            notifyObservers(new Event(Event.EventType.ON_REPAINT));
            return true;
        }
    }


    private void initializeBoard(BattleshipPlayer player) {
        final Playboard playboard = player.getPlayboard();
        if (player.getController().isAutonomous()) {
            // Non-blocking behaviour
            Queue<Pair<Ship, Pair<Position, Boolean>>> initialState = player.getController().getInitialState();
            for (Pair<Ship, Pair<Position, Boolean>> config : initialState) {
                Ship ship = config.getKey();
                Position p = config.getValue().getKey();
                boolean horizontal = config.getValue().getValue();
                placeShip(playboard, ship, p, horizontal);
            }
        } else {
            // Blocking behaviour
            BlockingQueue<Pair<Ship, Pair<Position, Boolean>>> initialState = (BlockingQueue<Pair<Ship,Pair<Position,Boolean>>>) player.getController().getInitialState();
            int playboardSize = player.getPlayboard().getSize();
            int numShips;
            if (playboardSize < 3) {
                // Place one ship
                numShips = 1;
            } else if (playboardSize < 8) {
                // Place two ships
                numShips = 2;
            } else {
                // Place three ships
                numShips = 3;
            }
            try {
                for (int i = 0; i < numShips; i++) {
                    Pair<Ship, Pair<Position, Boolean>> config = initialState.take();
                    Ship ship = config.getKey();
                    Position p = config.getValue().getKey();
                    boolean horizontal = config.getValue().getValue();
                    placeShip(playboard, ship, p, horizontal);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean hasWon(BattleshipPlayer player) {
        Playboard playboard = player.getPlayboard();
        boolean playerLost = true;
        for (int i = 0; i < playboard.getSize() && playerLost; i++) {
            for (int j = 0; j < playboard.getSize() && playerLost; j++) {
                Field f = playboard.getField(i, j);
                playerLost &= !f.isNotHit();
            }
        }
        if (playerLost) {
            if (player == player1) {
                winner = player2;
                notifyObservers(new Event(Event.EventType.GAME_OVER));
                return true;
            } else if (player == player2) {
                notifyObservers(new Event(Event.EventType.WON));
                winner = player1;
                return true;
            }
        }
        return false;
    }

    public Human getHuman() {
        return player1;
    }

    public Bot getBot() {
        return player2;
    }
}
