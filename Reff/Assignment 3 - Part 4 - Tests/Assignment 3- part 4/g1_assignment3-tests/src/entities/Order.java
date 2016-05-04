package entities;

import java.io.Serializable;

import controllers.FuelTypeControl;
import controllers.InvoiceControl;
import controllers.PeriodicDiscountsControl;

/**
 * 
 * This class represents a general fuel order , which is described in all its details.
 *
 */

public class Order implements Serializable {

	private static final long serialVersionUID = -5787923334140230796L;
	int orderID;
	double quantity;
	double price;
	int status;
	String orderTime;
	PeriodicDiscount periodicDiscount;
	int periodicDiscountID;
	Invoice invoice;
	int invoiceID;
	int fuelTypeID;
	FuelType fuelType;
	
	/**
	 * 
	 * This constructor puts a dummy references in the attributes 
	 * 
	 */
	public Order(){
		periodicDiscount = PeriodicDiscountsControl.dummyPeriodicDiscount;
		invoice = InvoiceControl.dummyInvoice;
		fuelType = FuelTypeControl.dummyFuelType;
	}
	
	// setters and getters//
	
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	public int getFuelTypeID() {
		return fuelTypeID;
	}

	public void setFuelTypeID(int fuelTypeID) {
		this.fuelTypeID = fuelTypeID;
	}
	/**
	 * 
	 * This method is getting a FuelType information 
	 * the method checks if the FuelType information exists is a dummy reference
	 * if it is, get the FuelType information from the DataBase and sets it in the FuelType reference
	 * 
	 * @return FuelType
	 */
	public FuelType getFuelType ()
	{
		if (fuelType == FuelTypeControl.dummyFuelType)
			fuelType = FuelTypeControl.getFuelTypeById(fuelTypeID);
		return fuelType;
	}
	/**
	 * This method to set a FuelType information.
	 * the method checks if the FuelType information exists is a dummy reference
	 * if it is, get the FuelType information from the DataBase and sets it in the FuelType reference
	 * 
	 * @param FuelType
	 */
	public void setFuelType (FuelType fuelType)
	{
		this.fuelType = fuelType;
		if (fuelType != FuelTypeControl.dummyFuelType)
			fuelTypeID = fuelType.getFuelTypeID();
	}
	/**
	 * 
	 * This method to get a PeriodicDiscount information 
	 * the method checks if the PeriodicDiscount information exists is a dummy reference
	 * if it is, get the PeriodicDiscount information from the DataBase and sets it in the PeriodicDiscount reference
	 * 
	 * @return PeriodicDiscount
	 */
	public PeriodicDiscount getPeriodicDiscount() {
		if (periodicDiscount == PeriodicDiscountsControl.dummyPeriodicDiscount)
			periodicDiscount = PeriodicDiscountsControl.getPeriodicDiscountById(periodicDiscountID);
		return periodicDiscount;
	}
	/**
	 * This method to set a PeriodicDiscount information.
	 * the method checks if the PeriodicDiscount information exists is a dummy reference
	 * if it is, get the PeriodicDiscount information from the DataBase and sets it in the PeriodicDiscount reference
	 * 
	 * @param PeriodicDiscount
	 */
	public void setPeriodicDiscount(PeriodicDiscount periodicDiscount) {
		this.periodicDiscount = periodicDiscount;
		if (periodicDiscount != PeriodicDiscountsControl.dummyPeriodicDiscount)
			this.periodicDiscountID = periodicDiscount.getPeriodicDiscountID();
	}

	public int getPeriodicDiscountID() {
		return periodicDiscountID;
	}

	public void setPeriodicDiscountID(int periodicDiscountID) {
		this.periodicDiscountID = periodicDiscountID;
	}
	/**
	 * 
	 * This method to get a Invoice information 
	 * the method checks if the Invoice information exists is a dummy reference
	 * if it is, get the Invoice information from the DataBase and sets it in the Invoice reference
	 * 
	 * @return Invoice
	 */
	public Invoice getInvoice() {
		if (invoice == InvoiceControl.dummyInvoice)
			invoice = InvoiceControl.getInvoiceById(invoiceID);
		return invoice;
	}
	/**
	 * This method to set a Invoice information.
	 * the method checks if the Invoice information exists is a dummy reference
	 * if it is, get the Invoice information from the DataBase and sets it in the Invoice reference
	 * 
	 * @param PeriodicDiscount
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
		if (invoice != InvoiceControl.dummyInvoice)
			invoiceID = invoice.getInvoiceID();
	}

	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}
	
	/**
	 * A method which calculate the amount to pay the for order. Later will be override by its order type method
	 */
	public void calcPrice (){
	}
	public String toString()
	{
		 return new String("OrderID:"+getOrderID()+"\nQuantity:"+getQuantity()+"\nPrice:"+getPrice()+"\nStatus:"+getStatus()+"\nOrderTime:"+getOrderTime()+"\nInvoiceID:"+getInvoiceID()+"\nPeriodicDiscountID:"+getPeriodicDiscountID());
	}
}
