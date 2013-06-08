package view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TUItest {

	@Before
	public void setup() {
		TUI.setFieldsize(10);
	}
	
	@Test
	public void testShowBotField() {
		assertNotNull(TUI.showBotField() != null);
	}

	@Test
	public void testShowField() {
		assertNotNull(TUI.showField() != null);
	}

	@Test
	public void testCHEATshowBotField() {
		assertNotNull(TUI.cheatShowBotField() != null);
	}
}
