package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import controllers.FuelTypeControl;
import controllers.StockControl;
import controllers.WorkerControl;

/**
 * 
 * This class represents all the information about fuel stocks.
 *
 */

public class FuelStock implements Serializable {
	
	private static final long serialVersionUID = 6323120200570975856L;
	
	int fuelStockID;
	int stockAmount;
	int minStockLevel;
	int maxStockLevel;
	int stationID;
	Station station;
	int fuelTypeID;
	FuelType fuelType;
	int replenishID;
	FuelReplenish lastFuelReplenish;
	
    //  Constructor //
	
	public FuelStock(){
		station = WorkerControl.dummyStation;
		fuelType = FuelTypeControl.dummyFuelType;
		lastFuelReplenish = StockControl.dummyFuelReplenish;
	}

	// setters and getters //
	
	public int getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(int stockAmount) {
		this.stockAmount = stockAmount;
	}

	public int getMinStockLevel() {
		return minStockLevel;
	}

	public void setMinStockLevel(int minStockLevel) {
		this.minStockLevel = minStockLevel;
	}

	public int getMaxStockLevel() {
		return maxStockLevel;
	}

	public void setMaxStockLevel(int maxStockLevel) {
		this.maxStockLevel = maxStockLevel;
	}
	
	public int getFuelStockID() {
		return fuelStockID;
	}

	public void setFuelStockID(int fuelStockID) {
		this.fuelStockID = fuelStockID;
	}
	/**
	 * method that gets the last fuel replenish for the current fuel stock
	 * checks if the fuel Stock's last replenish is a dummy reference.
	 * if it isn't, gets and stores the last fuel replenish for that fuel stock. 
	 * @return
	 */
	public FuelReplenish getLastReplenish() {
		if (lastFuelReplenish == StockControl.dummyFuelReplenish)
			lastFuelReplenish = StockControl.getFuelReplenishById(replenishID);
		return lastFuelReplenish;
	}
	/**
	 * method to set the last fuel Replenish for the current fuel Stock.
	 * checks if the Last fuel replenish info is a dummy reference and if it is, sets the last Replenish info in the class.
	 * @param replenish
	 */
	public void setLastReplenish(FuelReplenish replenish) {
		this.lastFuelReplenish = replenish;
		if (lastFuelReplenish != StockControl.dummyFuelReplenish)
			this.replenishID=replenish.getFuelReplenishID();
	}
	/**
	 * method to get the station info that relates to the current fuel stock.
	 * the method check if the existed station info is a dummy reference and if it is, gets the Station information from the Database and puts it in the Station reference
	 * @return
	 */
	public Station getStation() {
		if (station == WorkerControl.dummyStation)
			station = WorkerControl.getStationById(stationID);
		return station;
	}
	/**
	 * method to set a station information to the current fuel stock.
	 * method checks if the Station attached to the current fuelstock is a dummy reference and if it is, sets a station info instead.
	 * @param station
	 */
	public void setStation(Station station) {
		this.station = station;
		if (station != WorkerControl.dummyStation)
			this.stationID=station.getStationID();
	}
	/**
	 * method to get the information about the fuelstock's fuel type
	 * the method checks if the fuel type reference is a dummy reference and if it is, gets the information from the database nad sets it in the reference.
	 * @return
	 */
	public FuelType getFuelType() {
		if (fuelType == FuelTypeControl.dummyFuelType)
			fuelType = FuelTypeControl.getFuelTypeById(fuelTypeID);
		return fuelType;
	}
	/**
	 * method to attach a fuel type to the current fuel stock,
	 * method gets a fueltype and checks if the existing fuel type reference is a dummy, and if it is, sets the fuel type reference.
	 * 
	 * @param fuelType
	 */
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
		if (fuelType != FuelTypeControl.dummyFuelType)
			this.fuelTypeID=fuelType.getFuelTypeID();
	}
	
	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public int getFuelTypeID() {
		return fuelTypeID;
	}

	public void setFuelTypeID(int fuelTypeID) {
		this.fuelTypeID = fuelTypeID;
	}

	public int getLastReplenishID() {
		return replenishID;
	}

	public void setLastReplenishID(int replenishID) {
		this.replenishID = replenishID;
	}
	/**
	 * method to get all fuel replenishes for the current fuel stock,
	 * the method searches the fuel replenishes by comparing fueltypeID and StationID and returs a list
	 * @return
	 */
	public List<FuelReplenish> getFuelReplenishes ()
	{
		LinkedList<FuelReplenish> res = new LinkedList<FuelReplenish> ();
		
		for (FuelReplenish item : StockControl.getAllFuelReplenishes())
		{
			if (item.getFuelTypeID() == fuelTypeID && item.getStationID() == stationID)
				res.add(item);
		}
		
		return res;
	}
}
