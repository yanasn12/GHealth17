package controllers;

import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.AmountDataPerFuel;
import entities.Car;
import entities.PurchasesReport;

/**
 * This Class provides methods to manage PurchasesReports.
 * 
 * Manages: {@code PurchasesReport}
 * Active:  {@code PurchasesReport}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class PurchasesReportControl {
	
	static List<PurchasesReport> purchasesReports;
	static PurchasesReport active;
	
	/**
	 * signifies that this {@code  PurchasesReport} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static PurchasesReport dummyPurchaseReport = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl()
	{
		purchasesReports = new LinkedList<PurchasesReport>();
		
		//dummyPurchaseReport = new PurchasesReport();
	}
	
	// ===================================== BUFFERS ==================================
	
	/**
	 *  returns the PurchasesReports buffer that contains all PurchasesReports. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<PurchasesReport> buffer.
	 */
	public static List<PurchasesReport> getPurchasesReports() {
		return purchasesReports;
	}
	
	/**
	 *  Returns a {@code PurchasesReport} out of the {@code PurchasesReport} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The ReportID that is searched for.
	 * @return {@code PurchasesReport} with matching ID or NULL if entity was not found.
	 */
	public static PurchasesReport getPurchasesReportById(int id){
		
		
		for (PurchasesReport purchasesReport : purchasesReports)
		{
			if (purchasesReport.getReportID() == id)
				return purchasesReport;
		}
		return fetchPurchasesReport(id);
	}
	
	/**
	 * Returns a list of all PurchasesReports after updating it from the DB. 
	 * @return {@code List<PurchasesReport>} buffer
	 */
	public static List<PurchasesReport> getAllPurchasesReports(){
		
		fetchAllPurchasesReports ();
		return purchasesReports;
	}
	
	// ===================================== ACTIVE HANDLING ===================================
	
	/**
	 *  Returns the {@code PurchasesReport} that is currently being worked on.
	 * @return {@code PurchasesReport} loaded as active.
	 */
	public static PurchasesReport getActive() {
		return active;
	}
	
	/**
	 *  Designates a new {@code PurchasesReport} as the one being worked on. 
	 * @param activePurchasesReport The {@code PurchasesReport} to be worked on.
	 */
	public static void setActive(PurchasesReport activePurchasesReport) {
		active = activePurchasesReport;
	}
	
	/**
	 *  Initializes a new {@code PurchasesReport} and puts it as active.
	 */		
	public static void loadNewActive ()
	{
		active = new PurchasesReport ();
	}	

	// ===================================== DB ACESSS ===============================

	/**
	 *  Attempts to fetch a {@code PurchasesReport} from DB. Recommended using {@code getPurchasesReportById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code PurchasesReport} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static PurchasesReport fetchPurchasesReport (int id)
	{
		PurchasesReport res = null;
		
		Op op = new Op (Operations.GET_PURCHASES_REPORT, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (PurchasesReport) op.getEntity();
		if (res != null)
		{
			for (PurchasesReport compared : purchasesReports)
				if (compared.getReportID() == res.getReportID())
					return compared;
				purchasesReports.add(res);
				ReportControl.getReports().add(res);
		}
		return res;
	}
	
	/**
	 *  updates the PurchasesReport buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllPurchasesReports ()
	{
		List<PurchasesReport> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_PURCHASES_REPORT_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<PurchasesReport>) op.getEntity();
		if (res != null)
		{
			for (PurchasesReport resS : res)
			{
				addCurrent = true;
				for (PurchasesReport compared : purchasesReports)
					if (compared.getReportID() == resS.getReportID())
						addCurrent = false;
				
				if (addCurrent)
				{
					purchasesReports.add(resS);
					ReportControl.getReports().add(resS);
				}
			}
		}
	}
	
	/**
	 *  Removes the PurchasesReport with the given ReportID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the PurchasesReport to be removed.
	 */
	public static void evictPurchasesReport (int id)
	{
		for (PurchasesReport purchasesReport : purchasesReports)
		{
			if (purchasesReport.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_PURCHASES_REPORT,purchasesReport);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (AmountDataPerFuel item : StockReportControl.getAmountDatasPerFuel())
				{
					if (item.getPurchaseReportID() == purchasesReport.getReportID())
						item.setPurchaseReport(dummyPurchaseReport);
				}
				
				purchasesReports.remove (purchasesReport);
				ReportControl.getReports().remove(purchasesReport);
				break;
			}
		}
	}
	
	/**
	 * Updates the PurchasesReport with the given ReportID in the DB.
	 * @param id of the PurchasesReport to be updated
	 */
	public static void updatePurchasesReport (int id)
	{
		for (PurchasesReport purchasesReport : purchasesReports)
		{
			if (purchasesReport.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_PURCHASES_REPORT,purchasesReport);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given PurchasesReport in the DB.
	 * @param purchasesReport to be created in the DB.
	 */
	public static void createPurchasesReport (PurchasesReport purchasesReport)
	{
		Op op = new Op (Operations.CREATE_PURCHASES_REPORT,purchasesReport);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
