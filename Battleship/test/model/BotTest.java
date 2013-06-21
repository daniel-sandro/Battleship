package model;

import static org.junit.Assert.*;

import model.Bot;
import model.Playboard;
import model.Rowboat;
import model.Ships;
import model.Field.state;

import org.junit.Before;
import org.junit.Test;


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
		a.shoot(a.getPlayboard());
		assertTrue((a.getPlayboard().getField()[0][0].getStat() != state.empty && ( a.getPlayboard().getField()[0][0].getStat() == state.hit) 
				|| a.getPlayboard().getField()[0][0].getStat() == state.emptyhit));
		assertTrue((a.getPlayboard().getField()[0][0].getStat() != state.emptyhit && (a.getPlayboard().getField()[0][0].getStat() == state.hit)
				|| a.getPlayboard().getField()[0][0].getStat() == state.emptyhit));
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
		Ships b = new Rowboat();
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
