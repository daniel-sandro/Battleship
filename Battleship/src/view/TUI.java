package view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.*;

import model.Field.state;


public final class TUI implements Observer {

	private Controller controller;

	private TUI() {
	}
	
	public TUI (Controller controller) {
		this.controller = controller;
		controller.addObserver(this);
	}
	
	public void update(Observable o, Object arg) {
		System.out.println("bla");
		printTUI();
	}
	
	public void printTUI() {
		print(controller.getStatus());
	}
	
	/*
	public static void main(final String[] args) throws InterruptedException {
		
		do {
			fieldsize = scanner.nextInt();
			if (fieldsize > MAXFIELDSIZE || fieldsize < 1) {
				print("Die Feldgröße muss zwischen 1 & 26 liegen!!!!");
				continue;
			}
			break;
		} while (true);

		c = new Controller(fieldsize);
		howManyShipsBot();
		
		
		print("Setze deine Schiffe!!");
		howManyShipsHuman();
		print("Alles klar! LOS GEHT'S!! Und viel Glück ;)");
		print(showField(false, false).toString());
		menu();
	}

	public static void setFieldsize(int x) {
		TUI.fieldsize = x;
	}

	

	public static StringBuilder showField(boolean bot, boolean ship) {
		sb.setLength(0);
		header(bot, ship);
		for (int i = 0; i < fieldsize; i++) {
			pattern(i, sb);
			for (int j = 0; j < fieldsize; j++) {
				if (bot) {
					checkStateBot(i, j, sb, ship);
				} else {
					checkStateHuman(i, j, sb);
				}
			}
			sb.append("\n");
		}
		return sb;
	}
	
	private static void checkStateBot(int i, int j, StringBuilder sb, boolean ship) {
		if (c.getBot().getPlayboard().getField()[i][j].getStat() == state.empty) {
			sb.append("_ | ");
		} else if (c.getBot().getPlayboard().getField()[i][j]
				.getStat() == state.ship && !ship) {
			sb.append("_ | ");
		} else if (c.getBot().getPlayboard().getField()[i][j]
				.getStat() == state.emptyhit) {
			sb.append("O | ");
		} else if (c.getBot().getPlayboard().getField()[i][j]
				.getStat() == state.hit) {
			sb.append("X | ");
		} else if (c.getBot().getPlayboard().getField()[i][j]
				.getStat() == state.ship && ship) {
			sb.append("S | ");
		}
	}
	
	private static void checkStateHuman(int i, int j, StringBuilder sb) {
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

	private static void pattern(int i, StringBuilder sb) {
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
	}

	private static void header(boolean bot, boolean ship) {
		if (bot) {
			sb.append("##### Spielfeld des Bots ");
			if (ship) {
				sb.append("(mit Schiffen) ");
			}
			sb.append("#####").append("\n");
		} else {
			sb.append("##### DEIN SPIELFELD #####\n");
		}
	}

	private static void menu() throws InterruptedException {
		while (c.getPlayer().getNumberShips() > 0
				&& c.getBot().getNumberShips() > 0) {
			print("Du bist dran!!!!!!!!!!!!!! KNALL IHN AB MAAAAAAAAAAAAAAAAAAN");
			print("Deine Optionen im Spiel sind:");
			print("(1) EIGENES FELD ANZEIGEN");
			print("(2) AUF DAS SPIELFELD DES BOTS SCHIESSEN");
			print("(3) SPIEL BEENDEN");

			switch (scanner.nextInt()) {
			case 1:
				print(showField(false, false).toString());
				break;
			case 2:
				print("Nenne die Position auf die geschossen werden soll: ([X/Y])");
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				if(c.shootBot(y, x) == true)
				{
					print("TREFFER!!!!!!!!!");
				}
				else 
				{
					print("Nichts getroffen");
				}
				print(showField(true, false).toString());
				if (c.getBot().getNumberShips() == 0) {
					print("Glückwunsch!! Du hast gewonnen!!!!");
					break;
				}
				print("Bot ist am Zug!");
				Thread.sleep(WAIT);
				print("Der Bot hat geschossen!");
				c.shootHuman();
				if (c.getPlayer().getNumberShips() == 0) {
					print("Hasch verkackt wa!!!!");
					break;
				}
				break;
			case THREE:
				print("Vielen Dank für's Spielen! Bis bald!");
				System.exit(0);
			default:
				print(showField(true, true).toString());
				break;
			}
		}
	}

	
	
	
	*/
	
	private static void print(String string) {
		System.out.println(string);
	}
}
