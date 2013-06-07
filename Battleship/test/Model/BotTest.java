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
		a = new Bot(1);
		assertNotNull(a);
		
	}

	@Test
	public void testshoot() {
		a.initPlayboard(1);
		assertNotNull(a);
		a.shoot(a.playboard);
		assertTrue((a.playboard.getField()[0][0].getStat() != state.empty && ( a.playboard.getField()[0][0].getStat() == state.hit) 
				|| a.playboard.getField()[0][0].getStat() == state.emptyhit));
		assertTrue((a.playboard.getField()[0][0].getStat() != state.emptyhit && (a.playboard.getField()[0][0].getStat() == state.hit)
				|| a.playboard.getField()[0][0].getStat() == state.emptyhit));
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
		int x = a.initRandomNumber();
		assertNotNull(x);
	}
	
	@Test
	public void testsetShip(){
		Ships b = new Rowboat(false, true);
		a.setShip(b);
		assertTrue(b.getPosition()[0] == 0 && b.getPosition()[1] == 0);
	}
	
	@Test
	public void testvertical(){
		Bot b = new Bot(26);
		boolean tr = false;
		boolean fl = false;
		boolean i;
		while(tr == false || fl == false){
			if((i = b.vertical()) == true){
				tr = true;
				assertTrue(i == true || i == false);
			}else{
				fl = true;
				assertTrue(i == true || i == false);
			}
		}
	}
}
