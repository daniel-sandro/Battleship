package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sandro
 */
public class BattleshipInfos extends JPanel implements ActionListener {

    private JButton buttonAdd;
    private int fieldsize;

    public BattleshipInfos() {
        JPanel addPanel = new JPanel();
        buttonAdd = new JButton("Bla");
        addPanel.add(buttonAdd);
        buttonAdd.addActionListener(this);

        this.setBorder(BorderFactory.createTitledBorder("Eintrag hinzufügen"));
        this.setLayout(new GridLayout(1, 1));
        this.setVisible(true);
        this.add(addPanel);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == buttonAdd) {
        }
    }
}
