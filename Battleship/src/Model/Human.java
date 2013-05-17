package Model;

public class Human extends Player {


	public Human(){
		
	}
	
	public void shoot(int row, int col, Playboard board){
		board.getBoard()[row][col].shoot();
	}

	@Override
	public void initPlayboard(int size) {
		playboard = new Playboard(size);
	}
}
