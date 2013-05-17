package Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Model.Bot;
import Model.Field;
import Model.Playboard;
import Model.Field.state;

public class BotTest {
	
	Bot a;
	Playboard b;
	
	@Before
	public void testBot() {
		a = new Bot();
		assertNotNull(a);
		
	}

	@Test
	public void testshoot() {
		a.initPlayboard(1);
		assertNotNull(a);
		a.shoot();
		assertTrue((a.playboard.getBoard()[0][0].getStat() != state.empty && ( a.playboard.getBoard()[0][0].getStat() == state.hit) 
				|| a.playboard.getBoard()[0][0].getStat() == state.emptyhit));
		assertTrue((a.playboard.getBoard()[0][0].getStat() != state.emptyhit && (a.playboard.getBoard()[0][0].getStat() == state.hit)
				|| a.playboard.getBoard()[0][0].getStat() == state.emptyhit));
		}
	
	@Test
	public void testInitPlayboard() {
		a.initPlayboard(1);
		assertEquals(1, a.getPlayboard().getSize());
	}
	
	@Test
	public void testinitrandomNumber() {
		a.initPlayboard(1);
		assertNotNull(a);
		int[] x = a.initRandomNumber();
		assertEquals(2, x.length);
	}
}
