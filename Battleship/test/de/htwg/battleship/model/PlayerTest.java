package de.htwg.battleship.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import de.htwg.battleship.model.Human;
import de.htwg.battleship.model.Playboard;
import de.htwg.battleship.model.Player;


public class PlayerTest {
	Player a;
	Playboard b = new Playboard(10);
		
	@Before
	public void setup(){
		a = new Human(10);
	}
	
	@Test
	public void testInitPlayboard() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testGetNumberShips() {
		a.setNumberShips(2);
		int x = a.getNumberShips();
		assertTrue(x != 0);
	}
	
	@Test
	public void testSetNumberShips() {
		int x = 3;
		a.setNumberShips(x);
		assertTrue(a.getNumberShips() == x);
	}
	
	@Test
	public void testSetPlayboard() {
		a.setPlayboard(b);
		assertTrue(a.getPlayboard() == b);
	}
}