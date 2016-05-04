package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import controllers.CarFuelOrderControl;
import controllers.FuelTypeControl;
import controllers.LoginCont;
import controllers.PeriodicDiscountsControl;
import controllers.PurchasesReportControl;
import controllers.StockReportControl;
import entities.AmountDataPerFuel;
import entities.CarFuelOrder;
import entities.FuelType;
import entities.PurchasesReport;
import entities.Worker;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * purchases Report Screen, the report presents the quantity of CarFuelOrders for each fuel type in the Supply.
 * a AmountDataPerFuel class is created for each FuelType. 
 * the report can be presented in the "View Report" button.
 */
public class PurchasesReportTimingScreen extends JFrame{
	private static final long serialVersionUID = -328789648984030621L;
	private JTextField textField;
	private JTextField textField_1;
	private JFrame prevScreen;
	long startValue;
	long endValue;
	long ordertime;
	PurchasesReport pr;
	public PurchasesReportTimingScreen(JFrame prevScreen, PurchasesReport report) {
		getContentPane().setBackground(new Color(210, 180, 140));
		setTitle("Purchases Report Timing");
		getContentPane().setLayout(null);
		this.pr=report;
		this.prevScreen=prevScreen;
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(79, 41, 69, 26);
		getContentPane().add(lblStartDate);
		
		textField = new JTextField();
		textField.setBounds(158, 41, 178, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(158, 102, 178, 26);
		getContentPane().add(textField_1);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(79, 102, 69, 26);
		getContentPane().add(lblEndDate);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
			String startTime=new String(textField.getText());
			String endTime=new String(textField_1.getText());
			Worker w=(Worker) LoginCont.getCurrUser();
			AmountDataPerFuel adpf;
			int quantity=0;
			int nextAmountDataPerFuelID=0;
			for (AmountDataPerFuel d :StockReportControl.getAllAmountDatasPerFuel() )
			{
				if (d.getAmountDataPerFuelID() > nextAmountDataPerFuelID)
					nextAmountDataPerFuelID = d.getAmountDataPerFuelID();
			}
			nextAmountDataPerFuelID++;
			if(PeriodicDiscountsControl.checkDate(startTime)==false||PeriodicDiscountsControl.checkDate(endTime)==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Input For Date");
				return;
			}
			try{
				 startValue=PeriodicDiscountsControl.getDateNumericValue(startTime);
				 endValue=PeriodicDiscountsControl.getDateNumericValue(endTime);
				}
				catch(StringIndexOutOfBoundsException e1)
				{
					JOptionPane.showMessageDialog(null, "Wrong Date Input!");
					return;
				}
				pr.setPurchasesStationID(w.getLocationID());
				PurchasesReportControl.createPurchasesReport(pr);
			for(FuelType ft : FuelTypeControl.getAllFuelTypes())
			{
				for(CarFuelOrder cfo : CarFuelOrderControl.getAllCarFuelOrders())
				{
					ordertime=PeriodicDiscountsControl.getDateNumericValue(cfo.getOrderTime().substring(0,10));
					if(w.getLocationID()==cfo.getStationID()&&(ordertime>=startValue&&ordertime<=endValue)&&cfo.getFuelTypeID()==ft.getFuelTypeID())
					{
						quantity+=(int)(cfo.getQuantity()+0.5);
					}
					
				}
				adpf= new AmountDataPerFuel();
				adpf.setAmountDataPerFuelID(nextAmountDataPerFuelID);
				adpf.setFuelTypeID(ft.getFuelTypeID());
				adpf.setAmount(quantity);
				adpf.setReportID(pr.getReportID());
				StockReportControl.createAmountDataPerFuel(adpf);
				nextAmountDataPerFuelID++;
				quantity=0;
			}
			JOptionPane.showMessageDialog(null,"Generate Successfull! ReportID:"+pr.getReportID());
			getPrevScreen().setVisible(true);
			dispose();	
			}
			
		});
		btnGenerate.setBounds(315, 165, 96, 34);
		getContentPane().add(btnGenerate);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPrevScreen().setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(23, 165, 96, 34);
		getContentPane().add(btnReturn);
		this.setSize(459, 254);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}
	public JFrame getPrevScreen() {
		return this.prevScreen;
	}
}
