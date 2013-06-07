package model;

public class Destructor extends Ships {
	
	public Destructor(boolean vertikal, boolean horizontal){
		this.setSize(3);
		this.vertical = vertikal;
		this.horizontal = horizontal;
	}
	
	public Destructor(int row, int col, boolean vertikal, boolean horizontal) {
		this.setSize(3);
		this.vertical = vertikal;
		this.horizontal = horizontal;
		setPosition(row, col);
	}	
}
