package Model;

import Model.Field.state;

public class Bot extends Player{
	
	private int[] lasthit;
	
	public Bot() {
		lasthit = new int[2];
	}
	
	@Override
	public void initPlayboard(int size) {
		playboard = new Playboard(size);
	}
	
	protected int[] initRandomNumber() {
		int[] randPos = new int[2];
		int x = (int) (Math.random() * getPlayboard().getSize());
		int y = (int) (Math.random() * getPlayboard().getSize());
		randPos[0] = x;
		randPos[1] = y;
		return randPos;
	}
	

	public void shoot(Playboard board) {
		int[] shot;
		do{
			shot = initRandomNumber();
		}
		while (board.getBoard()[shot[0]][shot[1]].getStat() != state.hit && 
				board.getBoard()[shot[0]][shot[1]].getStat() != state.emptyhit);
		
		board.getBoard()[shot[0]][shot[1]].shoot();
	}
	
	public void setShip(Ships s){
		int[] posi;
		do{
			posi = initRandomNumber();
		}while(playboard.getBoard()[posi[0]][posi[1]].getStat()!= state.ship);
		
		playboard.setShip(s);
	}
}
