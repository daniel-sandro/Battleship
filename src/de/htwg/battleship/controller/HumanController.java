package de.htwg.battleship.controller;

import de.htwg.battleship.model.Human;
import de.htwg.battleship.model.Position;
import de.htwg.battleship.model.Ship;
import de.htwg.battleship.model.ship.Destructor;
import de.htwg.battleship.model.ship.Flattop;
import de.htwg.battleship.model.ship.Rowboat;
import de.htwg.battleship.observer.Event;
import de.htwg.battleship.observer.Observable;
import javafx.util.Pair;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class HumanController extends Observable implements PlayerController {
    private Human human;
    private BlockingQueue<Pair<Ship, Pair<Position, Boolean>>> initialState;
    private BlockingQueue<Position> shoots;

    public HumanController(Human human) {
        this.human = human;
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

    @Override
    public boolean isAutonomous() {
        return false;
    }

    public void placeShip(Ship ship, Position p, boolean horizontal) {
        try {
            initialState.put(new Pair<>(ship, new Pair<>(p, horizontal)));
            if (ship instanceof Rowboat) {
                notifyObservers(new Event(Event.EventType.SET_DESTRUCTOR));
            } else if (ship instanceof Destructor) {
                notifyObservers(new Event(Event.EventType.SET_FLATTOP));
            } else if (ship instanceof Flattop) {
                notifyObservers(new Event(Event.EventType.ON_ACTION));
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
