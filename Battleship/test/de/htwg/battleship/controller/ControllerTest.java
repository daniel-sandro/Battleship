package de.htwg.battleship.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.battleship.model.Bot;
import de.htwg.battleship.model.Field.state;
import de.htwg.battleship.model.Human;
import de.htwg.battleship.model.Playboard;


public class ControllerTest {
	
	Controller c;
	Playboard d;
	
	@Before
	public void setup(){
		c = new Controller();
		assertNotNull(c);
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
		c = new Controller();
		assertNotNull(c);
	}
	 
	@Test
	public void testShootBot() {
		Controller con = new Controller();
		con.setFieldsize(1);
		con.initPlayers(con.getFieldsize());
		con.shootBot(0, 0);
		assertEquals(state.emptyhit, con.getBot().getPlayboard().getField()[0][0].getStat());
		con.setBotRowboat();
		con.shootBot(0, 0);
		assertEquals(state.hit, con.getBot().getPlayboard().getField()[0][0].getStat());
	}
	
	@Test
	public void testShootHuman() {
		Controller con = new Controller();
		con.initPlayers(1);
		con.setHumanRowboat(0, 0);
		con.shootHuman();
		assertEquals(0, con.getPlayer().getNumberShips());
	}
	
	@Test
	public void testSetHumanFlattop() {
		c = new Controller();
		c.initPlayers(5);
		c.setHumanFlattop(0, 0, true);
		assertEquals(state.ship, c.getPlayer().getPlayboard().getField()[0][0]. getStat());
		assertEquals(1, c.getPlayer().getNumberShips());
	}
	
	@Test
	public void testSetHumanDestructor() {
		c = new Controller();
		c.initPlayers(3);
		c.setHumanDestructor(0, 0, true);
		assertEquals(state.ship, c.getPlayer().getPlayboard().getField()[0][0]. getStat());
		assertEquals(1, c.getPlayer().getNumberShips());
	}
	
	@Test
	public void testSetBotFlattop() {
		c = new Controller();
		c.initPlayers(5);
		c.setBotFlattop(false);
		assertEquals(1, c.getBot().getNumberShips());
	}
	
	@Test
	public void testSetBotDestructor() {
		c = new Controller();
		c.initPlayers(3);
		c.setBotDestructor(false);
		assertEquals(1, c.getBot().getNumberShips());
	}
	
	@Test
	public void testGetState() {
		c.initPlayers(1);
		assertEquals(state.empty, c.getState(c.getPlayer().getPlayboard().getField()[0][0]));
	}
	
	@Test
	public void testSetShipsBot() {
		c = new Controller();
		c.setFieldsize(10);
		c.initPlayers(10);
		c.setShipsBot();
		assertEquals(3, c.getBot().getNumberShips());
	}
	
	@Test
	public void testCheckSetShipPosition() {
		c = new Controller();
		c.initPlayers(5);
		assertEquals(1, c.checkSetShipPosition(1, 3, 3, true));
		assertEquals(1, c.checkSetShipPosition(1, 3, 3, false));
		assertEquals(3, c.checkSetShipPosition(2, 3, 3, true));
		assertEquals(3, c.checkSetShipPosition(2, 3, 3, false));
		assertEquals(0, c.checkSetShipPosition(1, 0, 0, true));
	}
	
	@Test
	public void testValidateInput() {
		c.setStep(0);
		assertTrue(c.validateInput("1 1"));
		assertFalse(c.validateInput("1"));
		c.setStep(2);
		assertFalse(c.validateInput("2 2"));
		assertTrue(c.validateInput("2 2 1"));
		c.setStep(8);
		assertTrue(c.validateInput("2 2 1"));
		assertTrue(c.validateInput("2 2"));
	}
	
	@Test
	public void testInput() {
	}
	
	@Test
	public void testInput0() {
		c = new Controller();
		c.initPlayers(2);
		c.setFieldsize(4);
		c.getPlayer().setNumberShips(1);
		c.getBot().setNumberShips(1);
		c.setStep(0);
		assertTrue(c.input("1 1"));
	}
	
