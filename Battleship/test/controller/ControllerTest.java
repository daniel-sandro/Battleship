package controller;

import static org.junit.Assert.*;

import model.Bot;
import model.Field;
import model.Flattop;
import model.Human;
import model.Playboard;
import model.Player;
import model.Rowboat;
import model.Ships;
import model.Field.state;

import org.junit.Before;
import org.junit.Test;


public class ControllerTest {
	
	Controller c;
	Playboard d;
	
	@Before
	public void setup(){
		c = new Controller(1);
		assertNotNull(c);
		assertTrue(c.getPlayer() != null && c.getBot() != null);
	}
	
	@Test
	public void testSetPlayer() {
		c.setPlayer(new Human(1));
		assertTrue(c.getPlayer() != null);
	}
	
	@Test
	public void testSetBot() {
		c.setBot(new Bot(1));
		assertTrue(c.getBot() != null);
	}
	
	@Test
	public void testController()  {
		assertNotNull(c);
		assertTrue(c.getPlayer() != null && c.getBot() != null);
	}
	 
	@Test
	public void testShootBot() {
		assertTrue(c.getBot().getPlayboard().getField()[0][0].getStat() == state.empty);
		c.shootBot(0, 0);
		assertTrue(c.getBot().getPlayboard().getField()[0][0].getStat() == state.emptyhit);
		c.setBotRowboat();
		assertTrue(c.getBot().getPlayboard().getField()[0][0].getStat() == state.emptyhit ||
				c.getBot().getPlayboard().getField()[0][0].getStat() == state.empty ||
				c.getBot().getPlayboard().getField()[0][0].getStat() == state.ship);
		int x = c.getBot().getNumberShips();
		c.shootBot(0, 0);
		assertTrue(c.getBot().getPlayboard().getField()[0][0].getStat() == state.hit);
	}
	
	@Test
	public void testShootHuman() {
		assertTrue(c.getPlayer().getPlayboard().getField()[0][0].getStat() == state.empty);
		c.shootHuman();
		assertTrue(c.getPlayer().getPlayboard().getField()[0][0].getStat() == state.emptyhit);
		c.setHumanRowboat(0, 0);
		assertTrue(c.getPlayer().getPlayboard().getField()[0][0].getStat() == state.emptyhit ||
				c.getPlayer().getPlayboard().getField()[0][0].getStat() == state.empty ||
				c.getPlayer().getPlayboard().getField()[0][0].getStat() == state.ship);
		int x = c.getPlayer().getNumberShips();
		c.shootHuman();
		assertTrue(c.getPlayer().getPlayboard().getField()[0][0].getStat() == state.hit);
		assertTrue(c.getPlayer().getNumberShips() == 0);
	}
	
	@Test
	public void testSetHumanFlattop() {
		c.getPlayer().initPlayboard(10);
		c.setHumanFlattop(0, 0, true);
		assertTrue(c.getPlayer().getPlayboard().getField()[0][0]. getStat() == state.ship);
	}
	
	@Test
	public void testSetHumanDestructor() {
		c.getPlayer().initPlayboard(3);
		c.setHumanDestructor(0, 0, true);
		assertTrue(c.getPlayer().getPlayboard().getField()[0][0]. getStat() == state.ship);
	}
	
	@Test
	public void testSetHumanRowboat() {
		c.getPlayer().initPlayboard(1);
		c.setHumanRowboat(0, 0);
		assertTrue(c.getPlayer().getPlayboard().getField()[0][0]. getStat() == state.ship);
	}
	
	@Test
	public void testSetBotFlattop() {
		c.getBot().initPlayboard(5);
		c.setBotFlattop(true);
		assertTrue(c.getBot().getPlayboard().getField()[0][0]. getStat() == state.ship);
	}
	
	@Test
	public void testSetBotRowboat(){
		c.getBot().initPlayboard(1);
		c.setBotRowboat();
		assertTrue(c.getBot().getPlayboard().getField()[0][0]. getStat() == state.ship);
	}
	
	@Test
	public void testSetBotDestructor() {
		c.getBot().initPlayboard(3);
		c.setBotDestructor(true);
		assertTrue(c.getBot().getPlayboard().getField()[0][0]. getStat() == state.ship);
	}
	
	@Test
	public void howManyShipsHuman() {
		
	}
}
