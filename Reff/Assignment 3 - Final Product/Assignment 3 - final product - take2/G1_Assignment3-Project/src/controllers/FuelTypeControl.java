package controllers;

import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.AmountDataPerFuel;
import entities.Car;
import entities.FuelStock;
import entities.FuelType;
import entities.Invoice;
import entities.Order;
import entities.ScorePerFuelType;

/**
 * This Class provides methods to manage fuelTypes.
 * 
 * Manages: {@code FuelType}
 * Active:  {@code FuelType}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class FuelTypeControl {
	
	/**
	 * The FuelTypeID corresponding to house fuel.
	 */
	public static final int HOUSE_FUEL_ID = 3;
	
	static List<FuelType> fuelTypes;
	static FuelType active;
	
	/**
	 * signifies that this {@code FuelType} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static FuelType dummyFuelType = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		fuelTypes = new LinkedList<FuelType>();
		//dummyFuelType = new FuelType ();
	}
	
	// ===================================== BUFFERS ==================================

	/**
	 *  returns the FuelTypes buffer that contains all FuelTypes. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<FuelType> buffer.
	 */
	public static List<FuelType> getFuelTypes() {
		return fuelTypes;
	}
	
	/**
	 *  Returns a {@code FuelType} out of the {@code FuelType} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The FuelTypeID that is searched for.
	 * @return {@code FuelType} with matching ID or NULL if entity was not found.
	 */
	public static FuelType getFuelTypeById(int id){
		
		
		for (FuelType fuelType : fuelTypes)
		{
			if (fuelType.getFuelTypeID() == id)
				return fuelType;
		}
		return fetchFuelType(id);
	}
	
	/**
	 * Returns a list of all FuelTypes after updating it from the DB. 
	 * @return {@code List<FuelType>} buffer
	 */
	public static List<FuelType> getAllFuelTypes(){
		
		fetchAllFuelTypes ();
		return fuelTypes;
	}
	
	// ===================================== ACTIVE HANDLING ===================================
	
	/**
	 *  Returns the {@code FuelType} that is currently being worked on.
	 * @return {@code FuelType} loaded as active.
	 */
	public static FuelType getActive() {
		return active;
	}
	
	/**
	 *  Designates a new {@code FuelType} as the one being worked on. 
	 * @param activeFuelType The {@code FuelType} to be worked on.
	 */
	public static void setActive(FuelType activeFuelType) {
		active = activeFuelType;
	}
	
	/**
	 *  Initializes a new {@code FuelType} and puts it as active.
	 */		
	public static void loadNewActive ()
	{
		active = new FuelType ();
	}	
	
	// ===================================== DB ACESSS ===============================
	
	/**
	 *  Attempts to fetch a {@code FuelType} from DB. Recommended using {@code getFuelTypeById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code FuelType} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static FuelType fetchFuelType (int id)
	{
		FuelType res = null;
		
		Op op = new Op (Operations.GET_FUEL_TYPE, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (FuelType) op.getEntity();
		if (res != null)
		{
			for (FuelType compared : fuelTypes)
				if (compared.getFuelTypeID() == res.getFuelTypeID())
					return compared;
				fuelTypes.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the FuelType buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllFuelTypes ()
	{
		List<FuelType> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_FUEL_TYPE_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<FuelType>) op.getEntity();
		if (res != null)
		{
			for (FuelType resS : res)
			{
				addCurrent = true;
				for (FuelType compared : fuelTypes)
					if (compared.getFuelTypeID() == resS.getFuelTypeID())
						addCurrent = false;
				
				if (addCurrent)
					fuelTypes.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the FuelType with the given FuelTypeID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the FuelType to be removed.
	 */
	public static void evictFuelType (int id)
	{
		for (FuelType fuelType : fuelTypes)
		{
			if (fuelType.getFuelTypeID() == id)
			{
				Op op = new Op (Operations.UPDATE_FUEL_TYPE,fuelType);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (Car item : CustomerControl.getCars ())
				{
					if (item.getFuelTypeID() == fuelType.getFuelTypeID())
						item.setFuelType(dummyFuelType);
				}
				
				for (Order item : OrderControl.getOrders())
				{
					if (item.getFuelTypeID() == fuelType.getFuelTypeID())
						item.setFuelType(dummyFuelType);
				}
				
				for (FuelStock item : StockControl.getFuelStocks())
				{
					if (item.getFuelTypeID() == fuelType.getFuelTypeID())
						item.setFuelType(dummyFuelType);
				}
				
				for (AmountDataPerFuel item : StockReportControl.getAmountDatasPerFuel())
				{
					if (item.getFuelTypeID() == fuelType.getFuelTypeID())
						item.setFuelType(dummyFuelType);
				}
				
				for (ScorePerFuelType item : GeneratedReportControl.getScoresPerFuelType())
				{
					if (item.getFuelTypeID() == fuelType.getFuelTypeID())
						item.setFuelType(dummyFuelType);
				}
				
				fuelTypes.remove (fuelType);
				break;
			}
		}
	}
	
	/**
	 * Updates the FuelType with the given FuelTypeID in the DB.
	 * @param id of the FuelType to be updated
	 */
	public static void updateFuelType (int id)
	{
		for (FuelType fuelType : fuelTypes)
		{
			if (fuelType.getFuelTypeID() == id)
			{
				Op op = new Op (Operations.UPDATE_FUEL_TYPE,fuelType);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
}
