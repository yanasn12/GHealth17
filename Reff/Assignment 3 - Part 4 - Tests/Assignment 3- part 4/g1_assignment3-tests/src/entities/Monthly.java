package entities;

import java.io.Serializable;
import java.util.Calendar;


/**
 * 
 * This class represents a monthly payment method, which the payment is calculate in the end of the month 
 *
 */

public class Monthly extends PaymentInfo implements Serializable {
	
	private static final long serialVersionUID = 949446240964630553L;
	
	/**
	 * Delays the payment of the given Invoice and adds it to the monthly Invoices which are to be paid by then end of the month.
	 * Fails if tries to delay Invoices that cannot be delayed (Monthlies and late payments)
	 * Succeeds otherwise.
	 * @param toBePaid invoice that we pay for
	 * @return boolean isPaymentSuccessful
	 */
	public boolean pay (Invoice toBePaid){
		if (toBePaid.getStatus() == OrderStatus.MONTHLY_ACTIVE.ordinal() 
		 || toBePaid.getStatus() == OrderStatus.MONTHLY_LATE.ordinal() 
		 || toBePaid.getStatus() == OrderStatus.NOT_PAID.ordinal())
		{
			payResult = "Cannot delay the payment of monthly and late Invoices to the next month !";
			return false;
		}
		
		Invoice monthlyInvoice = null;
		for (Invoice invoice :toBePaid.getCustomer().getInvoices())
		{
			if (invoice.getStatus() == OrderStatus.MONTHLY_ACTIVE.ordinal())
			{
				monthlyInvoice = invoice;
				break;
			}
		}
		
		if (monthlyInvoice == null)
		{
			monthlyInvoice = toBePaid;
			toBePaid.setStatus(OrderStatus.MONTHLY_ACTIVE.ordinal());
			
			Calendar currCal = Calendar.getInstance();
			toBePaid.setDueDate(String.format("%4d-%2d-01", currCal.get(Calendar.YEAR), 1+(currCal.get(Calendar.MONTH)+1)%12));
			toBePaid.setDueDate(toBePaid.getDueDate().replace(' ','0'));
		}
		else
		{
			for (Order order : toBePaid.getOrders())
			{
				order.setInvoiceID(monthlyInvoice.getInvoiceID());
				order.setStatus(OrderStatus.PAYMENT_PENDING.ordinal());
			}
			toBePaid.setStatus(OrderStatus.POSTPONED.ordinal());
		}
		payResult = "Successfully added the orders to your next month's monthly invoice !";
		return true;
	}
	
	 public boolean isAuto ()
	 {
		 return true;
	 }
	
	public String getName ()
	{
		return "Add to Monthly Invoice";
	}
}
