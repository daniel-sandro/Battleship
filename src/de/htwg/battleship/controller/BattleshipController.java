package de.htwg.battleship.controller;

import de.htwg.battleship.model.BattleshipPlayer;
import de.htwg.battleship.model.Playboard;
import de.htwg.battleship.model.Position;
import de.htwg.battleship.model.Ship;

public interface BattleshipController {

    public BattleshipPlayer[] getPlayers();

    /**
     * Places a ship in the playboard.
     * @param playboard The playboard to modify.
     * @param ship The ship to place.
     * @param p The position to place the ship in.
     * @param horizontal True to place the ship horizontally, false otherwise.
     * @return True if and only if the ship was placed. False otherwise.
     */
    public boolean placeShip(Playboard playboard, Ship ship, Position p, boolean horizontal);

    public void shoot(Playboard playboard, Position p);
}
