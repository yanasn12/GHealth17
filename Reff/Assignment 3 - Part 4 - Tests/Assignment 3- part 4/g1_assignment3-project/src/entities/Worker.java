package entities;

import java.io.Serializable;

/**
 * 
 *  This class stores  Worker information that it is also user
 *
 */

public class Worker extends User  implements Serializable {
	
	private static final long serialVersionUID = 6786739426718400951L;
	int locationID;
	Location location;	
	
	public Location getLocation (){
	
		return location;
	}
	
	public void setLocation (Location newLoc){
		location = newLoc;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	
	// methods //
	
	public String WtoString()
	{
		return new String(toString()+"\nLocationID:"+getLocationID());
	}	
}
