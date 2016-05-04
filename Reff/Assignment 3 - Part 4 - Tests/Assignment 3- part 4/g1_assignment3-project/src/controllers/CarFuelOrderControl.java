package controllers;

import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.CarFuelOrder;
import entities.HouseFuelOrder;
import entities.Invoice;
import entities.Order;
import entities.OrderStatus;
import entities.PeriodicDiscount;
import entities.Receipt;

/**
 * This Class provides methods to manage orders.
 * 
 * Manages: CarFuelOrder
 * Active:  CarFuelOrder
 * Active is used to efficiently access a specific object that is being edited.
 */
public class CarFuelOrderControl {
	/**
	 * signifies that this CarFueOrder reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static CarFuelOrder dummyCarFuelOrder = null;
	
	static List<CarFuelOrder> carFuelOrders;
	static CarFuelOrder active;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		carFuelOrders = new LinkedList<CarFuelOrder> ();
		
		//dummyCarFuelOrder = new CarFuelOrder ();
	}
	
	// ===================================== BUFFERS ==================================
	
	/**
	 *  Returns an {@code CarFuelOrder} out of the buffer with a matching id. If the entity doesn't exist attempts to fetch it from DB.
	 * @param id  The CarFuelOrderID that is searched for.
	 * @return {@code CarFuelOrder} with matching ID or NULL if the entity was not found.
	 */
	public static CarFuelOrder getCarFuelOrderById (int id)
	{
		for (CarFuelOrder order : carFuelOrders)
			if (order.getOrderID() == id)
				return order;
		
		return fetchCarFuelOrder (id);
	}
	
	/**
	 *  updates the CarFuelOrder buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static List<CarFuelOrder> getAllCarFuelOrders ()
	{
		fetchAllCarFuelOrders();
		return carFuelOrders;
	}
	
	/**
	 *  returns the CarFuelOrder buffer that contains all CarFuelOrders. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<CarFuelOrder> buffer.
	 */
	public static List<CarFuelOrder> getCarFuelOrders() {
		return carFuelOrders;
	}
	
	// ===================================== ACTIVE HANDLING ===================================

	public static CarFuelOrder getActive() {
		return active;
	}

	public static void setActive (CarFuelOrder active) {
		active = active;
	}
	
	public static void loadNewActive ()
	{
		active = new CarFuelOrder();
	}
	
	/**
	 * Creates a new carFuelOrder with all the required fields. Reduces the ordered amount from the ful stock and updates the database.
	 * @param stationID the id of the station where the fuelling took place
	 * @param carID the id of the car that refuelled
	 * @param quantity the amount of fuel ordered
	 * @param driverName who drove the car during refuelling.
	 * @return CarFuelOrder created order
	 */
	public static CarFuelOrder generateNewCarFuelOrder(int stationID,int carID,double quantity,String driverName)
	{
		int id;
		CarFuelOrder order = new CarFuelOrder();
		Invoice invoice = new Invoice();
		
		id = -1;
		for (Order ord : OrderControl.getAllOrders())
		{
			if (ord.getOrderID() > id)
				id = ord.getOrderID();
		}
		id++;
		order.setOrderID(id);
		order.setCarID(carID);
		order.setDriverName(driverName);
		order.setFuelTypeID(order.getCar().getFuelTypeID());
		order.setOrderTime(OrderControl.getCurrDateTime());
		order.setQuantity(quantity);
		order.setStationID(stationID);
		order.setStatus(OrderStatus.PAYMENT_PENDING.ordinal());		
		if (StockControl.reduceFromStock(order.getFuelTypeID(), stationID, quantity) == false)
			return null;
		
		id = -1;
		for (Invoice inv : InvoiceControl.getAllInvoices())
		{
			if (inv.getInvoiceID() > id)
				id = inv.getInvoiceID();
		}
		id++;
		
		invoice.setInvoiceID(id);
		order.setInvoiceID(id);
		invoice.setCustomerID(order.getCar().getCustomerID());
		invoice.setDueDate(OrderControl.getCurrDate());
		invoice.setPurchasePlanID(order.getCar().getCarCustomer().getPurchasePlanID());
		invoice.setStatus(OrderStatus.PAYMENT_PENDING.ordinal());
		invoice.setSubscriptionID(order.getCar().getSubscriptionID());
		
		id = 0;
		for (PeriodicDiscount discount : PeriodicDiscountsControl.getAllPeriodicDiscounts())
		{
			if (discount.applyDiscount(order, 10) != 10.0)
			{
				id = discount.getPeriodicDiscountID();
				break;
			}
		}
		order.setPeriodicDiscountID(id);
		order.calcPrice();
		invoice.setTotalPrice(order.getPrice());
		
		InvoiceControl.createInvoice(invoice);
		CarFuelOrderControl.createCarFuelOrder(order);
		return order;
		
	}
	
	// ============================= DB ACCESS ===============================
	
	/**
	 *  Attempts to fetch an {@code CarFuelOrder} from DB. Recommended using {@code getCarFuelOrderById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code CarFuelOrder} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static CarFuelOrder fetchCarFuelOrder (int id)
	{
		CarFuelOrder res = null;
		
		Op op = new Op (Operations.GET_CAR_FUEL_ORDER, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (CarFuelOrder) op.getEntity();
		if (res != null)
		{
			for (CarFuelOrder compared : carFuelOrders)
				if (compared.getOrderID() == res.getOrderID())
					return compared;

			carFuelOrders.add(res);
			OrderControl.getOrders().add(res);
		}
		return res;
	}
	
	/**
	 *  updates the CarFuelOrder buffer with all entities that didn't exist there.
	 *  In CarFuelOrder to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllCarFuelOrders ()
	{
		List<CarFuelOrder> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_CAR_FUEL_ORDER_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<CarFuelOrder>) op.getEntity();
		if (res != null)
		{
			for (CarFuelOrder resS : res)
			{
				addCurrent = true;
				for (CarFuelOrder compared : carFuelOrders)
					if (compared.getOrderID() == resS.getOrderID())
						addCurrent = false;
				
				if (addCurrent)
				{
					carFuelOrders.add(resS);
					OrderControl.getOrders().add(resS);
				}
			}
		}
	}
	
	/**
	 *  Removes the CarFuelOrder with the given ID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the CarFuelOrder to be removed.
	 */
	public static void evictCarFuelOrder (int id)
	{
		for (CarFuelOrder carFuelOrder : carFuelOrders)
		{
			if (carFuelOrder.getOrderID() == id)
			{
				Op op = new Op (Operations.UPDATE_CAR_FUEL_ORDER,carFuelOrder);
				LoginCont.client.handleMessageFromClientUI(op);
				
				carFuelOrders.remove (carFuelOrder);
				OrderControl.getOrders().remove (carFuelOrder);
				break;
			}
		}
	}
	

	/**
	 * Updates the CarFuelOrder with the given ID in the DB.
	 * @param id of the CarFuelOrder to be updated
	 */
	public static void updateCarFuelOrder (int id)
	{
		for (CarFuelOrder carFuelOrder : carFuelOrders)
		{
			if (carFuelOrder.getOrderID() == id)
			{
				Op op = new Op (Operations.UPDATE_CAR_FUEL_ORDER,carFuelOrder);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given CarFuelOrder in the DB.
	 * @param CarFuelOrder to be created in the DB.
	 */
	public static void createCarFuelOrder (CarFuelOrder carFuelOrder)
	{
		Op op = new Op (Operations.CREATE_CAR_FUEL_ORDER ,carFuelOrder);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
