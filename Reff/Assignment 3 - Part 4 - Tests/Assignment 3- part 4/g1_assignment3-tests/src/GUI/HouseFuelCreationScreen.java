package GUI;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import controllers.FuelTypeControl;
import controllers.HouseFuelOrderControl;
import controllers.InvoiceControl;
import controllers.LoginCont;
import controllers.OrderControl;
import controllers.PeriodicDiscountsControl;
import controllers.TrackingControl;
import entities.HouseFuelInfo;
import entities.Invoice;
import entities.Order;
import entities.OrderStatus;
import entities.HouseFuelOrder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * This window allows the creation of a new HouseFuelOrder by the customer. From this window the customer proceeds to payment.
 *
 */
public class HouseFuelCreationScreen extends JFrame {
	private static final long serialVersionUID = 2431491227292986957L;
	private JTextField textFieldOrderID;
	private JTextField textFieldInvoiceID;
	private JTextField textFieldQuantity;
	private JTextField textFieldDate;
	private JTextField textFieldTime;
	private JTextField textFieldPrice;
	private JTextField textFieldBasePrice;
	private JCheckBox chckbxUrgentDelivery;
	
	HouseFuelScreen prevWindow;
	HouseFuelOrder currOrder;
	HouseFuelInfo currInfo;
	Invoice currInvoice;
	
