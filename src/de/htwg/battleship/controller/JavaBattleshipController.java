package de.htwg.battleship.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.htwg.battleship.model.*;
import de.htwg.battleship.model.ship.Destructor;
import de.htwg.battleship.model.ship.Flattop;
import de.htwg.battleship.model.ship.Rowboat;
import de.htwg.battleship.observer.Event;
import de.htwg.battleship.observer.Observable;
import javafx.util.Pair;

import java.util.Set;

@Singleton
public class JavaBattleshipController extends Observable implements BattleshipController {
    private Human player1;
    private Bot player2;
    private BattleshipPlayer turn;
    private BattleshipPlayer winner;
    private String status;

    @Inject
    public JavaBattleshipController() {

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
        initializeBoard(player2);
        // TODO: workaround, manage in HumanController!
        notifyObservers(new Event(Event.EventType.SET_ROWBOAT));
        //initializeBoard(player1);
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        while (!hasWon(player1) && !hasWon(player1)) {
            if (turn == player1) {
                // TODO: implement
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

    public boolean placeHumanShip(Ship ship, Position p, boolean horizontal) {
        boolean res = placeShip(player1.getPlayboard(), ship, p, horizontal);
        if (ship instanceof Rowboat) {
            notifyObservers(new Event(Event.EventType.SET_DESTRUCTOR));
            if (getFieldSize() < 3) {
                this.notify();
            }
        } else if (ship instanceof Destructor) {
            notifyObservers(new Event(Event.EventType.SET_FLATTOP));
            if (getFieldSize() < 8) {
                this.notify();
            }
        } else if (ship instanceof Flattop) {
            notifyObservers(new Event(Event.EventType.ON_ACTION));
            synchronized (this) {
                this.notify();
            }
        }
        return res;
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
            notifyObservers(new Event(Event.EventType.CORRECT_POSITION));
            notifyObservers(new Event(Event.EventType.ON_REPAINT));
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
        for (int i = 0; i < playboard.getSize(); i++) {
            for (int j = 0; j < playboard.getSize(); j++) {
                playerLost &= playboard.getField(i, j).isEmpty() || playboard.getField(i, j).isHit();
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

    public BattleshipPlayer getHuman() {
        return player1;
    }

    public BattleshipPlayer getBot() {
        return player2;
    }
}
