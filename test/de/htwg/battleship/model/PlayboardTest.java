package de.htwg.battleship.model;

import de.htwg.battleship.model.Field.state;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


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
		assertNotNull(a.getField());
	}

	@Test
	public void testSetShip() {
		Ships b = new Rowboat(0, 0);
		a.setShip(b);
		for(int i = 0; i < b.getSize(); i++){
			assertEquals(state.ship, a.getField()[b.getPosition()[0]][b.getPosition()[1]+i].getStat());
		}
		assertNotNull(a);
	}

	@Test
	public void testGetSize() {
		int b = a.getSize();
		assertEquals(1, b);
	}
}
