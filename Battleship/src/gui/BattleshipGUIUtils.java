package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BattleshipGUIUtils extends JFrame {

	public static void fieldsizeError() {
		JOptionPane.showMessageDialog(BattleshipGUI.getMainPanel(), "Bitte eine Feldgröße zwischen 1 und 26" +
				" eingeben!", "Fehler!", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void noInputerror() {
		JOptionPane.showMessageDialog(BattleshipGUI.getMainPanel(), "Bitte etwas in das Eingabefeld" +
				" eingeben!", "Fehler!", JOptionPane.ERROR_MESSAGE);
	}

    public static int exit() {
    	System.exit(0);
    	return 0;
    }
}
