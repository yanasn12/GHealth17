package controllers;

import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.Car;
import entities.Invoice;
import entities.MonthlySimple;
import entities.Subscription;
import entities.MonthlySimple;
import entities.MonthlyMultiple;
import entities.MonthlyFull;

/**
 * This Class provides methods to manage Subscriptions.
 * 
 * Manages: {@code Subscription}, {@code MonthlySimple}, {@code MonthlyMultiple}, {@code MonthlyFull}
 */
public class SubscriptionControl {
	static List<Subscription> subscriptions;
	static List<MonthlySimple> monthlySimples;
	static List<MonthlyMultiple> monthlyMultiples;
	static List<MonthlyFull> monthlyFulls;
	
	/**
	 * signifies that this {@code Subscription} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static Subscription dummySubscription = null;
	/**
	 * signifies that this {@code MonthlySimple} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static MonthlySimple dummyMonthlySimple = null;
	/**
	 * signifies that this {@code  MonthlyMultiple} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static MonthlyMultiple dummyMonthlyMultiple = null;
	/**
	 * signifies that this {@code MonthlyFull} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static MonthlyFull dummyMonthlyFull = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		subscriptions = new LinkedList<Subscription> ();
		monthlySimples = new LinkedList<MonthlySimple>();
		monthlyMultiples = new LinkedList<MonthlyMultiple>();
		monthlyFulls = new LinkedList<MonthlyFull>();
		
		//dummySubscription = new Subscription();
		//dummyMonthlySimple = new MonthlySimple();
		//dummyMonthlyMultiple = new MonthlyMultiple();
		//dummyMonthlyFull = new MonthlyFull ();
	}
	
	// ===================================== BUFFERS ==================================

	// ==================== SUBSCRIPTION
	
	/**
	 *  returns the Subscriptions buffer that contains all Subscriptions. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<Subscription> buffer.
	 */
	public static List<Subscription> getSubscriptions() {
		return subscriptions;
	}
	
	/**
	 *  Returns a {@code Subscription} out of the {@code Subscription} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The SubscriptionID that is searched for.
	 * @return {@code Subscription} with matching ID or NULL if entity was not found.
	 */
	public static Subscription getSubscriptionById(int id){
		
		
		for (Subscription subscription : subscriptions)
		{
			if (subscription.getSubscriptionID() == id)
				return subscription;
		}
		return fetchSubscription(id);
	}
	
	/**
	 * Returns a list of all Subscriptions after updating it from the DB. 
	 * @return {@code List<Subscription>} buffer
	 */
	public static List<Subscription> getAllSubscriptions(){
		
		fetchAllSubscriptions ();
		return subscriptions;
	}
	
	// ==================== MONTHLY SIMPLE
	
	/**
	 *  returns the MonthlySimples buffer that contains all MonthlySimples. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<MonthlySimple> buffer.
	 */
	public static List<MonthlySimple> getMonthlySimples() {
		return monthlySimples;
	}
	
	/**
	 *  Returns a {@code MonthlySimple} out of the {@code MonthlySimple} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The MonthlySimpleID that is searched for.
	 * @return {@code MonthlySimple} with matching ID or NULL if entity was not found.
	 */
	public static MonthlySimple getMonthlySimpleById(int id){
		
		
		for (MonthlySimple monthlySimple : monthlySimples)
		{
			if (monthlySimple.getSubscriptionID() == id)
				return monthlySimple;
		}
		return fetchMonthlySimple(id);
	}
	
	/**
	 * Returns a list of all MonthlySimples after updating it from the DB. 
	 * @return {@code List<MonthlySimple>} buffer
	 */
	public static List<MonthlySimple> getAllMonthlySimples(){
		
		fetchAllMonthlySimples ();
		return monthlySimples;
	}
	
	// ==================== MONTHLY MULTIPLE
	
	/**
	 *  returns the MonthlyMultiples buffer that contains all MonthlyMultiples. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<MonthlyMultiple> buffer.
	 */
	public static List<MonthlyMultiple> getMonthlyMultiples() {
		return monthlyMultiples;
	}
	
