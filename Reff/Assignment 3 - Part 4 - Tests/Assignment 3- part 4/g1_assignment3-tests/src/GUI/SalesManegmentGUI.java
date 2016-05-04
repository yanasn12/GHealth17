package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class SalesManegmentGUI extends AbstractMenuGUI{
	
	private static final long serialVersionUID = -4092967256164037867L;

	public SalesManegmentGUI(JFrame MainMenu) {
		super(MainMenu);
		int i;
		String ButtonNames[];
		
		this.panel_1.setBounds(10, 10, 484, 60);
		this.panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		this.panel.setBounds(10,85,484,50);
		this.panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		
		ButtonNames = new String[] {"Modify Discount Table","Sales Templates","Init Sales Op"};
		this.setButtonNames(ButtonNames);
		for(i=0;i<ButtonNames.length;i++)
		{
			functionButton[i].setEnabled(true);
			functionButton[i].setPreferredSize(new Dimension(150, 40));
		}
		for(;i<buttonNum;i++)
			functionButton[i].setVisible(false);
		
		
		//OverRide functionButtons[] action listeners//
		//ALL BUTTONS FORWARD TO THE TESTGUI WINDOW ATM//
		
		//ChargeCustomer
		this.functionButton[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModifyDiscountTableScreen(mainframe);
				mainframe.setVisible(false);
			}
		});
		//View Sale(invoice)
		this.functionButton[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModifyPeriodicDiscountTemplate(mainframe);
				mainframe.setVisible(false);
			}
		});
		//Init Sales OP
		this.functionButton[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InitiateSalesOperationScreen(mainframe);
				mainframe.setVisible(false);
			}
		});
		
		this.setTitle("Purchase Plans Options");
		this.setSize(520,180);
	}

}
