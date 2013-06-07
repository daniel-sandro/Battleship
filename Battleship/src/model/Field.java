package model;

public class Field {
	
	public enum state {
		empty,
		ship,
		hit,
		emptyhit
	}
	
	private Ships ship;
	private state stat;
	
	public Field(){
		this.stat = state.empty;
	}
	
	public state getStat() {
		return stat;
	}
	
	public state shoot(){
		switch(this.stat){
		case empty:
			this.stat = state.emptyhit;
			return state.emptyhit;
		case ship:
			this.ship.setSize(ship.getSize()-1);
			this.stat = state.hit;
			return state.hit;
			
		default:
			return null;
		}
	}

	public Ships getShip() {
		return ship;
	}

	public void setShip(Ships ship) {
		this.ship = ship;
		this.stat = state.ship;
	}
}
