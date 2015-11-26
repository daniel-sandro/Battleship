package de.htwg.battleship.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import de.htwg.battleship.model.Field.state;


public class BotTest {
	
	Bot a;
	Playboard b;
	
	@Before
	public void testBot() {
		a = new Bot(1);
		assertNotNull(a);
		
	}

	@Test
	public void testshoot() {
		a.initPlayboard(1);
		assertNotNull(a);
		a.shoot(a.getPlayboard());
		assertTrue((a.getPlayboard().getField()[0][0].getStat() != state.empty && ( a.getPlayboard().getField()[0][0].getStat() == state.hit) 
				|| a.getPlayboard().getField()[0][0].getStat() == state.emptyhit));
		assertTrue((a.getPlayboard().getField()[0][0].getStat() != state.emptyhit && (a.getPlayboard().getField()[0][0].getStat() == state.hit)
				|| a.getPlayboard().getField()[0][0].getStat() == state.emptyhit));
		}
	
	@Test
	public void testInitPlayboard() {
		a.initPlayboard(1);
		assertEquals(1, a.getPlayboard().getSize());
	}
	
	@Test
	public void testInitrandomNumber() {
		a.initPlayboard(1);
		assertNotNull(a);
		int x = a.initRandomNumber();
		assertNotNull(x);
	}
	
	@Test
	public void testSetShip() {
		Bot x = new Bot(3);
		Ship r = new Rowboat();
		Ship r2 = new Destructor(false);
		int xpo = r2.getPosition()[0];
		int ypo = r2.getPosition()[1];
		x.setShip(r2);
		a.setShip(r);
		assertEquals(state.ship, a.getPlayboard().getField()[0][0].getStat());
	}
	
	@Test
	public void testVertical(){
		Bot b = new Bot(26);
		b.vertical();
		assertTrue(true);
	}
	
	@Test
	public void testCheck() {
		
		Bot b = new Bot(4);
		Ship x1 = new Rowboat();
		b.setShip(x1);
		Ship d = new Destructor(false);
		Ship e = new Destructor(true);
		b.setShip(e);
		b.setShip(d);
		assertTrue(true);
	}
}
