package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Model.Field;
import Model.Flattop;
import Model.Playboard;
import Model.Rowboat;
import Model.Ships;
import Model.Field.state;

public class ControllerTest {
	
	Controller c;
	Playboard d;
	
	@Before
	public void setup(){
		c = new Controller(1);
		assertNotNull(c);
		assertTrue(c.player != null && c.bot != null);
	}
	
	@Test
	public void testController()  {
		assertNotNull(c);
		assertTrue(c.player != null && c.bot != null);
	}
	
	@Test
	public void testShootBot() {
		assertTrue(c.bot.getPlayboard().getField()[0][0].getStat() == state.empty);
		c.shootBot(0, 0);
		assertTrue(c.bot.getPlayboard().getField()[0][0].getStat() == state.emptyhit ||
				c.bot.getPlayboard().getField()[0][0].getStat() == state.empty ||
				c.bot.getPlayboard().getField()[0][0].getStat() == state.ship);
		c.setBotRowboat(true, false);
		assertTrue(c.bot.getPlayboard().getField()[0][0].getStat() == state.emptyhit ||
				c.bot.getPlayboard().getField()[0][0].getStat() == state.empty ||
				c.bot.getPlayboard().getField()[0][0].getStat() == state.ship);
		int x = c.bot.getNumberShips();
		c.shootBot(0, 0);
		assertTrue(c.bot.getPlayboard().getField()[0][0].getStat() == state.hit);
	}
	
	@Test
	public void testShootHuman() {
		assertTrue(c.player.getPlayboard().getField()[0][0].getStat() == state.empty);
		c.shootHuman();
		assertTrue(c.player.getPlayboard().getField()[0][0].getStat() == state.emptyhit ||
				c.player.getPlayboard().getField()[0][0].getStat() == state.empty ||
				c.player.getPlayboard().getField()[0][0].getStat() == state.ship);
		c.setHumanRowboat(0, 0, true, false);
		assertTrue(c.player.getPlayboard().getField()[0][0].getStat() == state.emptyhit ||
				c.player.getPlayboard().getField()[0][0].getStat() == state.empty ||
				c.player.getPlayboard().getField()[0][0].getStat() == state.ship);
		int x = c.player.getNumberShips();
		c.shootHuman();
		assertTrue(c.player.getPlayboard().getField()[0][0].getStat() == state.hit);
		assertTrue(c.player.getNumberShips() == 0);
	}
	
	@Test
	public void testSetHumanFlattop() {
		c.player.initPlayboard(10);
		c.setHumanFlattop(0, 0, true, false);
		assertTrue(c.player.getPlayboard().getField()[0][0]. getStat() == state.ship);
	}
	
	@Test
	public void testSetHumanDestructor() {
		c.player.initPlayboard(3);
		c.setHumanDestructor(0, 0, true, false);
		assertTrue(c.player.getPlayboard().getField()[0][0]. getStat() == state.ship);
	}
	
	@Test
	public void testSetHumanRowboat() {
		c.player.initPlayboard(1);
		c.setHumanRowboat(0, 0, true, false);
		assertTrue(c.player.getPlayboard().getField()[0][0]. getStat() == state.ship);
	}
	
	@Test
	public void testSetBotFlattop() {
		c.bot.initPlayboard(5);
		c.setBotFlattop(true, false);
		assertTrue(c.bot.getPlayboard().getField()[0][0]. getStat() == state.ship);
	}
	
	@Test
	public void testSetBotRowboat(){
		c.bot.initPlayboard(1);
		c.setBotRowboat(true, false);
		assertTrue(c.bot.getPlayboard().getField()[0][0]. getStat() == state.ship);
	}
	
	@Test
	public void testSetBotDestructor() {
		c.bot.initPlayboard(3);
		c.setBotDestructor(true, false);
		assertTrue(c.bot.getPlayboard().getField()[0][0]. getStat() == state.ship);
	}
}
