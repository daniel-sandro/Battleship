package View;

import Controller.Controller;
import Model.Field.state;

import java.util.Scanner;

public class TUI {
	
	private static Controller c = new Controller();
	private static int fieldsize;
	
	public void showField(){
		for(int i =0; i< fieldsize; i++){
			System.out.printf("&d |", i);
			for(int j =0; j< fieldsize; j++){
				if(c.player.getPlayboard().getBoard()[i][j].getStat() == state.empty){
					System.out.print("_|");
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
