package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import controllers.CarFuelOrderControl;
import controllers.IncomeReportControl;
import controllers.LoginCont;
import controllers.PeriodicDiscountsControl;
import entities.CarFuelOrder;
import entities.IncomeReport;
import entities.Worker;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Income Report Timing Screen, the report presents for a time period inputed by the user the amount of money the
 * station had earned in that time period.
 *
 */
public class IncomeReportTimingScreen extends JFrame{
	
	private static final long serialVersionUID = 4980124289172948383L;
	private JTextField textField;
	private JTextField textField_1;
	private JFrame prevScreen;
	IncomeReport r;
	long startValue;
	long endValue;
	long orderTime;
	public IncomeReportTimingScreen(JFrame prevScreen, IncomeReport report) {
		setTitle("Income Timing");
		this.prevScreen=prevScreen;
		this.r=report;
		getContentPane().setLayout(null);
		
		JLabel lblStartDate = new JLabel("Start Date: ");
		lblStartDate.setBounds(35, 35, 68, 27);
		getContentPane().add(lblStartDate);
		
		textField = new JTextField();
		textField.setBounds(110, 38, 178, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End Date: ");
		lblEndDate.setBounds(35, 90, 68, 27);
		getContentPane().add(lblEndDate);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(110, 93, 178, 20);
		getContentPane().add(textField_1);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPrevScreen().setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(14, 164, 89, 23);
		getContentPane().add(btnReturn);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				double sum=0;
				String startTime=new String(textField.getText());
				String endTime=new String(textField_1.getText());
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
				
				Worker w= (Worker) LoginCont.getCurrUser();
				r.setIncomeStationID(w.getLocationID());
				for(CarFuelOrder or: CarFuelOrderControl.getAllCarFuelOrders())
				{
					orderTime=PeriodicDiscountsControl.getDateNumericValue(or.getOrderTime().substring(0,10));
					if(orderTime>=startValue && orderTime<endValue && or.getStationID() == r.getIncomeStationID())
					{
						sum+=or.getPrice();
					}
				}
				r.setTotalIncome(sum);
				IncomeReportControl.createIncomeReport(r);
				JOptionPane.showMessageDialog(null, "Income Report Successfull!, ReportID:"+r.getReportID());
				getPrevScreen().setVisible(true);
				dispose();
			}
		});
		btnGenerate.setBounds(324, 164, 89, 23);
		getContentPane().add(btnGenerate);
		this.setSize(454, 238);
		this.setVisible(true);
	}
	public JFrame getPrevScreen() {
		return this.prevScreen;
	}

}
