package de.htwg.battleship.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

public class Playboard {
	private Field[][] playboard;

	public Playboard(int size) {
		playboard = new Field[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				playboard[i][j] = new Field();
			}
		}
	}

	public Field getField(int row, int col) {
		return playboard[row][col];
	}

	public Field getField(Position p) {
		return playboard[p.getRow()][p.getCol()];
	}

	public int getSize() {
		return playboard.length;
	}

	public boolean validPosition(Position p) {
		return p.getRow() >= 0 && p.getRow() < getSize() && p.getCol() >= 0 && p.getCol() < getSize();
	}

	public String toJSON() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode playerPlayboard = mapper.createArrayNode();
		for (int i = 0; i < playboard.length; i++) {
			ArrayNode array = mapper.createArrayNode();
			for (int j = 0; j < playboard[i].length; j++) {
				array.add(playboard[i][j].toString());
			}
			playerPlayboard.add(array);
		}
		return playerPlayboard.toString();
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < playboard.length; i++) {
			for (int j = 0; j < playboard[0].length; j++) {
				s += playboard[i][j].toString();
			}
		}
		return s;
	}
}