	/**
	 *  Returns a {@code MonthlyMultiple} out of the {@code MonthlyMultiple} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The MonthlyMultipleID that is searched for.
	 * @return {@code MonthlyMultiple} with matching ID or NULL if entity was not found.
	 */
	public static MonthlyMultiple getMonthlyMultipleById(int id){
		
		
		for (MonthlyMultiple monthlyMultiple : monthlyMultiples)
		{
			if (monthlyMultiple.getSubscriptionID() == id)
				return monthlyMultiple;
		}
		return fetchMonthlyMultiple(id);
	}
	
	/**
	 * Returns a list of all MonthlyMultiples after updating it from the DB. 
	 * @return {@code List<MonthlyMultiple>} buffer
	 */
	public static List<MonthlyMultiple> getAllMonthlyMultiples(){
		
		fetchAllMonthlyMultiples ();
		return monthlyMultiples;
	}
	
	// ==================== MONTHLY FULL
	
	/**
	 *  returns the MonthlyFulls buffer that contains all MonthlyFulls. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<MonthlyFull> buffer.
	 */
	public static List<MonthlyFull> getMonthlyFulls() {
		return monthlyFulls;
	}
	
	/**
	 *  Returns a {@code MonthlyFull} out of the {@code MonthlyFull} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The MonthlyFullID that is searched for.
	 * @return {@code MonthlyFull} with matching ID or NULL if entity was not found.
	 */
	public static MonthlyFull getMonthlyFullById(int id){
		
		
		for (MonthlyFull monthlyFull : monthlyFulls)
		{
			if (monthlyFull.getSubscriptionID() == id)
				return monthlyFull;
		}
		return fetchMonthlyFull(id); 
	}
	
	/**
	 * Returns a list of all MonthlyFulls after updating it from the DB. 
	 * @return {@code List<MonthlyFull>} buffer
	 */
	public static List<MonthlyFull> getAllMonthlyFulls(){
		
		fetchAllMonthlyFulls ();
		return monthlyFulls;
	}
	
	// ===================================== DB ACESSS ===============================
	
	
	// ==================== SUBSCRIPTION
	/**
	 *  Attempts to fetch a {@code Subscription} from DB. Recommended using {@code getSubscriptionById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code Subscription} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static Subscription fetchSubscription (int id)
	{
		Subscription res = null;
		
		Op op = new Op (Operations.GET_SUBSCRIPTION, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (Subscription) op.getEntity();
		if (res != null)
		{
			for (Subscription compared : subscriptions)
				if (compared.getSubscriptionID() == res.getSubscriptionID())
					return compared;
				subscriptions.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the Subscription buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllSubscriptions ()
	{
		List<Subscription> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_SUBSCRIPTION_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<Subscription>) op.getEntity();
		if (res != null)
		{
			for (Subscription resS : res)
			{
				addCurrent = true;
				for (Subscription compared : subscriptions)
					if (compared.getSubscriptionID() == resS.getSubscriptionID())
						addCurrent = false;
				
				if (addCurrent)
					subscriptions.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the Subscription with the given SubscriptionID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the Subscription to be removed.
	 */
	public static void evictSubscription (int id)
	{
		for (Subscription subscription : subscriptions)
		{
			if (subscription.getSubscriptionID() == id)
			{
				evictSubscription(subscription);
				break;
			}
		}
	}
	
	/**
	 *  Removes the Subscription with the given SubscriptionID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param subscription the Subscription to be removed.
	 */
	public static void evictSubscription (Subscription subscription)
	{
		Op op = new Op (Operations.UPDATE_SUBSCRIPTION,subscription);
		LoginCont.client.handleMessageFromClientUI(op);
		
		for (Invoice item : InvoiceControl.getInvoices())
		{
			if (item.getSubscriptionID() == subscription.getSubscriptionID())
				item.setSubscription(dummySubscription);
		}
		
		for (Car item : CustomerControl.getCars())
		{
			if (item.getSubscriptionID() == subscription.getSubscriptionID())
				item.setSubscription(dummySubscription);
		}
		
		subscriptions.remove (subscription);
	}
	
