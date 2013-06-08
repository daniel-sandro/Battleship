package model;

public class Destructor extends Ships {
	
	private static final int SIZE = 3;
	
	public Destructor(boolean vertikal, boolean horizontal){
		this.setSize(SIZE);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
	}
	
	public Destructor(int row, int col, boolean vertikal, boolean horizontal) {
		this.setSize(SIZE);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
		setPosition(row, col);
	}	
}
