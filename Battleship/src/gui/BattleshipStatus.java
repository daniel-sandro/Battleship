package gui;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


/**
 *
 * @author Sandro
 */
public class BattleshipStatus extends JPanel {

    private JLabel jlImplementierung = new JLabel("Implementierung:           ");
    private JLabel jlEintraege = new JLabel("Einträge:           ");
    private JTextField implementierung;
    private JTextField eintraege;

    public BattleshipStatus() {

        jlImplementierung.setHorizontalAlignment(JTextField.RIGHT);
        implementierung = new JTextField();
        implementierung.setEditable(false);
        implementierung.setBorder(BorderFactory.createEmptyBorder());
        implementierung.setHorizontalAlignment(JTextField.LEFT);
        jlEintraege.setHorizontalAlignment(JTextField.RIGHT);
        eintraege = new JTextField();
        eintraege.setBorder(BorderFactory.createEmptyBorder());
        eintraege.setHorizontalAlignment(JTextField.LEFT);
        eintraege.setEditable(false);
        this.add(jlImplementierung);
        this.add(implementierung);
        this.add(jlEintraege);
        this.add(eintraege);

        this.setBorder(new TitledBorder("Wörterbuch"));
        this.setLayout(new GridLayout(2, 3));
        this.setVisible(true);
    }
}
