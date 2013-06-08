package model;

public class Human extends Player {
	
	public Human(int size){
		setPlayboard(new Playboard(size));
	}
	
	public void shoot(Field field){
		field.shoot();
	}

	@Override
	public void initPlayboard(int size) {
		setPlayboard(new Playboard(size));
	}
}
