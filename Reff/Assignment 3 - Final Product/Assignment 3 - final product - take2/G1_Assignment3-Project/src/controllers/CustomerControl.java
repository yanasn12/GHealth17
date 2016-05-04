package controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.log.Log;

import common.*;
import entities.*;


/**
 * This Class provides methods to manage customers and customer related entities.
 * 
 * Manages: Customer, User, UserType, CarCustomer. HouseOWner, Car
 * Active:  Customer
 * Active is used to efficiently access a specific object that is being edited.
 */
public class CustomerControl {
	static List<Customer> customers;
	static Customer activeCustomer;
	static List<UserType> userTypes;
	static List<User> users;
	static List<CarCustomer> carCustomers;
	static List<HouseOwner> houseOwners;
	static List<Car> cars;
	
	/**
	 * signifies that this customer reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static Customer dummyCustomer = null;
	/**
	 * signifies that this user reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static User dummyUser = null;
	/**
	 * signifies that this userType reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static UserType dummyUserType = null;
	/**
	 * signifies that this paymentInfo reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static  CarCustomer dummyCarCustomer = null;
	/**
	 * signifies that this paymentInfo reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static HouseOwner dummyHouseOwner = null;
	/**
	 * signifies that this Car reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static Car dummyCar = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		customers = new LinkedList<Customer> ();
		userTypes = new LinkedList<UserType> ();
		users = new LinkedList <User> ();
		carCustomers = new LinkedList <CarCustomer> ();
		houseOwners = new LinkedList<HouseOwner> ();
		cars = new LinkedList<Car> ();
		
		//dummyUser = new User ();
		//dummyCustomer = new Customer();
		//dummyUserType = new UserType ();
		//dummyHouseOwner = new HouseOwner();
		//dummyCarCustomer = new CarCustomer ();
		//dummyCar = new Car ();
	}
	
	// =================================== BUFFERS ==============================
	
	/**
	 * Gets a {@code Customer} out of the customer buffer with a matching id. If doesn't exist attempts to fetch it from DB.
	 * @param id The userID that is searched for.
	 * @return Customer with matching id or NULL if Customer not found.
	 */
	public static Customer getCustomerById(int id) {
		
		for (Customer customer : customers) {
			if (customer.getUserID() == id)
				return customer;
		}
		
		return fetchCustomer(id);
	}
	
	/**
	 * Returns a list of Customers after updating it from the DB. 
	 * @return {@code List<Customer>} buffer
	 */
	public static List<Customer> getAllCustomers ()
	{
		fetchAllCustomers();
		return customers;
	}

	/**
	 *  Returns a {@code UserType} out of the UserType buffer with a matching id. If userType doesn't exist attempts to fetch it from DB.
	 * @param id  The UserTypeID that is searched for.
	 * @return {@code UserType} with matching UserTypeID or NULL if UserType not found.
	 */
	public static UserType getUserTypeById(int id) {	
		
		for (UserType userType : userTypes) {
			if (userType.getUserTypeID() == id)
				return userType;
		}
		
		return fetchUserType(id);
	}
	
	/**
	 * Returns a list of UserTypes after updating it from the DB. 
	 * @return {@code List<UserType>} buffer
	 */
	public static List<UserType> getAllUserTypes ()
	{
		fetchAllUserTypes ();
		return userTypes;
	}
	
	/**
	 *  Returns a {@code User} out of the User buffer with a matching id. If the user doesn't exist attempts to fetch it from DB.
	 * @param id  The UserID that is searched for.
	 * @return {@code User} with matching UserID or NULL if User not found.
	 */
	public static User getUserById(int id){
		
		for (User user : users)
		{
			if (user.getUserID() == id)
				return user;
		}
		
		return fetchUser(id);
	}
	
	/**
	 * Returns a list of users after updating it from the DB. 
	 * @return {@code List<User>} buffer
	 */
	public static List<User> getAllUsers ()
	{
		fetchAllUsers ();
		return users;
	}
	
	/**
 	*  Returns a {@code CarCustomer} out of the CarCustomer buffer with a matching id. If the entity doesn't exist attempts to fetch it from DB.
 	* @param id  The CarCustomerID that is searched for.
 	* @return CarCustomer with matching CarCustomerID or NULL if User not found.
	*/	
	public static CarCustomer getCarCustomerById (int id)
	{
		for (CarCustomer carCustomer : carCustomers)
			if (carCustomer.getUserID() == id)
				return carCustomer;
		return fetchCarCustomer(id);
	}
	
