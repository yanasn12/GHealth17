package controllers;

import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.AmountDataPerFuel;
import entities.Car;
import entities.StockReport;

/**
 * This Class provides methods to manage StockReports and all related entities
 * 
 * Manages: {@code StockReport}, {@code AmountDataPerFuel}
 * Active:  {@code StockReport}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class StockReportControl {
	static List<StockReport> stockReports;
	static List<AmountDataPerFuel> amountDatasPerFuel;
	
	static StockReport active;
	
	/**
	 * signifies that this {@code  StockReport} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static StockReport dummyStockReport = null;
	/**
	 * signifies that this {@code DataPerCustomer} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static AmountDataPerFuel dummyAmountDataPerFuel = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		stockReports = new LinkedList<StockReport>();
		amountDatasPerFuel = new LinkedList<AmountDataPerFuel>();
		
		//dummyStockReport = new StockReport ();
		//dummyAmountDataPerFuel = new AmountDataPerFuel();
	}
	
	// ===================================== BUFFERS ==================================

	// ================== STOCK REPORT
	/**
	 *  returns the StockReports buffer that contains all StockReports. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<StockReport> buffer.
	 */
	public static List<StockReport> getStockReports() {
		return stockReports;
	}
	
	/**
	 *  Returns a {@code StockReport} out of the {@code StockReport} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The ReportID that is searched for.
	 * @return {@code StockReport} with matching ID or NULL if entity was not found.
	 */
	public static StockReport getStockReportById(int id){
		
		
		for (StockReport stockReport : stockReports)
		{
			if (stockReport.getReportID() == id)
				return stockReport;
		}
		return fetchStockReport(id);
	}
	
	/**
	 * Returns a list of all StockReports after updating it from the DB. 
	 * @return {@code List<StockReport>} buffer
	 */
	public static List<StockReport> getAllStockReports(){
		
		fetchAllStockReports ();
		return stockReports;
	}
	
	// ================= AMOUNT DATA PER FUEL
	
	/**
	 *  returns the AmountDatasPerFuel buffer that contains all AmountDatasPerFuel. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<AmountDataPerFuel> buffer.
	 */
	public static List<AmountDataPerFuel> getAmountDatasPerFuel() {
		return amountDatasPerFuel;
	}
	
	/**
	 *  Returns a {@code AmountDataPerFuel} out of the {@code AmountDataPerFuel} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The AmountDataPerFuelID that is searched for.
	 * @return {@code AmountDataPerFuel} with matching ID or NULL if entity was not found.
	 */
	public static AmountDataPerFuel getAmountDataPerFuelById(int id){
		
		
		for (AmountDataPerFuel amountDataPerFuel : amountDatasPerFuel)
		{
			if (amountDataPerFuel.getAmountDataPerFuelID() == id)
				return amountDataPerFuel;
		}
		return fetchAmountDataPerFuel(id);
	}
	
	/**
	 * Returns a list of all AmountDatasPerFuel after updating it from the DB. 
	 * @return {@code List<AmountDataPerFuel>} buffer
	 */
	public static List<AmountDataPerFuel> getAllAmountDatasPerFuel(){
		
		fetchAllAmountDatasPerFuel ();
		return amountDatasPerFuel;
	}
	
	// ===================================== ACTIVE HANDLING ===================================
	
	/**
	 *  Returns the {@code StockReport} that is currently being worked on.
	 * @return {@code StockReport} loaded as active.
	 */
	public static StockReport getActive() {
		return active;
	}
	
	/**
	 *  Designates a new {@code StockReport} as the one being worked on. 
	 * @param activeStockReport The {@code StockReport} to be worked on.
	 */
	public static void setActive(StockReport activeStockReport) {
		active = activeStockReport;
	}
	
	/**
	 *  Initializes a new {@code StockReport} and puts it as active.
	 */		
	public static void loadNewActive ()
	{
		active = new StockReport ();
	}	
	
	// ===================================== DB ACESSS ===============================
	
	// ================== STOCK REPORT
	/**
	 *  Attempts to fetch a {@code StockReport} from DB. Recommended using {@code getStockReportById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code StockReport} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static StockReport fetchStockReport (int id)
	{
		StockReport res = null;
		
		Op op = new Op (Operations.GET_STOCK_REPORT, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (StockReport) op.getEntity();
		if (res != null)
		{
			for (StockReport compared : stockReports)
				if (compared.getReportID() == res.getReportID())
					return compared;
				stockReports.add(res);
				ReportControl.getReports().add(res);
		}
		return res;
	}
	
	/**
	 *  updates the StockReport buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllStockReports ()
	{
		List<StockReport> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_STOCK_REPORT_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<StockReport>) op.getEntity();
		if (res != null)
		{
			for (StockReport resS : res)
			{
				addCurrent = true;
				for (StockReport compared : stockReports)
					if (compared.getReportID() == resS.getReportID())
						addCurrent = false;
				
				if (addCurrent)
				{
					stockReports.add(resS);
					ReportControl.getReports().add(resS);
				}
			}
		}
	}
	
	/**
	 *  Removes the StockReport with the given ReportID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the StockReport to be removed.
	 */
	public static void evictStockReport (int id)
	{
		for (StockReport stockReport : stockReports)
		{
			if (stockReport.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_STOCK_REPORT,stockReport);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (AmountDataPerFuel item : amountDatasPerFuel)
				{
					if (item.getStockReportID() == stockReport.getReportID())
						item.setStockReport(dummyStockReport);
				}
				
				stockReports.remove (stockReport);
				ReportControl.getReports().remove(stockReport);
				break;
			}
		}
	}
	
	/**
	 * Updates the StockReport with the given ReportID in the DB.
	 * @param id of the StockReport to be updated
	 */
	public static void updateStockReport (int id)
	{
		for (StockReport stockReport : stockReports)
		{
			if (stockReport.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_STOCK_REPORT,stockReport);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given StockReport in the DB.
	 * @param stockReport to be created in the DB.
	 */
	public static void createStockReport (StockReport stockReport)
	{
		Op op = new Op (Operations.CREATE_STOCK_REPORT,stockReport);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
	// ============= AMOUNT DATA PER FUEL 
	
	/**
	 *  Attempts to fetch a {@code AmountDataPerFuel} from DB. Recommended using {@code getAmountDataPerFuelById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code AmountDataPerFuel} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static AmountDataPerFuel fetchAmountDataPerFuel (int id)
	{
		AmountDataPerFuel res = null;
		
		Op op = new Op (Operations.GET_AMOUNT_DATA_PER_FUEL, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (AmountDataPerFuel) op.getEntity();
		if (res != null)
		{
			for (AmountDataPerFuel compared : amountDatasPerFuel)
				if (compared.getAmountDataPerFuelID() == res.getAmountDataPerFuelID())
					return compared;
				amountDatasPerFuel.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the AmountDataPerFuel buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllAmountDatasPerFuel ()
	{
		List<AmountDataPerFuel> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_AMOUNT_DATA_PER_FUEL_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<AmountDataPerFuel>) op.getEntity();
		if (res != null)
		{
			for (AmountDataPerFuel resS : res)
			{
				addCurrent = true;
				for (AmountDataPerFuel compared : amountDatasPerFuel)
					if (compared.getAmountDataPerFuelID() == resS.getAmountDataPerFuelID())
						addCurrent = false;
				
				if (addCurrent)
					amountDatasPerFuel.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the AmountDataPerFuel with the given AmountDataPerFuelID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the AmountDataPerFuel to be removed.
	 */
	public static void evictAmountDataPerFuel (int id)
	{
		for (AmountDataPerFuel amountDataPerFuel : amountDatasPerFuel)
		{
			if (amountDataPerFuel.getAmountDataPerFuelID() == id)
			{
				Op op = new Op (Operations.UPDATE_AMOUNT_DATA_PER_FUEL,amountDataPerFuel);
				LoginCont.client.handleMessageFromClientUI(op);
				
				amountDatasPerFuel.remove (amountDataPerFuel);
				break;
			}
		}
	}
	
	/**
	 * Updates the AmountDataPerFuel with the given AmountDataPerFuelID in the DB.
	 * @param id of the AmountDataPerFuel to be updated
	 */
	public static void updateAmountDataPerFuel (int id)
	{
		for (AmountDataPerFuel amountDataPerFuel : amountDatasPerFuel)
		{
			if (amountDataPerFuel.getAmountDataPerFuelID() == id)
			{
				Op op = new Op (Operations.UPDATE_AMOUNT_DATA_PER_FUEL,amountDataPerFuel);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given AmountDataPerFuel in the DB.
	 * @param amountDataPerFuel to be created in the DB.
	 */
	public static void createAmountDataPerFuel (AmountDataPerFuel amountDataPerFuel)
	{
		Op op = new Op (Operations.CREATE_AMOUNT_DATA_PER_FUEL, amountDataPerFuel);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
