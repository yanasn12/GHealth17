package entities;

import java.io.Serializable;

/**
 * 
 * This class represents a Subscription type - MonthlyFull which is a pre paid sum of many for one car of customer
 *
 */

public class MonthlyFull extends Subscription implements Serializable{
	
	private static final long serialVersionUID = -6371422963206443817L;
	double price;
	
	// constructor //
	
	
	/**
	 * Applies the subscription discount on the basePrice received.
	 * @param basePrice from which the discount is calculated.
	 * @return {@code double} price after the discount.
	 */
	public double applyDiscount (double basePrice)
	{
		return (basePrice - basePrice * (getDiscountTable().getDiscountAt(1)) 
				- basePrice * (getDiscountTable().getDiscountAt(2) + getDiscountTable().getDiscountAt(3)));
	}
}
