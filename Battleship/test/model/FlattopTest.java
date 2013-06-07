package model;

import static org.junit.Assert.*;

import model.Flattop;
import model.Ships;

import org.junit.Before;
import org.junit.Test;


public class FlattopTest {
	
	Ships a;
	
	@Before
	public void setUp(){
		a = new Flattop(1, 1, true, false);
	}
	
	@Test
	public void testFlattop() {
		a = new Flattop(true, false);
		assertNotNull(a);
	}

	@Test
	public void testSetPosition() {
		a.setPosition(2, 2);
		assert(a.getPosition()[0] == 2 && a.getPosition()[1] == 2);
	}

}
