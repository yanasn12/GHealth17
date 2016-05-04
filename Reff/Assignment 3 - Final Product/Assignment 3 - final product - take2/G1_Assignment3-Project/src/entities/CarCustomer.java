package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
//import java.util.List;

import controllers.CustomerControl;
import controllers.PurchasePlanControl;

/**
 * 
 * This class represents a CarCustomer which holds a list of cars and a purchase plan in addition to the standard customer info.
 *
 */

public class CarCustomer extends Customer implements Serializable {

	private static final long serialVersionUID = 5613419762223569341L;
	
	int carCustomerID;
	int purchasePlanID;
	PurchasePlan purchasePlan;
	//List<Car> cars;
	
	// constructor //
	
	/**
	 * Creates an empty CarCustomer and sets its refs to dummy refs.
	 */
    public CarCustomer (){
    	purchasePlan = PurchasePlanControl.dummyPurchasePlan;
    }
	
	// method //
	/**
	 * returns PurchasePlan of this class, fetches that info from the DB if the need arises.
	 * @return PurchasePlan related to this class.
	 */
	public PurchasePlan getPurchasePlan (){
		if (purchasePlan == PurchasePlanControl.dummyPurchasePlan)
			purchasePlan = PurchasePlanControl.getPurchasePlanById(purchasePlanID);
		return purchasePlan;	
	}
    
	/**
	 * sets this class's PurchasePlan to the given PurchasePlan and updates the purchasePlanID with the new value
	 * @param plan
	 */
	public void setPurchasePlan (PurchasePlan plan){
		this.purchasePlan = plan;
		if (purchasePlan != PurchasePlanControl.dummyPurchasePlan)
			purchasePlanID = purchasePlan.getPurchasePlanID ();
	}

	public int getPurchasePlanID() {
		return purchasePlanID;
	}

	public void setPurchasePlanID(int purchasePlanID) {
		this.purchasePlanID = purchasePlanID;
	}

	public int getCarCustomerID() {
		return carCustomerID;
	}

	public void setCarCustomerID(int carCustomerID) {
		this.carCustomerID = carCustomerID;
	}
	public String carCustoString()
	{
		return new String(super.toString()+"\nPurchasePlaneID:"+getPurchasePlanID());
	}

	public List<Car> getCars() {
		LinkedList<Car> res = new LinkedList<Car>();
		for (Car item : CustomerControl.getAllCars())
		{
			if (item.getCustomerID() == userID)
				res.add(item);
		}
		
		return res;
	}
}
