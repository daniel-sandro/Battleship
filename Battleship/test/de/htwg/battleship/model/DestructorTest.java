package de.htwg.battleship.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import de.htwg.battleship.model.Destructor;
import de.htwg.battleship.model.Ships;


public class DestructorTest {
	Ships a;
	
	@Before
	public void setUp(){
		a = new Destructor(1, 1, true);
	}

	@Test
	public void testDestructor() {
		a = new Destructor(true);
		assertNotNull(a);
	}
}
