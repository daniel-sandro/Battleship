package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BattleshipGUIUtils extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void fieldsizeError() {
		JOptionPane.showMessageDialog(BattleshipGUI.getMainPanel(), "Bitte eine Feldgröße zwischen 1 und 26" +
				" eingeben!", "Fehler!", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void noInputerror() {
		JOptionPane.showMessageDialog(BattleshipGUI.getMainPanel(), "Bitte etwas in das Eingabefeld" +
				" eingeben!", "Fehler!", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void wrongPosition(int x, boolean alignment) {
		StringBuilder sb = new StringBuilder();
		String s = "links";
		if (!alignment) {
			s = "oben";
		}
		sb.append("Das Schiff muss um ").append(x).append(" weiter nach ").append(s)
			.append(" verschoben werden, damit es auf das Spielfeld passt!");
		JOptionPane.showMessageDialog(BattleshipGUI.getMainPanel(), sb.toString(),
				"Fehler!", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void setShip(String s) {
		JOptionPane.showMessageDialog(BattleshipGUI.getMainPanel(), "Bitte" + s + "setzen!",
				"Bitte Schiff setzen!", JOptionPane.INFORMATION_MESSAGE);
	}

    public static int exit() {
    	System.exit(0);
    	return 0;
    }
}
