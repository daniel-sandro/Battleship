package de.htwg.battleship.controller;

import de.htwg.battleship.model.*;
import de.htwg.battleship.observer.Event;
import de.htwg.battleship.observer.Observable;
import javafx.util.Pair;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public abstract class GenericBattleshipController <P1 extends BattleshipPlayer, P2 extends BattleshipPlayer> extends Observable implements BattleshipController {
    protected P1 player1;
    protected P2 player2;
    protected BattleshipPlayer turn;
    private BattleshipPlayer winner;

    public GenericBattleshipController() {
    }

    public GenericBattleshipController(P1 player1, P2 player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = player1;
    }

    @Override
    public void setFieldSize(int fieldSize) {
        player1.setFieldSize(fieldSize);
        player2.setFieldSize(fieldSize);
    }

    @Override
    public Pair<P1, P2> getPlayers() {
        return new Pair<>(player1, player2);
    }

    @Override
    public BattleshipPlayer getTurn() {
        return turn;
    }

    @Override
    public String getStatus(BattleshipPlayer player) {
        return player.getController().getStatus();
    }

    @Override
    public int getFieldSize() {
        return player1.getPlayboard().getSize();
    }

    @Override
    public BattleshipPlayer startGame() {
        player1.getController().setStatus("Place your rowboat");
        player2.getController().setStatus("Place your rowboat");
        notifyObservers(Event.SET_ROWBOAT);
        initializeBoard(player1);
        initializeBoard(player2);
        while ((turn == player1 && !hasLost(player1)) || (turn == player2 && !hasLost(player2))) {
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
        BattleshipPlayer player = null;
        if (player1.getPlayboard() == playboard) {
            player = player1;
        } else if (player2.getPlayboard() == playboard) {
            player = player2;
        }

        // Check coordinates are within the playboard boundaries
        if (!playboard.validPosition(p)) {
            player.getController().setStatus("Position not valid");
            return false;
        }

        boolean placed = true;
        if (horizontal) {
            // Check that the ship fits in the chosen position and there's
            // nothing in its way
            if (p.getCol() + ship.getLength() > playboard.getSize()) {
                player.getController().setStatus("Position not valid");
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
                player.getController().setStatus("Position not valid");
            }
        } else {
            // Check that the ship fits in the chosen position and there's
            // nothing in its way
            if (p.getRow() + ship.getLength() > playboard.getSize()) {
                player.getController().setStatus("Position not valid");
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
                player.getController().setStatus("Position not valid");
            }
        }
        notifyObservers(Event.ON_REPAINT);
        return placed;
    }

    @Override
    public boolean shoot(Playboard playboard, Position p) {
        BattleshipPlayer player = null;
        if (player1.getPlayboard() == playboard) {
            player = player1;
        } else if (player2.getPlayboard() == playboard) {
            player = player2;
        }

        if (!playboard.validPosition(p)) {
            player.getController().setStatus("Position not valid");
            return false;
        } else {
            Field f = playboard.getField(p);
            if (f.isEmpty() || f.isMissed()) {
                f.setMissed();
            } else if (f.isNotHit() || f.isHit()) {
                f.setHit();
            }
            notifyObservers(Event.ON_REPAINT);
            return true;
        }
    }


    protected void initializeBoard(BattleshipPlayer player) {
        final Playboard playboard = player.getPlayboard();
        if (player.getController() instanceof HumanController) {
            // Blocking behaviour
            HumanController controller = (HumanController) player.getController();
            BlockingQueue<Pair<Ship, Pair<Position, Boolean>>> initialState = controller.getInitialState();
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
        } else {
            // Non-blocking behaviour
            Queue<Pair<Ship, Pair<Position, Boolean>>> initialState = player.getController().getInitialState();
            for (Pair<Ship, Pair<Position, Boolean>> config : initialState) {
                Ship ship = config.getKey();
                Position p = config.getValue().getKey();
                boolean horizontal = config.getValue().getValue();
                placeShip(playboard, ship, p, horizontal);
            }
        }
    }

    // TODO: replace events GAME_OVER and WON for generic GAME_FINISHED and check winner
    protected boolean hasLost(BattleshipPlayer player) {
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
                player1.getController().setStatus("Game over!");
                player2.getController().setStatus("Congratulations, you won!");
                notifyObservers(Event.GAME_OVER);
                return true;
            } else if (player == player2) {
                winner = player1;
                player1.getController().setStatus("Congratulations, you won!");
                player2.getController().setStatus("Game over!");
                notifyObservers(Event.WON);
                return true;
            }
        }
        return false;
    }

}
