package view.gui;

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
	
	public static boolean setShip(int x) {
		String s = " das Ruderboot ";
		if (x == 1) {
			s = " den Zerstörer ";
		} else if (x == 2) {
			s = " den Flugzeugträger ";
		}
		BattleshipGUIUtils.setShip(s);
		return true;
	}
	
	public static void correctShipPosition(int x, boolean al) {
		StringBuilder sb = new StringBuilder();
		sb.append("Das Schiff bitte ").append(x).append(" Feld(er) weiter");
		String s = " links ";
		if (al) {
			s = " oben ";
		}
		sb.append(s).append("setzen!");
		JOptionPane.showMessageDialog(BattleshipGUI.getMainPanel(), sb.toString(),
				"Fehler!", JOptionPane.ERROR_MESSAGE);
	}
	
	public static boolean setAlignment() {
		Object[] options = {"Horizontal", "Vertikal"};
        int selected = JOptionPane.showOptionDialog(null, "Schiff horizontal oder vertikal setzen?",
        		"Ausrichtung", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
        		null, options, options[0]);
        if (selected == 0) {
        	return false;
        }
        return true;
	}
	
	public static void gameOver() {
		Object[] options = {"OK"};
		int n = JOptionPane.showOptionDialog(null, "Der Bot hat alle deine Schiffe versenkt!\n" + 
				"Versuch es doch noch einmal!", "Game Over!", JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if (n == 0) {
			exit();
		}
	}
	
	public static void won() {
		Object[] options = {"YAY!"};
		int n = JOptionPane.showOptionDialog(null, "Glückwunsch, du hast alle Schiffe des Bots versenkt! Du hast gewonnen!", 
				"Gewonnen!", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if (n == 0) {
			exit();
		}
	}

    public static void exit() {
    	System.exit(0);
    }
}
