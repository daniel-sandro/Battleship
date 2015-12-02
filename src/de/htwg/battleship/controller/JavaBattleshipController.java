package de.htwg.battleship.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.htwg.battleship.model.Bot;
import de.htwg.battleship.model.Human;

@Singleton
public class JavaBattleshipController extends GenericBattleshipController<Human, Bot> {

    @Inject
    public JavaBattleshipController() {
    }

    @Override
    public void setFieldSize(int fieldSize) {
        // TODO: workaround; (better to use the constructor?)
        player1 = new Human(fieldSize, this);
        player2 = new Bot(fieldSize, this);
        super.turn = player1;
    }

    public Human getHuman() {
        return super.player1;
    }

    public Bot getBot() {
        return super.player2;
    }
}
