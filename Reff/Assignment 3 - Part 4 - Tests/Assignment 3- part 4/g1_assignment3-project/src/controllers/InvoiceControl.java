package controllers;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;
import common.SearchType;

import entities.*;


/**
 * This Class provides methods to manage invoices.
 * 
 * Manages: {@code Invoice}, {@code Receipt}
 * Active:  {@code Invoice}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class InvoiceControl {
	
	static List<Invoice> invoices;
	static List<Receipt> receipts;
	static Invoice active;

	
	/**
	 * signifies that this {@code Receipt} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static Receipt dummyReceipt = null;
	/**
	 * signifies that this {@code Invoice} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static Invoice dummyInvoice = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		invoices = new LinkedList<Invoice> ();
		receipts = new LinkedList<Receipt> ();
		//dummyInvoice = new Invoice();
		//dummyReceipt = new Receipt ();
	}
	
	// ===================================== BUFFERS ==================================

	/**
	 *  returns the Invoices buffer that contains all invoices. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<Invoice> buffer.
	 */
	public static List<Invoice> getInvoices() {
		return invoices;
	}
	
	/**
	 *  Returns an {@code Invoice} out of the {@code Invoice} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The invoiceID that is searched for.
	 * @return {@code Invoice} with matching invoiceID or NULL if invoice was not found.
	 */
	public static Invoice getInvoiceById(int id){
		
		
		for (Invoice invoice : invoices)
		{
			if (invoice.getInvoiceID() == id)
				return invoice;
		}
		return fetchInvoice(id);
	}
	
	/**
	 * Returns a list of Invoices after updating it from the DB. 
	 * @return {@code List<Invoice>} buffer
	 */
	public static List<Invoice> getAllInvoices(){
		
		fetchAllInvoices ();
		return invoices;
	}

	/**
	 *  returns the Receipts buffer that contains all Receipts. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<Receipt> buffer.
	 */
	public static List<Receipt> getReceipts() {
		return receipts;
	}
	
	/**
	 *  Returns a {@code Receipt} out of the {@code Receipt} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The ReceiptID that is searched for.
	 * @return {@code Receipt} with matching ID or NULL if entity was not found.
	 */
	public static Receipt getReceiptById(int id){
		
		
		for (Receipt receipt : receipts)
		{
			if (receipt.getReceiptID() == id)
				return receipt;
		}
		return fetchReceipt(id);
	}
	
	/**
	 * Returns a list of all Receipts after updating it from the DB. 
	 * @return {@code List<Receipt>} buffer
	 */
	public static List<Receipt> getAllReceipts(){
		
		fetchAllReceipts ();
		return receipts;
	}
	
	
	public static List<Invoice> getAllActiveInvoices ()
	{
		int status;
		LinkedList <Invoice> res = new LinkedList<Invoice>();
		
		for (Invoice item : getAllInvoices())
		{
			if (item.getStatus() != OrderStatus.PAID.ordinal())
				res.add(item);
		}
		return res;
	}
	
	// ===================================== ACTIVE HANDLING ===================================
	
	/**
	 *  Returns the {@code Invoice} that is currently being worked on.
	 * @return {@code Invoice} loaded as active.
	 */
	public static Invoice getActive() {
		return active;
	}
	
	/**
	 *  Designates a new {@code Invoice} as the one being worked on. 
	 * @param activeInvoice The {@code Invoice} to be worked on.
	 */
	public static void setActive(Invoice activeInvoice) {
		InvoiceControl.active = activeInvoice;
	}
	
	/**
	 *  Initializes a new {@code Invoice} and puts it as active {@code Invoice}
	 */		
	public static void loadNewActive ()
	{
		active = new Invoice ();
	}	
	
	/**
	 * Calculates the overall Invoice sum for this invoice based on the stored sums in the orders it contains.
	 */
	public static void calcActiveSum ()
	{
		double sum = 0;
		for (Order order : active.getOrders())
		{
			sum += order.getPrice();
		}
		active.setTotalPrice(sum);
	}
	
	/**
	 * Calculates the overall Invoice sum for this invoice. Calculates the sums of the orders this invoice contains and then the overall sum which is stored in the Invoice.
	 */
	public static void calcActiveSumDeep ()
	{
		double sum = 0;
		for (Order order : active.getOrders())
		{
			order.calcPrice();
			sum += order.getPrice();
		}
		active.setTotalPrice(sum);
	}
	
	/**
	 * Checks all invoices in the buffer and updates their information: 
	 * 			-Calculates orders price if missing.
	 * 			-Calculates overall Invoice sum.
	 * 			-Checks of the statuses of the orders are up to date.
	 * 			-checks if a monthly invoice is late in payment.
	 */
	public static void checkInvoiceData ()
	{
		long invDate, currDate;
		for (Invoice invoice : invoices)
		{
			float sum = 0;
			for (Order order : invoice.getOrders())
			{
				if (order.getPrice() < 0)
					order.calcPrice();
				if (invoice.getStatus() == OrderStatus.PAID.ordinal() 
				 || invoice.getStatus() ==  OrderStatus.CONFIRM_PENDING.ordinal())
					order.setStatus(invoice.getStatus());
				sum += order.getPrice();
			}
			invoice.setTotalPrice(sum);
			
			if (invoice.getStatus() == OrderStatus.MONTHLY_ACTIVE.ordinal())
			{
				invDate = PeriodicDiscountsControl.getDateNumericValue(invoice.getDueDate());
				Calendar currCal = Calendar.getInstance();
				currDate = currCal.get(Calendar.YEAR)*10000 + (currCal.get(Calendar.MONTH)+1)*100 + currCal.get(Calendar.DAY_OF_MONTH);
				
				if (currDate > invDate)
					invoice.setStatus(OrderStatus.MONTHLY_LATE.ordinal());
			}
		}
	}
	
	/**
	 *  Pays the active Invoice with the first automatic payment method which has the highest priority. If couldn't pay with an auto method
	 *  returns false.
	 * @return isPaymentSuccessful
	 */
	public static boolean payActiveAuto ()
	{
		List<PaymentInfo> paymentMethods = InvoiceControl.getActive().getCustomer().getPaymentInfos();
		PaymentInfo preferredMethod = null;
		int maxPrio;
		
		while (paymentMethods.isEmpty() == false)
		{
			maxPrio = -1;
			for (PaymentInfo method : paymentMethods)
			{
				if (method.getPreferredMethod() > maxPrio)
				{
					maxPrio = method.getPreferredMethod();
					preferredMethod = method;
				}
			}
			if (preferredMethod == null)
				return false;
			paymentMethods.remove(preferredMethod);
			if (preferredMethod.isAuto() == false)
				continue;
			if (payActive(preferredMethod) == true)
				return true;
		}	
		
		return false;
	}
	/**
	 * Pays the active Invoice with the given payment method. Creates a new Receipt if payment happened and updates the DB
	 * @param payWith the PaymentMethod to be used for payment
	 */
	public static boolean payActive (PaymentInfo payWith)
	{
		Invoice currActive = active;
		Receipt receipt;
		boolean result = payWith.pay(active);
		if (result == true)
		{
			if (active.getStatus() == OrderStatus.POSTPONED.ordinal())
			{
				for (Invoice invoice : active.getCustomer().getInvoices())
				{
					if (invoice.getStatus() ==  OrderStatus.MONTHLY_ACTIVE.ordinal())
					{
						InvoiceControl.setActive(invoice);
						InvoiceControl.calcActiveSum();
						InvoiceControl.updateInvoice(invoice.getInvoiceID());
						break;
					}
				}
			}
			else 
			{
				if (active.getStatus() != OrderStatus.MONTHLY_ACTIVE.ordinal())
				{
					receipt = new Receipt();
					receipt.setInvoiceID(active.getInvoiceID());
					Calendar currCal = Calendar.getInstance();
					String currDate = String.format("%4d-%2d-%2d", currCal.get(Calendar.YEAR),currCal.get(Calendar.MONTH)+1,currCal.get(Calendar.DAY_OF_MONTH));
					currDate = currDate.replace(' ','0');
			
					receipt.setPaymentDate(currDate);
					
					int id = 0;
					for (Receipt rec : getAllReceipts())
					{
						if (rec.getReceiptID() > id)
							id = rec.getReceiptID();
					}
					receipt.setReceiptID(id + 1);
					createReceipt(receipt);
				}
			}
			InvoiceControl.updateInvoice(currActive.getInvoiceID());
		}
		return result;
	}
	// ===================================== DB ACESSS ===============================
	
	/**
	 *  Attempts to fetch an {@code Invoice} from DB. Recommended using {@code getInvoiceById} instead to check if the invoice is already in the buffer.
	 * @param id which is looked for
	 * @return {@code Invoice} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static Invoice fetchInvoice (int id)
	{
		Invoice res = null;
		
		Op op = new Op (Operations.GET_INVOICE, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (Invoice) op.getEntity();
		if (res != null)
		{
			for (Invoice compared : invoices)
				if (compared.getInvoiceID() == res.getInvoiceID())
					return compared;

			invoices.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the Invoice buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllInvoices ()
	{
		List<Invoice> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_INVOICE_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<Invoice>) op.getEntity();
		if (res != null)
		{
			for (Invoice resS : res)
			{
				addCurrent = true;
				for (Invoice compared : invoices)
					if (compared.getInvoiceID() == resS.getInvoiceID())
						addCurrent = false;
				
				if (addCurrent)
					invoices.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the Invoice with the given InvoiceID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the invoice to be removed.
	 */
	public static void evictInvoice (int id)
	{
		for (Invoice invoice : invoices)
		{
			if (invoice.getInvoiceID() == id)
			{
				Op op = new Op (Operations.UPDATE_INVOICE,invoice);
				LoginCont.client.handleMessageFromClientUI(op);

				for (Order data : OrderControl.getOrders())
				{
					if (data.getInvoiceID() == id)
						data.setInvoice(dummyInvoice);
				}
				
				invoices.remove (invoice);
				break;
			}
		}
	}
	
	/**
	 *  Clears out the Invoice buffer entirely.
	 */
	public static void evictInvoiceAll ()
	{
		for (Invoice invoice : invoices)
		{
			Op op = new Op (Operations.UPDATE_INVOICE,invoice);
			LoginCont.client.handleMessageFromClientUI(op);

			for (Order data : OrderControl.getOrders())
			{
				if (data.getInvoiceID() == invoice.getInvoiceID())
					data.setInvoice(dummyInvoice);
			}
				
			invoices.remove (invoice);
		}
	}

	/**
	 * Updates the invoice with the given InvoiceID in the DB.
	 * @param id of the invoice to be updated
	 */
	public static void updateInvoice (int id)
	{
		for (Invoice invoice : invoices)
		{
			if (invoice.getInvoiceID() == id)
			{
				Op op = new Op (Operations.UPDATE_INVOICE,invoice);
				LoginCont.client.handleMessageFromClientUI(op);

				break;
			}
		}
	}
	
	/**
	 * Creates the given Invoice in the DB.
	 * @param invoice to be created in the DB.
	 */
	public static void createInvoice (Invoice invoice)
	{
		Op op = new Op (Operations.CREATE_INVOICE, invoice);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
	
	/**
	 *  Attempts to fetch a {@code Receipt} from DB. Recommended using {@code getReceiptById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code Receipt} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static Receipt fetchReceipt (int id)
	{
		Receipt res = null;
		
		Op op = new Op (Operations.GET_RECEIPT, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (Receipt) op.getEntity();
		if (res != null)
		{
			for (Receipt compared : receipts)
				if (compared.getReceiptID() == res.getReceiptID())
					return compared;
				receipts.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the Receipt buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllReceipts ()
	{
		List<Receipt> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_RECEIPT_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<Receipt>) op.getEntity();
		if (res != null)
		{
			for (Receipt resS : res)
			{
				addCurrent = true;
				for (Receipt compared : receipts)
					if (compared.getReceiptID() == resS.getReceiptID())
						addCurrent = false;
				
				if (addCurrent)
					receipts.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the Receipt with the given ReceiptID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the Receipt to be removed.
	 */
	public static void evictReceipt (int id)
	{
		for (Receipt receipt : receipts)
		{
			if (receipt.getReceiptID() == id)
			{
				Op op = new Op (Operations.UPDATE_RECEIPT,receipt);
				LoginCont.client.handleMessageFromClientUI(op);
				
				receipts.remove (receipt);
				break;
			}
		}
	}
	
	/**
	 * Clears out the receipt buffer entirely
	 */
	public static void evictReceiptAll ()
	{
		for (Receipt receipt : receipts)
		{
			Op op = new Op (Operations.UPDATE_RECEIPT,receipt);
			LoginCont.client.handleMessageFromClientUI(op);
			
			receipts.remove (receipt);
		}
	}
	
	/**
	 * Updates the Receipt with the given ReceiptID in the DB.
	 * @param id of the Receipt to be updated
	 */
	public static void updateReceipt (int id)
	{
		for (Receipt receipt : receipts)
		{
			if (receipt.getReceiptID() == id)
			{
				Op op = new Op (Operations.UPDATE_RECEIPT,receipt);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given Receipt in the DB.
	 * @param receipt to be created in the DB.
	 */
	public static void createReceipt (Receipt receipt)
	{
		Op op = new Op (Operations.CREATE_RECEIPT ,receipt);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
