package de.htwg.battleship.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


public class DestructorTest {
	Ship a;
	
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
