package GUI;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import javax.swing.JTextField;
import javax.swing.JLabel;

import controllers.CarFuelOrderControl;
import controllers.LoginCont;
import entities.CarFuelOrder;
import entities.Worker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarFuelOrderCreationScreen extends JFrame {
	private static final long serialVersionUID = 332056472148881058L;
	private JTextField textFieldStationID;
	private JTextField textFieldCarID;
	private JTextField textFieldQuantity;
	private JTextField textFieldDriverName;
	private JFrame prevWindow;
	
	public CarFuelOrderCreationScreen(JFrame prev) {
		
		prevWindow = prev;
		
		getContentPane().setBackground(new Color(0, 102, 51));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 102));
		panel.setBounds(10, 11, 304, 190);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		textFieldStationID = new JTextField();
		textFieldStationID.setEditable(false);
		textFieldStationID.setBounds(103, 11, 154, 20);
		panel.add(textFieldStationID);
		textFieldStationID.setColumns(10);
		
		JLabel lblStationid = new JLabel("StationID:");
		lblStationid.setBounds(25, 14, 90, 14);
		panel.add(lblStationid);
		
		textFieldCarID = new JTextField();
		textFieldCarID.setText("");
		textFieldCarID.setBounds(103, 42, 154, 20);
		panel.add(textFieldCarID);
		textFieldCarID.setColumns(10);
		
		JLabel lblCarid = new JLabel("CarID:");
		lblCarid.setBounds(25, 45, 90, 14);
		panel.add(lblCarid);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setBounds(103, 73, 154, 20);
		panel.add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(25, 76, 90, 14);
		panel.add(lblQuantity);
		
		textFieldDriverName = new JTextField();
		textFieldDriverName.setBounds(103, 104, 154, 20);
		panel.add(textFieldDriverName);
		textFieldDriverName.setColumns(10);
		
		JLabel lblDriverName = new JLabel("Driver Name:");
		lblDriverName.setBounds(25, 107, 90, 14);
		panel.add(lblDriverName);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 102));
		panel_1.setBounds(10, 212, 304, 52);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnCreateOrder = new JButton("Create Order");
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int stationID = 0;
				int carID = 0;
				double quantity = 0;
				String driverName = "";
				CarFuelOrder order;
				
				try {
					quantity = Double.parseDouble(textFieldQuantity.getText());
					carID = Integer.parseInt(textFieldCarID.getText());
					driverName = textFieldDriverName.getText();
					stationID = Integer.parseInt(textFieldStationID.getText());
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Invalid fields! Please check you have entered proper numbers.");
					return;
				}
				
				try {
					order = CarFuelOrderControl.generateNewCarFuelOrder(stationID, carID, quantity, driverName);
					if (order == null)
					{
						JOptionPane.showMessageDialog(null,"No enough fuel in stock for this refuelling !");
						return;
					}
					
					JOptionPane.showMessageDialog(null,"Successfully created order: \n" +
													   "OrderID:   " + order.getOrderID() + "\n" +
													   "CarID:     " + order.getCarID() + "\n" +
													   "Time:      " + order.getOrderTime() + "\n\n"+
													   "FuelType:  " + order.getFuelType().getFuelName() + "\n" +
													   "Quantity:  " + order.getQuantity() + "\n" +
													   "Price:     " + order.getPrice() + "\n\n" +
													   "InvoiceID: " + order.getInvoiceID() + "\n" +
													   "The invoice is ready to be paid by the customer in the invoices menu !");
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Order creation failed ...");
				}
					
			}
		});
		btnCreateOrder.setBounds(10, 11, 109, 30);
		panel_1.add(btnCreateOrder);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prevWindow.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(193, 11, 101, 30);
		panel_1.add(btnReturn);
		setTitle("Car Fuel Order Creation");
		
		textFieldStationID.setText(""+((Worker)LoginCont.getCurrUser()).getLocationID());
		
		setSize (340,312);
		setVisible(true);
	}

}
