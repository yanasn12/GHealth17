package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class mysqlConnection {
/**String to ArrayList
 * resevie from gui - Action:DB:filed1,_____,filed2,______,.... ,....,.....*/
 public static ArrayList<String> ActionMode (String query)
 {
	  String[] str_arr; 
	  str_arr = (query).split(":");
	  String where="sql1.";//Selelct * From DataSet  
	  ArrayList<String> returnData =new ArrayList<String>();
	  where=where+str_arr[1];
	  switch (str_arr[0].toString().toUpperCase())
	  {
		  case ("PULL"): 
		  {
			  returnData=pull(where);
			  break;
		  }
		  case ("PUSH"): 
		  {
			  	String [] inputData;
		  		String fields="(";
		  		ArrayList<String> toFilled=new ArrayList<String>();
		  		String toByFilld="(";
				//ArrayList<String> Dataset=new ArrayList<String>();//Selelct * From DataSet
		  		inputData = str_arr[2].split(",");
		  		for(int i=0; i<inputData.length;i++)
		  		{
		  			if(i%2==0)
		  			{
		  				fields=fields+inputData[i];
		  				toByFilld=toByFilld+"?";
		  			}
		  			else
		  				toFilled.add(inputData[i]);
		  			if((i+2)<inputData.length)
		  			{
		  				if(i%2==0)
		  					fields=fields+",";
		  				else
		  					toByFilld=toByFilld+",";
		  				
		  			}
		  			if((i+1)>=inputData.length)
		  			{
		  				fields=fields+")";
		  				toByFilld=toByFilld+")";
		  			}
		  		}
		  		if(checkme(where,inputData)==false)
		  			returnData.add("Error");
		  		else
		  			{
		  				push(where,fields,toByFilld,toFilled);
		  			}
			  break;
		  }
		  case ("UPDATE"):
		  {
			  	String [] inputData;
		  		String keyValues;
		  		String fixField="";
		  		ArrayList<String> toFilled=new ArrayList<String>();
				//ArrayList<String> Dataset=new ArrayList<String>();//Selelct * From DataSet
		  		inputData = str_arr[2].split(",");
			  if(checkme(where,inputData)==true)
				  returnData.add("Error");
			  	else
			  	{
			  		keyValues=inputData[0]+"=?;";
			  		toFilled.add(inputData[1]);
			  		for(int i=2;i<inputData.length;i=i+2)
			  		{
			  			if(inputData[i+1]!=null)
			  			{
			  				fixField=fixField+inputData[i]+"=? ";
			  				toFilled.add(inputData[i+1]);
			  			}
			  		}
			  		
			  		update(where,fixField,keyValues,toFilled);
			  	}
			  
			  break;
		  }
		  case ("DELETE"):
			  {
				 String[] inputData;
				 String keyValue;
				 inputData = str_arr[2].split(",");
				 keyValue=inputData[0]+"=?;";
				  	if(checkme(where,inputData)==true)
				  		returnData.add("Error");
				  	else
				  	{
				  	 delete(where,keyValue,inputData[1].toString());
			  
				  	} 
			  break;
			 }
		  case ("PULLBYKEY"): 
			  {
			  String[] inputData;
			  String keyValue;
			  inputData = str_arr[2].split(",");	
			  keyValue=inputData[0]+"='"+inputData[1]+"'";
			  returnData=pullByKey(where,keyValue );

			  break;
			  }
		  default: returnData.add("Error"); break;
	  }
	  return returnData;
 }

/**Explanation: draw method pulling the table with all fields
//InPut: Receives the name of the table we want to pull from
//OutOut: Get a linked list with all the details of the table.	Each entry contains the key line in addition to all the information that it */
private static ArrayList<String> pull (String DB)
	{
		ArrayList<String> data= new ArrayList<String>(); // a Temporary location where we keep the table with all the details
		Statement stmt; // Enable to create a statment
		String line=""; // a temperary place where we keep every line of info
		try 
			{
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	        } catch (Exception ex) {/* handle the error*/}
		try 
			{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sql1","root","xhxnt");
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+ DB+";");
			// ResultSetMetaData a class that get the info from the Sql and use it, in this case to tell as how many Values we need to get every time
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
	 		while(rs.next())
	 		  { 
		 			for(int i=1; i <= columnsNumber; i++)
		 				line=line+ " " + rs.getString(i);
				data.add(line);
				line="";
	 		  } 
			rs.close();
			return data;
			} catch (SQLException e) {e.printStackTrace(); return null;}
	}
	
	
