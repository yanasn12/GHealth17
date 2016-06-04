package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class mysqlConnection {
	
	
	public static void main(String[] args) {
		Scanner input =new Scanner(System.in);
		String str = input.nextLine();
		ArrayList<String> data = new ArrayList<String>();
		data=ActionMode(str);
		for(int i=0; i<data.size();i++)
		{
			System.out.println(data.get(i));
		}
	}
/**String to ArrayList
 * resevie from gui - Action:DB:filed1,_____,filed2,______,.... ,....,.....*/
 public static ArrayList<String> ActionMode (String query)
 {
	  String[] str_arr; 
	  str_arr = (query).split(":");
	  String where="ghealth.";//Selelct * From DataSet  
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
			  			{
			  				if(inputData[i].toString().equals(""))
			  					toFilled.add("-1");
			  				else
			  					toFilled.add(inputData[i]);
			  			}
		  			if(inputData.length%2!=0)
		  			{
			  			if((i+2)<=inputData.length)
			  			{
			  				if(i%2==0)
			  					fields=fields+",";
			  				else
			  					toByFilld=toByFilld+",";
			  				
			  			}
		  			}
			  		else
		  			{
			  			if((i+2)<inputData.length)
			  			{
			  				if(i%2==0)
			  					fields=fields+",";
			  				else
			  					toByFilld=toByFilld+",";
			  				
			  			}
		  			}			  				
		  			if((i+1)>=inputData.length)
		  			{
		  				fields=fields+")";
		  				toByFilld=toByFilld+")";
		  			}
		  		}
		  		if(inputData.length%2==1)
		  		{
		  			toFilled.add("-1");
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
			  		for(int i=2;i<(inputData.length-1);i=i+2)
			  		{
			  			if(!inputData[i+1].equals(""))
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
		  case ("PULLBYDEMAND"):
		  {
			  String[] inputData;
			  inputData = str_arr[2].split(",");
			  String[] inputTables;
			  inputTables = str_arr[1].split(",");
			  ArrayList<String> conditions = new ArrayList<String>();
			  where="ghealth."+inputTables[0];
			  for(int i=1;i<inputTables.length;i++)
			  {
				  where=where+" , ";
				  where=where+"ghealth."+inputTables[i];
			  }
			  for(int i=0;i<inputData.length;i++)
			  {
				  conditions.add(inputData[i]);
			  }
			  returnData=pullByDemand(where, conditions);
			  break;
		  }
		  default: returnData.add("Error"); break;
	  }
	  return returnData;
 }
/**
 * format only for pullByDemand:
 * pullByDemand:___DB1, DB2, DB3,:FROM_DBn,FILLD,FROM_DB(n+1),FILLD ,....
 * 									   DB1.FILLD1=       DB2.FILLD7, .....   
 * */

 private static ArrayList<String> pullByDemand (String DB, ArrayList<String> conditions)
 {
	 ArrayList<String> result = new ArrayList<String>();
		Statement stmt; // Enable to create a statment
		String line=""; // a temperary place where we keep every line of info
		String quary ="SELECT * FROM "+ DB+" WHERE ";

		try 
			{
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	        } catch (Exception ex) {/* handle the error*/}
		try 
			{
			for(int i=0; i<conditions.size();i+=4)
			{
				quary=quary+" "+conditions.get(i)+"."+conditions.get(i+1)+"="+conditions.get(i+2)+"."+conditions.get(i+3);
				if((i+4)<conditions.size())
					quary=quary+" AND";
				else
					quary=quary+";";
			}
			System.out.println(quary);
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ghealth","root","xhxnt");
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(quary);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
	 		while(rs.next())
	 		  { 
		 			for(int i=1; i <= columnsNumber; i++)
		 				line=line+ " " + rs.getString(i);
				result.add(line);
				line="";
	 		  } 
			rs.close();
			return result;
			} catch (SQLException e) {e.printStackTrace(); return null;}
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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ghealth","root","xhxnt");
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+ DB+";");
			// ResultSetMetaData a class that get the info from the Sql and use it, in this case to tell as how many Values we need to get every time
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
	 		while(rs.next())
	 		  { 
		 			for(int i=1; i <= columnsNumber; i++)
		 				line=line+ "," + rs.getString(i);
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
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ghealth","root","xhxnt");
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
		 Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ghealth","root","xhxnt");
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
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ghealth","root","xhxnt");
		PreparedStatement update = con.prepareStatement(insertTableSQL);
		for(int i=0; i<data.size();i++)
			update.setString((i+1), data.get(i));
		update.executeUpdate();
		return true;
	} catch (SQLException e) 
	{
			System.out.println("unable to push -PUSH");
			System.out.println("DB "+DB+" values "+values+" qustion"+ qustion);
			for(int i=0; i<data.size();i++)
				System.out.print(i+"="+data.get(i)+" ");
			System.out.println();
			System.out.println(insertTableSQL);
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
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ghealth","root","xhxnt");
	      
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
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ghealth","root","xhxnt");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(pullingString);
			// ResultSetMetaData a class that get the info from the Sql and use it, in this case to tell as how many Values we need to get every time
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

	 		while(rs.next())
	 		{
	 			for(int i=1; i <= columnsNumber; i++)
	 				line=line+ "," + rs.getString(i);
			data.add(line);
			line="";
	 		} 
			rs.close();
			if(data.isEmpty())
				return null;
			else
				return data;
		} catch (SQLException e){
					e.printStackTrace();
					System.out.println("error: pullByKey DB "+DB+" SearchKey "+ SearchKey);
					return null;} 
}

 
 
}