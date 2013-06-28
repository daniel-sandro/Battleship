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
		a = new Rowboat(1, 1);
		assertNotNull(a);
	}
	
	@Test
	public void testIsHorizontal() {
		assertTrue(a.getAlignment());
	}

	@Test
	public void testGetSize() {
		assertEquals(1, a.getSize());
	} 

	@Test
	public void testGetPosition() {
		assertEquals(1, a.getPosition()[0]);
		assertEquals(1, a.getPosition()[1]);
	}

	@Test
	public void testSetPosition() {
		a.setPosition(2, 2);
		assertEquals(2, a.getPosition()[0]);
		assertEquals(2, a.getPosition()[1]);
	}

}
