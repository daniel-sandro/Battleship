package de.htwg.battleship.view.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BattleshipGUIUtils extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * gives out an errorquote for the case that a ship has been placed badly
	 */
	public static void fieldsizeError() {
		JOptionPane.showMessageDialog(BattleshipGUI.getMainPanel(), "Bitte eine Feldgröße zwischen 1 und 26" +
				" eingeben!", "Fehler!", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * gives out an errorwuote if an input was required but not given
	 */
	public static void noInputerror() {
		JOptionPane.showMessageDialog(BattleshipGUI.getMainPanel(), "Bitte etwas in das Eingabefeld" +
				" eingeben!", "Falsche Eingabe!", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * @param x
	 * @param alignment
	 * Gives out a Hint where to place a ship 
	 */
	public static void wrongPosition(int x, boolean alignment) {
		StringBuilder sb = new StringBuilder();
		String s = "links";
		if (!alignment) {
			s = "oben";
		}
		sb.append("Das Schiff muss um ").append(x).append(" weiter nach ").append(s)
			.append(" verschoben werden, damit es auf das Spielfeld passt!");
		JOptionPane.showMessageDialog(BattleshipGUI.getMainPanel(), sb.toString(),
				"Schiff neu setzen!", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * gives out a quote that asks to set a ship
	 * @param s
	 */
	public static void setShip(String s) {
		JOptionPane.showMessageDialog(BattleshipGUI.getMainPanel(), "Bitte" + s + "setzen!",
				"Bitte Schiff setzen!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * appends the normal outputline for the setship
	 * @param x
	 * @return
	 */
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
	
	/**
	 * @param x
	 * @param al
	 * Gives out a Hint where to place a ship
	 */
	public static void correctShipPosition(int x, boolean al) {
		StringBuilder sb = new StringBuilder();
		sb.append("Das Schiff bitte ").append(x).append(" Feld(er) weiter");
		String s = " links ";
		if (al) {
			s = " oben ";
		}
		sb.append(s).append("setzen!");
		JOptionPane.showMessageDialog(BattleshipGUI.getMainPanel(), sb.toString(),
				"Achtung!", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * gives out a quote that askes to decide whether a ship is placed vertical or hotizontal
	 * @return
	 */
	public static int setAlignment() {
		Object[] options = {"Horizontal", "Vertikal"};
        int selected = JOptionPane.showOptionDialog(null, "Schiff horizontal oder vertikal setzen?",
        		"Ausrichtung", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
        		null, options, options[0]);
        return selected;
	}
	
	/**
	 * gives out a 'you are defeatet' message
	 */
	public static void gameOver() {
		Object[] options = {"OK"};
		JOptionPane.showOptionDialog(null, "Der Bot hat alle deine Schiffe versenkt!\n" + 
			"Versuch es doch noch einmal!", "Game Over!", JOptionPane.YES_OPTION,
			JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
	}
	
	/**
	 * gives out a 'you have won' message
	 */
	public static void won() {
		Object[] options = {"YAY!"};
		JOptionPane.showOptionDialog(null, "Glückwunsch, du hast alle Schiffe des Bots versenkt! Du hast gewonnen!", 
			"Gewonnen!", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
	}
}
