package model;

public class Destructor extends Ships {
	
	public Destructor(boolean vertikal, boolean horizontal){
		this.setSize(3);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
	}
	
	public Destructor(int row, int col, boolean vertikal, boolean horizontal) {
		this.setSize(3);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
		setPosition(row, col);
	}	
}
