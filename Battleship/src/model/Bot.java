package model;

import model.Field.state;

public class Bot extends Player{
	
	public Bot(int fieldsize) {
		setPlayboard(new Playboard(fieldsize));
	}
	
	@Override
	public void initPlayboard(int size) {
		setPlayboard(new Playboard(size));
	}
	
	protected int initRandomNumber() {
		return (int) (Math.random() * getPlayboard().getSize());
	}
	

	public int[] shoot(Playboard board) {
		int[] shot = {0, 0};
		do{
			shot[0] = initRandomNumber();
			shot[1] = initRandomNumber();
		}
		while (board.getField()[shot[0]][shot[1]].getStat() == state.hit || 
				board.getField()[shot[0]][shot[1]].getStat() == state.emptyhit);
		
		board.getField()[shot[0]][shot[1]].shoot();
		return shot;  
	}
	 
	public void setShip(Ships s){
		int[] posi = {0, 0};		
		
		do {
			do{
				posi[0] = initRandomNumber();
			}while(posi[0]+s.getSize() > getPlayboard().getSize());

			do{
				posi[1] = initRandomNumber();
			}while(posi[1]+s.getSize() > getPlayboard().getSize());
			
			
		} while(getPlayboard().getField()[posi[0]][posi[1]].getStat() == state.ship);
		s.setPosition(posi[0], posi[1]);
		getPlayboard().setShip(s);
	}
	
	public boolean vertical(){
		if(initRandomNumber() <= (getPlayboard().getSize() / 2)){
			return true;
		} 
		return false;
	}
}
