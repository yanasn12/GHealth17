package controllers;

import java.util.LinkedList;
import java.util.List;

import javax.xml.crypto.Data;

import common.Op;
import common.Operations;

import entities.*;

/**
 * This Class provides methods to manage PeriodicCustomerReports and all related entities
 * 
 * Manages: {@code PeriodicCustomerReport}, {@code DataPerCustomer}
 * Active:  {@code PeriodicCustomerReport}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class PeriodicCustomerReportControl {
	
	static List<PeriodicCustomerReport> periodicCustomerReports;
	static List<DataPerCustomer> datasPerCustomer;
	
	static PeriodicCustomerReport active;
	
	/**
	 * signifies that this {@code DataPerCustomer} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static DataPerCustomer dummyDataPerCustomer = null;
	/**
	 * signifies that this {@code  PeriodicCustomerReport} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static PeriodicCustomerReport dummyPeriodicCustomerReport = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		periodicCustomerReports = new LinkedList<PeriodicCustomerReport>();
		datasPerCustomer = new LinkedList<DataPerCustomer>();
		
		//dummyPeriodicCustomerReport = new PeriodicCustomerReport();
		//dummyDataPerCustomer = new DataPerCustomer();
	}
	
	// ===================================== BUFFERS ==================================

	// ================ PERIODIC CUSTOMER REPORT
	/**
	 *  returns the PeriodicCustomerReports buffer that contains all PeriodicCustomerReports. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<PeriodicCustomerReport> buffer.
	 */
	public static List<PeriodicCustomerReport> getPeriodicCustomerReports() {
		return periodicCustomerReports;
	}
	
	/**
	 *  Returns a {@code PeriodicCustomerReport} out of the {@code PeriodicCustomerReport} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The ReportID that is searched for.
	 * @return {@code PeriodicCustomerReport} with matching ID or NULL if entity was not found.
	 */
	public static PeriodicCustomerReport getPeriodicCustomerReportById(int id){
		
		
		for (PeriodicCustomerReport periodicCustomerReport : periodicCustomerReports)
		{
			if (periodicCustomerReport.getReportID() == id)
				return periodicCustomerReport;
		}
		return fetchPeriodicCustomerReport(id);
	}
	
	/**
	 * Returns a list of all PeriodicCustomerReports after updating it from the DB. 
	 * @return {@code List<PeriodicCustomerReport>} buffer
	 */
	public static List<PeriodicCustomerReport> getAllPeriodicCustomerReports(){
		
		fetchAllPeriodicCustomerReports ();
		return periodicCustomerReports;
	}
	
	// =================== DATA PER CUSTOMER
	
	/**
	 *  returns the DataPerCustomers buffer that contains all DataPerCustomers. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<DataPerCustomer> buffer.
	 */
	public static List<DataPerCustomer> getDatasPerCustomer() {
		return datasPerCustomer;
	}
	
	/**
	 *  Returns a {@code DataPerCustomer} out of the {@code DataPerCustomer} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The DataPerCustomerID that is searched for.
	 * @return {@code DataPerCustomer} with matching ID or NULL if entity was not found.
	 */
	public static DataPerCustomer getDataPerCustomerById(int id){
		
		
		for (DataPerCustomer dataPerCustomer : datasPerCustomer)
		{
			if (dataPerCustomer.getDataPerCustomerID() == id)
				return dataPerCustomer;
		}
		return fetchDataPerCustomer(id);
	}
	
	/**
	 * Returns a list of all DatasPerCustomer after updating it from the DB. 
	 * @return {@code List<DataPerCustomer>} buffer
	 */
	public static List<DataPerCustomer> getAllDatasPerCustomer(){
		
		fetchAllDatasPerCustomer ();
		return datasPerCustomer;
	}
	
	// ===================================== ACTIVE HANDLING ===================================
	
	/**
	 *  Returns the {@code PeriodicCustomerReport} that is currently being worked on.
	 * @return {@code PeriodicCustomerReport} loaded as active.
	 */
	public static PeriodicCustomerReport getActive() {
		return active;
	}
	
	/**
	 *  Designates a new {@code PeriodicCustomerReport} as the one being worked on. 
	 * @param activePeriodicCustomerReport The {@code PeriodicCustomerReport} to be worked on.
	 */
	public static void setActive(PeriodicCustomerReport activePeriodicCustomerReport) {
		active = activePeriodicCustomerReport;
	}
		
	/**
	 *  Initializes a new {@code PeriodicCustomerReport} and puts it as active.
	 */		
	public static void loadNewActive ()
	{
		active = new PeriodicCustomerReport ();
	}	
	
	// ===================================== DB ACESSS ===============================
	
	// ============== PERIODIC CUSTOMER REPORT
	/**
	 *  Attempts to fetch a {@code PeriodicCustomerReport} from DB. Recommended using {@code getPeriodicCustomerReportById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code PeriodicCustomerReport} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static PeriodicCustomerReport fetchPeriodicCustomerReport (int id)
	{
		PeriodicCustomerReport res = null;
		
		Op op = new Op (Operations.GET_PERIODIC_CUSTOMER_REPORT, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (PeriodicCustomerReport) op.getEntity();
		if (res != null)
		{
			for (PeriodicCustomerReport compared : periodicCustomerReports)
				if (compared.getReportID() == res.getReportID())
					return compared;
				periodicCustomerReports.add(res);
				ReportControl.getReports().add(res);
		}
		return res;
	}
	
	/**
	 *  updates the PeriodicCustomerReport buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllPeriodicCustomerReports ()
	{
		List<PeriodicCustomerReport> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_PERIODIC_CUSTOMER_REPORT_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<PeriodicCustomerReport>) op.getEntity();
		if (res != null)
		{
			for (PeriodicCustomerReport resS : res)
			{
				addCurrent = true;
				for (PeriodicCustomerReport compared : periodicCustomerReports)
					if (compared.getReportID() == resS.getReportID())
						addCurrent = false;
				
				if (addCurrent)
				{
					periodicCustomerReports.add(resS);
					ReportControl.getReports().add(resS);
				}
			}
		}
	}
	
	/**
	 *  Removes the PeriodicCustomerReport with the given PeriodicCustomerReportID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the PeriodicCustomerReport to be removed.
	 */
	public static void evictPeriodicCustomerReport (int id)
	{
		for (PeriodicCustomerReport periodicCustomerReport : periodicCustomerReports)
		{
			if (periodicCustomerReport.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_PERIODIC_CUSTOMER_REPORT,periodicCustomerReport);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (DataPerCustomer item : datasPerCustomer)
				{
					if (item.getReportID() == periodicCustomerReport.getReportID())
						item.setPeriodicCustomerReport(dummyPeriodicCustomerReport);
				}
				
				periodicCustomerReports.remove (periodicCustomerReport);
				ReportControl.getReports().remove(periodicCustomerReport);
				break;
			}
		}
	}
	
	/**
	 * Updates the PeriodicCustomerReport with the given ReportID in the DB.
	 * @param id of the PeriodicCustomerReport to be updated
	 */
	public static void updatePeriodicCustomerReport (int id)
	{
		for (PeriodicCustomerReport periodicCustomerReport : periodicCustomerReports)
		{
			if (periodicCustomerReport.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_PERIODIC_CUSTOMER_REPORT,periodicCustomerReport);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given PeriodicCustomerReport in the DB.
	 * @param report to be created in the DB.
	 */
	public static void createPeriodicCustomerReport (PeriodicCustomerReport report)
	{
		Op op = new Op (Operations.CREATE_PERIODIC_CUSTOMER_REPORT ,report);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
	
	// ================== DATA PER CUSTOMER
	
	/**
	 *  Attempts to fetch a {@code DataPerCustomer} from DB. Recommended using {@code getDataPerCustomerById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code DataPerCustomer} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static DataPerCustomer fetchDataPerCustomer (int id)
	{
		DataPerCustomer res = null;
		
		Op op = new Op (Operations.GET_DATA_PER_CUSTOMER, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (DataPerCustomer) op.getEntity();
		if (res != null)
		{
			for (DataPerCustomer compared : datasPerCustomer)
				if (compared.getDataPerCustomerID() == res.getDataPerCustomerID())
					return compared;
				datasPerCustomer.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the DataPerCustomer buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllDatasPerCustomer ()
	{
		List<DataPerCustomer> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_DATA_PER_CUSTOMER_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<DataPerCustomer>) op.getEntity();
		if (res != null)
		{
			for (DataPerCustomer resS : res)
			{
				addCurrent = true;
				for (DataPerCustomer compared : datasPerCustomer)
					if (compared.getDataPerCustomerID() == resS.getDataPerCustomerID())
						addCurrent = false;
				
				if (addCurrent)
					datasPerCustomer.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the DataPerCustomer with the given DataPerCustomerID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the DataPerCustomer to be removed.
	 */
	public static void evictDataPerCustomer (int id)
	{
		for (DataPerCustomer dataPerCustomer : datasPerCustomer)
		{
			if (dataPerCustomer.getDataPerCustomerID() == id)
			{
				Op op = new Op (Operations.UPDATE_DATA_PER_CUSTOMER,dataPerCustomer);
				LoginCont.client.handleMessageFromClientUI(op);
				
				datasPerCustomer.remove (dataPerCustomer);
				break;
			}
		}
	}
	
	/**
	 * Updates the DataPerCustomer with the given DataPerCustomerID in the DB.
	 * @param id of the DataPerCustomer to be updated
	 */
	public static void updateDataPerCustomer (int id)
	{
		for (DataPerCustomer dataPerCustomer : datasPerCustomer)
		{
			if (dataPerCustomer.getDataPerCustomerID() == id)
			{
				Op op = new Op (Operations.UPDATE_DATA_PER_CUSTOMER,dataPerCustomer);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given DataPerCustomer in the DB.
	 * @param data to be created in the DB.
	 */
	public static void createDataPerCustomer (DataPerCustomer data)
	{
		Op op = new Op (Operations.CREATE_DATA_PER_CUSTOMER,data);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
