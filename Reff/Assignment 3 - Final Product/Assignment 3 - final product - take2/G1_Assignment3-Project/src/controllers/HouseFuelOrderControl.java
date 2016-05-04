package controllers;

import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.Car;
import entities.CarFuelOrder;
import entities.HouseFuelInfo;
import entities.HouseFuelOrder;
import entities.Order;

/**
 * This Class provides methods to manage orders.
 * 
 * Manages: HouseFuelOrder
 * Active:  HouseFuelOrder
 * Active is used to efficiently access a specific object that is being edited.
 */
public class HouseFuelOrderControl {
	static List<HouseFuelOrder> houseFuelOrders;
	static HouseFuelOrder active;
	
	/**
	 * signifies that this HouseFuelOrder reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static HouseFuelOrder dummyHouseFuelOrder = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		houseFuelOrders = new LinkedList<HouseFuelOrder> ();

		//dummyHouseFuelOrder = new HouseFuelOrder ();
	}
	
	// ===================================== BUFFERS ==================================
	/**
	 *  Returns an {@code HouseFuelOrder} out of the buffer with a matching id. If the entity doesn't exist attempts to fetch it from DB.
	 * @param id  The HouseFuelOrderID that is searched for.
	 * @return {@code HouseFuelOrder} with matching ID or NULL if the entity was not found.
	 */	
	public static HouseFuelOrder getHouseFuelOrderById (int id)
	{
		for (HouseFuelOrder order : houseFuelOrders)
			if (order.getOrderID() == id)
				return order;
		
		return fetchHouseFuelOrder (id);
	}
	
	/**
	 *  updates the HouseFuelOrder buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static List<HouseFuelOrder> getAllHouseFuelOrders ()
	{
		fetchAllHouseFuelOrders();
		return houseFuelOrders;
	}
	
	/**
	 *  returns the HouseFuelOrder buffer that contains all HouseFuelOrders. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<HouseFuelOrder> buffer.
	 */
	public static List<HouseFuelOrder> getHouseFuelOrders() {
		return houseFuelOrders;
	}
	
	// ===================================== ACTIVE HANDLING ===================================

	public static HouseFuelOrder getActive() {
		return active;
	}

	public static void setActive (HouseFuelOrder active) {
		active = active;
	}
	
	public static void loadNewActive ()
	{
		active = new HouseFuelOrder ();
	}
	
	// ============================= DB ACCESS ===============================
	
	/**
	 *  Attempts to fetch an {@code HouseFuelOrder} from DB. Recommended using {@code getHouseFuelOrderById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code HouseFuelOrder} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static HouseFuelOrder fetchHouseFuelOrder (int id)
	{
		HouseFuelOrder res = null;
		
		Op op = new Op (Operations.GET_HOUSE_FUEL_ORDER, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (HouseFuelOrder) op.getEntity();
		if (res != null)
		{
			for (HouseFuelOrder compared : houseFuelOrders)
				if (compared.getOrderID() == res.getOrderID())
					return compared;

			houseFuelOrders.add(res);
			OrderControl.getOrders().add(res);
		}
		return res;
	}
	
	/**
	 *  updates the HouseFuelOrder buffer with all entities that didn't exist there.
	 *  In HouseFuelOrder to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllHouseFuelOrders ()
	{
		List<HouseFuelOrder> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_HOUSE_FUEL_ORDER_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<HouseFuelOrder>) op.getEntity();
		if (res != null)
		{
			for (HouseFuelOrder resS : res)
			{
				addCurrent = true;
				for (HouseFuelOrder compared : houseFuelOrders)
					if (compared.getOrderID() == resS.getOrderID())
						addCurrent = false;
				
				if (addCurrent)
				{
					houseFuelOrders.add(resS);
					OrderControl.getOrders().add(resS);
				}
			}
		}
	}
	
	/**
	 *  Removes the HouseFuelOrder with the given ID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the HouseFuelOrder to be removed.
	 */
	public static void evictHouseFuelOrder (int id)
	{
		for (HouseFuelOrder houseFuelOrder : houseFuelOrders)
		{
			if (houseFuelOrder.getOrderID() == id)
			{
				Op op = new Op (Operations.UPDATE_HOUSE_FUEL_ORDER,houseFuelOrder);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (HouseFuelInfo item : TrackingControl.getHouseFuelInfos())
				{
					if (item.getHouseFuelOrderID() == houseFuelOrder.getOrderID())
						item.setHouseFuelOrder(dummyHouseFuelOrder);
				}
				
				houseFuelOrders.remove (houseFuelOrder);
				OrderControl.getOrders().remove(houseFuelOrder);
				break;
			}
		}
	}
	

	/**
	 * Updates the HouseFuelOrder with the given ID in the DB.
	 * @param id of the HouseFuelOrder to be updated
	 */
	public static void updateHouseFuelOrder (int id)
	{
		for (HouseFuelOrder houseFuelOrder : houseFuelOrders)
		{
			if (houseFuelOrder.getOrderID() == id)
			{
				Op op = new Op (Operations.UPDATE_HOUSE_FUEL_ORDER,houseFuelOrder);
				LoginCont.client.handleMessageFromClientUI(op);

				break;
			}
		}
	}
	
	/**
	 * Creates the given HouseFuelOrder in the DB.
	 * @param order to be created in the DB.
	 */
	public static void createHouseFuelOrder (HouseFuelOrder order)
	{
		Op op = new Op (Operations.CREATE_HOUSE_FUEL_ORDER,order);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
