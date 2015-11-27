package de.htwg.battleship.model;

public class Playboard {
	private Field[][] field;

	public Playboard(int size) {
		field = new Field[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				field[i][j] = new Field();
			}
		}
	}

	public Field getField(int row, int col) {
		return field[row][col];
	}

	public int getSize() {
		return field.length;
	}
}
