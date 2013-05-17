package battleship;

public class Human extends Player {


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
