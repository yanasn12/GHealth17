package entities;

import java.io.Serializable;

import controllers.StockControl;

/**
 * 
 * This class is used to replenish a fuelstock, using the replenish info the fuelstock level can be updated properly, every storage filling is registered as a fuel Stock
 */


public class FuelReplenish implements Serializable {

	private static final long serialVersionUID = 6666104641168426407L;
	
	int fuelReplenishID;
	int amount;
	int status;
	String deliveryDate;
	int fuelTypeID;
	int stationID;
	FuelStock fuelStock;
	
	//  Constructor //
	
	public FuelReplenish(){
		fuelStock = StockControl.dummyFuelStock;
	}
	
	// setters and getters //

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getFuelReplenishID() {
		return fuelReplenishID;
	}

	public void setFuelReplenishID(int fuelReplenishID) {
		this.fuelReplenishID = fuelReplenishID;
	}

	public int getFuelTypeID() {
		return fuelTypeID;
	}

	public void setFuelTypeID(int fuelTypeID) {
		this.fuelTypeID = fuelTypeID;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}
	/**
	 * method to get the relevant FuelStock for the current Fuel Replenish,
	 * if the FuelStock info attached is a dummy reference it gets the information 
	 * @return
	 */
	public FuelStock getFuelStock() {
		if (fuelStock == StockControl.dummyFuelStock)
			fuelStock = StockControl.getFuelStockById(fuelTypeID, stationID);
		return fuelStock;
	}
	/**
	 * method to set the relevant fuel Stock to that current replenish
	 * the method check if the reference of the fuel stock is a dummy and if it is, sets a FuelStock reference to the replenish.
	 * @param fuelStock
	 */
	public void setFuelStock(FuelStock fuelStock) {
		this.fuelStock = fuelStock;
		if (fuelStock != StockControl.dummyFuelStock)
		{
			this.fuelTypeID = fuelStock.getFuelTypeID();
			this.stationID = fuelStock.getStationID();
		}
	}
	public String toString()
	{
		
		return new String("ReplenishID:"+getFuelReplenishID()+"\nAmount:"+getAmount()+"\nstatus"+getStatus()+"\ndeliveryDate"+getDeliveryDate()+"\nfuelTypeID"+getFuelTypeID()+"StationID:"+getStationID());
	}
	
	

}
