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

	public static void main(String[] args) 
	{
		ArrayList<String> data= new ArrayList<String>();
		data.add("0");
		data.add("6");
		data.add("6");
		data.add("6");
		data.add("6");
		data.add("6");
		try 
		{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {/* handle the error*/}
        
        try 
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sql1","root","xhxnt");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.3.68/test","root","Root");
            System.out.println("SQL connection succeed");
      //      data=pull("sql1.human",conn);
      //      System.out.println(data.toString());
     //       createTableCourses(conn);
           // push("sql1.human",data,conn);
			if(!(checkme("sql1.human",data,conn)))
			{
				System.out.println("error!");
			}
     	} catch (SQLException ex) 
     	    {/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
   	}
	
	public static ArrayList<String> pull (String DB,Connection con)
	{
		ArrayList<String> data= new ArrayList<String>();
		Statement stmt;
		String line="";
		try 
		{
			stmt = con.createStatement();
		//	PreparedStatement newPull = con.prepareStatement("SELECT *" + " FROM ?");
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+ DB+";");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			
	 		while(rs.next())
	 		{
				 // Print out the values
	 			for(int i=1; i < columnsNumber; i++)
	 				line=line+ " " + rs.getString(i);
			data.add(line);
			line="";
	 		} 
			rs.close();
			//stmt.executeUpdate("UPDATE course SET semestr=\"W08\" WHERE num=61309");
		} catch (SQLException e) {e.printStackTrace();}
		finally{ return data;}
	}
public static boolean  checkme (String DB,ArrayList<String> data,Connection con)
{
	Statement stmt;
	String insertTableSQL = "SELECT * FROM "+ DB + " WHERE UserID="+data.get(0);
	try {
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(insertTableSQL);
		if(rs.next())
			return true;
		return false;
							
	} catch (SQLException e) {	e.printStackTrace(); return false;}
	 		
}



	
	public static void push (String DB,ArrayList<String> data,Connection con){
		Statement stmt;
		String insertTableSQL = "INSERT INTO "+DB + "(UserID,Fname, LastName, Phone, address, Email) VALUES"
				+ "(?,?,?,?,?,?)";
		try {	
			
			PreparedStatement update = con.prepareStatement(insertTableSQL);

			update.setString(2, data.get(1));
			update.setString(3, data.get(2));
			update.setInt(4, Integer.valueOf(data.get(3)));
			update.setString(5, data.get(4));
			update.setString(6, data.get(5));
			update.setInt(1, Integer.valueOf(data.get(0)));
			update.executeUpdate();
				

		} catch (SQLException e) {	e.printStackTrace();}
		 		
	}
	
	
}


