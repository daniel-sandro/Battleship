package battleship;

public class Bot extends Player{
	
	/*
	public int numberShips;
	public Playboard playboard;
	*/
	private int[] lasthit;
	
	public Bot() {
		lasthit = new int[2];
	}
	
	@Override
	public void initPlayboard(int size) {
		playboard = new Playboard(size);
		shots = new int[playboard.getSize()][playboard.getSize()];
	}
	
	protected int[] initRandomNumber() {
		int[] randPos = new int[2];
		int X = (int) (Math.random() * getPlayboard().getSize());
		int Y = (int) (Math.random() * getPlayboard().getSize());
		randPos[0] = X;
		randPos[1] = Y;
		return randPos;
	}
	

	public void shoot() {
		int[] shot;
		do{
			shot = initRandomNumber();
		}
		while (shots[shot[0]][shot[1]] == 1);
		playboard.getBoard()[shot[0]][shot[1]].shoot();
		shots[shot[0]][shot[1]] = 1;
	}
}
