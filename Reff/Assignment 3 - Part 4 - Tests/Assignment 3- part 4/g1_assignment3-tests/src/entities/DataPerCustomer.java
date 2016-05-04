package entities;

import java.io.Serializable;

import controllers.CustomerControl;
import controllers.PeriodicCustomerReportControl;

/**
 * 
 * This class stores orderCount about customer and it is for class - periodic Customer Report.
 * using this class periodic customer report creates data on every user that is a custoer.
 * the view report button will present the report detatils and all the relevant data on the customers.
 */

public class DataPerCustomer implements Serializable {
	
	private static final long serialVersionUID = -407700552726231856L;
	
	int dataPerCustomerID;
	int orderCount;
	int customerID;
	int reportID;
	Customer customer;
	PeriodicCustomerReport periodicCustomerReport;
	
	// Constructor //
	
	public DataPerCustomer(){
		customer = CustomerControl.dummyCustomer;
		periodicCustomerReport = PeriodicCustomerReportControl.dummyPeriodicCustomerReport;
	}
	
	// setter and getter //

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getDataPerCustomerID() {
		return dataPerCustomerID;
	}

	public void setDataPerCustomerID(int dataPerCustomerID) {
		this.dataPerCustomerID = dataPerCustomerID;
	}
	/**
	 * checks if the Customer reference is a dummy one, and if it is, gets the Customer info into it.
	 * good to prevent multiple  database access.
	 * @return
	 */
	public Customer getCustomer() {
		if (customer == CustomerControl.dummyCustomer)
			customer = CustomerControl.getCustomerById(customerID);
		return customer;
	}
	/**
	 * method to attach a customer to its data. 
	 * checking if the Customer reference isn't a dummy(meaning if there is a Customer info in this reference)
	 * if it isn't a dummy the method attaches a Customer to his data.
	 * @param Customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
		if (customer != CustomerControl.dummyCustomer)
			this.customerID=customer.getCustomerID();
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}
	/**
	 * method to get the relevant Periodic Customer report.
	 * searching the report and returns it.
	 * @return
	 */
	public PeriodicCustomerReport getPeriodicCustomerReport() {
		if (periodicCustomerReport == PeriodicCustomerReportControl.dummyPeriodicCustomerReport)
			periodicCustomerReport = PeriodicCustomerReportControl.getPeriodicCustomerReportById(reportID);
		return periodicCustomerReport;
	}
	/**
	 * method to set a PeriodicCustomer Report for this data.
	 * checks if the report isnt a dummy and if it isnt, places the report id.
	 * with that id we can pull the report info later.
	 * @param periodicCustomerReport
	 */
	public void setPeriodicCustomerReport(PeriodicCustomerReport periodicCustomerReport) {
		this.periodicCustomerReport = periodicCustomerReport;
		if (periodicCustomerReport != PeriodicCustomerReportControl.dummyPeriodicCustomerReport)
			reportID = periodicCustomerReport.getReportID();
	}
	
}
