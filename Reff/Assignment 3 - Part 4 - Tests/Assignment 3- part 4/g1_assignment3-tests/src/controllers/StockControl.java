package controllers;

import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.AmountDataPerFuel;
import entities.Car;
import entities.FuelReplenish;
import entities.FuelStock;
import entities.Worker;

/**
 * This Class provides methods to manage FuelStocks.
 * 
 * Manages: {@code FuelStock} {@code FuelReplenish}
 * Active:  {@code FuelStock}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class StockControl {
		
	/**
	 * FuelReplenish status - this replenish was just created and not yet approved and sent.
	 */
	public static final int CREATED = 0;
	/**
	 * FuelReplenish status - this replenish was approved, send and is now awaiting arrival.
	 */
	public static final int SENT = 1;
	/**
	 * FuelReplenish status - this replenish has arrived and its arrival was approved.
	 */
	public static final int RECEIVED = 2;
	
	static List<FuelStock> fuelStocks;
	static List<FuelReplenish> fuelReplenishes;
	static FuelStock active;
	
	/**
	 * signifies that this {@code FuelStock} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static FuelStock dummyFuelStock = null;
	/**
	 * signifies that this {@code FuelReplenish} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static FuelReplenish dummyFuelReplenish = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		fuelStocks = new LinkedList<FuelStock>();
		fuelReplenishes = new LinkedList<FuelReplenish> ();
		//dummyFuelStock = new FuelStock ();
		//dummyFuelReplenish = new FuelReplenish ();
	}
	
	// ===================================== BUFFERS ==================================

	/**
	 *  returns the FuelStocks buffer that contains all FuelStocks. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<FuelStock> buffer.
	 */
	public static List<FuelStock> getFuelStocks() {
		return fuelStocks;
	}
	
	/**
	 *  Returns a {@code FuelStock} out of the {@code FuelStock} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param fuelTypeID of the stock which is looked for.
	 * @param stationID of the stock which is looked for.
	 * @return {@code FuelStock} with matching ID or NULL if entity was not found.
	 */
	public static FuelStock getFuelStockById(int fuelTypeID, int stationID){
				
		for (FuelStock fuelStock : fuelStocks)
		{
			if (fuelStock.getFuelTypeID() == fuelTypeID && fuelStock.getStationID() == stationID)
				return fuelStock;
		}
		return fetchFuelStock(fuelTypeID, stationID);
	}
		
	/**
	 * Returns a list of all FuelStocks after updating it from the DB. 
	 * @return {@code List<FuelStock>} buffer
	 */
	public static List<FuelStock> getAllFuelStocks(){
		
		fetchAllFuelStocks ();
		return fuelStocks;
	}
	
	/**
	 *  returns the FuelReplenish buffer that contains all FuelReplenishs. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<FuelReplenish> buffer.
	 */
	public static List<FuelReplenish> getFuelReplenishes() {
		return fuelReplenishes;
	}
	
	/**
	 *  Returns a {@code FuelReplenish} out of the {@code FuelReplenish} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id fuelReplenishID of the Replenish which is looked for.
	 * @return {@code FuelReplenish} with matching ID or NULL if entity was not found.
	 */
	public static FuelReplenish getFuelReplenishById(int id){
				
		for (FuelReplenish fuelReplenish : fuelReplenishes)
		{
			if (fuelReplenish.getFuelReplenishID() == id)
				return fuelReplenish;
		}
		return fetchFuelReplenish(id);
	}
		
	/**
	 * Returns a list of all FuelReplenishes after updating it from the DB. 
	 * @return {@code List<FuelReplenish>} buffer
	 */
	public static List<FuelReplenish> getAllFuelReplenishes(){
		
		fetchAllFuelReplenishes ();
		return fuelReplenishes;
	}
	
	// ===================================== ACTIVE HANDLING ===================================
	
	/**
	 *  Returns the {@code FuelStock} that is currently being worked on.
	 * @return {@code FuelStock} loaded as active.
	 */
	public static FuelStock getActive() {
		return active;
	}
			
	/**
	 *  Designates a new {@code FuelStock} as the one being worked on. 
	 * @param activeFuelStock The {@code FuelStock} to be worked on.
	 */
	public static void setActive(FuelStock activeFuelStock) {
		active = activeFuelStock;
	}
			
	/**
	 *  Initializes a new {@code FuelStock} and puts it as active.
	 */		
	public static void loadNewActive ()
	{
		active = new FuelStock ();
	}	
	
	/**
	 * Reduces the given quantity from the given FuelStock, creates refuelling request if needed.
	 * @param fuelTypeID reduced FuelType
	 * @param stationID fuelling station.
	 * @param quantity the ordered amount.
	 * @return boolean hasEnoughFuel
	 */
	public static boolean reduceFromStock (int fuelTypeID, int stationID, double quantity)
	{
		int id = 0;
		FuelStock stock = getFuelStockById(fuelTypeID, stationID);
		if (stock.getStockAmount() < (int)(quantity+1))
			return false;
		
		stock.setStockAmount((int)(stock.getStockAmount() - quantity + 0.5));
		if (stock.getMinStockLevel() > stock.getStockAmount())
		{
			FuelReplenish replenish = new FuelReplenish();
			
			replenish.setFuelTypeID(fuelTypeID);
			replenish.setStationID(stationID);
			replenish.setStatus(CREATED);
			
			for (FuelReplenish rep : getAllFuelReplenishes())
			{
				if (rep.getFuelReplenishID() > id)
					id = rep.getFuelReplenishID();
				if (rep.getFuelTypeID() == fuelTypeID && rep.getStationID() == stationID)
				{
					if (rep.getStatus() == CREATED)
					{
						rep.setAmount(stock.getMaxStockLevel() - stock.getStockAmount());
						updateFuelReplenish(rep.getFuelReplenishID());
						updateFuelStock(fuelTypeID, stationID);
						return true;
					}
					if (rep.getStatus() == SENT)
					{
						updateFuelStock(fuelTypeID, stationID);
						return true;
					}
				}
			}
			
			replenish.setFuelReplenishID(id+1);
			replenish.setAmount(stock.getMaxStockLevel() - stock.getStockAmount());
			createFuelReplenish(replenish);
			
			List<Worker> wl = WorkerControl.getAllWorkers();
			Worker stationManager = null;
			for(Worker w : wl)
				if(w.getUserTypeID()==5 && w.getLocationID()==stationID)
					stationManager = w;
			if(stationManager!=null)
				LoginCont.sendMail("g1.myfuel@gmail.com", "Braude1234", stationManager.geteMail(), "New Fuel Replenish Order", "Fuel Stock is lower then the minimal warrning level at StationID:"+stationID+" FuelTypeID:"+fuelTypeID+".A New Fuel Replenish order was created , please Log in to the system to approve the order.");
		}
		updateFuelStock(fuelTypeID, stationID);
		
		return true;
	}
			
	// ===================================== DB ACESSS ===============================
			
	/**
	 *  Attempts to fetch a {@code FuelStock} from DB. Recommended using {@code getFuelStockById} instead to check if the entity is already in the buffer.
	 * Notice this fetch receives two keys for FuelType and Station as fuelStock's primary key is the combinations of these two.
	 * @param fuelTypeID of the stock which is looked for.
	 * @param stationID of the stock which is looked for.
	 * @return {@code FuelStock} from DB with matching ids to the given ones or NULL if entity not found.
	 */
	public static FuelStock fetchFuelStock (int fuelTypeID, int stationID)
	{
		FuelStock res = null;
		int[] idPair = new int [2];
		idPair[0] = fuelTypeID;
		idPair[1] = stationID;
		
		Op op = new Op (Operations.GET_FUEL_STOCK, idPair);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (FuelStock) op.getEntity();
		if (res != null)
		{
			for (FuelStock compared : fuelStocks)
				if (compared.getFuelTypeID() == res.getFuelTypeID() && compared.getStationID() == res.getStationID())
					return compared;

			fuelStocks.add(res);
		}
		return res;
	}
			
	/**
	 *  updates the FuelStock buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllFuelStocks ()
	{
		List<FuelStock> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_FUEL_STOCK_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<FuelStock>) op.getEntity();
		if (res != null)
		{
			for (FuelStock resS : res)
			{
				addCurrent = true;
				for (FuelStock compared : fuelStocks)
					if (compared.getFuelTypeID() == resS.getFuelTypeID() && compared.getStationID() == resS.getStationID())
						addCurrent = false;
				
				if (addCurrent)
					fuelStocks.add(resS);
			}
		}
	}
			
	/**
	 *  Removes the FuelStock with the given FuelTypeID and StationID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param fuelTypeID of the stock to be removed.
	 * @param stationID of the stock to be removed.
	 */
	public static void evictFuelStock (int fuelTypeID, int stationID)
	{
		for (FuelStock fuelStock : fuelStocks)
		{
			if (fuelStock.getFuelTypeID() == fuelTypeID && fuelStock.getStationID() == stationID)
			{
				Op op = new Op (Operations.UPDATE_FUEL_STOCK,fuelStock);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (FuelReplenish item : fuelReplenishes)
				{
					if (item.getFuelTypeID() == fuelStock.getFuelTypeID() && item.getStationID() == fuelStock.getStationID())
						item.setFuelStock(dummyFuelStock);
				}
				
				fuelStocks.remove (fuelStock);
				break;
			}
		}
	}
			

	/**
	 * Updates the FuelStock with the given FuelTypeID and StationID in the DB.
	 * @param fuelTypeID of the stock to be updated.
	 * @param stationID of the stock to be updated.
	 */
	public static void updateFuelStock (int fuelTypeID, int stationID)
	{
		for (FuelStock fuelStock : fuelStocks)
		{
			if (fuelStock.getFuelTypeID() == fuelTypeID && fuelStock.getStationID() == stationID)
			{
				Op op = new Op (Operations.UPDATE_FUEL_STOCK,fuelStock);
				LoginCont.client.handleMessageFromClientUI(op);
						
				break;
			}
		}
	}
	
	/**
	 *  Attempts to fetch a {@code FuelReplenish} from DB. Recommended using {@code getFuelReplenishById} instead to check if the entity is already in the buffer.
	 * @param id FuelReplenishID of the FUelReplenish which is looked for.
	 * @return {@code FuelReplenish} from DB with matching ids to the given ones or NULL if entity not found.
	 */
	public static FuelReplenish fetchFuelReplenish (int id)
	{
		FuelReplenish res = null;
		
		Op op = new Op (Operations.GET_FUEL_REPLENISH, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (FuelReplenish) op.getEntity();
		if (res != null)
		{
			for (FuelReplenish compared : fuelReplenishes)
				if (compared.getFuelReplenishID() == res.getFuelReplenishID())
					return compared;

			fuelReplenishes.add(res);
		}
		return res;
	}
			
	/**
	 *  updates the FuelReplenish buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllFuelReplenishes ()
	{
		List<FuelReplenish> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_FUEL_REPLENISH_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<FuelReplenish>) op.getEntity();
		if (res != null)
		{
			for (FuelReplenish resS : res)
			{
				addCurrent = true;
				for (FuelReplenish compared : fuelReplenishes)
					if (compared.getFuelReplenishID() == resS.getFuelReplenishID())
						addCurrent = false;
				
				if (addCurrent)
					fuelReplenishes.add(resS);
			}
		}
	}
			
	/**
	 *  Removes the FuelReplenish with the given FuelReplenishID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param fuelTypeID of the Replenish to be removed.
	 * @param stationID of the Replenish to be removed.
	 */
	public static void evictFuelReplenish (int id)
	{
		for (FuelReplenish fuelReplenish : fuelReplenishes)
		{
			if (fuelReplenish.getFuelReplenishID() == id)
			{
				Op op = new Op (Operations.UPDATE_FUEL_REPLENISH,fuelReplenish);
				LoginCont.client.handleMessageFromClientUI(op);
						
				for (FuelStock item : fuelStocks)
				{
					if (item.getLastReplenishID() == fuelReplenish.getFuelReplenishID())
						item.setLastReplenish(dummyFuelReplenish);
				}
				
				fuelReplenishes.remove (fuelReplenish);
				break;
			}
		}
	}
			

	/**
	 * Updates the FuelReplenish with the given FuelReplenishID in the DB.
	 * @param fuelTypeID of the Replenish to be updated.
	 * @param stationID of the Replenish to be updated.
	 */
	public static void updateFuelReplenish (int id)
	{
		for (FuelReplenish fuelReplenish : fuelReplenishes)
		{
			if (fuelReplenish.getFuelReplenishID() == id)
			{
				Op op = new Op (Operations.UPDATE_FUEL_REPLENISH,fuelReplenish);
				LoginCont.client.handleMessageFromClientUI(op);

				break;
			}
		}
	}
	
	/**
	 * Creates the given FuelReplenish in the DB.
	 * @param fuelReplenish to be created in the DB.
	 */
	public static void createFuelReplenish (FuelReplenish fuelReplenish)
	{
		Op op = new Op (Operations.CREATE_FUEL_REPLENISH,fuelReplenish);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
