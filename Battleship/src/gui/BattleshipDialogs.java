package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class BattleshipDialogs extends JFrame {
	
	private JButton JBgo;
	private JTextField JTFsize;
	private JTextField JTxCol;
	private JTextField JTyRow;
	private JDialog enterFieldsize;
	private int fieldsize, x, y;
	private BattleshipGUI gui;
	
	public BattleshipDialogs(BattleshipGUI gui) {
		this.gui = gui;
	}
	
	public void setFieldsize() {
		enterFieldsize = new JDialog();
		enterFieldsize.setTitle("Bitte die Feldgröße eingeben!");
		enterFieldsize.setLayout(new FlowLayout());
		JBgo = new JButton("OK");
		JTFsize = new JTextField(2);
		enterFieldsize.add(new JLabel("Feldgröße:"));
		enterFieldsize.add(JTFsize);
		enterFieldsize.add(JBgo);
		enterFieldsize.setSize(300, 80);
		enterFieldsize.setLocationRelativeTo(null);
		JBgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == JBgo) {
					String s;
					s = JTFsize.getText();
					if (s == null) {
						BattleshipGUIUtils.noInputerror();
					} else {
						fieldsize = Integer.valueOf(s);
						if (fieldsize < 1 || fieldsize > 26) {
							BattleshipGUIUtils.fieldsizeError();
						} else {
							gui.getController().setFieldsize(fieldsize);
							enterFieldsize.dispose();
						}
					}
				}
			}
		});
		enterFieldsize.setModal(true);
		enterFieldsize.setVisible(true);
	}
	
	public boolean setShip(int x) {
		String s = " das Ruderboot ";
		if (x == 1) {
			s = " den Zerstörer ";
		} else if (x == 2) {
			s = " den Flugzeugträger ";
		}
		JOptionPane.showMessageDialog(BattleshipGUI.getMainPanel(), "Bitte" + s + "setzen!",
				"Bitte Schiff setzen!", JOptionPane.INFORMATION_MESSAGE);
		return true;
	}
}
