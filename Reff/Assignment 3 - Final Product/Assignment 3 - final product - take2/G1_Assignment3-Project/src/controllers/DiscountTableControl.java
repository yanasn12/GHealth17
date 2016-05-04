package controllers;

import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;
import entities.DiscountTable;
import entities.Subscription;

/**
 * This Class provides methods to manage DiscountTables.
 * 
 * Manages: {@code DiscountTable}
 * Active:  {@code DiscountTable}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class DiscountTableControl {
	
	static List<DiscountTable> discountTables;
	static DiscountTable active;
	
	/**
 	* signifies that this {@code DiscountTable} reference might exist in the DB but wasn't brought clientside yet.
   	*/
	public static DiscountTable dummyDiscountTable = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		discountTables = new LinkedList<DiscountTable>();
		//dummyDiscountTable = new DiscountTable ();
	}
	
	// ===================================== BUFFERS ==================================
	/**
	 *  returns the DiscountTable buffer that contains all DiscountTables. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<DiscountTable> buffer.
	 */
	public static List<DiscountTable> getDiscountTables() {
		return discountTables;
	}
	
	/**
	 *  Returns an {@code DiscountTable} out of the {@code DiscountTable} buffer with a matching id. If the discountTable doesn't exist attempts to fetch it from DB.
	 * @param id  The DiscountTableID that is searched for.
	 * @return {@code DiscountTable} with matching ID or NULL if entity was not found.
	 */
	public static DiscountTable getDiscountTableById(int id){
		
		
		for (DiscountTable discountTable : discountTables)
		{
			if (discountTable.getDiscountTableID() == id)
				return discountTable;
		}
		return fetchDiscountTable(id);
	}
	
	/**
	 * Returns a list of all DiscountTables after updating it from the DB. 
	 * @return {@code List<DiscountTable>} buffer
	 */
	public static List<DiscountTable> getAllDiscountTables(){
		
		fetchAllDiscountTables ();
		return discountTables;
	}
	
	// ===================================== ACTIVE HANDLING ===================================
	
		/**
		 *  Returns the {@code DiscountTable} that is currently being worked on.
		 * @return {@code DiscountTable} loaded as active.
		 */
		public static DiscountTable getActive() {
			return active;
		}
		
		/**
		 *  Designates a new {@code DiscountTable} as the one being worked on. 
		 * @param activeFuelType The {@code DiscountTable} to be worked on.
		 */
		public static void setActive(DiscountTable activeDiscountTable) {
			active = activeDiscountTable;
		}
		
		/**
		 *  Initializes a new {@code DiscountTable} and puts it as active.
		 */		
		public static void loadNewActive ()
		{
			active = new DiscountTable ();
		}	
		
		/**
		 * Returns the discounts of the default DiscountTable (DiscountTableID 0)
		 * @return double[] discount array.
		 */
		public static double[] getAllDiscounts ()
		{
			return getDiscountTableById(0).getDiscountTable();
		}
		
		/**
		 * Returns the pending approval discount changes of the default DiscountTable (DiscountTableID 0)
		 * @return double[] changed discounts array.
		 */
		public static double[] getChanged ()
		{
			return getDiscountTableById(0).getDiscountTableChanges();
		}
		
		/**
		 *  Sets the discount at the given index as approved (sets the actual used discount to the one that was pending approval).
		 *  The changes are applied to the active so make sure the proper DiscountTable is loaded as active.
		 */
		public static void approveAt (int index)
		{
			if (index < active.getDiscountTable().length && index >= 0)
				active.getDiscountTable()[index] = active.getDiscountTableChanges()[index];
		}
			
		// ===================================== DB ACESSS ===============================
			
		/**
		 *  Attempts to fetch a {@code DiscountTable} from DB. Recommended using {@code getDiscountTableById} instead to check if the entity is already in the buffer.
		 * @param id which is looked for
		 * @return {@code DiscountTable} from DB with matching id to the given one or NULL if entity not found.
		 */
		public static DiscountTable fetchDiscountTable (int id)
		{
			DiscountTable res = null;
			
			Op op = new Op (Operations.GET_DISCOUNT_TABLE, id);
			LoginCont.client.handleMessageFromClientUI(op);
			op = (Op) LoginCont.client.getMessage();
			res = (DiscountTable) op.getEntity();
			if (res != null)
			{
				for (DiscountTable compared : discountTables)
					if (compared.getDiscountTableID() == res.getDiscountTableID())
						return compared;

				discountTables.add(res);
			}
			return res;
		}
			
		/**
		 *  updates the DiscountTable buffer with all entities that didn't exist there.
		 *  In order to refresh the buffer please evict all entities in it first
		 */
		public static void fetchAllDiscountTables ()
		{
			List<DiscountTable> res = null;
			boolean addCurrent;
				
			Op op = new Op (Operations.GET_DISCOUNT_TABLE_ALL, -1);
			LoginCont.client.handleMessageFromClientUI(op);
			op = (Op) LoginCont.client.getMessage();
			res = (List<DiscountTable>) op.getEntity();
			if (res != null)
			{
				for (DiscountTable resS : res)
				{
					addCurrent = true;
					for (DiscountTable compared : discountTables)
						if (compared.getDiscountTableID() == resS.getDiscountTableID())
							addCurrent = false;
					
					if (addCurrent)
						discountTables.add(resS);
				}
			}
		}
		
		/**
		 *  Removes the DiscountTable with the given DiscountTableID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
		 * @param id of the DiscountTable to be removed.
		 */
		public static void evictDiscountTable (int id)
		{
			for (DiscountTable discountTable : discountTables)
			{
				if (discountTable.getDiscountTableID() == id)
				{
					Op op = new Op (Operations.UPDATE_DISCOUNT_TABLE,discountTable);
					LoginCont.client.handleMessageFromClientUI(op);
					
					for (Subscription item : SubscriptionControl.getSubscriptions())
					{
						if (item.getDiscountTableID() == discountTable.getDiscountTableID())
							item.setDiscountTable(dummyDiscountTable);
					}
					
					discountTables.remove (discountTable);
					break;
				}
			}
		}
			
		/**
		 * Updates the DiscountTable with the given DiscountTableID in the DB.
		 * @param id of the DiscountTable to be updated
		 */
		public static void updateDiscountTable (int id)
		{
			for (DiscountTable discountTable : discountTables)
			{
				if (discountTable.getDiscountTableID() == id)
				{
					Op op = new Op (Operations.UPDATE_DISCOUNT_TABLE,discountTable);
					LoginCont.client.handleMessageFromClientUI(op);

					break;
				}
			}
		}
}

