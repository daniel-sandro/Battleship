package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class BattleshipDialogs extends JFrame {
	
	private JButton JBgo;
	private JTextField JTFsize;
	private JTextField JTxCol;
	private JTextField JTyRow;
	private int fieldsize, x, y;
	BattleshipGUI gui;
	
	public BattleshipDialogs(BattleshipGUI gui) {
		this.gui = gui;
	}
	
	public void setShip(int nr, boolean x) {
		JDialog setShip = new JDialog();
		JBgo = new JButton("OK");
		setShip.setSize(300, 200);
		setShip.setLocationRelativeTo(null);
		JLabel des = new JLabel("Bitte die Position des Zerstörers angeben!");
		JLabel fla = new JLabel("Bitte die Position des Flugzeugträgers angeben!");
		JLabel row = new JLabel("Bitte die Position des Ruderbootes angeben!");
		JLabel xCol = new JLabel("X-Position: ");
		JTxCol = new JTextField(2);
		JLabel yRow = new JLabel("Y-Position: ");
		JTyRow = new JTextField(2);
		JPanel text = new JPanel();
		if (nr == 1) {
			text.add(row);
		} else if (nr == 2) {
			text.add(des);
		} else if (nr == 3) {
			text.add(fla);
		}
		setShip.setLayout(new BorderLayout(5, 5));
		setShip.setTitle("Bitte die Position angeben!");
		setShip.add(text, BorderLayout.NORTH);
		setShip.add(xCol, BorderLayout.WEST);
		setShip.add(JTxCol, BorderLayout.EAST);
		setShip.add(yRow, BorderLayout.WEST);
		setShip.add(JTyRow, BorderLayout.EAST);
		setShip.add(JBgo, BorderLayout.SOUTH);
		JBgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == JBgo) {
					String s, t;
					s = JTxCol.getText();
					t = JTyRow.getText();
					if (s == null || t == null) {
						BattleshipGUIUtils.noInputerror();
					} else {
						x = Integer.parseInt(JTxCol.getText());
						y = Integer.parseInt(JTyRow.getText());
					}
				}
			}
		});
		setShip.setModal(true);
		setShip.setVisible(true);
		setShip.dispose();
	}
	
	public void setFieldsize() {
		JDialog enterFieldsize = new JDialog();
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
						if (fieldsize < 1 || fieldsize > 26) {
							BattleshipGUIUtils.fieldsizeError();
						} else {
							fieldsize = Integer.valueOf(s);
							gui.controller.setFieldsize(fieldsize);
						}
					}
				}
			}
		});
		enterFieldsize.setModal(true);
		enterFieldsize.setVisible(true);
		enterFieldsize.dispose();
	}
	
	public static boolean setShip(int x, Controller controller) {
		new BattleshipSetShip(1, controller).setVisible(true);
		return true;
	}
}
