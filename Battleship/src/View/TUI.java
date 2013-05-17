package View;

import Controller.Controller;
import Model.Field.state;

import java.util.Scanner;

public class TUI {
	
	private static Controller c = new Controller();
	private static int fieldsize;
	
	public void showField(){
		for(int i =0; i< fieldsize; i++){
			if(i == 0){
				System.out.print(" ");
				for(int k = 65; k < fieldsize+65; k++){
					System.out.printf(" | %s", (char) k);
				}
				System.out.println();
			}
			System.out.printf("&d | ", i);
			for(int j =0; j< fieldsize; j++){
				if(c.player.getPlayboard().getBoard()[i][j].getStat() == state.empty){
					System.out.print("_ | ");
				}
				else if(c.player.getPlayboard().getBoard()[i][j].getStat() == state.emptyhit){
					System.out.print("O | ");
				}
				else if(c.player.getPlayboard().getBoard()[i][j].getStat() == state.hit){
					System.out.print("X | ");
				}
				else if(c.player.getPlayboard().getBoard()[i][j].getStat() == state.ship){
					System.out.print("S | ");
				}
				
			}
			System.out.println("");
		}
	}
	
	
	public static void main(final String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Willkommen zu Battleship!!!! \n Initialisieren Sie zunächst die Feldgröße: ");
		fieldsize = scanner.nextInt();		
		
		
	}
	
	

}
