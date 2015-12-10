package de.htwg.battleship.view.gui;

import com.google.inject.Inject;
import de.htwg.battleship.controller.JavaBattleshipController;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Sandro
 */
@SuppressWarnings("serial")
public class BattleshipInfos extends JPanel {

	private JFormattedTextField jTextField2;
    private static final int COL = 255;
    private static final int TF = 12;
    private static final int XA = 14;
    private static final int XB = 102;
    private static final int XC = 140;
    private static final int XD = 88;
    private static final int XE = 104;
    private static final int XF = 161;
    private static final int XG = 23;
    private static final int XH = 139;
    private static final int XI = 39;
    
    private Color background = new Color(COL, COL, COL);
    private JavaBattleshipController controller;

    /**
     * declares some minor attributes/options/information
     * @param controller
     */
    @Inject
    public BattleshipInfos(JavaBattleshipController controller) {
    	
    	this.controller = controller;
        
    	jTextField2 = new javax.swing.JFormattedTextField();
        JPanel jPanel1 = new javax.swing.JPanel();
        jPanel1.setBackground(background);
        JTextField jTextField3 = new javax.swing.JTextField();
        JTextField jTextField1 = new javax.swing.JTextField();
        
        setBackground(background);

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, XA));
        jTextField2.setBackground(background);
        jTextField2.setForeground(new java.awt.Color(0, 0, XB));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText(controller.getStatus());
        jTextField2.setAutoscrolls(false);
        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Status:"));
        jTextField2.setName("");

        jTextField3.setEditable(false);
        jTextField3.setBackground(background);
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, XA));
        jTextField3.setForeground(new java.awt.Color(XE, XF, COL));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("Flotte des Bots");
        jTextField3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField3.setPreferredSize(new java.awt.Dimension(XD, XG));
        jTextField3.setSelectionEnd(TF);
        jTextField3.setSelectionStart(TF);

        jTextField1.setEditable(false);
        jTextField1.setBackground(background);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, XA));
        jTextField1.setForeground(new java.awt.Color(XE, XF, COL));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Deine Flotte");
        jTextField1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField1.setScrollOffset(0);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, XC, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, XH, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextField2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, XI, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }
    
    /**
     * asks the controller for it's statusline
     */
    public void update() {
    	jTextField2.setValue(controller.getStatus());
    	revalidate();
    }
}
