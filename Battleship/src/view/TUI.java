/*
 * Aus irgendeinem grund setzt der Computer nur ein Schiff und das immer bei 0/0 horizontal, aber Horizontal vertikal funktioniert eigentlich!!
 * */package view;


import java.util.Scanner;

import controller.Controller;

import model.Field.state;

public class TUI {

	private static Controller c;
	private static int fieldsize;

	public static void showBotField() {
		for (int i = 0; i < fieldsize; i++) {
			if (i == 0) {
				System.out.print(" ");
				for (int k = 65; k < fieldsize + 65; k++) {
					System.out.printf(" | %s", (char) k);
				}
				System.out.println();
			}
			if (i <= 9) {
				System.out.printf("%d | ", i); 
			} else { 
				System.out.printf("%d| ", i);

			}
			for (int j = 0; j < fieldsize; j++) {
				if (c.bot.getPlayboard().getField()[i][j].getStat() == state.empty
						|| c.bot.getPlayboard().getField()[i][j].getStat() == state.ship) {
					System.out.print("_ | ");
				} else if (c.bot.getPlayboard().getField()[i][j].getStat() == state.emptyhit) {
					System.out.print("O | ");
				} else if (c.bot.getPlayboard().getField()[i][j].getStat() == state.hit) {
					System.out.print("X | ");
				}

			}
			System.out.println("");
		}
	}
	
	public static void CHEATshowBotField() {
		for (int i = 0; i < fieldsize; i++) {
			if (i == 0) {
				System.out.print(" ");
				for (int k = 65; k < fieldsize + 65; k++) {
					System.out.printf(" | %s", (char) k);
				}
				System.out.println();
			}
			if (i <= 9) {
				System.out.printf("%d | ", i);
			} else {
				System.out.printf("%d| ", i);

			}
			for (int j = 0; j < fieldsize; j++) {
				/*if (c.bot.getPlayboard().getField()[i][j].getStat() == state.empty
						|| c.bot.getPlayboard().getField()[i][j].getStat() == state.ship) {*/
				if (c.bot.getPlayboard().getField()[i][j].getStat() == state.empty){
					System.out.print("_ | ");
				} else if (c.bot.getPlayboard().getField()[i][j].getStat() == state.emptyhit) {
					System.out.print("O | ");
				} else if (c.bot.getPlayboard().getField()[i][j].getStat() == state.hit) {
					System.out.print("X | ");
				}
				else if(c.bot.getPlayboard().getField()[i][j].getStat() == state.ship){
					System.out.print("S | ");
				}

			}
			System.out.println("");
		}
	}

	public static void showField() {
		for (int i = 0; i < fieldsize; i++) {
			if (i == 0) {
				System.out.print(" ");
				for (int k = 65; k < fieldsize + 65; k++) {
					System.out.printf(" | %s", (char) k);
				}
				System.out.println();
			}
			if (i <= 9) {
				System.out.printf("%d | ", i);
			} else {
				System.out.printf("%d| ", i);

			}
			for (int j = 0; j < fieldsize; j++) {
				if (c.player.getPlayboard().getField()[i][j].getStat() == state.empty) {
					System.out.print("~ | ");
				} else if (c.player.getPlayboard().getField()[i][j].getStat() == state.emptyhit) {
					System.out.print("O | ");
				} else if (c.player.getPlayboard().getField()[i][j].getStat() == state.hit) {
					System.out.print("X | ");
				} else if (c.player.getPlayboard().getField()[i][j].getStat() == state.ship) {
					System.out.print("S | ");
				}

			}
			System.out.println("");
		}
	}