	/**
	 * Returns a list of CarCustomers after updating it from the DB. 
	 * @return {@code List<CarCustomer>} CarCustomers buffer.
	 */
	public static List<CarCustomer> getAllCarCustomers ()	
	{
		fetchAllCarCustomers();
		return carCustomers;
	}
	
	/**
 	*  Returns a {@code HouseOwner} out of the HouseOwner buffer with a matching id. If the entity doesn't exist attempts to fetch it from DB.
 	* @param id  The HouseOwnerID that is searched for.
 	* @return HouseOwner with matching HouseOwnerID or NULL if User not found.
	*/	
	public static HouseOwner getHouseOwnerById (int id)
	{
		for (HouseOwner houseOwner : houseOwners)
			if (houseOwner.getUserID() == id)
				return houseOwner;
		return fetchHouseOwner (id);
	}
	
	/**
	 * Returns a list of HouseOwners after updating it from the DB. 
	 * @return {@code List<HouseOwner>} HouseOwners buffer.
	 */	
	public static List<HouseOwner> getAllHouseOwners ()
	{
		fetchAllHouseOwners();
		return houseOwners;
	}
	
	/**
	 *  Returns a {@code Car} out of the {@code Car} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The CarID that is searched for.
	 * @return {@code Car} with matching ID or NULL if entity was not found.
	 */
	public static Car getCarById(int id){
		
		
		for (Car car : cars)
		{
			if (car.getCarID() == id)
				return car;
		}
		return fetchCar(id);
	}
	
	/**
	 * Returns a list of all Cars after updating it from the DB. 
	 * @return {@code List<Car>} buffer
	 */
	public static List<Car> getAllCars(){
		
		fetchAllCars ();
		return cars;
	}
	// ================= BUFFERS GET
	
	/**
	 * returns the customers buffer , recommended to use getAll instead as this method does not updates the buffer from the DB.
	 * @return {@code List<Customer>} 
	 */
	public static List<Customer> getCustomers() {
		return customers;
	}

	/**
	 *  returns the userTypes buffer , recommended to use getAll instead as this method does not updates the buffer from the DB.
	 * @return {@code List<USerType>} 
	 */
	public static List<UserType> getUserTypes() {
		return userTypes;
	}

	/**
	 *  returns the users buffer , recommended to use getAll instead as this method does not updates the buffer from the DB.
	 * @return {@code List<User>} 
	 */
	public static List<User> getUsers() {
		return users;
	}
	
	/**
	 *  returns the CarCustomers buffer , recommended to use getAll instead as this method does not updates the buffer from the DB.
	 * @return {@code List<CarCustomer>} 
	 */
	public static List<CarCustomer> getCarCustomers() {
		return carCustomers;
	}
	
	/**
	 *  returns the HouseOwners buffer , recommended to use getAll instead as this method does not updates the buffer from the DB.
	 * @return {@code List<HouseOwner>} 
	 */
	public static List<HouseOwner> getHouseOwners() {
		return houseOwners;
	}
	
	/**
	 *  returns the Cars buffer , recommended to use getAll instead as this method does not updates the buffer from the DB.
	 * @return {@code List<Car>} 
	 */
	public static List<Car> getCars (){
		return cars;
	}
	
	// ===================================== ACTIVE HANDLING ===================================

	/**
	 *  Returns the {@code Customer} that is currently being worked on.
	 * @return {@code Customer} loaded as active.
	 */
	public static Customer getActive() {
		return activeCustomer;
	}
	/**
	 *  Designates a new {@code Customer} as the one being worked on. 
	 * @param activeCustomer The {@code Customer} to be worked on.
	 */
	public static void setActive(Customer activeCustomer) {
		CustomerControl.activeCustomer = activeCustomer;
	}
	/**
	 *  Initializes a new Customer and puts it as active customer
	 */
	public static void loadNewActive ()
	{
		activeCustomer = new Customer ();
	}
	
	// ===================================== DB ACESSS ===============================
	
	
	
	// ================= CUSTOMER
	/**
	 *  Attempts to fetch a customer from DB. Recommended using {@code getCustomerById} instead to check if the Customer is already in the buffer.
	 * @param id which is looked for
	 * @return Customer with the matching ID
	 */
	
	public static Customer fetchCustomer (int id)
	{
		Customer res = null;
		
		Op op = new Op (Operations.GET_CUSTOMER, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op)LoginCont.client.getMessage();
		res = (Customer) op.getEntity();
		if (res != null)
		{	
			for (Customer compared : customers)
				if (compared.getUserID() == res.getUserID())
					return compared;
			
			customers.add(res);
			users.add(res);	
		}
		return res;
	}
	
