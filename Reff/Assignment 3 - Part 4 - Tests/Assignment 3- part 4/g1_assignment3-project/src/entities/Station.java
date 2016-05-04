package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import controllers.CarFuelOrderControl;
import controllers.StockControl;

/**
 * 
 * This class represents a station and its properties 
 *
 */

public class Station extends Location  implements Serializable {
	
	private static final long serialVersionUID = -3594268888052560473L;
	
	// constructor  //
	
	public Station(){
	// need TBD	
	}	

	public void setStationID(int stationID) {
		this.locationID = stationID;
	}

	// method //
	public int getStationID() {
		return locationID;
	}
	
	public List<FuelStock> getFuelStocks ()
	{
		LinkedList<FuelStock> res = new LinkedList<FuelStock> ();
		
		for (FuelStock item : StockControl.getAllFuelStocks())
		{
			if (item.getStationID() == locationID)
				res.add(item);
		}
		
		return res;
	}
	
	public List<CarFuelOrder> getCarFuelOrders ()
	{
		LinkedList<CarFuelOrder> res = new LinkedList <CarFuelOrder> ();
		
		for (CarFuelOrder item : CarFuelOrderControl.getAllCarFuelOrders())
		{
			if (item.getStationID() == locationID)
				res.add(item);
		}
		
		return res;
	}
}
