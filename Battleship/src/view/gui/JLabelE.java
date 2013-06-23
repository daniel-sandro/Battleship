package view.gui;

import javax.swing.Icon;
import javax.swing.JLabel;

public class JLabelE extends JLabel {

	int xPos;
	int yPos;
	private Icon normalIcon;
	private Icon selectedIcon;
	
	public JLabelE(Icon i, Icon selected) {
		super.setIcon(i);
		this.normalIcon = i;
		this.selectedIcon = selected;
	}
	
	public int[] getPosition() {
		int[] positions = {xPos, yPos};
		return positions;
	}
	
	public void setPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public Icon getSelectedIcon() {
		return selectedIcon;
	}

	public void setSelectedIcon(Icon icon) {
		this.selectedIcon = icon;
	}

	public Icon getNormalIcon() {
		return this.normalIcon;
	}
	
	public void setNormalIcon(Icon normalIcon) {
		super.setIcon(normalIcon);
		this.normalIcon = normalIcon;
	}
}
