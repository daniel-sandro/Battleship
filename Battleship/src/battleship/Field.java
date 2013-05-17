package battleship;

public class Field {
	
	public enum state {
		empty,
		ship,
		hit,
		emptyhit
	}
	
	private Ships ship;
	private state stat;
	public String s;

	
	public Field(){
		this.stat = state.empty;
		this.s = "test";
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
			this.stat = state.hit;
			return state.hit;
		default:
			System.out.println("Depp\n");
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
