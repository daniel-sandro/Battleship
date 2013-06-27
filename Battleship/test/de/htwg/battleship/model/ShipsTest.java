package de.htwg.battleship.model;

import static org.junit.Assert.*;
import junit.framework.Assert;


import org.junit.Before;
import org.junit.Test;

import de.htwg.battleship.model.Rowboat;


public class ShipsTest {
	public Rowboat a;
	
	@Before
	public void createRowboat(){
		a = new Rowboat(1,1);
		assertNotNull(a);
	}
	
	@Test
	public void testIsHorizontal() {
		assertFalse(!a.getAlignment());
	}

	@Test
	public void testGetSize() {
		assertTrue(a.getSize() >= 1);
	} 

	@Test
	public void testGetPosition() {
		assertTrue(a.getPosition()[0] == 1 && a.getPosition()[1] == 1);
	}

	@Test
	public void testSetPosition() {
		a.setPosition(2, 2);
		assertTrue(a.getPosition()[0] == 2 && a.getPosition()[1] == 2);
	}

}
