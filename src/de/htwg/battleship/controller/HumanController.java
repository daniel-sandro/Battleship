package de.htwg.battleship.controller;

import de.htwg.battleship.model.Position;
import de.htwg.battleship.model.Ship;
import de.htwg.battleship.model.ship.Destructor;
import de.htwg.battleship.model.ship.Rowboat;
import de.htwg.battleship.observer.Event;
import de.htwg.battleship.observer.Observable;
import javafx.util.Pair;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class HumanController extends Observable implements PlayerController {
    protected BattleshipController controller;
    private BlockingQueue<Pair<Ship, Pair<Position, Boolean>>> initialState;
    private BlockingQueue<Position> shoots;

    public HumanController(BattleshipController controller) {
        this.controller = controller;
        this.initialState = new SynchronousQueue<>();
        this.shoots = new SynchronousQueue<>();
    }

    @Override
    public BlockingQueue<Pair<Ship, Pair<Position, Boolean>>> getInitialState() {
        return initialState;
    }

    @Override
    public Position getNextShot() {
        try {
            return shoots.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void placeShip(Ship ship, Position p, boolean horizontal) {
        try {
            initialState.put(new Pair<>(ship, new Pair<>(p, horizontal)));
            if (ship instanceof Rowboat && controller.getFieldSize() >= 3) {
                controller.setStatus("Place your destructor");
                notifyObservers(Event.SET_DESTRUCTOR);
            } else if (ship instanceof Destructor && controller.getFieldSize() >= 8) {
                controller.setStatus("Place your flattop");
                notifyObservers(Event.SET_FLATTOP);
            } else {
                controller.setStatus("Shoot your opponent");
                notifyObservers(Event.ON_ACTION);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shoot(Position p) {
        try {
            shoots.put(p);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
