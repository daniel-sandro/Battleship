package battleship;

public class Playboard {
	private int size;
	private Field[][] board;
	
	public Playboard(int size) {
		this.size = size;
		board = new Field[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				board[i][j] = new Field();
			}
		}		
	}
	
	public Field[][] getBoard() {
		return board;
	}

	public void setShip(Ships a) {
		int[] posi = a.getPosition();
		for (int i = 0; i < a.getSize(); i++) {
			this.board[posi[0]][posi[1]+i].setShip(a);
			//System.out.printf("board[%d][%d].state: %s", posi[0], posi[1], board[posi[0]][posi[1]].getStat().toString()  );
		}
	}

	public int getSize() {
		return size;
	}
}


