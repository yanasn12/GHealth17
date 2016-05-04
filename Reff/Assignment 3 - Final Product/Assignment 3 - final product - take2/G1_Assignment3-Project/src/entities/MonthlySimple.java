package entities;

import java.io.Serializable;

/**
 * 
 * This class represents a Subscription type - Monthly Simple which gives discount for single car of customer
 * 
 */

public class  MonthlySimple extends Subscription implements Serializable{
		
	
	private static final long serialVersionUID = -8624675900053199676L;
	double price;
		
		
		// method //
	
	/**
	 * Applies the subscription discount on the basePrice received.
	 * @param basePrice from which the discount is calculated.
	 * @return {@code double} price after the discount.
	 */
	public double applyDiscount (double basePrice)
	{
		return (basePrice - basePrice * getDiscountTable().getDiscountAt(1));
	}
}