	/**
	 *  updates the Customer buffer with all customers that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllCustomers ()
	{
		List <Customer> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_CUSTOMER_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op)LoginCont.client.getMessage();
		res = (List <Customer>) op.getEntity();
		if (res != null)
		{	
			for (Customer resS : res)
			{
				addCurrent = true;
				for (Customer compared : customers)
					if (compared.getUserID() == resS.getUserID())
						addCurrent = false;
				
				if (addCurrent)
				{
					customers.add(resS);
					users.add(resS);
				}
			}
		}
	}
	
	/**
	 *  Removes the customer with the given userID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the customer to be removed.
	 */
	public static void evictCustomer (int id)
	{
		for (Customer customer : customers)
		{
			if (customer.getUserID() == id)
				evictCustomer(customer);
		}
	}
	
	/**
	 *  Removes thegivem customer from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param customer the customer to be removed from the buffer.
	 */
	public static void evictCustomer (Customer customer)
	{
		Op op = new Op (Operations.UPDATE_CUSTOMER,customer);
		LoginCont.client.handleMessageFromClientUI(op);

		for (Invoice data : InvoiceControl.getInvoices())
		{
			if (data.getCustomerID() == customer.getUserID())
				data.setCustomer(dummyCustomer);
		}
		
		for (DataPerCustomer data : PeriodicCustomerReportControl.getDatasPerCustomer())
		{
			if (data.getCustomerID() == customer.getUserID())
				data.setCustomer(dummyCustomer);
		}
		
		for (PaymentInfo data : PaymentInfoControl.getPaymentInfos())
		{
			if (data.getCustomerID() == customer.getUserID())
				data.setCustomer(dummyCustomer);
		}
		customers.remove(customer);
		users.remove(customer);
	}
	
	/**
	 * Updates the customer with the given userID in the DB.
	 * @param id of the customer to be updated.
	 */
	public static void updateCustomer (int id)
	{
		for (Customer customer : customers)
		{
			if (customer.getUserID() == id)
			{
				Op op = new Op (Operations.UPDATE_CUSTOMER,customer);
				LoginCont.client.handleMessageFromClientUI(op);
				break;
			}
		}
	}
		
	// =============== USER
	
