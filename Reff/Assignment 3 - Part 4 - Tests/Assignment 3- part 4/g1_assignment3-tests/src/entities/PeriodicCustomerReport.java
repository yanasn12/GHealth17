package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import controllers.PeriodicCustomerReportControl;

/**
 * 
 * This class represents a periodic report that describes customers specification purchases, organized by weighting level -
 * customers who committed the purchasing activities and their dispersal among various fuel supply companies
 *
 */

public class PeriodicCustomerReport extends Report implements Serializable{
	
	private static final long serialVersionUID = -4868833950088292536L;
	
	/**
	 * 
	 * This method brings all the DataPerCustomer that has specific ReportID
	 * 
	 * @return List of DataPerCustomer that has the specific ReportID
	 */
	public List<DataPerCustomer> getDatasPerCustomer ()
	{
		LinkedList <DataPerCustomer> res = new LinkedList <DataPerCustomer> ();
		
		for (DataPerCustomer item : PeriodicCustomerReportControl.getAllDatasPerCustomer())
		{
			if (item.getReportID() == reportID)
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
		List<DataPerCustomer> tableData = getDatasPerCustomer();
		table = new String [tableData.size()+1][3];
		
		table[0][0] = "CustomerID";
		table[0][1] = "Customer Type";
		table[0][2] = "Order Count";
		
		int i = 1;
		DataPerCustomer maxData = null;
		int maxOrderCnt;
		while (tableData.size() > 0)
		{
			maxOrderCnt = -1;
			for (DataPerCustomer data : tableData)
			{
				if (data.getOrderCount() > maxOrderCnt)
				{
					maxData = data;
					maxOrderCnt = data.getOrderCount();
				}
			}
			table[i][0] = ""+maxData.getCustomerID();
			table[i][1] = maxData.getCustomer().getUserType().getPosName();
			table[i][2] = ""+maxData.getOrderCount();
			tableData.remove(maxData);
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
		
		String[][] table = new String[2][1];
		
		table[0][0] = "Empty";
		table[1][0] = "";

		return table;
	}
}
