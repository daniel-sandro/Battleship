package de.htwg.battleship.view.gui;

import javax.swing.Icon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class JLabelE extends JLabel {

	private int xPos;
	private int yPos;
	private Icon normalIcon;
	private Icon selectedIcon;
	
	/**
	 * @param i
	 * @param selected
	 * initializes stuff...
	 */
	public JLabelE(Icon i, Icon selected) {
		super.setIcon(i);
		this.normalIcon = i;
		this.selectedIcon = selected;
	}
	
	/**
	 * @return
	 * returns the x-coordinate
	 */
	public int getXPosition() {
		return xPos;
	}
	
	/**
	 * @return
	 * returns the y-coordinate
	 */
	public int getYPosition() {
		return yPos;
	}
	
	/**
	 * @param x
	 * @param y
	 * sets the position
	 */
	public void setPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	/**
	 * @return
	 * returns the selected icon
	 */
	public Icon getSelectedIcon() {
		return selectedIcon;
	}

	/**
	 * @param icon
	 * sets the selected icon
	 */
	public void setSelectedIcon(Icon icon) {
		this.selectedIcon = icon;
	}

	/**
	 * @return
	 * returns a normal icon
	 */
	public Icon getNormalIcon() {
		return this.normalIcon;
	}
	
	/**
	 * @param normalIcon
	 * sets a normal icon
	 */
	public void setNormalIcon(Icon normalIcon) {
		super.setIcon(normalIcon);
		this.normalIcon = normalIcon;
	}
}
