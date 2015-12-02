package de.htwg.battleship.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.htwg.battleship.model.Bot;
import de.htwg.battleship.model.Human;

@Singleton
public class JavaBattleshipController extends GenericBattleshipController<Human, Bot> {

    @Inject
    public JavaBattleshipController() {
        super.player1 = new Human(this);
        super.player2 = new Bot(this);
        super.turn = player1;
    }

    public Human getHuman() {
        return super.player1;
    }

    public Bot getBot() {
        return super.player2;
    }
}
