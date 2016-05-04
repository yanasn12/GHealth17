package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import controllers.GeneratedReportControl;

/**
 * 
 * This class represents report produced by ratings according to different cases.
 *
 */

public class GeneratedScoreReport extends Report implements Serializable {
	
	private static final long serialVersionUID = -3777045926410436817L;
	
	int GeneratedScoreReportID;
	
	// constructor //
	
	public GeneratedScoreReport(){
		// need TBD
	}

    //  setters and getters //
	
	public int getGeneratedScoreReportID() {
		return GeneratedScoreReportID;
	}

	public void setGeneratedScoreReportID(int generatedScoreReportID) {
		GeneratedScoreReportID = generatedScoreReportID;
	}
	/**
	 * method to get all the scores per fuel type that relates to this current Generated Score Report. 
	 * @return
	 */
	public List<ScorePerFuelType> getScoresPerFuelType ()
	{
		LinkedList<ScorePerFuelType> res = new LinkedList<ScorePerFuelType> ();
		
		for (ScorePerFuelType item : GeneratedReportControl.getAllScoresPerFuelType())
		{
			if (item.getGeneratedScoreReportID() == reportID)
				res.add(item);
		}
		
		return res;
	}
	/**
	 * method to get all data per customer types related to this current Generated Score Report
	 * @return
	 */
	public List<DataPerCustomerType> getDatasPerCustomerType ()
	{
		LinkedList <DataPerCustomerType> res = new LinkedList<DataPerCustomerType>();
		
		for (DataPerCustomerType item : GeneratedReportControl.getAllDatasPerCustomerType())
		{
			if (item.getGeneratedScoreReportID() == reportID)
				res.add(item);
		}
		return res;
	}
	/**
	 * method to get a table that will later contain the reports data.
	 * 
	 */
	public String[][] getReportTable() {
		String[][] table;
		List<DataPerCustomerType> tableData = getDatasPerCustomerType();
		table = new String [tableData.size()+1][3];
		
		table[0][0] = "Customer Type";
		table[0][1] = "Score";
		table[0][2] = "Hour Scores (00:00-23:00)";
		
		int i = 1;
		for (DataPerCustomerType data : tableData)
		{
			table[i][0] = ""+data.getUserType().getPosName();
			table[i][1] = ""+data.getScore();
			
			String hourScore = "";
			for (int val : data.getHourScore())
				hourScore += String.format("%2d|", val);
			table[i][2] = hourScore;
			i++;
		}
		return table;
		
	}
	/**
	 * method to get the general report data in the shape of a table.
	 * 
	 */
	public String [][] getGeneralReportData() {
		
		List<ScorePerFuelType> tableData = getScoresPerFuelType ();
		String[][] table = new String[tableData.size()+2][3];
		
		table[0][0] = "#Split@data";
		table[0][1] = "#Split@data";
		table[0][2] = "#Split@data";
		
		table[1][0] = "FuelTypeID";
		table[1][1] = "Fuel Name";
		table[1][2] = "Fuel Score";
		
		int i = 2;
		for (ScorePerFuelType data : tableData)
		{
			table[i][0] = ""+data.getFuelTypeID();
			table[i][1] = data.getFuelType().getFuelName();
			table[i][2] = ""+data.getScore();
			i++;
		}
		return table;
	}
}
