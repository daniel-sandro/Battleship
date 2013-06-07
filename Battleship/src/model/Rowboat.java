package model;

public class Rowboat extends Ships {
	
	public Rowboat(boolean vertikal, boolean horizontal){
		this.setSize(1);
		this.vertical = vertikal;
		this.horizontal = horizontal;		
	}
	
	public Rowboat(int row, int col, boolean vertikal, boolean horizontal) {
		this.setSize(1);
		setPosition(row, col);
		this.vertical = vertikal;
		this.horizontal = horizontal;
	}
}
