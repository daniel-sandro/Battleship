package Controller;

import Model.Bot;
import Model.Destructor;
import Model.Flattop;
import Model.Human;
import Model.Rowboat;

public class Controller {
	
	public Human player;
	public Bot bot;	
	
	public Controller(){
		player = new Human();		
		bot = new Bot();
	}
	
	
	//Methods for shooting each other via the controller
	public void shootBot(int row, int col){
		player.shoot(row, col, bot.getPlayboard());
		if(player.getPlayboard().getBoard()[row][col].getShip().getSize() <= 0){
			player.setNumberShips(player.getNumberShips()-1);
		}
	}
	
	public void shootHuman(){
		bot.shoot(player.getPlayboard());
	}
	
	
	
	//Methods for setting the ships via the controller
	public void setHumanRowboat(int row, int col){
		player.getPlayboard().setShip(new Rowboat(row, col));
	}
	
	public void setHumanFlattop(int row, int col){
		player.getPlayboard().setShip(new Flattop(row, col));
	}
	
	public void setHumanDestructor(int row, int col){
		player.getPlayboard().setShip(new Destructor(row, col));
	}
	
	//Ships for bot
	public void setBotRowboat(int row, int col){
		bot.getPlayboard().setShip(new Rowboat(row, col));
	}
	
	public void setBotFlattop(int row, int col){
		bot.getPlayboard().setShip(new Flattop(row, col));
	}
	
	public void setBotDestructor(int row, int col){
		bot.getPlayboard().setShip(new Destructor(row, col));
	}
	
	
	//Methods for initialising the Playboards via the Cobntroller	
	public void initPlayboard(int size){
		bot.initPlayboard(size);
		player.initPlayboard(size);
	}
	
	
	
	

}
