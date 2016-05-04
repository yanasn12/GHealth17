package GUI;

import javax.swing.JFrame;

import java.awt.Font;
import java.awt.SystemColor;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import entities.Car;
import entities.FuelType;
import entities.Monthly;
import entities.MonthlyFull;
import entities.MonthlyMultiple;
import entities.MonthlySimple;
import entities.PaymentInfo;
import entities.Subscription;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import controllers.CustomerControl;
import controllers.FuelTypeControl;
import controllers.PaymentInfoControl;
import controllers.SubscriptionControl;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


/**
 * GUI interface to add cars and change there car information concerning the carCustomer
 * Car Customer can have any amount of car he chooses.
 * On the building of the GUI the constructor will scan the Car table and depends on if the
 * Car Customer is already in the car table then we only add a new car (if AddNew was pressed)
 * or update the car. if the customer is not in the car table then we create a new subscribtion and
 * new car for the customer.
 * 
 * @author Sergei Kovalchuck
 */

public class ModifyCustomerCarsScreen extends JFrame{

	/**
	 * The working mode of this screen is creation of a new user.
	 */
	public static final int CREATE = 0;
	/**
	 * The working mode of this screen is modification of existing user.
	 */
	public static final int MODIFY = 1;
	
	private static final long serialVersionUID = -8983706853412461599L;
	private JFrame PrevWindow;
	private JFrame mainframe;
	private JButton btnAddNew = new JButton("Add New");
	private JButton btnFinish = new JButton("Finish");
	private JButton btnSet = new JButton("Set");
	private JComboBox<Integer> comboBox = new JComboBox<Integer>();
	private JComboBox<String> comboBox_1 = new JComboBox<String>();
	private JComboBox<String> comboBox_2 = new JComboBox<String>();
	private boolean isNew = false;
	private int cusID;
	private Car ct = null;
	private int mode;
	private int maxSID=-1;
	private int maxCID=-1;
	
	public ModifyCustomerCarsScreen(JFrame pw,int carCustID, int wMode)
	{

		this.cusID=carCustID;
		this.setMainframe(this);
		this.setPrevWindow(pw);
		this.mode = wMode;
		
		List<Car> customerCars = new LinkedList<Car>();
		
		List<Car> cl = null;
		cl = CustomerControl.getAllCars();
		maxCID=-1;
		for(Car c : cl)
		{
			if(c.getCustomerID()==carCustID)
			{
				ct=c;
				customerCars.add(c);
			}
			if(c.getCarID()>maxCID)
				maxCID=c.getCarID();
		}
		
		setTitle("Modify Car Customer Cars");
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 11, 280, 113);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCarId = new JLabel("Car ID:");
		lblCarId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCarId.setBounds(10, 11, 80, 21);
		panel.add(lblCarId);
		
		comboBox.setBounds(100, 12, 169, 20);
		panel.add(comboBox);
		
		JLabel lblSub = new JLabel("Subscription:");
		lblSub.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSub.setBounds(10, 41, 80, 21);
		panel.add(lblSub);
		comboBox_1.setEnabled(false);
		
		comboBox_1.setBounds(100, 43, 169, 20);
		panel.add(comboBox_1);
		
		comboBox_2.setBounds(100, 74, 170, 20);
		panel.add(comboBox_2);
		
