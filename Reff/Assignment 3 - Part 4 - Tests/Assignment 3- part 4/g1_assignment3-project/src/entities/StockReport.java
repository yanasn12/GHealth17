package entities;

import java.io.Serializable;
import java.util.LinkedList;
//import java.util.LinkedList;
import java.util.List;

import controllers.StockReportControl;
import controllers.WorkerControl;

/**
 * 
 *  Report table with info about the stock of each FuelType in the station in the given time period. 
 *
 */

public class StockReport extends Report implements Serializable {
	
	private static final long serialVersionUID = -2239441125340629105L;
	private int stationID;
	Station station;
	
	// constructor //
	public StockReport (){
		station = WorkerControl.dummyStation;
	}

	public int getStockStationID() {
		return stationID;
	}

	public void setStockStationID(int stockStationID) {
		this.stationID = stockStationID;
	}
	
	
	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}
	/**
	 * This method to get a Station information.
	 * the method checks if the Station information exists is a dummy reference
	 * if it is, get the Station information from the DataBase and sets it in the Station reference 
	 * 
	 * @return Station the station that needed
	 */
	public Station getStation() {
		if (station == WorkerControl.dummyStation)
			station = WorkerControl.getStationById(stationID);
		return station;
	}
	/**
	 * This method to set a Station information.
	 * the method checks if the Station information exists is a dummy reference
	 * if it is, get the Station information from the DataBase and sets it in the Station reference
	 * 
	 * @param Station The input Station that need to be set
	 */
	public void setStation(Station station) {
		this.station = station;
		if (station != WorkerControl.dummyStation)
			stationID = station.getStationID();
	}

	public String toString()	
	{
		return new String(super.toString()+" stationID:"+getStockStationID());
	}
	/**
	 * 
	 * This method brings all the AmountDataPerFuel that has specific StockReportID
	 * 
	 * @return List of AmountDataPerFuel that has the specific StockReportID
	 */
	public List<AmountDataPerFuel> getAmountDatasPerFuel ()
	{
		LinkedList <AmountDataPerFuel> res = new LinkedList <AmountDataPerFuel> ();
		
		for (AmountDataPerFuel item : StockReportControl.getAllAmountDatasPerFuel())
		{
			if (item.getStockReportID() == reportID)
				res.add(item);
		}
		return res;
	}
	/**
	 *
	 * This method get a table of stock report from the DataBase and display it in the report
	 * 
	 * @return String[][] A table with the information needed to the report
	 */

	public String[][] getReportTable() {
		String[][] table;
		List<AmountDataPerFuel> tableData = getAmountDatasPerFuel();
		table = new String [tableData.size()+1][3];
		
		table[0][0] = "FuelTypeID";
		table[0][1] = "Fuel Name";
		table[0][2] = "Amount";
		
		int i = 1;
		for (AmountDataPerFuel data : tableData)
		{
			table[i][0] = ""+data.getFuelTypeID();
			table[i][1] = data.getFuelType().getFuelName();
			table[i][2] = ""+data.getAmount();
			i++;
		}
		return table;
		
	}
	/**
	 * 
	 * This method get a table of station from the DataBase and display it in the report
	 * 
	 * @return String [][] A table with the information needed to the general report
	 */
	public String [][] getGeneralReportData() {
		
		String[][] table = new String[2][2];
		
		table[0][0] = "StationID";
		table[0][1] = "Station Address";
		table[1][0] = ""+stationID;
		table[1][1] = getStation().getLocalAddr();

		return table;
	}
}
