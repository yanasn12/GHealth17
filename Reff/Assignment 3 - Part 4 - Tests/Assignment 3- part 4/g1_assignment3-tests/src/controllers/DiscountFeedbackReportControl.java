package controllers;

import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.Car;
import entities.DiscountFeedbackReport;

/**
 * This Class provides methods to manage DiscountFeedbackReports.
 * 
 * Manages: {@code DiscountFeedbackReport}
 * Active:  {@code DiscountFeedbackReport}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class DiscountFeedbackReportControl {
	
	static List<DiscountFeedbackReport> discountFeedbackReports;
	static DiscountFeedbackReport active;
	
	/**
	 * signifies that this {@code  DiscountFeedbackReport} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static DiscountFeedbackReport dummyPurchaseReport = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl()
	{
		discountFeedbackReports = new LinkedList<DiscountFeedbackReport>();
		
		//dummyPurchaseReport = new DiscountFeedbackReport();
	}
	
	// ===================================== BUFFERS ==================================
	
	/**
	 *  returns the DiscountFeedbackReports buffer that contains all DiscountFeedbackReports. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<DiscountFeedbackReport> buffer.
	 */
	public static List<DiscountFeedbackReport> getDiscountFeedbackReports() {
		return discountFeedbackReports;
	}
	
	/**
	 *  Returns a {@code DiscountFeedbackReport} out of the {@code DiscountFeedbackReport} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The ReportID that is searched for.
	 * @return {@code DiscountFeedbackReport} with matching ID or NULL if entity was not found.
	 */
	public static DiscountFeedbackReport getDiscountFeedbackReportById(int id){
		
		
		for (DiscountFeedbackReport discountFeedbackReport : discountFeedbackReports)
		{
			if (discountFeedbackReport.getReportID() == id)
				return discountFeedbackReport;
		}
		return fetchDiscountFeedbackReport(id);
	}
	
	/**
	 * Returns a list of all DiscountFeedbackReports after updating it from the DB. 
	 * @return {@code List<DiscountFeedbackReport>} buffer
	 */
	public static List<DiscountFeedbackReport> getAllDiscountFeedbackReports(){
		
		fetchAllDiscountFeedbackReports ();
		return discountFeedbackReports;
	}
	
	// ===================================== ACTIVE HANDLING ===================================
	
	/**
	 *  Returns the {@code DiscountFeedbackReport} that is currently being worked on.
	 * @return {@code DiscountFeedbackReport} loaded as active.
	 */
	public static DiscountFeedbackReport getActive() {
		return active;
	}
	
	/**
	 *  Designates a new {@code DiscountFeedbackReport} as the one being worked on. 
	 * @param activeDiscountFeedbackReport The {@code DiscountFeedbackReport} to be worked on.
	 */
	public static void setActive(DiscountFeedbackReport activeDiscountFeedbackReport) {
		active = activeDiscountFeedbackReport;
	}
	
	/**
	 *  Initializes a new {@code DiscountFeedbackReport} and puts it as active.
	 */		
	public static void loadNewActive ()
	{
		active = new DiscountFeedbackReport ();
	}	

	// ===================================== DB ACESSS ===============================

	/**
	 *  Attempts to fetch a {@code DiscountFeedbackReport} from DB. Recommended using {@code getDiscountFeedbackReportById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code DiscountFeedbackReport} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static DiscountFeedbackReport fetchDiscountFeedbackReport (int id)
	{
		DiscountFeedbackReport res = null;
		
		Op op = new Op (Operations.GET_DISCOUNT_FEEDBACK_REPORT, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (DiscountFeedbackReport) op.getEntity();
		if (res != null)
		{
			for (DiscountFeedbackReport compared : discountFeedbackReports)
				if (compared.getReportID() == res.getReportID())
					return compared;
				discountFeedbackReports.add(res);
				ReportControl.getReports().add(res);
		}
		return res;
	}
	
	/**
	 *  updates the DiscountFeedbackReport buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllDiscountFeedbackReports ()
	{
		List<DiscountFeedbackReport> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_DISCOUNT_FEEDBACK_REPORT_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<DiscountFeedbackReport>) op.getEntity();
		if (res != null)
		{
			for (DiscountFeedbackReport resS : res)
			{
				addCurrent = true;
				for (DiscountFeedbackReport compared : discountFeedbackReports)
					if (compared.getReportID() == resS.getReportID())
						addCurrent = false;
				
				if (addCurrent)
				{
					discountFeedbackReports.add(resS);
					ReportControl.getReports().add(resS);
				}
			}
		}
	}
	
	/**
	 *  Removes the DiscountFeedbackReport with the given ReportID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the DiscountFeedbackReport to be removed.
	 */
	public static void evictDiscountFeedbackReport (int id)
	{
		for (DiscountFeedbackReport discountFeedbackReport : discountFeedbackReports)
		{
			if (discountFeedbackReport.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_DISCOUNT_FEEDBACK_REPORT,discountFeedbackReport);
				LoginCont.client.handleMessageFromClientUI(op);
				
				discountFeedbackReports.remove (discountFeedbackReport);
				ReportControl.getReports().remove(discountFeedbackReport);
				break;
			}
		}
	}
	
	/**
	 * Updates the DiscountFeedbackReport with the given ReportID in the DB.
	 * @param id of the DiscountFeedbackReport to be updated
	 */
	public static void updateDiscountFeedbackReport (int id)
	{
		for (DiscountFeedbackReport discountFeedbackReport : discountFeedbackReports)
		{
			if (discountFeedbackReport.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_DISCOUNT_FEEDBACK_REPORT,discountFeedbackReport);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given DiscountFeedbackReport in the DB.
	 * @param discountFeedbackReport to be created in the DB.
	 */
	public static void createDiscountFeedbackReport (DiscountFeedbackReport discountFeedbackReport)
	{
		Op op = new Op (Operations.CREATE_DISCOUNT_FEEDBACK_REPORT,discountFeedbackReport);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
