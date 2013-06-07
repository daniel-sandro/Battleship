package model;

public class Flattop extends Ships {
	
	public Flattop(boolean vertikal, boolean horizontal){
		this.setSize(5);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
	}
	
	public Flattop(int row, int col, boolean vertikal, boolean horizontal) {
		this.setSize(5);
		setPosition(row, col);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
	}
	
}