	@Test
	public void testInput01() {
		c = new Controller();
		c.initPlayers(2);
		c.setFieldsize(4);
		c.getPlayer().setNumberShips(1);
		c.getBot().setNumberShips(1);
		c.setFieldsize(2);
		assertTrue(c.input("1 1"));
	}
	
	@Test
	public void testBotTurn() {
		c = new Controller();
		c.initPlayers(2);
		c.botTurn();
		assertTrue(true);
	}
	
	@Test
	public void testInput1() {
		c = new Controller();
		c.initPlayers(10);
		c.setFieldsize(10);
		c.setStep(2);
		assertTrue(!c.input("1 1 1"));
	}
	
	@Test
	public void testCaseOne() {
		c = new Controller();
		c.initPlayers(10);
		c.setFieldsize(10);
		c.setStep(1);
		assertTrue(!c.input("1 1 1"));
	}
	
	@Test
	public void testCaseOne1() {
		c = new Controller();
		c.initPlayers(5);
		c.setFieldsize(6);
		c.setStep(1);
		assertTrue(!c.input("1 1 1"));
	}
	
	@Test
	public void testInput2() {
		c = new Controller();
		c.initPlayers(8);
		c.setFieldsize(8);
		c.setStep(1);
		assertTrue(c.input("7 7 1"));
	}
	
	@Test
	public void testInput3() {
		c = new Controller();
		c.initPlayers(8);
		c.setFieldsize(8);
		c.setStep(2);
		assertTrue(c.input("7 7 1"));
	}
	
	@Test
	public void testInput4() {
		c = new Controller();
		c.setFieldsize(1);
		c.initPlayers(1);
		c.getBot().setNumberShips(0);
		c.getPlayer().setNumberShips(0);
		c.setStep(3);
		assertEquals(false, c.input("0 0"));
	}
	
	@Test
	public void testFive() {
		c = new Controller();
		c.initPlayers(4);
		c.setStep(5);
		c.input("1");
		c.input("2");
		c.input("3");
		c.input("4");
		assertTrue(!c.input("3"));
	}
	
	@Test
	public void testInitPlayers() {
		c.initPlayers(c.getFieldsize());
		assertNotNull(c.getBot());
		assertNotNull(c.getPlayer());
	}
	
	@Test
	public void testGetStep() {
		c.setStep(0);
		assertEquals(0, c.getStep());
	}
	
	@Test
	public void testGetLastShotBot() {
		c = new Controller();
		c.setFieldsize(1);
		c.initPlayers(1);
		c.shootHuman();
		int[] x = {0, 0};
		assertEquals(x[0], c.getLastBotShot()[0]);
		assertEquals(x[1], c.getLastBotShot()[1]);
	}
	
	@Test
	public void testSleep() {
		c.sleep(1);
		assertTrue(true);
	}
	
	@Test
	public void testIsGameOver() {
		c.initPlayers(1);
		assertEquals(2, c.isGameOver());
		assertEquals(true, c.gameOver());
		c.setHumanRowboat(0, 0);
		assertEquals(true, c.gameOver());
		assertEquals(1, c.isGameOver());
		c.setBotRowboat();
		assertEquals(0, c.isGameOver());
		assertEquals(false, c.gameOver());
	}
	
	@Test
	public void testStart() {
		c = new Controller();
		c.initPlayers(10);
		c.setFieldsize(10);
		c.start();
		assertTrue(true);
	}
	
	@Test
	public void testSetCorrectPos() {
		c.setCorrectPos(1);
		assertEquals(1, c.getCorrectPos());
	}
	
	@Test
	public void testSetCorrectAl() {
		c.setCorrectAl(true);
		assertEquals(true, c.isCorrectAl());
	}
	
	@Test
	public void testGetStatusLine() {
		assertNotNull(c.getStatus());
	}
	
	@Test
	public void testSetStatus() {
		c.setStatus("test");
		assertEquals("test", c.getStatus());
	}
	
	@Test
	public void howManyShipsHuman() {
	}
	
	@Test
	public void testGetInput() {
		assertEquals(0, c.getInput());
	}
	
	@Test
	public void testSetFieldsize() {
		c.setFieldsize(4);
		assertEquals(4, c.getFieldsize());
	}
}
