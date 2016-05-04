package Fixtures;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import server.DBC;

import com.mysql.jdbc.Connection;

import common.Op;
import client.ChatClient;
import entities.CarFuelOrder;

public class LocalClient {

	public LocalClient(){}
	
	private Object res;
	

	
	
	public void setUp(String str ,Object Data){
			/* DB connection */
			try {
			  //  Class.forName("com.mysql.jdbc.Driver").newInstance();
			    	
				//Mysql connection 
				DBC dbc = new DBC("jdbc:mysql://localhost:3306/myfuel", "root", "Braude");
				if  (str  == "GET_CAR_FUEL_ORDER"){
					 CarFuelOrder data  = (CarFuelOrder)Data;
					 //check wrong input 
					 if (data.getCarID() == -1 || data.getQuantity()<0){
						 res = false;
						 return;
					 } 
					 // check 2 
					 
					 dbc.addCarFuelOrder(data);
					 this.res= true;
					
				}
				else if (str  == "GET_"){
					res=true;
				
				}	
				
				
				
			//	SqlConn.close();
						
			} catch (SQLException ex) {
				/* handle any errors */
				System.out.println("SQLException: " + ex.getMessage());
				//System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
				this.res=false;
			
			
			} catch (Exception ex) {
			    System.out.println("Failed to connect SQL DB: 'JDBC driver problem'.");
			    this.res=false;
			}
	}


	public Object getRes() {
		return res;
	}

	public void setRes(Object res) {
		this.res = res;
	}
}
