package GUI;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PurchasePlanGUI extends AbstractMenuGUI{

	public PurchasePlanGUI(JFrame MainMenu) {
		super(MainMenu);
		int i;
		String ButtonNames[];
		
		this.panel_1.setBounds(10, 10, 484, 60);
		this.panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		this.panel.setBounds(10,85,484,50);
		this.panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		
		ButtonNames = new String[] {"Match Plan To Cusustomer","Modify Purchase Plans"};
		this.setButtonNames(ButtonNames);
		for(i=0;i<ButtonNames.length;i++)
		{
			functionButton[i].setEnabled(true);
			functionButton[i].setPreferredSize(new Dimension(220, 40));
		}
		for(;i<buttonNum;i++)
			functionButton[i].setVisible(false);
		
		
		//OverRide functionButtons[] action listeners//
		//ALL BUTTONS FORWARD TO THE TESTGUI WINDOW ATM//
		this.functionButton[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MatchPlanToCustomerScreen(mainframe);
				mainframe.setVisible(false);
			}
		});
		//Modify Purchase Plans was pressed
		this.functionButton[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModifyPurchasePlanScreen(mainframe);
				mainframe.setVisible(false);
			}
		});
	
		
		this.setTitle("Purchase Plans Options");
		this.setSize(520,180);
	}

	private static final long serialVersionUID = -4980880010829011983L;
}
