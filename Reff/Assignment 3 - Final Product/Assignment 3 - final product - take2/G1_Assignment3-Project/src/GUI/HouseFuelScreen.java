package GUI;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import entities.HouseFuelOrder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JCheckBox;
import controllers.HouseFuelOrderControl;
import controllers.LoginCont;
import controllers.TrackingControl;
import javax.swing.ListSelectionModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
/**
 * This window handles the presentation of all HouseFuelOrders, either per Customer or for all customers depending on who views the window.
 */
public class HouseFuelScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTable tableOrders;
	private JCheckBox chckbxShowonlyactive;
	
	JFrame prevWindow;
	HouseFuelScreen currWindow;
	List <HouseFuelOrder> loadedOrders;
	List <HouseFuelOrder> shownOrders;
	
	/**
	 * Creates a new HouseFuelScreen
	 * @param prev the window to which this screen returns.
	 */
	public HouseFuelScreen(JFrame prev) {
		
		prevWindow = prev;
		currWindow = this;
		
		setTitle("House Fuel Managment");
		getContentPane().setBackground(new Color(51, 102, 153));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(10, 411, 614, 40);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnReturn = new JButton("return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prevWindow.setVisible(true);
				dispose ();
			}
		});
		btnReturn.setBounds(517, 11, 87, 23);
		panel.add(btnReturn);
		
		JButton btnNewOrder = new JButton("New Order");
		btnNewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new HouseFuelCreationScreen(currWindow);
				setVisible (false);
			}
		});
		btnNewOrder.setBounds(10, 11, 104, 23);
		panel.add(btnNewOrder);
		
		JButton btnOrderTracking = new JButton("Track Order");
		btnOrderTracking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tableOrders.getSelectedRow() >= 0 && tableOrders.getSelectedRow() < shownOrders.size())
				{
					new HouseFuelTrackingScreen (currWindow, shownOrders.get(tableOrders.getSelectedRow()).getHouseFuelInfo());
					setVisible (false);
				}
			}
		});
		btnOrderTracking.setBounds(120, 11, 104, 23);
		panel.add(btnOrderTracking);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 614, 389);
		getContentPane().add(scrollPane);
		
		tableOrders = new JTable();
		tableOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableOrders.setBackground(new Color(153, 204, 255));
		tableOrders.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"OrderID", "CustomerID", "Customer Address", "InvoiceID", "Quantity", "Price", "Urgent", "Delivery Time"
			}
		));
		tableOrders.getColumnModel().getColumn(0).setPreferredWidth(54);
		tableOrders.getColumnModel().getColumn(1).setPreferredWidth(73);
		tableOrders.getColumnModel().getColumn(2).setPreferredWidth(130);
		tableOrders.getColumnModel().getColumn(3).setPreferredWidth(64);
		tableOrders.getColumnModel().getColumn(4).setPreferredWidth(58);
		tableOrders.getColumnModel().getColumn(5).setPreferredWidth(49);
		tableOrders.getColumnModel().getColumn(6).setPreferredWidth(45);
		tableOrders.getColumnModel().getColumn(7).setPreferredWidth(160);
		
		scrollPane.setViewportView(tableOrders);
		
		chckbxShowonlyactive = new JCheckBox("Show Only Active");
		chckbxShowonlyactive.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				showOrders ();
			}
		});
		chckbxShowonlyactive.setBackground(new Color(153, 204, 255));
		chckbxShowonlyactive.setBounds(235, 11, 154, 23);
		panel.add(chckbxShowonlyactive);
		
		if (LoginCont.getCurrUser().getUserType().isCustomer() == false)
			btnNewOrder.setEnabled(false);
		
		loadOrders();
		setSize (650,500);
		setVisible(true);
	}
	
	/**
	 * Chooses a set of orders to show, the orders will depend on whether or not the logged in user is a customer or not. Customers see only their own orders, workers can see all orders.
	 */
	public void loadOrders ()
	{
		if (LoginCont.getCurrUser().getUserType().isCustomer())
		{
			loadedOrders = new LinkedList<HouseFuelOrder>();
			for (HouseFuelOrder order : HouseFuelOrderControl.getAllHouseFuelOrders())
			{
				if (order.getHouseOwnerID() == LoginCont.getCurrUser().getUserID())
					loadedOrders.add(order);
			}
		}
		else
			loadedOrders = HouseFuelOrderControl.getAllHouseFuelOrders();
		showOrders();
	}
	
	/**
	 * Puts the chosen set of orders into the order table.
	 */
	public void showOrders ()
	{
		String[][] table;
		if (chckbxShowonlyactive.isSelected())
		{
			shownOrders = new LinkedList<HouseFuelOrder>();
			for (HouseFuelOrder order : loadedOrders)
			{
				if (order.getHouseFuelInfo().getDeliveryStatus() != TrackingControl.DELIVERED)
					shownOrders.add(order);
			}
		}
		else
			shownOrders = loadedOrders;
		if (shownOrders == null || shownOrders.size() == 0)
		{
			table = new String [1][8];
			for (int i = 0; i < 8; i++)
				table[0][i] = "";
		}
		else
		{
			table = new String[shownOrders.size()][8];
			int i = 0;
			for (HouseFuelOrder order : shownOrders)
			{
				table[i][0] = ""+order.getOrderID();
				table[i][1] = ""+order.getHouseOwnerID();
				table[i][2] = ""+order.getHouseOwner().getAddress();
				table[i][3] = ""+order.getInvoiceID();
				table[i][4] = ""+order.getQuantity();
				table[i][5] = ""+order.getPrice();
				if (order.isUrgent())
					table[i][6] = "Yes";
				else
					table[i][6] = "No";
				if (order.getHouseFuelInfo().getDeliveryStatus() == TrackingControl.DELIVERED)
					table[i][7] = order.getDeliveryTime();
				else
					table[i][7]="";
				i++;
			}
		}
		
		tableOrders.setModel(new DefaultTableModel(
				table,
				new String[] {
						"OrderID", "CustomerID", "Customer Address", "InvoiceID", "Quantity", "Price", "Urgent", "Delivery Time"
				}
			) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
		tableOrders.getColumnModel().getColumn(0).setPreferredWidth(54);
		tableOrders.getColumnModel().getColumn(1).setPreferredWidth(73);
		tableOrders.getColumnModel().getColumn(2).setPreferredWidth(130);
		tableOrders.getColumnModel().getColumn(3).setPreferredWidth(64);
		tableOrders.getColumnModel().getColumn(4).setPreferredWidth(58);
		tableOrders.getColumnModel().getColumn(5).setPreferredWidth(49);
		tableOrders.getColumnModel().getColumn(6).setPreferredWidth(45);
		tableOrders.getColumnModel().getColumn(7).setPreferredWidth(160);
	}
}
