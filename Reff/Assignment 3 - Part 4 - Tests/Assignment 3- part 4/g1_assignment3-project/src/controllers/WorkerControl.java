package controllers;
import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.*;

/**
 * This Class provides methods to manage workers and all related classes.
 * 
 * Manages: {@code Worker}, {@code Location}, {@code Station}
 */
public class WorkerControl {
	
	static List<Worker> workers;
	static List<Location> locations;
	static List<Station> stations;
	
	/**
	 * signifies that this {@code Worker} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static Worker dummyWorker = null;
	/**
	 * signifies that this {@code Location} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static Location dummyLocation = null;
	/**
	 * signifies that this {@code Station} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static Station dummyStation = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		workers = new LinkedList<Worker> ();
		locations = new LinkedList<Location>();
		stations = new LinkedList<Station>();
		
		//dummyWorker = new Worker();
		//dummyLocation = new Location();
		//dummyStation = new Station();
	}
	
	// ===================================== BUFFERS ==================================

	// ==================== WORKER
	/**
	 *  returns the Workers buffer that contains all Workers. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<Worker> buffer.
	 */
	public static List<Worker> getWorkers() {
		return workers;
	}
	
	/**
	 *  Returns a {@code Worker} out of the {@code Worker} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The UserID that is searched for.
	 * @return {@code Worker} with matching ID or NULL if entity was not found.
	 */
	public static Worker getWorkerById(int id){
		
		
		for (Worker worker : workers)
		{
			if (worker.getUserID() == id)
				return worker;
		}
		return fetchWorker(id);
	}
	
	/**
	 * Returns a list of all Workers after updating it from the DB. 
	 * @return {@code List<Worker>} buffer
	 */
	public static List<Worker> getAllWorkers(){
		
		fetchAllWorkers ();
		return workers;
	}
	
	// ==================== LOCATION
	
	/**
	 *  returns the Locations buffer that contains all Locations. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<Location> buffer.
	 */
	public static List<Location> getLocations() {
		return locations;
	}
	
	/**
	 *  Returns a {@code Location} out of the {@code Location} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The LocationID that is searched for.
	 * @return {@code Location} with matching ID or NULL if entity was not found.
	 */
	public static Location getLocationById(int id){
		
		
		for (Location location : locations)
		{
			if (location.getLocationID() == id)
				return location;
		}
		return fetchLocation(id);
	}
	
	/**
	 * Returns a list of all Locations after updating it from the DB. 
	 * @return {@code List<Location>} buffer
	 */
	public static List<Location> getAllLocations(){
		
		fetchAllLocations ();
		return locations;
	}
	
	// ==================== STATION
	
		/**
		 *  returns the Stations buffer that contains all Stations. 
		 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
		 * @return List<Station> buffer.
		 */
		public static List<Station> getStations() {
			return stations;
		}
		
		/**
		 *  Returns a {@code Station} out of the {@code Station} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
		 * @param id  The LocationID that is searched for.
		 * @return {@code Station} with matching ID or NULL if entity was not found.
		 */
		public static Station getStationById(int id){
			
			
			for (Station station : stations)
			{
				if (station.getLocationID() == id)
					return station;
			}
			return fetchStation(id);
		}
		
		/**
		 * Returns a list of all Stations after updating it from the DB. 
		 * @return {@code List<Station>} buffer
		 */
		public static List<Station> getAllStations(){
			
			fetchAllStations ();
			return stations;
		}
	
	// ===================================== DB ACESSS ===============================
	
	// ==================== WORKER
	/**
	 *  Attempts to fetch a {@code Worker} from DB. Recommended using {@code getWorkerById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code Worker} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static Worker fetchWorker (int id)
	{
		Worker res = null;
		
		Op op = new Op (Operations.GET_WORKER, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (Worker) op.getEntity();
		if (res != null)
		{
			for (Worker compared : workers)
				if (compared.getUserID() == res.getUserID())
					return compared;
				workers.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the Worker buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllWorkers ()
	{
		List<Worker> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_WORKER_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<Worker>) op.getEntity();
		if (res != null)
		{
			for (Worker resS : res)
			{
				addCurrent = true;
				for (Worker compared : workers)
					if (compared.getUserID() == resS.getUserID())
						addCurrent = false;
				
				if (addCurrent)
					workers.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the Worker with the given UserID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the Worker to be removed.
	 */
	public static void evictWorker (int id)
	{
		for (Worker worker : workers)
		{
			if (worker.getUserID() == id)
			{
				Op op = new Op (Operations.UPDATE_WORKER,worker);
				LoginCont.client.handleMessageFromClientUI(op);
				
				workers.remove (worker);
				break;
			}
		}
	}
	