		JLabel lblFuelTypeId = new JLabel("Fuel Type ID:");
		lblFuelTypeId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFuelTypeId.setBounds(10, 77, 80, 17);
		panel.add(lblFuelTypeId);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 135, 280, 50);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		//FINISH WAS PRESSED
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (mode == CREATE)
				{
					JOptionPane.showMessageDialog(mainframe, "Please Choose Purchase Plan - note that not chosing one will revert to purchase plan being the default : exclusive purchase plan", "Info", JOptionPane.INFORMATION_MESSAGE);
					new MatchPlanToCustomerScreen(PrevWindow,CustomerControl.getCarCustomerById(cusID).getUserName());
				}
				else
					PrevWindow.setVisible(true);
				mainframe.setVisible(false);
				dispose();
			}
		});
		
		btnFinish.setEnabled(false);
		btnFinish.setBounds(201, 13, 69, 24);
		panel_1.add(btnFinish);
		//SET WAS PRESSED
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBox.setEnabled(true);
				btnFinish.setEnabled(true);
				
				if(isNew==true)//if we are adding a new car
				{
					Car nc = new Car();
					nc.setCarID(maxCID+1);
					nc.setCustomerID(cusID);
					
					List<Subscription>  sl = SubscriptionControl.getAllSubscriptions();
					maxSID=-1;
					for(Subscription s : sl)
						if(s.getSubscriptionID()>maxSID)
							maxSID=s.getSubscriptionID();
					
					List<FuelType> ftl = new LinkedList<FuelType>();
					ftl = FuelTypeControl.getAllFuelTypes();
					for(FuelType f : ftl)
						if (f.getFuelName() == comboBox_2.getSelectedItem())
							nc.setFuelTypeID(f.getFuelTypeID());

					if(comboBox_1.getSelectedItem().equals("None"))
						nc.setSubscriptionID(0);
					else
					{
						if(comboBox_1.getSelectedItem().equals("Monthly Simple"))
						{
							MonthlySimple ms = new MonthlySimple();
							ms.setDiscountTableID(0);
							ms.setSubID(maxSID+1);
							SubscriptionControl.createMonthlySimple(ms);
							nc.setSubscriptionID(maxSID+1);
						}
						if(comboBox_1.getSelectedItem().equals("Monthly Full"))
						{
							MonthlyFull mf = new MonthlyFull();
							mf.setDiscountTableID(0);
							mf.setSubID(maxSID+1);
							SubscriptionControl.createMonthlyFull(mf);
							
							List<PaymentInfo> pil = PaymentInfoControl.getAllPaymentInfos();
							int maxPID=-1;
							for(PaymentInfo p : pil)
							{
								if(p.getPaymentInfoID()>maxPID)
									maxPID=p.getPaymentInfoID();
								if(p.getCustomerID()==cusID)
								{
									p.setPrefferedMethod(1);
									PaymentInfoControl.updatePaymentInfo(p.getPaymentInfoID());
									PaymentInfoControl.evictPaymentInfo(p.getPaymentInfoID());
								}
							}
							
							Monthly m = new Monthly();
							m.setCustomerID(cusID);
							m.setPrefferedMethod(1);
							m.setPaymentInfoID(maxPID+1);
							PaymentInfoControl.createMonthly(m);
							nc.setSubscriptionID(maxSID+1);
							
						}
						if(comboBox_1.getSelectedItem().equals("Monthly Multiple"))
						{
							List<MonthlyMultiple> mml = SubscriptionControl.getAllMonthlyMultiples();
							List<Car> cl = CustomerControl.getAllCars();
							ct=null;
							for(Car c : cl)
								if(c.getCustomerID()==cusID)
									for(MonthlyMultiple m : mml)
										if(m.getSubscriptionID()==c.getSubscriptionID())
											ct=c;
							if(ct==null)//1st car of sub multiple for the car customer
							{	
								MonthlyMultiple mm = new MonthlyMultiple();
								mm.setDiscountTableID(0);
								mm.setSubID(maxSID+1);
								mm.setNumOfCars(1);
								SubscriptionControl.createMonthlyMultiple(mm);
								nc.setSubscriptionID(maxSID+1);
							}
							else//the customer already has a multiple subscribtion
							{
								MonthlyMultiple mm = null;
								List<Car> customerCars = new LinkedList<Car>();

								for(Car c : cl)
									if(c.getCustomerID()==cusID)
										customerCars.add(c);
								
								for(Car c: customerCars)
								{
									boolean eflag=false;
									for(MonthlyMultiple m : mml)
										if(m.getSubID()==c.getSubscriptionID())
										{
											mm = SubscriptionControl.getMonthlyMultipleById(m.getSubID());//must find this eventually
											eflag=true;
										}
									if(eflag==true)
										break;
								}
								mm.setNumOfCars((mm.getNumOfCars()+1));
								SubscriptionControl.updateMonthlyMultiple(mm.getSubID());
								SubscriptionControl.evictMonthlyMultiple(mm.getSubID());
								nc.setSubscriptionID(mm.getSubID());
							}
						}
					}
					CustomerControl.createCar(nc);
					comboBox_1.setEnabled(false);
					JOptionPane.showMessageDialog(mainframe, "Creating Car Completed", "Operation Success", JOptionPane.INFORMATION_MESSAGE);
				}
				else//if we are modifying an exsiting car
				{
					comboBox_1.setEnabled(false);
					Car uc = CustomerControl.getCarById((int)comboBox.getSelectedItem());
					
					List<FuelType> ftl = new LinkedList<FuelType>();
					ftl = FuelTypeControl.getAllFuelTypes();
					for(FuelType f : ftl)
						if (f.getFuelName() == comboBox_2.getSelectedItem())
							uc.setFuelTypeID(f.getFuelTypeID());
					CustomerControl.updateCar(uc.getCarID());
					CustomerControl.evictCar(uc.getCarID());
					JOptionPane.showMessageDialog(mainframe, "Updating Car Completed", "Operation Success", JOptionPane.INFORMATION_MESSAGE);					
				}
				btnAddNew.setEnabled(true);
				btnAddNew.setVisible(true);
			}
		});
		
		btnSet.setBounds(10, 14, 69, 23);
		panel_1.add(btnSet);
		
		//ADD NEW WAS PRESSED
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				isNew=true;
				
				List<Car> cl = CustomerControl.getAllCars();
				maxCID=-1;
				for(Car c : cl)
					if(c.getCarID()>maxCID)
						maxCID=c.getCarID();
				
				comboBox.setEnabled(false);
				comboBox.addItem(maxCID+1);
				comboBox.setSelectedItem(maxCID+1);
				
				List<Subscription>  sl = SubscriptionControl.getAllSubscriptions();
				maxSID=-1;
				for(Subscription s : sl)
					if(s.getSubscriptionID()>maxSID)
						maxSID=s.getSubscriptionID();
				
				comboBox_1.setEnabled(true);
				comboBox_2.setEnabled(true);				
			}
		});
		//comboBox of carID's changed
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				
				 	int id = (int) event.getItem();

		            Car ct = null;
		            ct=CustomerControl.getCarById(id);
		            
		            List<MonthlyFull> mfl = SubscriptionControl.getAllMonthlyFulls();
					List<MonthlySimple> msl = SubscriptionControl.getAllMonthlySimples();
					List<MonthlyMultiple> mml = SubscriptionControl.getAllMonthlyMultiples();
					
					boolean nonFlag=true;
					
					for(MonthlySimple ms : msl)
						if(ms.getSubscriptionID()==ct.getSubscriptionID())
						{
							comboBox_1.setSelectedItem("Monthly Simple");
							nonFlag=false;
						}
					for(MonthlyMultiple mm : mml)
						if(mm.getSubscriptionID()==ct.getSubscriptionID())
						{
							comboBox_1.setSelectedItem("Monthly Multiple"); 
							nonFlag=false;
						}
					for(MonthlyFull mf : mfl)
						if(mf.getSubscriptionID()==ct.getSubscriptionID())
						{
							comboBox_1.setSelectedItem("Monthly Full");
							nonFlag=false;
						}
					if(nonFlag==true)
						comboBox_1.setSelectedItem("None");
					
					List<FuelType> ftl = new LinkedList<FuelType>();
					ftl = FuelTypeControl.getAllFuelTypes();
					for(FuelType f : ftl)
						if (f.getFuelTypeID()==ct.getFuelTypeID())
							comboBox_2.setSelectedItem(f.getFuelName());
			}
		});
		
		btnAddNew.setEnabled(false);
		btnAddNew.setVisible(false);
		btnAddNew.setBounds(89, 14, 102, 23);
		panel_1.add(btnAddNew);
		
		if(ct==null)//if we didnt find a car with the customerID = new customer and new car
		{
			isNew=true;
			comboBox.setEnabled(false);
			comboBox.addItem(maxCID+1);
			comboBox_2.setEnabled(true);
			comboBox_1.setEnabled(true);
			btnAddNew.setEnabled(false);
		}
		
		else//the customer already has a car in the database = change car info for existing customer
		{
			isNew=false;
			comboBox.setEnabled(true);
			for(Car c : customerCars)
				comboBox.addItem(c.getCarID());
			
			comboBox_2.setEnabled(true);		
			comboBox_2.setSelectedItem(ct.getFuelTypeID());
			btnAddNew.setEnabled(true);
			btnAddNew.setVisible(true);
			btnFinish.setEnabled(true);
		}
		
		List<FuelType> ft = new LinkedList<FuelType>();
		ft = FuelTypeControl.getAllFuelTypes();
		for(FuelType f : ft)
			if (f.getFuelTypeID() != FuelTypeControl.HOUSE_FUEL_ID)
				comboBox_2.addItem(f.getFuelName());

		comboBox_1.removeAllItems();
		comboBox_1.addItem("Monthly Full");
		comboBox_1.addItem("Monthly Multiple");
		comboBox_1.addItem("Monthly Simple");
		comboBox_1.addItem("None");
		
		this.setSize(315,235);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	
	
	//GETTERS AND SETTERS//
	
	public JFrame getPrevWindow() {
		return PrevWindow;
	}

	public void setPrevWindow(JFrame prevWindow) {
		PrevWindow = prevWindow;
	}

	public JFrame getMainframe() {
		return mainframe;
	}

	public void setMainframe(JFrame mainframe) {
		this.mainframe = mainframe;
	}
}
