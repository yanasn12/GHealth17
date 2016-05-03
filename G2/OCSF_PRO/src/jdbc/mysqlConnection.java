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
		
		try 
		{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {/* handle the error*/}
        
        try 
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/G17","root","Braude");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.3.68/test","root","Root");
            System.out.println("SQL connection succeed");
      //      data=pull("sql1.human",conn);
      //      System.out.println(data.toString());
     //       createTableCourses(conn);
           // push("sql1.human",data,conn);
     	} catch (SQLException ex) 
     	    {/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
   	}
	
	public static ArrayList<String> pull (String DB)
	{
		ArrayList<String> data= new ArrayList<String>();
		Statement stmt;
		String line="";
		try 
		{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {/* handle the error*/}

		try 
		{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/g17","root","Braude");
			stmt = con.createStatement();
		//	PreparedStatement newPull = con.prepareStatement("SELECT *" + " FROM ?");
			

			ResultSet rs = stmt.executeQuery("SELECT * FROM "+ DB+";");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			
	 		while(rs.next())
	 		{
				 // Print out the values
	 			for(int i=1; i <= columnsNumber; i++)
	 				line=line+ " " + rs.getString(i);
			data.add(line);
			line="";
	 		} 
			rs.close();
			//stmt.executeUpdate("UPDATE course SET semestr=\"W08\" WHERE num=61309");
		} catch (SQLException e) {e.printStackTrace();}
		finally { return data; }
	}
public static boolean  checkme (String DB,ArrayList<String> data)
{
	Statement stmt;
	boolean result=true;
	String insertTableSQL = "SELECT * FROM "+ DB + " WHERE PhysicianName="+data.get(0);
	try 
	{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (Exception ex) {/* handle the error*/}

	try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/g17","root","Braude");
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(insertTableSQL);
		if(rs.next())
		{
			result=false;
			return result;
		}
			
		return result;
							
	} catch (SQLException e) {System.out.println("error: checkme- DB "+DB+" PhysicianName"+ data.get(0));return false;}
	 		
}

	
	public static void push (String DB,ArrayList<String> data)
	{
		String text= "UPDATE "+ DB +" SET Specialization=? WHERE PhysiciaName=? ;";
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {/* handle the error*/}

		try {	
			
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost/g17","root","Braude");
			PreparedStatement insertTableSQL = con.prepareStatement(text);
			
															//UPDATE course SET semestr="Winter08-09" WHERE num=61309;
			insertTableSQL.setString(1, data.get(1).toString());
			insertTableSQL.setString(2, data.get(0).toString());
			System.out.println("im here");
			insertTableSQL.executeUpdate();
			

		} catch (SQLException e) 
		{
			//if(checkme(DB,data)==false)
				System.out.println("unable to push -PUSH "+ text);
		}
		 		
	}
	
	
}


