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
		assertEquals(2, a.getNumberShips());
	}
	
	@Test
	public void testSetNumberShips() {
		a.setNumberShips(3);
		assertEquals(3, a.getNumberShips());
	}
	
	@Test
	public void testSetPlayboard() {
		a.setPlayboard(b);
		assertEquals(b, a.getPlayboard()); 
	}
}
