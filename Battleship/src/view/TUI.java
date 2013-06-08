package view;

import java.util.Scanner;

import controller.Controller;

import model.Field.state;
import model.Utils;

public class TUI {
 
	private static Controller c;
	private static int fieldsize;
	private static StringBuilder sb = new StringBuilder();

	public static StringBuilder showBotField() {
		
		sb.setLength(0);
		
		for (int i = 0; i < fieldsize; i++) {
			if (i == 0) {
				sb.append(" ");
				for (int k = 65; k < fieldsize + 65; k++) {
					sb.append(" | ").append((char) k);
				}
				sb.append("\n");
			}
			if (i <= 9) {
				sb.append(i).append(" | "); 
			} else {
				sb.append(i).append("| ");
			}
			for (int j = 0; j < fieldsize; j++) {
				if (c.getBot().getPlayboard().getField()[i][j].getStat() == state.empty
						|| c.getBot().getPlayboard().getField()[i][j].getStat() == state.ship) {
					sb.append("_ | ");
				} else if (c.getBot().getPlayboard().getField()[i][j].getStat() == state.emptyhit) {
					sb.append("O | ");
				} else if (c.getBot().getPlayboard().getField()[i][j].getStat() == state.hit) {
					sb.append("X | ");
				}
			}
			sb.append("\n");
		}
		return sb;
	}
	
	public static StringBuilder CHEATshowBotField() {
		sb.setLength(0);
		
		for (int i = 0; i < fieldsize; i++) {
			if (i == 0) {
				sb.append(" ");
				for (int k = 65; k < fieldsize + 65; k++) {
					sb.append(" | ").append((char) k);
				}
				sb.append("\n");
			}
			if (i <= 9) {
				sb.append(i).append(" | ");
			} else {
				sb.append(i).append("| ");
			}
			for (int j = 0; j < fieldsize; j++) {
				/*if (c.bot.getPlayboard().getField()[i][j].getStat() == state.empty
						|| c.bot.getPlayboard().getField()[i][j].getStat() == state.ship) {*/
				if (c.getBot().getPlayboard().getField()[i][j].getStat() == state.empty){
					sb.append("_ | ");
				} else if (c.getBot().getPlayboard().getField()[i][j].getStat() == state.emptyhit) {
					sb.append("O | ");
				} else if (c.getBot().getPlayboard().getField()[i][j].getStat() == state.hit) {
					sb.append("X | ");
				}
				else if(c.getBot().getPlayboard().getField()[i][j].getStat() == state.ship){
					sb.append("S | ");
				}
			}
			sb.append("\n");
		}
		return sb;
	}

	public static StringBuilder showField() {
		sb.setLength(0);
		
		for (int i = 0; i < fieldsize; i++) {
			if (i == 0) {
				sb.append(" ");
				for (int k = 65; k < fieldsize + 65; k++) {
					sb.append(" | ").append((char) k);
				}
				sb.append("\n");
			}
			if (i <= 9) {
				sb.append(i).append(" | ");
			} else {
				sb.append(i).append("| ");
			}
			for (int j = 0; j < fieldsize; j++) {
				if (c.getPlayer().getPlayboard().getField()[i][j].getStat() == state.empty) {
					sb.append("~ | ");
				} else if (c.getPlayer().getPlayboard().getField()[i][j].getStat() == state.emptyhit) {
					sb.append("O | ");
				} else if (c.getPlayer().getPlayboard().getField()[i][j].getStat() == state.hit) {
					sb.append("X | ");
				} else if (c.getPlayer().getPlayboard().getField()[i][j].getStat() == state.ship) {
					sb.append("S | ");
				}
			}
			sb.append("\n");
		}
		return sb;
	}

