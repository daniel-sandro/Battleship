package model;

public abstract class Ships {
	
	private int size;
	private int[] position = new int[2];
	private boolean vertical = false;
	private boolean horizontal = false;
	
	public boolean isVertical() {
		return vertical;
	}
	
	public void setVertical(boolean v) {
		this.vertical = v;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean h) {
		this.horizontal = h;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public int[] getPosition(){
		return this.position;
	}
	
	public void setPosition(int row, int col){
		this.position[0] = row;
		this.position[1] = col;
	}
	
	public void setSize(int a){
		this.size = a;
	}

}
