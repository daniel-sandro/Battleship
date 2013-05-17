package Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Model.Destructor;
import Model.Ships;

public class DestructorTest {
	Ships a;
	
	@Before
	public void setUp(){
		a = new Destructor(1, 1);
	}

	@Test
	public void testDestructor() {
		a = new Destructor(1, 1);
		assertNotNull(a);
	}

	@Test
	public void testSetPosition() {
		a.setPosition(2, 2);
		assert(a.getPosition()[0] == 2 && a.getPosition()[1] == 2);
	}
}
