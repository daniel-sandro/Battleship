package Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Model.Field;
import Model.Flattop;
import Model.Ships;
import Model.Field.state;

public class FieldTest {
	
	Field a;

	@Before
	public void testField() {
		a = new Field();
		assertNotNull(a);
		assertTrue(a.getStat() == state.empty);
	}
	

	@Test
	public void testGetStat() {
		assertNotNull(a.getStat());
	}
	
	@Test
	public void testShoot() {
		Ships x = new Flattop(0, 0, true, false);
		assertTrue(a.getStat() == state.empty);
		a.shoot();
		assertTrue(a.getStat() == state.emptyhit);
		a.setShip(x);
		a.shoot();
		assertTrue(a.getStat() == state.hit);
		a.shoot();
		assertTrue(a.getStat() == state.emptyhit || a.getStat() == state.hit);
		
		
	}

	@Test
	public void testGetShip() {
		a.getShip();
		assertNotNull(a);
	}

	@Test
	public void testSetShip() {
		Ships b = new Flattop(1, 1, true, false);
		assertNotNull(b);
		a.setShip(b);	
		assertNotNull(a.getShip());
	}

}
