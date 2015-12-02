package de.htwg.battleship.view.gui;

import com.google.inject.Inject;
import de.htwg.battleship.controller.HumanController;
import de.htwg.battleship.controller.JavaBattleshipController;
import de.htwg.battleship.model.Position;
import de.htwg.battleship.model.Ship;
import de.htwg.battleship.model.ship.Destructor;
import de.htwg.battleship.model.ship.Flattop;
import de.htwg.battleship.model.ship.Rowboat;
import de.htwg.battleship.observer.Event;
import de.htwg.battleship.observer.IObserver;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

/**
 * @author Sandro, Julian
 * The Gui-Frame
 */
@SuppressWarnings("serial")
public class BattleshipGUI extends JFrame implements IObserver {
	private enum HumanAction {
		PLACE_ROWBOAT,
		PLACE_DESTRUCTOR,
		PLACE_FLATTOP,
		SHOOT
	}

	private static JPanel mainPanel;
	private HumanAction action = HumanAction.PLACE_ROWBOAT;
	private JPanel fieldsPanel;
	private BattleshipInfos infoPanel;
	private JavaBattleshipController controller;
	private HumanController humanController;
	private PlayboardPanel playerPanel;
	private PlayboardPanel botPanel;
    private Color background;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int COL = 255;
	private static final int XA = 285;
	private static final int XB = 118;
    
    
    /**
     * @param controller
     * sets up the constructor of a BattleshipGUI and
     * initializes its controller varibale woth the argument
     */
	@Inject
	public BattleshipGUI(JavaBattleshipController controller) {
		this.controller = controller;
		controller.addObserver(this);

		this.humanController = controller.getHuman().getController();
		humanController.addObserver(this);

		background = new Color(COL, COL, COL);
	}
    
    public JavaBattleshipController getController() {
		return controller;
	}

	public void setController(JavaBattleshipController controller) {
		this.controller = controller;
	}
	
	/**
	 * Declares the Main-Frame
	 */
	public void printMainFrame() {
		infoPanel = new BattleshipInfos(controller);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
	    showPlayboards();
	    mainPanel.add(infoPanel, BorderLayout.NORTH);
	    mainPanel.add(fieldsPanel, BorderLayout.SOUTH);
	    mainPanel.setBackground(background);

	    Dimension d = new Dimension(XA, XB);
        mainPanel.setMaximumSize(d);
        mainPanel.setMinimumSize(d);
	    this.setContentPane(mainPanel);
        this.setMaximumSize(d);
        this.setBackground(background);
        this.setMinimumSize(d);
        this.setTitle("Battleship");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
	}
	
	/**
	 * 	getter for the Main-Panel
	 * @return
	 */
	public static JPanel getMainPanel() {
		return mainPanel;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onNotifyObservers(de.htwg.battleship.observer.Event)
	 * reacts on the given Event
	 */
	public void onNotifyObservers(Event e) {
		switch (e) {
			case SET_ROWBOAT:
				onSetRowboat();
				break;
			case SET_DESTRUCTOR:
				onSetDestructor();
				break;
			case SET_FLATTOP:
				onSetFlattop();
				break;
			case ON_ACTION:
				onAction();
				break;
			case ON_STATUS:
				onStatus();
				break;
			case GAME_OVER:
				onGameOver();
				break;
			case WON:
				onWon();
				break;
			case ON_REPAINT:
				onRepaint();
				break;
		}
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onSetRowboat()
	 * sets action to 1 (set rowboat)
	 */
	public void onSetRowboat() {
		action = HumanAction.PLACE_ROWBOAT;
		printMainFrame();
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onSetDestructor()
	 * sets action to 2 (se destructor)
	 */
	public void onSetDestructor() {
		action = HumanAction.PLACE_DESTRUCTOR;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onSetFlattop()
	 * sets action to 3 (set flattop)
	 */
	public void onSetFlattop() {
		action = HumanAction.PLACE_FLATTOP;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#ON_ACTION()
	 * sets action to 4
	 */
	public void onAction() {
		action = HumanAction.SHOOT;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#ON_STATUS()
	 * prevents deadlocks
	 */
	public void onStatus() {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	if (infoPanel != null) {
			    	infoPanel.update();
		    	}
		    }
		});
	}
	
	/**
	 * repaints the field after a change has been done
	 */
	public void repaintFields() {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	playerPanel.update(true);
        		botPanel.update(false);
		    }
		});
	}
	
	/**
	 * shows the players playboards
	 */
	public void showPlayboards() {
		fieldsPanel = new JPanel();
		fieldsPanel.setLayout(new BorderLayout());
		fieldsPanel.setBackground(background);
		JPanel left = new JPanel();
		playerPanel = new PlayboardPanel(this);
		left.add(playerPanel.getPanel());
		left.setBackground(background);
		JPanel right = new JPanel();
		botPanel = new PlayboardPanel(this);
		right.add(botPanel.getPanel());
		right.setBackground(background);
		fieldsPanel.add(left, BorderLayout.WEST);
		fieldsPanel.add(right, BorderLayout.EAST);
	}
	
	/**
	 * @param x
	 * @param y
	 */
	public void mouseClick(int x, int y) {
		Position p = new Position(y, x);
		switch (action) {
			case PLACE_ROWBOAT:
				// TODO: control when the ship wasn't placed correctly (overlaps another ship, etc)
				Ship ship = new Rowboat();
				boolean horizontal = BattleshipGUIUtils.setAlignment();
				humanController.placeShip(ship, p, horizontal);
				break;
			case PLACE_DESTRUCTOR:
				ship = new Destructor();
				horizontal = BattleshipGUIUtils.setAlignment();
				humanController.placeShip(ship, p, horizontal);
				break;
			case PLACE_FLATTOP:
				ship = new Flattop();
				horizontal = BattleshipGUIUtils.setAlignment();
				humanController.placeShip(ship, p, horizontal);
				break;
			case SHOOT:	// Shoot
				humanController.shoot(p);
				break;
		}
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onGameOver()
	 * reaction on the status gameover
	 */
	public void onGameOver() {
		BattleshipGUIUtils.gameOver();
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onWon()
	 * reaction to event WON
	 */
	public void onWon() {
		BattleshipGUIUtils.won();
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#ON_REPAINT()
	 * reaction on the event repaint
	 */
	public void onRepaint() {
		repaintFields();		
	}
}
