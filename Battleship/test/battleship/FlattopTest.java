package battleship;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import battleship.Flattop;
import battleship.Ships;

public class FlattopTest {
	
	Ships a;
	
	@Before
	public void setUp(){
		a = new Flattop(1, 1);
	}
	
	@Test
	public void testFlattop() {
		a = new Flattop(1, 1);
		assertNotNull(a);
	}

	@Test
	public void testSetPosition() {
		a.setPosition(2, 2);
		assert(a.getPosition()[0] == 2 && a.getPosition()[1] == 2);
	}

}
