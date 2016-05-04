package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import controllers.OrderControl;
import controllers.PeriodicDiscountsControl;


/**
 * 
 * This class represents a Periodic Discount which establishes a periodic discount that will be from the start to the end date
 *
 */


public class PeriodicDiscount implements Serializable {

	int periodicDiscountID;
	private static final long serialVersionUID = -111396286424400061L;
	String startDate;
	String endDate;
	double discount;
	PeriodicDiscountTemplate periodicDiscountTemplate;
	int periodicDiscountTemplateID;
	
	public PeriodicDiscount() {
		periodicDiscountTemplate = PeriodicDiscountsControl.dummyPeriodicDiscountTemplate;
	}

	// setters and getters //
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	/**
	 * 
	 * This method to get a PeriodicDiscountTemplate information 
	 * the method checks if the PeriodicDiscountTemplate information exists is a dummy reference
	 * if it is, get the PeriodicDiscountTemplate information from the DataBase and sets it in the PeriodicDiscountTemplate reference
	 * 
	 * @return periodicDiscountTemplate
	 */
	public PeriodicDiscountTemplate getPeriodicDiscountTemplate() {
		if (periodicDiscountTemplate == PeriodicDiscountsControl.dummyPeriodicDiscountTemplate)
			periodicDiscountTemplate = PeriodicDiscountsControl.getPeriodicDiscountTemplateById(periodicDiscountTemplateID);
		return periodicDiscountTemplate;
	}
	/**
	 * This method to set a periodicDiscountTemplate information.
	 * the method checks if the periodicDiscountTemplate information exists is a dummy reference
	 * if it is, get the periodicDiscountTemplate information from the DataBase and sets it in the periodicDiscountTemplate reference
	 * 
	 * @param periodicDiscountTemplate
	 */
	public void setPeriodicDiscountTemplate (PeriodicDiscountTemplate periodicDiscountTemplate) {
		this.periodicDiscountTemplate = periodicDiscountTemplate;
		if (periodicDiscountTemplate != PeriodicDiscountsControl.dummyPeriodicDiscountTemplate)
			this.periodicDiscountTemplateID = periodicDiscountTemplate.getPeriodicDiscountTemplateID();
	}

	public int getPeriodicDiscountTemplateID() {
		return periodicDiscountTemplateID;
	}

	public void setPeriodicDiscountTemplateID(int periodicDiscountTemplateID) {
		this.periodicDiscountTemplateID = periodicDiscountTemplateID;
	}

	public int getPeriodicDiscountID() {
		return periodicDiscountID;
	}

	public void setPeriodicDiscountID(int periodicDiscountID) {
		this.periodicDiscountID = periodicDiscountID;
	}
	/**
	 * 
	 * method to get all Order for the current PeriodicDiscountID,
	 * the method searches the Order by comparing PeriodicDiscountID returns a list
	 *  
	 */
	public List<Order> getOrders ()
	{
		LinkedList<Order> res = new LinkedList<Order> ();
		
		for (Order item : OrderControl.getAllOrders())
		{
			if (item.getPeriodicDiscountID() == periodicDiscountID)
				res.add(item);
		}
		return res;
	}
	
	/**
 	* Checks if the given Order is allowed to receive a discount and calculates a new price after the discount.
 	* 
 	* @param order The order for which to calculate discount.
 	* @param basePrice The price to which apply discount.
 	* @return {@code double} price after discount
 	*/
	public double applyDiscount (CarFuelOrder order, double basePrice)
	{
		long startDateNumericVal, endDateNumericVal; 
		long startTimeNumericVal, endTimeNumericVal;
		long orderDateVal, orderTimeVal;
		
		startDateNumericVal = PeriodicDiscountsControl.getDateNumericValue(startDate);
		endDateNumericVal =  PeriodicDiscountsControl.getDateNumericValue(endDate);
		startTimeNumericVal = PeriodicDiscountsControl.getTimeNumericValue(getPeriodicDiscountTemplate().getStartTime());
		endTimeNumericVal = PeriodicDiscountsControl.getTimeNumericValue(getPeriodicDiscountTemplate().getEndTime());

		orderDateVal = PeriodicDiscountsControl.getDateNumericValue(order.getOrderTime());
		orderTimeVal = PeriodicDiscountsControl.getTimeNumericValue (order.getOrderTime().substring(11,19));
		
		if (startTimeNumericVal <= orderTimeVal && orderTimeVal <= endTimeNumericVal &&
				startDateNumericVal <= orderDateVal && orderDateVal <= endDateNumericVal &&
				order.getQuantity() >= getPeriodicDiscountTemplate().minFuelAmount)
			return (basePrice - basePrice * discount);
		
		return basePrice;
	}
}
