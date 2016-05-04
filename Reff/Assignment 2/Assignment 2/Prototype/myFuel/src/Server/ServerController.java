package Server;

import common.fuel;
import Server.DBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The server controller responsible for contacting the database
 * and executing querys on the database from the server
 * the methods return an answer to the caller with a specific type
 * to handle at the other end.
 * 
 */

public class ServerController {

	public static fuel searchFuel(String con)
	{
		fuel f1 = null;
		
		Connection DBcon = DBC.mySQLConnection();
		try{
			PreparedStatement getRow = DBcon.prepareStatement("SELECT * FROM fueltype where FuelName = ?");
			getRow.setString(1, con);
			ResultSet rs = getRow.executeQuery();
			if(rs.next())
				f1 = new fuel(rs.getInt(1),rs.getString(2),rs.getInt(3));
			DBcon.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return f1;
	}
	
	public static void updateFuel(fuel f1)
	{

		Connection DBcon = DBC.mySQLConnection();
		try{
			PreparedStatement updateRow = DBcon.prepareStatement("UPDATE fueltype SET Price = ? , FuelName = ? WHERE idFuelType = ?");
			updateRow.setInt(1,f1.getPrice());
			updateRow.setString(2,f1.getName());
			updateRow.setInt(3, f1.getId());
			updateRow.executeUpdate();

			DBcon.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
}
