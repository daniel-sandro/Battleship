package de.htwg.battleship.model.ship;

import de.htwg.battleship.model.Ship;

public class Flattop implements Ship {
    private static final int LENGTH = 5;

    @Override
    public int getLength() {
        return LENGTH;
    }
}
