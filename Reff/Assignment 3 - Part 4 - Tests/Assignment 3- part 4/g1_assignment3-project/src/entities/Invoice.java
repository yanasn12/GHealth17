package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import controllers.CustomerControl;
import controllers.InvoiceControl;
import controllers.OrderControl;
import controllers.PurchasePlanControl;
import controllers.SubscriptionControl;

/**
 * 
 *  This class represents all the information about invoice that produced for customer that made a purchase fuel.
 *
 */

public class Invoice implements Serializable {
	
	private static final long serialVersionUID = -1685789357905994353L;
	
	 int invoiceID;            //invoiceNo
	 double totalPrice;
	 String dueDate;
	 int customerID;
	 Customer customer;
	 int purchasePlanID;
	 PurchasePlan purchasePlan;
	 int subscriptionID;
	 Subscription subscription;
	 int status;
	 
	 // constructor //
	 
	 public Invoice (){
		 customer = CustomerControl.dummyCustomer;
		 purchasePlan = PurchasePlanControl.dummyPurchasePlan;
	 }
	 
	 
	 // setter and getter //
	 
	public int getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(int invoiceNo) {
		this.invoiceID = invoiceNo;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * method that returns a specific invoice. 
	 * 
	 * @return
	 */
	public Receipt getReceipt() {
		List<Receipt> items = getReceipts();
		if (items.size() != 0)
			return items.get(0);
		return null;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	/**
	 * method to get the customer information the invoice relates to.
	 * if the customer reference is a dummy, the method pulls the customers information from the database and puts it in the reference.
	 * @return
	 */
	public Customer getCustomer() {
		if (customer == CustomerControl.dummyCustomer)
			customer = CustomerControl.getCustomerById(customerID);
		return customer;
	}
	/**
	 * method to attach a customer to the current invoice,
	 * the method checks the customer reference and if it isnt a dummy sets a customer id .
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
		if (customer != CustomerControl.dummyCustomer)
			this.customerID=customer.getCustomerID();
	}

	public int getPurchasePlanID() {
		return purchasePlanID;
	}

	public void setPurchasePlanID(int purchasePlanID) {
		this.purchasePlanID = purchasePlanID;
	}
	/**
	 * method to get the purchase plan related to the current invoice.
	 * the method checks if the current purchase plan reference is a dummy and if it is, gets the information about the purchase plan and sets in the reference.
	 * @return
	 */
	public PurchasePlan getPurchasePlan() {
		if (purchasePlan == PurchasePlanControl.dummyPurchasePlan)
			purchasePlan = PurchasePlanControl.getPurchasePlanById(purchasePlanID);
		return purchasePlan;
	}
	/**
	 * method to change a purchase plan reference by changing its id.
	 * method checks if the purchase plan reference isn't a dummy and if it isn't sets a different purchase plan id.
	 * @param purchasePlan
	 */
	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
		if (purchasePlan != PurchasePlanControl.dummyPurchasePlan)
			this.purchasePlanID=purchasePlan.getPurchasePlanID();
	}

	public int getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(int subscriptionID) {
		this.subscriptionID = subscriptionID;
	}
	/**
	 * method to get the subscription that relates to the current invoice,
	 * the method checks if the reference existed is a dummy and if it is,
	 * gets the information about the subscription from the Database and sets it in the reference and returns it.
	 * @return
	 */
	public Subscription getSubscription() {
		if (subscription == SubscriptionControl.dummySubscription)
			subscription = SubscriptionControl.getSubscriptionById(subscriptionID);
		return subscription;
	}
	/**
	 * method to change a subscription info to the current invoice,
	 * the method checks wether the subscription reference isn't a dummy and if it isn't changes the subscription id for the current invoice.
	 * @param subscription
	 */
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
		if (subscription != SubscriptionControl.dummySubscription)
			this.subscriptionID=subscription.getSubID();
	}
	
	/**
	 * Returns all orders contained in this Invoice
	 * @return
	 */
	public List<Order> getOrders ()
	{
		LinkedList<Order> res = new LinkedList <Order> ();
		
		for (Order item : OrderControl.getAllOrders())
		{
			if (item.getInvoiceID() == invoiceID)
				res.add(item);
		}
		return res;
	}
	
	/**
	 * Get all receipts of this Invoice (usually no more than one, might be more in case of repeated payments)
	 * @return List<Receipt> with this invoice's receipts.
	 */
	public List<Receipt> getReceipts ()
	{
		LinkedList<Receipt> res = new LinkedList<Receipt>();
		
		for (Receipt item : InvoiceControl.getAllReceipts())
		{
			if (item.getInvoiceID() == invoiceID)
				res.add(item);
		}
		return res;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public String toString()
	{
		 return new String("InvoiceID:"+getInvoiceID()+"\nTotalPrice:"+getTotalPrice()+"\nDueDate:"+getDueDate()+"\ncustomerID:"+getCustomerID()+"\nPurchasePlanID:"+getPurchasePlanID()+"\nSubscriptionID:"+getSubscriptionID());
	}

}
