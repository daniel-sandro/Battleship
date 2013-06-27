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

import model.Field.state;

@SuppressWarnings("serial")
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
    Icon hit = new ImageIcon(BattleshipGUI.class.getResource("/images/Hit.jpg"));
    Icon shot = new ImageIcon(BattleshipGUI.class.getResource("/images/Shot2.png"));
    Icon pre = new ImageIcon(BattleshipGUI.class.getResource("/images/SchiffPre.jpg"));
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
		this.fields[y][x].setNormalIcon(normal);
		this.fields[y][x].setSelectedIcon(selected);
		playboardPanel.repaint();
	}
	
	private JPanel printPlayboard() {
		int width = 25 * fieldsize + 25;
		Border borderRB = BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(135, 180, 255));
	    playboardPanel.setLayout(new GridLayout(fieldsize + 1, fieldsize + 1));
	    playboardPanel.setSize(width, width);
		fields = new JLabelE[fieldsize + 1][fieldsize + 1];
		for (int i = 0; i <= fieldsize; i++) {
			for (int j = 0; j <= fieldsize; j++) {
				if (i == 0) {
					if (j == 0) {
						fields[i][j] = new JLabelE(this.pattern, null);
						fields[i][j].setBorder(borderRB);
						playboardPanel.add(fields[i][j]);
					} else {
					fields[i][j] = new JLabelE(this.pattern, null);
					fields[i][j].setText("<html><font style=\"font-size:14px; font-family: " +
							"trebuchet ms,helvetica,sans-serif; color:#3f87ff;\">" + 
							String.valueOf((char) (j - 1 + HEX)) + "</font></html>");
					fields[i][j].setHorizontalTextPosition(JLabel.CENTER);
					fields[i][j].setVerticalTextPosition(JLabel.CENTER);
					fields[i][j].setBorder(borderRB);
					playboardPanel.add(fields[i][j]);
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
					fields[i][j].setSize(25, 25);
					fields[i][j].setPosition(j, i);
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
							gui.mouseClick(lbl.getXPosition(), lbl.getYPosition());
						}
					});
					playboardPanel.add(fields[i][j]);
				}
			}
		}
		return playboardPanel;
	}
	
	public void update(boolean player) {
		for (int i = 0; i < fieldsize; i++) {
			for (int j = 0; j < fieldsize; j++) {
				if (player) {
					checkstateHuman(i, j);
				} else {
					checkstateBot(i, j);
				}				
			}
		}
	}
	
	public void checkstateBot(int i, int j) {
		if (gui.controller.getBot().getPlayboard().getField()[i][j].getStat() == state.empty) {
		} else if (gui.controller.getBot().getPlayboard().getField()[i][j].getStat() == state.hit) {
			fields[i + 1][j + 1].setNormalIcon(hit);
			fields[i + 1][j + 1].setSelectedIcon(hit);
		} else if (gui.controller.getBot().getPlayboard().getField()[i][j].getStat() == state.emptyhit) {
			fields[i + 1][j + 1].setNormalIcon(hit);
			fields[i + 1][j + 1].setSelectedIcon(hit);
		}
	}
	
	public void checkstateHuman(int i, int j) {
		if (gui.controller.getPlayer().getPlayboard().getField()[i][j].getStat() == state.empty) {
		} else if (gui.controller.getPlayer().getPlayboard().getField()[i][j].getStat() == state.hit) {
			fields[i + 1][j + 1].setNormalIcon(hit);
			fields[i + 1][j + 1].setSelectedIcon(hit);
		} else if (gui.controller.getPlayer().getPlayboard().getField()[i][j].getStat() == state.emptyhit) {
			fields[i + 1][j + 1].setNormalIcon(shot);
			fields[i + 1][j + 1].setSelectedIcon(shot);
		} else if(gui.controller.getPlayer().getPlayboard().getField()[i][j].getStat() == state.ship) {
			fields[i + 1][j + 1].setNormalIcon(pre);
			fields[i + 1][j + 1].setSelectedIcon(pre);
		}
	}
}
