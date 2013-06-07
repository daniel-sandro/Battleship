package model;

public class Playboard {
	private int size;
	private Field[][] field;
	
	public Playboard(int size) {
		this.size = size;
		field = new Field[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				field[i][j] = new Field();
			}
		}		
	}
	
	public Field[][] getField() {
		return field;
	}

	public void setShip(Ships a) {
		int[] posi = a.getPosition();
		if(a.vertical){
			for(int i = 0; i<a.getSize(); i++){
				this.field[posi[0]+i][posi[1]].setShip(a);
			}
		}else{
			for (int i = 0; i < a.getSize(); i++) {
				this.field[posi[0]][posi[1]+i].setShip(a);
			}
		}
		
	}

	public int getSize() {
		return size;
	}
}


