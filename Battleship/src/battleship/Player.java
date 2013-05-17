package battleship;

public abstract class Player {
	
	protected int numberShips;
	protected Playboard playboard;
	protected int[][] shots;
	

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



	public int[][] getShots() {
		return shots;
	}



	public void setShots(int a, int b) {
		this.shots = new int[a][b];
	}

}
