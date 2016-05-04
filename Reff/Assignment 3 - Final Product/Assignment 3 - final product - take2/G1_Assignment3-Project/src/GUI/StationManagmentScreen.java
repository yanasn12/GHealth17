package GUI;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StationManagmentScreen extends JFrame {
	private static final long serialVersionUID = -747952778419715451L;
	private JFrame prevWindow;
	private JFrame mainframe;
	
	/**
	 * This menu screen allows workers to modify the station's stock as well as create manual CarFuelOrders for customers that refuel at their station.
	 * @param prev the window to be returned to.
	 */
	public StationManagmentScreen(JFrame prev) {
		prevWindow = prev;
		mainframe = this;
		
		getContentPane().setBackground(new Color(0, 102, 51));
		setTitle("Station Managment");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 102));
		panel.setBounds(10, 11, 246, 183);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnCreateCarFuel = new JButton("Create Car Fuel Order");
		btnCreateCarFuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CarFuelOrderCreationScreen(mainframe);
				setVisible(false);
			}
		});
		btnCreateCarFuel.setBounds(46, 29, 159, 41);
		panel.add(btnCreateCarFuel);
		
		JButton btnStockManagment = new JButton("Stock Managment");
		btnStockManagment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ModifyStockLevelsScreen(mainframe);
				setVisible (false);
			}
		});
		btnStockManagment.setBounds(46, 108, 159, 41);
		panel.add(btnStockManagment);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 102));
		panel_1.setBounds(10, 205, 246, 46);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnReturn = new JButton("return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prevWindow.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(147, 11, 89, 23);
		panel_1.add(btnReturn);
		
		setSize(285,300);
		setVisible(true);
	}

}
