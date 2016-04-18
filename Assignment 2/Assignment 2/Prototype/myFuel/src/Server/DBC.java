package Server;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class to server as a connector to the database at the selected connection adress
 * connects to database localhost/myfuel with user roor and pass 16499461
 * static for now but can be changed to be dynamic by adding a Constructor with parameters
 * returns the established connection to the calling class.
 * 
 */

public class DBC {

	public static Connection mySQLConnection()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		try{
			return DriverManager.getConnection("jdbc:mysql://localhost/myfuel","root","16499461");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
