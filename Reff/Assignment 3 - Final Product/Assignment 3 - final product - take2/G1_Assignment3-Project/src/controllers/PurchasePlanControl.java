package controllers;
import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;
import common.SearchType;

import entities.*;

/**
 * This Class provides methods to manage PurchasePlans.
 * 
 * Manages: {@code PurchasePlan}
 * Active:  {@code PurchasePlan}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class PurchasePlanControl {
	
	/**
	 * signifies that this purchasePlan reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static PurchasePlan dummyPurchasePlan = null;
	
	static List<PurchasePlan> purchasePlans = new LinkedList<PurchasePlan>();
	static PurchasePlan active;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		purchasePlans = new LinkedList<PurchasePlan>();
		//dummyPurchasePlan = new PurchasePlan ();
	}
	
	// ========================= ACTIVE MANAGMENT ==============================
	
	/**
	 *  Returns the {@code PurchasePlan} that is currently being worked on.
	 * @return {@code PurchasePlan} loaded as active.
	 */
	public static PurchasePlan getActive() {
		return active;
	}

	/**
	 *  Designates a new {@code PurchasePlan} as the one being worked on. 
	 * @param activeCustomer The {@code PurchasePlan} to be worked on.
	 */
	public static void setActive(PurchasePlan active) {
		PurchasePlanControl.active = active;
	}

	/**
	 *  Initializes a new PurchasePlan and puts it as active PurchasePlan
	 */
	public static void loadNewActive ()
	{
		active = new PurchasePlan();
	}
	// =============================== BUFFER =================================
	/**
	 * Returns the purchasePlan with the specified purchasePlanID
	 * @param id of the plan to be returned
	 * @return {@code PurchasePlan} with the given id.
	 */
	public static PurchasePlan getPurchasePlanById (int id)
	{
		for (PurchasePlan plan : purchasePlans)
			if (plan.getPurchasePlanID() == id)
				return plan;
		
		return fetchPurchasePlan(id);
	}
	
	/**
	 * Returns a list of PurchasePlans after updating it from the DB. 
	 * @return {@code List<PurchasePlan>} buffer
	 */
	public static List<PurchasePlan> getAllPurchasePlans()
	{
		fetchAllPurchasePlans();
		return purchasePlans;
	}
	
	/**
	 *  Returns the purchasePlans buffer for manual work. Recommended to call fetchAll beforehand.
	 * @return List<PurchasePlan> with all buffered plans
	 */
	public static List<PurchasePlan> getPurchasePlans() {
		return purchasePlans;
	}

	
	
	// ============================= DB ACCESS ===============================

	/**
	 *  Attempts to fetch a PurchasePlan from DB. Recommended using {@code getPurchasePlanById} instead to check if the PurchasePlan is already in the buffer.
	 * @param id which is looked for
	 */
	public static PurchasePlan fetchPurchasePlan (int id)
	{
		PurchasePlan res = null;
		
		Op op = new Op (Operations.GET_PURCHASE_PLAN, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (PurchasePlan) op.getEntity();
		
		if (res != null)
		{
			for (PurchasePlan purchasePlan : purchasePlans)
				if (purchasePlan.getPurchasePlanID() == res.getPurchasePlanID())
					return purchasePlan;
			purchasePlans.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the PurchasePlan buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllPurchasePlans ()
	{
		List<PurchasePlan> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_PURCHASE_PLAN_ALL,-1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<PurchasePlan>) op.getEntity();
		
		if (res != null)
			for (PurchasePlan resS : res)
			{
				addCurrent = true;
				for (PurchasePlan compared : purchasePlans)
					if (compared.getPurchasePlanID() == resS.getPurchasePlanID())
						addCurrent = false;
				
				if (addCurrent)
					purchasePlans.add(resS);
			}
	}
	
	/**
	 *  Removes the PurchasePlan with the given PurchasePlanID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the PurchasePlan to be removed.
	 */
	public static void evictPurchasePlan (int id)
	{
		for (PurchasePlan purchasePlan : purchasePlans)
		{
			if (purchasePlan.getPurchasePlanID() == id)
			{
				Op op = new Op (Operations.UPDATE_PURCHASE_PLAN,purchasePlan);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (CarCustomer item : CustomerControl.getCarCustomers())
				{
					if (item.getPurchasePlanID() == purchasePlan.getPurchasePlanID())
						item.setPurchasePlan(dummyPurchasePlan);
				}
				
				for (Invoice item : InvoiceControl.getInvoices())
				{
					if (item.getPurchasePlanID() == purchasePlan.getPurchasePlanID())
						item.setPurchasePlan(dummyPurchasePlan);
				}
				
				purchasePlans.remove(purchasePlan);
				break;
			}
		}
	}
	
	/**
	 * Updates the UserType with the given UserTypeID in the DB.
	 * @param id of the UserType to be updated
	 */
	public static void updateUserType (int id)
	{
		for (PurchasePlan purchasePlan : purchasePlans)
		{
			if (purchasePlan.getPurchasePlanID() == id)
			{
				Op op = new Op (Operations.UPDATE_PURCHASE_PLAN,purchasePlan);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}	
	
	/**
	 * Creates the given PurchasePlan in the DB.
	 * @param purchasePlan to be created in the DB.
	 */
	public static void createPurchasePlan (PurchasePlan purchasePlan)
	{
		Op op = new Op (Operations.CREATE_PURCHASE_PLAN ,purchasePlan);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}

