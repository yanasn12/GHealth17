package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import controllers.CustomerControl;
import controllers.OrderControl;
import controllers.StockControl;

/**
 * 
 * This class represents all the information about fuel types.
 *
 */

public class FuelType implements Serializable {
	
	private static final long serialVersionUID = -8534248168388109595L;
	
	int fuelTypeID;
	String fuelName;
	double basePrice;
	
	// constructor //
	
	public FuelType(){
		// need TBD
	}
	
	//  setters and getters //
	
	public String getFuelName() {
		return fuelName;
	}
	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public int getFuelTypeID() {
		return fuelTypeID;
	}

	public void setFuelTypeID(int fuelTypeID) {
		this.fuelTypeID = fuelTypeID;
	}
	/**
	 * method that gets all the orders that has the same fuel type.
	 * @return
	 */
	public List<Order> getOrders ()
	{
		LinkedList <Order> res = new LinkedList <Order> ();
		
		for (Order item : OrderControl.getAllOrders())
		{
			if (item.getFuelTypeID() == fuelTypeID)
				res.add(item);
		}
		return res;
	}
	/**
	 * method returns all the fuelstocks that has the same FuelTypeID as the current fueltype.
	 * 
	 * @return
	 */
	public List<FuelStock> getStocks ()
	{
		LinkedList <FuelStock> res = new LinkedList<FuelStock>();
		
		for (FuelStock item : StockControl.getAllFuelStocks())
		{
			if (item.getFuelTypeID() == fuelTypeID)
				res.add(item);
		}
		return res;
	}
	/**
	 * method that gets all the cars that has the same fuel types as the current FuelType.
	 * the cars are returned in a List.
	 * @return
	 */
	public List<Car> getCars ()
	{
		LinkedList <Car> res = new LinkedList<Car>();
		
		for (Car item : CustomerControl.getAllCars())
		{
			if (item.getFuelTypeID() == fuelTypeID)
				res.add(item);
		}
		return res;
	}
	
	public String toString()
	{
		return new String("Id: "+getFuelTypeID()+"\nTypeName:"+getFuelName()+"\nBasePrice:"+getBasePrice());
	}
}
