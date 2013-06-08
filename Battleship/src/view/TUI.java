package view;

import java.util.Scanner;

import controller.*;

import model.Field.state;

public final class TUI {

	private static final int HEX = 65;
	private static final int LINELEN = 9;
	private static final int MAXFIELDSIZE = 26;
	private static final int WAIT = 1000;
	private static final String SEP = " | ";

	private static Controller c;
	private static int fieldsize;
	private static StringBuilder sb = new StringBuilder();

	private TUI() {
	}

	public static void main(final String[] args) throws InterruptedException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Utils.output("Willkommen zu Battleship!!!! \n\nInitialisieren Sie zunächst die Feldgröße: ");
		do {
			fieldsize = scanner.nextInt();
			if (fieldsize > MAXFIELDSIZE || fieldsize < 1) {
				Utils.output("Die Feldgröße muss zwischen 1 & 26 liegen!!!!");
				continue;
			}
			break;
		} while (true);

		c = new Controller(fieldsize);

		if (c.getBot().vertical()) {
			c.setBotRowboat(true, false);
		} else {
			c.setBotRowboat(false, true);
		}

		if (c.getBot().vertical()) {
			c.setBotDestructor(true, false);
		} else {
			c.setBotDestructor(false, true);
		}

		if (c.getBot().vertical()) {
			c.setBotFlattop(true, false);
		} else {
			c.setBotFlattop(false, true);
		}

		int nextX = 0;
		int nextY = 0;
		int nextbool = 0;
		Utils.output("Setzen Sie ihre Schiffe!! \nRuderboot: ([X/Y])\n");
		nextX = scanner.nextInt();
		nextY = scanner.nextInt();
		Utils.output("vertikal oder horizontal setzen ? (1 oder 2)");
		do {
			nextbool = scanner.nextInt();
			if (nextbool == 1) {
				c.setHumanRowboat(nextY, nextX, true, false);
				break;
			} else if (nextbool == 2) {
				c.setHumanRowboat(nextY, nextX, false, true);
				break;
			} else {
				Utils.output("Des war dein falsche Entscheidung, du kleiner "
						+ "Sportsfreund!!!!!!!!!!!!!!!!!!!!! gleich nochmal\n");
			}
		} while (true);

		Utils.output("\nDestructor-Boot: ([X/Y])\n");
		nextX = scanner.nextInt();
		nextY = scanner.nextInt();
		Utils.output("vertikal oder horizontal setzen ? (1 oder 2)");
		nextbool = scanner.nextInt();
		if (nextbool == 1) {
			c.setHumanDestructor(nextX, nextY, true, false);
		} else if (nextbool == 2) {
			c.setHumanDestructor(nextX, nextY, false, true);
		} else {
			Utils.output("So nich Kollege!!!!!!!!!!!!!!!!!!!!!");
			System.exit(0);
		}
		Utils.output("\nFlattop-Boot: ([X/Y])\n");
		nextX = scanner.nextInt();
		nextY = scanner.nextInt();
		Utils.output("vertikal oder horizontal setzen ? (1 oder 2)");
		nextbool = scanner.nextInt();
		if (nextbool == 1) {
			c.setHumanFlattop(nextX, nextY, true, false);
		} else if (nextbool == 2) {
			c.setHumanFlattop(nextX, nextY, false, true);
		} else {
			Utils.output("So nich Kollege!!!!!!!!!!!!!!!!!!!!!");
			System.exit(0);
		}

		Utils.output("Dein Feld sieht aus wie folgt:");
		Utils.output(showField().toString());
		menu();
	}

	public static void setFieldsize(int x) {
		TUI.fieldsize = x;
	}

	public static StringBuilder showBotField() {

		sb.setLength(0);

		sb.append("##### Spielfeld des Bots #####").append("\n");
		for (int i = 0; i < fieldsize; i++) {
			if (i == 0) {
				sb.append(" ");
				for (int k = HEX; k < fieldsize + HEX; k++) {
					sb.append(SEP).append((char) k);
				}
				sb.append("\n");
			}
			if (i <= LINELEN) {
				sb.append(i).append(SEP);
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

	public static StringBuilder cheatShowBotField() {
		sb.setLength(0);

		sb.append("##### Spielfeld des Bots (mit Schiffen) #####").append("\n");
		for (int i = 0; i < fieldsize; i++) {
			if (i == 0) {
				sb.append(" ");
				for (int k = HEX; k < fieldsize + HEX; k++) {
					sb.append(SEP).append((char) k);
				}
				sb.append("\n");
			}
			if (i <= LINELEN) {
				sb.append(i).append(SEP);
			} else {
				sb.append(i).append("| ");
			}
			for (int j = 0; j < fieldsize; j++) {
				if (c.getBot().getPlayboard().getField()[i][j].getStat() == state.empty) {
					sb.append("_ | ");
				} else if (c.getBot().getPlayboard().getField()[i][j].getStat() == state.emptyhit) {
					sb.append("O | ");
				} else if (c.getBot().getPlayboard().getField()[i][j].getStat() == state.hit) {
					sb.append("X | ");
				} else if (c.getBot().getPlayboard().getField()[i][j].getStat() == state.ship) {
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
				for (int k = HEX; k < fieldsize + HEX; k++) {
					sb.append(SEP).append((char) k);
				}
				sb.append("\n");
			}
			if (i <= LINELEN) {
				sb.append(i).append(SEP);
			} else {
				sb.append(i).append("| ");
			}
			for (int j = 0; j < fieldsize; j++) {
				if (c.getPlayer().getPlayboard().getField()[i][j].getStat() == state.empty) {
					sb.append("~ | ");
				} else if (c.getPlayer().getPlayboard().getField()[i][j]
						.getStat() == state.emptyhit) {
					sb.append("O | ");
				} else if (c.getPlayer().getPlayboard().getField()[i][j]
						.getStat() == state.hit) {
					sb.append("X | ");
				} else if (c.getPlayer().getPlayboard().getField()[i][j]
						.getStat() == state.ship) {
					sb.append("S | ");
				}
			}
			sb.append("\n");
		}
		return sb;
	}

	private static void menu() throws InterruptedException {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (c.getPlayer().getNumberShips() > 0
				&& c.getBot().getNumberShips() > 0) {
			Utils.output("Du bist dran!!!!!!!!!!!!!! KNALL IHN AB MAAAAAAAAAAAAAAAAAAN");
			Utils.output("Deine Optionen im Spiel sind:");
			Utils.output("(1) EIGENES FELD ANZEIGEN");
			Utils.output("(2) AUF FELD DES COMPUTERS SCHIEßEN");

			switch (scanner.nextInt()) {
			case 1:
				Utils.output(showField().toString());
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
				Thread.sleep(WAIT);
				c.shootHuman();
				if (c.getPlayer().getNumberShips() == 0) {
					Utils.output("Hasch verkackt wa!!!!");
					break;
				}
				break;
			default:
				Utils.output(cheatShowBotField().toString());
				break;
			}
		}
	}
}
