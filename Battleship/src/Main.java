import view.TUI;
import controller.Controller;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Controller controller = new Controller(0);
		TUI tui = new TUI(controller);
		
		controller.gameLoop();
	}
}
