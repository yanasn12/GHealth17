package entities;

import java.io.Serializable;

import controllers.HouseFuelOrderControl;

/**
 * 
 * This class stores information about housefuel, theres info about order and its status of delivery, estimated time left,
 *
 */

public class HouseFuelInfo implements Serializable {

	private static final long serialVersionUID = -7988999421476658360L;
	
	int houseFuelInfoID;
	int deliveryStatus;
	int estimatedTimeLeft;
	int houseFuelOrderID;
	HouseFuelOrder houseFuelOrder;
	
	// Constructor  //
	/**
	 * constructor.. sets a dummy reference of house fuel order to the house fuel info
	 */
	public HouseFuelInfo(){
		houseFuelOrder = HouseFuelOrderControl.dummyHouseFuelOrder;
	}
	
	// setters and getter //
	
	public int getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(int deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public int getEstimatedTimeLeft() {
		return estimatedTimeLeft;
	}
	public void setEstimatedTimeLeft(int estimatedTimeLeft) {
		this.estimatedTimeLeft = estimatedTimeLeft;
	}

	public int getHouseFuelInfoID() {
		return houseFuelInfoID;
	}

	public void setHouseFuelInfoID(int houseFuelInfoID) {
		this.houseFuelInfoID = houseFuelInfoID;
	}

	public int getHouseFuelOrderID() {
		return houseFuelOrderID;
	}

	public void setHouseFuelOrderID(int houseFuelOrderID) {
		this.houseFuelOrderID = houseFuelOrderID;
	}
	/**
	 * method to get the house fuel order of that houseFuel,checks if the houseFuel Order attached is a dummy reference.
	 * if it is, gets the info from the database and returns it.
	 * @return
	 */
	public HouseFuelOrder getHouseFuelOrder() {
		if (houseFuelOrder == HouseFuelOrderControl.dummyHouseFuelOrder)
			houseFuelOrder = HouseFuelOrderControl.getHouseFuelOrderById(houseFuelOrderID);
		return houseFuelOrder;
	}
	/**
	 * method to set a houseFuel order to the current house fuel info, checks if the attached order is a dummy reference.
	 *  and if it isn't sets the HouseFuelOrderID to the order reference.
	 * @param houseFuelOrder
	 */
	public void setHouseFuelOrder(HouseFuelOrder houseFuelOrder) {
		this.houseFuelOrder = houseFuelOrder;
		if (houseFuelOrder != HouseFuelOrderControl.dummyHouseFuelOrder)
			houseFuelOrderID = houseFuelOrder.getOrderID();
	}
	
	

}
