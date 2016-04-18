package Client;

import common.fuel;
import Server.op;

/**
 * the Client controller sets up the op class to be entity+operation
 * depending on the activated method
 * then the op is sent to the server to handle
 */

public class ClientContorller {// controller is setting the operations to be done by the server - give each operation a unique id 0-update(setting the new entity) 1-search(setting to search parameter) 
	public static op UpdateFuel(fuel f1)
	{
		op operation = new op(0);// 0 update
		operation.setEntity(new fuel(f1.getId(),f1.getName(),f1.getPrice()));
		return operation;
	}
	public static op searchFuel(String name)
	{
		op operation = new op(1);// 1 search by name
		operation.setCon(name);
		return operation;
	}
}//end controller 