	public static void main(final String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Willkommen zu Battleship!!!! \n\nInitialisieren Sie zunächst die Feldgröße: ");
		do{
			fieldsize = scanner.nextInt();
			if(fieldsize > 26 || fieldsize < 1){
				System.out.println("Die Feldgröße muss zwischen 1 & 26 liegen!!!!");
				continue;
			}
			break;
		}while(true);
		
		c = new Controller(fieldsize);
		
		if(c.bot.vertical()){
			c.setBotRowboat(true, false);
		}else{
			c.setBotRowboat(false, true);
		}
		
		if(c.bot.vertical()){
			c.setBotDestructor(true, false);
		}else{
			c.setBotDestructor(false, true);
		}
		
		if(c.bot.vertical()){
			c.setBotFlattop(true, false);
		}else{
			c.setBotFlattop(false, true);
		}

		int nextX =0;
		int nextY =0;
		int nextbool =0;
		System.out.println("Setzen Sie ihre Schiffe!! \nRuderboot: ([X/Y])\n");
		nextX = scanner.nextInt();
		nextY = scanner.nextInt();
		System.out.println("vertikal oder horizontal setzen ? (1 oder 2)");
		do{
			nextbool = scanner.nextInt();
			if (nextbool == 1){
				c.setHumanRowboat(nextX, nextY, true, false);
				break;
			}else if(nextbool == 2){
				c.setHumanRowboat(nextX, nextY, false, true);
				break;
			}else{
				System.out.println("Des war dein falsche Entscheidung, du kleiner " +
						"Sportsfreund!!!!!!!!!!!!!!!!!!!!! gleich nochmal\n"); 
				}
		}while(true);
		
		System.out.println("\nDestructor-Boot: ([X/Y])\n");
		nextX = scanner.nextInt();
		nextY = scanner.nextInt();
		System.out.println("vertikal oder horizontal setzen ? (1 oder 2)");
		nextbool = scanner.nextInt();
		if (nextbool == 1){
			c.setHumanDestructor(nextX, nextY, true, false);
		}else if(nextbool == 2){
			c.setHumanDestructor(nextX, nextY, false, true);
		}else{
			System.out.println("So nich Kollege!!!!!!!!!!!!!!!!!!!!!");
			System.exit(0);
		}
		
		System.out.println("\nFlattop-Boot: ([X/Y])\n");
		nextX = scanner.nextInt();
		nextY = scanner.nextInt();
		System.out.println("vertikal oder horizontal setzen ? (1 oder 2)");
		nextbool = scanner.nextInt();
		if (nextbool == 1){
			c.setHumanFlattop(nextX, nextY, true, false);
		}else if(nextbool == 2){
			c.setHumanFlattop(nextX, nextY, false, true);
		}else{
			System.out.println("So nich Kollege!!!!!!!!!!!!!!!!!!!!!");
			System.exit(0);
		}
		

		System.out.println("Alles klar!");
		System.out.println("Dein Feld sieht aus wie folgt:");
		showField();
		while (c.player.getNumberShips() > 0 && c.bot.getNumberShips() > 0) {
			System.out.println("Du bist dran!!!!!!!!!!!!!! KNALL IHN AB MAAAAAAAAAAAAAAAAAAN");
			System.out.println("Deine Optionen im Spiel sind:\nEIGENES FELD ANZEIGEN (1)\nAUF FELD DES COMPUTERS SCHIEßEN (2)\n");

			switch (scanner.nextInt()) {
			case 1:
				showField();
				break;
			case 2:
				System.out.println("Nenne die Position auf die geschossen werden soll: ([X/Y])");
				c.shootBot(scanner.nextInt(), scanner.nextInt());
				showBotField();
				if (c.bot.getNumberShips() == 0) {
					System.out.println("Glückwunsch!! Du hast gewonnen!!!!");
					break;
				}
				System.out.println("Bot ist am Zug!");
				Thread.sleep(1000);
				c.shootHuman();
				if (c.player.getNumberShips() == 0) {
					System.out.println("Hasch verkackt wa!!!!");
					break;
				}
				break;
			case 3: 
				CHEATshowBotField();
				break;
			}
		}
	}
}