	public static void main(final String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Utils.output("Willkommen zu Battleship!!!! \n\nInitialisieren Sie zunächst die Feldgröße: ");
		do{
			fieldsize = scanner.nextInt();
			if(fieldsize > 26 || fieldsize < 1){
				Utils.output("Die Feldgröße muss zwischen 1 & 26 liegen!!!!");
				continue;
			}
			break;
		}while(true);
		
		c = new Controller(fieldsize);
		
		if(c.getBot().vertical()){
			c.setBotRowboat(true, false);
		}else{
			c.setBotRowboat(false, true);
		}
		
		if(c.getBot().vertical()){
			c.setBotDestructor(true, false);
		}else{
			c.setBotDestructor(false, true);
		}
		
		if(c.getBot().vertical()){
			c.setBotFlattop(true, false);
		}else{
			c.setBotFlattop(false, true);
		}

		int nextX =0;
		int nextY =0;
		int nextbool =0;
		Utils.output("Setzen Sie ihre Schiffe!! \nRuderboot: ([X/Y])\n");
		nextX = scanner.nextInt();
		nextY = scanner.nextInt();
		Utils.output("vertikal oder horizontal setzen ? (1 oder 2)");
		do{
			nextbool = scanner.nextInt();
			if (nextbool == 1){
				c.setHumanRowboat(nextY, nextX, true, false);
				break;
			}else if(nextbool == 2){
				c.setHumanRowboat(nextY, nextX, false, true);
				break;
			}else{
				Utils.output("Des war dein falsche Entscheidung, du kleiner " +
						"Sportsfreund!!!!!!!!!!!!!!!!!!!!! gleich nochmal\n"); 
				}
		}while(true);
		
		/*
		Utils.output("\nDestructor-Boot: ([X/Y])\n");
		nextX = scanner.nextInt();
		nextY = scanner.nextInt();
		Utils.output("vertikal oder horizontal setzen ? (1 oder 2)");
		nextbool = scanner.nextInt();
		if (nextbool == 1){
			c.setHumanDestructor(nextX, nextY, true, false);
		}else if(nextbool == 2){
			c.setHumanDestructor(nextX, nextY, false, true);
		}else{
			Utils.output("So nich Kollege!!!!!!!!!!!!!!!!!!!!!");
			System.exit(0);
		}*/
		/*
		Utils.output("\nFlattop-Boot: ([X/Y])\n");
		nextX = scanner.nextInt();
		nextY = scanner.nextInt();
		Utils.output("vertikal oder horizontal setzen ? (1 oder 2)");
		nextbool = scanner.nextInt();
		if (nextbool == 1){
			c.setHumanFlattop(nextX, nextY, true, false);
		}else if(nextbool == 2){
			c.setHumanFlattop(nextX, nextY, false, true);
		}else{
			Utils.output("So nich Kollege!!!!!!!!!!!!!!!!!!!!!");
			System.exit(0);
		}*/
		

		Utils.output("Alles klar!");
		Utils.output("Dein Feld sieht aus wie folgt:");
		Utils.output(showField().toString());
		while (c.getPlayer().getNumberShips() > 0 && c.getBot().getNumberShips() > 0) {
			Utils.output("Du bist dran!!!!!!!!!!!!!! KNALL IHN AB MAAAAAAAAAAAAAAAAAAN");
			Utils.output("Deine Optionen im Spiel sind:\nEIGENES FELD ANZEIGEN (1)\nAUF FELD DES COMPUTERS SCHIEßEN (2)\n");

			switch (scanner.nextInt()) {
			case 1:
				showField();
				break;
			case 2:
				Utils.output("Nenne die Position auf die geschossen werden soll: ([X/Y])");
				c.shootBot(scanner.nextInt(), scanner.nextInt());
				Utils.output(showBotField().toString());
				if (c.getBot().getNumberShips() == 0) {
					Utils.output("Glückwunsch!! Du hast gewonnen!!!!");
					break;
				}
				Utils.output("Bot ist am Zug!");
				Utils.output("Bot ist am Zug!");
				Thread.sleep(1000);
				c.shootHuman();
				if (c.getPlayer().getNumberShips() == 0) {
					Utils.output("Hasch verkackt wa!!!!");
					break;
				}
				break;
			case 3: 
				Utils.output(CHEATshowBotField().toString());
				break;
			}
		}
	}
}