	/**
	 * Updates the Subscription with the given SubscriptionID in the DB.
	 * @param id of the Subscription to be updated
	 */
	public static void updateSubscription (int id)
	{
		for (Subscription subscription : subscriptions)
		{
			if (subscription.getSubscriptionID() == id)
			{
				Op op = new Op (Operations.UPDATE_SUBSCRIPTION,subscription);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	// ==================== MONTHLY SIMPLE
	
	/**
	 *  Attempts to fetch a {@code MonthlySimple} from DB. Recommended using {@code getMonthlySimpleById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code MonthlySimple} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static MonthlySimple fetchMonthlySimple (int id)
	{
		MonthlySimple res = null;
		
		Op op = new Op (Operations.GET_MONTHLY_SIMPLE, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (MonthlySimple) op.getEntity();
		if (res != null)
		{
			for (MonthlySimple compared : monthlySimples)
				if (compared.getSubscriptionID() == res.getSubscriptionID())
					return compared;
				monthlySimples.add(res);
				subscriptions.add (res);
		}
		return res;
	}
	
	/**
	 *  updates the MonthlySimple buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllMonthlySimples ()
	{
		List<MonthlySimple> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_MONTHLY_SIMPLE_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<MonthlySimple>) op.getEntity();
		if (res != null)
		{
			for (MonthlySimple resS : res)
			{
				addCurrent = true;
				for (MonthlySimple compared : monthlySimples)
					if (compared.getSubscriptionID() == resS.getSubscriptionID())
						addCurrent = false;
				
				if (addCurrent)
				{
					monthlySimples.add(resS);
					subscriptions.add (resS);
				}
				
			}
		}
	}
	
	/**
	 *  Removes the MonthlySimple with the given MonthlySimpleID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the MonthlySimple to be removed.
	 */
	public static void evictMonthlySimple (int id)
	{
		for (MonthlySimple monthlySimple : monthlySimples)
		{
			if (monthlySimple.getSubscriptionID() == id)
			{
				Op op = new Op (Operations.UPDATE_MONTHLY_SIMPLE,monthlySimple);
				LoginCont.client.handleMessageFromClientUI(op);
				
				evictSubscription(monthlySimple);
				monthlySimples.remove (monthlySimple);
				break;
			}
		}
	}
	
	/**
	 * Updates the MonthlySimple with the given MonthlySimpleID in the DB.
	 * @param id of the MonthlySimple to be updated
	 */
	public static void updateMonthlySimple (int id)
	{
		for (MonthlySimple monthlySimple : monthlySimples)
		{
			if (monthlySimple.getSubscriptionID() == id)
			{
				Op op = new Op (Operations.UPDATE_MONTHLY_SIMPLE,monthlySimple);
				LoginCont.client.handleMessageFromClientUI(op);

				break;
			}
		}
	}
	
	/**
	 * Creates the given MonthlySimple in the DB.
	 * @param monthlySimple to be created in the DB.
	 */
	public static void createMonthlySimple (MonthlySimple monthlySimple)
	{
		Op op = new Op (Operations.CREATE_MONTHLY_SIMPLE ,monthlySimple);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
	
	// ==================== MONTHLY MULTIPLE
	
	/**
	 *  Attempts to fetch a {@code MonthlyMultiple} from DB. Recommended using {@code getMonthlyMultipleById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code MonthlyMultiple} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static MonthlyMultiple fetchMonthlyMultiple (int id)
	{
		MonthlyMultiple res = null;
		
		Op op = new Op (Operations.GET_MONTHLY_MULTIPLE, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (MonthlyMultiple) op.getEntity();
		if (res != null)
		{
			for (MonthlyMultiple compared : monthlyMultiples)
				if (compared.getSubscriptionID() == res.getSubscriptionID())
					return compared;
			monthlyMultiples.add(res);
			subscriptions.add (res);
		}
		return res;
	}
	
	/**
	 *  updates the MonthlyMultiple buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllMonthlyMultiples ()
	{
		List<MonthlyMultiple> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_MONTHLY_MULTIPLE_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<MonthlyMultiple>) op.getEntity();
		if (res != null)
		{
			for (MonthlyMultiple resS : res)
			{
				addCurrent = true;
				for (MonthlyMultiple compared : monthlyMultiples)
					if (compared.getSubscriptionID() == resS.getSubscriptionID())
						addCurrent = false;
				
				if (addCurrent)
				{
					monthlyMultiples.add(resS);
					subscriptions.add (resS);
				}
			}
		}
	}
	
	/**
	 *  Removes the MonthlyMultiple with the given MonthlyMultipleID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the MonthlyMultiple to be removed.
	 */
	public static void evictMonthlyMultiple (int id)
	{
		for (MonthlyMultiple monthlyMultiple : monthlyMultiples)
		{
			if (monthlyMultiple.getSubscriptionID() == id)
			{
				Op op = new Op (Operations.UPDATE_MONTHLY_MULTIPLE,monthlyMultiple);
				LoginCont.client.handleMessageFromClientUI(op);
				
				evictSubscription(monthlyMultiple);
				monthlyMultiples.remove (monthlyMultiple);
				break;
			}
		}
	}
	
	/**
	 * Updates the MonthlyMultiple with the given MonthlyMultipleID in the DB.
	 * @param id of the MonthlyMultiple to be updated
	 */
	public static void updateMonthlyMultiple (int id)
	{
		for (MonthlyMultiple monthlyMultiple : monthlyMultiples)
		{
			if (monthlyMultiple.getSubscriptionID() == id)
			{
				Op op = new Op (Operations.UPDATE_MONTHLY_MULTIPLE,monthlyMultiple);
				LoginCont.client.handleMessageFromClientUI(op);

				break;
			}
		}
	}
	
	/**
	 * Creates the given MonthlyMultiple in the DB.
	 * @param monthlyMultiple to be created in the DB.
	 */
	public static void createMonthlyMultiple (MonthlyMultiple monthlyMultiple)
	{
		Op op = new Op (Operations.CREATE_MONTHLY_MULTIPLE ,monthlyMultiple);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
	
	// ==================== MONTHLY FULL
	
	/**
	 *  Attempts to fetch a {@code MonthlyFull} from DB. Recommended using {@code getMonthlyFullById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code MonthlyFull} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static MonthlyFull fetchMonthlyFull (int id)
	{
		MonthlyFull res = null;
		
		Op op = new Op (Operations.GET_MONTHLY_FULL, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (MonthlyFull) op.getEntity();
		if (res != null)
		{
			for (MonthlyFull compared : monthlyFulls)
				if (compared.getSubscriptionID() == res.getSubscriptionID())
					return compared;
				monthlyFulls.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the MonthlyFull buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllMonthlyFulls ()
	{
		List<MonthlyFull> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_MONTHLY_FULL_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<MonthlyFull>) op.getEntity();
		if (res != null)
		{
			for (MonthlyFull resS : res)
			{
				addCurrent = true;
				for (MonthlyFull compared : monthlyFulls)
					if (compared.getSubscriptionID() == resS.getSubscriptionID())
						addCurrent = false;
				
				if (addCurrent)
					monthlyFulls.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the MonthlyFull with the given MonthlyFullID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the MonthlyFull to be removed.
	 */
	public static void evictMonthlyFull (int id)
	{
		for (MonthlyFull monthlyFull : monthlyFulls)
		{
			if (monthlyFull.getSubscriptionID() == id)
			{
				Op op = new Op (Operations.UPDATE_MONTHLY_FULL,monthlyFull);
				LoginCont.client.handleMessageFromClientUI(op);
				
				evictSubscription(monthlyFull);
				monthlyFulls.remove (monthlyFull);
				break;
			}
		}
	}
	
	/**
	 * Updates the MonthlyFull with the given MonthlyFullID in the DB.
	 * @param id of the MonthlyFull to be updated
	 */
	public static void updateMonthlyFull (int id)
	{
		for (MonthlyFull monthlyFull : monthlyFulls)
		{
			if (monthlyFull.getSubscriptionID() == id)
			{
				Op op = new Op (Operations.UPDATE_MONTHLY_FULL,monthlyFull);
				LoginCont.client.handleMessageFromClientUI(op);

				break;
			}
		}
	}
	
	/**
	 * Creates the given MonthlyFull in the DB.
	 * @param monthlyFull to be created in the DB.
	 */
	public static void createMonthlyFull (MonthlyFull monthlyFull)
	{
		Op op = new Op (Operations.CREATE_MONTHLY_FULL,monthlyFull);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
