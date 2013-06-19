package model;

public abstract class Ships {
	
	private int size;
	private int[] position = new int[2];
	// Alignment false = horizontal, true = vertical
	private boolean alignment = false;

	public boolean getAlignment() {
		return alignment;
	}

	public void setAlignment(boolean a) {
		this.alignment = a;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public int[] getPosition(){
		return this.position;
	}
	
	public void setPosition(int x, int y){
		this.position[0] = x;
		this.position[1] = y;
	}
	
	public void setSize(int a){
		this.size = a;
	}

}