	public HouseFuelCreationScreen(HouseFuelScreen prev) {
		
		prevWindow = prev;
		
		setTitle("Create House Fuel Order");
		getContentPane().setBackground(new Color(0, 102, 153));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(10, 203, 414, 48);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnReturn = new JButton("return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prevWindow.setVisible(true);
				dispose ();
			}
		});
		btnReturn.setBounds(323, 11, 81, 23);
		panel.add(btnReturn);
		
		JButton btnProceedToPayment = new JButton("Proceed to Payment");
		btnProceedToPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkInput() == false)
					return;
				currOrder.setDeliveryTime(textFieldDate.getText().trim()+" "+textFieldTime.getText().trim());
				currOrder.setQuantity(Integer.parseInt(textFieldQuantity.getText()));
				currOrder.setUrgent(chckbxUrgentDelivery.isSelected());
				currOrder.calcPrice();
				currOrder.setOrderTime(OrderControl.getCurrDateTime());
				
				currInvoice.setDueDate(textFieldDate.getText().trim());
				currInvoice.setTotalPrice(currOrder.getPrice());
				
				currInfo.setEstimatedTimeLeft((int)PeriodicDiscountsControl.getSecsLeft(currOrder.getDeliveryTime())/3600);
				InvoiceControl.createInvoice(currInvoice);
				HouseFuelOrderControl.createHouseFuelOrder(currOrder);
				TrackingControl.createHouseFuelInfo(currInfo);
				
				JOptionPane.showMessageDialog(null,"Your order has been created ! You can choose to either pay the order now or upon arrival.");
				new MakePaymentScreen(prevWindow, currInvoice);
				dispose ();
			}
		});
		btnProceedToPayment.setBounds(10, 11, 160, 23);
		panel.add(btnProceedToPayment);
		
		JButton btnCalculatePrice = new JButton("Calculate Price");
		btnCalculatePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (checkInput() == false)
					return;
				
				currOrder.setQuantity(Integer.parseInt(textFieldQuantity.getText()));
				currOrder.setUrgent(chckbxUrgentDelivery.isSelected());
				currOrder.calcPrice();
				textFieldPrice.setText(""+currOrder.getPrice());
			}
		});
		btnCalculatePrice.setBounds(180, 11, 131, 23);
		panel.add(btnCalculatePrice);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBounds(10, 11, 414, 181);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblOrderid = new JLabel("OrderID:");
		lblOrderid.setBounds(28, 14, 104, 14);
		panel_1.add(lblOrderid);
		
		JLabel lblInvoiceid = new JLabel("InvoiceID:");
		lblInvoiceid.setBounds(28, 39, 104, 14);
		panel_1.add(lblInvoiceid);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(28, 64, 104, 14);
		panel_1.add(lblQuantity);
		
		textFieldOrderID = new JTextField();
		textFieldOrderID.setEditable(false);
		textFieldOrderID.setBounds(142, 11, 128, 20);
		panel_1.add(textFieldOrderID);
		textFieldOrderID.setColumns(10);
		
		textFieldInvoiceID = new JTextField();
		textFieldInvoiceID.setEditable(false);
		textFieldInvoiceID.setBounds(142, 36, 128, 20);
		panel_1.add(textFieldInvoiceID);
		textFieldInvoiceID.setColumns(10);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setBounds(142, 61, 128, 20);
		panel_1.add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
		
		JLabel lblDeliveryDate = new JLabel("Delivery Date:");
		lblDeliveryDate.setBounds(28, 89, 104, 14);
		panel_1.add(lblDeliveryDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(142, 86, 128, 20);
		panel_1.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JLabel lblDeliveryTime = new JLabel("Delivery Time:");
		lblDeliveryTime.setBounds(28, 114, 104, 14);
		panel_1.add(lblDeliveryTime);
		
		textFieldTime = new JTextField();
		textFieldTime.setBounds(142, 111, 128, 20);
		panel_1.add(textFieldTime);
		textFieldTime.setColumns(10);
		
		JLabel lblOverallPrice = new JLabel("Overall Price");
		lblOverallPrice.setBounds(305, 86, 84, 14);
		panel_1.add(lblOverallPrice);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setEditable(false);
		textFieldPrice.setBounds(295, 108, 109, 20);
		panel_1.add(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		JLabel lblPricePerL = new JLabel("Price Per L");
		lblPricePerL.setBounds(312, 14, 77, 14);
		panel_1.add(lblPricePerL);
		
		textFieldBasePrice = new JTextField();
		textFieldBasePrice.setEditable(false);
		textFieldBasePrice.setBounds(295, 36, 109, 20);
		panel_1.add(textFieldBasePrice);
		textFieldBasePrice.setColumns(10);
		
		chckbxUrgentDelivery = new JCheckBox("Urgent Delivery");
		chckbxUrgentDelivery.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chckbxUrgentDelivery.isSelected())
				{
					currOrder.setDeliveryTime(PeriodicDiscountsControl.addHour(OrderControl.getCurrDateTime(), 6));
					textFieldDate.setText(currOrder.getDeliveryTime().substring(0,10));
					textFieldTime.setText(currOrder.getDeliveryTime().substring(11,19));
					textFieldDate.setEditable(false);
					textFieldTime.setEditable(false);
				}
				else
				{
					textFieldDate.setEditable(true);
					textFieldTime.setEditable(true);
				}
			}
		});
		chckbxUrgentDelivery.setBackground(new Color(153, 204, 255));
		chckbxUrgentDelivery.setBounds(142, 138, 128, 23);
		panel_1.add(chckbxUrgentDelivery);
		
		loadNewOrder ();
		textFieldBasePrice.setText(""+currOrder.getFuelType().getBasePrice());
		textFieldInvoiceID.setText(""+currOrder.getInvoiceID());
		textFieldOrderID.setText(""+currOrder.getOrderID());
		
		setSize (450,300);
		setVisible (true);
	}

	/**
	 * Initialises a new Order, OrderInfo and Invoice to be modified in this screen
	 */
	public void loadNewOrder ()
	{
		int id;
		currOrder = new HouseFuelOrder();
		currInvoice = new Invoice();
		currInfo = new HouseFuelInfo();
		
		id = 0;
		for (Order order : OrderControl.getAllOrders())
		{
			if (order.getOrderID() > id)
				id = order.getOrderID();
		}
		currOrder.setOrderID(id + 1);
		currOrder.setHouseFuelInfoID(id + 1);
		currInfo.setHouseFuelInfoID(id + 1);
		currInfo.setHouseFuelOrderID(id + 1);
		
		id = 0;
		for (Invoice invoice : InvoiceControl.getAllInvoices())
		{
			if (invoice.getInvoiceID() > id)
				id = invoice.getInvoiceID();
		}
		currInvoice.setInvoiceID(id + 1);
		currOrder.setInvoiceID(id + 1);
		
		currOrder.setFuelTypeID(FuelTypeControl.HOUSE_FUEL_ID);
		currOrder.setHouseOwnerID(LoginCont.getCurrUser().getUserID());
		currOrder.setPeriodicDiscountID(0);
		currOrder.setStatus(OrderStatus.PAYMENT_PENDING.ordinal());
		
		currInvoice.setCustomerID(currOrder.getHouseOwnerID());
		currInvoice.setPurchasePlanID(0);
		currInvoice.setSubscriptionID(0);
		currInvoice.setStatus(OrderStatus.PAYMENT_PENDING.ordinal());
	}
	
	/**
	 * Checks if the inputed screen fields are valid. Clears the invalid fields
	 */
	public boolean checkInput ()
	{
		long time;
		long date;
		long urgentDate;
		long urgentTime;
		boolean isValid = true;
		try {
			if (Integer.parseInt(textFieldQuantity.getText()) < 0)
			{
				textFieldQuantity.setText("");
				isValid = false;
			}
		}
		catch (Exception e)
		{
			textFieldQuantity.setText("");
			isValid = false;
		}
		
		if (PeriodicDiscountsControl.checkDate(textFieldDate.getText().trim()) == false)
		{
			textFieldDate.setText("");
			isValid = false;
		}
		
		if (PeriodicDiscountsControl.checkTime (textFieldTime.getText().trim()) == false)
		{
			textFieldTime.setText("");
			isValid = false;
		}
		
		if (PeriodicDiscountsControl.getDateNumericValue(textFieldDate.getText().trim()) 
				< PeriodicDiscountsControl.getDateNumericValue(OrderControl.getCurrDate()))
		{
			textFieldDate.setText("");
			isValid = false;
		}
		else if (PeriodicDiscountsControl.getDateNumericValue(textFieldDate.getText().trim()) 
				== PeriodicDiscountsControl.getDateNumericValue(OrderControl.getCurrDate()))
		{
			if (PeriodicDiscountsControl.getTimeNumericValue(textFieldTime.getText().trim()) 
					< PeriodicDiscountsControl.getTimeNumericValue(OrderControl.getCurrTime()))
			{
				textFieldTime.setText("");
				isValid = false;
			}
		}
		
		if (isValid && chckbxUrgentDelivery.isSelected() == false)
		{
			String urgentDateTime = PeriodicDiscountsControl.addHour(OrderControl.getCurrDateTime(),6);
			urgentDate = PeriodicDiscountsControl.getDateNumericValue(urgentDateTime.substring(0,10));
			urgentTime = PeriodicDiscountsControl.getTimeNumericValue(urgentDateTime.substring(11,19));
		
			date = PeriodicDiscountsControl.getDateNumericValue(textFieldDate.getText().trim());
			time = PeriodicDiscountsControl.getTimeNumericValue(textFieldTime.getText().trim());
			if (date < urgentDate)
			{
				chckbxUrgentDelivery.setSelected(true);
			}
			else
			{
				if (date == urgentDate && time < urgentTime)
					chckbxUrgentDelivery.setSelected(true);
			}
		}
		return isValid;
	}
}
