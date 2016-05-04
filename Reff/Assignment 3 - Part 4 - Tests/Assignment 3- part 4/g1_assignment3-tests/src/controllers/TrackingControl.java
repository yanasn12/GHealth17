package controllers;
import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.Car;
import entities.HouseFuelInfo;
import entities.HouseFuelOrder;

/**
 * This Class provides methods to manage HouseFuUelInfos.
 * 
 * Manages: {@code HouseFuUelInfo}
 * Active:  {@code HouseFuUelInfo}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class TrackingControl {
	
	/**
	 * The deliveryStatus of an order. The order has been created and is now awaiting acceptance by the staff.
	 */
	public static final int CREATED = 0;
	/**
	 *  The deliveryStatus of an order. The order has been accepted and is now being processed.
	 */
	public static final int ACCEPTED = 1;
	/**
	 *  The deliveryStatus of an order. The order has been sent and is now awaiting arrival.
	 */
	public static final int SENT = 2;
	/**
	 *  The deliveryStatus of an order. The order has been delivered.
	 */
	public static final int DELIVERED = 3;
	
	
	static List<HouseFuelInfo> houseFuelInfos;
	/**
	 * signifies that this {@code FuelStock} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static HouseFuelInfo dummyHouseFuelInfo = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		houseFuelInfos = new LinkedList<HouseFuelInfo> ();
		//dummyHouseFuelInfo = new HouseFuelInfo ();
	}
	
	// ===================================== BUFFERS ==================================

	/**
	 *  returns the HouseFuelInfos buffer that contains all HouseFuelInfos. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<HouseFuelInfo> buffer.
	 */
	public static List<HouseFuelInfo> getHouseFuelInfos() {
		return houseFuelInfos;
	}
	
	/**
	 *  Returns a {@code HouseFuelInfo} out of the {@code HouseFuelInfo} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The HouseFuelInfoID that is searched for.
	 * @return {@code HouseFuelInfo} with matching ID or NULL if entity was not found.
	 */
	public static HouseFuelInfo getHouseFuelInfoById(int id){
		
		
		for (HouseFuelInfo houseFuelInfo : houseFuelInfos)
		{
			if (houseFuelInfo.getHouseFuelInfoID() == id)
				return houseFuelInfo;
		}
		return fetchHouseFuelInfo(id);
	}
	
	/**
	 * Returns a list of all HouseFuelInfos after updating it from the DB. 
	 * @return {@code List<HouseFuelInfo>} buffer
	 */
	public static List<HouseFuelInfo> getAllHouseFuelInfos(){
		
		fetchAllHouseFuelInfos ();
		return houseFuelInfos;
	}
	
	// ===================================== ACTIVE HANDLING ===================================
	

		
	// ===================================== DB ACESSS ===============================
		
	/**
	 *  Attempts to fetch a {@code HouseFuelInfo} from DB. Recommended using {@code getHouseFuelInfoById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code HouseFuelInfo} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static HouseFuelInfo fetchHouseFuelInfo (int id)
	{
		HouseFuelInfo res = null;
			
		Op op = new Op (Operations.GET_HOUSE_FUEL_INFO, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (HouseFuelInfo) op.getEntity();
		if (res != null)
		{
			for (HouseFuelInfo compared : houseFuelInfos)
				if (compared.getHouseFuelInfoID() == res.getHouseFuelInfoID())
					return compared;

			houseFuelInfos.add(res);
		}
		return res;
	}
		
	/**
	 *  updates the HouseFuelInfo buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllHouseFuelInfos ()
	{
		List<HouseFuelInfo> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_HOUSE_FUEL_INFO_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<HouseFuelInfo>) op.getEntity();
		if (res != null)
		{
			for (HouseFuelInfo resS : res)
			{
				addCurrent = true;
				for (HouseFuelInfo compared : houseFuelInfos)
					if (compared.getHouseFuelInfoID() == resS.getHouseFuelInfoID())
						addCurrent = false;
				
				if (addCurrent)
					houseFuelInfos.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the HouseFuelInfo with the given HouseFuelInfoID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the HouseFuelInfo to be removed.
	 */
	public static void evictHouseFuelInfo (int id)
	{
		for (HouseFuelInfo houseFuelInfo : houseFuelInfos)
		{
			if (houseFuelInfo.getHouseFuelInfoID() == id)
			{
				Op op = new Op (Operations.UPDATE_HOUSE_FUEL_INFO,houseFuelInfo);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (HouseFuelOrder item : HouseFuelOrderControl.getHouseFuelOrders())
				{
					if (item.getHouseFuelInfoID() == houseFuelInfo.getHouseFuelInfoID())
						item.setHouseFuelInfo(dummyHouseFuelInfo);
				}
				
				houseFuelInfos.remove (houseFuelInfo);
				break;
			}
		}
	}
	
	/**
	 * Updates the HouseFuelInfo with the given HouseFuelInfoID in the DB.
	 * @param id of the HouseFuelInfo to be updated
	 */
	public static void updateHouseFuelInfo (int id)
	{
		for (HouseFuelInfo houseFuelInfo : houseFuelInfos)
		{
			if (houseFuelInfo.getHouseFuelInfoID() == id)
			{
				Op op = new Op (Operations.UPDATE_HOUSE_FUEL_INFO,houseFuelInfo);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given HouseFuelInfo in the DB.
	 * @param houseFuelInfo to be created in the DB.
	 */
	public static void createHouseFuelInfo (HouseFuelInfo houseFuelInfo)
	{
		Op op = new Op (Operations.CREATE_HOUSE_FUEL_INFO, houseFuelInfo);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
