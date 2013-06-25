package view.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.Controller;
import view.gui.*;

public class PlayboardPanel extends JPanel {

	public JLabelE[][] fields;
	private BattleshipGUI gui;
	int fieldsize;
	private static final int HEX = 65;
	URL resource = BattleshipGUI.class.getResource("/images/Field.jpg");
    URL resourceSelected = BattleshipGUI.class.getResource("/images/FieldS.jpg");
    URL resourcePattern = BattleshipGUI.class.getResource("/images/Pattern.jpg");
    Icon icon = new ImageIcon(resource);
    Icon selectedFieldIcon = new ImageIcon(resourceSelected);
    Icon pattern = new ImageIcon(resourcePattern);
    JPanel playboardPanel;
	
	public PlayboardPanel(BattleshipGUI gui) {
		this.gui = gui;
		playboardPanel = new JPanel();
		fieldsize = gui.controller.getFieldsize();
		printPlayboard();
	}
	
	public JPanel getPanel() {
		return this.playboardPanel;
	}
	
	public void setIcon(int x, int y, Icon normal, Icon selected) {
		this.fields[x][y].setNormalIcon(normal);
		this.fields[x][y].setSelectedIcon(selected);
		playboardPanel.repaint();
	}
	
	private JPanel printPlayboard() {
		int width = 25 * fieldsize + 25;
		Border borderRB = BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(135, 180, 255));
	    playboardPanel.setLayout(new GridLayout(fieldsize+1, fieldsize+1));
	    playboardPanel.setSize(width, width);
		fields = new JLabelE[fieldsize + 2][fieldsize + 2];
		for (int i = 0; i < fieldsize + 1; i++) {
			for (int j = 0; j < fieldsize + 1; j++) {
				if (i == 0) {
					if (j == 0) {
						fields[i][j] = new JLabelE(this.pattern, null);
						fields[i][j].setBorder(borderRB);
						playboardPanel.add(fields[i][j]);
					} else {
					fields[i][j + 1] = new JLabelE(this.pattern, null);
					fields[i][j + 1].setText("<html><font style=\"font-size:14px; font-family: " +
							"trebuchet ms,helvetica,sans-serif; color:#3f87ff;\">" + 
							String.valueOf((char) (j - 1 + HEX)) + "</font></html>");
					fields[i][j + 1].setHorizontalTextPosition(JLabel.CENTER);
					fields[i][j + 1].setVerticalTextPosition(JLabel.CENTER);
					fields[i][j + 1].setBorder(borderRB);
					playboardPanel.add(fields[i][j + 1]);
					}
				} else if (j == 0) {
					fields[i][j] = new JLabelE(this.pattern, null);
					fields[i][j].setText("<html><font style=\"font-size:14px; font-family: " +
							"trebuchet ms,helvetica,sans-serif; color:#3f87ff;\">" + 
							String.valueOf(i) + "</font></html>");
					fields[i][j].setHorizontalTextPosition(JLabel.CENTER);
					fields[i][j].setVerticalTextPosition(JLabel.CENTER);
					fields[i][j].setBorder(borderRB);
					playboardPanel.add(fields[i][j]);
				} else {
					fields[i][j] = new JLabelE(icon, selectedFieldIcon);
					//checkStateHuman(fields, i, j);
					fields[i][j].setSize(25, 25);
					fields[i][j].setPosition(i, j);
					fields[i][j].setBorder(borderRB);
					fields[i][j].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(java.awt.event.MouseEvent evt) {
							JLabelE lbl = (JLabelE)evt.getSource();
							lbl.setIcon(lbl.getSelectedIcon());
						}
						@Override
						public void mouseExited(java.awt.event.MouseEvent evt) {
							JLabelE lbl = (JLabelE)evt.getSource();
							lbl.setIcon(lbl.getNormalIcon());
						}
						@Override
						public void mouseClicked(java.awt.event.MouseEvent evt) {
							JLabelE lbl = (JLabelE)evt.getSource();
							gui.mouseClick(lbl.getPosition());
						}
					});
					playboardPanel.add(fields[i][j]);
				}
			}
		}
		return playboardPanel;
	}
}
