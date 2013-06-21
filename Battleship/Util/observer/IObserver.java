package observer;

public interface IObserver {

	public boolean onSetFieldsize();
	
	public boolean onSetRowboat();
	
	public boolean onSetDestructor();
	
	public boolean onSetFlattop();
	
	public void onShowMenu();
	
	public void onAction();
	
	public void onShowPlayersField();
	
	public void onShowBotsField(boolean withShip);
	
	public void onShootOnBot();
	
	public void onStatus();
}