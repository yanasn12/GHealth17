package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import controllers.HouseFuelOrderControl;

/**
 * 
 * This class stores house owner information.
 * 
 */

public class HouseOwner extends Customer implements Serializable {

	private static final long serialVersionUID = 5040334695613351514L;
	
	int houseOwnerID;
	
	// Constructor //
	
	public HouseOwner (){
		// need TBD
	}

	// get and set //
	
	public int getHouseOwnerID() {
		return customerID;
	}

	public void setHouseOwnerID(int houseOwnerID) {
		this.customerID = houseOwnerID;
	}
	/**
	 * method to get all the house fuel orders relates to the current HouseOwner.
	 * @return
	 */
	public List<HouseFuelOrder> getHouseFuelOrders ()
	{
		LinkedList<HouseFuelOrder> res = new LinkedList <HouseFuelOrder> ();
		
		for (HouseFuelOrder item : HouseFuelOrderControl.getAllHouseFuelOrders())
		{
			if (item.getHouseOwnerID() == userID)
				res.add(item);
		}
		return res;
	}

}
