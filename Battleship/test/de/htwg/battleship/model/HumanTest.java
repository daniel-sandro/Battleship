package de.htwg.battleship.model;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.battleship.model.Bot;
import de.htwg.battleship.model.Human;
import de.htwg.battleship.model.Rowboat;
import de.htwg.battleship.model.Field.state;


public class HumanTest {
	
	public Human a;
	public Bot b;

	@Test
	public void testShoot() {
		a = new Human(1);
		b = new Bot(1);
		
		b.getPlayboard().setShip(new Rowboat());
		a.shoot(b.getPlayboard().getField()[0][0]);
		
		assertTrue(b.getPlayboard().getField()[0][0].getStat() == state.hit);
	}
	
	@Test
	public void testInitPlayboard() {
		Player a = new Human(1);
		a.initPlayboard(2);
		assertEquals(2, a.getPlayboard().getSize());
	}
}
