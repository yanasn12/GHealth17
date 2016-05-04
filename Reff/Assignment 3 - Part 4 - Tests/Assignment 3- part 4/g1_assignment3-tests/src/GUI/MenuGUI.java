package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MenuGUI extends AbstractMenuGUI{

	private static final long serialVersionUID = -8827467900918003289L;

	public MenuGUI(JFrame loginGUI) {
		super(loginGUI);
		
		//ACTION LISTENERS TO ALL BUTTON FUNCTIONS 0-12//
		//BUTTON NUM 0 WILL DIRECT TO THE TEST SCREEN FOR DB TESTING - FOR NOW//
		functionButton[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    new StationManagmentScreen(mainframe);
				mainframe.setVisible(false);
			}
		});
				
		//BUTTON NUM 1 WILL DIRECT TO THE PURCHASE PLAN MENU//
		functionButton[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PurchasePlanGUI(mainframe);
				mainframe.setVisible(false);
			}
		});
		//BUTTON NUM 2 WILL DIRECT TO THE Customer registration screen//
				functionButton[2].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new CustomerRegistrationScreen(mainframe);
						mainframe.setVisible(false);
					}
				});
		//BUTTON NUM 3 WILL DIRECT TO THE Sales management GUI//
		functionButton[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SalesManegmentGUI(mainframe);
				mainframe.setVisible(false);
			}
		});
		//BUTTON NUM 4 WILL DIRECT TO THE OpenReports Screen//
				functionButton[4].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new NFCModuleGUI(mainframe);
						mainframe.setVisible(false);
					}
				});
		//BUTTON NUM 5 WILL DIRECT TO THE Fuel management GUI//
		functionButton[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModifyFuelTypeScreen(mainframe);
				mainframe.setVisible(false);
			}
		});

		//BUTTON NUM 6 WILL DIRECT TO THE Messages GUI//
		functionButton[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerRegistrationScreen(mainframe);
				mainframe.setVisible(false);
			}
		});
		
		//BUTTON NUM 7 WILL DIRECT TO THE Invoices GUI//
		functionButton[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InvoiceGUI(mainframe);
				mainframe.setVisible(false);
			}
		});
		
		//BUTTON NUM 8 WILL DIRECT TO THE Reports GUI//
		functionButton[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReportsMenuGUI(mainframe);
				mainframe.setVisible(false);
			}
		});
				
		//BUTTON NUM 9 WILL DIRECT TO THE HouseFuelOrderAdmin GUI//
		functionButton[9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HouseFuelScreen(mainframe);
				mainframe.setVisible(false);
			}
		});
				
				//END OF ACTION LISTENERS//
	}
	

}
