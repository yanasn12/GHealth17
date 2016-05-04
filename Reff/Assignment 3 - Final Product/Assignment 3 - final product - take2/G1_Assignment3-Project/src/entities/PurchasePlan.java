package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import controllers.CustomerControl;
import controllers.InvoiceControl;

/**
 *
 * This class stores info about a PurchasePlan agreement that exists within the company as well as the discount it grants
 *
 */

public class PurchasePlan implements Serializable {
	
	private static final long serialVersionUID = -2807903913285452369L;

	int purchasePlanID;
	
	String planName;
	double discount;
	String agreementDetails;
	
	// Contractor //
	
	public PurchasePlan (){
		setPurchasePlanID(-1);
	}
	
	
	// setters and getters //
	
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getAgreementDetails() {
		return agreementDetails;
	}
	public void setAgreementDetails(String agreenentDetails) {
		this.agreementDetails = agreenentDetails;
	}

	public int getPurchasePlanID() {
		return purchasePlanID;
	}

	public void setPurchasePlanID(int purchasePlanID) {
		this.purchasePlanID = purchasePlanID;
	}
	/**
	 * 
	 * This method is getting a CarCustomer information. 
	 * the method checks if the CarCustomer has the specific PurchasePlanID and returns a table
	 * 
	 * @return res  A list of CarCustomer that has the specific PurchasePlanID 
	 */
	public List<CarCustomer> getCarCustomers ()
	{
		LinkedList <CarCustomer> res = new LinkedList <CarCustomer> ();
		
		for (CarCustomer item : CustomerControl.getAllCarCustomers())
		{
			if (item.getPurchasePlanID() == purchasePlanID)
				res.add(item);
		}
		return res;
	}
	
	/**
	 * 
	 * This method is getting a CarCustomer information.
	 * the method checks if the Invoice has the specific PurchasePlanID and returns a table
	 * 
	 * @return res  A list of Invoices that has the specific PurchasePlanID 
	 */
	public List<Invoice> getInvoices ()
	{
		LinkedList<Invoice> res = new LinkedList<Invoice>();
		
		for (Invoice item : InvoiceControl.getAllInvoices())
		{
			if (item.getPurchasePlanID() == purchasePlanID)
				res.add(item);
		}
		return res;
	}
}
