package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import controllers.StockReportControl;
import controllers.WorkerControl;

/**
 *
 * Report table with info about the sales of each FuelType in the station in the given time period. 
 *
 */

public class PurchasesReport extends Report implements Serializable {
	
	private static final long serialVersionUID = -6762465685067215010L;
	private int purchasesStationID;
	
	public int getPurchasesStationID() {
		return purchasesStationID;
	}

	public void setPurchasesStationID(int stationID) {
		this.purchasesStationID = stationID;
	}
	/**
	 * 
	 * This method is getting a AmountDatasPerFuel information.
	 * the method checks if the user AmountDatasPerFuel information exists is a dummy reference
	 * if it is, get the AmountDatasPerFuel information from the DataBase.
	 * 
	 * @return res  A list of AmountDatasPerFuel that has the specific PurchaseReportID 
	 */
	public List<AmountDataPerFuel> getAmountDatasPerFuel() {
		LinkedList <AmountDataPerFuel> res = new LinkedList<AmountDataPerFuel>();
		
		for (AmountDataPerFuel item : StockReportControl.getAllAmountDatasPerFuel())
		{
			if (item.getPurchaseReportID() == reportID)
				res.add(item);
		}
		return res;
	}
	/**
	 * 
	 * This method is putting the data that needed to the report and display it
	 * 
	 * @return table with the data for the report
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
	 * This method is putting the data that needed to the general report and display it
	 * 
	 * @return table with the data for the report
	 */
	public String [][] getGeneralReportData() {
		
		String[][] table = new String[2][2];
		
		table[0][0] = "StationID";
		table[0][1] = "Station Address";
		table[1][0] = ""+ purchasesStationID;
		table[1][1] = WorkerControl.getStationById(purchasesStationID).getLocalAddr();

		return table;
	}
	
	public String toString()
	{
		return new String(super.toString()+" StationID:"+getPurchasesStationID());
	}
}
