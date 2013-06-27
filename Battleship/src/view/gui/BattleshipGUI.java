package view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Field.state;

import observer.Event;
import observer.IObserver;
import controller.Controller;

@SuppressWarnings("serial")
public class BattleshipGUI extends JFrame implements IObserver {

	public static JPanel mainPanel;
	// action: 0 = eigenes feld nur anzeigen; 1 = ruderboot setzen; 2 = zerstörer setzen
	// 3 = flugzeugträger setzen; 4 = auf bot schießen
	private int action = 0;
	boolean cont = false;
	private JPanel fieldsPanel;
	BattleshipInfos infoPanel;
	public Controller controller;
	private PlayboardPanel playerPanel;
	private PlayboardPanel botPanel;
	BattleshipSetFieldsize dialogs;
	int i, j;
    Icon rowboatNormal = new ImageIcon(BattleshipGUI.class.getResource("/images/RowboatNormal.jpg"));
    Icon rowboatSelected = new ImageIcon(BattleshipGUI.class.getResource("/images/RowboatSelected.jpg"));
    Icon schiffPre = new ImageIcon(BattleshipGUI.class.getResource("/images/SchiffPre.jpg"));
    Icon hit = new ImageIcon(BattleshipGUI.class.getResource("/images/Hit.jpg"));
    Icon shot = new ImageIcon(BattleshipGUI.class.getResource("/images/Shot2.png"));
    Icon pre = new ImageIcon(BattleshipGUI.class.getResource("/images/SchiffPre.jpg"));
    private Color background;
 	
	public BattleshipGUI(Controller controller) {
		this.controller = controller;
		controller.addObserver(this);
		
		background = new Color(255, 255, 255);
		dialogs = new BattleshipSetFieldsize(this);
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
			default:
				break;
		}
	}
	
	public void onBotShoots() {
		int[] shots = controller.getLastBotShot();
		System.out.printf("shots[0]: %d, shots[1]: %d", shots[0], shots[1]);
		if (controller.getPlayer().getPlayboard().getField()[shots[0]][shots[1]].getStat() == state.hit) {
			//playerPanel.setIcon(shots[1] + 1, shots[0] + 1, hit, hit);
		} else if(controller.getPlayer().getPlayboard().getField()[shots[0]][shots[1]].getStat() == state.ship) { 
			//playerPanel.setIcon(shots[1] + 1, shots[0] + 1, hit, hit);
		} else  {
			//playerPanel.setIcon(shots[1] + 1, shots[0] + 1, shot, shot);
		}
		//paint();
	}
	
	public void onSetRowboat () {
		action = 1;
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
		controller.setInput(false);
	}

	public void onShowPlayersField() {}

	public void onShowBotsField() {}
	
	public void onCheat() {}

	public void onShootOnBot() {}

	public void onStatus() {
		Thread t = new Thread(new Runnable()
		{
		    public void run()
		    {
		        SwingUtilities.invokeLater(new Runnable()
		        {
		            public void run()
		            {
		            	infoPanel.update();
		            }
		        });
		    }
		});
		t.start();
		repaintFields();
	}
	
	// Um auf eventuelle eingaben der Tui zu reagieren
	public void waitForInput(int timeInMS) {
		while (!controller.isInput()) {
			try {
				Thread.sleep(timeInMS);
			} catch (InterruptedException e) {
			}
		}
	}
	
	public void repaintFields() {
		Thread t = new Thread(new Runnable()
		{
		    public void run()
		    {
		        SwingUtilities.invokeLater(new Runnable()
		        {
		            public void run()
		            {
		        		playerPanel.update(true);
		        		botPanel.update(false);
		            }
		        });
		    }
		});
		t.start();
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
		boolean align;
		if (action == 0) {
			return;
		} else if (action == 1) {
			controller.setHumanRowboat(x - 1, y - 1);
			action = 0;
		} else if (action == 2) {
			align = BattleshipGUIUtils.setAlignment();
			if (checkSetShipPosition(1, x - 1, y - 1, align)) {
				controller.setHumanDestructor(x - 1, y - 1, align);
				action = 0;
			}
		} else if (action == 3) {
			align = BattleshipGUIUtils.setAlignment();
			if (checkSetShipPosition(2, x - 1, y - 1, align)) {
				controller.setHumanFlattop(x - 1, y - 1, align);
				action = 0;
			}
		} else if (action == 4) {
			controller.shootBot(x - 1, y - 1);
			action = 0;
		}
		repaintFields();
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
}
