package de.htwg.battleship.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.google.inject.Inject;

import de.htwg.battleship.controller.IController;
import de.htwg.battleship.observer.Event;
import de.htwg.battleship.observer.IObserver;

/**
 * @author Sandro, Julian
 * The Gui-Frame
 */
@SuppressWarnings("serial")
public class BattleshipGUI extends JFrame implements IObserver {

	private static JPanel mainPanel;
	private int action = 0;
	private JPanel fieldsPanel;
	private BattleshipInfos infoPanel;
	private IController controller;
	private PlayboardPanel playerPanel;
	private PlayboardPanel botPanel;
    private Color background;
    private StringBuilder sb = new StringBuilder();
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
	public BattleshipGUI(IController controller) {
		this.controller = controller;
		controller.addObserver(this);
		
		background = new Color(COL, COL, COL);
	}
    
    public IController getController() {
		return controller;
	}

	public void setController(IController controller) {
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
	 * @see de.htwg.battleship.observer.IObserver#onSetFieldsize()
	 * calls the printMainFrame function
	 */
	public void onSetFieldsize() {}
	
	/**
	 * gtter for the fieldsize
	 * @return
	 */
	public int getFieldsize() {
		return controller.getFieldsize();
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onNotifyObservers(de.htwg.battleship.observer.Event)
	 * reacts on the given Event
	 */
	public void onNotifyObservers(Event t) {
		switch (t.getEventType()) {
			case CORRECT_POSITION:
				onCorrectPosition();
				break;
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
			default:
				break;
		}
	}
	
	/**
	 * corrects the position of a ship
	 */
	public void onCorrectPosition() {
		BattleshipGUIUtils.correctShipPosition(controller.getCorrectPos(), controller.isCorrectAl());
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onBotShoots()
	 * the reaction on the event to shoot at bot
	 * shoots at bot
	 */
	public void onBotShoots() {}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onSetRowboat()
	 * sets action to 1 (set rowboat)
	 */
	public void onSetRowboat () {
		action = 1;
		printMainFrame();
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onSetDestructor()
	 * sets action to 2 (se destructor)
	 */
	public void onSetDestructor() {
		action = 2;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onSetFlattop()
	 * sets action to 3 (set flattop)
	 */
	public void onSetFlattop() {
		action = THREE;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onShowMenu()
	 * empty function, is only implemented by TUI
	 */
	public void onShowMenu() {}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#ON_ACTION()
	 * sets action to 4
	 */
	public void onAction() {
		final int x = 4;
		action = x;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onShowPlayersField()
	 * empty function, is only implemented by TUI
	 */
	public void onShowPlayersField() {}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onShowBotsField()
	 * empty function, is only implemented by TUI
	 */
	public void onShowBotsField() {}
	
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onCheat()
	 * empty function, is only implemented by TUI
	 */
	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onCheat()
	 * empty function, is only implemented by TUI
	 */
	public void onCheat() {}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#onShootOnBot()
	 * empty function, is only implemented by TUI
	 */
	public void onShootOnBot() {}

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
		int align;
		sb.append(x).append(" ").append(y);
		if (action == 1 || action == FOUR) {
			controller.input(sb.toString());
			sb.setLength(0);
			return;
		} else if (action == 2 || action == THREE) {
			align = BattleshipGUIUtils.setAlignment();
			sb.append(" ").append(align);
		} else if (action == 0) {
			return;
		}
		controller.input(sb.toString());
		sb.setLength(0);
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
	
	/**
	 * checks if a chosen position of a ship is valid
	 * @param ship
	 * @param x
	 * @param y
	 * @param align
	 * @return
	 */
	public boolean checkSetShipPosition(int ship, int x, int y, boolean align) {
		int t = controller.checkSetShipPosition(ship, x, y, align);
		if (t != 0) {
			BattleshipGUIUtils.correctShipPosition(t, align);
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see de.htwg.battleship.observer.IObserver#ON_REPAINT()
	 * reaction on the event repaint
	 */
	public void onRepaint() {
		repaintFields();		
	}
}
