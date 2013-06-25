package view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import observer.Event;
import observer.IObserver;
import controller.Controller;

@SuppressWarnings("serial")
public class BattleshipGUI extends JFrame implements IObserver {

	public static JPanel mainPanel;
	// action: 0 = eigenes feld nur anzeigen; 1 = ruderboot setzen; 2 = zerstörer setzen
	// 3 = flugzeugträger setzen
	private int action = 0;
	boolean cont = false;
	private JPanel fieldsPanel;
	BattleshipInfos infoPanel;
	public Controller controller;
	private PlayboardPanel playerPanel;
	private PlayboardPanel botPanel;
	BattleshipDialogs dialogs;
	int i, j;
    Icon rowboatNormal = new ImageIcon(BattleshipGUI.class.getResource("/images/RowboatNormal.jpg"));
    Icon rowboatSelected = new ImageIcon(BattleshipGUI.class.getResource("/images/RowboatSelected.jpg"));
    Icon schiffPre = new ImageIcon(BattleshipGUI.class.getResource("/images/SchiffPre.jpg"));
    Icon des1NH = new ImageIcon(BattleshipGUI.class.getResource("/images/des/Des1NH.jpg"));
    Icon des2NH = new ImageIcon(BattleshipGUI.class.getResource("/images/des/Des2NH.jpg"));
    Icon des3NH = new ImageIcon(BattleshipGUI.class.getResource("/images/des/Des3NH.jpg"));
    Icon des1SH = new ImageIcon(BattleshipGUI.class.getResource("/images/des/Des1SH.jpg"));
    Icon des2SH = new ImageIcon(BattleshipGUI.class.getResource("/images/des/Des2SH.jpg"));
    Icon des3SH = new ImageIcon(BattleshipGUI.class.getResource("/images/des/Des3SH.jpg"));
    Icon des1NV = new ImageIcon(BattleshipGUI.class.getResource("/images/des/Des1NV.jpg"));
    Icon des2NV = new ImageIcon(BattleshipGUI.class.getResource("/images/des/Des2NV.jpg"));
    Icon des3NV = new ImageIcon(BattleshipGUI.class.getResource("/images/des/Des3NV.jpg"));
    Icon des1SV = new ImageIcon(BattleshipGUI.class.getResource("/images/des/Des1SV.jpg"));
    Icon des2SV = new ImageIcon(BattleshipGUI.class.getResource("/images/des/Des2SV.jpg"));
    Icon des3SV = new ImageIcon(BattleshipGUI.class.getResource("/images/des/Des3SV.jpg"));
    Icon flu1NH = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/Flu1NH.jpg"));
    Icon flu2NH = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/Flu2NH.jpg"));
    Icon flu3NH = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/Flu3NH.jpg"));
    Icon flu4NH = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/Flu4NH.jpg"));
    Icon flu5NH = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/Flu5NH.jpg"));
    Icon flu1NV = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/flu1NV.jpg"));
    Icon flu2NV = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/flu2NV.jpg"));
    Icon flu3NV = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/flu3NV.jpg"));
    Icon flu4NV = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/flu4NV.jpg"));
    Icon flu5NV = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/flu5NV.jpg"));
    //---

    Icon flu1SH = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/Flu1SH.jpg"));
    Icon flu2SH = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/Flu2SH.jpg"));
    Icon flu3SH = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/Flu3SH.jpg"));
    Icon flu4SH = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/Flu4SH.jpg"));
    Icon flu5SH = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/Flu5SH.jpg"));
    Icon flu1SV = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/flu1SV.jpg"));
    Icon flu2SV = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/flu2SV.jpg"));
    Icon flu3SV = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/flu3SV.jpg"));
    Icon flu4SV = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/flu4SV.jpg"));
    Icon flu5SV = new ImageIcon(BattleshipGUI.class.getResource("/images/flu/flu5SV.jpg"));
    private Color background;
 	
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
	
	public static JPanel getMainPanel() {
		return mainPanel;
	}

	public void onSetFieldsize() {
		try {
			dialogs.setFieldsize();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		case onStatus:
			onStatus();
		default:
			break;
		}
	}
	
	public void onSetRowboat () {
		action = 1;
		BattleshipGUIUtils.setShip(0);
		paint();
	}

	public void onSetDestructor() {
		action = 2;
		BattleshipGUIUtils.setShip(1);
		paint();
	}

	public void onSetFlattop() {
		action = 3;
		BattleshipGUIUtils.setShip(2);
		paint();
	}
	
	private void paint() {
		infoPanel.invalidate();
		infoPanel.revalidate();
		infoPanel.repaint();
		mainPanel.validate();
	}

	public void onShowMenu() {}

	public void onAction() {
		// TODO Auto-generated method stub
		
	}

	public void onShowPlayersField() {}

	public void onShowBotsField(boolean withShip) {
		// TODO Auto-generated method stub
		
	}

	public void onShootOnBot() {
		// TODO Auto-generated method stub
		
	}

	public void onStatus() {
		infoPanel.setT(controller.getStatus());
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
		if (action == 1) {
			controller.setHumanRowboat(x - 1, y - 1);
			playerPanel.setIcon(x, y, rowboatNormal, rowboatSelected);
		} else if (action == 2) {
			align = BattleshipGUIUtils.setAlignment();
			if (checkSetShipPosition(1, x - 1, y - 1, align)) {
				controller.setHumanDestructor(x - 1, y - 1, align);
				if (!align) {//horizontal
					playerPanel.setIcon(x, y, des1NH, des1SH);
					playerPanel.setIcon(x + 1, y, des2NH, des2SH);
					playerPanel.setIcon(x + 2, y, des3NH, des3SH);
				} else {
					playerPanel.setIcon(x, y, des1NV, des1SV);
					playerPanel.setIcon(x, y + 1, des2NV, des2SV);
					playerPanel.setIcon(x, y + 2, des3NV, des3SV);
				}
			}
		} else if (action == 3) {
			align = BattleshipGUIUtils.setAlignment();
			if (checkSetShipPosition(2, x - 1, y - 1, align)) {
				controller.setHumanFlattop(x - 1, y - 1, align);
				if (!align) {//horizontal
					playerPanel.setIcon(x, y, flu1NH, flu1SH);
						playerPanel.setIcon(x + 1, y, flu2NH, flu2SH);
						playerPanel.setIcon(x + 2, y, flu3NH, flu3SH);
						playerPanel.setIcon(x + 3, y, flu4NH, flu4SH);
						playerPanel.setIcon(x + 3, y, flu5NH, flu5SH);
				} else {
						playerPanel.setIcon(x, y, flu1NV, flu1SV);
						playerPanel.setIcon(x, y + 1, flu2NV, flu2SV);
						playerPanel.setIcon(x, y + 2, flu3NV, flu3SV);
						playerPanel.setIcon(x, y + 3, flu4NV, flu4SV);
						playerPanel.setIcon(x, y + 4, flu5NV, flu5SV);
				}
			}
		}
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
