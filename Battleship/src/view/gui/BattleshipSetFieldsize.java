package view.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BattleshipSetFieldsize extends JFrame {
	
	private JButton JBgo;
	private JTextField JTFsize;
	private JPanel enterFieldsize;
	private BattleshipGUI gui;
	
	public BattleshipSetFieldsize(BattleshipGUI gui) {
		this.gui = gui;
		
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
		JBgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == JBgo) {
					try {  
						Integer.getInteger(JTFsize.getText());  
				        Integer value = new Integer(JTFsize.getText());  
				        if(value.intValue() <= 0 || value.intValue() > 26) {
					    	BattleshipGUIUtils.fieldsizeError();
				        } else {
				        	getGui().controller.setFieldsize(value);
				    		getGui().controller.setInput(true);
				    		dispose();
				        }
				    } catch (NumberFormatException e1) {
				    	BattleshipGUIUtils.noInputerror();
				    }
				}
			}
		});
		setContentPane(enterFieldsize);
		setVisible(true);
	}
	
	private BattleshipGUI getGui() {
		return this.gui;
	}
}
