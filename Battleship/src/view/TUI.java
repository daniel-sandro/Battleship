package view;

import java.util.Observable;
import java.util.Scanner;

/*import observer.Event;
import observer.IObserver;
import observer.IObservable;
import observer.Observable;*/
import java.util.*;

import observer.IObserver;
import controller.*;


public final class TUI implements IObserver {

	private static final int MAXFIELDSIZE = 26;
	private int x, y;
	private Controller controller;
	private Scanner scanner = new Scanner(System.in);
	private static StringBuilder sb = new StringBuilder();

	private TUI() {
	}
	
	public TUI (Controller controller) {
		this.controller = controller;
		controller.addObserver(this);
		print(controller.getStatus());
		print("\n");
	}
	
	/*
	public void update(Event e) {
		System.out.println("bla");
		printTUI();		
	}*/
	
	public void update(Observable arg0, Object arg1) {
		//checkObject(arg1);	
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
	
	public boolean onSetFieldsize() {
		int fieldsize;
		System.out.println("Bitte Feldgröße eingeben!");
		while (true) {
			fieldsize = scanner.nextInt();
			if (fieldsize < 0 || fieldsize > MAXFIELDSIZE) {
				print("Die Feldgröße muss zwischen 0 und 26 liegen! " +
						"Bitte erneut eingeben!\n");
				continue;
			}
			return true;
		}
	}

	public boolean onSetRowboat() {
		System.out.println("Bitte Ruderboot setzten (X/Y):");
		x = scanner.nextInt();
		y = scanner.nextInt();
		controller.setHumanRowboat(y, x, setAlignment());
		return true;
	}
	
	public boolean onSetDestructor() {
		System.out.println("Bitte Zerstörer setzten (X/Y):");
		controller.setHumanDestructor(setYPos(), setXPos(), setAlignment());
		return true;
	}
	
	public boolean onSetFlattop() {
		System.out.println("Bitte Flugzeugträger setzten (X/Y):");		
		controller.setHumanFlattop(setYPos(), setXPos(), setAlignment());
		return true;
	}
	
	public int setXPos() {
		int x;
		while (true) {
			x = scanner.nextInt();
			if (x < 0 || x > controller.getFieldsize()) {
				System.out.printf("Die X-Koordinate muss zwischen 0 und " +
						"%d liegen!\nBitte erneut eingeben!", controller.getFieldsize());
				continue;
			}
			break;
		}
		return x;
	}
	
	public int setYPos() {
		int y;
		while (true) {
			y = scanner.nextInt();
			if (y < 0 || y > controller.getFieldsize()) {
				print("Die Y-Koordinate muss zwischen 0 und ");
				sb.append("Die Y-Koordinate muss zwischen 0 und ").append(controller.getFieldsize())
					.append( "liegen!\nBitte erneut eingeben!\n");
				print(sb.toString());
				sb.setLength(0);
				continue;
			}
			break;
		}
		return y;
	}
	
	/*
	 * Returns true if ship has be set horizontal, false if vertical
	 */
	public boolean setAlignment() {
		int nextBool;
		while (true) {
			print("Horizontal (1) oder vertikal (2) setzen?\n");
			nextBool = scanner.nextInt();
			if (nextBool == 1) {
				return true;
			} else if (nextBool == 2) {
				return false;
			} else {
				print("Falsche Eingabe!\n");
				continue;
			}
		}
	}
	
	private static void print(String string) {
		System.out.printf(string);
	}
}
