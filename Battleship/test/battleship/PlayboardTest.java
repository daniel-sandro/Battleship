package battleship;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import battleship.Field.state;

public class PlayboardTest {
	
	Playboard a;
	
	@Before
	public void setup(){
		a = new Playboard(1);
		assertNotNull(a);
	}

	@Test
	public void testGetBoard() {
		a = new Playboard(1);
		assertNotNull(a);
		assertNotNull(a.getBoard());
	}

	@Test
	public void testSetShip() {
		Ships b = new Rowboat(0, 0);
		a.setShip(b);
		for(int i = 0; i < b.getSize(); i++){
			assertTrue(a.getBoard()[b.getPosition()[0]][b.getPosition()[1]+i].getStat() == state.ship);
		}
		assertNotNull(a);
	}

	@Test
	public void testGetSize() {
		int b = a.getSize();
		assertEquals(1, b);
	}
}