	/**
	 * Updates the Worker with the given UserID in the DB.
	 * @param id of the Worker to be updated
	 */
	public static void updateWorker (int id)
	{
		for (Worker worker : workers)
		{
			if (worker.getUserID() == id)
			{
				Op op = new Op (Operations.UPDATE_WORKER,worker);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	// ==================== LOCATION
	
	/**
	 *  Attempts to fetch a {@code Location} from DB. Recommended using {@code getLocationById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code Location} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static Location fetchLocation (int id)
	{
		Location res = null;
		
		Op op = new Op (Operations.GET_LOCATION, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (Location) op.getEntity();
		if (res != null)
		{
			for (Location compared : locations)
				if (compared.getLocationID() == res.getLocationID())
					return compared;
				locations.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the Location buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllLocations ()
	{
		List<Location> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_LOCATION_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<Location>) op.getEntity();
		if (res != null)
		{
			for (Location resS : res)
			{
				addCurrent = true;
				for (Location compared : locations)
					if (compared.getLocationID() == resS.getLocationID())
						addCurrent = false;
				
				if (addCurrent)
					locations.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the Location with the given LocationID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the Location to be removed.
	 */
	public static void evictLocation (int id)
	{
		for (Location location : locations)
		{
			if (location.getLocationID() == id)
			{
				Op op = new Op (Operations.UPDATE_LOCATION,location);
				LoginCont.client.handleMessageFromClientUI(op);
				
				locations.remove (location);
				break;
			}
		}
	}
	
	/**
	 * Updates the Location with the given LocationID in the DB.
	 * @param id of the Location to be updated
	 */
	public static void updateLocation (int id)
	{
		for (Location location : locations)
		{
			if (location.getLocationID() == id)
			{
				Op op = new Op (Operations.UPDATE_LOCATION,location);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	// ==================== STATION
	
	/**
	 *  Attempts to fetch a {@code Station} from DB. Recommended using {@code getStationById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code Station} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static Station fetchStation (int id)
	{
		Station res = null;
		
		Op op = new Op (Operations.GET_STATION, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (Station) op.getEntity();
		if (res != null)
		{
			for (Station compared : stations)
				if (compared.getLocationID() == res.getLocationID())
					return compared;
			stations.add(res);
			locations.add (res);
		}
		return res;
	}
	
	/**
	 *  updates the Station buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllStations ()
	{
		List<Station> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_STATION_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<Station>) op.getEntity();
		if (res != null)
		{
			for (Station resS : res)
			{
				addCurrent = true;
				for (Station compared : stations)
					if (compared.getLocationID() == resS.getLocationID())
						addCurrent = false;
				
				if (addCurrent)
				{
					stations.add(resS);
					locations.add (resS);
				}
			}
		}
	}
	
	/**
	 *  Removes the Station with the given LocationID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the Station to be removed.
	 */
	public static void evictStation (int id)
	{
		for (Station station : stations)
		{
			if (station.getLocationID() == id)
			{
				Op op = new Op (Operations.UPDATE_STATION,station);
				LoginCont.client.handleMessageFromClientUI(op);
				
				stations.remove (station);
				locations.remove(station);
				break;
			}
		}
	}
	
	/**
	 * Updates the Station with the givenLocationID in the DB.
	 * @param id of the Station to be updated
	 */
	public static void updateStation (int id)
	{
		for (Station station : stations)
		{
			if (station.getLocationID() == id)
			{
				Op op = new Op (Operations.UPDATE_STATION,station);
				LoginCont.client.handleMessageFromClientUI(op);

				break;
			}
		}
	}
}
