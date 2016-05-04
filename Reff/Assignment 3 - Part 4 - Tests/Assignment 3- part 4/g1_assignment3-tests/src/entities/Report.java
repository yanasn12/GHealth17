package entities;

import java.io.Serializable;

/**
 * 
 * This class represents a general Report template with the common attributes for all reports
 *
 */

public class Report implements Serializable, IReportable {

	private static final long serialVersionUID = -5745120781080262927L;
	protected int reportID;
	protected String reportTitle;
	protected String reportDate;
	protected String reportDesc;
	

	//setters and getters
	public int getReportID ()
	{
		return reportID;
	}
	public void setReportID (int reportID)
	{
		this.reportID = reportID;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportDesc() {
		return reportDesc;
	}

	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}

	public String[][] getReportTable() {
		return new String[0][0];
	}

	public String [][] getGeneralReportData() {
		
		return new String[0][0];
	}
	

}
