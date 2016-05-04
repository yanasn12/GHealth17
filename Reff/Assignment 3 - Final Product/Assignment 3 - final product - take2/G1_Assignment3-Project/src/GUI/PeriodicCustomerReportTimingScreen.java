package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import controllers.CustomerControl;
import controllers.GeneratedReportControl;
import controllers.OrderControl;
import controllers.PeriodicCustomerReportControl;
import controllers.PeriodicDiscountsControl;
import entities.Customer;
import entities.DataPerCustomer;
import entities.Invoice;
import entities.Order;
import entities.PeriodicCustomerReport;
import entities.Report;
import entities.ScorePerFuelType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;

public class PeriodicCustomerReportTimingScreen extends JFrame {
	/**
	 * Screen to produce a periodic customer Report, creates Data Per Customer for every user that is a customer 
	 * adds it to report and can be viewed by clicking  "view Reports" buttons
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JFrame prevScreen;
	long startValue;
	long endValue;
	protected PeriodicCustomerReport periodicReport;
	
	public PeriodicCustomerReportTimingScreen(JFrame prevScreen, PeriodicCustomerReport report) {
		setBackground(new Color(255, 255, 255));
		getContentPane().setBackground(SystemColor.scrollbar);
		periodicReport = report;
		this.prevScreen=prevScreen;
		setTitle("Time Period");
		getContentPane().setLayout(null);
		
		JLabel lblStartTime = new JLabel("Start Time:");
		lblStartTime.setBounds(38, 30, 77, 23);
		getContentPane().add(lblStartTime);
		
		textField = new JTextField();
		textField.setBounds(111, 31, 168, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setBounds(38, 90, 77, 23);
		getContentPane().add(lblEndTime);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(111, 91, 168, 20);
		getContentPane().add(textField_1);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPrevScreen().setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(26, 142, 89, 23);
		getContentPane().add(btnReturn);
		
		JButton btnNext = new JButton("Generate");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int nextDataPerCustomerID=0;
				
				DataPerCustomer data;
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
					JOptionPane.showMessageDialog(null, "Invalid Input For Date");
					return;
				}
				long ordertime;
				int count;
				PeriodicCustomerReportControl.createPeriodicCustomerReport(periodicReport);
				for (DataPerCustomer d : PeriodicCustomerReportControl.getAllDatasPerCustomer())
				{
					if (d.getDataPerCustomerID() > nextDataPerCustomerID)
						nextDataPerCustomerID = d.getDataPerCustomerID();
				}
				nextDataPerCustomerID++;
				for (Customer customer : CustomerControl.getAllCustomers())
				{
					data = new DataPerCustomer();
					data.setDataPerCustomerID(nextDataPerCustomerID);
					nextDataPerCustomerID++;
					
					data.setCustomerID(customer.getUserID());
					data.setReportID(periodicReport.getReportID());
					count = 0;
					for (Invoice invoice : customer.getInvoices())
					{
						for(Order order : invoice.getOrders())
						{
							ordertime=PeriodicDiscountsControl.getDateNumericValue(order.getOrderTime().substring(0,10));
							if(ordertime>=startValue&&ordertime<endValue)
								count ++;
					
						}
					}
					data.setOrderCount(count);
					PeriodicCustomerReportControl.createDataPerCustomer(data);
				}
				JOptionPane.showMessageDialog(null,"Generate Successfull! ReportID:"+periodicReport.getReportID());
				getPrevScreen().setVisible(true);
				dispose();
			}
			
		});
		btnNext.setBounds(299, 142, 89, 23);
		getContentPane().add(btnNext);
		
		this.setSize(426, 227);
		this.setVisible(true);
	}
	public JFrame getPrevScreen() {
		return this.prevScreen;
	}

}
