package de.htwg.battleship.view.gui;

import com.google.inject.Inject;
import de.htwg.battleship.controller.IController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.IllegalFormatException;

@SuppressWarnings("serial")
public class BattleshipSetFieldsize extends JFrame implements ActionListener {
	
	private JButton jbgo;
	private JTextField jtfsize;
	private IController controller;
	private static final int MAX = 26;
	private static final int XX = 350;
	private static final int XY = 80;
	
	/**
	 * @param controller
	 * sets the fieldsize
	 */
	@Inject
	public BattleshipSetFieldsize(final IController controller) {
		this.controller = controller;
		
		JPanel enterFieldsize = new JPanel();
		setTitle("Bitte die Feldgröße eingeben!");
		setLayout(new FlowLayout());
		jbgo = new JButton("OK");
		jtfsize = new JTextField(2);
		enterFieldsize.add(new JLabel("Feldgröße:"));
		enterFieldsize.add(jtfsize);
		enterFieldsize.add(jbgo);
		setSize(XX, XY);
		setLocationRelativeTo(null);
		jbgo.addActionListener(this);
		setContentPane(enterFieldsize);
		setVisible(true);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * fetches the source of an event so the classes can react on it
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbgo) {
			String erg = jtfsize.getText();
			int value;
			try {
				value = Integer.valueOf(erg);
		        if(value <= 0 || value > MAX) {
			    	BattleshipGUIUtils.fieldsizeError();
		        } else {
		        	controller.setFieldsize(value);
		    		synchronized (this) {
	    				this.notify();
		    		}
		    		dispose();
		        }
		    } catch (IllegalFormatException e1) {
		    	BattleshipGUIUtils.noInputerror();
		    }
		}		
	}
}
