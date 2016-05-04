package controllers;

import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.Car;
import entities.IncomeReport;

/**
 * This Class provides methods to manage IncomeReports.
 * 
 * Manages: {@code IncomeReport}
 * Active:  {@code IncomeReport}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class IncomeReportControl {
	
	static List<IncomeReport> incomeReports;
	static IncomeReport active;
	
	/**
	 * signifies that this {@code  IncomeReport} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static IncomeReport dummyPurchaseReport = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl()
	{
		incomeReports = new LinkedList<IncomeReport>();
		
		//dummyPurchaseReport = new IncomeReport();
	}
	
	// ===================================== BUFFERS ==================================
	
	/**
	 *  returns the IncomeReports buffer that contains all IncomeReports. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<IncomeReport> buffer.
	 */
	public static List<IncomeReport> getIncomeReports() {
		return incomeReports;
	}
	
	/**
	 *  Returns a {@code IncomeReport} out of the {@code IncomeReport} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The ReportID that is searched for.
	 * @return {@code IncomeReport} with matching ID or NULL if entity was not found.
	 */
	public static IncomeReport getIncomeReportById(int id){
		
		
		for (IncomeReport incomeReport : incomeReports)
		{
			if (incomeReport.getReportID() == id)
				return incomeReport;
		}
		return fetchIncomeReport(id);
	}
	
	/**
	 * Returns a list of all IncomeReports after updating it from the DB. 
	 * @return {@code List<IncomeReport>} buffer
	 */
	public static List<IncomeReport> getAllIncomeReports(){
		
		fetchAllIncomeReports ();
		return incomeReports;
	}
	
	// ===================================== ACTIVE HANDLING ===================================
	
	/**
	 *  Returns the {@code IncomeReport} that is currently being worked on.
	 * @return {@code IncomeReport} loaded as active.
	 */
	public static IncomeReport getActive() {
		return active;
	}
	
	/**
	 *  Designates a new {@code IncomeReport} as the one being worked on. 
	 * @param activeIncomeReport The {@code IncomeReport} to be worked on.
	 */
	public static void setActive(IncomeReport activeIncomeReport) {
		active = activeIncomeReport;
	}
	
	/**
	 *  Initializes a new {@code IncomeReport} and puts it as active.
	 */		
	public static void loadNewActive ()
	{
		active = new IncomeReport ();
	}	

	// ===================================== DB ACESSS ===============================

	/**
	 *  Attempts to fetch a {@code IncomeReport} from DB. Recommended using {@code getIncomeReportById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code IncomeReport} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static IncomeReport fetchIncomeReport (int id)
	{
		IncomeReport res = null;
		
		Op op = new Op (Operations.GET_INCOME_REPORT, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (IncomeReport) op.getEntity();
		if (res != null)
		{
			for (IncomeReport compared : incomeReports)
				if (compared.getReportID() == res.getReportID())
					return compared;
				incomeReports.add(res);
				ReportControl.getReports().add(res);
		}
		return res;
	}
	
	/**
	 *  updates the IncomeReport buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllIncomeReports ()
	{
		List<IncomeReport> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_INCOME_REPORT_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<IncomeReport>) op.getEntity();
		if (res != null)
		{
			for (IncomeReport resS : res)
			{
				addCurrent = true;
				for (IncomeReport compared : incomeReports)
					if (compared.getReportID() == resS.getReportID())
						addCurrent = false;
				
				if (addCurrent)
				{
					incomeReports.add(resS);
					ReportControl.getReports().add(resS);
				}
			}
		}
	}
	
	/**
	 *  Removes the IncomeReport with the given ReportID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the IncomeReport to be removed.
	 */
	public static void evictIncomeReport (int id)
	{
		for (IncomeReport incomeReport : incomeReports)
		{
			if (incomeReport.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_INCOME_REPORT,incomeReport);
				LoginCont.client.handleMessageFromClientUI(op);
				
				incomeReports.remove (incomeReport);
				ReportControl.getReports().remove(incomeReport);
				break;
			}
		}
	}
	
	/**
	 * Updates the IncomeReport with the given ReportID in the DB.
	 * @param id of the IncomeReport to be updated
	 */
	public static void updateIncomeReport (int id)
	{
		for (IncomeReport incomeReport : incomeReports)
		{
			if (incomeReport.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_INCOME_REPORT,incomeReport);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given IncomeReport in the DB.
	 * @param report to be created in the DB.
	 */
	public static void createIncomeReport (IncomeReport report)
	{
		Op op = new Op (Operations.CREATE_INCOME_REPORT,report);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
