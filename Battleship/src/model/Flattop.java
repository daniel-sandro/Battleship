package model;

public class Flattop extends Ships {
	
	private static final int SIZE = 5;
	
	public Flattop(boolean vertikal, boolean horizontal){
		this.setSize(SIZE);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
	}
	
	public Flattop(int row, int col, boolean vertikal, boolean horizontal) {
		this.setSize(SIZE);
		setPosition(row, col);
		this.setVertical(vertikal);
		this.setHorizontal(horizontal);
	}
	
}
