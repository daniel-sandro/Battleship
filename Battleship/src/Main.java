import javax.swing.SwingUtilities;

import view.gui.BattleshipGUI;
import view.tui.TUI;
import controller.Controller;

public class Main {

	private static Controller controller;
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {
		
		controller = new Controller(0);
		BattleshipGUI gui = new BattleshipGUI(controller);
		//TUI tui = new TUI(controller);
		
		Thread t = new Thread(new Runnable()
		{
		    public void run()
		    {
		        SwingUtilities.invokeLater(new Runnable()
		        {
		            public void run()
		            {
		        		TUI tui = new TUI(controller);
		            }
		        });
		    }
		});
		t.start();
		
		controller.gameLoop();
	}
}
