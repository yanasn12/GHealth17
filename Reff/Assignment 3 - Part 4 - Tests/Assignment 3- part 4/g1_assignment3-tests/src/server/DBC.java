package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import common.Operations;
import entities.*;


/**
 * Class to server as a connector to the database at the selected connection adress
 * connects to database localhost/myfuel with user , pass and login
 * saved the connection and the log information
 * 
 * all database querys will be writen here
 */

public class DBC {
	
	public Connection conn;
	private static final String driver = "com.mysql.jdbc.Driver";
	private String database;
	private String username;
	private String password;
	private Statement st;
/**
 * constructor to connect to the database with the jdbc drivers
 * saves the database information localy
 * @param dbName
 * @param dbLogin
 * @param dbPass
 * @throws Exception
 */
	public DBC(String dbName ,String dbLogin ,String dbPass) throws Exception
	{
		try {
			Class.forName(driver).newInstance();
		} catch (Exception ex) {
			throw ex;
		}
		try {
		this.database = new String(dbName);
		this.username = new String(dbLogin);
		this.password = new String(dbPass);

		conn = DriverManager.getConnection(this.database, this.username,this.password);

		}
		catch(SQLException e)
		{
			System.out.println("Could not connect to DB");
		}

	}
	/**
	 * method to return what to do with the user details requesting to log in
	 * if details match do not match the database will return ERROR - not registered
	 * else the method will chech the state and position of the user and will return corespondigle
	 * UserLoggedIn/Not Logged In:Worker/Customer
	 * @param userName
	 * @param userPass
	 * @return
	 */
	public Operations getLoginUser(String userName, String userPass)
	{
		ResultSet rs = null;
		Operations ans = Operations.ERROR;
		String query = new String("SELECT * FROM myfuel.login  WHERE UserName = '" + userName+ "' AND Password = '" + userPass + "' ;");
		try{
		st = conn.createStatement();
		rs = st.executeQuery(query);
		if(rs.next())
			ans = Operations.ALLOW_LOG;
		rs.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return ans;
	}
	/**
	 * template databse query method
	 * @param id
	 * @return
	 */
	public String getTnameByTid(int id) {
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.testing WHERE idtesting ="+id);
		String ans = null;
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				ans=rs.getString(2);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return ans;
	}	
	/**
	 * method to get Car info from the database by CarID
	 * a query and being executed and the info is being sent back.
	 * the result is saved a Car class and returned for further use/
	 * @param id
	 * @return
	 */
	public Car getCarInfoByID(int id)  {
		Car currentCar= new Car();//will be changed to getactive in the future
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.car WHERE carID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					//if(id==rs.getInt(1));
					currentCar.setCarID(rs.getInt(1));
					currentCar.setSubscriptionID(rs.getInt(2));
					currentCar.setFuelTypeID(rs.getInt(3));
					currentCar.setCustomerID(rs.getInt(4));
					
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return currentCar;
	}
	/**
	 * method to add a car into the database.
	 * receives a Car type class and executes a query to add the car to the DataBase.
	 * @param carToAdd
	 * @throws SQLException
	 */
	public void addCarInfo(Car carToAdd) 
	{
		//ResultSet rs = null;
		try{
			
			st=conn.createStatement();
			String query = new String("INSERT INTO myfuel.car (CarID, subscriptionID,fuelTypeID,carCustomerID) VALUES ('" + carToAdd.getCarID()+"','"+carToAdd.getSubscriptionID()+"','"+carToAdd.getFuelTypeID()+"','"+carToAdd.getCustomerID()+"');");
			st.executeUpdate(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}
		
	}
	/**
	 * method to change fields of a specific Car in the DataBase.
	 * the method receives a Car class and updates the fields accordingly.
	 * @param c
	 * @throws SQLException
	 */
	public void changeCarInfo(Car c) throws SQLException
	{
		try{
				//incase u want to change something else its literally copying the query and change the fields. No Biggie
				st=conn.createStatement();
				String query = new String("UPDATE myfuel.car SET subscriptionID='"+c.getSubscriptionID()+"'WHERE carID='"+c.getCarID()+"';");
				st.executeUpdate(query);
				String query1 = new String("UPDATE myfuel.car SET fuelTypeID='"+c.getFuelTypeID()+"'WHERE carID='"+c.getCarID()+"';");
				st.executeUpdate(query1);
				String query2 = new String("UPDATE myfuel.car SET carCustomerID='"+c.getCustomerID()+"'WHERE carID='"+c.getCarID()+"';");
				st.executeUpdate(query2);
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
		
	}
	/**
	 * method to delete a Car from the DataBase.
	 * receives Car ID and deletes the car that matches.
	 * @param id
	 */
	public void DeleteCarByCid(int id)  {
				try{
					//DELETE FROM `myfuel`.`car_customer` WHERE `carCustomerID`='4';
					st=conn.createStatement();
					String query = new String("DELETE FROM myfuel.car"+" WHERE carID="+id+";");
					st.executeUpdate(query);
				}
				//String query = new String("INSERT INTO myfuel.car (CarID, subscriptionID) VALUES ('" 
				//+ carToAdd.getCarId()+ "', '"+ ut.getPosName()+ "', '"+ ut.getPrivilegeLevels()+ "', b'"+ boolToInt(ut.isCustomer())+ "', b'"+ boolToInt(ut.isCarCustomer())+ "');");
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
		}
	/**
	 * method to get all the Cars information from the Database 
	 * Method executes a query and stores the information in a LinkedList
	 *  returns LinkedList<Car>
	 * 
	 * @return
	 */
	public LinkedList<Car> getAllCars() 
	{
		
		Car c;
		ResultSet rs = null;
		LinkedList<Car> cars=new LinkedList<Car>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.car");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				c=new Car();
				c.setCarID(rs.getInt(1));
				c.setSubscriptionID(rs.getInt(2));
				c.setFuelTypeID(rs.getInt(3));
				c.setCustomerID(rs.getInt(4));
				cars.add(c);
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cars;
		
	}
	/**
	 * method to get a subscription info
	 * receives subscription ID and returns the result by executing a query.
	 * the information is saved in a Subscription type  Class and  returned for further use.
	 * @param id
	 * @return
	 */
	public Subscription getSubscriptionBySid(int id)  {
		MonthlyFull mf;
		MonthlySimple ms;
		MonthlyMultiple mm;
		Subscription currentSub= new Subscription();//will be changed to getactive in the future
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.subscription WHERE subscriptionID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					currentSub.setSubID(rs.getInt(1));
					currentSub.setDiscountTableID(rs.getInt(2));
					switch(rs.getInt(3))
					{
					case 0:
						return currentSub;
					case 1:
						mf=new MonthlyFull();
						mf=getMonthlyFullByMID(id,currentSub);
						return mf;
					case 2:
						mm=new MonthlyMultiple();
						mm=getMonthlyMultByMID(id, currentSub);
						return mm;
					case 3:
						ms=new MonthlySimple();
						ms=getMonthlySimpleByMID(id, currentSub);
						return ms;
					}
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return currentSub;
	}
	/**
	 * method to add a Subscription to the DataBase.
	 * the method receives a Subscription Class and adds it by executing a query.
	 * @param subToAdd
	 * @throws SQLException
	 */
	public void addSubscription(Subscription subToAdd,int disc) throws SQLException
	{
		//ResultSet rs = null;
		try{
			
			st=conn.createStatement();
			String query = new String("INSERT INTO myfuel.subscription (subscriptionID, discountTableID,discriminator) VALUES ('" + subToAdd.getSubID()+"','"+subToAdd.getDiscountTableID()+"','"+disc+"');");
			st.executeUpdate(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}
		
	}
	/**
	 * method to change a Subscription information in the DataBase.
	 * Receives a Subscription Class Type and updates the fields.
	 * @param sub
	 * @throws SQLException
	 */
	public void changeSubInfo(Subscription sub) throws SQLException
	{
		try{
			//incase we(israel) wants id as a secondary variable to the func its a tiny change! All GOOD 
				st=conn.createStatement();
				String query = new String("UPDATE myfuel.subscription SET discountTableID='"+sub.getDiscountTableID()+"'WHERE subscriptionID='"+sub.getSubID()+"';");
				st.executeUpdate(query);
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
		
	}	
	/**
	 * method to delete a subscription from the DataBase
	 * receives a Subscription ID and deletes the matching line from the DataBase table.
	 * @param id
	 */
	public void DeleteSubByCid(int id)  {
		try{
			//DELETE FROM `myfuel`.`car_customer` WHERE `carCustomerID`='4';
			st=conn.createStatement();
			String query = new String("DELETE FROM myfuel.subscription"+" WHERE subscriptionID="+id+";");
			st.executeUpdate(query);
		}
		//String query = new String("INSERT INTO myfuel.car (CarID, subscriptionID) VALUES ('" 
		//+ carToAdd.getCarId()+ "', '"+ ut.getPosName()+ "', '"+ ut.getPrivilegeLevels()+ "', b'"+ boolToInt(ut.isCustomer())+ "', b'"+ boolToInt(ut.isCarCustomer())+ "');");
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}
	}
	/**
	 *method to receive all the Subscriptions from the DataBase 
	 * the method executes a Query and returning a LinkedList<Subscription>
	 */
	public LinkedList<Subscription> getAllSubscriptions() 
	{
		
		Subscription sub;
		ResultSet rs = null;
		LinkedList<Subscription> subs=new LinkedList<Subscription>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.subscription");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				sub=new Subscription();
				sub.setSubscriptionID(rs.getInt(1));
				sub.setDiscountTableID(rs.getInt(2));
				subs.add(sub);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return subs;
		
	}
	/**
	 * method to get a discount info from the DataBase by DiscountID
	 * receives DiscountID and returns a Line from the DataBase that Matches that ID.
	 * the information is saved in a Discount_Table class and returned for further use.
	 * @param id
	 * @return
	 */
	public DiscountTable getDiscountByDid(int id) 
	{
		DiscountTable discT=new DiscountTable();
		String descTable, descTableChanges;
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.discount_table WHERE discountTableID ="+id);
		
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
			{
				discT.setDiscountTableID(rs.getInt(1));
				descTable = rs.getString(2);
				descTableChanges = rs.getString(3);
				discT.setDiscountTable(discT.fromStrToDisc(descTable));
				discT.setDiscountTableChanges(discT.fromStrToDisc(descTableChanges));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return discT;
	}
	/**
	 * method to add a discount to the DataBase
	 * receives a DiscountTable class variable and add the discount to the DataBase.
	 * addition is done by executing a query.
	 * 
	 */
	public void addDiscount(DiscountTable discToAdd) throws SQLException
	{
		//ResultSet rs = null;
		try{
			
			st=conn.createStatement();
			String query = new String("INSERT INTO myfuel.discount_table (discountTableID, DiscountTable,DiscountTableChanges) VALUES ('" + discToAdd.getDiscountTableID()+"','"+discToAdd.DoubleToStr(discToAdd.getDiscountTable())+"','"+discToAdd.DoubleToStr(discToAdd.getDiscountTableChanges())+"');");
			st.executeUpdate(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}
		
	}
	/**
	 * method to chage a discount info in the DataBase
	 * receives a DiscountTable class and updates the fields in the database by executing a query,
	 * @param disc
	 * @throws SQLException
	 */
	public void changeDiscont(DiscountTable disc) throws SQLException
	{
		try{
			//incase we(israel) wants id as a secondary variable to the func its a tiny change! All GOOD 
				st=conn.createStatement();
				String query = new String("UPDATE myfuel.discount_table SET discountTable='"+disc.DoubleToStr(disc.getDiscountTable())+"'WHERE discountTableID='"+disc.getDiscountTableID()+"';");
				st.executeUpdate(query);
				st=conn.createStatement();
				String query1 = new String("UPDATE myfuel.discount_table SET discountTableChanges='"+disc.DoubleToStr(disc.getDiscountTableChanges())+"'WHERE discountTableID='"+disc.getDiscountTableID()+"';");
				st.executeUpdate(query1);
				
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
		
	}
	/**
	 * method to delete a discount from the DataBase by the Discount ID.
	 * receives a discount ID and deletes the matching discount by executing a query.
	 * @param id
	 */
	public void deleteDiscountByDid(int id)
	{
		try{
			//DELETE FROM `myfuel`.`car_customer` WHERE `carCustomerID`='4';
			st=conn.createStatement();
			String query = new String("DELETE FROM myfuel.discount_table"+" WHERE discountTableID="+id+";");
			st.executeUpdate(query);
		}
		//String query = new String("INSERT INTO myfuel.car (CarID, subscriptionID) VALUES ('" 
		//+ carToAdd.getCarId()+ "', '"+ ut.getPosName()+ "', '"+ ut.getPrivilegeLevels()+ "', b'"+ boolToInt(ut.isCustomer())+ "', b'"+ boolToInt(ut.isCarCustomer())+ "');");
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}
    }
	/**
	 * method to pull all the discounts from the db and return them as a LinkedList
	 * used to work with several discounts.
	 * method not receiving anything and returning a LinkedList of DiscountTables.
	 */
	public LinkedList<DiscountTable> getAllDiscounts()
	{
		String descTable,descTableChanges;
		LinkedList<DiscountTable> discounts=new LinkedList<DiscountTable>();
		DiscountTable discT;
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.discount_table");
		//String ans = null;
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			while(rs.next())
			{
				discT=new DiscountTable();
				discT.setDiscountTableID(rs.getInt(1));
				descTable = rs.getString(2);
				descTableChanges = rs.getString(3);
				discT.setDiscountTable(discT.fromStrToDisc(discT.getDiscounts()));
				discT.setDiscountTableChanges(discT.fromStrToDisc(discT.getDiscountChanges()));
				discounts.add(discT);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return discounts;
		
	}
	/**
	 * method to get a fuel_type information from the DataBase.
	 * receives a  fuel_type id and returns the matching results from the DB
	 * the information is saved into a Fuel_Type class and returned for further Use.
	 * @param id
	 * @return
	 */
	public FuelType getFuelInfo(int id)
	{
		FuelType currentType= new FuelType();//will be changed to getactive in the future
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.fuel_type WHERE fueltypeID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					currentType.setFuelTypeID(rs.getInt(1));
					currentType.setFuelName(rs.getString(2));
					currentType.setBasePrice(rs.getDouble(3));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return currentType;
	}	
	/**
	 * method to add a FuelType to the DataBase
	 * receives a Fuel_Type class and adds it to the DataBase by executing a query,
	 * @param typeToAdd
	 */
	public void addFuelType(FuelType typeToAdd)
	{
		//ResultSet rs = null;
				try{
					
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.fuel_type (fuelTypeID, fuelName,basePrice) VALUES ('" + typeToAdd.getFuelTypeID()+"','"+typeToAdd.getFuelName()+"','"+typeToAdd.getBasePrice()+"');");
					st.executeUpdate(query);
				}
				//String query = new String("INSERT INTO myfuel.car (CarID, subscriptionID) VALUES ('" 
				//+ carToAdd.getCarId()+ "', '"+ ut.getPosName()+ "', '"+ ut.getPrivilegeLevels()+ "', b'"+ boolToInt(ut.isCustomer())+ "', b'"+ boolToInt(ut.isCarCustomer())+ "');");
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * method to change the fields of a fuelType
	 * receives a fuelType Class and updates the fields of the fuel with the matching id
	 * the update is done by executing a query
	 * @param UT_id
	 * @return
	 */
	public void changeFuelType(FuelType typeTochange)
	{

		try{
			//incase we(israel) wants id as a secondary variable to the func its a tiny change! All GOOD 
				st=conn.createStatement();
				String query = new String("UPDATE myfuel.fuel_type SET fuelName='"+typeTochange.getFuelName()+"'WHERE fuelTypeID='"+typeTochange.getFuelTypeID()+"';");
				st.executeUpdate(query);
				st=conn.createStatement();
				String query1 = new String("UPDATE myfuel.fuel_type SET basePrice='"+typeTochange.getBasePrice()+"'WHERE fuelTypeID='"+typeTochange.getFuelTypeID()+"';");
				st.executeUpdate(query1);
				
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
			
	}
	/**
	 * metod to delete a fuel type from the Database
	 * Finds the matching fuelTypeID in the Database and Deletes it
	 * the removal is done by executing a query
	 * 
	 * @param id
	 */
	public void DelFuelTypeByfID(int id)
	{
		try{
			//DELETE FROM `myfuel`.`car_customer` WHERE `carCustomerID`='4';
			st=conn.createStatement();
			String query = new String("DELETE FROM myfuel.fuel_type"+" WHERE fuelTypeID="+id+";");
			st.executeUpdate(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}
		
	}
	/**
	 * method to receive all the FuelTypes from the DataBase
	 * method executes a Query and returns LinkedList<FuelType>
	 * 
	 */
	public LinkedList<FuelType> getAllFuelTypes()
	{
		LinkedList<FuelType> fTypes=new LinkedList<FuelType>();
		FuelType fuel;
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.fuel_type");
		//String ans = null;
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			while(rs.next())
			{
				fuel=new FuelType();
				fuel.setFuelTypeID(rs.getInt(1));
				fuel.setFuelName(rs.getString(2));
				fuel.setBasePrice(rs.getDouble(3));
				fTypes.add(fuel);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return fTypes;
		
	}
	/**
	 * method to get a UserType By ID
	 * method received an id and returning a UserType Class with the required information 
	 * get is done by executing an SQL query.
	 * @param UT_id
	 * @return
	 */
	public UserType getUserTypeByUid(int UT_id)
	{
		UserType retU = new UserType();

		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.user_type WHERE userTypeID ="+UT_id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
			{	
				retU.setUserTypeID(rs.getInt(1));
				retU.setPosName(rs.getString(2));
				retU.setPrivilegeLevels(rs.getString(3));
				retU.setCustomer(rs.getBoolean(4));
				retU.setCarCustomer(rs.getBoolean(5));
			}
			rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return retU;
	}
	/**
	 * Database method to execute a insert command
	 * Dissasemble the User_Type class ut to the primetive fields
	 * And add a new row to the User_Type table with the fields
	 * @param ut - instance of a User_Type class
	 * @throws SQLException to handle in the controller or the server in case of "Duplicate Pk ,Etc..."
	 */
	public void insertNewUserType(UserType ut) 
	{
		try{
		String query = new String("INSERT INTO myfuel.user_type (userTypeID, posName, privilegeLevels, isCustomer, isCarCustomer) VALUES ('" 
				+ ut.getUserTypeID()+ "', '"+ ut.getPosName()+ "', '"+ ut.getPrivilegeLevels()+ "', b'"+ boolToInt(ut.isCustomer())+ "', b'"+ boolToInt(ut.isCarCustomer())+ "');");
		st = conn.createStatement();
		st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Method to chage a User_Type row in the database table bu its PK = userTypeID 
	 * can set privilegeLevels and posName by userTableID
	 * 
	 * @param ut - instance of a user_type class which had the information that needs
	 * to be inserted into the database with the corresponding row's PK
	 * @throws SQLException - to handle outside the database in extream cases
	 */
	public void changeUserTypeByUid(UserType ut) throws SQLException
	{
		st = conn.createStatement();
		String query = new String("UPDATE myfuel.user_type  SET privilegeLevels = '"+ut.getPrivilegeLevels() +"' WHERE userTypeID = '"+ ut.getUserTypeID() + "';");
		st.executeUpdate(query);
		query = new String("UPDATE myfuel.user_type  SET posName = '"+ut.getPosName() +"' WHERE userTypeID = '"+ ut.getUserTypeID() + "';");
		st.executeUpdate(query);
	}
	/**
	 * Method to delete a User_Type by its PK
	 * Will first update all the users that have said User_type to be UserTypeID='0'==Undefiened - dummy reffrence
	 * and delets the entire row with UT_id
	 * @param UT_id the PK of the row desiered to be deleted
	 * @throws SQLException - to handle outside in controllers and such in extream cases
	 */
	public void delUserTypeByUid(int UT_id) throws SQLException
	{
		st = conn.createStatement();
		String query = new String("UPDATE myfuel.user  SET UserTypeID = '0' WHERE userTypeID = '"+ UT_id + "';");
		st.executeUpdate(query);
		query = new String("DELETE FROM myfuel.user_type"+" WHERE userTypeID = "+UT_id+";");
		st.executeUpdate(query);
	}
	/**
	 * method to get all the userTypes from the database
	 * the method executes a query and returns LinkedList<UserType>
	 * 
	 */
	public LinkedList<UserType> getAllUserTypes()
	{
		LinkedList<UserType> usTypes=new LinkedList<UserType>();
		UserType usType;
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.user_type");
		//String ans = null;
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			while(rs.next())
			{
				usType=new UserType();
				usType.setUserTypeID(rs.getInt(1));
				usType.setPosName(rs.getString(2));
				usType.setPrivilegeLevels(rs.getString(3));
				usType.setCustomer(rs.getBoolean(4));
				usType.setCarCustomer(rs.getBoolean(5));
				usTypes.add(usType);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return usTypes;
		
	}
	/**
	 * Method to create and return a User Class reffrenced in the database by the PK UserID
	 * the method will select all with the required returned Uid (only 1 since Uid is the PK)
	 * will create a returned User class retU and dissasemble the data from the databse
	 * to fill the retU.
	 * The SQLException is handled inside the method to not risk the chance of sending wrong information

	 * @param Uid - the PK of the User we want to get
	 * @return retU a User Class with the required UserID
	 */
	public User getUserByUid(int Uid)
	{
		User retU = new User();
		Customer c;
		Worker w;
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.user WHERE userID ="+Uid);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
			{	
				retU.setUserID(rs.getInt(1));
				retU.setUserName(rs.getString(2));
				retU.setPhoneNo(rs.getString(3));
				retU.setFirstName(rs.getString(4));
				retU.setLastName(rs.getString(5));
				retU.seteMail(rs.getString(6));
				retU.setUserTypeID(rs.getInt(7));
				switch(rs.getInt(8))
				{
				case 1:
					c=new Customer();
					c=getCustomerBycID(Uid,retU);
					return c;
				case 2:
					w= new Worker();
					w=getWorkerByWID(Uid,retU);
					return w;
				}
			}
			rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return retU;
	}
	/**
	 * method to get user by username - to handle login by user name
	 * @param Uname - the user name to be searched in the database , which is also a unique field
	 * @return
	 */
	public User getUserByUname(String Uname)
	{
		
		User retU = new User();

		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.user WHERE userName ='"+Uname+"';");
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
			{	
				retU = getUserByUid(rs.getInt(1));
			}
			rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return retU;
	}
	/**
	 * Method to insert a new user to the database
	 * the method gets the user data to enter and INSERTS Into the user table
	 * @param us - the data of the row to be inserted
	 * @throws SQLException - to handle outside of the method in case of failures
	 */
	public void insertNewUser(User us,int disc) 
	{
		try{
		String query = new String("INSERT INTO myfuel.user (userId, userName, phoneNo, firstName, lastName, eMail, userTypeID,discriminator) VALUES ('" 
				+ us.getUserID()+ "', '"+ us.getUserName()+ "', '"+ us.getPhoneNo()+ "', '"+ us.getFirstName()+ "', '"+ us.getLastName()+"', '"+us.geteMail()+"', '"+us.getUserTypeID() +"', '"+disc+ "');");
		st = conn.createStatement();
		st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * method to change User Information in the database
	 * the method will handle changing the login table aswell incase the userName is changed
	 * since the login table uses the same UserName in order to keep consistency
	 * the method will execute an UPDATE query to set each individual field of the User row to be the new data set.
	 * @param us - a user class containing all the data to be changed in the row
	 * @throws SQLException - to handle outside of the database incase of failures
	 */
	public void changeUserByUid(User us) throws SQLException
	{
		String usN=null;
		ResultSet rs;
		String query =  new String("SELECT * FROM myfuel.user WHERE userID = '"+us.getUserID()+"';");
		rs=st.executeQuery(query);
		if(rs.next())
			usN = rs.getString(2);
		
		st = conn.createStatement();
		query = new String("UPDATE myfuel.user  SET userName = '"+us.getUserName() +"' WHERE userID = '"+ us.getUserID() + "';");
		st.executeUpdate(query);
		query = new String("UPDATE myfuel.login  SET UserName = '"+us.getUserName() +"' WHERE UserName = '"+ usN + "';");
		st.executeUpdate(query);
		query = new String("UPDATE myfuel.user  SET phoneNo = '"+us.getPhoneNo() +"' WHERE userID = '"+ us.getUserID() + "';");
		st.executeUpdate(query);
		query = new String("UPDATE myfuel.user  SET firstName = '"+us.getFirstName() +"' WHERE userID = '"+ us.getUserID() + "';");
		st.executeUpdate(query);
		query = new String("UPDATE myfuel.user  SET lastName = '"+us.getLastName() +"' WHERE userID = '"+ us.getUserID() + "';");
		st.executeUpdate(query);
		query = new String("UPDATE myfuel.user  SET eMail = '"+us.geteMail() +"' WHERE userID = '"+ us.getUserID() + "';");
		st.executeUpdate(query);
		query = new String("UPDATE myfuel.user  SET UserTypeID = '"+us.getUserTypeID() +"' WHERE userID = '"+ us.getUserID() + "';");
		st.executeUpdate(query);
	}
	/**
	 * method to delete a user class reffrence in the database by the userID
	 * @param Uid - the PK of the desired rowed to be deleted
	 * @throws SQLException - to handle outside of the database incase of failures
	 */
	public void delUserByUid(int Uid) throws SQLException
	{
		st = conn.createStatement();
		String query = new String("DELETE FROM myfuel.user"+" WHERE UserID = "+Uid+";");
		st.executeUpdate(query);
	}
	/**
	 * method to get all the Users in the DataBase 
	 * the Method Executes a query and returns LinkedList<User>
	 */
	public LinkedList<User> getAllUsers()
	{
		LinkedList<User> users=new LinkedList<User>();
		User user;
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.user");
		//String ans = null;
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			while(rs.next())
			{
				user=new User();
				user.setUserID(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPhoneNo(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.seteMail(rs.getString(6));
				user.setUserTypeID(rs.getInt(7));
				users.add(user);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return users;
		
	}
	/**
	 * method to get a Station Information from the DataBase.
	 * the method receives a station ID and returns a station info (including location) by executing a query.
	 * @param Sid
	 * @return
	 */
	public Station getStationById(int Sid)
	{
		Station retS = new Station();

		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.station WHERE stationID ="+Sid);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
			{	
				retS.setStationID(rs.getInt(1));
				String query1 = new String("SELECT * FROM myfuel.location WHERE locationID ="+Sid);
				rs=st.executeQuery(query1);
				System.out.println (query1);
				if(rs.next())
				{
					retS.setLocalAddr(rs.getString(2));
				}
			}
			else
				return null;
			
			rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return retS;
	}
	/**
	 * method to add a new station into the DataBase.
	 * the method receives a new station class and adds it by fields to the DataBase table by executing a query
	 * @param s
	 * @throws SQLException
	 */
	public void insertNewStation(Station s) throws SQLException
	{
		String query = new String("INSERT INTO myfuel.location (locationID,locAddr) VALUES ('" +s.getLocationID()+ "', '"+s.getLocalAddr()+"');");
		st = conn.createStatement();
		st.executeUpdate(query);
		String query1 = new String("INSERT INTO myfuel.station (stationID) VALUES ('" +s.getLocationID()+"');");
		st.executeUpdate(query1);
	}
	/**
	 * method to change a station information in the DataBase.
	 * the method receives a Station type class and updates every field.
	 * update is done by executing a query.
	 * @param s
	 * @throws SQLException
	 */
	public void changeStationById(Station s) 
	{
		try
		{
		st = conn.createStatement();
		String query = new String("UPDATE myfuel.location SET locAddr = '"+s.getLocalAddr() +"' WHERE locationID = '"+ s.getLocationID() + "';");
		st.executeUpdate(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	
	}
	/**
	 * method to delete a station from the DataBase by the stationID
	 * method extends location therefore in order to delte a station a delete from location table is required 
	 * all the deletes are done by executing queries
	 * @param Sid
	 * @throws SQLException
	 */
	public void delStationById(int Sid) throws SQLException//add after adding fuel+stock to handle FK
	{
		st = conn.createStatement();
		String query = new String("DELETE FROM myfuel.station"+" WHERE stationID = "+Sid+";");
		st.executeUpdate(query);
		 query = new String("DELETE FROM myfuel.location"+" WHERE locationID = "+Sid+";");
		 st.executeUpdate(query);
	}
	/**
	 * method to receive all the stations in the DataBase
	 * method executes a query and stores the information in a LinkedList
	 * the method returns LinkedList<Station>
	 * 
	 */
	public LinkedList<Station> getAllStations()
	{
		LinkedList<Station> stations=new LinkedList<Station>();
		Station stat;
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.station");
		//String ans = null;
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			while(rs.next())
			{
				stat=new Station();
				stat=getStationById(rs.getInt(1));
				
				stations.add(stat);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return stations;
		
	}
	/**
	 * method to get a Worker info by ID.
	 * the method receives a id and searching the DataBase for the line with the same id.
	 * the information is copied to a Worker class and returned for further use.
	 * @param Wid
	 * @return
	 */
	public Worker getWorkerByWid(int Wid)
	{
		Worker retW = new Worker();

		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.worker WHERE workerID ="+Wid);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
			{	
				retW.setUserID(rs.getInt(1));
				retW.setLocationID(rs.getInt(2));;
				query = new String("SELECT * FROM myfuel.user WHERE userID ="+Wid);
				rs=st.executeQuery(query);
				if(rs.next()){
					retW.setUserName(rs.getString(2));
					retW.setPhoneNo(rs.getString(3));
					retW.setFirstName(rs.getString(4));
					retW.setLastName(rs.getString(5));
					retW.seteMail(rs.getString(6));
					retW.setUserTypeID(rs.getInt(7));
				}
			}
			rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return retW;
	}
	/**
	 * method to get a Worker from the Database by WorkerID
	 * the method gets a User Class and an id and returns a Worker with all the information
	 * the is designed to work with discriminators.
	 */
	public Worker getWorkerByWID(int Wid,User retU)
	{
		Worker retW= new Worker();

		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.worker WHERE workerID ="+Wid);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
			{	
				retW.setUserName(retU.getUserName());
				retW.setPhoneNo(retU.getPhoneNo());
				retW.setFirstName(retU.getFirstName());
				retW.setLastName(retU.getLastName());
				retW.seteMail(retU.geteMail());
				retW.setUserTypeID(retU.getUserTypeID());
				retW.setLocationID(rs.getInt(2));
			}
			rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return retW;
		
		
	}
	/**
	 * method to insert a new worker.
	 * receives a worker class and adds the fields into the Database Table.
	 * adding is done by executing a query.
	 * @param w
	 * @throws SQLException
	 */
	public void insertNewWorker(Worker w) throws SQLException
	{
		insertNewUser(w,2);
		String query = new String("INSERT INTO myfuel.worker (workerID, stationID) VALUES ('" + w.getUserID()+"', '"+w.getLocationID() + "');");
		st = conn.createStatement();
		st.executeUpdate(query);
	}
	/**
	 * method to change a worker data in the DataBase. 
	 * reveives Worker class and updates the worker in the user table and then in the Worker Table.
	 * @param w
	 * @throws SQLException
	 */
	public void changeWorker(Worker w) 
	{
		try
		{
		changeUserByUid(w);
		st=conn.createStatement();
		String query = new String("UPDATE myfuel.station  SET locationID = '"+w.getLocationID() +"' WHERE workerID = '"+ w.getUserID() + "';");
		st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * method to delete a worker from the DataBase. 
	 * reveives ID and deletes the worker from the user table and then from the Worker Table.
	 * @param w
	 * @throws SQLException
	 */
	public void delWorkerByWid(Worker w) throws SQLException
	{
		String query = new String("DELETE FROM myfuel.worker"+" WHERE workerID = "+w.getUserID()+";");
		st.executeUpdate(query);
		delUserByUid(w.getUserID());
		st = conn.createStatement();
	}
	/**
	 * method to get all the Workers in the DataBase 
	 * the method executes a query and stores the workers information in a LinkedList
	 * the method returns LinkedList<Worker> 
	 * @return
	 */
	public LinkedList<Worker> getAllWorkers()
	{	
		
		LinkedList<Worker> workers= new LinkedList<Worker>();
		Worker wo;
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.worker");
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			while(rs.next())
			{
				wo=new Worker();
				wo=getWorkerByWid(rs.getInt(1));
				workers.add(wo);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return workers;
		
	}
	/**
	 * method to get location information from the DataBase 
	 * the method receives a location ID and returns the location info in a class.
	 * info is pulled by executing a query.
	 * @param b
	 * @return
	 */
	public Location getLocationByLID(int id)  {
		Location loc= new Location();//will be changed to getactive in the future
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.location WHERE locationID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					//if(id==rs.getInt(1));
					loc.setLocationID(rs.getInt(1));
					loc.setLocalAddr(rs.getString(2));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return loc;
	}
	/**
	 * method to add a new location to the DataBase .
	 * method receives a Location class and adds to the table by fields using getters and setters
	 * addition is done by executing a query.
	 * @param b
	 * @return
	 * @throws SQLException 
	 */
	public void addNewLocation(Location loc) throws SQLException
	{
		String query = new String("INSERT INTO myfuel.location (locationID, locAddr) VALUES ('" + loc.getLocationID()+"', '"+loc.getLocalAddr() + "');");
		st = conn.createStatement();
		st.executeUpdate(query);
	}
	/**
	 * method to change a location info in the Database.
	 * method receives a location class and updates the fields in the DataBase table accordingly.
	 * information update is done by executing a query.
	 * @param b
	 * @return
	 */
	public void changeLocationInfo(Location loc) 
	{
		try{
		st=conn.createStatement();
		String query = new String("UPDATE myfuel.location  SET locAddr = '"+loc.getLocalAddr() +"' WHERE locationID = '"+ loc.getLocationID() + "';");
		st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * method to delete a location from the db by a location id
	 * method receives a location id and deletes the matching table line from the DataBase
	 * the delete action is done by executing a query.
	 * @param id
	 * @throws SQLException
	 */
	public void delLocationByLocID(int id) throws SQLException
	{
		st=conn.createStatement();
		String query = new String("DELETE FROM myfuel.location"+" WHERE locationID = "+id+";");
		st.executeUpdate(query);
	}
	/**
	 * method to get all the location in the DataBase 
	 * the method executes a quey and stores the information in a LinkedList
	 * the method returs LinkedList<Location>
	 */
	public LinkedList<Location> getAllLocations()
	{	
		
		LinkedList<Location> locations= new LinkedList<Location>();
		Location loc;
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.location");
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			while(rs.next())
			{
				loc=new Location();
				loc=getLocationByLID(rs.getInt(1));
				locations.add(loc);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return locations;
		
	}
	/**
	 * method to get a credit card information from the DataBase
	 * the method recevies a CreditCard ID and executes a query to find the matching line
	 * information is stored in a CreditCard Class and returned for further use.
	 * @param b
	 * @return
	 */
	public CreditCard getCreditInfoByCID(int id)
	{
		PaymentInfo payinf=new PaymentInfo();
		CreditCard credC= new CreditCard();//will be changed to getactive in the future
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.credit_card WHERE creditCardID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					payinf=getPaymentInfoByID(rs.getInt(1));
					credC.setPaymentInfoID(payinf.getPaymentInfoID());
					credC.setCustomerID(payinf.getCustomerID());
					credC.setPrefferedMethod(payinf.getPreferredMethod());
					credC.setCreditCardID(rs.getInt(1));
					credC.setCardNo(rs.getString(2));
					credC.setValidDate(rs.getString(3));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return credC;	
	}
	/**
	 * method to get a CreditCard info that is designed to work with discriminators
	 * the method gets a PaymentInfo and an id and returns a CreditCard with the matching information.
	 * @param id
	 * @param payinf
	 * @return
	 */
	public CreditCard getCreditInfoByCID(int id,PaymentInfo payinf)
	{
		CreditCard credC=new CreditCard();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.credit_card WHERE creditCardID ="+id);
		try{
		st = conn.createStatement();
		rs=st.executeQuery(query);
		if(rs.next())
		{
		credC.setPaymentInfoID(payinf.getPaymentInfoID());
		credC.setCustomerID(payinf.getCustomerID());
		credC.setPrefferedMethod(payinf.getPreferredMethod());
		credC.setCreditCardID(rs.getInt(1));
		credC.setCardNo(rs.getString(2));
		credC.setValidDate(rs.getString(3));
		}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return credC;
		
	}
		/**method to add a newe credit Card info to the Database.
	 * the method receives a CreditCard Class and add its inormation to the table
	 * Addition is done by executing a query.
	 * 
	 */
	public void addNewCreditCard(CreditCard cred) 
	{
			try{
			AddPaymentInfo((PaymentInfo)cred,1);
			String query = new String("INSERT INTO myfuel.credit_card (creditCardID, cardNo,validDate) VALUES ('" + cred.getCreditCardID()+"', '"+cred.getCardNo()+"', '" +cred.getValidDate()+ "');");
			st = conn.createStatement();
			st.executeUpdate(query);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
	}
		/**
	 * method to change a credit card info in the DataBase 
	 * the method receives a CreditCard Class and updates all the fields in the Table
	 * update is done by executing update queries.
	 * @param b
	 * @return
	 */
	public void changeCreditCardInfo(CreditCard c)
	{
		try{
		ChangePaymentInfo((PaymentInfo)c);
		st=conn.createStatement();
		String query = new String("UPDATE myfuel.credit_card  SET cardNo = '"+c.getCardNo() +"' WHERE creditCardID = '"+ c.getCreditCardID() + "';");
		st.executeUpdate(query);
		query = new String("UPDATE myfuel.credit_card  SET validDate = '"+c.getValidDate() +"' WHERE creditCardID = '"+ c.getCreditCardID() + "';");
		st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * method to receive all the credit cards information in the DataBase 
	 * helps to diffirenciate which payment method is a credit card
	 * the method executes a query and returns LinkedList<CreditCard>
	 * returns a LinkedList<CreditCard> of the plans.
	 * @return
	 */
	public LinkedList<CreditCard> getAllCreditCards() 
	{
		
		CreditCard credC;
		ResultSet rs = null;
		LinkedList<CreditCard> cards=new LinkedList<CreditCard>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.credit_card");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				credC=new CreditCard();
				credC=getCreditInfoByCID(rs.getInt(1));
				cards.add(credC);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cards;
		
	}
	/**
	 * method to get a purchase plan from the DataBase by PurchasePlan ID
	 * method receives an id and returns a purchase plan class .
	 * @param id
	 * @return
	 */
	public PurchasePlan getPurchasePlanByID(int id)
	{
		PurchasePlan planToGet= new PurchasePlan();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.purchase_plan WHERE purchasePlanID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					//if(id==rs.getInt(1));
					planToGet.setPurchasePlanID(rs.getInt(1));
					planToGet.setPlanName(rs.getString(2));
					planToGet.setDiscount(rs.getDouble(3));
					planToGet.setAgreementDetails(rs.getString(4));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return planToGet;	 
	}
	/**
	 * 
	 * method to change a purchase plan info in the database
	 * method receives a purchase plan class and updates the fields 
	 * update is done by executing a query
	 * @param newPlan
	 * @throws SQLException
	 */
	public void changePurchasePlan(PurchasePlan newPlan) throws SQLException
	{
		st=conn.createStatement();
		String query = new String("UPDATE myfuel.purchase_plan  SET planName = '"+newPlan.getPlanName() +"' WHERE purchasePlanID = '"+ newPlan.getPurchasePlanID() + "';");
		st.executeUpdate(query);
		query = new String("UPDATE myfuel.purchase_plan  SET discount = '"+newPlan.getDiscount() +"' WHERE purchasePlanID = '"+ newPlan.getPurchasePlanID() + "';");
		st.executeUpdate(query);
		query = new String("UPDATE myfuel.purchase_plan  SET agreementDetails = '"+newPlan.getAgreementDetails() +"' WHERE purchasePlanID = '"+ newPlan.getPurchasePlanID() + "';");
		st.executeUpdate(query);
		
	}
	/**
	 * method to receive all the Purchase Plans information in the database
	 * the method executes a query and storing the information in a LinkedList
	 * method returns LinkedList<PurchasePlan>
	 * @return
	 */
	public LinkedList<PurchasePlan> getAllPlans() 
	{
		
		PurchasePlan plan;
		ResultSet rs = null;
		LinkedList<PurchasePlan> plans=new LinkedList<PurchasePlan>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.purchase_plan");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				plan=new PurchasePlan();
				plan.setPurchasePlanID(rs.getInt(1));
				plan.setPlanName(rs.getString(2));
				plan.setDiscount(rs.getDouble(3));
				plan.setAgreementDetails(rs.getString(4));
				plans.add(plan);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return plans;
		
	}
	/**
	 * method to get a invoice information from the DataBase.
	 * receives a  invoice id and returns the matching results from the DB
	 * the information is saved into a Invoice class and returned for further Use.
	 * @param id
	 * @return
	 */
	public Invoice getInvoiceInfo(int id)
	{
		Invoice currentType= new Invoice();//will be changed to getactive in the future
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.invoice WHERE invoiceID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
								
					currentType.setInvoiceID(rs.getInt(1));
					currentType.setTotalPrice(rs.getDouble(2));
					currentType.setDueDate(rs.getString(3));
					currentType.setCustomerID(rs.getInt(4));
					currentType.setPurchasePlanID(rs.getInt(5));
					currentType.setSubscriptionID(rs.getInt(6));
					currentType.setStatus(rs.getInt(7));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return currentType;
	}
	/**
	 * method to add a New invoice to the DataBase, 
	 * the method gets a Invoice class and by executing a query.
	 * @param rec
	 */
	public void addInvoice(Invoice rec)
	{
				try{
					
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.invoice (invoiceID, totalPrice,dueDate,customerID,purchasePlanID,subscriptionID,status) VALUES ('" + rec.getInvoiceID()+"','"+rec.getTotalPrice()+"','"+rec.getDueDate()+"','"+rec.getCustomerID()+"','"+rec.getPurchasePlanID()+"','"+rec.getSubscriptionID()+"','"+rec.getStatus()+"')");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}

	/**
	 * method to change the invoice to the DataBase
	 * receives a invoice class and change it to the DataBase by executing a query,
	 * @param invo
	 */
	public void changeInvoiceInfo (Invoice invo) throws SQLException{
		try{
				
				st=conn.createStatement();
				String query1 = new String("UPDATE myfuel.invoice SET totalPrice='"+invo.getTotalPrice()+"'WHERE invoiceID='"+invo.getInvoiceID()+"';");
				st.executeUpdate(query1); 
				String query2 = new String("UPDATE myfuel.invoice SET dueDate='"+invo.getDueDate()+"'WHERE invoiceID='"+invo.getInvoiceID()+"';");
				st.executeUpdate(query2); 
				String query3 = new String("UPDATE myfuel.invoice SET customerID='"+invo.getCustomerID()+"'WHERE invoiceID='"+invo.getInvoiceID()+"';");
				st.executeUpdate(query3); 
				String query4 = new String("UPDATE myfuel.invoice SET purchasePlanID='"+invo.getPurchasePlanID()+"'WHERE invoiceID='"+invo.getInvoiceID()+"';");
				st.executeUpdate(query4); 
				String query5 = new String("UPDATE myfuel.invoice SET subscriptionID='"+invo.getSubscriptionID()+"'WHERE invoiceID='"+invo.getInvoiceID()+"';");
				st.executeUpdate(query5); 
				String query6 = new String("UPDATE myfuel.invoice SET status='"+invo.getStatus()+"'WHERE invoiceID='"+invo.getInvoiceID()+"';");
				st.executeUpdate(query6); 
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
		
	}	
	/**
	 * method to get a all invoices information from the DataBase.
	 * returns all the invoices from the DB and their details
	 * the information is saved into a linked list and returned for further Use.
	 * @param id
	 * @return
	 */
	public LinkedList<Invoice> getAllInvoices() {
		Invoice plan;
		ResultSet rs = null;
		LinkedList<Invoice> plans=new LinkedList<Invoice>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.invoice");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				plan=new Invoice();
				plan.setInvoiceID(rs.getInt(1));
				plan.setTotalPrice(rs.getDouble(2));
				plan.setDueDate(rs.getString(3));
				plan.setCustomerID(rs.getInt(4));
				plan.setPurchasePlanID(rs.getInt(5));
				plan.setSubscriptionID(rs.getInt(6));
				plan.setStatus(rs.getInt(7));
				plans.add(plan);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return plans;
		
	}
	/**
	 * method to receive an order information from the Database
	 * the method gets an orderID and returns the matching Order Class with the information.
	 * the get is done by executing a query.
	 * @param id
	 * @return
	 */
	public Order getOrderInfo(int id)
	{
		HouseFuelOrder hfo;
		CarFuelOrder cfo;
		Order currentType= new Order();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.order WHERE orderID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
								
					currentType.setOrderID(rs.getInt(1));
					currentType.setQuantity(rs.getDouble(2));
					currentType.setPrice(rs.getDouble(3));
					currentType.setStatus(rs.getInt(4));
					currentType.setOrderTime(rs.getString(5));
					currentType.setInvoiceID(rs.getInt(6));
					currentType.setPeriodicDiscountID(rs.getInt(7));
					currentType.setFuelTypeID(rs.getInt(8));
					switch(rs.getInt(9))
					{
						case 1:
							cfo=new CarFuelOrder();
							cfo=getCarFuelOrederByCFOID(id,currentType);
							return cfo;
						case 2:
							hfo=new HouseFuelOrder();
							hfo=getHouseFuelOrderByHFOID(id,currentType);
							return hfo;
					}
					
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return currentType;
	}
	/**
	 * method to add a new oreder to the database,the method gets an order class and adds it to the database by executing a query.
	 */
	public void addOrder(Order rec,int disc)
	{
				try{
					
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.order (orderID,quantity,price,status,orderTime,invoiceID,periodicDiscountID,fuelTypeID,discriminator ) VALUES ('" + rec.getOrderID()+"','"+rec.getQuantity()+"','"+rec.getPrice()+"','"+rec.getStatus()+"','"+rec.getOrderTime()+"','"+rec.getInvoiceID()+"','"+rec.getPeriodicDiscountID()+"','"+rec.getFuelTypeID()+"','"+disc+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * method to change a specific orders info in the database,
	 * the method gets an order info with the information and changes the information by executing an update query
	 * @param id
	 * @return
	 */
	public void changeOrderInfo (Order ordr) throws SQLException{
		try{
				
				st=conn.createStatement();
				String query1 = new String("UPDATE myfuel.order SET quantity='"+ordr.getQuantity()+"'WHERE orderID='"+ordr.getOrderID()+"';");
				st.executeUpdate(query1); 
				String query2 = new String("UPDATE myfuel.order SET price='"+ordr.getPrice()+"'WHERE orderID='"+ordr.getOrderID()+"';");
				st.executeUpdate(query2); 
				String query3 = new String("UPDATE myfuel.order SET status='"+ordr.getStatus()+"'WHERE orderID='"+ordr.getOrderID()+"';");
				st.executeUpdate(query3); 
				String query4 = new String("UPDATE myfuel.order SET orderTime='"+ordr.getOrderTime()+"'WHERE orderID='"+ordr.getOrderID()+"';");
				st.executeUpdate(query4); 
				String query5 = new String("UPDATE myfuel.order SET invoiceID='"+ordr.getInvoiceID()+"'WHERE orderID='"+ordr.getOrderID()+"';");
				st.executeUpdate(query5); 
				String query6 = new String("UPDATE myfuel.order SET periodicDiscountID='"+ordr.getPeriodicDiscountID()+"'WHERE orderID='"+ordr.getOrderID()+"';");
				st.executeUpdate(query6); 
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	/**
	 * method to get all the orders in the Database, the method stores the orders information in a Linked list and 
	 * returns that list for further use.
	 * @return
	 */
public LinkedList<Order> getAllOrders(){
		
		Order plan;
		ResultSet rs = null;
		LinkedList<Order> plans=new LinkedList<Order>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.order");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				plan = getOrderInfo (rs.getInt(1));
				plans.add(plan);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return plans;
		
	}
	/**
	 * a method to get a CarFuelOrder from the DataBase
	 * the method gets an id and pulls the matching line information 
	 * the information is stored in a CarFuelOrder class and returned
	 * @param id
	 * @return
	 */
	public CarFuelOrder getCarFuelOrderInfo(int id){
		Order ordr = new Order();
		CarFuelOrder cfo =new CarFuelOrder();
		
		ordr = getOrderInfo(id);
		
		cfo.setQuantity(ordr.getQuantity());
		cfo.setPrice(ordr.getPrice());
		cfo.setStatus(ordr.getStatus());
		cfo.setOrderTime(ordr.getOrderTime());
		cfo.setInvoiceID(ordr.getInvoiceID());
		cfo.setFuelTypeID(ordr.getFuelTypeID());
		cfo.setPeriodicDiscountID(ordr.getPeriodicDiscountID());
		
		
		
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.car_fuel_order WHERE carFuelOrderID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					cfo.setCarID(rs.getInt(1));
					cfo.setDriverName(rs.getString(2));
					cfo.setCarID(rs.getInt(3));
					cfo.setStationID(rs.getInt(4));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return cfo;		
	}
	
	/**
	 * a method to get a Carfuel order that works with the discriminators. 
	 * gets the carfuel order and returns it to the getOrder func
	 */
	public CarFuelOrder getCarFuelOrederByCFOID(int id,Order ordr)
	{
		ResultSet rs=null;
		CarFuelOrder cfo=new CarFuelOrder();
		cfo.setOrderID(ordr.getOrderID());	
		cfo.setQuantity(ordr.getQuantity());
		cfo.setPrice(ordr.getPrice());
		cfo.setStatus(ordr.getStatus());
		cfo.setOrderTime(ordr.getOrderTime());
		cfo.setInvoiceID(ordr.getInvoiceID());
		cfo.setFuelTypeID(ordr.getFuelTypeID());
		cfo.setPeriodicDiscountID(ordr.getPeriodicDiscountID());
		String query = new String("SELECT * FROM myfuel.car_fuel_order WHERE carFuelOrderID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					cfo.setCarID(rs.getInt(1));
					cfo.setDriverName(rs.getString(2));
					cfo.setCarID(rs.getInt(3));
					cfo.setStationID(rs.getInt(4));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return cfo;	
		
		
	}
	/**
	 * a method to add a CarFuelOrder to the DataBase
	 * the method gets a CarFuelOrder class and adds it to the DataBase by executing a query.
	 * @param b
	 * @return
	 */
	public void addCarFuelOrder(CarFuelOrder rec){
				try{
					addOrder((Order)rec,1); 
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.car_fuel_Order (carFuelOrderID, driverName,carID,stationID) VALUES ('" + rec.getCarFuelOrderID()+"','"+rec.getDriverName()+"','"+rec.getCarID()+"','"+rec.getStationID()+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * a method to change a CarFuelOrder in the DataBase 
	 * the method gets a CarFuelOrder Class and updates the fields by executing query.
	 * @param car_ordr
	 * @throws SQLException
	 */
	public void changeCarFuelOrder (CarFuelOrder car_ordr) throws SQLException{
		try{
				
				st=conn.createStatement();
				String query1 = new String("UPDATE myfuel.carFuelOrder SET driverName='"+car_ordr.getDriverName()+"'WHERE carFuelOrderID='"+car_ordr.getCarFuelOrderID()+"';");
				st.executeUpdate(query1); 
				String query2 = new String("UPDATE myfuel.carFuelOrder SET carID='"+car_ordr.getCarID()+"'WHERE carFuelOrderID='"+car_ordr.getCarFuelOrderID()+"';");
				st.executeUpdate(query2); 

			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	/**
	 * method to get all the CarFuelOrders in the DataBase 
	 * the method pulls all the oreder and stores ea order in a LinkedList
	 * the method returns LinkedList<CarFuelOrder>
	 */
	public LinkedList<CarFuelOrder> getAllCarFuelOrders() {
		
		Order ordr;
		CarFuelOrder cfordr;
		ResultSet rs = null;
		LinkedList<CarFuelOrder> cfordrs=new LinkedList<CarFuelOrder>();
		
		
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.car_fuel_order");
			rs=st.executeQuery(query);
			
			while(rs.next())
			{
				ordr=new Order();
				cfordr=new CarFuelOrder();
				cfordr.setOrderID(rs.getInt(1));
				ordr=getOrderInfo(cfordr.getOrderID());
				
				cfordr.setQuantity(ordr.getQuantity());
				cfordr.setPrice(ordr.getPrice());
				cfordr.setStatus(ordr.getStatus());
				cfordr.setOrderTime(ordr.getOrderTime());
				cfordr.setInvoiceID(ordr.getInvoiceID());
				cfordr.setPeriodicDiscountID(ordr.getPeriodicDiscountID());
				cfordr.setDriverName(rs.getString(2));
				cfordr.setCarID(rs.getInt(3));
				
				cfordrs.add(cfordr);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cfordrs;
		
	}
	/**
	 * method to get a housefuel order info from the Database
	 * the method gets a housefueloreder id and stores the matching line in the Database in a HouseFuelOrder class.
	 * @param id
	 * @return
	 */
	public HouseFuelOrder getHouseFuelOrderInfo(int id){
		Order ordr = new Order();
		HouseFuelOrder hfo =new HouseFuelOrder();
		
		ordr = getOrderInfo(id);
		
		hfo.setOrderID (ordr.getOrderID());
		hfo.setQuantity(ordr.getQuantity());
		hfo.setPrice(ordr.getPrice());
		hfo.setStatus(ordr.getStatus());
		hfo.setOrderTime(ordr.getOrderTime());
		hfo.setInvoiceID(ordr.getInvoiceID());
		hfo.setFuelTypeID(ordr.getFuelTypeID());
		hfo.setPeriodicDiscountID(ordr.getPeriodicDiscountID());
		
		
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.house_fuel_order WHERE houseFuelOrderID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					hfo.setOrderID(rs.getInt(1));
					hfo.setUrgent((rs.getBoolean(2)));
					hfo.setDeliveryTime(rs.getString(3));
					hfo.setHouseFuelInfoID(rs.getInt(4));
					hfo.setHouseOwnerID(rs.getInt(5));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return hfo;		
	}
	/**
	 * method to get a CarFuelOrder that works with the discriminators
	 * gets an id and a Order Class and returns the CarFuelOrder to GetOrder;
	 */
	public HouseFuelOrder getHouseFuelOrderByHFOID(int id,Order ordr)
	{
		HouseFuelOrder hfo=new HouseFuelOrder();
		hfo.setOrderID (ordr.getOrderID());
		hfo.setQuantity(ordr.getQuantity());
		hfo.setPrice(ordr.getPrice());
		hfo.setStatus(ordr.getStatus());
		hfo.setOrderTime(ordr.getOrderTime());
		hfo.setInvoiceID(ordr.getInvoiceID());
		hfo.setFuelTypeID(ordr.getFuelTypeID());
		hfo.setPeriodicDiscountID(ordr.getPeriodicDiscountID());
		
		
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.house_fuel_order WHERE houseFuelOrderID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					hfo.setOrderID(rs.getInt(1));
					hfo.setUrgent((rs.getBoolean(2)));
					hfo.setDeliveryTime(rs.getString(3));
					hfo.setHouseFuelInfoID(rs.getInt(4));
					hfo.setHouseOwnerID(rs.getInt(5));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return hfo;	
	}
	/**
	 * a method to add a HouseFuelOrder to the DataBase 
	 * the method gets a HouseFuelOrder class and adding it to the DataBase By executing a query
	 * @param b
	 * @return
	 */
	public void addHouseFuelOrder(HouseFuelOrder rec){
				try{
					addOrder((Order)rec,2);
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.house_fuel_order (houseFuelOrderID, urgent, deliveryTime, houseFuelInfoID, houseOwnerID) VALUES ('" + rec.getOrderID()+"',b'"+boolToInt(rec.isUrgent())+"','"+rec.getDeliveryTime()+"','"+rec.getHouseFuelInfoID()+"','"+rec.getHouseOwnerID()+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * a method to change a specific HouseFuelOrder in the Database 
	 * the method get a HouseFuelOrder Class and updates the fields in the Database accordingly.
	 * update is done by executinga query
	 * @param car_ordr
	 * @throws SQLException
	 */
	public void changeHouseFuelOrder (HouseFuelOrder house_ordr) throws SQLException{
		try{
				changeOrderInfo((Order)house_ordr);
				st=conn.createStatement();
				String query1 = new String("UPDATE myfuel.house_fuel_order SET urgent=b'"+boolToInt(house_ordr.isUrgent())+"'WHERE houseFuelOrderID='"+house_ordr.getOrderID()+"';");
				st.executeUpdate(query1); 
				String query2 = new String("UPDATE myfuel.house_fuel_order SET deliveryTime='"+house_ordr.getDeliveryTime()+"'WHERE houseFuelOrderID='"+house_ordr.getOrderID()+"';");
				st.executeUpdate(query2);
				String query3 = new String("UPDATE myfuel.house_fuel_order SET houseFuelInfoID='"+house_ordr.getHouseFuelInfoID()+"'WHERE houseFuelOrderID='"+house_ordr.getOrderID()+"';");
				st.executeUpdate(query3); 
				String query4 = new String("UPDATE myfuel.house_fuel_order SET houseOwnerID='"+house_ordr.getHouseOwnerID()+"'WHERE houseFuelOrderID='"+house_ordr.getOrderID()+"';");
				st.executeUpdate(query4); 

			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	/**
	 * method to get all HouseFuelOrders from the Database 
	 * each order is saved in a LinkedList and the method returns a LinkedList<HouseFuelOrder>
	 */
	public LinkedList<HouseFuelOrder> getAllHouseFuelOrders() {
		
		Order ordr;
		HouseFuelOrder hfordr;
		ResultSet rs = null;
		LinkedList<HouseFuelOrder> hfordrs=new LinkedList<HouseFuelOrder>();
		
		
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.house_fuel_order");
			rs=st.executeQuery(query);
			
			while(rs.next())
			{
				ordr=new Order();
				hfordr=new HouseFuelOrder();
				hfordr.setOrderID(rs.getInt(1));
				ordr=getOrderInfo(hfordr.getOrderID());
				
				hfordr.setQuantity(ordr.getQuantity());
				hfordr.setPrice(ordr.getPrice());
				hfordr.setStatus(ordr.getStatus());
				hfordr.setOrderTime(ordr.getOrderTime());
				hfordr.setInvoiceID(ordr.getInvoiceID());
				hfordr.setPeriodicDiscountID(ordr.getPeriodicDiscountID());
				hfordr.setUrgent(rs.getBoolean(2));
				hfordr.setDeliveryTime(rs.getString(3));
				hfordr.setHouseFuelInfoID(rs.getInt(4));
				hfordr.setHouseOwnerID(rs.getInt(5));
				
				hfordrs.add(hfordr);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hfordrs;
		
	}
	/**
	 * method to get a customer info from the database 
	 * customer extends user therefore first get the user info and then get the rest from the customer 
	 * method receives id and returns a Customer Class with the required infrormation 
	 * @param b
	 * @return
	 */
	public Customer getCustomerInfoByID(int id){
		User usr=new User();
		Customer cusToGet= new Customer();//will be changed to getactive in the future
		usr=getUserByUid(id);
		cusToGet.setUserID(usr.getUserID());
		cusToGet.setUserName(usr.getUserName());
		cusToGet.setPhoneNo(usr.getPhoneNo());
		cusToGet.setFirstName(usr.getFirstName());;
		cusToGet.setLastName(usr.getLastName());
		cusToGet.seteMail(usr.geteMail());
		cusToGet.setUserTypeID(usr.getUserTypeID());
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.customer WHERE customerID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					//if(id==rs.getInt(1));
					cusToGet.setAddress(rs.getString(2));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return cusToGet;	
		
		
	}
	/**
	 * method to get a customer by its id and designed to work with the discriminators
	 * the method get a user class and an id and returns a Customer class.
	 */
	public Customer getCustomerBycID(int id,User usr)
	{
		CarCustomer c;
		HouseOwner hw;
		Customer cusToGet= new Customer();//will be changed to getactive in the future
		cusToGet.setUserID(usr.getUserID());
		cusToGet.setUserName(usr.getUserName());
		cusToGet.setPhoneNo(usr.getPhoneNo());
		cusToGet.setFirstName(usr.getFirstName());;
		cusToGet.setLastName(usr.getLastName());
		cusToGet.seteMail(usr.geteMail());
		cusToGet.setUserTypeID(usr.getUserTypeID());
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.customer WHERE customerID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					//if(id==rs.getInt(1));
					cusToGet.setAddress(rs.getString(2));
					switch(rs.getInt(3))
					{
					case 1:
						c=new CarCustomer();
						c=getCarCustomerByCCID(id,cusToGet);
						return c;
					case 2:
						hw=new HouseOwner();
						hw=getHouseOwnerByHWID(id,cusToGet);
						return hw;
					}
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return cusToGet;	
		
	}
	/**
	 * method to add a new customer to the Database.
	 * the method receives a Customer Class and adds it to the table
	 * updating is done by executing a query;
	 * @param c
	 * @throws SQLException
	 */
	public void addNewCustomer(Customer c,int disc) 
	{
		try{
		insertNewUser((User)c,1);
		String query = new String("INSERT INTO myfuel.customer (customerID,address,discriminator) VALUES ('" + c.getUserID()+"', '"+ c.getAddress()+"', '"+disc+ "');");
		st = conn.createStatement();
		st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * method to change a customer information in the DataBase, 
	 * the method receives a Customer Class and updates the fields
	 * update is done by executing a query.
	 * @param b
	 * @return
	 * @throws SQLException 
	 */
	public void ChangeCustomerInfo(Customer cusToChange) throws SQLException{
		changeUserByUid((User)cusToChange);
		st=conn.createStatement();
		String query = new String("UPDATE myfuel.customer  SET address = '"+cusToChange.getAddress() +"' WHERE customerID = '"+ cusToChange.getUserID() + "';");
		st.executeUpdate(query);
		
	}
	/**
	 * method to delete a Customer from the DataBase 
	 * method receives a customerID and deletes it from the database of user and customer
	 * 
	 * @param b
	 * @return
	 */
	public void delCustomerByID(int id) throws SQLException
	{
		st=conn.createStatement();
		String query = new String("DELETE FROM myfuel.customer"+" WHERE customerID = "+id+";");
		st.executeUpdate(query);
		delUserByUid(id);	
	}
	/**
	 * method to get all customers in the DataBase,
	 * fetching by executing a query and returning LinkedList<Customer>
	 * @param b
	 * @return
	 */
	public LinkedList<Customer> getAllCustomers() 
	{
		User usr=new User();
		Customer cus;
		ResultSet rs = null;
		LinkedList<Customer> customers=new LinkedList<Customer>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.customer");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				cus= new Customer();
				cus.setUserID(rs.getInt(1));
				usr=getUserByUid(cus.getUserID());
				cus.setUserID(usr.getUserID());
				cus.setUserName(usr.getUserName());
				cus.setPhoneNo(usr.getPhoneNo());
				cus.setFirstName(usr.getFirstName());
				cus.setLastName(usr.getLastName());
				cus.seteMail(usr.geteMail());
				cus.setUserTypeID(usr.getUserTypeID());
				cus.setAddress(rs.getString(2));
				customers.add(cus);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return customers;
		
	}
	/**
	 * method to get a car customer information from the database 
	 * the method gets all the car customer's information and returns a CarCustomer  
	 * @param id
	 * @return
	 */
	public CarCustomer getCarCusByCcID(int id)
	{
		Customer cus;
		CarCustomer carCus=new CarCustomer();
		cus=getCustomerInfoByID(id);
		carCus.setUserID(cus.getUserID());
		carCus.setUserName(cus.getUserName());
		carCus.setPhoneNo(cus.getPhoneNo());
		carCus.setFirstName(cus.getFirstName());;
		carCus.setLastName(cus.getLastName());
		carCus.seteMail(cus.geteMail());
		carCus.setUserTypeID(cus.getUserTypeID());
		carCus.setAddress(cus.getAddress());
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.car_customer WHERE carCustomerID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					
					carCus.setPurchasePlanID(rs.getInt(2));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return carCus;
	}
	/**
	 * method to get a CarCustomer Info from tht DataBase by id and designed to to work with discriminators
	 * the method gets a Customer Class and an id and reuturns a CarCustomer Class with the required information.
	 * @param id
	 * @param cus
	 * @return
	 */
	public CarCustomer getCarCustomerByCCID(int id,Customer cus)
	{
		CarCustomer carCus=new CarCustomer();
		
		carCus.setUserID(cus.getUserID());
		carCus.setUserName(cus.getUserName());
		carCus.setPhoneNo(cus.getPhoneNo());
		carCus.setFirstName(cus.getFirstName());;
		carCus.setLastName(cus.getLastName());
		carCus.seteMail(cus.geteMail());
		carCus.setUserTypeID(cus.getUserTypeID());
		carCus.setAddress(cus.getAddress());
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.car_customer WHERE carCustomerID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					
					carCus.setPurchasePlanID(rs.getInt(2));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return carCus;
		
	}
	/**
	 * method to add a new car customer into the Database 
	 * the method executes a query that adds the customer.
	 * method receives a CarCustomer Class
	 * 
	 * @param c
	 * @throws SQLException
	 */
	public void AddCarCustomer(CarCustomer c)
	{
		try{
		addNewCustomer((Customer)c,1);
		String query = new String("INSERT INTO myfuel.car_customer (carCustomerID,purchasePlanID) VALUES ('" + c.getUserID()+"', '" +c.getPurchasePlanID()+ "');");
		st = conn.createStatement();
		st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * method to change a CarCustomer in the DataBase 
	 * method receives a CarCustomer Class and adds the CarCustomer by executing a query.
	 * 
	 * @param b
	 * @return
	 * @throws SQLException 
	 */
	public void changeCarCustomerInfo(CarCustomer c) throws SQLException
	{
		changeUserByUid((User)c);
		ChangeCustomerInfo((Customer)c);
		st=conn.createStatement();
		String query = new String("UPDATE myfuel.car_customer  SET purchasePlanID = '"+c.getPurchasePlanID() +"' WHERE carCustomerID = '"+ c.getUserID() + "';");
		st.executeUpdate(query);
	}
	/**
	 * method to get all the CarCustomers in the DataBase.
	 * the method executes a query and stoers everything in a LinkedList
	 * method returns LinkedList<CarCustomer>
	 * @return
	 */
	public LinkedList<CarCustomer> getAllCarCuss() 
	{
		Customer cus;
		CarCustomer customer;
		ResultSet rs = null;
		LinkedList<CarCustomer> customers =new LinkedList<CarCustomer>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.car_customer");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				customer=new CarCustomer();
				customer.setUserID(rs.getInt(1));
				cus=getCustomerInfoByID(customer.getUserID());
				customer.setUserName(cus.getUserName());
				customer.setPhoneNo(cus.getPhoneNo());
				customer.setFirstName(cus.getFirstName());;
				customer.setLastName(cus.getLastName());
				customer.seteMail(cus.geteMail());
				customer.setUserTypeID(cus.getUserTypeID());
				customer.setAddress(cus.getAddress());
				customer.setCustomerID(cus.getUserID());
				customer.setPurchasePlanID(rs.getInt(2));
				customers.add(customer);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return customers;
		
	}
	/**
	 * method to get a HouseOwner information from the DataBase
	 * the function executes a query and returns and saves the data in a houseOwner Clas 
	 * 
	 * @param b
	 * @return
	 */
	public HouseOwner getHouseOwnerByID(int id)
	{
		Customer cus;
		HouseOwner houseCus=new HouseOwner();
		cus=getCustomerInfoByID(id);
		houseCus.setUserID(cus.getUserID());
		houseCus.setUserName(cus.getUserName());
		houseCus.setPhoneNo(cus.getPhoneNo());
		houseCus.setFirstName(cus.getFirstName());;
		houseCus.setLastName(cus.getLastName());
		houseCus.seteMail(cus.geteMail());
		houseCus.setUserTypeID(cus.getUserTypeID());
		houseCus.setAddress(cus.getAddress());
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.house_owner WHERE houseOwnerID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					
					houseCus.setHouseOwnerID(rs.getInt(1));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return houseCus;
		
	}
	/**
	 * method to get a HouseOwner information and designed to work with discriminators.
	 * the method gets a Customer class and an id and reutrns a HouseOwner class with the relevant information
	 * @param id
	 * @param cus
	 * @return
	 */
	public HouseOwner getHouseOwnerByHWID(int id,Customer cus)
	{
		HouseOwner houseCus=new HouseOwner();
		houseCus.setUserID(cus.getUserID());
		houseCus.setUserName(cus.getUserName());
		houseCus.setPhoneNo(cus.getPhoneNo());
		houseCus.setFirstName(cus.getFirstName());;
		houseCus.setLastName(cus.getLastName());
		houseCus.seteMail(cus.geteMail());
		houseCus.setUserTypeID(cus.getUserTypeID());
		houseCus.setAddress(cus.getAddress());
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.house_owner WHERE houseOwnerID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					
					houseCus.setHouseOwnerID(rs.getInt(1));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return houseCus;
		
	}
	/**method to add a house owner into the DataBase Table
	 * the method receives a HouseOwner Class and adding it to the Database
	 * @throws SQLException  
	 * 
	 */
	public void addHouseOwner(HouseOwner hou) 
	{
		try{
		addNewCustomer((Customer)hou,2);
		String query = new String("INSERT INTO myfuel.house_owner (houseOwnerID) VALUES ('" + hou.getUserID()+ "');");
		st = conn.createStatement();
		st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * method to change a HouseOwner Information in the Database.
	 * method rececives a HouseOwner class and updates the fields corrispondingly
	 * 
	 * @param b
	 * @return
	 * @throws SQLException 
	 */
	public void changeHouseOwnerInfo(HouseOwner hou) throws SQLException
	{
		//changeUserByUid((User)hou);
		ChangeCustomerInfo((Customer)hou);
	}
	/**
	 * method to get all the houseOwners in the DataBase
	 * method executes a query and saves the information in a LinkedList
	 * the method returns LinkedList<HouseOwner>
	 * @param b
	 * @return
	 */
	public LinkedList<HouseOwner> getAllHouseOwners() 
	{
		Customer cus;
		HouseOwner houw;
		ResultSet rs = null;
		LinkedList<HouseOwner> owners =new LinkedList<HouseOwner>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.house_owner");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				houw=new HouseOwner();
				houw.setUserID(rs.getInt(1));
				cus=getCustomerInfoByID(houw.getUserID());
				houw.setUserName(cus.getUserName());
				houw.setPhoneNo(cus.getPhoneNo());
				houw.setFirstName(cus.getFirstName());;
				houw.setLastName(cus.getLastName());
				houw.seteMail(cus.geteMail());
				houw.setUserTypeID(cus.getUserTypeID());
				houw.setAddress(cus.getAddress());
				houw.setCustomerID(cus.getUserID());
				owners.add(houw);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return owners;
	}
	/**
	 * method to get a Receipt Data in from the Database 
	 * the function executes a Query and returns the information ias to luk a clown 
	 * 
	 */
	public Receipt getReceiptInfoByID(int id)
	{
		Receipt rec= new Receipt();//will be changed to getactive in the future
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.receipt WHERE receiptID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					rec.setReceiptID(rs.getInt(1));
					rec.setPaymentDate(rs.getString(2));
					rec.setInvoiceID(rs.getInt(3));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rec;
	}
	/**
	 * method to add a New Receipt to the DataBase
	 * the method receives a Receipt Class and adding it to the table 
	 * the addition is done by executing a query
	 * 
	 * @param b
	 * @return
	 */
	public void addNewReceipt(Receipt rec)
	{
				try{
					
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.receipt (receiptID, paymentDate,invoiceID) VALUES ('" + rec.getReceiptID()+"','"+rec.getPaymentDate()+"','"+rec.getInvoiceID()+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * method to change a Receipt information in the DataBase 
	 * method receives a Receipt Class and updates the Fields by executing a query
	 *
	 * @param b
	 * @return
	 * @throws SQLException 
	 */
	public void ChangeReceiptInfo(Receipt rec) throws SQLException
	{
		st=conn.createStatement();
		String query1 = new String("UPDATE myfuel.receipt SET paymentDate='"+rec.getPaymentDate()+"'WHERE invoiceID='"+rec.getReceiptID()+"';");
		st.executeUpdate(query1);
		st=conn.createStatement();
		String query2 = new String("UPDATE myfuel.receipt SET invoiceID='"+rec.getInvoiceID()+"'WHERE invoiceID='"+rec.getReceiptID()+"';");
		st.executeUpdate(query2);	
	}
	/**
	 * method to get all the Receipts in the Database 
	 * the method stores the data in a LinkedList
	 * the method returns LinkedList<Receipt>
	 * 
	 * @param b
	 * @return
	 */
	public LinkedList<Receipt> getAllReceipts() 
	{
		
		Receipt rec;
		ResultSet rs = null;
		LinkedList<Receipt> receipts =new LinkedList<Receipt>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.receipt");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				rec=new Receipt();
				rec.setReceiptID(rs.getInt(1));
				rec.setPaymentDate(rs.getString(2));
				rec.setInvoiceID(rs.getInt(3));
				receipts.add(rec);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return receipts;
		
	}
	/**
	 * method to get a FuelReplenish information from the DataBase.
	 * the method receives an id of a FuelReplenish and returns the replenish required
	 * the getting is done by executing a query 
	 * @param id
	 * @return
	 */
	public FuelReplenish getReplenishByID(int id)
	{
		FuelReplenish repl= new FuelReplenish();//will be changed to getactive in the future
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.fuel_replanish WHERE FuelReplanishID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					repl.setFuelReplenishID(rs.getInt(1));
					repl.setAmount(rs.getInt(2));
					repl.setStatus(rs.getInt(3));
					repl.setDeliveryDate(rs.getString(4));
					repl.setFuelTypeID(rs.getInt(5));
					repl.setStationID(rs.getInt(6));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return repl;
		
	}
	/**
	 * method to add a FuelReplenish to the DataBase 
	 * the method receives a FuelReplenish Class nad adds it to the DataBase.
	 * 
	 * @param repl
	 */
	public void addFuelReplenish(FuelReplenish repl)
	{
		//ResultSet rs = null;
				try{
					
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.fuel_replanish (FuelReplanishID,amount,status,deliveryDate,fuelTypeID,stationID) VALUES ('" + repl.getFuelReplenishID()+"','"+repl.getAmount()+"','"+repl.getStatus()+"','"+repl.getDeliveryDate()+"','"+repl.getFuelTypeID()+"','"+repl.getStationID()+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
		
		
	}
	/**
	 * method to change a FuelReplenish 
	 * the method receives a FuelReplenish Class and updates its fields 
	 * update is done by executing a query
	 * @param repl
	 */
	public void changeFuelReplenish(FuelReplenish repl) throws SQLException
	{
		st=conn.createStatement();
		String query1 = new String("UPDATE myfuel.fuel_replanish SET amount='"+repl.getAmount()+"'WHERE FuelReplanishID='"+repl.getFuelReplenishID()+"';");
		st.executeUpdate(query1);
		String query2 = new String("UPDATE myfuel.fuel_replanish SET status='"+repl.getStatus()+"'WHERE FuelReplanishID='"+repl.getFuelReplenishID()+"';");
		st.executeUpdate(query2);
		String query3 = new String("UPDATE myfuel.fuel_replanish SET deliveryDate='"+repl.getDeliveryDate()+"'WHERE FuelReplanishID='"+repl.getFuelReplenishID()+"';");
		st.executeUpdate(query3);
		String query4 = new String("UPDATE myfuel.fuel_replanish SET fuelTypeID='"+repl.getFuelTypeID()+"'WHERE FuelReplanishID='"+repl.getFuelReplenishID()+"';");
		st.executeUpdate(query4);
		String query5 = new String("UPDATE myfuel.fuel_replanish SET stationID='"+repl.getStationID()+"'WHERE FuelReplanishID='"+repl.getFuelReplenishID()+"';");
		st.executeUpdate(query5);
	}
	/**
	 * A method that returns a Linked List of all the Fuel Replenishes in the Datbase 
	 * the method executing a query and stores the information in a linked list
	 * returns LinkedList<FuelReplenish>
	 * @param b
	 * @return
	 */
	public LinkedList<FuelReplenish> getAllReplenishes() 
	{
		
		FuelReplenish repl;
		ResultSet rs = null;
		LinkedList<FuelReplenish> replanishes =new LinkedList<FuelReplenish>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.fuel_replanish");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				repl=new FuelReplenish();
				repl.setFuelReplenishID(rs.getInt(1));
				repl.setAmount(rs.getInt(2));
				repl.setStatus(rs.getInt(3));
				repl.setDeliveryDate(rs.getString(4));
				repl.setFuelTypeID(rs.getInt(5));
				repl.setStationID(rs.getInt(6));
				replanishes.add(repl);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return replanishes;
		
	}
	/**
	 * A method to get a PaymentInfo From a Database Table,
	 * the method executes a query and stores the information in a class
	 * returns a PaymentInfo Class
	 * @param b
	 * @return
	 */
	public PaymentInfo getPaymentInfoByID(int id)
	{
		Monthly m;
		CreditCard c;
		Cash cash;
		PaymentInfo payinf= new PaymentInfo();//will be changed to getactive in the future
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.payment_info WHERE paymentInfoID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					payinf.setPaymentInfoID(rs.getInt(1));
					payinf.setCustomerID(rs.getInt(2));
					payinf.setPrefferedMethod(rs.getInt(3));
					switch(rs.getInt(4))
					{
					case 0:
						return payinf;
					case 1:
						c=new CreditCard();
						c=getCreditInfoByCID(payinf.getPaymentInfoID(),payinf);
						return c;
					case 2:
						cash=new Cash();
						cash=getCashInfoByCashID(payinf.getPaymentInfoID(),payinf);
						return cash;
					case 3:
						m=new Monthly();
						m=getMonthlyInfoByMID(payinf.getPaymentInfoID(),payinf);
						return m;
						
					}
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return payinf;
		
	}
	/**A method to add a new PaymentInfo to a DataBase Table
	 * the method gets a PaymentInfo Class and adding all its field to the able 
	 * by executing a query
	 * @param b
	 * @return
	 */
	public void AddPaymentInfo(PaymentInfo payinf,int disc)
	{
		//ResultSet rs = null;
		try{
			
			st=conn.createStatement();
			String query = new String("INSERT INTO myfuel.payment_info (paymentInfoID,customerID,preferredPaymentMethod,discriminator) VALUES ('" + payinf.getPaymentInfoID()+"','"+payinf.getCustomerID()+"','"+payinf.getPreferredMethod()+"','"+disc+"');");
			st.executeUpdate(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}

	}
	/**
	 * A method to Change A PaymentInfo method in the DataBase 
	 * mothod gets a PaymentInfo Class and updates the line fields by executing a query
	 * @param b
	 * @return
	 * @throws SQLException 
	 */
	public void ChangePaymentInfo(PaymentInfo payinf) throws SQLException
	{
		
		st=conn.createStatement();
		String query1 = new String("UPDATE myfuel.payment_info SET customerID='"+payinf.getCustomerID()+"'WHERE paymentInfoID='"+payinf.getPaymentInfoID()+"';");
		st.executeUpdate(query1);
		st=conn.createStatement();
		String query2 = new String("UPDATE myfuel.payment_info SET preferredPaymentMethod='"+payinf.getPreferredMethod()+"'WHERE paymentInfoID='"+payinf.getPaymentInfoID()+"';");
		st.executeUpdate(query2);
		
	}
	/**
	 * A Method to get all the Database PaymentInfos 
	 * the method stores the information in a LinkedList and returns LinkedList<PaymentInfo>
	 * @param b
	 * @return
	 */
	public LinkedList<PaymentInfo> getAllPayInfos() 
	{
		
		PaymentInfo payinf;
		ResultSet rs = null;
		LinkedList<PaymentInfo> infos =new LinkedList<PaymentInfo>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.payment_info");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				payinf=new PaymentInfo();
				payinf=getPaymentInfoByID(rs.getInt(1));
				infos.add(payinf);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return infos;
		
	}
	/**a method to get a Cash Payment method information from the DataBase
	 * method executes a query and stores the information in a Cash Class and returns it
	 */
	public Cash getCashInfoByCID(int id)
	{
		PaymentInfo payinf=new PaymentInfo();
		Cash c= new Cash();//will be changed to getactive in the future
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.cash WHERE cashID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					payinf=getPaymentInfoByID(rs.getInt(1));
					c.setPaymentInfoID(payinf.getPaymentInfoID());
					c.setCustomerID(payinf.getCustomerID());
					c.setPrefferedMethod(payinf.getPreferredMethod());
					c.setCashID(rs.getInt(1));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return c;	
		
	}
	/**
	 * 
	 * 
	 */
	public Cash getCashInfoByCashID(int id,PaymentInfo payinf)
	{
		ResultSet rs = null;
		Cash c=new Cash();
		String query = new String("SELECT * FROM myfuel.cash WHERE cashID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					c.setPaymentInfoID(payinf.getPaymentInfoID());
					c.setCustomerID(payinf.getCustomerID());
					c.setPrefferedMethod(payinf.getPreferredMethod());
					c.setCashID(rs.getInt(1));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return c;
	
		
	}
	/**
	 * A method to add a new Cash Info to the DataBase.
	 * get a Cash Class and by executing a query adds it to a new line.
	 * @param b
	 * @return
	 */
	public void addnewCash(Cash c)
	{
		try{
		AddPaymentInfo((PaymentInfo)c,2);
		String query = new String("INSERT INTO myfuel.cash (cashID) VALUES ('" + c.getPaymentInfoID()+"');");
		st = conn.createStatement();
		st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * A method to change an existing cash info in the DataBase
	 * The Method get a Cash Class and by executing a query Updates the matching line
	 * @param b
	 * @return
	 */
	public void changeCashInfo(Cash c)
	{
		try {
			ChangePaymentInfo((PaymentInfo)c);
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
	}
	/**
	 * A method to that Returns all the Cash info in the DataBase 
	 * Method Executing a Query and returns a LinkedList<Cash>
	 * @param b
	 * @return
	 */
	public LinkedList<Cash> getAllCashInfo() 
	{
		
		Cash c;
		ResultSet rs = null;
		LinkedList<Cash> cashs=new LinkedList<Cash>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.cash");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				c=new Cash();
				c=getCashInfoByCID(rs.getInt(1));
				cashs.add(c);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cashs;
		
	}
	/**
	 * a method to receive an information on a monthly fuel
	 * by executing a query the method gets the information and stores it in a Mpnthly Class
	 * @param b
	 * @return
	 */
	public Monthly getMonthlyinfoByID(int id)
	{
		PaymentInfo payinf=new PaymentInfo();
		Monthly mo= new Monthly();//will be changed to getactive in the future
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.monthly WHERE monthlyID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					payinf=getPaymentInfoByID(rs.getInt(1));
					mo.setPaymentInfoID(payinf.getPaymentInfoID());
					mo.setCustomerID(payinf.getCustomerID());
					mo.setPrefferedMethod(payinf.getPreferredMethod());
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return mo;
	}
	/**
	 * 
	 * 
	 * @param id
	 * @param payinf
	 * @return
	 */
	public Monthly getMonthlyInfoByMID(int id,PaymentInfo payinf)
	{
		Monthly mo=new Monthly();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.monthly WHERE monthlyID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					mo.setPaymentInfoID(payinf.getPaymentInfoID());
					mo.setCustomerID(payinf.getCustomerID());
					mo.setPrefferedMethod(payinf.getPreferredMethod());
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return mo;
	}
	/**
	 * A method to Add a new monthly info to the Database 
	 * The method receives a Monthly Class and by executing a query adding it to the DB
	 * @param b
	 * @return
	 */
	public void addMonthly(Monthly mo)
	{
		try{
		AddPaymentInfo((PaymentInfo)mo,3);
		String query = new String("INSERT INTO myfuel.monthly (monthlyID) VALUES ('" + mo.getPaymentInfoID()+ "');");
		st = conn.createStatement();
		st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * A method to change an an existing monthly Info in the Database 
	 * the method get a Monthly Class and by executing a query updates the line fields
	 * @param b
	 * @return
	 */
	public void ChangeMonthlyinfo(Monthly mo)
	{
		try {
			ChangePaymentInfo((PaymentInfo)mo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * A method to get all the monthly info that is in the DataBase 
	 * The method stores the information in a LinkedList and returns it for further use
	 * @param b
	 * @return
	 */
	public LinkedList<Monthly> getAllMonthlys() 
	{
		
		Monthly mo;
		ResultSet rs = null;
		LinkedList<Monthly> monthlys=new LinkedList<Monthly>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.monthly");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				mo=new Monthly();
				mo=getMonthlyinfoByID(rs.getInt(1));
				monthlys.add(mo);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return monthlys;
	}
	/**
	 * A method to get a MonthlyFull information from the database by ID
	 * the method gets an id and returns a MonthlyFull class with the required information.
	 * @param b
	 * @return
	 */
	public MonthlyFull getMonthlyFullByMID(int id)
	{
		ResultSet rs=null;
		Subscription sub=new Subscription();
		MonthlyFull moF= new MonthlyFull();
		String query = new String("SELECT * FROM myfuel.monthly_full WHERE monthlyFullID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					sub=getSubscriptionBySid(rs.getInt(1));
					moF.setSubID(sub.getSubID());
					moF.setDiscountTableID(sub.getDiscountTableID());
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return moF;
	}
	/**
	 * method to get a Monthly Full type of subscription that is designed to work with discriminators
	 * the method gets a Subscription class and an id and returns a monthly full class that is conatining the information in the Database.
	 */
	public MonthlyFull getMonthlyFullByMID(int id,Subscription sub)
	{
		ResultSet rs=null;
		MonthlyFull moF= new MonthlyFull();
		String query = new String("SELECT * FROM myfuel.monthly_full WHERE monthlyFullID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					moF.setSubID(sub.getSubID());
					moF.setDiscountTableID(sub.getDiscountTableID());
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return moF;
	}
	/**
	 * A method to get Add a MonthlyFull Subscription to the DataBase 
	 * The method receives a MonthlyFull Class and Adding it to the DataBase by executing a query 
	 * 
	 * @param b
	 * @return
	 */
	public void AddMonthlyFull(MonthlyFull moF)
	{
		try{
			addSubscription((Subscription)moF,1);
			String query = new String("INSERT INTO myfuel.monthly_full (monthlyFullID) VALUES ('" + moF.getSubscriptionID()+ "');");
			st = conn.createStatement();
			st.executeUpdate(query);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		
	}
	/**
	 * A method to change an existing MonthlyFull in the DataBase  
	 * The method gets a MonthlyFull class and updates the matching line 
	 * Update is done by executing a query 
	 * @param b
	 * @return
	 */
	public void changeMonthlyFull(MonthlyFull moF)
	{
		try {
			changeSubInfo((Subscription)moF);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * A method to get all the MonthlyFull Subscriptions from the DataBase 
	 * The method stores the information in a LinkedList and Sends it back for further use.
	 * @param b
	 * @return
	 */
	public LinkedList<MonthlyFull> getAllMonthlyFulls() 
	{
		
		MonthlyFull mo;
		ResultSet rs = null;
		LinkedList<MonthlyFull> monthlyFulls=new LinkedList<MonthlyFull>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.monthly_full");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				mo=new MonthlyFull();
				mo=getMonthlyFullByMID(rs.getInt(1));
				monthlyFulls.add(mo);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return monthlyFulls;
	}
	/**
	 * a method to get a MonthlyMultiple Subscription from the DataBase by id
	 * the method gets an id and returns the matching monthlymupltiple as a MonthlyMultiple Class
	 * @param b
	 * @return
	 */
	public MonthlyMultiple getMonthlyMultInfoByID(int id)
	{
		ResultSet rs=null;
		Subscription sub=new Subscription();
		MonthlyMultiple moM= new MonthlyMultiple();
		String query = new String("SELECT * FROM myfuel.monthly_multiple WHERE monthlyMultipleID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					sub=getSubscriptionBySid(rs.getInt(1));
					moM.setSubID(sub.getSubID());
					moM.setDiscountTableID(sub.getDiscountTableID());
					moM.setNumOfCars(rs.getInt(2));
					
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return moM;
	}
	/**
	 * a method to get a monthly multiple type of subscription 
	 * the method gets a subscription class and retunrns a monthly multiple class with the relevant information.
	 */
	public MonthlyMultiple getMonthlyMultByMID(int id,Subscription sub)
	{
		ResultSet rs=null;
		MonthlyMultiple moM= new MonthlyMultiple();
		String query = new String("SELECT * FROM myfuel.monthly_multiple WHERE monthlyMultipleID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					moM.setSubID(sub.getSubID());
					moM.setDiscountTableID(sub.getDiscountTableID());
					moM.setNumOfCars(rs.getInt(2));
					
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return moM;
		
	}
	/**
	 * A method to add a New MonthlyMultiple subscription to the DataBase
	 * the method receives a MonthlyMultiple Class and adds it to the DB
	 * addition is done by executing a query 
	 * @param b
	 * @return
	 */
	public void AddMonthlyMultiple(MonthlyMultiple moM)
	{
		try{
			addSubscription((Subscription)moM,2);
			String query = new String("INSERT INTO myfuel.monthly_multiple (monthlyMultipleID,numOfCars) VALUES ('" + moM.getSubscriptionID()+"','"+moM.getNumOfCars()+ "');");
			st = conn.createStatement();
			st.executeUpdate(query);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		
	}
	/**
	 * A method to change a an existing monthlyMultiple subscription in the DataBase 
	 * the method receives a MonthlyMultiple Class and updates the fields by executing a query 
	 * @param b
	 * @return
	 */
	public void changeMonthlyMult(MonthlyMultiple moM)
	{
		try {
			changeSubInfo((Subscription)moM);
			st=conn.createStatement();
			String query = new String("UPDATE myfuel.monthly_multiple SET numOfCars='"+moM.getNumOfCars()+"'WHERE monthlyMultipleID='"+moM.getSubID()+"';");
			st.executeUpdate(query);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * A method to get all the the MonthlyMultiple Subscriptions in the DataBase 
	 * the method stores the information in a linked list and returns LinkedList<MonthlyMultuple>
	 * @param b
	 * @return
	 */
	public LinkedList<MonthlyMultiple> getAllMonthlyMultiples() 
	{
		MonthlyMultiple mo;
		ResultSet rs = null;
		LinkedList<MonthlyMultiple> monthlyMultiples=new LinkedList<MonthlyMultiple>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.monthly_multiple");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				mo=new MonthlyMultiple();
				mo=getMonthlyMultInfoByID(rs.getInt(1));
				monthlyMultiples.add(mo);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return monthlyMultiples;
	}
	/**
	 * a method to get a MonthlySimple Subscription from the DataBase by id
	 * the method gets an id and returns the matching monthlysimple as a MonthlySimple Class
	 * @param b
	 * @return
	 */
	public MonthlySimple getMonthlySimpleByID(int id)
	{
		ResultSet rs=null;
		Subscription sub=new Subscription();
		MonthlySimple moS= new MonthlySimple();
		String query = new String("SELECT * FROM myfuel.monthly_simple WHERE monthlySimpleID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					sub=getSubscriptionBySid(rs.getInt(1));
					moS.setSubID(sub.getSubID());
					moS.setDiscountTableID(sub.getDiscountTableID());
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return moS;
	}
	/**
	 * a method to get a monthly simple type of subscription 
	 * the method gets a subscription class and retunrns a monthly simple class with the relevant information.
	 */
	public MonthlySimple getMonthlySimpleByMID(int id,Subscription sub)
	{
		ResultSet rs=null;
		MonthlySimple moS= new MonthlySimple();
		String query = new String("SELECT * FROM myfuel.monthly_simple WHERE monthlySimpleID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					moS.setSubID(sub.getSubID());
					moS.setDiscountTableID(sub.getDiscountTableID());
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return moS;
	}
	/**
	 * A method to add a New MonthlySimple subscription to the DataBase
	 * the method receives a MonthlySimple Class and adds it to the DB
	 * addition is done by executing a query 
	 * @param b
	 * @return
	 */
	public void AddMonthlySimple(MonthlySimple moM)
	{
		try{
			addSubscription((Subscription)moM,3);
			String query = new String("INSERT INTO myfuel.monthly_simple (monthlySimpleID) VALUES ('" + moM.getSubscriptionID()+ "');");
			st = conn.createStatement();
			st.executeUpdate(query);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		
	}
	/**
	 * A method to change a an existing monthlyMultiple subscription in the DataBase 
	 * the method receives a MonthlyMultiple Class and updates the fields by executing a query 
	 * @param b
	 * @return
	 */
	public void changeMonthlySimple(MonthlySimple moS)
	{
		try {
			changeSubInfo((Subscription)moS);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * A method to get all the the MonthlySimple Subscriptions in the DataBase 
	 * the method stores the information in a linked list and returns LinkedList<MonthlySimple>
	 * @param b
	 * @return
	 */
	public LinkedList<MonthlySimple> getAllMonthlySimples() 
	{
		MonthlySimple mo;
		ResultSet rs = null;
		LinkedList<MonthlySimple> monthlysimples=new LinkedList<MonthlySimple>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.monthly_simple");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				mo=new MonthlySimple();
				mo=getMonthlySimpleByID(rs.getInt(1));
				monthlysimples.add(mo);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return monthlysimples;
	}	
	/**
	 * Method to execute a query to get a specific fuelStock from the database by a combination of 2 PK's
	 * @param StationID - primary key to identify the wanted stationID which the stock belongs to
	 * @param FuelTypeID - primary key to identify the fuelType which the stock consists of
	 * @return
	 */
	public FuelStock getFuelStockByID(int StationID,int FuelTypeID){
		FuelStock fsToGet= new FuelStock();
		
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.fuel_stock WHERE stationID = '" + StationID+ "' AND FuelTypeID = '" + FuelTypeID + "' ;");
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					fsToGet.setStationID(rs.getInt(1));
					fsToGet.setFuelTypeID(rs.getInt(2));
					fsToGet.setStockAmount(rs.getInt(3));
					fsToGet.setMinStockLevel(rs.getInt(4));
					fsToGet.setMaxStockLevel(rs.getInt(5));
					fsToGet.setLastReplenishID(rs.getInt(6));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return fsToGet;	

	}
	/**
	 * method to change a specific FuelStock.
	 * gets a FuelStockClass and updates its fields in DB by executing a query
	 * @param fsToChange
	 * @throws SQLException
	 */
	public void ChangeFuelStockInfo(FuelStock fsToChange) throws SQLException{
		st=conn.createStatement();
		String query = new String("UPDATE myfuel.fuel_stock  SET StockAmount = '"+fsToChange.getStockAmount()+"' WHERE stationID = '"+ fsToChange.getStationID() + "' AND FuelTypeID='"+fsToChange.getFuelTypeID()+"';");
		st.executeUpdate(query);
		query = new String("UPDATE myfuel.fuel_stock  SET minStockLevel = '"+fsToChange.getMinStockLevel()+"' WHERE stationID = '"+ fsToChange.getStationID() + "' AND FuelTypeID='"+fsToChange.getFuelTypeID()+"';");
		st.executeUpdate(query);
		query = new String("UPDATE myfuel.fuel_stock  SET maxStockLevel = '"+fsToChange.getMaxStockLevel()+"' WHERE stationID = '"+ fsToChange.getStationID() + "' AND FuelTypeID='"+fsToChange.getFuelTypeID()+"';");
		st.executeUpdate(query);
		query = new String("UPDATE myfuel.fuel_stock  SET lastFuelReplanishID = '"+fsToChange.getLastReplenishID()+"' WHERE stationID = '"+ fsToChange.getStationID() + "' AND FuelTypeID='"+fsToChange.getFuelTypeID()+"';");
		st.executeUpdate(query);
	}
	/**
	 * method to delete a FuelStock from the DataBase 
	 * the method gets station id and FuelTypeId and deletes the matching fuelStock.
	 * removing is done by executing a query.
	 * @param StationID
	 * @param FuelTypeID
	 * @throws SQLException
	 */
	public void delFuelStockByID(int StationID,int FuelTypeID) throws SQLException
	{
		st=conn.createStatement();
		String query = new String("DELETE FROM myfuel.fuel_stock"+" WHERE stationID = '"+StationID+"' AND FuelTypeID='"+FuelTypeID+"';");
		st.executeUpdate(query);
	}
	/**
	 * method to get all the FuelStocks in the DataBase 
	 * the method pulls the informatiom from the DB and returns a LinkedList<FuelStock>
	 * @return
	 */
	public LinkedList<FuelStock> getAllFuelStocks() 
	{
		FuelStock fs;
		ResultSet rs = null;
		LinkedList<FuelStock> fsl=new LinkedList<FuelStock>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.fuel_stock");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				fs= new FuelStock();
				fs.setStationID(rs.getInt(1));
				fs.setFuelTypeID(rs.getInt(2));
				fs.setStockAmount(rs.getInt(3));
				fs.setMinStockLevel(rs.getInt(4));
				fs.setMaxStockLevel(rs.getInt(5));
				fs.setLastReplenishID(rs.getInt(6));
				fsl.add(fs);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fsl;
	}
	/**
	 * A method to get a report from the DataBase by report id
	 * the method receives id and retuns a Report class with the information
	 * @param id
	 * @return
	 */
	public Report getReportInfo(int id)
	{
		Report repToRet= new Report();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.report WHERE reportID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
								
					repToRet.setReportID(rs.getInt(1));
					repToRet.setReportTitle(rs.getString(2));
					repToRet.setReportDate(rs.getString(3));
					repToRet.setReportDesc(rs.getString(4));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return repToRet;
	}
	
	/**
	 * A method to get a report from the DataBase by report id with the use of a discriminator
	 * the method receives id and retuns a Report class with the information
	 * @param id
	 * @return
	 */
	public Report getReportInfoWithDisc(int id)
	{
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.report WHERE reportID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{		
					switch (rs.getInt(5))
					{
						case 0:
							return getReportInfo(id);
						case 1:
							return getDiscountFeedbackReportByID(id);
						case 2:
							return getGeneratedScoreReportByID(id);
						case 3:
							return getIncomeReportInfo(id);
						case 4:
							return getPeriodicCustomerReportByID(id);
						case 5:
							return getPurchaseReportInfo(id);
						case 6:
							return getStockReportInfo(id);
					}
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/**
	 *	A method to add a new report to the Datbase 
	 *the method gets a Report class and adds it to the Database by executing a Query 
	 */
	public void addReport(Report rep, int disc)
	{
				try{
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.report (reportID,reportTitle,reportDate,reportDesc,discriminator) VALUES ('" + rep.getReportID()+"','"+rep.getReportTitle()+"','"+rep.getReportDate()+"','"+rep.getReportDesc()+"','"+disc+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * a method to Chage an existing reports fields
	 * method get a Report Class and cnages the field by executing a Query.
	 * @param id
	 * @return
	 */
	public void changeReportInfo (Report rep) {
		try{
				
				st=conn.createStatement();
				String query = new String("UPDATE myfuel.report SET reportTitle='"+rep.getReportTitle()+"'WHERE reportID='"+rep.getReportID()+"';");
				st.executeUpdate(query); 
				query = new String("UPDATE myfuel.report SET reportDate='"+rep.getReportDate()+"'WHERE reportID='"+rep.getReportID()+"';");
				st.executeUpdate(query); 
				query = new String("UPDATE myfuel.report SET reportDesc='"+rep.getReportDesc()+"'WHERE reportID='"+rep.getReportID()+"';");
				st.executeUpdate(query); 
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	/**
	 * a method To get All the Reports From the DataBase 
	 * the Method stores the information in a LinkedList
	 * method returns LinkedList<Report>. 
	 * @return
	 */
	public LinkedList<Report> getAllReports(){
		
		Report rep;
		ResultSet rs = null;
		LinkedList<Report> repL=new LinkedList<Report>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.report");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				repL.add(getReportInfoWithDisc(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return repL;
		
	}
	/**
	 * a method get a DiscountFeedbackReport from the DataBase by id
	 * the method get and id and pulls the information from the database into a report class
	 * method returns a Report class.
	 * @param id
	 * @return
	 */
	public DiscountFeedbackReport getDiscountFeedbackReportByID(int id)
	{
		Report rep= new Report();
		DiscountFeedbackReport discR=new DiscountFeedbackReport();
		rep=getReportInfo(id);
		discR.setReportID(rep.getReportID());
		discR.setReportTitle(rep.getReportTitle());
		discR.setReportDate(rep.getReportDate());
		discR.setReportDesc(rep.getReportDesc());
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.discount_feedback_report WHERE discountFeedbackReportID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					discR.setOverallOrders(rs.getInt(2));
					discR.setPeriodicDiscountID(rs.getInt(3));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return discR;
		
	}
	/**
	 * a method to add a new DiscountFeedBackReport to the DataBase 
	 * the method get a DiscountfeedbackReport and adds it to the database 
	 * addition is done by executing a query.
	 * @param b
	 * @return
	 */
	public void addDiscountFeedbackReport(DiscountFeedbackReport rep)
	{
		addReport((Report)rep,1);
		try{
			st=conn.createStatement();
			String query = new String("INSERT INTO myfuel.discount_feedback_report(discountFeedbackReportID,overallOrders,periodicDiscountID) VALUES ('" + rep.getReportID()+"','"+rep.getOverallOrders()+"','"+rep.getPeriodicDiscountID()+"');");
			st.executeUpdate(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}

	}
	/**
	 * a method to change an existing DiscountFeedbackReport in the DataBase 
	 * the method gets a DiscountFeedReport class and updating the fields 
	 * update is done by executing a query.
	 * @param b
	 * @return
	 */
	public void changeDiscountFeedbackReport(DiscountFeedbackReport rep)
	{
		try {
			changeReportInfo((Report)rep);
			st=conn.createStatement();
			String query = new String("UPDATE myfuel.discount_feedback_report SET overallOrders='"+rep.getOverallOrders()+"'WHERE discountFeedbackReportID='"+rep.getReportID()+"';");
			st.executeUpdate(query); 
			query = new String("UPDATE myfuel.discount_feedback_report SET periodicDiscountID='"+rep.getPeriodicDiscountID()+"'WHERE discountFeedbackReportID='"+rep.getReportID()+"';");
			st.executeUpdate(query); 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	/**
	 * method to get all the DiscountFeedbackReport info that is in the DataBase 
	 * the method get the info and stores it in a LinkedList<DiscountFeedbackReport>
	 * @param b
	 * @return
	 */
	public LinkedList<DiscountFeedbackReport> getAllDiscountFeedbackReports()
	{
		DiscountFeedbackReport discR;
		ResultSet rs = null;
		LinkedList<DiscountFeedbackReport> repL=new LinkedList<DiscountFeedbackReport>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.discount_feedback_report");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				discR= new DiscountFeedbackReport();
				discR=getDiscountFeedbackReportByID(rs.getInt(1));
				repL.add(discR);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return repL;
	}
	/**
	 * a method to get an amountDataPerFuel information from the DataBase 
	 * the method gets an id and returns the matching AmountDataPerFuel in class shape
	 * @param id
	 * @return
	 */
	public AmountDataPerFuel getAmountDataPerFuelByAID(int id)
	{
		AmountDataPerFuel adpf= new AmountDataPerFuel();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.amount_data_per_fuel WHERE amountDataPerFuelID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					adpf.setAmountDataPerFuelID(rs.getInt(1));
					adpf.setAmount(rs.getInt(2));
					adpf.setFuelTypeID(rs.getInt(3));
					adpf.setReportID(rs.getInt(4));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return adpf;
	}
	/**
	 * method to add a new AmountDataPerFuel to the DataBase 
	 * the method get a AmountDataPerFuel Class adds it to the DataBase
	 * @param b
	 * @return
	 */
	public void addAmountDataPerFuel(AmountDataPerFuel adpf)
	{
		try{
			st=conn.createStatement();
			String query = new String("INSERT INTO myfuel.amount_data_per_fuel(amountDataPerFuelID,amount,fuelTypeID,reportID) VALUES ('" + adpf.getAmountDataPerFuelID()+"','"+adpf.getAmount()+"','"+adpf.getFuelTypeID()+"','"+adpf.getReportID()+"');");
			st.executeUpdate(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}
		
	}
	/**
	 * a method to change an amountDataPerFuel in the DataBase  
	 * the method gets an AmountDataPerFuel Class and updates the field by executing a query.
	 * @param adpf
	 */
	public void changeAmountDataPerFuel(AmountDataPerFuel adpf)
	{

		try {
			st=conn.createStatement();
			String query = new String("UPDATE myfuel.amount_data_per_fuel SET amount='"+adpf.getAmount()+"'WHERE amountDataPerFuelID='"+adpf.getAmountDataPerFuelID()+"';");
			st.executeUpdate(query); 
			query = new String("UPDATE myfuel.amount_data_per_fuel SET fuelTypeID='"+adpf.getFuelTypeID()+"'WHERE amountDataPerFuelID='"+adpf.getAmountDataPerFuelID()+"';");
			st.executeUpdate(query); 
			query = new String("UPDATE myfuel.amount_data_per_fuel SET reportID='"+adpf.getReportID()+"'WHERE amountDataPerFuelID='"+adpf.getAmountDataPerFuelID()+"';");
			st.executeUpdate(query);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	/**
	 * method to get all the AmountDataPerFuels in the Database 
	 * the method pulls the information and stores it in a LinkedList<AmountDataPerFuel>
	 * the method return the linkedList.
	 * @param b
	 * @return
	 */
	public LinkedList<AmountDataPerFuel> getAllAmountDataPerFuels()
	{
		AmountDataPerFuel adpf;
		ResultSet rs = null;
		LinkedList<AmountDataPerFuel> amounts=new LinkedList<AmountDataPerFuel>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.amount_data_per_fuel");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				adpf= new AmountDataPerFuel();
				adpf=getAmountDataPerFuelByAID(rs.getInt(1));
				amounts.add(adpf);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return amounts;
	
		
	}
	/**
	 * method to get ScorePerfuelType info by id 
	 * the method gets an id and pulls the information out of the database, returns a ScorePerFuelType info.
	 * @param b
	 * @return
	 */
	public ScorePerFuelType getScorePerFuelTypeByID(int id)
	{
		ScorePerFuelType spft= new ScorePerFuelType();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.score_per_fuel_type WHERE scorePerFuelTypeID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
					spft.setScorePerFuelTypeID(rs.getInt(1));
					spft.setScore(rs.getInt(2));
					spft.setFuelTypeID(rs.getInt(3));
					spft.setGeneratedScoreReportID(rs.getInt(4));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return spft;
		
	}
	/**
	 * metho to add a new ScorePerFuelType information to the DataBase,
	 * the method gets a ScorePerFuelType class and adds the information to the DataBase by executing a query.
	 * @param adpf
	 */
	public void addScorePerFuelType(ScorePerFuelType adpf)
	{
		try{
			st=conn.createStatement();
			String query = new String("INSERT INTO myfuel.score_per_fuel_type(scorePerFuelTypeID,score,fuelTypeID,generatedScoreReportID) VALUES ('" + adpf.getScorePerFuelTypeID()+"','"+adpf.getScore()+"','"+adpf.getFuelTypeID()+"','"+adpf.getGeneratedScoreReportID()+"');");
			st.executeUpdate(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}
		
	}
	/**
	 * method to change a specific ScorePerFuelType in the Database
	 * the method get a ScorePerFuelType class and updates the fields by executing updates.
	 * @param b
	 * @return
	 */
	public void ChangeScorePerFuelType(ScorePerFuelType spft)
	{
		try {
			st=conn.createStatement();
			String query = new String("UPDATE myfuel.score_per_fuel_type SET score='"+spft.getScore()+"'WHERE scorePerFuelTypeID='"+spft.getScorePerFuelTypeID()+"';");
			st.executeUpdate(query); 
			query = new String("UPDATE myfuel.score_per_fuel_type SET fuelTypeID='"+spft.getFuelTypeID()+"'WHERE scorePerFuelTypeID='"+spft.getScorePerFuelTypeID()+"';");
			st.executeUpdate(query); 
			query = new String("UPDATE myfuel.score_per_fuel_type SET generatedScoreReportID='"+spft.getGeneratedScoreReportID()+"'WHERE scorePerFuelTypeID='"+spft.getScorePerFuelTypeID()+"';");
			st.executeUpdate(query);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	/**
	 * method to get all the ScorePerFuelType information in the database 
	 * the information is stored in a linked list and returned.
	 * @param b
	 * @return
	 */
	public LinkedList<ScorePerFuelType> getAllScorePerFuelTypes()
	{
		ScorePerFuelType adpf;
		ResultSet rs = null;
		LinkedList<ScorePerFuelType> scores=new LinkedList<ScorePerFuelType>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.score_per_fuel_type");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				adpf=new ScorePerFuelType();
				adpf=getScorePerFuelTypeByID(rs.getInt(1));
				scores.add(adpf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return scores;
	
		
	}
	/**
	 * method to get DataPerCustomer information from the DataBase by id
	 * the method gets an id and pulls the matching information in database, returns a DataPerCustomer class with the relevant info
	 * @param b
	 * @return
	 */
	public DataPerCustomer getDataPerCustomerInfo(int id)
	{
		DataPerCustomer dpcToRet= new DataPerCustomer();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.data_per_customer WHERE dataPerCustomerID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
								
					dpcToRet.setDataPerCustomerID(rs.getInt(1));
					dpcToRet.setOrderCount(rs.getInt(2));
					dpcToRet.setCustomerID(rs.getInt(3));
					dpcToRet.setReportID(rs.getInt(4));					
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return dpcToRet;
	}
	/**
	 * method to add a DataPerCustomer info to the DataBase, method gets a DataPerCustomer class and adds it to the DataBase by executing query.
	 */
	public void addDataPerCustomer(DataPerCustomer rep)
	{
				try{
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.data_per_customer (dataPerCustomerID,orderCount,customerID,periodicCustomerReportID) VALUES ('" + rep.getDataPerCustomerID()+"','"+rep.getOrderCount()+"','"+rep.getCustomerID()+"','"+rep.getReportID()+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * method to change a specific DataPerCustomer in the Database
	 * the method get a DataPerCustomer class and updates the fields by executing updates.
	 * @param rep DataPerCustomer
	 * 
	 */
	public void changeDataPerCustomer (DataPerCustomer rep){
		try{
				
				st=conn.createStatement();
				String query = new String("UPDATE myfuel.data_per_customer SET orderCount='"+rep.getOrderCount()+"'WHERE dataPerCustomerID='"+rep.getDataPerCustomerID()+"';");
				st.executeUpdate(query); 
				query = new String("UPDATE myfuel.data_per_customer SET customerID='"+rep.getCustomerID()+"'WHERE dataPerCustomerID='"+rep.getDataPerCustomerID()+"';");
				st.executeUpdate(query); 
				query = new String("UPDATE myfuel.data_per_customer SET periodicCustomerReportID='"+rep.getReportID()+"'WHERE dataPerCustomerID='"+rep.getDataPerCustomerID()+"';");
				st.executeUpdate(query); 
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	
	/**
	 * method to get all the DataPerCustomer information in the database 
	 * the information is stored in a linked list and returned.
	 */
	public LinkedList<DataPerCustomer> getAllDataPerCustomers(){
		
		DataPerCustomer rep;
		ResultSet rs = null;
		LinkedList<DataPerCustomer> repL=new LinkedList<DataPerCustomer>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.data_per_customer");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				rep=new DataPerCustomer();
				rep = getDataPerCustomerInfo(rs.getInt(1));
				repL.add(rep);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return repL;
	}
	 /**
	 * method to get DataPerCustomerType information from the DataBase by id
	 * the method gets an id and pulls the matching information in database, returns a DataPerCustomerType class with the relevant info
	 * @param id 
	 * @return DataPerCustomerType
	 */

	public DataPerCustomerType getDataPerCustomerTypeInfo(int id)
	{
		DataPerCustomerType dpcToRet= new DataPerCustomerType();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.data_per_customer_type WHERE dataPerCustomerTypeID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
								
					dpcToRet.setDataPerCustomerTypeID(rs.getInt(1));
					dpcToRet.setScore(rs.getInt(2));
					dpcToRet.setHourScore(stringToIntArr(rs.getString(3)));
					dpcToRet.setGeneratedScoreReportID(rs.getInt(4));	
					dpcToRet.setUserTypeID(rs.getInt(5));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return dpcToRet;
	}
	/**
	 * method to add a addDataPerCustomerType info to the DataBase, method gets a addDataPerCustomerType class and adds it to the DataBase by executing query.
	 */
	public void addDataPerCustomerType(DataPerCustomerType rep)
	{
				try{
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.data_per_customer_type (dataPerCustomerTypeID,score,hourScores,generatedScoreReportID,userTypeID) VALUES ('" + rep.getDataPerCustomerTypeID()+"','"+rep.getScore()+"','"+intArrToString(rep.getHourScore())+"','"+rep.getGeneratedScoreReportID()+"','"+rep.getUserTypeID()+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * method to change a specific DataPerCustomerType in the Database
	 * the method get a DataPerCustomerType class and updates the fields by executing updates.
	 * @param rep DataPerCustomerType
	 * 
	 */
	public void changeDataPerCustomerType (DataPerCustomerType rep){
		try{
				
				st=conn.createStatement();
				String query = new String("UPDATE myfuel.data_per_customer_type SET score='"+rep.getScore()+"'WHERE dataPerCustomerTypeID='"+rep.getDataPerCustomerTypeID()+"';");
				st.executeUpdate(query); 
				query = new String("UPDATE myfuel.data_per_customer_type SET hourScores='"+intArrToString(rep.getHourScore())+"'WHERE dataPerCustomerTypeID='"+rep.getDataPerCustomerTypeID()+"';");
				st.executeUpdate(query); 
				query = new String("UPDATE myfuel.data_per_customer_type SET generatedReportID='"+rep.getGeneratedScoreReportID()+"'WHERE dataPerCustomerTypeID='"+rep.getDataPerCustomerTypeID()+"';");
				st.executeUpdate(query); 
				query = new String("UPDATE myfuel.data_per_customer_type SET userTypeID='"+rep.getUserTypeID()+"'WHERE dataPerCustomerTypeID='"+rep.getDataPerCustomerTypeID()+"';");
				st.executeUpdate(query); 
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	/**
	 * method to get all the DataPerCustomerType information in the database 
	 * the information is stored in a linked list and returned.
	 */
	public LinkedList<DataPerCustomerType> getAllDataPerCustomerTypes(){
		
		DataPerCustomerType rep;
		ResultSet rs = null;
		LinkedList<DataPerCustomerType> repL=new LinkedList<DataPerCustomerType>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.data_per_customer_type");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				rep=new DataPerCustomerType();
				rep = getDataPerCustomerTypeInfo(rs.getInt(1));
				repL.add(rep);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return repL;
	}
	/**
	 * Returns a houseFuelInfo with the given id from the database
	 * @param id
	 * @return HouseFuelInfo infoFromDB
	 */
	public HouseFuelInfo getHouseFuelInfo(int id)
	{
		HouseFuelInfo hfiToRet= new HouseFuelInfo();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.house_fuel_info WHERE houseFuelInfoID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
								
					hfiToRet.setHouseFuelInfoID(rs.getInt(1));
					hfiToRet.setDeliveryStatus(rs.getInt(2));
					hfiToRet.setEstimatedTimeLeft(rs.getInt(3));
					hfiToRet.setHouseFuelOrderID(rs.getInt(4));

					
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return hfiToRet;
	}
	/**
	 * method to add a HouseFuelInfo info to the DataBase, method gets a HouseFuelInfo class and adds it to the DataBase by executing query.
	 */
	public void addHouseFuelInfo(HouseFuelInfo rep)
	{
				try{
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.house_fuel_info (houseFuelInfoID,deliveryStatus,estimatedTimeLeft,houseFuelOrderID) VALUES ('" + rep.getHouseFuelInfoID()+"','"+rep.getDeliveryStatus()+"','"+rep.getEstimatedTimeLeft()+"','"+rep.getHouseFuelOrderID()+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * method to change a specific HouseFuelInfo in the Database
	 * the method get a HouseFuelInfo class and updates the fields by executing updates.
	 * @param rep HouseFuelInfo
	 * 
	 */
	public void changeHouseFuelInfo (HouseFuelInfo rep){
		try{
				
				st=conn.createStatement();
				String query = new String("UPDATE myfuel.house_fuel_info SET deliveryStatus='"+rep.getDeliveryStatus()+"'WHERE houseFuelInfoID='"+rep.getHouseFuelInfoID()+"';");
				st.executeUpdate(query); 
				query = new String("UPDATE myfuel.house_fuel_info SET estimatedTimeLeft='"+rep.getEstimatedTimeLeft()+"'WHERE houseFuelInfoID='"+rep.getHouseFuelInfoID()+"';");
				st.executeUpdate(query); 
				query = new String("UPDATE myfuel.house_fuel_info SET houseFuelOrderID='"+rep.getHouseFuelOrderID()+"'WHERE houseFuelInfoID='"+rep.getHouseFuelInfoID()+"';");
				st.executeUpdate(query); 
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	/**
	 * method to get all the HouseFuelInfo information in the database 
	 * the information is stored in a linked list and returned.
	 */
	public LinkedList<HouseFuelInfo> getAllHouseFuelInfos(){
		
		HouseFuelInfo rep;
		ResultSet rs = null;
		LinkedList<HouseFuelInfo> repL=new LinkedList<HouseFuelInfo>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.house_fuel_info");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				rep=new HouseFuelInfo();
				rep = getHouseFuelInfo(rs.getInt(1));
				repL.add(rep);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return repL;
	}
	/**
	 * Returns a IncomeReportInfo with the given id from the database
	 * @param id
	 * @return IncomeReportInfo infoFromDB
	 */
	public IncomeReport getIncomeReportInfo(int id)
	{
		IncomeReport repToRet= new IncomeReport();
		Report rep = new Report();
		ResultSet rs = null;
		
		
		String query = new String("SELECT * FROM myfuel.income_report WHERE incomeReportID ="+id);
		try{
			rep = getReportInfo(id);
			repToRet.setReportID(id);
			repToRet.setReportTitle(rep.getReportTitle());
			repToRet.setReportDate(rep.getReportDate());
			repToRet.setReportDesc(rep.getReportDesc());
			
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{			
					repToRet.setReportID(rs.getInt(1));
					repToRet.setTotalIncome(rs.getDouble(2));
					repToRet.setIncomeStationID(rs.getInt(3));				
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return repToRet;
	}
	/**
	 * method to add a IncomeReport info to the DataBase, method gets a IncomeReport class and adds it to the DataBase by executing query.
	 */
	public void addIncomeReport(IncomeReport rep)
	{
				try{
					addReport((Report)rep,3);
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.income_report (incomeReportID,totalIncome,stationID) VALUES('"+rep.getReportID()+"','"+rep.getTotalIncome()+"','"+rep.getIncomeStationID()+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * method to change a specific IncomeReport in the Database
	 * the method get a IncomeReport class and updates the fields by executing updates.
	 * @param rep IncomeReport
	 * 
	 */
	public void changeIncomeReportInfo (IncomeReport rep){
		try{
				changeReportInfo((Report)rep);
				st=conn.createStatement();
				String query = new String("UPDATE myfuel.income_report SET stationID='"+rep.getIncomeStationID()+"'WHERE incomeReportID='"+rep.getReportID()+"';");
				st.executeUpdate(query);
				query = new String("UPDATE myfuel.income_report SET totalIncome='"+rep.getTotalIncome()+"'WHERE incomeReportID='"+rep.getReportID()+"';");
				st.executeUpdate(query);
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	/**
	 * method to get all the IncomeReport information in the database 
	 * the information is stored in a linked list and returned.
	 */
	public LinkedList<IncomeReport> getAllIncomeReports(){
		
		IncomeReport rep ;
		Report re;
		ResultSet rs = null;
		LinkedList<IncomeReport> repL=new LinkedList<IncomeReport>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.income_report");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				rep = new IncomeReport();
				re = new Report();
				re = getReportInfo(rs.getInt(1));
				rep.setReportID(re.getReportID());
				rep.setReportTitle(re.getReportTitle());
				rep.setReportDate(re.getReportDate());
				rep.setReportDesc(re.getReportDesc());
				rep.setTotalIncome(rs.getDouble(2));
				rep.setIncomeStationID(rs.getInt(3));
				repL.add(rep);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return repL;
		
	}
	/**
	 * Returns a PeriodicDiscount with the given id from the database
	 * @param id
	 * @return PeriodicDiscount infoFromDB
	 */
	public PeriodicDiscount getPeriodicDiscountInfo(int id)
	{
		PeriodicDiscount currentType= new PeriodicDiscount();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.periodic_discount WHERE periodicDiscountID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
								
					currentType.setPeriodicDiscountID(rs.getInt(1));
					currentType.setStartDate(rs.getString(2));
					currentType.setEndDate(rs.getString(3));
					currentType.setPeriodicDiscountTemplateID(rs.getInt(4));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return currentType;
	}
	/**
	 * method to add a PeriodicDiscount info to the DataBase, method gets a PeriodicDiscount class and adds it to the DataBase by executing query.
	 */
	public void addPeriodicDiscount(PeriodicDiscount rec)
	{
				try{
					
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.periodic_discount (periodicDiscountID,startDate,endDate,periodicDiscountTemplateID) VALUES ('" + rec.getPeriodicDiscountID()+"','"+rec.getStartDate()+"','"+rec.getEndDate()+"','"+rec.getPeriodicDiscountTemplateID()+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * method to change a specific PeriodicDiscount in the Database
	 * the method get a PeriodicDiscount class and updates the fields by executing updates.
	 * @param rep PeriodicDiscount
	 * 
	 */
	public void changePeriodicDiscountInfo (PeriodicDiscount perd){
		try{
				
				st=conn.createStatement();
				String query1 = new String("UPDATE myfuel.periodic_discount SET StartDate='"+perd.getStartDate()+"'WHERE periodicDiscountID='"+perd.getPeriodicDiscountID()+"';");
				st.executeUpdate(query1); 
				String query2 = new String("UPDATE myfuel.periodic_discount SET endDate='"+perd.getEndDate()+"'WHERE periodicDiscountID='"+perd.getPeriodicDiscountID()+"';");
				st.executeUpdate(query2); 
				String query3 = new String("UPDATE myfuel.periodic_discount SET periodicDiscountTemplateID='"+perd.getPeriodicDiscountTemplateID()+"'WHERE periodicDiscountID='"+perd.getPeriodicDiscountID()+"';");
				st.executeUpdate(query3); 
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	/**
	 * method to get all the PeriodicDiscount information in the database 
	 * the information is stored in a linked list and returned.
	 */
	public LinkedList<PeriodicDiscount> getAllPeriodicDiscount(){	
		PeriodicDiscount perd;
		ResultSet rs = null;
		LinkedList<PeriodicDiscount>perds=new LinkedList<PeriodicDiscount>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.periodic_discount");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				perd=new PeriodicDiscount();
				perd.setPeriodicDiscountID(rs.getInt(1));
				perd.setStartDate(rs.getString(2));
				perd.setEndDate(rs.getString(3));
				perd.setPeriodicDiscountTemplateID(rs.getInt(4));
				
				perds.add(perd);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return perds;
		
	}
	/**
	 * Returns a StockReport with the given id from the database
	 * @param id
	 * @return StockReport infoFromDB
	 */
	public StockReport getStockReportInfo(int id)
	{
		StockReport repToRet= new StockReport();
		Report rep = new Report();
		ResultSet rs = null;
		
		
		String query = new String("SELECT * FROM myfuel.stock_report WHERE stockReportID ="+id);
		try{
			rep = getReportInfo(id);
			repToRet.setReportTitle(rep.getReportTitle());
			repToRet.setReportDate(rep.getReportDate());
			repToRet.setReportDesc(rep.getReportDesc());
			
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{			
					repToRet.setReportID(rs.getInt(1));
					repToRet.setStockStationID(rs.getInt(2));				
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return repToRet;
	}
	/**
	 * method to add a StockReport info to the DataBase, method gets a StockReport class and adds it to the DataBase by executing query.
	 */
	public void addStockReport(StockReport rep)
	{
				try{
					addReport((Report)rep,6);
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.stock_report (stockReportID,stockStationID) VALUES('"+rep.getReportID()+"','"+rep.getStockStationID()+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * method to change a specific StockReport in the Database
	 * the method get a StockReport class and updates the fields by executing updates.
	 * @param rep StockReport
	 * 
	 */
	public void changeStockReportInfo (StockReport rep){
		try{
				changeReportInfo((Report)rep);
				st=conn.createStatement();
				String query = new String("UPDATE myfuel.stock_report SET stockStationID='"+rep.getStockStationID()+"'WHERE stockReportID='"+rep.getReportID()+"';");
				st.executeUpdate(query); 
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	/**
	 * method to get all the StockReport information in the database 
	 * the information is stored in a linked list and returned.
	 */
	public LinkedList<StockReport> getAllStockReports(){
		
		StockReport rep ;
		Report re;
		ResultSet rs = null;
		LinkedList<StockReport> repL=new LinkedList<StockReport>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.stock_report");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				rep = new StockReport();
				re = new Report();
				re = getReportInfo(rs.getInt(1));
				rep.setReportID(re.getReportID());
				rep.setReportTitle(re.getReportTitle());
				rep.setReportDate(re.getReportDate());
				rep.setReportDesc(re.getReportDesc());
				rep.setStockStationID(rs.getInt(2));
				repL.add(rep);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return repL;
		
	}
	/**
	 * Returns a PurchasesReport with the given id from the database
	 * @param id
	 * @return PurchasesReport infoFromDB
	 */
	public PurchasesReport getPurchaseReportInfo(int id)
	{
		PurchasesReport repToRet= new PurchasesReport();
		Report rep = new Report();
		ResultSet rs = null;
		
		
		String query = new String("SELECT * FROM myfuel.purchases_report WHERE purchasesReportID ="+id);
		try{
			rep = getReportInfo(id);
			repToRet.setReportTitle(rep.getReportTitle());
			repToRet.setReportDate(rep.getReportDate());
			repToRet.setReportDesc(rep.getReportDesc());
			
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{			
					repToRet.setReportID(rs.getInt(1));
					repToRet.setPurchasesStationID(rs.getInt(2));				
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return repToRet;
	}
	/**
	 * method to add a PurchasesReport info to the DataBase, method gets a PurchasesReport class and adds it to the DataBase by executing query.
	 */
	public void addPurchasesReport(PurchasesReport rep)
	{
				try{
					addReport((Report)rep,5);
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.purchases_report (purchasesReportID,stationID) VALUES('"+rep.getReportID()+"','"+rep.getPurchasesStationID()+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * method to change a specific PurchasesReport in the Database
	 * the method get a PurchasesReport class and updates the fields by executing updates.
	 * @param rep PurchasesReport
	 * 
	 */
	public void changePurchasesReportInfo (PurchasesReport rep){
		try{
				changeReportInfo((Report)rep);
				st=conn.createStatement();
				String query = new String("UPDATE myfuel.purchases_report SET stationID='"+rep.getPurchasesStationID()+"'WHERE purchasesReportID='"+rep.getReportID()+"';");
				st.executeUpdate(query); 
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	/**
	 * method to get all the PurchasesReport information in the database 
	 * the information is stored in a linked list and returned.
	 */
	public LinkedList<PurchasesReport> getAllPurchasesReports(){
		
		PurchasesReport rep ;
		Report re;
		ResultSet rs = null;
		LinkedList<PurchasesReport> repL=new LinkedList<PurchasesReport>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.purchases_report");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				rep = new PurchasesReport();
				re = new Report();
				re = getReportInfo(rs.getInt(1));
				rep.setReportID(re.getReportID());
				rep.setReportTitle(re.getReportTitle());
				rep.setReportDate(re.getReportDate());
				rep.setReportDesc(re.getReportDesc());
				rep.setPurchasesStationID(rs.getInt(2));
				repL.add(rep);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return repL;
		
	}
	/**
	 * a method to get a PeriodicDiscountTemplate Info from the DataBase By ID 
	 * the method gets an id and extracts the matching line in the Table 
	 * the information is stored in a class and the method returns that class
	 * @param b
	 * @return
	 */
	public PeriodicDiscountTemplate getPeriodicDiscountTemplateInfo(int id)
	{
		PeriodicDiscountTemplate currentType= new PeriodicDiscountTemplate();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.periodic_discount_template WHERE PeriodicDiscountTemplateID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
								
					currentType.setPeriodicDiscountTemplateID(rs.getInt(1));
					currentType.setDiscount(rs.getDouble(2));
					currentType.setStartTime(rs.getString(3));
					currentType.setEndTime(rs.getString(4));
					currentType.setMinFuelAmount(rs.getDouble(5));
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return currentType;
	}
	/**
	 * method to add a new periodic discount template to the DataBase 
	 * the method gets a PeriodicDiscountTemplate class and adds it to the DataBase by executing a query 
	 * @param rec
	 */
	public void addPeriodicDiscountTemplate(PeriodicDiscountTemplate rec)
	{
				try{
					
					st=conn.createStatement();
					String query = new String("INSERT INTO myfuel.periodic_discount_template (periodicDiscountTemplateID,discount,startTime,endTime,minFuelAmount) VALUES ('" + rec.getPeriodicDiscountTemplateID()+"','"+rec.getDiscount()+"','"+rec.getStartTime()+"','"+rec.getEndTime()+"','"+rec.getMinFuelAmount()+"');");
					st.executeUpdate(query);
				}
				catch(SQLException err)
				{
					err.printStackTrace();
					
				}
	}
	/**
	 * method to change a speicif periodic discount in the DataBase 
	 * the method get a PeriodicDiscountTemplate Class and updates the fields
	 * update is done by executing queries.
	 */
	public void changePeriodicDiscountTemplateInfo (PeriodicDiscountTemplate perd){
		try{
				
				st=conn.createStatement();
				String query1 = new String("UPDATE myfuel.periodic_discount_template SET discount='"+perd.getDiscount()+"'WHERE periodicDiscountTemplateID='"+perd.getPeriodicDiscountTemplateID()+"';");
				st.executeUpdate(query1); 
				String query2 = new String("UPDATE myfuel.periodic_discount_template SET startTime='"+perd.getStartTime()+"'WHERE periodicDiscountTemplateID='"+perd.getPeriodicDiscountTemplateID()+"';");
				st.executeUpdate(query2); 
				String query3 = new String("UPDATE myfuel.periodic_discount_template SET endTime='"+perd.getEndTime()+"'WHERE periodicDiscountTemplateID='"+perd.getPeriodicDiscountTemplateID()+"';");
				st.executeUpdate(query3); 
				String query4 = new String("UPDATE myfuel.periodic_discount_template SET minFuelAmount='"+perd.getMinFuelAmount()+"'WHERE periodicDiscountTemplateID='"+perd.getPeriodicDiscountTemplateID()+"';");
				st.executeUpdate(query3); 
				st.executeUpdate(query4); 
			}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	/**
	 * method to get all the periodic discount templates from the database
	 * the method stores each template in a LinkedList and returns it to continue work
	 */
	public LinkedList<PeriodicDiscountTemplate> getAllPeriodicDiscountTemplate(){	
		PeriodicDiscountTemplate perd;
		ResultSet rs = null;
		LinkedList<PeriodicDiscountTemplate>perds=new LinkedList<PeriodicDiscountTemplate>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.periodic_discount_template");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				perd=new PeriodicDiscountTemplate();
				perd=getPeriodicDiscountTemplateInfo(rs.getInt(1));
				perds.add(perd);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return perds;		
	}	
	/**
	 * method to convert int array to string 
	 * @param arr
	 * @return
	 */
	public String intArrToString(int[] arr)
	{
		String toRet = "";
		for(int i=0;i<arr.length;i++)
		{
			toRet = toRet+String.format("%2d|", arr[i]).replace(' ','0');
		}
		return toRet;
	}
	/**
	 * method that converts from string to Int array 
	 * @param str
	 * @return
	 */
	public int[] stringToIntArr(String str)
	{
		int[] arr = new int[24];
		
		for(int i=0; i < str.length(); i+=3)
			arr[i/3] = Integer.parseInt(str.substring(i,i+2));
		return arr;
	}
	/**
	 * a method to get a peridoicCustomerReport from the DataBase 
	 * the method get and id and returns the matching PeriodicCustomerReport.
	 * @param b
	 * @return
	 */
	public PeriodicCustomerReport getPeriodicCustomerReportByID(int id)
	{
		PeriodicCustomerReport pcr=new PeriodicCustomerReport();
		Report rep=new Report();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.periodic_customer_report WHERE periodicCustomerID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
				rep=getReportInfo(rs.getInt(1));
				pcr.setReportID(id);
				pcr.setReportTitle(rep.getReportTitle());
				pcr.setReportDate(rep.getReportDate());
				pcr.setReportDesc(rep.getReportDesc());
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return pcr;	
	}
	/**
	 * a method to add a PeriodicCustomer Report to the DataBase 
	 * the method get a PeriodicCustomerReport class and adds it to the Database
	 * the addition is done by executing a query 
	 * @param b
	 * @return
	 */
	public void addPeriodicCustomerReport(PeriodicCustomerReport pcr)
	{
		addReport((Report)pcr,4);
		try{
			
			st=conn.createStatement();
			String query = new String("INSERT INTO myfuel.periodic_customer_report (periodicCustomerID) VALUES ('" +pcr.getReportID()+"');");
			st.executeUpdate(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}
	}
	/**
	 * 
	 * method to get all the PeriodicCustomerReports from the Database
	 * the method stores each report in a LinkedList and returns LinkedList<PeriodicCustomerReport>
	 * @param b
	 * @return
	 */
	public LinkedList<PeriodicCustomerReport> getAllPerioicCustomerReports()
	{
		PeriodicCustomerReport perd;
		ResultSet rs = null;
		LinkedList<PeriodicCustomerReport>perdCusreps=new LinkedList<PeriodicCustomerReport>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.periodic_discount_template");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				perd=new PeriodicCustomerReport();
				perd=getPeriodicCustomerReportByID(rs.getInt(1));
				perdCusreps.add(perd);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return perdCusreps;
	}
	/**
	 * a method to get a GeneratedScoreReport from the DataBase
	 * the method gets an id and returns a GeneratedScoreReport Class
	 * the get action is done by executing a query
	 * @param id
	 * @return
	 */
	public GeneratedScoreReport getGeneratedScoreReportByID(int id)
	{
		GeneratedScoreReport gcr=new GeneratedScoreReport();
		Report rep=new Report();
		ResultSet rs = null;
		String query = new String("SELECT * FROM myfuel.generated_score_report WHERE generatedScoreReportID ="+id);
		try{
			st = conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next())
				{
				rep=getReportInfo(rs.getInt(1));
				gcr.setReportID(id);
				gcr.setReportTitle(rep.getReportTitle());
				gcr.setReportDate(rep.getReportDate());
				gcr.setReportDesc(rep.getReportDesc());
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return gcr;	
	}
	/**
	 * a method to add a GeneratedScoreReport  to the DataBase 
	 * the method get a GeneratedScoreReport class and adds it to the Database
	 * the addition is done by executing a query 
	 * @param b
	 * @return
	 */
	public void addGeneratedScoreReport(GeneratedScoreReport gcr)
	{
		addReport((Report)gcr,2);
		try{
			
			st=conn.createStatement();
			String query = new String("INSERT INTO myfuel.generated_score_report (generatedScoreReportID) VALUES ('" +gcr.getReportID()+"');");
			st.executeUpdate(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}
	}

public void addPurchasePlan(PurchasePlan pp)
	{
		try{
			
			st=conn.createStatement();
			String query = new String("INSERT INTO myfuel.purchase_plan (purchasePlanID,planName,discount,agreementDetails) VALUES ('" +pp.getPurchasePlanID()+"','"+pp.getPlanName()+"','"+pp.getDiscount()+"','"+pp.getAgreementDetails()+"');");
			st.executeUpdate(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}
	}


	/**
	 * 
	 * method to get all the GeneratedScoreReport from the Database
	 * the method stores each report in a LinkedList and returns LinkedList<GeneratedScoreReport>
	 * @param b
	 * @return
	 */
	public LinkedList<GeneratedScoreReport> getAllGeneratedScoreReports()
	{
		GeneratedScoreReport perd;
		ResultSet rs = null;
		LinkedList<GeneratedScoreReport>perdCusreps=new LinkedList<GeneratedScoreReport>();
		try {
			st=conn.createStatement();
			String query=new String("SELECT * FROM myfuel.generated_score_report");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				perd=new GeneratedScoreReport();
				perd=getGeneratedScoreReportByID(rs.getInt(1));
				perdCusreps.add(perd);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return perdCusreps;
	}
	public void addLogin(UserLogin us) {
		try{
			st=conn.createStatement();
			String query = new String("INSERT INTO myfuel.login (UserName,Password) VALUES('"+us.getUserName()+"','"+us.getUserPass()+"');");
			st.executeUpdate(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			
		}
		
	}

	
	
	//GETTER $ SETTER//
	int boolToInt(boolean b) {
	    return Boolean.compare(b, false);
	}
	
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}	
}

	
	
	
	
