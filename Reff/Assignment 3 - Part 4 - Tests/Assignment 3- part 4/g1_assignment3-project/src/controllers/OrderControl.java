package controllers;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.*;


/**
 * This Class provides methods to manage orders.
 * 
 * Manages: Order
 * Active:  Order
 * Active is used to efficiently access a specific object that is being edited.
 */
public class OrderControl {

	/**
	 * signifies that this Order reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static Order dummyOrder = null;
	
	static List<Order> orders;
	static Order activeOrder;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		orders = new LinkedList<Order> ();

		//dummyOrder = new Order ();
	}
	// ===================================== BUFFERS ==================================
	
	/**
	 *  Returns an {@code Order} out of the buffer with a matching id. If the entity doesn't exist attempts to fetch it from DB.
	 * @param id  The orderID that is searched for.
	 * @return {@code Order} with matching ID or NULL if the entity was not found.
	 */
	public static Order getOrderById (int id)
	{
		for (Order order : orders)
			if (order.getOrderID() == id)
				return order;
		
		return fetchOrder (id);
	}
	
	/**
	 *  updates the Order buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static List<Order> getAllOrders ()
	{
		fetchAllOrders();
		return orders;
	}
	
	/**
	 *  returns the Orders buffer that contains all Orders.
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<Order> buffer.
	 */
	public static List<Order> getOrders() {
		return orders;
	}

	/**
	 * Returns the current date in String format
	 * @return String formated date
	 */
	public static String getCurrDate ()
	{
		Calendar currCal = Calendar.getInstance();
		String currDate = String.format("%4d-%2d-%2d", currCal.get(Calendar.YEAR),currCal.get(Calendar.MONTH)+1,currCal.get(Calendar.DAY_OF_MONTH));
		currDate = currDate.replace(' ','0');
		return currDate;
	}
	
	/**
	 * Returns the current Time in String format
	 * @return String formated Time
	 */
	public static String getCurrTime ()
	{
		Calendar currCal = Calendar.getInstance();
		String currTime = String.format("%2d:%2d:%2d", currCal.get(Calendar.HOUR_OF_DAY),currCal.get(Calendar.MINUTE)+1,currCal.get(Calendar.SECOND));
		currTime = currTime.replace(' ','0');
		return currTime;
	}
	
	/**
	 * Returns the current DateTime in String format
	 * @return String formated DateTime
	 */
	public static String getCurrDateTime ()
	{
		return (getCurrDate() + " " + getCurrTime());
	}
	
	// ===================================== ACTIVE HANDLING ===================================

	public static Order getActive() {
		return activeOrder;
	}

	public static void setActive (Order activeOrder) {
		OrderControl.activeOrder = activeOrder;
	}
	
	public static void loadNewActive ()
	{
		activeOrder = new Order ();
	}
	
	// ============================= DB ACCESS ===============================

	/**
	 *  Attempts to fetch an {@code Order} from DB. Recommended using {@code getOrderById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code Order} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static Order fetchOrder (int id)
	{
		Order res = null;
		
		Op op = new Op (Operations.GET_ORDER, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (Order) op.getEntity();
		if (res != null)
		{
			for (Order compared : orders)
				if (compared.getOrderID() == res.getOrderID())
					return compared;

			orders.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the Order buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllOrders ()
	{
		List<Order> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_ORDER_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<Order>) op.getEntity();
		if (res != null)
		{
			for (Order resS : res)
			{
				addCurrent = true;
				for (Order compared : orders)
					if (compared.getOrderID() == resS.getOrderID())
						addCurrent = false;
				
				if (addCurrent)
					orders.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the Order with the given ID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the order to be removed.
	 */
	public static void evictOrder (int id)
	{
		for (Order order : orders)
		{
			if (order.getOrderID() == id)
			{
				Op op = new Op (Operations.UPDATE_ORDER,order);
				LoginCont.client.handleMessageFromClientUI(op);
				
				orders.remove (order);
				break;
			}
		}
	}
	
	/**
	 *  Clears the Order buffer entirely
	 */
	public static void evictOrderAll ()
	{
		for (Order order : orders)
		{
			Op op = new Op (Operations.UPDATE_ORDER,order);
			LoginCont.client.handleMessageFromClientUI(op);
				
			orders.remove (order);
		}
	}

	/**
	 * Updates the Order with the given ID in the DB.
	 * @param id of the order to be updated
	 */
	public static void updateOrder (int id)
	{
		for (Order order : orders)
		{
			if (order.getOrderID() == id)
			{
				Op op = new Op (Operations.UPDATE_ORDER,order);
				LoginCont.client.handleMessageFromClientUI(op);

				break;
			}
		}
	}
}
