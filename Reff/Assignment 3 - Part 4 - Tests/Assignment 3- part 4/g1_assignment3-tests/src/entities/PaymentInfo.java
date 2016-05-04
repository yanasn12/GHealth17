package entities;

import java.io.Serializable;

import controllers.CustomerControl;

/**
 * 
 * This class represents a PaymentInfo method
 *
 */

public class PaymentInfo implements Serializable{
 
 private static final long serialVersionUID = -321406287429791221L;
 int paymentInfoID;
 Customer customer;
 int customerID;
 int preferredPaymentMethod;
 protected String payResult;
 
 
 public int getCustomerID() {
  return customerID;
 }

 public void setCustomerID(int customerID) {
  this.customerID = customerID;
 }

 public Customer getCustomer() {
	 if (customer == CustomerControl.dummyCustomer)
		 customer = CustomerControl.getCustomerById(customerID);
	 return customer;
 }
 
 public void setCustomer(Customer customer) {
  this.customer = customer;
  if (customer != CustomerControl.dummyCustomer)
	  this.customerID = customer.getCustomerID();
 }

 public int getPaymentInfoID() {
  return paymentInfoID;
 }

 public void setPaymentInfoID(int paymentInfoID) {
  this.paymentInfoID = paymentInfoID;
 }
 
 public int getPreferredMethod()
 {
  return this.preferredPaymentMethod;
 }
 
 public void setPrefferedMethod(int num)
 {
  this.preferredPaymentMethod=num;
 }
 
 /**
  * Returns true if the given paymentInfo is automatic and does not require human intervention.
  * @return boolean is auto payment method.
  */
 public boolean isAuto ()
 {
	 return false;
 }
 
 public String toString()
 {
  return new String("PaymentInfoID:"+getPaymentInfoID()+"\nCustomerID:"+getCustomerID()+"\nPrefferedPayMethod:"+getPreferredMethod());
 }
 
 public String getName ()
 {
	 return "Empty Payment Method";
 }
  
	/**
	 * Empty method which has to be overridden by subclasses.
	 * Always fails.
	 * @param toBePaid invoice that we pay for
	 * @return boolean isPaymentSuccessful
	 */
	public boolean pay(Invoice toBePaid){
		payResult = "Cannot pay with an empty payment method !";
		return false;
	}

	/**
	 * Returns the textual result of the last attempted payment.
	 * @return String payment result message
	 */
	public String getPayResult() {
		return payResult;
	}
}