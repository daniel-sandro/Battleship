package battleship;

public class Human extends Player {
	/*
	public int numberShips;
	public Playboard playboard;
	*/

	public Human(){
	}
	
	public void shoot(int row, int col){
		getPlayboard().getBoard()[row][col].shoot();
	}

	@Override
	public void initPlayboard(int size) {
		// TODO Auto-generated method stub
		
	}
}
