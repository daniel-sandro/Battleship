package de.htwg.battleship.controller;

import de.htwg.battleship.model.*;

public class JavaBattleshipController implements BattleshipController {
    private final Human player1;
    private final Bot player2;

    public JavaBattleshipController(Human player1, Bot player2, int playboardSize) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public JavaBattleshipPlayer[] getPlayers() {
        return new JavaBattleshipPlayer[]{ player1, player2 };
    }

    public boolean placeShip(Playboard playboard, Ship ship, Position p, boolean horizontal) {
        // Check coordinates are within the playboard boundaries
        if (p.getRow() >= playboard.getSize() || p.getRow() < 0 ||
                p.getCol() < 0 || p.getCol() >= playboard.getSize()) {
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

    public void shoot(Playboard playboard, Position p) {

    }
}
