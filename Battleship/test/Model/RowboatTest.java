package Model;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import Model.Rowboat;

public class RowboatTest {
	public Rowboat a;
	
	@Before
	public void setUp(){
		a = new Rowboat(true, false);
	}
	
	@Test
	public void testRowboat() {
		a = new Rowboat(1, 1, true, false);
		assertNotNull(a);
	}

	@Test
	public void testSetPosition() {
		a.setPosition(2, 2);
		assertTrue(a.getPosition()[0] == 2 && a.getPosition()[1] == 2);
	}
}
