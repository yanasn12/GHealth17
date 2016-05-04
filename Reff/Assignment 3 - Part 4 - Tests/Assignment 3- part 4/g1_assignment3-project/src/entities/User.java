package entities;

/**
 * 
 * This class stores User information 
 *
 */

import java.io.Serializable;

import controllers.CustomerControl;

public class User implements Serializable{
	
	protected static final long serialVersionUID = -5377165730288899644L;
	protected int userID;
	protected String userName;
	protected String firstName;
	protected String lastName;
	protected String eMail;
	protected String phoneNo;
	protected int userTypeID;
	protected UserType userType;
	
	// constructor //
	
	public User()
	{
		userType = CustomerControl.dummyUserType;
	}
	
	// setters and getters  //
	public int getUserTypeID() {
		return userTypeID;
	}

	public void setUserTypeID(int userTypeID) {
		this.userTypeID = userTypeID;
	}
	/**
	 * 
	 * This method to get a user Type information that relates to the current user
	 * the method checks if the user type information exists is a dummy reference
	 * if it is, get the user type information from the DataBase and sets it in the usertype reference
	 * 
	 * @return UserType the user type information from the DB
	 */
	public UserType getUserType() {	
		if (userType == CustomerControl.dummyUserType)
			userType = CustomerControl.getUserTypeById(userTypeID);
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String toString()
	{
		return new String(" UserID: "+getUserID()+" UserName: "+getUserName()+" Phone: "+getPhoneNo()+" First_Name: "+getFirstName()+" Last_Name: "+getLastName()+" UserTypeID: "+getUserTypeID());
	}
	
}