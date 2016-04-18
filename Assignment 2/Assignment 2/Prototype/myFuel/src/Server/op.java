package Server;

import java.io.Serializable;

/**
 * op is my way to send both the operation code (0-update 1-read to handle properly with the inputs) 
 * and the object together to the server form the client controller while making it Serializable
 * if we would need to identify the result sent back from the server (when its not expected such as here) 
 * then it would be best to create another class as such that will work similar to this
 * to use to send both result code ( to handle properly) and the entity itself.
 * 
 */


public class op implements Serializable{

	private static final long serialVersionUID = 3068160616914105151L;
	private int com;
	private Object entity;
	private String con;
	
	public op(int com)
	{
		this.setCom(com);
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public int getCom() {
		return com;
	}

	public void setCom(int com) {
		this.com = com;
	}
	
	
	
	
}
