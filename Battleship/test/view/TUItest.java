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
	public void testShowField() {
		assertNotNull(TUI.showField(true, false) != null);
		assertNotNull(TUI.showField(true, true) != null);
		assertNotNull(TUI.showField(false, false) != null);
	}
}
