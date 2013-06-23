package gui;

import javax.swing.Icon;
import javax.swing.JLabel;

public class JLabelE extends JLabel{

	int xPos;
	int yPos;
	
	public JLabelE(Icon i) {
		super.setIcon(i);
	}
	
	public int[] getPosition() {
		int[] positions = {xPos, yPos};
		return positions;
	}
	
	public void setPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
}
