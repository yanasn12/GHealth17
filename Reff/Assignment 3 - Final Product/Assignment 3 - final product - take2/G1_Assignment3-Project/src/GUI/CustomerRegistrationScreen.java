package GUI;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;

import controllers.CustomerControl;
import controllers.LoginCont;
import controllers.PaymentInfoControl;
import controllers.PeriodicDiscountsControl;
import entities.CarCustomer;
import entities.Cash;
import entities.CreditCard;
import entities.Customer;
import entities.HouseOwner;
import entities.PaymentInfo;
import entities.User;

import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * GUI window to create a new customerthat will redirect the user to select a subscribtion/car for that customer and a purchase plan.
 * the window also allows to get a customer by its name to edit the customer and its car information.
 * editing will not open certain fields that create will.
 * 
 * @author Sergei Kovalchuck
 */

public class CustomerRegistrationScreen extends JFrame{

	private static final long serialVersionUID = 5901615254274718378L;
	private JFrame mainframe;
	private JFrame PrevWindow;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private	JButton btnCar = new JButton("Car info");
	private JButton btnSet = new JButton("Set");
	private JButton btnRegister = new JButton("New");
	private JButton btnGet = new JButton("Get");
	private JPanel panel = new JPanel();
	private JLabel lblPass = new JLabel("Login Password:");
	private JLabel lblCreditCardNo = new JLabel("Credit Card No:");
	private JLabel lblPrefferedPaymentmethod = new JLabel("Preffered Payment Method:");
	private JLabel lblCreditCardDate = new JLabel("Credit Card Date:");
	private List<Customer> cusl = new LinkedList<Customer>();
	private JCheckBox chckbxCarFleet = new JCheckBox("Car Fleet");
	
	private JComboBox<String> comboBox_1 = new JComboBox<String>();
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JTextField textField_8;
	
