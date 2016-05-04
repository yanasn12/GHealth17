package controllers;

import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.Car;
import entities.DataPerCustomerType;
import entities.ScorePerFuelType;
import entities.GeneratedScoreReport;

/**
 * This Class provides methods to manage GeneratedScoreReports and all related entities
 * 
 * Manages: {@code GeneratedScoreReport}, {@code DataPerCustomerType}, {@code ScorePerFuelType}
 * Active:  {@code GeneratedScoreReport}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class GeneratedReportControl {

	static List<GeneratedScoreReport> generatedScoreReports;
	static GeneratedScoreReport active;
	static List<DataPerCustomerType> datasPerCustomerType;
	static List<ScorePerFuelType> scoresPerFuelType;
	
	/**
	 * signifies that this {@code  GeneratedScoreReport} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static GeneratedScoreReport dummyGeneratedScoreReport = null;
	/**
	 * signifies that this {@code DataPerCustomerType} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static DataPerCustomerType dummyDataPerCustomerType = null;
	/**
	 * signifies that this {@code ScorePerFuelType} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static ScorePerFuelType dummyScorePerFuelType = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl()
	{
		generatedScoreReports = new LinkedList<GeneratedScoreReport>();
		datasPerCustomerType = new LinkedList<DataPerCustomerType>();
		scoresPerFuelType = new LinkedList<ScorePerFuelType>();
		
		//dummyGeneratedScoreReport = new GeneratedScoreReport();
		//dummyDataPerCustomerType = new DataPerCustomerType();
		//dummyScorePerFuelType = new ScorePerFuelType();	
	}
	
	// ===================================== BUFFERS ==================================
	
	// ================== GENERATED SCORE REPORT
	/**
	 *  returns the GeneratedScoreReports buffer that contains all GeneratedScoreReports. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<GeneratedScoreReport> buffer.
	 */
	public static List<GeneratedScoreReport> getGeneratedScoreReports() {
		return generatedScoreReports;
	}
	
	/**
	 *  Returns a {@code GeneratedScoreReport} out of the {@code GeneratedScoreReport} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The ReportID that is searched for.
	 * @return {@code GeneratedScoreReport} with matching ID or NULL if entity was not found.
	 */
	public static GeneratedScoreReport getGeneratedScoreReportById(int id){
		
		
		for (GeneratedScoreReport generatedScoreReport : generatedScoreReports)
		{
			if (generatedScoreReport.getReportID() == id)
				return generatedScoreReport;
		}
		return fetchGeneratedScoreReport(id);
	}
	
	/**
	 * Returns a list of all GeneratedScoreReports after updating it from the DB. 
	 * @return {@code List<GeneratedScoreReport>} buffer
	 */
	public static List<GeneratedScoreReport> getAllGeneratedScoreReports(){
		
		fetchAllGeneratedScoreReports ();
		return generatedScoreReports;
	}
	
	// ================= DATA PER CUSTOMER TYPE
	/**
	 *  returns the DataPerCustomerTypes buffer that contains all DataPerCustomerTypes. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<DataPerCustomerType> buffer.
	 */
	public static List<DataPerCustomerType> getDatasPerCustomer() {
		return datasPerCustomerType;
	}
	
	/**
	 *  Returns a {@code DataPerCustomerType} out of the {@code DataPerCustomerType} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The DataPerCustomerTypeID that is searched for.
	 * @return {@code DataPerCustomerType} with matching ID or NULL if entity was not found.
	 */
	public static DataPerCustomerType getDataPerCustomerTypeById(int id){
		
		
		for (DataPerCustomerType dataPerCustomerType : datasPerCustomerType)
		{
			if (dataPerCustomerType.getDataPerCustomerTypeID() == id)
				return dataPerCustomerType;
		}
		return fetchDataPerCustomerType(id);
	}
	
	/**
	 * Returns a list of all DatasPerCustomer after updating it from the DB. 
	 * @return {@code List<DataPerCustomerType>} buffer
	 */
	public static List<DataPerCustomerType> getAllDatasPerCustomerType(){
		
		fetchAllDatasPerCustomerType ();
		return datasPerCustomerType;
	}
	
	// ================= SCORE PER FUEL TYPE
	
	/**
	 *  returns the ScorePerFuelTypes buffer that contains all ScorePerFuelTypes. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<ScorePerFuelType> buffer.
	 */
	public static List<ScorePerFuelType> getScoresPerFuelType() {
		return scoresPerFuelType;
	}
	
	/**
	 *  Returns a {@code ScorePerFuelType} out of the {@code ScorePerFuelType} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The ScorePerFuelTypeID that is searched for.
	 * @return {@code ScorePerFuelType} with matching ID or NULL if entity was not found.
	 */
	public static ScorePerFuelType getScorePerFuelTypeById(int id){
		
		
		for (ScorePerFuelType scorePerFuelType : scoresPerFuelType)
		{
			if (scorePerFuelType.getScorePerFuelTypeID() == id)
				return scorePerFuelType;
		}
		return fetchScorePerFuelType(id);
	}
	
	/**
	 * Returns a list of all ScorePerFuelTypes after updating it from the DB. 
	 * @return {@code List<ScorePerFuelType>} buffer
	 */
	public static List<ScorePerFuelType> getAllScoresPerFuelType(){
		
		fetchAllScorePerFuelTypes ();
		return scoresPerFuelType;
	}
	
	// ===================================== ACTIVE HANDLING ===================================
	
	/**
	 *  Returns the {@code GeneratedScoreReport} that is currently being worked on.
	 * @return {@code GeneratedScoreReport} loaded as active.
	 */
	public static GeneratedScoreReport getActive() {
		return active;
	}
	
	/**
	 *  Designates a new {@code GeneratedScoreReport} as the one being worked on. 
	 * @param activeGeneratedScoreReport The {@code GeneratedScoreReport} to be worked on.
	 */
	public static void setActive(GeneratedScoreReport activeGeneratedScoreReport) {
		active = activeGeneratedScoreReport;
	}
	
	/**
	 *  Initializes a new {@code GeneratedScoreReport} and puts it as active.
	 */		
	public static void loadNewActive ()
	{
		active = new GeneratedScoreReport ();
	}	

	// ===================================== DB ACESSS ===============================

	// ================== GENERATED SCORE REPORT
	/**
	 *  Attempts to fetch a {@code GeneratedScoreReport} from DB. Recommended using {@code getGeneratedScoreReportById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code GeneratedScoreReport} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static GeneratedScoreReport fetchGeneratedScoreReport (int id)
	{
		GeneratedScoreReport res = null;
		
		Op op = new Op (Operations.GET_GENERATED_SCORE_REPORT, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (GeneratedScoreReport) op.getEntity();
		if (res != null)
		{
			for (GeneratedScoreReport compared : generatedScoreReports)
				if (compared.getReportID() == res.getReportID())
					return compared;
				generatedScoreReports.add(res);
				ReportControl.getReports().add(res);
		}
		return res;
	}
	
	/**
	 *  updates the GeneratedScoreReport buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllGeneratedScoreReports ()
	{
		List<GeneratedScoreReport> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_GENERATED_SCORE_REPORT_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<GeneratedScoreReport>) op.getEntity();
		if (res != null)
		{
			for (GeneratedScoreReport resS : res)
			{
				addCurrent = true;
				for (GeneratedScoreReport compared : generatedScoreReports)
					if (compared.getReportID() == resS.getReportID())
						addCurrent = false;
				
				if (addCurrent)
				{
					generatedScoreReports.add(resS);
					ReportControl.getReports().add(resS);
				}
			}
		}
	}
	
	/**
	 *  Removes the GeneratedScoreReport with the given ReportID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the GeneratedScoreReport to be removed.
	 */
	public static void evictGeneratedScoreReport (int id)
	{
		for (GeneratedScoreReport generatedScoreReport : generatedScoreReports)
		{
			if (generatedScoreReport.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_GENERATED_SCORE_REPORT,generatedScoreReport);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (DataPerCustomerType item : datasPerCustomerType)
				{
					if (item.getGeneratedScoreReportID() == generatedScoreReport.getGeneratedScoreReportID())
						item.setGeneratedScoreReport(dummyGeneratedScoreReport);
				}
				
				for (ScorePerFuelType item : scoresPerFuelType)
				{
					if (item.getGeneratedScoreReportID() == generatedScoreReport.getGeneratedScoreReportID())
						item.setGeneratedScoreReport(dummyGeneratedScoreReport);
				}
				
				generatedScoreReports.remove (generatedScoreReport);
				ReportControl.getReports().remove(generatedScoreReport);
				break;
			}
		}
	}
	
	/**
	 * Updates the GeneratedScoreReport with the given ReportID in the DB.
	 * @param id of the GeneratedScoreReport to be updated
	 */
	public static void updateGeneratedScoreReport (int id)
	{
		for (GeneratedScoreReport generatedScoreReport : generatedScoreReports)
		{
			if (generatedScoreReport.getReportID() == id)
			{
				Op op = new Op (Operations.UPDATE_GENERATED_SCORE_REPORT,generatedScoreReport);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given GeneratedScoreReport in the DB.
	 * @param report to be created in the DB.
	 */
	public static void createGeneratedScoreReport (GeneratedScoreReport report)
	{
		Op op = new Op (Operations.CREATE_GENERATED_SCORE_REPORT, report);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
	
	// ================= DATA PER CUSTOMER TYPE
	
	/**
	 *  Attempts to fetch a {@code DataPerCustomerType} from DB. Recommended using {@code getDataPerCustomerTypeById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code DataPerCustomerType} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static DataPerCustomerType fetchDataPerCustomerType (int id)
	{
		DataPerCustomerType res = null;
		
		Op op = new Op (Operations.GET_DATA_PER_CUSTOMER_TYPE, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (DataPerCustomerType) op.getEntity();
		if (res != null)
		{
			for (DataPerCustomerType compared : datasPerCustomerType)
				if (compared.getDataPerCustomerTypeID() == res.getDataPerCustomerTypeID())
					return compared;
				datasPerCustomerType.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the DataPerCustomerType buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllDatasPerCustomerType ()
	{
		List<DataPerCustomerType> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_DATA_PER_CUSTOMER_TYPE_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<DataPerCustomerType>) op.getEntity();
		if (res != null)
		{
			for (DataPerCustomerType resS : res)
			{
				addCurrent = true;
				for (DataPerCustomerType compared : datasPerCustomerType)
					if (compared.getDataPerCustomerTypeID() == resS.getDataPerCustomerTypeID())
						addCurrent = false;
				
				if (addCurrent)
					datasPerCustomerType.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the DataPerCustomerType with the given DataPerCustomerTypeID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the DataPerCustomerType to be removed.
	 */
	public static void evictDataPerCustomerType (int id)
	{
		for (DataPerCustomerType dataPerCustomerType : datasPerCustomerType)
		{
			if (dataPerCustomerType.getDataPerCustomerTypeID() == id)
			{
				Op op = new Op (Operations.UPDATE_DATA_PER_CUSTOMER_TYPE,dataPerCustomerType);
				LoginCont.client.handleMessageFromClientUI(op);
				
				datasPerCustomerType.remove (dataPerCustomerType);
				break;
			}
		}
	}
	
	/**
	 * Updates the DataPerCustomerType with the given DataPerCustomerTypeID in the DB.
	 * @param id of the DataPerCustomerType to be updated
	 */
	public static void updateDataPerCustomerType (int id)
	{
		for (DataPerCustomerType dataPerCustomerType : datasPerCustomerType)
		{
			if (dataPerCustomerType.getDataPerCustomerTypeID() == id)
			{
				Op op = new Op (Operations.UPDATE_DATA_PER_CUSTOMER_TYPE,dataPerCustomerType);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given DataPerCustomerType in the DB.
	 * @param data to be created in the DB.
	 */
	public static void createDataPerCustomerType (DataPerCustomerType data)
	{
		Op op = new Op (Operations.CREATE_DATA_PER_CUSTOMER_TYPE, data);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
	
	// ================= SCORE PER FUEL TYPE
	
	/**
	 *  Attempts to fetch a {@code ScorePerFuelType} from DB. Recommended using {@code getScorePerFuelTypeById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code ScorePerFuelType} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static ScorePerFuelType fetchScorePerFuelType (int id)
	{
		ScorePerFuelType res = null;
		
		Op op = new Op (Operations.GET_SCORE_PER_FUEL_TYPE, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (ScorePerFuelType) op.getEntity();
		if (res != null)
		{
			for (ScorePerFuelType compared : scoresPerFuelType)
				if (compared.getScorePerFuelTypeID() == res.getScorePerFuelTypeID())
					return compared;
				scoresPerFuelType.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the ScorePerFuelType buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllScorePerFuelTypes ()
	{
		List<ScorePerFuelType> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_SCORE_PER_FUEL_TYPE_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<ScorePerFuelType>) op.getEntity();
		if (res != null)
		{
			for (ScorePerFuelType resS : res)
			{
				addCurrent = true;
				for (ScorePerFuelType compared : scoresPerFuelType)
					if (compared.getScorePerFuelTypeID() == resS.getScorePerFuelTypeID())
						addCurrent = false;
				
				if (addCurrent)
					scoresPerFuelType.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the ScorePerFuelType with the given ScorePerFuelTypeID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the ScorePerFuelType to be removed.
	 */
	public static void evictScorePerFuelType (int id)
	{
		for (ScorePerFuelType scorePerFuelType : scoresPerFuelType)
		{
			if (scorePerFuelType.getScorePerFuelTypeID() == id)
			{
				Op op = new Op (Operations.UPDATE_SCORE_PER_FUEL_TYPE,scorePerFuelType);
				LoginCont.client.handleMessageFromClientUI(op);
				
				scoresPerFuelType.remove (scorePerFuelType);
				break;
			}
		}
	}
	
	/**
	 * Updates the ScorePerFuelType with the given ScorePerFuelTypeID in the DB.
	 * @param id of the ScorePerFuelType to be updated
	 */
	public static void updateScorePerFuelType (int id)
	{
		for (ScorePerFuelType scorePerFuelType : scoresPerFuelType)
		{
			if (scorePerFuelType.getScorePerFuelTypeID() == id)
			{
				Op op = new Op (Operations.UPDATE_SCORE_PER_FUEL_TYPE,scorePerFuelType);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given ScorePerFuelType in the DB.
	 * @param score to be created in the DB.
	 */
	public static void createScorePerFuelType (ScorePerFuelType score)
	{
		Op op = new Op (Operations.CREATE_SCORE_PER_FUEL_TYPE,score);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
