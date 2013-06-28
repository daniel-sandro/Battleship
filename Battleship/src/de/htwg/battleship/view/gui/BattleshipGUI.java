package de.htwg.battleship.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.google.inject.Inject;

import de.htwg.battleship.controller.Controller;
import de.htwg.battleship.controller.IController;
import de.htwg.battleship.model.Field.state;
import de.htwg.battleship.observer.Event;
import de.htwg.battleship.observer.IObserver;

@SuppressWarnings("serial")
public class BattleshipGUI extends JFrame implements IObserver {

	public static JPanel mainPanel;
	// action: 0 = eigenes feld nur anzeigen; 1 = ruderboot setzen; 2 = zerstörer setzen
	// 3 = flugzeugträger setzen; 4 = auf bot schießen
	private int action = 0;
	boolean cont = false;
	private JPanel fieldsPanel;
	BattleshipInfos infoPanel;
	public IController controller;
	private PlayboardPanel playerPanel;
	private PlayboardPanel botPanel;
	int i, j;
    private Color background;
    private StringBuilder sb = new StringBuilder();
 	
    @Inject
	public BattleshipGUI(IController controller) {
		this.controller = controller;
		controller.addObserver(this);
		
		background = new Color(255, 255, 255);
	}
	
	public void printMainFrame() {
		infoPanel = new BattleshipInfos(controller);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
	    showPlayboards();
	    mainPanel.add(infoPanel, BorderLayout.NORTH);
	    mainPanel.add(fieldsPanel, BorderLayout.SOUTH);
	    mainPanel.setBackground(background);

	    Dimension d = new Dimension(285, 118);
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
	
	public static JPanel getMainPanel() {
		return mainPanel;
	}

	public void onSetFieldsize() {
		printMainFrame();
	}
	
	public int getFieldsize() {
		return controller.getFieldsize();
	}
	
	public void onNotifyObservers(Event t) {
		switch (t.getEventType()) {
			case setFieldsize:
				onSetFieldsize();
				break;
			case correctPosition:
				onCorrectPosition();
				break;
			case setRowboat:
				onSetRowboat();
				break;
			case setDestructor:
				onSetDestructor();
				break;
			case setFlattop:
				onSetFlattop();
				break;
			case onAction:
				onAction();
				break;
			case onStatus:
				onStatus();
				break;
			case showBotsField:
				break;
			case shootBot:
				onShootOnBot();
				break;
			case gameOver:
				onGameOver();
				break;
			case won:
				onWon();
				break;
			case botShoots:
				onBotShoots();
				break;
			case onRepaint:
				onRepaint();
				break;
			default:
				break;
		}
	}
	
	public void onCorrectPosition() {
		BattleshipGUIUtils.correctShipPosition(controller.getCorrectPos(), controller.isCorrectAl());
	}
	
	public void onBotShoots() {
		int[] shots = controller.getLastBotShot();
		System.out.printf("shots[0]: %d, shots[1]: %d", shots[0], shots[1]);
		if (controller.getPlayer().getPlayboard().getField()[shots[0]][shots[1]].getStat() == state.hit) {
		} else if(controller.getPlayer().getPlayboard().getField()[shots[0]][shots[1]].getStat() == state.ship) { 
		} else  {
		}
	}
	
	public void onSetRowboat () {
		action = 1;
		printMainFrame();
	}

	public void onSetDestructor() {
		action = 2;
	}

	public void onSetFlattop() {
		action = 3;
	}

	public void onShowMenu() {}

	public void onAction() {
		// hier auf Input des Players warten
		action = 4;
	}

	public void onShowPlayersField() {}

	public void onShowBotsField() {}
	
	public void onCheat() {}

	public void onShootOnBot() {}

	public void onStatus() {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	if (infoPanel != null) {
			    	infoPanel.update();
		    	}
		    }
		});
	}
	
	public void repaintFields() {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	playerPanel.update(true);
        		botPanel.update(false);
		    }
		});
	}
	
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
	
	public void mouseClick(int x, int y) {
		int align;
		sb.append(x).append(" ").append(y);
		if (action == 1 || action == 4) {
		} else if (action == 2) {
			align = BattleshipGUIUtils.setAlignment();
			sb.append(" ").append(align);
		} else if (action == 3) {
			align = BattleshipGUIUtils.setAlignment();
			sb.append(" ").append(align);
		} else if (action == 0) {
			return;
		}
		controller.input(sb.toString());
		sb.setLength(0);
	}
	
	public void onGameOver() {
		BattleshipGUIUtils.gameOver();
	}
	
	public void onWon() {
		BattleshipGUIUtils.won();
	}
	
	public boolean checkSetShipPosition(int ship, int x, int y, boolean align) {
		int t = controller.checkSetShipPosition(ship, x, y, align);
		if (t != 0) {
			BattleshipGUIUtils.correctShipPosition(t, align);
			return false;
		}
		return true;
	}

	public void onRepaint() {
		repaintFields();		
	}
}
