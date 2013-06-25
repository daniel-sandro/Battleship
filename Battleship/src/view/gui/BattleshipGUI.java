package view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Field.state;
import observer.IObserver;
import controller.Controller;

public class BattleshipGUI extends JFrame implements IObserver {

	public static JPanel mainPanel;
	// action: 0 = eigenes feld nur anzeigen; 1 = ruderboot setzen; 2 = zerstörer setzen
	// 3 = flugzeugträger setzen
	private int action = 0;
	boolean cont = false;
	private JPanel fieldsPanel;
	BattleshipInfos infoPanel;
	private int fieldsize;
	private static final int HEX = 65;
	public Controller controller;
	private PlayboardPanel playerPanel;
	private PlayboardPanel botPanel;
	BattleshipDialogs dialogs;
	int i, j;
    Icon rowboatNormal = new ImageIcon(BattleshipGUI.class.getResource("/images/RowboatNormal.jpg"));
    Icon rowboatSelected = new ImageIcon(BattleshipGUI.class.getResource("/images/RowboatSelected.jpg"));
    Icon schiffPre = new ImageIcon(BattleshipGUI.class.getResource("/images/SchiffPre.jpg"));
    private Color background;
    private JLabelE[][] fields;
 	
	public BattleshipGUI(Controller controller) {
		this.controller = controller;
		controller.addObserver(this);
		
		background = new Color(255, 255, 255);
		dialogs = new BattleshipDialogs(this);
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
	
	private Dimension calcSize() {
		return new Dimension(fieldsize * 25 + 25, fieldsize * 25 + 25);
	}
	
	public static JPanel getMainPanel() {
		return mainPanel;
	}

	public boolean onSetFieldsize() {
		dialogs.setFieldsize();
		fieldsize = controller.getFieldsize();
		printMainFrame();
		return true;
	}
	
	public int getFieldsize() {
		return controller.getFieldsize();
	}
	
	public boolean onSetRowboat () {
		action = 1;
		dialogs.setShip(0);
		infoPanel.repaint();
		mainPanel.repaint();
		return true;
	}

	public boolean onSetDestructor() {
		action = 2;
		dialogs.setShip(1);
		infoPanel.repaint();
		mainPanel.repaint();
		return true;
	}

	public boolean onSetFlattop() {
		dialogs.setShip(2);
		return true;
	}

	public void onShowMenu() {
		// TODO Auto-generated method stub
		
	}

	public void onAction() {
		// TODO Auto-generated method stub
		
	}

	public void onShowPlayersField() {
		// TODO Auto-generated method stub
		
	}

	public void onShowBotsField(boolean withShip) {
		// TODO Auto-generated method stub
		
	}

	public void onShootOnBot() {
		// TODO Auto-generated method stub
		
	}

	public void onStatus() {
		// TODO Auto-generated method stub
		
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
	
	public void mouseClick(int[] positions) {
		boolean align;
		int x = positions[0];
		int y = positions[1];
		if (action == 1) {
			controller.setHumanRowboat(x - 1, y - 1);
			playerPanel.setIcon(x, y, rowboatNormal, rowboatSelected);
		} else if (action == 2) {
			align = dialogs.setAlignment();
			controller.setHumanDestructor(x, y, align);
			checkSetShipPosition(1, x, y, align);
			playerPanel.setIcon(x, y, schiffPre, schiffPre);
			if (align) {//horizontal
				playerPanel.setIcon(x + 1, y, schiffPre, schiffPre);
				playerPanel.setIcon(x + 2, y, schiffPre, schiffPre);
			} else {
				playerPanel.setIcon(x, y + 1, schiffPre, schiffPre);
				playerPanel.setIcon(x, y + 2, schiffPre, schiffPre);
			}
		} else if (action == 3) {
			align = dialogs.setAlignment();
			controller.checkSetShipPosition(2, x, y, dialogs.setAlignment());
		}
	}
	
	public void checkSetShipPosition(int ship, int x, int y, boolean align) {
		int t = controller.checkSetShipPosition(ship, x, y, align);
		if (t != 0) {
			;
		}
	}
	
	/*
	private void checkStateHuman(int i, int j) {
		state stat = controller.getState(controller.getPlayer().getPlayboard().getField()[i][j]);
		if (stat == state.empty) {
			fields[i][j] = new JLabelE(icon, icon2);
		} else if (stat == state.rowboat) {
			fields[i][j] = new JLabelE(rowboatNormal, rowboatSelected);
		}
	}*/
}
