package view.tui;

import java.util.Observable;
import java.util.Scanner;

import model.Field.state;

import observer.Event;
import observer.IObserver;
import controller.Controller;
/*import observer.Event;*/


public final class TUI implements IObserver {

	private static final int MAXFIELDSIZE = 26;
	private static final int HEX = 65;
	private static final int LINELEN = 9;
	private static final String SEP = " | ";
	
	private Controller controller;
	private Scanner scanner = new Scanner(System.in);
	private static StringBuilder sb = new StringBuilder();

	@SuppressWarnings("unused")
	private TUI() {}
	
	public TUI (Controller controller) {
		this.controller = controller;
		controller.addObserver(this);
		print(controller.getStatus());
		print("\n");
	}
	
	public void onNotifyObservers(Event t) {
		switch (t.getEventType()) {
		case setDestructor:
			onSetDestructor();
			break;
		default:
			break;
		}
	}
	
	public void sleep() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void onSetFieldsize() {
		int fieldsize;
		print("Bitte Feldgröße eingeben!");
		while (!controller.isInput() && !scanner.hasNextInt()) {
			sleep();
		}
		if (controller.isInput()) {
			return;
		}
		while (true) {
			fieldsize = scanner.nextInt();
			if (fieldsize < 0 || fieldsize > MAXFIELDSIZE) {
				print("Die Feldgröße muss zwischen 0 und 26 liegen! " +
						"Bitte erneut eingeben!\n");
				continue;
			}
			controller.setFieldsize(fieldsize);
			controller.setInput(true);
		}
	}

	public void onSetRowboat() {
		print("Bitte Ruderboot setzten (X/Y):\n");
		controller.setHumanRowboat(setXPos(), setYPos());
	}
	
	public void onSetDestructor() {
		print("Bitte Zerstörer setzten (X/Y):\n");
		controller.setHumanDestructor(setXPos(), setYPos(), setAlignment());
	}
	
	public void onSetFlattop() {
		print("Bitte Flugzeugträger setzten (X/Y):\n");		
		controller.setHumanFlattop(setXPos(), setYPos(), setAlignment());
	}
	
	public int setXPos() {
		int x;
		while (true) {
			x = scanner.nextInt();
			if (x < 0 || x > controller.getFieldsize()) {
				print("Die X-Koordinate muss zwischen 0 und ");
				print(String.valueOf(controller.getFieldsize()));
				print("liegen!\nBitte erneut eingeben!");
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
	
	public void onStatus() {
		print(controller.getStatus());
		print("\n");
	}
	
	public void onShowMenu() {
		print("\n");
		print("Du bist am Zug!\n");
		print("Deine Optionen im Spiel sind:\n");
		print("(1) EIGENES FELD ANZEIGEN\n");
		print("(2) AUF DAS SPIELFELD DES BOTS SCHIESSEN\n");
		print("(3) SPIEL BEENDEN\n");
		print("\n");
	}
	
	public void onAction() {
		controller.setInput(scanner.nextInt());
	}
	
	public void onShowPlayersField() {
		print(showField(false, false).toString());
		print(showField(true, false).toString());
		sb.setLength(0);	
	}
	
	public void onShowBotsField(boolean withShip) {
		print(showField(true, withShip).toString());
		sb.setLength(0);
	}
	
	public void onShootOnBot() {
		print("Nenne die Position auf die geschossen werden soll: ([X/Y])\n");
		
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		if(controller.shootBot(y, x) == true)
		{
			print("\n");
			print("TREFFER!!\n");
		}
		else 
		{
			print("Leider nichts getroffen!\n");
		}
	}
	
	public StringBuilder showField(boolean bot, boolean ship) {
		printHeader(bot, ship);
		for (int i = 0; i < controller.getFieldsize(); i++) {
			printPattern(i);
			for (int j = 0; j < controller.getFieldsize(); j++) {
				if (bot) {
					checkStateBot(i, j, ship);
				} else {
					checkStateHuman(i, j);
				}
			}
			sb.append("\n");
		}
		return sb;
	}
	
	private void printHeader(boolean bot, boolean ship) {
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
	
	private void printPattern(int i) {
		if (i == 0) {
			sb.append(" ");
			for (int k = HEX; k < controller.getFieldsize() + HEX; k++) {
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
	
	private void checkStateBot(int i, int j, boolean ship) {
		if (controller.getBot().getPlayboard().getField()[i][j].getStat() == state.empty) {
			sb.append("_ | ");
		} else if (controller.getBot().getPlayboard().getField()[i][j]
				.getStat() == state.ship && !ship) {
			sb.append("_ | ");
		} else if (controller.getBot().getPlayboard().getField()[i][j]
				.getStat() == state.emptyhit) {
			sb.append("O | ");
		} else if (controller.getBot().getPlayboard().getField()[i][j]
				.getStat() == state.hit) {
			sb.append("X | ");
		} else if (controller.getBot().getPlayboard().getField()[i][j]
				.getStat() == state.ship && ship) {
			sb.append("S | ");
		}
	}
	
	private void checkStateHuman(int i, int j) {
		if (controller.getPlayer().getPlayboard().getField()[i][j].getStat() == state.empty) {
			sb.append("~ | ");
		} else if (controller.getPlayer().getPlayboard().getField()[i][j]
				.getStat() == state.emptyhit) {
			sb.append("O | ");
		} else if (controller.getPlayer().getPlayboard().getField()[i][j]
				.getStat() == state.hit) {
			sb.append("X | ");
		} else if (controller.getPlayer().getPlayboard().getField()[i][j]
				.getStat() == state.ship) {
			sb.append("S | ");
		}
	}
	
	private static void print(String string) {
		System.out.printf(string);
	}
}
