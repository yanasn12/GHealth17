package entities;

import java.io.Serializable;
/**
 * 
 * This class contains all the users and passwords that allowed to get in to the system
 *
 */
public class UserLogin implements Serializable{

	private static final long serialVersionUID = 6720461120562109153L;
	
	final public static int UserAlreadyLogged = -2;
	final public static int ERROR = -1;
	final public static int Worker = 1;
	final public static int Customer = 2;
	
	private String host;
	private int port;
	private int PremmisionLevel;
	private String userName;
	private String userPass;
	
	public UserLogin(String host, int port, String userName, String userPass) {
		super();
		this.setHost(new String(host));
		this.setPort(port);
		this.setUserName(userName);
		this.setUserPass(new String(userPass));
	}
	
	
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPremmisionLevel() {
		return PremmisionLevel;
	}

	public void setPremmisionLevel(int premmisionLevel) {
		PremmisionLevel = premmisionLevel;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
}
