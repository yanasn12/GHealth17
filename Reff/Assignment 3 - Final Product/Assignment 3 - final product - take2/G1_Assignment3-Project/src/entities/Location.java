package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import controllers.WorkerControl;

/**
 * 
 * This class stores location information.
 *
 */

public class Location implements Serializable {
	
	private static final long serialVersionUID = -376532328620076198L;
	
	int locationID;
	String localAddr;
	
	// constructor //
	
	public Location (){
		// need TBD
	}
	
	// setters and getters //

	public String getLocalAddr() {
		return localAddr;
	}
	public void setLocalAddr(String localAddr) {
		this.localAddr = localAddr;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	public String toString()
	{
		return new String("LocationID:"+getLocationID()+"\nLocationAddress:"+getLocalAddr());
	}
	
	public List<Worker> getWorkers ()
	{
		LinkedList <Worker> res = new LinkedList <Worker> ();
		
		for (Worker item : WorkerControl.getAllWorkers())
		{
			if (item.getLocationID() == locationID)
				res.add(item);
		}
		return res;
	}
	

}
