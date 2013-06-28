package de.htwg.battleship.view.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.inject.Inject;

import de.htwg.battleship.controller.Controller;
import de.htwg.battleship.controller.IController;

@SuppressWarnings("serial")
public class BattleshipSetFieldsize extends JFrame implements ActionListener {
	
	private JButton JBgo;
	private JTextField JTFsize;
	private JPanel enterFieldsize;
	private IController controller;
	
	@Inject
	public BattleshipSetFieldsize(final IController controller) {
		this.controller = controller;
		
		enterFieldsize = new JPanel();
		setTitle("Bitte die Feldgröße eingeben!");
		setLayout(new FlowLayout());
		JBgo = new JButton("OK");
		JTFsize = new JTextField(2);
		enterFieldsize.add(new JLabel("Feldgröße:"));
		enterFieldsize.add(JTFsize);
		enterFieldsize.add(JBgo);
		setSize(350, 80);
		setLocationRelativeTo(null);
		JBgo.addActionListener(this);
		setContentPane(enterFieldsize);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == JBgo) {
			try {  
				Integer.getInteger(JTFsize.getText());  
		        Integer value = new Integer(JTFsize.getText());  
		        if(value.intValue() <= 0 || value.intValue() > 26) {
			    	BattleshipGUIUtils.fieldsizeError();
		        } else {
		        	controller.setFieldsize(value);
		    		synchronized (this) {
	    				this.notify();
		    		}
		    		dispose();
		        }
		    } catch (NumberFormatException e1) {
		    	BattleshipGUIUtils.noInputerror();
		    }
		}		
	}
}
