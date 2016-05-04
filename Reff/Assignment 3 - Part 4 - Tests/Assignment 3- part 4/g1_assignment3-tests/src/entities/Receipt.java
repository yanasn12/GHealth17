package entities;

import java.io.Serializable;

import controllers.InvoiceControl;

/**
 * 
 * This class represents a Receipt and all its details
 *
 */

public class Receipt implements Serializable{

	private static final long serialVersionUID = 2455635113697348581L;
	private int receiptID;
	private String paymentDate;
	private int invoiceID;
	Invoice invoice;
	
	// Constructor //
	
	public Receipt (){
		invoice = InvoiceControl.dummyInvoice;
	}
	
	// setters and getters 
	public Invoice getInvoice() {
		if (invoice == InvoiceControl.dummyInvoice)
			invoice = InvoiceControl.getInvoiceById(invoiceID);
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
		if (invoice != InvoiceControl.dummyInvoice)
			invoiceID = invoice.invoiceID;
	}
	public int getReceiptID() {
		return receiptID;
	}
	public void setReceiptID(int receiptID) {
		this.receiptID = receiptID;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

		public String toString()
	{
		return new String("Id: "+getReceiptID()+"\npaymentDate:"+getPaymentDate()+"\nInvoiceID:"+getInvoiceID());
	}


}
