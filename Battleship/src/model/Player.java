package model;

public abstract class Player {
	
	private int numberShips = 0;
	private Playboard playboard;

	public abstract void initPlayboard(int size);
	
	public int getNumberShips() {
		return numberShips;
	}

	public void setNumberShips(int numberShips) {
		this.numberShips = numberShips;
	}

	public Playboard getPlayboard() {
		return playboard;
	}

	public void setPlayboard(Playboard playboard) {
		this.playboard = playboard;
	}
}
