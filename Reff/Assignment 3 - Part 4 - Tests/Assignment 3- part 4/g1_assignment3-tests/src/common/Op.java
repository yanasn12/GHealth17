package common;

import java.io.Serializable;


/**
 * an assisting class to send messages and commands as 1 package between client and server
 * @param Op - an enum that represents the operation that needs to be done with the information sent
 * @param entity - the informtaion sent
 * 
 */


public class Op implements Serializable{

	private static final long serialVersionUID = 3068160616914105151L;
	protected Operations op;
	private SearchType searchType;
	private Object entity;
	
	
	public Op(Operations o,Object msg, SearchType method)
	{	
		searchType = method;
		setOp(o);
		setEntity(msg);
	}
	
	public Op(Operations o,Object msg)
	{
		searchType = SearchType.DEFAULT;
		setOp(o);
		setEntity(msg);
	}
	
	public SearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}

	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
	}
	public void setOp(Operations o)
	{
		this.op=o;
	}
	
	public Operations getOp() {
		return op;
	}
	
}
