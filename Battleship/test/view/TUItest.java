package view;

import static org.junit.Assert.*;

import org.junit.Test;

public class TUItest {
	
	@Test
	public void testShowBotField(){
		assertNotNull(TUI.showBotField() != null);
	}

	@Test
	public void testShowField(){
		assertNotNull(TUI.showField() != null);
	}
	
	@Test
	public void testCHEATshowBotField(){
		assertNotNull(TUI.CHEATshowBotField() != null);
	}
	
}
