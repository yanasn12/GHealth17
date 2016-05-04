package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import controllers.InvoiceControl;
import controllers.PaymentInfoControl;

/**
 * 
 * Class that describes the relevant information about the customer
 * 
 */

public class Customer extends User implements Serializable {
	
	private static final long serialVersionUID = 8006177389460084217L;
	
	int customerID;
	String Address;
	
	// Constructor //
	public Customer(){
		//need TBD
	}
		
	// setters and getters //
	
	public String getAddress() { 
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	public int getCustomerID() {
		return userID;
	}
	public void setCustomerID(int customerID) {
		this.userID = customerID;
	}
	/**
	 * method to get all payment infos for the current customer.
	 * the payment infos stored in a LinkedList.
	 * @return
	 */
	public List<PaymentInfo> getPaymentInfos() {
		LinkedList<PaymentInfo> res = new LinkedList <PaymentInfo> ();
		for (PaymentInfo item : PaymentInfoControl.getAllPaymentInfos())
		{
			if (item.getCustomerID() == userID)
				res.add(item);
		}
		return res;
	}
	/**
	 * method to get all the invoices of the current customer.
	 * relevant invoices are stored in a list and returned.
	 * @return
	 */
	public List<Invoice> getInvoices() {
		LinkedList<Invoice> res = new LinkedList<Invoice> ();
		for (Invoice item : InvoiceControl.getAllInvoices())
		{
			if (item.getCustomerID() == userID)
				res.add (item);
		}
		return res;
	}
}
