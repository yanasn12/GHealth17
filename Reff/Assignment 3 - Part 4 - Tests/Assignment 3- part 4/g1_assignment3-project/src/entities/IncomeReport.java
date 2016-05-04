package entities;

import java.io.Serializable;
import java.util.List;
import controllers.WorkerControl;

/**
 * 
 * This class represents a quarter income report.
 *
 */

public class IncomeReport extends Report implements Serializable {

	private static final long serialVersionUID = 9127647093191006279L;
	private int incomeStationID;
	private double totalIncome;
	
	// constructor //
	
	public IncomeReport(){
		// need TBD
	}
	
	// setters and getters //

	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public int getIncomeStationID() {
		return incomeStationID;
	}

	public void setIncomeStationID(int incomeStationID) {
		this.incomeStationID = incomeStationID;
	}
	
	public String toString()
	{
		return new String(super.toString()+" StationID:"+getIncomeStationID());
	}
	/**
	 * gets the report in the shape of a table.
	 * 
	 */
	public String[][] getReportTable() {
		String[][] table = new String [2][1];
		
		table[0][0] = "Empty";
		table[1][0] = "";

		return table;	
	}
	/**
	 * get the report fields to the table.
	 * 
	 */
	public String [][] getGeneralReportData() {
		
		String[][] table = new String[2][3];
		
		table[0][0] = "StationID";
		table[0][1] = "Station Address";
		table[0][2] = "Total Income";
		table[1][0] = ""+incomeStationID;
		table[1][1] = WorkerControl.getStationById(incomeStationID).getLocalAddr();
		table[1][2] = ""+totalIncome;

		return table;
	}
}
