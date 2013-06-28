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

import de.htwg.battleship.controller.IController;

@SuppressWarnings("serial")
public class BattleshipSetFieldsize extends JFrame implements ActionListener {
	
	private JButton JBgo;
	private JTextField JTFsize;
	private JPanel enterFieldsize;
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
		
		enterFieldsize = new JPanel();
		setTitle("Bitte die Feldgröße eingeben!");
		setLayout(new FlowLayout());
		JBgo = new JButton("OK");
		JTFsize = new JTextField(2);
		enterFieldsize.add(new JLabel("Feldgröße:"));
		enterFieldsize.add(JTFsize);
		enterFieldsize.add(JBgo);
		setSize(XX, XY);
		setLocationRelativeTo(null);
		JBgo.addActionListener(this);
		setContentPane(enterFieldsize);
		setVisible(true);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * fetches the source of an event so the classes can react on it
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == JBgo) {
			try {  
				Integer.getInteger(JTFsize.getText());  
		        Integer value = new Integer(JTFsize.getText());  
		        if(value.intValue() <= 0 || value.intValue() > MAX) {
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
