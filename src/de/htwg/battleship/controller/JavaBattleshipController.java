package de.htwg.battleship.controller;

import de.htwg.battleship.model.*;
import de.htwg.battleship.observer.Observable;
import javafx.util.Pair;

import java.util.Set;

public class JavaBattleshipController extends Observable implements BattleshipController {
    private final Human player1;
    private final Bot player2;
    private BattleshipPlayer turn;
    private BattleshipPlayer winner;
    private String status;

    public JavaBattleshipController(Human player1, Bot player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = player1;
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
        initializeBoard(player1);
        initializeBoard(player2);
        while (!hasWon(player1) && !hasWon(player1)) {
            if (turn == player1) {
                Position p = player1.getController().generateNextShot();
                shoot(player2.getPlayboard(), p);
                turn = player2;
            } else if (turn == player2) {
                Position p = player2.getController().generateNextShot();
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
            }
        } else {
            // Check that the ship fits in the chosen position and there's
            // nothing in its way
            if (p.getRow() + ship.getLength() > playboard.getSize()) {
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
            }
        }
        return placed;
    }

    public boolean placeHumanShip(Ship ship, Position p, boolean horizontal) {
        return placeShip(player1.getPlayboard(), ship, p, horizontal);
    }

    @Override
    public boolean shoot(Playboard playboard, Position p) {
        // TODO: check events that must be triggered
        if (!playboard.validPosition(p)) {
            return false;
        } else {
            Field f = playboard.getField(p);
            if (f.isEmpty()) {
                f.setMissed();
            } else {
                f.setHit();
            }
            return true;
        }
    }

    public boolean humanShoot(Position p) {
        return shoot(player2.getPlayboard(), p);
    }

    private void initializeBoard(BattleshipPlayer player) {
        final Playboard playboard = player.getPlayboard();
        Set<Pair<Ship, Pair<Position, Boolean>>> initialState = player.getController().generateInitialState();
        for (Pair<Ship, Pair<Position, Boolean>> config : initialState) {
            Ship ship = config.getKey();
            Position p = config.getValue().getKey();
            boolean horizontal = config.getValue().getValue();
            placeShip(playboard, ship, p, horizontal);
        }
    }

    private boolean hasWon(BattleshipPlayer player) {
        Playboard playboard = player.getPlayboard();
        boolean playerLost = false;
        for (int i = 0; i < playboard.getSize() && !playerLost; i++) {
            for (int j = 0; j < playboard.getSize() && !playerLost; j++) {
                playerLost = playboard.getField(i, j).isNotHit();
            }
        }
        if (player == player1) {
            winner = player2;
            return true;
        } else if (player == player2) {
            winner = player1;
            return true;
        } else {
            return false;
        }
    }

    public BattleshipPlayer getHuman() {
        return player1;
    }

    public BattleshipPlayer getBot() {
        return player2;
    }
}