	/**
	 *  Attempts to fetch a {@code User} from DB. Recommended using {@code getUserById} instead to check if the user is already in the buffer.
	 * @param id which is looked for
	 * @return User with the give ID.
	 */
	public static User fetchUser (int id)
	{
		User res = null;
		
		Op op = new Op (Operations.GET_USER, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op)LoginCont.client.getMessage();
		res = (User) op.getEntity();
		if (res != null)
		{
			for (User compared : users)
				if (compared.getUserID() == res.getUserID())
					return compared;
			
			users.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the User buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllUsers ()
	{
		List<User> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_USER_ALL,-1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op)LoginCont.client.getMessage();
		res = (List<User>) op.getEntity();
		if (res != null)
		{
			for (User resS : res)
			{
				addCurrent = true;
				for (User compared : users)
					if (compared.getUserID() == resS.getUserID())
						addCurrent = false;
				
				if (addCurrent)
					users.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the User with the given userID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the user to be removed.
	 */
	public static void evictUser (int id)
	{
		for (User user : users)
		{
			if (user.getUserID() == id)
			{
				Op op = new Op (Operations.UPDATE_USER,user);
				LoginCont.client.handleMessageFromClientUI(op);

				users.remove(user);
				break;
			}
		}
	}
	
	/**
	 * Updates the user with the given userID in the DB.
	 * @param id of the user to be updated
	 */
	public static void updateUser (int id)
	{
		for (User user : users)
		{
			if (user.getUserID() == id)
			{
				Op op = new Op (Operations.UPDATE_USER,user);
				LoginCont.client.handleMessageFromClientUI(op);
				break;
			}
		}
	}
	
	// =============== USER TYPE
	
	/**
	 *  Attempts to fetch a {@code UserType} from DB. Recommended using {@code getUserTypeById} instead to check if the user is already in the buffer.
	 * @param id which is looked for
	 * @return UserType with the given ID
	 */
	public static UserType fetchUserType (int id)
	{
		UserType res = null;
		
		Op op = new Op (Operations.GET_USER_TYPE, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op)LoginCont.client.getMessage();
		res = (UserType) op.getEntity();
		
		if (res != null)
		{
				for (UserType compared : userTypes)
					if (compared.getUserTypeID() == res.getUserTypeID())
						return compared;
				userTypes.add(res);
		}
		return res;		
	}
	
	/**
	 *  updates the UserType buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllUserTypes ()
	{
		List<UserType> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_USER_TYPE_ALL,-1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op)LoginCont.client.getMessage();
		res = (List<UserType>) op.getEntity();
		
		if (res != null)
			for (UserType resS : res)
			{
				addCurrent = true;
				for (UserType compared : userTypes)
					if (compared.getUserTypeID() == resS.getUserTypeID())
						addCurrent = false;
				
				if (addCurrent)
					userTypes.add(resS);
			}
	}
	/**
	 *  Removes the UserType with the given userTypeID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the UserType to be removed.
	 */
	public static void evictUserType (int id)
	{
		for (UserType userType : userTypes)
		{
			if (userType.getUserTypeID() == id)
			{
				Op op = new Op (Operations.UPDATE_USER_TYPE,userType);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (User data : users)
				{
					if (data.getUserTypeID() == id)
						data.setUserType(dummyUserType);
				}
				
				userTypes.remove(userType);
				break;
			}
		}
	}
	
	/**
	 * Updates the UserType with the given UserTypeID in the DB.
	 * @param id of the UserType to be updated
	 */
	public static void updateUserType (int id)
	{
		for (UserType userType : userTypes)
		{
			if (userType.getUserTypeID() == id)
			{
				Op op = new Op (Operations.UPDATE_USER_TYPE,userType);
				LoginCont.client.handleMessageFromClientUI(op);
				break;
			}
		}
	}	
	
	// =================== CAR CUSTOMER
	
	/**
	 *  Attempts to fetch a {@code CarCustomer} from DB. Recommended using {@code getCarCustomerById} instead to check if the user is already in the buffer.
	 * @param id which is looked for
	 * @return CarCustomer with the give ID.
	 */
	public static CarCustomer fetchCarCustomer (int id)
	{
		CarCustomer res = null;
		
		Op op = new Op (Operations.GET_CAR_CUSTOMER, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op)LoginCont.client.getMessage();
		res = (CarCustomer) op.getEntity();
		if (res != null)
		{
			for (CarCustomer compared : carCustomers)
				if (compared.getUserID() == res.getUserID())
					return compared;
			
			carCustomers.add(res);
			customers.add(res);
			users.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the CarCustomer buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllCarCustomers ()
	{
		List<CarCustomer> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_CAR_CUSTOMER_ALL,-1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op)LoginCont.client.getMessage();
		res = (List<CarCustomer>) op.getEntity();
		if (res != null)
		{
			for (CarCustomer resS : res)
			{
				addCurrent = true;
				for (CarCustomer compared : carCustomers)
					if (compared.getUserID() == resS.getUserID())
						addCurrent = false;
				
				if (addCurrent)
				{
					carCustomers.add(resS);
					customers.add(resS);
					users.add(resS);
				}
			}
		}
	}
	
	/**
	 *  Removes the User with the given CarCustomerID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the CarCustomer to be removed.
	 */
	public static void evictCarCustomer (int id)
	{
		for (CarCustomer searched : carCustomers)
		{
			if (searched.getUserID() == id)
			{
				Op op = new Op (Operations.UPDATE_CAR_CUSTOMER,searched);
				LoginCont.client.handleMessageFromClientUI(op);

				for (Car car : cars)
				{
					if (car.getCustomerID() == searched.getUserID())
						car.setCarCustomer(dummyCarCustomer);
				}
				
				evictCustomer(searched);
				
				carCustomers.remove(searched);
				break;
			}
		}
	}
	
	/**
	 * Updates the CarCustomer with the given CarCustomerID in the DB.
	 * @param id of the CarCustomer to be updated
	 */
	public static void updateCarCustomer (int id)
	{
		for (CarCustomer searched : carCustomers)
		{
			if (searched.getUserID() == id)
			{
				Op op = new Op (Operations.UPDATE_CAR_CUSTOMER,searched);
				LoginCont.client.handleMessageFromClientUI(op);

				break;
			}
		}
	}
	
	/**
	 * Creates the given CarCustomer in the DB.
	 * @param carCustomer to be created in the DB.
	 */
	public static void createCarCustomer (CarCustomer carCustomer)
	{
		Op op = new Op (Operations.CREATE_CAR_CUSTOMER,carCustomer);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
	
	// ==================== HOUSE OWNER
	
	/**
	 *  Attempts to fetch a {@code HouseOwner} from DB. Recommended using {@code getHouseOwnerById} instead to check if the user is already in the buffer.
	 * @param id which is looked for
	 * @return HouseOwner with the give ID.
	 */
	public static HouseOwner fetchHouseOwner (int id)
	{
		HouseOwner res = null;
		
		Op op = new Op (Operations.GET_HOUSE_OWNER, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op)LoginCont.client.getMessage();
		res = (HouseOwner) op.getEntity();
		if (res != null)
		{
			for (HouseOwner compared : houseOwners)
				if (compared.getUserID() == res.getUserID())
					return compared;
			
			houseOwners.add(res);
			customers.add(res);
			users.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the HouseOwner buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllHouseOwners ()
	{
		List<HouseOwner> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_HOUSE_OWNER_ALL,-1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op)LoginCont.client.getMessage();
		res = (List<HouseOwner>) op.getEntity();
		if (res != null)
		{
			for (HouseOwner resS : res)
			{
				addCurrent = true;
				for (HouseOwner compared : houseOwners)
					if (compared.getUserID() == resS.getUserID())
						addCurrent = false;
				
				if (addCurrent)
				{
					houseOwners.add(resS);
					customers.add(resS);
					users.add(resS);
				}
			}
		}
	}
	
	/**
	 *  Removes the User with the given HouseOwnerID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the HouseOwner to be removed.
	 */
	public static void evictHouseOwner (int id)
	{
		for (HouseOwner searched : houseOwners)
		{
			if (searched.getUserID() == id)
			{
				Op op = new Op (Operations.UPDATE_HOUSE_OWNER,searched);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (HouseFuelOrder item : HouseFuelOrderControl.getHouseFuelOrders())
				{
					if (item.getHouseOwnerID() == searched.getUserID())
						item.setHouseOwner(dummyHouseOwner);
				}
				
				evictCustomer(searched);
				houseOwners.remove(searched);
				break;
			}
		}
	}
	
	/**
	 * Updates the HouseOwner with the given HouseOwnerID in the DB.
	 * @param id of the HouseOwner to be updated
	 */
	public static void updateHouseOwner (int id)
	{
		for (HouseOwner searched : houseOwners)
		{
			if (searched.getUserID() == id)
			{
				Op op = new Op (Operations.UPDATE_HOUSE_OWNER,searched);
				LoginCont.client.handleMessageFromClientUI(op);

				break;
			}
		}
	}
	
	/**
	 * Creates the given HouseOWner in the DB.
	 * @param carCustomer to be created in the DB.
	 */
	public static void createHouseOwner (HouseOwner houseOwner)
	{
		Op op = new Op (Operations.CREATE_HOUSE_OWNER,houseOwner);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
	
	// ============================ CAR
	
	/**
	 *  Attempts to fetch a {@code Car} from DB. Recommended using {@code getCarById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code Car} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static Car fetchCar (int id)
	{
		Car res = null;
		
		Op op = new Op (Operations.GET_CAR, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (Car) op.getEntity();
		if (res != null)
		{
			for (Car compared : cars)
				if (compared.getCarID() == res.getCarID())
					return compared;
				cars.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the Car buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllCars ()
	{
		List<Car> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_CAR_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<Car>) op.getEntity();
		if (res != null)
		{
			for (Car resS : res)
			{
				addCurrent = true;
				for (Car compared : cars)
					if (compared.getCarID() == resS.getCarID())
						addCurrent = false;
				
				if (addCurrent)
					cars.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the Car with the given CarID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the Car to be removed.
	 */
	public static void evictCar (int id)
	{
		for (Car car : cars)
		{
			if (car.getCarID() == id)
			{
				Op op = new Op (Operations.UPDATE_CAR,car);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (CarFuelOrder item : CarFuelOrderControl.getCarFuelOrders())
				{
					if (item.getCarID() == car.getCarID())
						item.setCar(dummyCar);
				}
				
				cars.remove (car);
				break;
			}
		}
	}
	
	/**
	 * Updates the Car with the given CarID in the DB.
	 * @param id of the Car to be updated
	 */
	public static void updateCar (int id)
	{
		for (Car car : cars)
		{
			if (car.getCarID() == id)
			{
				Op op = new Op (Operations.UPDATE_CAR,car);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given Car in the DB.
	 * @param car to be created in the DB.
	 */
	public static void createCar (Car car)
	{
		Op op = new Op (Operations.CREATE_CAR,car);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
