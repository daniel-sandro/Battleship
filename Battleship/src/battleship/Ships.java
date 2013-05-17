package battleship;

public abstract class Ships {
	
	private int size;
	private int[] position = new int[2];
	
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
