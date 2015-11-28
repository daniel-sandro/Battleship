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

	public Field getField(Position p) {
		return field[p.getRow()][p.getCol()];
	}

	public int getSize() {
		return field.length;
	}

	public boolean validPosition(Position p) {
		return p.getRow() >= 0 && p.getRow() < getSize() && p.getCol() >= 0 && p.getCol() < getSize();
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				s += field[i][j].toString();
			}
		}
		return s;
	}
}
