package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import controllers.FuelTypeControl;
import controllers.LoginCont;
import controllers.OrderControl;
import controllers.StockControl;
import controllers.WorkerControl;
import entities.FuelReplenish;
import entities.Worker;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * This screen shows the information about the latest fuel replenish and allows the station workers to modify the status of the replenish order
 *
 */
public class FuelReplenishScreen extends JFrame {
	private static final long serialVersionUID = 7631894065526139565L;
	private JTextField textFieldReplenishID;
	private JTextField textFieldFuelTypeID;
	private JTextField textFieldFuelName;
	private JTextField textFieldStationID;
	private JTextField textFieldAmount;
	private JTextField textFieldStatus;
	private JButton btnConfirmArrival;
	private JButton btnSend;
	
	ModifyStockLevelsScreen prevWindow;
	FuelReplenish replenish;
	
	public FuelReplenishScreen(ModifyStockLevelsScreen prev, FuelReplenish rep) {
		
		prevWindow = prev;
		replenish = rep;
		
		getContentPane().setBackground(new Color(0, 102, 153));
		setTitle("Fuel Replenish Screen");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 195, 389, 44);
		panel.setBackground(new Color(153, 204, 255));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prevWindow.setVisible(true);
				dispose ();
			}
		});
		btnReturn.setBounds(290, 11, 89, 23);
		panel.add(btnReturn);
		
		btnConfirmArrival = new JButton("Confirm Arrival");
		btnConfirmArrival.setEnabled(false);
		btnConfirmArrival.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				replenish.setStatus(StockControl.RECEIVED);
				setFieldStatus(replenish.getStatus());
				replenish.setDeliveryDate(OrderControl.getCurrDate());
				replenish.getFuelStock().setStockAmount(replenish.getFuelStock().getStockAmount()+replenish.getAmount());
				StockControl.updateFuelReplenish(replenish.getFuelReplenishID());
				StockControl.updateFuelStock(replenish.getFuelTypeID(), replenish.getStationID());
				prevWindow.updateAmount(replenish.getFuelStock().getStockAmount());
				
				List<Worker> wl = WorkerControl.getAllWorkers();
				Worker stationManager = null;
				for(Worker w : wl)
					if(w.getUserTypeID()==5 && w.getLocationID()==replenish.getStationID())
						stationManager = w;
				
				LoginCont.sendMail("g1.myfuel@gmail.com", "Braude1234", stationManager.geteMail(), "New Fuel Replenish Order", "Recived a fuel replenish at StationID:"+replenish.getStationID()+" of FuelTypeID:"+replenish.getFuelTypeID());
				
			}
		});
		btnConfirmArrival.setBounds(10, 11, 124, 23);
		panel.add(btnConfirmArrival);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				replenish.setStatus(StockControl.SENT);
				setFieldStatus(replenish.getStatus());
				StockControl.updateFuelReplenish(replenish.getFuelReplenishID());
			}
		});
		btnSend.setEnabled(false);
		btnSend.setBounds(144, 11, 89, 23);
		panel.add(btnSend);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 389, 173);
		panel_1.setBackground(new Color(153, 204, 255));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblReplenishid = new JLabel("ReplenishID:");
		lblReplenishid.setBounds(26, 14, 91, 14);
		panel_1.add(lblReplenishid);
		
		textFieldReplenishID = new JTextField();
		textFieldReplenishID.setEditable(false);
		textFieldReplenishID.setBounds(127, 11, 109, 17);
		panel_1.add(textFieldReplenishID);
		textFieldReplenishID.setColumns(10);
		
		JLabel lblFueltypeid = new JLabel("FuelTypeID:");
		lblFueltypeid.setBounds(26, 39, 91, 14);
		panel_1.add(lblFueltypeid);
		
		textFieldFuelTypeID = new JTextField();
		textFieldFuelTypeID.setEditable(false);
		textFieldFuelTypeID.setColumns(10);
		textFieldFuelTypeID.setBounds(127, 36, 109, 17);
		panel_1.add(textFieldFuelTypeID);
		
		JLabel lblFuelName = new JLabel("Fuel Name:");
		lblFuelName.setBounds(26, 64, 91, 14);
		panel_1.add(lblFuelName);
		
		textFieldFuelName = new JTextField();
		textFieldFuelName.setEditable(false);
		textFieldFuelName.setColumns(10);
		textFieldFuelName.setBounds(127, 61, 109, 17);
		panel_1.add(textFieldFuelName);
		
		JLabel lblStationid = new JLabel("StationID:");
		lblStationid.setBounds(26, 89, 91, 14);
		panel_1.add(lblStationid);
		
		textFieldStationID = new JTextField();
		textFieldStationID.setEditable(false);
		textFieldStationID.setColumns(10);
		textFieldStationID.setBounds(127, 86, 109, 17);
		panel_1.add(textFieldStationID);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(26, 114, 91, 14);
		panel_1.add(lblAmount);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setEditable(false);
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(127, 111, 109, 17);
		panel_1.add(textFieldAmount);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(26, 139, 91, 14);
		panel_1.add(lblStatus);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setEditable(false);
		textFieldStatus.setColumns(10);
		textFieldStatus.setBounds(127, 136, 109, 17);
		panel_1.add(textFieldStatus);
		
		textFieldAmount.setText(""+replenish.getAmount());
		textFieldFuelName.setText(FuelTypeControl.getFuelTypeById(replenish.getFuelTypeID()).getFuelName());
		textFieldFuelTypeID.setText(""+replenish.getFuelTypeID());
		textFieldReplenishID.setText(""+replenish.getFuelReplenishID());
		textFieldStationID.setText(""+replenish.getStationID());
		setFieldStatus(replenish.getStatus());
		
		setSize (425,285);
		setVisible(true);
	}
	
	/**
	 * Setups the value of the status field.
	 * @param status the status to be set to.
	 */
	public void setFieldStatus (int status)
	{
		switch (status)
		{
			case StockControl.CREATED:
				textFieldStatus.setText("Created");
				btnConfirmArrival.setEnabled(false);
				btnSend.setEnabled(true);
				break;
			case StockControl.SENT:
				btnConfirmArrival.setEnabled(true);
				btnSend.setEnabled(false);
				textFieldStatus.setText("Sent");
				break;
			case StockControl.RECEIVED:
				btnConfirmArrival.setEnabled(false);
				btnSend.setEnabled(false);
				textFieldStatus.setText("Arrived");
				break;
		}
	}
}
