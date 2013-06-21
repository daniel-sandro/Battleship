package model;

import static org.junit.Assert.*;
import junit.framework.Assert;

import model.Rowboat;

import org.junit.Before;
import org.junit.Test;


public class RowboatTest {
	public Rowboat a;
	
	@Before
	public void setUp(){
		a = new Rowboat();
	}
	
	@Test
	public void testRowboat() {
		a = new Rowboat(1, 1);
		assertNotNull(a);
	}

	@Test
	public void testSetPosition() {
		a.setPosition(2, 2);
		assertTrue(a.getPosition()[0] == 2 && a.getPosition()[1] == 2);
	}
}
