package model;

public class Rowboat extends Ships {
	
	public Rowboat(boolean vertikal, boolean horizontal){
		this.setSize(1);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);	
	}
	
	public Rowboat(int row, int col, boolean vertikal, boolean horizontal) {
		this.setSize(1);
		setPosition(row, col);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
	}
}
