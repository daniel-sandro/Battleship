package de.htwg.battleship.model.ship;

import de.htwg.battleship.model.Ship;

public class Rowboat implements Ship {
    private static final int LENGTH = 1;

    @Override
    public int getLength() {
        return LENGTH;
    }
}
