package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest {

	@Test
	public final void testInitUtils() {
		assertNotNull(Utils.initUtils());
	}
}
