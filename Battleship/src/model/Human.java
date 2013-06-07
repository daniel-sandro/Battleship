package model;

public class Human extends Player {

	Playboard playboard = getPlayboard();
	
	public Human(int size){
		initPlayboard(size);
	}
	
	public void shoot(Field field){
		field.shoot();
	}

	@Override
	public void initPlayboard(int size) {
		playboard = new Playboard(size);
	}
}
