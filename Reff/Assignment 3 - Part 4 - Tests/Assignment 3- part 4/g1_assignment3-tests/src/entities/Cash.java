package entities;

import java.io.Serializable;

/**
 * 
 * This class represents a payment method by cash
 *
 */

public class Cash extends PaymentInfo implements Serializable {
	
	private static final long serialVersionUID = 5994696234232703898L;
	
	int cashID;
	// constructor //
	
	public Cash (){
		// need TBD
	}
	
	// method //
	/**
	 * a method to use cash in order to pay for invoice.
	 */
	public boolean pay(Invoice toBePaid){
		if (toBePaid.getStatus() == OrderStatus.PAYMENT_PENDING_AUTO.ordinal())
		{
			payResult = "Cannot pay an automatic Invoice with a manual payment method";
			return false;
		}
		
		toBePaid.setStatus(OrderStatus.CONFIRM_PENDING.ordinal());
		for (Order order : toBePaid.getOrders())
			order.setStatus(OrderStatus.CONFIRM_PENDING.ordinal());
		
		payResult = "Your payment request is received and is awaiting approval. Once we confirm \n" +
				 "your payment the Invoice status will be changed to paid.";
		return true;
	}

	// get and set //
	
	public int getCashID() {
		return cashID;
	}

	public void setCashID(int cashID) {
		this.cashID = cashID;
	}
	
	public String getName ()
	{
		return "Cash";
	}
	
	 public boolean isAuto ()
	 {
		 return false;
	 }
}
