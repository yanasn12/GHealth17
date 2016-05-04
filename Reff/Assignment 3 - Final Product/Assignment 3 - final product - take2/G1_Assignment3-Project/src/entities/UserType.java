package entities;

import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;

import controllers.CustomerControl;

/**
 * 
 * This class represents an User Type, this class contains the relevant data for each user type
 *
 */
  
public class UserType implements Serializable{
	

	private static final long serialVersionUID = -615112283207405267L;
	private int userTypeID;
	private String posName;
	private boolean[] privilegeLevels;
	private boolean isCustomer;
	private boolean isCarCustomer;
	
	
	// setters and getters  //
	public int getUserTypeID() {
		return userTypeID;
	}

	public void setUserTypeID(int userTypeID) {
		this.userTypeID = userTypeID;
	}
	
	public String getPosName() {
		return posName;
	}
	public void setPosName(String posName) {
		this.posName = posName;
	}
	public boolean[] getPrivilegeLevels() {
		return privilegeLevels;
	}
	/**
	 * 
	 *  This method places binary sequence that gives specific privileges the user type and by it the program will know which window it would be shown to the user
	 * @param s represents the bit sequences of the privilege levels
	 */	
	public void setPrivilegeLevels(String s) {
		privilegeLevels = new boolean[s.length()];
		char[] pl = s.toCharArray();
		for(int i=0;i<s.length();i++)
			privilegeLevels[i] = charToBool(pl[i]);
	}
	public boolean isCustomer() {
		return isCustomer;
	}
	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}
	public boolean isCarCustomer() {
		return isCarCustomer;
	}
	public void setCarCustomer(boolean isCarCustomer) {
		this.isCarCustomer = isCarCustomer;
	}
	public String toString()
	{
		return new String("UserTypeID: "+userTypeID+" Position Name: "+posName);
	}
	/**
	 * 
	 * This method translate from char to boolean value
	 * @return true if it 1 and false if it 0
	 */
	public boolean charToBool(char c)
	{
		if(c=='1') return true;
		return false;
	}
	/**
	 * 
	 * This method returns all the user in the DB that have the same user type
	 */
	public List<User> getUsers() {
		LinkedList<User> res = new LinkedList<User> ();
		
		for (User item : CustomerControl.getAllUsers())
		{
			if (item.getUserTypeID() == userTypeID)
				res.add(item);
		}
		return res;
	}

	public void setPrivilegeLevels(boolean[] privilegeLevels) {
		this.privilegeLevels = privilegeLevels;
	}
}
