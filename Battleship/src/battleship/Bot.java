package battleship;

public class Bot extends Player{
	
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
		int x = (int) (Math.random() * getPlayboard().getSize());
		int y = (int) (Math.random() * getPlayboard().getSize());
		randPos[0] = x;
		randPos[1] = y;
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