/**Explanation: a method that checks whether if the value is already in the table (check the key of the table), 
//          this test is carried out before the PUSH to make sure that there will be duplication of information,
//      	and before UPDATE, to make sure that there is a value we can update
//InPut: Receives the name of the table we want to check from and data that contain the key value we check  
//OutOut: return TRUE   if the key info is not in the DB
//        return FALSE if the key info is already in the DB*/
//
private static boolean  checkme (String DB, String[]  data)
{
	Statement stmt; // Enable to create a statment
	boolean result=true; // a startup value that is initialized "true"
	String insertTableSQL = "SELECT * FROM "+ DB + " WHERE";// the structure of the query
	for(int i=0; i<1;i=i+2)
		insertTableSQL=insertTableSQL+" "+data[i]+"='"+data[i+1]+"'";

	try 
	{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (Exception ex) {/* handle the error*/}

	try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sql1","root","xhxnt");
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(insertTableSQL);
		//if there isnt any line in the sql table with the key value the IF condition will not be activate 
		if(rs.next())
		{
			result=false;
		}
			
		return result;							
	} catch (SQLException e) {System.out.println("error: checkme- DB "+DB+" Quary "+insertTableSQL);return false;}
	 		
}
/**Explanation: The method updates the value in the table by using it key value
//InPut: Receives the name of the table we want to update from and data that contain the key value and the value we want to change  
//OutOut: the method return true if it succeeded to update , else false;*/
private static boolean update (String DB,String filled, String keyValue,ArrayList<String> data)
{
 String updateString= "UPDATE "+DB.toString()+" SET "+ filled+ " WHERE "+keyValue;// the structure of the query
 try {
	   Class.forName("com.mysql.jdbc.Driver").newInstance();
  } catch (Exception ex) {/* handle the error*/}
 try {	
		 Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sql1","root","xhxnt");
		 PreparedStatement insertTableSQL = con.prepareStatement(updateString);
		 for(int i=1; i<data.size();i++)
			 insertTableSQL.setString(i, data.get(i).toString());
		 insertTableSQL.setString(data.size(), data.get(0).toString());
		 insertTableSQL.executeUpdate();
		 return true;
 	} catch (SQLException e) {
			System.out.println("unable to update");
			return false;}	 		
}
/**Explanation: The method enter to the table the record we want to add our SQL table
//InPut: Receives the name of the table we want to add info, the data we want to enter, 
//        the values of the sql table and a line of (?,...) acording to the table;
//OutOut: return TRUE   if the info was add.
//		  return FALSE if the info was not add.*/
private static boolean push (String DB,String values,String qustion,ArrayList<String> data)
{
	String insertTableSQL = "INSERT INTO "+DB + " "+values+" VALUES "+ qustion;// the structure of the query
	try 
	{
    Class.forName("com.mysql.jdbc.Driver").newInstance();
 } catch (Exception ex) {/* handle the error*/}

	try {	
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sql1","root","xhxnt");
		PreparedStatement update = con.prepareStatement(insertTableSQL);
		update.setString(2, data.get(1));
		update.setString(1, data.get(0));
		update.executeUpdate();
		return true;
	} catch (SQLException e) 
	{
			System.out.println("unable to push -PUSH");
			System.out.println("DB "+DB+" values "+values+" qustion"+ qustion+" data1: "+data.get(0)+" data2: "+data.get(1));
			return false;
	}	 		
}
/**Explanation: The method enter to the table the record we want to add our SQL table
//InPut: Receives the name of the table we want to delete the info and the data (key data) we want to delete 
//OutOut: return TRUE   if the info was delete.
//return FALSE if the info was not delete.*/
private static boolean  delete(String DB,String keyplace,String key)
{
	String insertTableSQL = "DELETE FROM "+ DB.toString() + " WHERE "+keyplace;// the structure of the query
	try 
	{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (Exception ex) {/* handle the error*/}

	try {	
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sql1","root","xhxnt");
	      
		PreparedStatement st = con.prepareStatement(insertTableSQL);
	     st.setString(1, key.toString());	 
	      st.executeUpdate();
	        return true;
		}
	catch (SQLException e) 
		{
		System.out.println("unable to Delete -delete :DB - "+ DB+ " key-"+key);
		return false;
		}
 		
}

/**Explanation: draw method pulling the info from the table with all fields of the Specific key
//InPut: Receives the name of the table we want to pull info from and Specific key
//OutOut: return the a ArrayList with one tab (the Specific key)*/
private static ArrayList<String> pullByKey(String DB, String SearchKey)
{
		String pullingString="SELECT * FROM "+DB.toString() + " WHERE "+SearchKey;// the structure of the query
		ArrayList<String> data= new ArrayList<String>();  // a Temporary location where we keep the table with all the details
		String line=""; // a temperary place where we keep every line of info
		try 
		{
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	    } catch (Exception ex) {/* handle the error*/}
		try {	
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sql1","root","xhxnt");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(pullingString);
			// ResultSetMetaData a class that get the info from the Sql and use it, in this case to tell as how many Values we need to get every time
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
	 		while(rs.next())
	 		{
	 			for(int i=1; i <= columnsNumber; i++)
	 				line=line+ " " + rs.getString(i);
			data.add(line);
			line="";
	 		} 
			rs.close();
			return data;
		} catch (SQLException e){
					e.printStackTrace();
					System.out.println("error: pullByKey DB "+DB+" SearchKey "+ SearchKey);
					return null;} 
}

 
 
}