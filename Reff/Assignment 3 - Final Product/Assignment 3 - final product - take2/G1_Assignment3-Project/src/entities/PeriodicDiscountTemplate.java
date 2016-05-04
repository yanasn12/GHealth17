package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import controllers.PeriodicDiscountsControl;

/**
 * 
 * This class represents a Periodic Discount Template, that describes the general template of periodic discount.
 *
 */

public class PeriodicDiscountTemplate implements Serializable{
	
	private static final long serialVersionUID = 4219725339344370412L;
	int PeriodicDiscountTemplateID;
	double discount;
	String startTime;
	String endTime;
	double minFuelAmount;
	
	
	// setters and getters //
	public int getPeriodicDiscountTemplateID() {
		return PeriodicDiscountTemplateID;
	}
	public void setPeriodicDiscountTemplateID(int periodicDiscountTemplateID) {
		PeriodicDiscountTemplateID = periodicDiscountTemplateID;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public double getMinFuelAmount() {
		return minFuelAmount;
	}
	public void setMinFuelAmount(double minFuelAmount) {
		this.minFuelAmount = minFuelAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 
	 * method to get all PeriodicDiscount for the current PeriodicDiscountTemplateID,
	 * the method searches the PeriodicDiscount by comparing PeriodicDiscountTemplateID returns a list
	 *  
	 */
	public List<PeriodicDiscount> getPeriodicDiscounts ()
	{
		LinkedList <PeriodicDiscount> res = new LinkedList <PeriodicDiscount> ();
		
		for (PeriodicDiscount item : PeriodicDiscountsControl.getAllPeriodicDiscounts())
		{
			if (item.getPeriodicDiscountTemplateID() == PeriodicDiscountTemplateID)
				res.add(item);
		}
		return res;
	}
}