	public CustomerRegistrationScreen(JFrame PW) {
		this.PrevWindow=PW;
		this.mainframe=this;
		
		getContentPane().setBackground(new Color(46, 139, 87));
		getContentPane().setLayout(null);
		
		panel.setBackground(new Color(60, 179, 113));
		panel.setBounds(10, 11, 404, 359);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCustomerName = new JLabel("User Name:");
		lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomerName.setBounds(10, 11, 164, 21);
		panel.add(lblCustomerName);
		
		JLabel lblFamilyName = new JLabel("Family Name:");
		lblFamilyName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFamilyName.setBounds(10, 174, 130, 14);
		panel.add(lblFamilyName);
		
		JLabel lblAdd = new JLabel("Address:");
		lblAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAdd.setBounds(10, 104, 130, 21);
		panel.add(lblAdd);
		
		JLabel lblEmailAddress = new JLabel("eMail Address:");
		lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailAddress.setBounds(10, 136, 130, 21);
		panel.add(lblEmailAddress);
		
		lblCreditCardNo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCreditCardNo.setBounds(10, 232, 109, 21);
		
		textField = new JTextField();
		textField.setBounds(183, 12, 211, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(183, 105, 211, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(183, 137, 211, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(183, 169, 211, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPass.setBounds(10, 327, 109, 17);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setBounds(183, 200, 211, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setBounds(183, 232, 211, 20);
		textField_5.setColumns(10);
		
		JLabel lblCustomerType = new JLabel("Customer Type:");
		lblCustomerType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomerType.setBounds(10, 73, 98, 20);
		panel.add(lblCustomerType);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBox.getSelectedItem()=="House Owner")
				{
					chckbxCarFleet.setSelected(false);
					chckbxCarFleet.setVisible(false);
				}
				else
					chckbxCarFleet.setVisible(true);
			}
		});
		
		comboBox.setBounds(183, 74, 122, 20);
		
		panel.add(comboBox);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(60, 179, 113));
		panel_1.setBounds(10, 381, 404, 45);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		textField_6 = new JTextField();
		textField_6.setBounds(183, 326, 211, 20);
		textField_6.setEnabled(false);
		textField_6.setColumns(10);
		
		lblPass.setVisible(false);
		lblCreditCardNo.setVisible(false);
		textField_5.setVisible(false);
		textField_6.setVisible(false);
		
		panel.add(lblPass);
		panel.add(textField_6);
		panel.add(lblCreditCardNo);
		panel.add(textField_5);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhoneNumber.setBounds(10, 203, 130, 18);
		panel.add(lblPhoneNumber);
		
		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setVisible(false);
		textField_7.setBounds(183, 263, 211, 20);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		comboBox_1.setEnabled(false);
		comboBox_1.setVisible(false);
		comboBox_1.setBounds(183, 294, 211, 21);
		panel.add(comboBox_1);
		
		lblCreditCardDate.setBounds(10, 264, 109, 19);
		lblCreditCardDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCreditCardDate.setVisible(false);
		panel.add(lblCreditCardDate);
		
		lblPrefferedPaymentmethod.setBounds(10, 297, 163, 19);
		lblPrefferedPaymentmethod.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrefferedPaymentmethod.setVisible(false);
		panel.add(lblPrefferedPaymentmethod);
		
		textField_8 = new JTextField();
		textField_8.setEnabled(false);
		textField_8.setBounds(183, 43, 211, 20);
		panel.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblPrivateName = new JLabel("Private Name:");
		lblPrivateName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrivateName.setBounds(10, 46, 108, 21);
		panel.add(lblPrivateName);
		chckbxCarFleet.setBounds(311, 73, 83, 23);
		panel.add(chckbxCarFleet);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrevWindow.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(319, 11, 75, 23);
		panel_1.add(btnReturn);
		//NEW was pressed//
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			
			btnCar.setVisible(false);
			btnCar.setEnabled(false);
			btnSet.setText("Next");
			lblPass.setVisible(true);
			lblCreditCardNo.setVisible(true);
			textField_5.setVisible(true);
			textField_6.setVisible(true);
			lblCreditCardDate.setVisible(true);
			lblPrefferedPaymentmethod.setVisible(true);
			
			comboBox.removeAllItems();
			comboBox_1.removeAllItems();
			btnGet.setEnabled(false);
			btnSet.setEnabled(true);
			textField.setText("");
			textField.setEnabled(true);
			textField_1.setEnabled(true);
			textField_1.setText("");
			textField_2.setEnabled(true);	
			textField_2.setText("");
			textField_3.setEnabled(true);
			textField_3.setText("");
			textField_4.setEnabled(true);	
			textField_4.setText("");
			textField_5.setEnabled(true);
			textField_5.setText("");
			textField_6.setEnabled(true);
			textField_6.setText("");
			textField_7.setEnabled(true);
			textField_7.setText("");
			textField_7.setVisible(true);
			textField_8.setEnabled(true);
			textField_8.setText("");
			
			comboBox.addItem("Car Customer");
			comboBox.addItem("House Owner");
			comboBox_1.setEnabled(true);
			comboBox_1.setVisible(true);
			comboBox_1.addItem("Cash");
			comboBox_1.addItem("CreditCard");
			
			CustomerControl.loadNewActive();
			}
		});
		btnRegister.setBounds(10, 11, 65, 23);
		panel_1.add(btnRegister);
		//SET WAS PRESSED
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(btnSet.getText().equals("Next"))
					btnSet.setText("Set");
				btnGet.setEnabled(true);
				lblPass.setVisible(false);
				lblCreditCardNo.setVisible(false);
				textField_5.setVisible(false);
				textField_6.setVisible(false);
				textField_7.setVisible(false);
				textField_7.setEnabled(false);
				comboBox_1.setEnabled(false);
				comboBox_1.setVisible(false);
				lblCreditCardDate.setVisible(false);
				lblPrefferedPaymentmethod.setVisible(false);
				
				CustomerControl.getActive().setUserName(textField.getText());
				CustomerControl.getActive().setFirstName(textField_8.getText());
				CustomerControl.getActive().setAddress(textField_1.getText());
				CustomerControl.getActive().seteMail(textField_2.getText());
				CustomerControl.getActive().setLastName(textField_3.getText());
				CustomerControl.getActive().setPhoneNo(textField_4.getText());
				
				if(CustomerControl.getActive().getCustomerID()==0)//if new customer
				{
					List<User> users = new LinkedList<User>();
					users = CustomerControl.getAllUsers();
					int maxUID=0;
					for(User usl : users)
					{
						if(usl.getUserName().equals(CustomerControl.getActive().getUserName()))
						{
							btnGet.setEnabled(true);
							btnSet.setEnabled(false);
							JOptionPane.showMessageDialog(mainframe, "User Name Already Taken Please Chose A Diffrent One", "Error In UserName", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if(usl.getUserID()>maxUID)
							maxUID=usl.getUserID();
					}
					List<PaymentInfo> pis = new LinkedList<PaymentInfo>();
					pis=PaymentInfoControl.getAllPaymentInfos();
					int maxPI=-1;
					for(PaymentInfo p : pis)
						if(p.getPaymentInfoID()>maxPI)
							maxPI=p.getPaymentInfoID();
					
					CustomerControl.getActive().setCustomerID(maxUID+1);	
					CustomerControl.getActive().setUserID(maxUID+1);	
					
					
					if(comboBox.getSelectedItem().equals("Car Customer"))
					{
						if( chckbxCarFleet.isSelected())
							CustomerControl.getActive().setUserTypeID(9);
						else
							CustomerControl.getActive().setUserTypeID(2);
						CarCustomer c = new CarCustomer();
						c.setCarCustomerID(CustomerControl.getActive().getCustomerID());
						c.setPurchasePlanID(0);
						c.setUserID(CustomerControl.getActive().getCustomerID());
						c.setAddress(CustomerControl.getActive().getAddress());
						c.seteMail(CustomerControl.getActive().geteMail());
						c.setFirstName(CustomerControl.getActive().getFirstName());
						c.setLastName(CustomerControl.getActive().getLastName());
						c.setPhoneNo(CustomerControl.getActive().getPhoneNo());
						c.setUserTypeID(CustomerControl.getActive().getUserTypeID());
						c.setUserID(CustomerControl.getActive().getUserID());
						c.setUserName(CustomerControl.getActive().getUserName());
						
						CustomerControl.createCarCustomer(c);
						JOptionPane.showMessageDialog(mainframe, "Please Enter Car/Subscribtion Details  ", "Info", JOptionPane.INFORMATION_MESSAGE);
						new ModifyCustomerCarsScreen(mainframe,c.getCustomerID(),ModifyCustomerCarsScreen.CREATE);
						setVisible(false);
					}
					if(comboBox.getSelectedItem().equals("House Owner"))
					{
						CustomerControl.getActive().setUserTypeID(3);
						HouseOwner h = new HouseOwner();
						h.setHouseOwnerID(CustomerControl.getActive().getCustomerID());
						h.setCustomerID(CustomerControl.getActive().getCustomerID());
						h.setAddress(CustomerControl.getActive().getAddress());
						h.seteMail(CustomerControl.getActive().geteMail());
						h.setFirstName(CustomerControl.getActive().getFirstName());
						h.setLastName(CustomerControl.getActive().getLastName());
						h.setPhoneNo(CustomerControl.getActive().getPhoneNo());
						h.setUserTypeID(CustomerControl.getActive().getUserTypeID());
						h.setUserID(CustomerControl.getActive().getUserID());
						h.setUserName(CustomerControl.getActive().getUserName());
						
						CustomerControl.createHouseOwner(h);
						
					}
					
					Cash ch = new Cash();
					
					ch.setPaymentInfoID(maxPI+1);
					ch.setCashID(maxPI+1);
					
					ch.setCustomerID(CustomerControl.getActive().getCustomerID());
					
					if(comboBox_1.getSelectedItem().equals("Cash"))
						ch.setPrefferedMethod(0);//0 = cash is the preffered method
					if(comboBox_1.getSelectedItem().equals("Credit Card"))
					{
						if(textField_5.getText().equals("") || textField_7.getText().equals(""))
						{
							btnGet.setEnabled(true);
							btnSet.setEnabled(false);
							JOptionPane.showMessageDialog(mainframe, "No Credit Card Information Found", "Error Invalid Credit Card", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						else
						{
							ch.setPrefferedMethod(2);//2= credit card is the preffered method
						}
					}
					
					
					LoginCont.updateLogin(textField.getText(), textField_6.getText());
					PaymentInfoControl.createCash(ch);
					
					maxPI++;
					
					if(textField_5.getText().equals("") || textField_7.getText().equals(""))//empty for not adding creditcard
					{
						if(comboBox_1.getSelectedItem().equals("Credit Card"))
						{
							btnGet.setEnabled(true);
							btnSet.setEnabled(false);
							JOptionPane.showMessageDialog(mainframe, "No Credit Card Information Found", "Error Invalid Credit Card", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					}
					else{
						
						CreditCard ci = new CreditCard();
						
						ci.setPaymentInfoID(maxPI+1);
						ci.setCreditCardID(maxPI+1);
						ci.setCustomerID(CustomerControl.getActive().getCustomerID());
						
						if(comboBox_1.getSelectedItem().equals("Credit Card"))
							ci.setPrefferedMethod(2);
						if(comboBox_1.getSelectedItem().equals("Cash"))
							ci.setPrefferedMethod(0);

						ci.setCardNo(textField_5.getText());
						if(textField_7.getText().equals("") ||PeriodicDiscountsControl.checkDate(textField_7.getText()) == false)
						{
							btnGet.setEnabled(true);
							btnSet.setEnabled(false);
							JOptionPane.showMessageDialog(mainframe, "Invalid Credit Card Date - Please Retry with Correct Date", "Error Invalid Credit Card Date", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						ci.setValidDate(textField_7.getText());
						
						//LoginCont.updateLogin(textField.getText(), textField_6.getText());
						PaymentInfoControl.createCreditCard(ci);
					}
					//JOptionPane.showMessageDialog(mainframe, "Creating new customer Completed", "Operation Success", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else
				{
				JOptionPane.showMessageDialog(mainframe, "Updating Complete", "Operation Success", JOptionPane.INFORMATION_MESSAGE);
				CustomerControl.evictCustomer(CustomerControl.getActive());
				}
			}
		});
		
		btnSet.setEnabled(false);
		btnSet.setBounds(150, 11, 65, 23);
		panel_1.add(btnSet);
		
		
		//GET WAS PRESSED//
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				lblPass.setVisible(false);
				lblCreditCardNo.setVisible(false);
				textField_5.setVisible(false);
				textField_6.setVisible(false);
				
				btnSet.setEnabled(true);
				comboBox.removeAllItems();
				cusl = CustomerControl.getAllCustomers();
				Customer c = null;
				for (Customer cus : cusl)
				{
					if(cus.getUserName().equals(textField.getText()))
					{
						CustomerControl.setActive(cus);
						c=cus;
						break;			
					}
					
				}
				if (c==null)
				{
					btnGet.setEnabled(true);
					btnSet.setEnabled(false);
					JOptionPane.showMessageDialog(mainframe, "No Such Customer In the Database", "Error Getting Customer", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					textField.setEnabled(false);
					if(CustomerControl.getActive().getUserTypeID()==2 || CustomerControl.getActive().getUserTypeID()==9)
					{
						comboBox.addItem("Car Customer");
						btnCar.setVisible(true);
						btnCar.setEnabled(true);
					}
					if(CustomerControl.getActive().getUserTypeID()==3)
					{
						comboBox.addItem("House Owner");
						btnCar.setVisible(false);
						btnCar.setEnabled(false);
					}
					textField_1.setText(CustomerControl.getActive().getAddress());
					textField_1.setEnabled(true);
					textField_2.setText(CustomerControl.getActive().geteMail());
					textField_2.setEnabled(true);
					textField_3.setText(CustomerControl.getActive().getLastName());
					textField_3.setEnabled(true);
					textField_4.setText(CustomerControl.getActive().getPhoneNo());
					textField_4.setEnabled(true);
					textField_8.setText(CustomerControl.getActive().getFirstName());
					textField_8.setEnabled(true);
				}
				
			}
		});
		
		btnGet.setBounds(80, 11, 65, 23);
		panel_1.add(btnGet);
		//CAR WAS PRESSED TO EDIT CAR INFO//
		btnCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ModifyCustomerCarsScreen(mainframe, CustomerControl.getActive().getCustomerID(),ModifyCustomerCarsScreen.MODIFY);	
			}
		});
		
		btnCar.setVisible(false);
		btnCar.setEnabled(false);
		btnCar.setBounds(220, 11, 89, 23);
		panel_1.add(btnCar);
		setTitle("Customer Registration");
		
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setSize(440, 475);
	}
	public JFrame getPrevWindow() {
		return PrevWindow;
	}
	public void setPrevWindow(JFrame prevWindow) {
		PrevWindow = prevWindow;
	}
}
