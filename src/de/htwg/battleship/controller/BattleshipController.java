package de.htwg.battleship.controller;

import de.htwg.battleship.model.BattleshipPlayer;
import de.htwg.battleship.model.Playboard;
import de.htwg.battleship.model.Position;
import de.htwg.battleship.model.Ship;
import de.htwg.battleship.observer.IObservable;

public interface BattleshipController extends IObservable {

    /**
     * Returns the players playing the game.
     * @return An array with the players.
     */
    public BattleshipPlayer[] getPlayers();

    public BattleshipPlayer getTurn();

    public String getStatus();

    public int getFieldSize();

    public BattleshipPlayer startGame();

    public boolean isGameFinished();

    public BattleshipPlayer getWinner();

    /**
     * Places a ship in the playboard.
     * @param playboard The playboard to modify.
     * @param ship The ship to place.
     * @param p The position to place the ship in.
     * @param horizontal True to place the ship horizontally, false otherwise.
     * @return True if and only if the ship was placed. False otherwise.
     */
    public boolean placeShip(Playboard playboard, Ship ship, Position p, boolean horizontal);

    /**
     * Shoots a position in a playboard.
     * @param playboard The playboard to shoot.
     * @param p The targeted position.
     */
    public boolean shoot(Playboard playboard, Position p);
}
