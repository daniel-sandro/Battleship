package de.htwg.battleship.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import de.htwg.battleship.model.Flattop;
import de.htwg.battleship.model.Ships;


public class FlattopTest {
	
	Ships a;
	
	@Before
	public void setUp(){
		a = new Flattop(1, 1, true);
	}
	
	@Test
	public void testFlattop() {
		a = new Flattop(true);
		assertNotNull(a);
	}

	@Test
	public void testSetPosition() {
		a.setPosition(2, 2);
		assert(a.getPosition()[0] == 2 && a.getPosition()[1] == 2);
	}

}
