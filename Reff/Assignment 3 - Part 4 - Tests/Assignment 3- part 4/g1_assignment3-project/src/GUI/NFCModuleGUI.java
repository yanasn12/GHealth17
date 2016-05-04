package GUI;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import controllers.CarFuelOrderControl;
import controllers.CustomerControl;
import controllers.FuelTypeControl;
import controllers.InvoiceControl;
import controllers.WorkerControl;
import entities.Car;
import entities.CarFuelOrder;
import entities.FuelType;
import entities.Station;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JComboBox;

/**
 * This window simulates and NFC module that sends to our system a carID and the quantity that needs to be filled.
 */
public class NFCModuleGUI extends JFrame {
	private static final long serialVersionUID = 5974031952401110978L;
	private JTextField textFieldCarID;
	private JTextField textFieldQuantity;
	private JLabel lblFuelQuantity;
	private JPanel panel_1;
	private JButton btnCreate;
	private JFrame prevWindow;
	private JTextField textFieldStation;
	private JComboBox comboBoxFuelType;
	
	List<FuelType> pumps;
	
	/**
	 * Creates a new NFCModuleGUI
	 * @param prev the window to which this screen returns.
	 */
	public NFCModuleGUI(JFrame prev) {
		
		prevWindow = prev;
		
		setTitle("NFC Module Simulation");
		getContentPane().setBackground(new Color(0, 102, 102));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 102));
		panel.setBounds(10, 11, 337, 184);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		textFieldCarID = new JTextField();
		textFieldCarID.setBounds(91, 11, 163, 20);
		panel.add(textFieldCarID);
		textFieldCarID.setColumns(10);
		
		JLabel lblCarid = new JLabel("CarID:");
		lblCarid.setBounds(10, 14, 61, 14);
		panel.add(lblCarid);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setBounds(91, 42, 163, 20);
		panel.add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
		
		lblFuelQuantity = new JLabel("Fuel Quantity:");
		lblFuelQuantity.setBounds(10, 45, 82, 14);
		panel.add(lblFuelQuantity);
		
		textFieldStation = new JTextField();
		textFieldStation.setBounds(91, 73, 163, 20);
		panel.add(textFieldStation);
		textFieldStation.setColumns(10);
		
		JLabel lblStationid = new JLabel("StationID:");
		lblStationid.setBounds(10, 76, 82, 14);
		panel.add(lblStationid);
		
		pumps = FuelTypeControl.getAllFuelTypes();
		
		comboBoxFuelType = new JComboBox();
		comboBoxFuelType.setBounds(91, 119, 163, 20);
		for (FuelType fuel : pumps)
		{
			if (fuel.getFuelTypeID() != 3)
				comboBoxFuelType.addItem(fuel.getFuelName());
		}
		panel.add(comboBoxFuelType);
		
		JLabel lblFuelPump = new JLabel("Fuel Pump:");
		lblFuelPump.setBounds(10, 122, 82, 14);
		panel.add(lblFuelPump);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 102));
		panel_1.setBounds(10, 206, 337, 45);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prevWindow.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(241, 11, 79, 23);
		panel_1.add(btnReturn);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				creteNFCOrder ();
			}
		});
		btnCreate.setBounds(10, 11, 89, 23);
		panel_1.add(btnCreate);
		
		setSize(375,300);
		setVisible(true);
	}
	
	/**
	 * Attempts to create an automatic NFC order and pay it automatically. Shows message boxes to give information about the process results.
	 */
	public void creteNFCOrder ()
	{
		CarFuelOrder order;
		int stationID = 0; 
		int carID = 0;
		double quantity = 0;
		boolean paymentRes;
		Car car;
		
		try {
			stationID = Integer.parseInt(textFieldStation.getText());
			carID = Integer.parseInt(textFieldCarID.getText());
			quantity = Double.parseDouble(textFieldQuantity.getText());
			car = CustomerControl.getCarById(carID);
			if (car.getFuelType().getFuelName().equals((String)comboBoxFuelType.getSelectedItem()) == false)
			{
				JOptionPane.showMessageDialog(null,"Wrong pump selected !");
				return;
			}
			
			boolean canAdd = false;
			for (Car c : CustomerControl.getAllCars())
				if (c.getCarID() == carID)
				{
					canAdd = true;
					break;
				}
			
			if (!canAdd)
			{
				JOptionPane.showMessageDialog(null,"The given CarID doesn't exist !");
				return;
			}
			
			canAdd = false;
			for (Station s : WorkerControl.getAllStations())
				if (s.getLocationID() == stationID)
				{
					canAdd = true;
					break;
				}
			
			if (!canAdd)
			{
				JOptionPane.showMessageDialog(null,"The given StationID doesn't exist !");
				return;
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Invalid fields ! Please check you have entered proper numbers.");
			return;
		}
		order = CarFuelOrderControl.generateNewCarFuelOrder(stationID, carID, quantity,"");
		if (order == null)
		{
			JOptionPane.showMessageDialog(null,"No enough fuel in station for refuelling !");
			return;
		}
			
		InvoiceControl.setActive(order.getInvoice());
		paymentRes = InvoiceControl.payActiveAuto();
		if (paymentRes == false)
			JOptionPane.showMessageDialog(null,"Payment failed for this order ! Invoice remains open for manual payment by the customer as standard refueling.");
		else
			JOptionPane.showMessageDialog(null,"Payment successful ! Invoice added as paid !");
	}
}
