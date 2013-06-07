package model;

import static org.junit.Assert.*;
import org.junit.Test;

import Model.Bot;
import Model.Human;
import Model.Rowboat;
import Model.Field.state;

public class HumanTest {
	
	public Human a;
	public Bot b;

	@Test
	public void testShoot() {
		a = new Human(1);
		b = new Bot(1);
		
		b.getPlayboard().setShip(new Rowboat(false, true));
		a.shoot(b.getPlayboard().getField()[0][0]);
		
		assertTrue(b.getPlayboard().getField()[0][0].getStat() == state.hit);
		
	}

}
