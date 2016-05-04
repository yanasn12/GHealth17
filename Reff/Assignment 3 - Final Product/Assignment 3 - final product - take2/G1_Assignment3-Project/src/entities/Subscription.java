package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import controllers.CustomerControl;
import controllers.DiscountTableControl;
import controllers.InvoiceControl;
/**
 * 
 * This class contains the information on the type of subscription which type and its details
 *
 */

public class Subscription implements Serializable{
	
	private static final long serialVersionUID = 7585867391820146622L;
	int subscriptionID;
	DiscountTable discountTable;
	int discountTableID;
	
	// Contractor 
	public Subscription(){
		discountTable = DiscountTableControl.dummyDiscountTable;
	}
	
	
	// setters and getters
	public int getSubID()
	{	
		return this.subscriptionID;
	}

	public void setSubID(int subID)
	{
		this.subscriptionID=subID;
	}
	public int getSubscriptionID()
	{
		
		return this.subscriptionID;
	}

	public void setSubscriptionID(int subscriptionID)
	{
		this.subscriptionID= subscriptionID;
	}
		
	public void setDiscountTableID(int id)
	{
		this.discountTableID=id;
	}
	
	public int getDiscountTableID()
	{
		return this.discountTableID;
	}
	/**
	 * This method to get a discountTable information.
	 * the method checks if the discountTable information exists is a dummy reference
	 * if it is, get the discountTable information from the DataBase and sets it in the discountTable reference
	 * 
	 * @return discountTable The discount table that needed
	 */
	public DiscountTable getDiscountTable() {
		if (discountTable == DiscountTableControl.dummyDiscountTable)
			discountTable = DiscountTableControl.getDiscountTableById(discountTableID);
		return discountTable;
	}
	/**
	 * This method to set a discountTable information.
	 * the method checks if the discountTable information exists is a dummy reference
	 * if it is, get the discountTable information from the DataBase and sets it in the discountTable reference
	 * 
	 * @param subDiscTable The input discount table that need to be set
	 */

	public void setDiscountTable(DiscountTable subDiscTable) {
		this.discountTable = subDiscTable;
		if (discountTable != DiscountTableControl.dummyDiscountTable)
			this.discountTableID = subDiscTable.getDiscountTableID();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	// methods
	public String toString()
	{
		return new String("Id: "+getSubID()+"Subscription Table ID:"+getDiscountTableID());
	}
	public double applyDiscount(double base_price)
	{
		return base_price;
	}
	
	/**
	 * 
	 * This method brings all the cars that has specific subscriptionID, it is needed for multiple Subscription
	 * 
	 * @return List of cars that has the specific subscriptionID
	 */
	public List<Car> getCars ()
	{
		LinkedList <Car> res = new LinkedList <Car> ();
		
		for (Car item : CustomerControl.getAllCars())
		{
			if (item.getSubscriptionID() == subscriptionID)
				res.add(item);
		}
		return res;
	}
	/**
	 * 
	 * This method brings all the invoices that has specific subscriptionID, it is needed for multiple Subscription
	 * 
	 * @return List of cars that has the specific subscriptionID
	 */
	public List<Invoice> getInvoices ()
	{
		LinkedList <Invoice> res = new LinkedList<Invoice>();
		
		for (Invoice item : InvoiceControl.getAllInvoices())
		{
			if (item.getSubscriptionID() == subscriptionID)
				res.add(item);
		}
		return res;
	}
}
