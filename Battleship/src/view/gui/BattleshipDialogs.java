package view.gui;

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
	
	public void setFieldsize() throws InterruptedException {
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
					try {  
						Integer.getInteger(JTFsize.getText());  
				        Integer value = new Integer(JTFsize.getText());  
				        if(value.intValue() <= 0 || value.intValue() > 26) {
					    	BattleshipGUIUtils.fieldsizeError();
				        } else {
				        	gui.controller.setFieldsize(value);
				    		gui.controller.setInput(true);
				        }
				    } catch (NumberFormatException e1) {
				    	BattleshipGUIUtils.noInputerror();
				    }
				}
			}
		});
		// enterFieldsize.setModal(true);
		enterFieldsize.setVisible(true);
		while (!gui.controller.isInput()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}
		enterFieldsize.setVisible(false);
    	enterFieldsize.dispose();
	}
}
