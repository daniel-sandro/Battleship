package model;

public class Rowboat extends Ships {

	private static final int SIZE = 1;
	
	public Rowboat(boolean vertikal, boolean horizontal){
		this.setSize(SIZE);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);	
	}
	
	public Rowboat(int row, int col, boolean vertikal, boolean horizontal) {
		this.setSize(SIZE);
		setPosition(row, col);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
	}
}
