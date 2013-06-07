package model;

public class Flattop extends Ships {
	
	public Flattop(boolean vertikal, boolean horizontal){
		this.setSize(5);
		this.vertical = vertikal;
		this.horizontal = horizontal;
	}
	
	public Flattop(int row, int col, boolean vertikal, boolean horizontal) {
		this.setSize(5);
		setPosition(row, col);
		this.vertical = vertikal;
		this.horizontal = horizontal;
	}
	
}
