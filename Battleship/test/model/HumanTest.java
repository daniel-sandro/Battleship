package model;

import static org.junit.Assert.*;
import model.Bot;
import model.Human;
import model.Rowboat;
import model.Field.state;

import org.junit.Test;


public class HumanTest {
	
	public Human a;
	public Bot b;

	@Test
	public void testShoot() {
		a = new Human(1);
		b = new Bot(1);
		
		b.getPlayboard().setShip(new Rowboat());
		a.shoot(b.getPlayboard().getField()[0][0]);
		
		assertTrue(b.getPlayboard().getField()[0][0].getStat() == state.hit);
		
	}

}
