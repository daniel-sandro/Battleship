package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class BattleshipGUI extends JFrame {

	private JPanel mainPanel;
    private JButton JBsize;
    private JTextField JTFsize;
	BattleshipStatus statusPanel;
	BattleshipInfos infoPanel;
	private int fieldsize;
	Controller controller;
	
	
	public BattleshipGUI(Controller controller) {
		mainPanel = new JPanel();
	    mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	    
	    JButton test = new JButton("test");
	    mainPanel.add(test);
	    
	    
	    statusPanel = new BattleshipStatus();
	    infoPanel = new BattleshipInfos();
	    
	    //mainPanel.add(infoPanel);
	    
	    this.setContentPane(mainPanel);
        this.setTitle("Battleship");
        // this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
        this.go();
	}
	
	public void setFieldsize() {
		final JDialog enterFieldsize = new JDialog();
		enterFieldsize.setTitle("Bitte die Feldgröße eingeben!");
		enterFieldsize.setLayout(new FlowLayout());
		JBsize = new JButton("OK");
		JTFsize = new JTextField(2);
		enterFieldsize.add(new JLabel("Feldgröße:"));
		enterFieldsize.add(JTFsize);
		enterFieldsize.add(JBsize);
		enterFieldsize.setSize(300, 80);
		enterFieldsize.setLocationRelativeTo(null);
		JBsize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == JBsize) {
					String s;
					s = JTFsize.getText();
					if (s == null) {
						error();
					} else {
						if (fieldsize < 1 || fieldsize > 26) {
							error();
						} else {
							fieldsize = Integer.valueOf(s);
							controller.setFieldsize(fieldsize);
						}
					}
				}
			}
		});
		enterFieldsize.setModal(true);
		enterFieldsize.setVisible(true);
		enterFieldsize.dispose();
	}
	
	public void setShip(int x) {
		int nr = x;
		final JDialog setShip = new JDialog();
		setShip.setModal(true);
		JLabel des = new JLabel("Bitte die Position des Zerstörers angeben!");
		JLabel fla = new JLabel("Bitte die Position des Flugzeugträgers angeben!");
		JLabel row = new JLabel("Bitte die Position des Ruderbootes angeben!");
		JLabel xCol = new JLabel("X-Position: ");
		JTextField JTxCol = new JTextField(2);
		JLabel yRow = new JLabel("Y-Position: ");
		JTextField JTyRow = new JTextField(2);
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
		JBsize = new JButton("OK");
		JBsize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == JBsize) {
					fieldsize = Integer.parseInt(JTFsize.getText());
					if (fieldsize < 1 || fieldsize > 26) {
						error();
					} else {
						setShip.dispose();
					}
				}
			}
		});
		setShip.add(JBsize, BorderLayout.SOUTH);
		setShip.setVisible(true);
		setShip.setSize(300, 200);
		setShip.setLocationRelativeTo(null);
	}
	
	private void error() {
		JOptionPane.showMessageDialog(this, "Bitte eine Feldgröße zwischen 1 und 26" +
				" eingeben!", "Fehler!", JOptionPane.ERROR_MESSAGE);
	}
	
	public void go() {
		setFieldsize();
		setShip(1);
	}
	
	public static void main(String args[]) {
		BattleshipGUI gui = new BattleshipGUI(new Controller(0));
	}
}
