package entities;

import java.io.Serializable;

import java.util.Calendar;

import controllers.PeriodicDiscountsControl;

/**
 * 
 * This class represents a payment method by CreditCard
 *
 */

public class CreditCard extends PaymentInfo implements Serializable {
	
	private static final long serialVersionUID = -99630289957313791L;
	
	int creditCardID;
	String cardNo;
	String validDate;
	
	
	
	public CreditCard(){
		
	}
	
	// setters and getters //
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public int getCreditCardID() {
		return creditCardID;
	}

	public void setCreditCardID(int creditCardID) {
		this.creditCardID = creditCardID;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public String CtoString()
	{
		return new String(toString()+"\ncreditCard ID:"+getCreditCardID()+"\ncreditCard number:"+getCardNo()+"\ncreditCard validDate:"+getValidDate());
	}
	
	/**
	 * Pays the given Invoice as a credit card.
	 * Fails if credit card is no longer valid,
	 * Succeeds otherwise.
	 * @param toBePaid invoice that we pay for
	 * @return boolean isPaymentSuccessful
	 */
	public boolean pay(Invoice toBePaid){
		long validVal = PeriodicDiscountsControl.getDateNumericValue(validDate);
		Calendar currCal = Calendar.getInstance();
		long currVal = currCal.get(Calendar.YEAR)*10000 + currCal.get(Calendar.MONTH)*100 + currCal.get(Calendar.DAY_OF_MONTH);
		
		if (currVal > validVal)
		{
			payResult = "The given credit card is no longer valid !";
			return false;
		}
		toBePaid.setStatus(OrderStatus.PAID.ordinal());
		payResult = "Invoice successfully paid !";
		return true;
	}
	
	 public boolean isAuto ()
	 {
		 return true;
	 }
	
	public String getName ()
	{
		return "Credit Card";
	}
	

}
