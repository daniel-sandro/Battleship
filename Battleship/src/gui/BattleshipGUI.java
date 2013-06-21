package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import observer.IObserver;

import controller.Controller;

public class BattleshipGUI extends JFrame implements IObserver {

	public static JPanel mainPanel;
	private JButton JBsize;
    private JTextField JTFsize;
	BattleshipStatus statusPanel;
	BattleshipInfos infoPanel;
	private int fieldsize;
	Controller controller;
	BattleshipDialogs dialogs;
	
	
	public BattleshipGUI(Controller controller) {
		dialogs = new BattleshipDialogs(this);
		
		mainPanel = new JPanel();
	    mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	    
	    JButton test = new JButton("test");
	    mainPanel.add(test);
	    
	    
	    statusPanel = new BattleshipStatus();
	    infoPanel = new BattleshipInfos();
	    
	    //mainPanel.add(infoPanel);
	    
	    this.setContentPane(mainPanel);
        this.setTitle("Battleship");
        // this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
        this.go();
	}
	
    public static JPanel getMainPanel() {
		return mainPanel;
	}

	public static void setMainPanel(JPanel mainPanel) {
		BattleshipGUI.mainPanel = mainPanel;
	}
	
	public boolean onSetRowboat () {
		dialogs.setFieldsize();
		return true;
	}
	
	public boolean onSetFieldsize() {
		dialogs.setFieldsize();
		return true;
	}
	
	public void go() {
		onSetFieldsize();
	}
	
	public int getFieldsize() {
		return controller.getFieldsize();
	}
	
	public static void main(String args[]) {
		BattleshipGUI gui = new BattleshipGUI(new Controller(0));
	}

	public boolean onSetDestructor() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onSetFlattop() {
		// TODO Auto-generated method stub
		return false;
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
}
