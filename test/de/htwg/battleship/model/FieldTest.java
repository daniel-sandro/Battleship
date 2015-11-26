package de.htwg.battleship.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import de.htwg.battleship.model.Field.state;


public class FieldTest {
	
	Field a;

	@Before
	public void testField() {
		a = new Field();
		assertNotNull(a);
		assertEquals(state.empty, a.getStat());
	}
	

	@Test
	public void testGetStat() {
		assertNotNull(a.getStat());
	}
	
	@Test
	public void testShoot() {
		Ship x = new Flattop(0, 0, true);
		assertEquals(state.empty, a.getStat());
		a.shoot();
		assertEquals(state.emptyhit, a.getStat());
		a.setShip(x);
		a.shoot();
		assertEquals(state.hit, a.getStat());
		a.shoot();
		assertEquals(state.hit, a.getStat());
	}

	@Test
	public void testGetShip() {
		a.getShip();
		assertNotNull(a);
	}

	@Test
	public void testSetShip() {
		Ship b = new Flattop(1, 1, true);
		assertNotNull(b);
		a.setShip(b);	
		assertNotNull(a.getShip());
	}

}
