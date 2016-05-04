package entities;

import java.io.Serializable;

import controllers.CustomerControl;
import controllers.DiscountTableControl;
import controllers.TrackingControl;

/**
 * 
 * This class stores information about a house fuel order such as order status,estimated time and all the orders data.
 *
 */
public class HouseFuelOrder extends Order implements Serializable {
	
	private static final long serialVersionUID = 6518082433050816198L;

	boolean urgent;
	String deliveryTime;
	int houseFuelInfoID;
	HouseFuelInfo houseFuelInfo;
	int houseOwnerID;
	HouseOwner houseOwner;
	
	// Constructor //
	/**
	 * constructor, sets dummy references to the HouseFuelInfo and the HouseFuelOrder, helps prevent a multiple database access.
	 */
	public HouseFuelOrder (){
		houseFuelInfo = TrackingControl.dummyHouseFuelInfo;
		houseOwner = CustomerControl.dummyHouseOwner;
	}
	
	
	/**
	 * Calculates the overall price of this order after applying the discounts.
	 */
	public void calcPrice(){
		double price = getFuelType().getBasePrice() * quantity;
		
		if (urgent)
			price = price + price * DiscountTableControl.getDiscountTableById(0).getDiscountAt(4);
		if (quantity<= 800 && quantity> 600)
			price = price - price * DiscountTableControl.getDiscountTableById(0).getDiscountAt(5);
		else if (quantity > 800)
			price = price - price * DiscountTableControl.getDiscountTableById(0).getDiscountAt(6);
		
		this.price = price;
	}
	
	// setters and getters //

	public String getDeliveryTime() {
		return deliveryTime;
	}


	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}


	public boolean isUrgent() {
		return urgent;
	}


	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}


	public int getHouseFuelInfoID() {
		return houseFuelInfoID;
	}


	public void setHouseFuelInfoID(int houseFuelInfoID) {
		this.houseFuelInfoID = houseFuelInfoID;
	}

	/**
	 * method to get the HouseFuelInfo the relates to that house fuel order.
	 * the method checks if the reference is a dummy and if it is, pulls the info from the Database and sets it in the reference.
	 * 
	 * @return
	 */
	public HouseFuelInfo getHouseFuelInfo() {
		if (houseFuelInfo == TrackingControl.dummyHouseFuelInfo)
			houseFuelInfo = TrackingControl.getHouseFuelInfoById(houseFuelInfoID);
		return houseFuelInfo;
	}

	/**
	 * method to set a HouseFuel info to the current houseFuelOrder
	 * if the reference isnt a dummy, the ,method sets the HouseFuelInfoID to the order
	 * @param houseFuelInfo
	 */
	public void setHouseFuelInfo(HouseFuelInfo houseFuelInfo) {
		this.houseFuelInfo = houseFuelInfo;
		if (houseFuelInfo != TrackingControl.dummyHouseFuelInfo)
			this.houseFuelInfoID=houseFuelInfo.getHouseFuelInfoID();
	}


	public int getHouseOwnerID() {
		return houseOwnerID;
	}


	public void setHouseOwnerID(int houseOwnerID) {
		this.houseOwnerID = houseOwnerID;
	}

	/**
	 * method to get a house fuel owner that relates to the current house fuel order.
	 * @return
	 */
	public HouseOwner getHouseOwner() {
		if (houseOwner == CustomerControl.dummyHouseOwner)
			houseOwner = CustomerControl.getHouseOwnerById(houseOwnerID);
		return houseOwner;
	}

	/**
	 * method that attaches a house fuel owner to the houseFuelOrder.
	 * @param houseOwner
	 */
	public void setHouseOwner(HouseOwner houseOwner) {
		this.houseOwner = houseOwner;
		if (houseOwner != CustomerControl.dummyHouseOwner)
			this.houseOwnerID=houseOwner.getHouseOwnerID();
	}
	

}
