package controllers;

import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.Report;

/**
 * This Class provides methods to manage reports.
 * 
 * Manages: {@code Report}
 * Active:  {@code Report}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class ReportControl {
	
	static List<Report> reports;
	static Report active;
	
	/**
	 * signifies that this {@code Report} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static Report dummyReport = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		reports = new LinkedList <Report> ();
		//dummyReport = new Report ();
	}
	
	// ===================================== BUFFERS ==================================

	/**
	 *  returns the Reports buffer that contains all Reports. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<Report> buffer.
	 */
	public static List<Report> getReports() {
		return reports;
	}
	
	/**
	 *  Returns a {@code Report} out of the {@code Report} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The ReportID that is searched for.
	 * @return {@code Report} with matching ID or NULL if entity was not found.
	 */
	public static Report getReportById(int id){
		
		
		for (Report report : reports)
		{
			if (report.getReportID() == id)
				return report;
		}
		return fetchReport(id);
	}
	
	/**
	 * Returns a list of all Reports after updating it from the DB. 
	 * @return {@code List<Report>} buffer
	 */
	public static List<Report> getAllReports(){
		
		fetchAllReports ();
		return reports;
	}
	
	// ===================================== ACTIVE HANDLING ===================================
	
	/**
	 *  Returns the {@code Report} that is currently being worked on.
	 * @return {@code Report} loaded as active.
	 */
	public static Report getActive() {
		return active;
	}
	
	/**
	 *  Designates a new {@code Report} as the one being worked on. 
	 * @param activeReport The {@code Report} to be worked on.
	 */
	public static void setActive(Report activeReport) {
		active = activeReport;
	}
	
	/**
	 *  Initializes a new {@code Report} and puts it as active.
	 */		
	public static void loadNewActive ()
	{
		active = new Report ();
	}	
	
	// ===================================== DB ACESSS ===============================
	
	/**
	 *  Attempts to fetch a {@code Report} from DB. Recommended using {@code getReportById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code Report} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static Report fetchReport (int id)
	{
		Report res = null;
		
		Op op = new Op (Operations.GET_REPORT, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (Report) op.getEntity();
		if (res != null)
		{
			for (Report compared : reports)
				if (compared.getReportID() == res.getReportID())
					return compared;
				reports.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the Report buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllReports ()
	{
		List<Report> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_REPORT_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<Report>) op.getEntity();
		if (res != null)
		{
			for (Report resS : res)
			{
				addCurrent = true;
				for (Report compared : reports)
					if (compared.getReportID() == resS.getReportID())
						addCurrent = false;
				
				if (addCurrent)
					reports.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the Report with the given ReportID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the Report to be removed.
	 */
	public static void evictReport (int id)
	{
		for (Report report : reports)
		{
			if (report.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_REPORT,report);
				LoginCont.client.handleMessageFromClientUI(op);
				
				reports.remove (report);
				break;
			}
		}
	}
	
	/**
	 * Updates the Report with the given ReportID in the DB.
	 * @param id of the Report to be updated
	 */
	public static void updateReport (int id)
	{
		for (Report report : reports)
		{
			if (report.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_REPORT,report);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
}
