package entities;

import java.io.Serializable;

/**
 * 
 *   This class represents a Subscription type - Monthly Multiple which gives discount for numbers of cars of one customer
 *
 */

public class MonthlyMultiple  extends Subscription implements Serializable{
	
	private static final long serialVersionUID = -7473592021011497253L;
	int numOfCars;
	double price;
	

	// setter and getter //
	
	public int getNumOfCars() {
		return numOfCars;
	}

	public void setNumOfCars(int numOfCars) {
		this.numOfCars = numOfCars;
		
	}
	
	// method //
	public String MtoString()
	{
		return new String(toString()+"\nNumberOfCars:"+getNumOfCars());
	}
	
	/**
	 * Applies the subscription discount on the basePrice received.
	 * @param basePrice from which the discount is calculated.
	 * @return {@code double} price after the discount.
	 */
	public double applyDiscount (double basePrice)
	{
		return (basePrice - basePrice * (getDiscountTable().getDiscountAt(1) * numOfCars) - basePrice * getDiscountTable().getDiscountAt(2));
	}

}
