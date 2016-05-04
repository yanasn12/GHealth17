package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import controllers.CarFuelOrderControl;
import controllers.CustomerControl;
import controllers.FuelTypeControl;
import controllers.SubscriptionControl;

/**
 * This class stores Car information .
 */

public class Car implements Serializable {

	private static final long serialVersionUID = -4148452844009864829L;
	
	int carID;
	int subscriptionID;
	int fuelTypeID;
	int customerID;
	Subscription subscription;
	FuelType fuelType;
	CarCustomer carCustomer;
	
	// constructor //
	
	public Car (){
		subscription = SubscriptionControl.dummySubscription;
		fuelType = FuelTypeControl.dummyFuelType;
		carCustomer = CustomerControl.dummyCarCustomer;
	}
	
	// setter and getter  // 

	public int getCarID() {
		
		return carID;
	}

	public void setCarID(int carId) {
		this.carID = carId;
	}
	
	public int getFuelTypeID()
	{
		return fuelTypeID;
	}
	
	public void setFuelTypeID(int id)
	{
		this.fuelTypeID=id;
		
	}
	
	public int getSubscriptionID()
	{
		return this.subscriptionID;	
		
	}
	
	public void setSubscriptionID(int id)
	{
		this.subscriptionID=id;
	}
	
	public int getCustomerID()
	{
		return this.customerID;
	}
	
	public void setCustomerID(int id )
	{	
		this.customerID=id;
	}
	/**
		 * checks if the subscription reference is a dummy one, and if it is, gets the subscription info in it.
		 * good to prevent multiple  database access.
		 * @return
		 */	
	public Subscription getSubscription()
	{
		if (subscription == SubscriptionControl.dummySubscription)
			subscription = SubscriptionControl.getSubscriptionById(subscriptionID);
		return this.subscription;
	}
	/**
	 * method to set a subscription for a car
	 * checking if the subscription isn't a dummy(meaning if there is a subscription info in this reference)
	 * if it isn't a dummy the method sets a subscription for this car.
	 * @param subscription
	 */
	public void setSubscription(Subscription subscription)
	{
	    this.subscription=subscription;
	    if (subscription != SubscriptionControl.dummySubscription)
	    	this.subscriptionID= subscription.getSubID();
	}
	/**
	 * checks if the FuelType reference is a dummy one, and if it is, gets the FuelType info in it.
	 * good to prevent multiple  database access.
	 * @return
	 */
	public FuelType getFuelType()
	{
		if (fuelType == FuelTypeControl.dummyFuelType)
			fuelType = FuelTypeControl.getFuelTypeById(fuelTypeID);
		return this.fuelType;	
	}
	/**
	 * method to set a FuelType for a car
	 * checking if the FuelType isn't a dummy(meaning if there is a FuelType info in this reference)
	 * if it isn't a dummy the method sets a FuelType for this car.
	 * @param FuelType
	 */
	public void setFuelType(FuelType fueltype )
	{
		 this.fuelType=fueltype;
		 if (fuelType != FuelTypeControl.dummyFuelType)
		 	this.fuelTypeID=fuelType.getFuelTypeID();
	}
	/**
	 * checks if the CarCustomer reference is a dummy one, and if it is, gets the CarCustomer info in it.
	 * good to prevent multiple  database access.
	 * @return
	 */
	public CarCustomer getCarCustomer()
	{
		if (carCustomer == CustomerControl.dummyCarCustomer)
			carCustomer = CustomerControl.getCarCustomerById(customerID);
		return this.carCustomer;
	}
	/**
	 * method to set a CarCustomer for a car
	 * checking if the CarCustomer isn't a dummy(meaning if there is a CarCustomer info in this reference)
	 * if it isn't a dummy the method sets a CarCustomer for this car.
	 * @param FuelType
	 */
	public void setCarCustomer(CarCustomer customer)
	{
		this.carCustomer=customer;
		if (carCustomer != CustomerControl.dummyCarCustomer)
			this.customerID=customer.getCustomerID();		
	}
	/**
	 * method to get all the carFuelOrders for this car.
	 * @return
	 */
	public List<CarFuelOrder> getCarFuelOrders()
	{
		List <CarFuelOrder> res = new LinkedList <CarFuelOrder> ();
		for (CarFuelOrder item : CarFuelOrderControl.getAllCarFuelOrders())
		{
			if (item.getCarID() == carID)
				res.add(item);
		}
		return res;
	}
	
	public String toString()
	{
		return new String("Id: "+getCarID()+"\nSubscriptionID:"+getSubscriptionID()+"\nFuelTypeID:"+getFuelTypeID()+"\nCustomerID:"+getCustomerID());
	}
	
	
	
	


}
