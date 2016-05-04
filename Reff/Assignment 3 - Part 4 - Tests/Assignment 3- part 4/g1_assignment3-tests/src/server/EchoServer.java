package server;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import GUI.ServerGUI;
import common.Operations;
import common.Op;
import entities.*;
import ocsf.server.*;

/**
 * 
 * This class maintains the connection between the server and the clients. Its waits for messages from the clients and handles them if it needed 
 * 
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************

  final public static int DEFAULT_PORT = 5555;
  public DBC db;
  ArrayList<UserLogin> LoggedInList = new ArrayList<UserLogin>();
  //static mysqlConnection conn;
  //Constructors ****************************************************

  public EchoServer(int port) 
  {
    super(port);
  }
  /**
   * constructor to initialize the connection between the server and the database
   * @param port
   * @param sqlIp
   * @param sqlPort
   * @param sqlUser
   * @param sqlDBName
   * @param sqlPassword
   */
  
  public EchoServer(int port, String sqlIp, int sqlPort, String sqlUser,String sqlDBName, String sqlPassword) {
		super(port);

		try {
			String hostName = new String("jdbc:mysql://" + sqlIp + ":"+ sqlPort + "/" + sqlDBName);
			db = new DBC(hostName,sqlUser,sqlPassword);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(ServerGUI.mainframe,"ERROR - Could not listen for clients!");
		}
}
  
  /**
   * method to handle the msg recieved from the client
   * depending ont the OP of the msg
   * @param msg - OP consisting of operation and entity
   */
  
  
  public void handleMessageFromClient(Object msg, ConnectionToClient client)
  {
	  try{
	  Op operation = (Op) msg;
	  
	  //if (operation.getOp() != Operations.LOGOUT && operation.getOp() != Operations.LOGIN && operation.getOp()!= Operations.GET_UT)
		//  return;
	  
		switch (operation.getOp()) {
		case LOGIN:
			login(operation, client);
			break;
		case TEST:
			test(operation,client);
			break;
		case LOGOUT:
			logout(operation,client);
			break;
		case GET_UT:
			getUserTypeAsUser(operation,client);
			break;
		case GET_PURCHASE_PLAN:
			getPurchasePlan(operation,client);
			break;
		case GET_PURCHASE_PLAN_ALL:
			getPurchasePlanAll(operation,client);
			break;
		case UPDATE_PURCHASE_PLAN:
			updatePurchasePlan(operation,client);
			break;
		case GET_FUEL_TYPE:
			getFuelType(operation,client);
			break;
		case GET_FUEL_TYPE_ALL:
			getFuelTypeAll(operation,client);
			break;
		case UPDATE_FUEL_TYPE:
			db.changeFuelType((FuelType) operation.getEntity());
			break;
		case GET_USER:
			getUser(operation, client);
			break;
		case GET_USER_ALL:
			getUserAll(operation, client);
			break;
		case UPDATE_USER:
			updateUser(operation, client);
			break;
		case GET_USER_TYPE:
			getUserType(operation, client);
			break;
		case GET_USER_TYPE_ALL:
			getUserTypeAll(operation, client);
			break;
		case UPDATE_USER_TYPE:
			updateUserType(operation, client);
			break;
		case GET_CUSTOMER:
			getCustomer(operation, client);
			break;
		case GET_CUSTOMER_ALL:
			getCustomerAll(operation, client);
			break;
		case UPDATE_CUSTOMER:
			updateCustomer(operation, client);
			break;
		case GET_INVOICE:
			getInvoice(operation, client);
			break;
		case GET_INVOICE_ALL:
			getInvoiceAll(operation, client);
			break;
		case UPDATE_INVOICE:
			updateInvoice(operation, client);
			break;
		case GET_ORDER:
			getOrder(operation, client);
			break;
		case GET_ORDER_ALL:
			getOrderAll(operation, client);
			break;
		case UPDATE_ORDER:
			updateOrder(operation, client);
			break;
		case GET_CAR_FUEL_ORDER:
			getCarFuelOrder(operation, client);
			break;
		case GET_CAR_FUEL_ORDER_ALL:
			getCarFuelOrderAll(operation, client);
			break;
		case UPDATE_CAR_FUEL_ORDER:
			updateCarFuelOrder(operation, client);
			break;
		case GET_PAYMENT_INFO:
			getPaymentInfo(operation, client);
			break;
		case GET_PAYMENT_INFO_ALL:
			getPaymentInfoAll(operation, client);
			break;
		case UPDATE_PAYMENT_INFO:
			updatePaymentInfo(operation, client);
			break;
		case GET_HOUSE_FUEL_ORDER:
			getHouseFuelOrder(operation, client);
			break;
		case GET_HOUSE_FUEL_ORDER_ALL:
			getHouseFuelOrderAll(operation, client);
			break;
		case UPDATE_HOUSE_FUEL_ORDER:
			updateHouseFuelOrder(operation, client);
			break;
		case GET_CAR_CUSTOMER:
			getCarCustomer(operation, client);
			break;
		case GET_CAR_CUSTOMER_ALL:
			getCarCustomerAll(operation, client);
			break;
		case UPDATE_CAR_CUSTOMER:
			updateCarCustomer(operation, client);
			break;
		case GET_HOUSE_OWNER:
			getHouseOwner(operation, client);
			break;
		case GET_HOUSE_OWNER_ALL:
			getHouseOwnerAll(operation, client);
			break;
		case UPDATE_HOUSE_OWNER:
			updateHouseOwner(operation, client);
			break;
		case GET_DISCOUNT_TABLE:
			getDiscountTable(operation, client);
			break;
		case GET_DISCOUNT_TABLE_ALL:
			getDiscountTableAll(operation, client);
			break;
		case UPDATE_DISCOUNT_TABLE:
			updateDiscountTable(operation, client);
			break;
		case GET_FUEL_STOCK:
			getFuelStock(operation, client);
			break;
		case GET_FUEL_STOCK_ALL:
			getFuelStockAll(operation, client);
			break;
		case UPDATE_FUEL_STOCK:
			updateFuelStock(operation, client);
			break;
		case GET_FUEL_REPLENISH:
			getFuelReplenish(operation, client);
			break;
		case GET_FUEL_REPLENISH_ALL:
			getFuelReplenishAll(operation, client);
			break;
		case UPDATE_FUEL_REPLENISH:
			updateFuelReplenish(operation, client);
			break;
		case GET_HOUSE_FUEL_INFO:
			getHouseFuelInfo(operation, client);
			break;
		case GET_HOUSE_FUEL_INFO_ALL:
			getHouseFuelInfoAll(client);
			break;
		case GET_RECEIPT:
			getReceipt(operation, client);
			break;
		case GET_RECEIPT_ALL:
			getReceiptAll(operation, client);
			break;
		case UPDATE_RECEIPT:
			updateReceipt(operation, client);
			break;
		case GET_CAR:
			getCar(operation, client);
			break;
		case GET_CAR_ALL:
			getCarAll(operation, client);
			break;
		case UPDATE_CAR:
			updateCar(operation, client);
			break;
		case GET_SUBSCRIPTION:
			getSubscription(operation, client);
			break;
		case GET_SUBSCRIPTION_ALL:
			getSubscriptionAll(operation, client);
			break;
		case UPDATE_SUBSCRIPTION:
			updateSubscription(operation, client);
			break;
		case GET_MONTHLY_SIMPLE:
			getMonthlySimple(operation, client);
			break;
		case GET_MONTHLY_SIMPLE_ALL:
			getMonthlySimpleAll(operation, client);
			break;
		case CREATE_FUEL_TYPE:
			db.addFuelType((FuelType)operation.getEntity());
			break;
		case CREATE_USER:
			db.insertNewUser((User)operation.getEntity(),0);
			break;
		case CREATE_INVOICE:
			db.addInvoice((Invoice)operation.getEntity());
			break;
		case CREATE_HOUSE_FUEL_ORDER:
			db.addHouseFuelOrder((HouseFuelOrder)operation.getEntity());
			break;
		case CREATE_CAR_FUEL_ORDER:
			db.addCarFuelOrder((CarFuelOrder)operation.getEntity());
			break;
		case CREATE_ORDER:
			db.addOrder((Order)operation.getEntity(),0);
			break;
		case CREATE_HOUSE_OWNER:
			db.addHouseOwner((HouseOwner)operation.getEntity());
			break;
		case CREATE_CAR_CUSTOMER:
			db.AddCarCustomer((CarCustomer)operation.getEntity());
			break;
		case CREATE_CUSTOMER:
			db.addNewCustomer((Customer)operation.getEntity(),0);
			break;
		case CREATE_CAR:
			db.addCarInfo((Car)operation.getEntity());
			break;
		case GET_CASH:
			getCashInfo(operation,client);
			break;
		case GET_CASH_ALL:
			getCashInfoAll(client);
			break;
		case UPDATE_CASH:
			db.changeCashInfo((Cash)operation.getEntity());
			break;
		case CREATE_CASH:
			db.addnewCash((Cash)operation.getEntity());
			break;
		case GET_CREDIT_CARD:
			getCreditCard(operation,client);
			break;
		case GET_CREDIT_CARD_ALL:
			getCreditCardAll(client);
			break;
		case UPDATE_CREDIT_CARD:
			db.changeCreditCardInfo((CreditCard)operation.getEntity());
			break;
		case CREATE_CREDIT_CARD:
			db.addNewCreditCard((CreditCard)operation.getEntity());
			break;
		case CREATE_DISCOUNT_FEEDBACK_REPORT:
			db.addDiscountFeedbackReport((DiscountFeedbackReport)operation.getEntity());
			break;
		case UPDATE_DISCOUNT_FEEDBACK_REPORT:
			db.changeDiscountFeedbackReport((DiscountFeedbackReport)operation.getEntity());
			break;
		case GET_DISCOUNT_FEEDBACK_REPORT:
			getDiscountFeedbackReport(operation,client);
			break;
		case GET_DISCOUNT_FEEDBACK_REPORT_ALL:
			getDiscountFeedbackReport(client);
			break;
		case GET_INCOME_REPORT:
			getIncomeReport(operation,client);
			break;
		case GET_INCOME_REPORT_ALL:
			getIncomeReportAll(client);
			break;
		case UPDATE_INCOME_REPORT:
			db.changeIncomeReportInfo((IncomeReport)operation.getEntity());
			break;
		case CREATE_INCOME_REPORT:
			db.addIncomeReport((IncomeReport)operation.getEntity());
			break;	
		case GET_LOCATION:
			getLocation(operation,client);
			break;
		case GET_LOCATION_ALL:
			getLocationAll(operation,client);
			break;
		case UPDATE_LOCATION:
			db.changeLocationInfo((Location)operation.getEntity());
			break;
		case UPDATE_MONTHLY_SIMPLE:
			db.changeMonthlySimple((MonthlySimple)operation.getEntity());
			break;
		case CREATE_MONTHLY_SIMPLE:
			db.AddMonthlySimple((MonthlySimple)operation.getEntity());
			break;
		case GET_MONTHLY:
			getMonthly(operation,client);
			break;
		case GET_MONTHLY_ALL:
			getMonthlyAll(client);
			break;
		case UPDATE_MONTHLY:
			db.ChangeMonthlyinfo((Monthly)operation.getEntity());
			break;
		case CREATE_MONTHLY:
			db.addMonthly((Monthly)operation.getEntity());
			break;
		case GET_MONTHLY_FULL:
			getMonthlyFull(operation,client);
			break;
		case GET_MONTHLY_FULL_ALL:
			getMonthlyFullAll(client);
			break;
		case UPDATE_MONTHLY_FULL:
			db.changeMonthlyFull((MonthlyFull)operation.getEntity());
			break;
		case CREATE_MONTHLY_FULL:
			db.AddMonthlyFull((MonthlyFull)operation.getEntity());
			break;
		case GET_MONTHLY_MULTIPLE:
			getMonthlyMult(operation,client);
			break;
		case GET_MONTHLY_MULTIPLE_ALL:
			getMonthlyMultAll(client);
			break;
		case UPDATE_MONTHLY_MULTIPLE:
			db.changeMonthlyMult((MonthlyMultiple)operation.getEntity());
			break;
		case CREATE_MONTHLY_MULTIPLE:
			db.AddMonthlyMultiple((MonthlyMultiple)operation.getEntity());
			break;
		case CREATE_PAYMENT_INFO:
			db.AddPaymentInfo((PaymentInfo)operation.getEntity(),0);
			break;
		case GET_PERIODIC_DISCOUNT:
			getPeriodicDiscount(operation,client);
			break;
		case GET_PERIODIC_DISCOUNT_ALL:
			getPeriodicDiscountAll(client);
			break;
		case UPDATE_PERIODIC_DISCOUNT:
			db.changePeriodicDiscountInfo((PeriodicDiscount)operation.getEntity());
			break;
		case CREATE_PERIODIC_DISCOUNT:
			db.addPeriodicDiscount((PeriodicDiscount)operation.getEntity());
			break;
		case GET_PERIODIC_DISCOUNT_TEMPLATE:
			getPeriodicDiscountTemplate(operation,client);
			break;
		case GET_PERIODIC_DISCOUNT_TEMPLATE_ALL:
			getPeriodicDiscountTemplateAll(client);
			break;
		case UPDATE_PERIODIC_DISCOUNT_TEMPLATE:
			db.changePeriodicDiscountTemplateInfo((PeriodicDiscountTemplate)operation.getEntity());
			break;
		case CREATE_PERIODIC_DISCOUNT_TEMPLATE:
			db.addPeriodicDiscountTemplate((PeriodicDiscountTemplate)operation.getEntity());
			break;
		case CREATE_USER_TYPE:
			db.insertNewUserType((UserType)operation.getEntity());
			break;
		case GET_PURCHASES_REPORT:
			getPurchasesReport(operation,client);
			break;
		case GET_PURCHASES_REPORT_ALL:
			getPurchasesReportAll(client);
			break;
		case UPDATE_PURCHASES_REPORT:
			db.changePurchasesReportInfo((PurchasesReport)operation.getEntity());
			break;
		case CREATE_PURCHASES_REPORT:
			db.addPurchasesReport((PurchasesReport)operation.getEntity());
			break;
		case CREATE_RECEIPT:
			db.addNewReceipt((Receipt)operation.getEntity());
			break;
		case GET_STOCK_REPORT:
			getStockReport(operation,client);
			break;
		case GET_STOCK_REPORT_ALL:
			getStockReportAll(client);
			break;
		case UPDATE_STOCK_REPORT:
			db.changeStockReportInfo((StockReport)operation.getEntity());
			break;
		case CREATE_STOCK_REPORT:
			db.addStockReport((StockReport)operation.getEntity());
			break;
		case GET_REPORT:
			getReport(operation,client);
			break;
		case GET_REPORT_ALL:
			getReportAll(client);
			break;
		case UPDATE_REPORT:
			db.changeReportInfo((Report)operation.getEntity());
			break;
		case GET_STATION:
			getStation(operation,client);
			break;
		case GET_STATION_ALL:
			getStationAll(client);
			break;
		case UPDATE_STATION:
			db.changeStationById((Station)operation.getEntity());
			break;
		case GET_WORKER:
			getWorker(operation,client);
			break;
		case GET_WORKER_ALL:
			getWorkerAll(client);
			break;
		case UPDATE_WORKER:
			db.changeWorker((Worker)operation.getEntity());
			break;
		case CREATE_AMOUNT_DATA_PER_FUEL:
			db.addAmountDataPerFuel((AmountDataPerFuel)operation.getEntity());
			break;
		case UPDATE_AMOUNT_DATA_PER_FUEL:
			db.changeAmountDataPerFuel((AmountDataPerFuel)operation.getEntity());
			break;
		case GET_AMOUNT_DATA_PER_FUEL:
			getAmountDataPerFuel(operation, client);
			break;
		case GET_AMOUNT_DATA_PER_FUEL_ALL:
			getAmountDataPerFuelAll(client);
			break;
		case UPDATE_HOUSE_FUEL_INFO:
			db.changeHouseFuelInfo((HouseFuelInfo)operation.getEntity());
			break;
		case CREATE_HOUSE_FUEL_INFO:
			db.addHouseFuelInfo((HouseFuelInfo)operation.getEntity());
			break;
		case GET_SCORE_PER_FUEL_TYPE:
			getScorePerFuelType(operation,client);
			break;
		case GET_SCORE_PER_FUEL_TYPE_ALL:
			getScorePerFuelTypeAll(client);
			break;
		case UPDATE_SCORE_PER_FUEL_TYPE:
			db.ChangeScorePerFuelType((ScorePerFuelType)operation.getEntity());
			break;
		case CREATE_SCORE_PER_FUEL_TYPE:
			db.addScorePerFuelType((ScorePerFuelType)operation.getEntity());
			break;
		case GET_DATA_PER_CUSTOMER:
			getDataPerFuelCustomer(operation,client);
			break;
		case GET_DATA_PER_CUSTOMER_ALL:
			getDataPerCustomerAll(client);
			break;
		case UPDATE_DATA_PER_CUSTOMER:
			db.changeDataPerCustomer((DataPerCustomer)operation.getEntity());
			break;
		case CREATE_DATA_PER_CUSTOMER:
			db.addDataPerCustomer((DataPerCustomer)operation.getEntity());
			break;
		case CREATE_LOGIN:
			db.addLogin((UserLogin)operation.getEntity());
			break;
		case CREATE_PERIODIC_CUSTOMER_REPORT:
			db.addPeriodicCustomerReport((PeriodicCustomerReport)operation.getEntity());
			break;
		case GET_DATA_PER_CUSTOMER_TYPE_ALL:
			getDataPerCustomerTypeAll (operation,client);
			break;
		case CREATE_FUEL_REPLENISH:
			db.addFuelReplenish((FuelReplenish)operation.getEntity());
			break;
		case CREATE_PURCHASE_PLAN:
			db.addPurchasePlan((PurchasePlan)operation.getEntity());
			break;
		case GET_GENERATED_SCORE_REPORT:
			getGeneratedScoreReport(operation,client);
			break;
		case CREATE_GENERATED_SCORE_REPORT:
			db.addGeneratedScoreReport((GeneratedScoreReport)operation.getEntity());
			break;
		case GET_GENERATED_SCORE_REPORT_ALL:
			db.getAllGeneratedScoreReports();
			break;
		case GET_DATA_PER_CUSTOMER_TYPE:
			getDataPerCustomer(operation,client);
			break;
		case CREATE_DATA_PER_CUSTOMER_TYPE:
			db.addDataPerCustomerType((DataPerCustomerType)operation.getEntity());
			break;
		case UPDATE_DATA_PER_CUSTOMER_TYPE:
			db.changeDataPerCustomerType((DataPerCustomerType)operation.getEntity());
			break;
		

		default:
			break;
		}
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
  }
   private void getDataPerCustomerTypeAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllDataPerCustomerTypes());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
 
 private void getDataPerCustomerAll(ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getAllDataPerCustomers());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getDataPerFuelCustomer(Op operation,ConnectionToClient client) {
			 Op op = new Op(Operations.ANS,db.getDataPerCustomerInfo((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		} 
  
  
 private void getScorePerFuelTypeAll(ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getAllScorePerFuelTypes());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getScorePerFuelType(Op operation,ConnectionToClient client) {
			 Op op = new Op(Operations.ANS,db.getScorePerFuelTypeByID((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		} 
  
  
  private void getHouseFuelInfoAll(ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getAllHouseFuelInfos());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getHouseFuelInfo(Op operation,ConnectionToClient client) {
			 Op op = new Op(Operations.ANS,db.getHouseFuelInfo((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		} 
  
 private void getAmountDataPerFuelAll(ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getAllAmountDataPerFuels());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getAmountDataPerFuel(Op operation,ConnectionToClient client) {
		Op op = new Op(Operations.ANS,db.getAmountDataPerFuelByAID((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}    
  
 private void getWorkerAll(ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getAllWorkers());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getWorker(Op operation,ConnectionToClient client) {
			 Op op = new Op(Operations.ANS,db.getWorkerByWid((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}  
  
private void getStationAll(ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getAllStations());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getStation(Op operation,ConnectionToClient client) {
			 Op op = new Op(Operations.ANS,db.getStationById((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}
  
 private void getReportAll(ConnectionToClient client) {
	 
		 Op op = new Op(Operations.ANS,db.getAllReports());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getReport(Op operation,ConnectionToClient client) {
			 Op op = new Op(Operations.ANS,db.getReportInfoWithDisc((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}  
  
private void getStockReportAll(ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getAllStockReports());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getStockReport(Op operation,ConnectionToClient client) {
			 Op op = new Op(Operations.ANS,db.getStockReportInfo((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		} 
  
private void getPurchasesReportAll(ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getAllPurchasesReports());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getPurchasesReport(Op operation,ConnectionToClient client) {
			 Op op = new Op(Operations.ANS,db.getPurchaseReportInfo((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}    
  
private void getPeriodicDiscountTemplateAll(ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getAllPeriodicDiscountTemplate());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getPeriodicDiscountTemplate(Op operation,ConnectionToClient client) {
			 Op op = new Op(Operations.ANS,db.getPeriodicDiscountTemplateInfo((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}  
  
private void getPeriodicDiscountAll(ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getAllPeriodicDiscount());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getPeriodicDiscount(Op operation,ConnectionToClient client) {
			 Op op = new Op(Operations.ANS,db.getPeriodicDiscountInfo((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		} 
  
private void getMonthlyMultAll(ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getAllMonthlyMultiples());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getMonthlyMult(Op operation,ConnectionToClient client) {
			 Op op = new Op(Operations.ANS,db.getMonthlyMultInfoByID((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}  
  
 private void getMonthlyFullAll(ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getAllMonthlyFulls());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getMonthlyFull(Op operation,ConnectionToClient client) {
			 Op op = new Op(Operations.ANS,db.getMonthlyFullByMID((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}  
  
private void getMonthlyAll(ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getAllMonthlys());
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
private void getMonthly(Op operation,ConnectionToClient client) {
			 Op op = new Op(Operations.ANS,db.getMonthlyinfoByID((int)operation.getEntity()));
			try{
				client.sendToClient(op);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}
private void getLocationAll(Op operation, ConnectionToClient client) {
	 Op op = new Op(Operations.ANS,db.getAllLocations());
		try{
			client.sendToClient(op);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
}
private void getLocation(Op operation,ConnectionToClient client) {
		 Op op = new Op(Operations.ANS,db.getLocationByLID((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
  
 private void getIncomeReportAll(ConnectionToClient client) {
	 Op op = new Op(Operations.ANS,db.getAllIncomeReports());
	try{
		client.sendToClient(op);
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
}
private void getIncomeReport(Op operation, ConnectionToClient client) {
	Op op = new Op(Operations.ANS,db.getIncomeReportInfo((int)operation.getEntity()));
	try{
		client.sendToClient(op);
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	
}
private void getDiscountFeedbackReport(Op operation, ConnectionToClient client) {
	  //Op op = new Op(Operations.ANS,db.getDiscountFeedbackReportByID((int)operation.getEntity()));
		//try{
		//	client.sendToClient(op);
		//}
		//catch(IOException e)
		//{
		//	e.printStackTrace();
		//}
}
private void getDiscountFeedbackReport(ConnectionToClient client) {
	//Op op = new Op(Operations.ANS,db.getAllDiscountFeedbackReports());
			//try{
			//	client.sendToClient(op);
			//}
			//catch(IOException e)
			//{
			//	e.printStackTrace();
			//}
}
private void getCreditCardAll(ConnectionToClient client) {
	Op op = new Op(Operations.ANS,db.getAllCreditCards());
	try{
		client.sendToClient(op);
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	
}
private void getCreditCard(Op operation, ConnectionToClient client) {
	  Op op = new Op(Operations.ANS,db.getCreditInfoByCID((int)operation.getEntity()));
	  try{
		  client.sendToClient(op);
	  }
	  catch(IOException e)
	  {
		  e.printStackTrace();
	  }
}
private void getCashInfoAll(ConnectionToClient client) {
	Op op = new Op(Operations.ANS,db.getAllCashInfo());
	try{
		client.sendToClient(op);
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
}
private void getCashInfo(Op operation,ConnectionToClient client)
  {
	  Op op = new Op(Operations.ANS,db.getCashInfoByCID(((int)operation.getEntity())));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void getUserTypeAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllUserTypes());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  private void getUserType (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getUserTypeByUid(((int)operation.getEntity())));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  private void updateUserType (Op operation, ConnectionToClient client)
  {
		try{
			db.changeUserByUid((User)operation.getEntity());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
  }
  private void getUserAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllUsers());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  } 
  private void getUser (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getUserByUid(((int)operation.getEntity())));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }  
  private void updateUser (Op operation, ConnectionToClient client)
  {
		try{
			db.changeUserByUid((User)operation.getEntity());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
  }
  private void getFuelType (Op operation, ConnectionToClient client)
  {
	Op op = new Op(Operations.ANS,db.getFuelInfo((int)operation.getEntity()));
	try{
		client.sendToClient(op);
	}
	catch (IOException e){
		e.printStackTrace();
	}
  }
  private void getFuelTypeAll (Op operation, ConnectionToClient client)
  {
	Op op = new Op(Operations.ANS,db.getAllFuelTypes());
	  try{
		  client.sendToClient(op);
	  }
	  catch (IOException e){
			e.printStackTrace();
	  }
  }
  private void updatePurchasePlan(Op operation, ConnectionToClient client) {
	PurchasePlan PPid = (PurchasePlan) operation.getEntity();
	try{
	db.changePurchasePlan(PPid);
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	
}
  private void getPurchasePlanAll(Op operation, ConnectionToClient client) {
	Op op = new Op (Operations.ANS,db.getAllPlans());
	try{
		client.sendToClient(op);
	}
	catch (IOException e)
	{
		e.printStackTrace();
	}
}
  private void getPurchasePlan(Op operation, ConnectionToClient client) {	
	Op op = new Op(Operations.ANS,db.getPurchasePlanByID((int)operation.getEntity()));
	try{
		client.sendToClient(op);
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	
}
  private void getUserTypeAsUser(Op operation, ConnectionToClient client) {
		User us;
		UserType ut;
		us = (User) operation.getEntity();
		ut = db.getUserTypeByUid(us.getUserTypeID());
		us.setUserType(ut);
		Op ans = new Op(Operations.ANS,us);
		try{
			client.sendToClient(ans);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	} 
 

private void logout(Op operation, ConnectionToClient client) {
	UserLogin logoutUser;
	int index=-1;
	logoutUser = (UserLogin) operation.getEntity();
	for(int i=0;i<LoggedInList.size();i++)
		if(logoutUser.getUserName().equals(LoggedInList.get(i).getUserName()))
		{
			index=i;
			break;
		}
	if(index!=-1)
	{
		LoggedInList.remove(index);
		System.out.println("Logout Complete!");
	}
}
/**
 * template to write server functions
 * @param operation
 * @param client
 * 
 */
private synchronized void test(Op operation, ConnectionToClient client)  {
	//LinkedList<HouseFuelInfo> t1;
	HouseFuelInfo t2;
	t2 =(HouseFuelInfo) operation.getEntity();
	//t1 = db.getAllHouseInfos();
	//Op op = new Op(Operations.ANS,t1);
	//db.DeleteSubByCid(t.getSubID());
	
	//try{
//		client.sendToClient(op);
//	}
	//catch(IOException e)
	//{
	//	e.printStackTrace();
	//}
}
/**
 * method to login a client to the system
 * querys the database for a match with username and password
 * which will return the perm level of the suer - ERROR if not register
 * and will update a arraylist of logged in users to the servers 
 * @param operation
 * @param client
 */

private synchronized void login(Op operation, ConnectionToClient client) {
	UserLogin ulog;
	User user = null;
	int index=-1;
	Operations logReqState;
	
	ulog = (UserLogin) operation.getEntity();
	logReqState = db.getLoginUser(ulog.getUserName(),ulog.getUserPass());
	if(logReqState == Operations.ALLOW_LOG)
	{
	for(int i=0;i<LoggedInList.size();i++)
		if(ulog.getUserName().equals(LoggedInList.get(i).getUserName()))
		{
			index=i;
			break;
		}
	if(index==-1)
	{
		LoggedInList.add(ulog);
		user=db.getUserByUname(ulog.getUserName());
	}
	else{
		logReqState=Operations.USER_ALREADY_LOGGED;
	}
	}
	
	Op oper = new Op(logReqState,user);
	try{
		client.sendToClient(oper);
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
}


/**
 * print to console that server started
 */

protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
/**
 * print to the console that server stopped
 */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  //////////////////////////////////////////ADDING DOWN////////////////////////////////////////////////////////////////////////////////
  
  private void getCustomer (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getCustomerInfoByID((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void getCustomerAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllCustomers());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}  
  }
  
  private void updateCustomer(Op operation, ConnectionToClient client) {
	  Customer PPid = (Customer) operation.getEntity();
	try{
	db.ChangeCustomerInfo(PPid);
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
}
  
  private void getInvoice (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getInvoiceInfo((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void getInvoiceAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllInvoices());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void updateInvoice(Op operation, ConnectionToClient client) {
	  Invoice PPid = (Invoice) operation.getEntity();
	try{
	db.changeInvoiceInfo(PPid);
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
}
  
  private void getOrder (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getOrderInfo((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		} 
  }
  
  private void getOrderAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllOrders());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void updateOrder (Op operation, ConnectionToClient client) {
  	Order PPid = (Order) operation.getEntity();
  	try{
  	db.changeOrderInfo(PPid);
  	}
  	catch(SQLException e)
  	{
  		e.printStackTrace();
  	}
  }
  
  private void getCarFuelOrder (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getCarFuelOrderInfo((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		} 
  }
  
  private void getCarFuelOrderAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllCarFuelOrders());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}  
  }
  
  private void updateCarFuelOrder (Op operation, ConnectionToClient client) {
  	CarFuelOrder PPid = (CarFuelOrder) operation.getEntity();
  	try{
  	db.changeCarFuelOrder(PPid);
  	}
  	catch(SQLException e)
  	{
  		e.printStackTrace();
  	}
  }
  
  private void getPaymentInfo (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getPaymentInfoByID((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		} 
  }
  
  private void getPaymentInfoAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllPayInfos());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}  
  }
  
  private void updatePaymentInfo (Op operation, ConnectionToClient client) {
  	PaymentInfo PPid = (PaymentInfo) operation.getEntity();
  	try{
  	db.ChangePaymentInfo(PPid); 
  	}
  	catch(SQLException e)
  	{
  		e.printStackTrace();
  	}
  }
  
  private void getHouseFuelOrder (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getHouseFuelOrderInfo((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void getHouseFuelOrderAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllHouseFuelOrders());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void updateHouseFuelOrder (Op operation, ConnectionToClient client) {
  	HouseFuelOrder PPid = (HouseFuelOrder) operation.getEntity();
  	try{
  	db.changeHouseFuelOrder(PPid);
  	}
  	catch(SQLException e)
  	{
  		e.printStackTrace();
  	}
  }
  
  private void getCarCustomer (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getCarCusByCcID((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void getCarCustomerAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllCarCuss());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void updateCarCustomer (Op operation, ConnectionToClient client) {
  	CarCustomer PPid = (CarCustomer) operation.getEntity();
  	try{
  	db.changeCarCustomerInfo(PPid);
  	}
  	catch(SQLException e)
  	{
  		e.printStackTrace();
  	}
  }
  
  private void getHouseOwner (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getHouseOwnerByID((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void getHouseOwnerAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllHouseOwners());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void updateHouseOwner (Op operation, ConnectionToClient client) {
  	HouseOwner PPid = (HouseOwner) operation.getEntity();
  	try{
  	db.changeHouseOwnerInfo(PPid);
  	}
  	catch(SQLException e)
  	{
  		e.printStackTrace();
  	}
  }
  
  private void getDiscountTable (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getDiscountByDid((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void getDiscountTableAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllDiscounts());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
   
  private void updateDiscountTable (Op operation, ConnectionToClient client) {
  	DiscountTable PPid = (DiscountTable) operation.getEntity();
  	try{
  	db.changeDiscont(PPid);  
  	}
  	catch(SQLException e)
  	{
  		e.printStackTrace();
  	}
  }
   
  private void getFuelStock (Op operation, ConnectionToClient client)
  {
	  	int[] idPair = (int[])operation.getEntity();
		Op op = new Op(Operations.ANS,db.getFuelStockByID( idPair[1], idPair[0]));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  
  private void getFuelStockAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllFuelStocks());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
   
  private void updateFuelStock (Op operation, ConnectionToClient client) {
	  FuelStock PPid = (FuelStock) operation.getEntity();
  	try{
  	db.ChangeFuelStockInfo(PPid); 
  	}
  	catch(SQLException e)
  	{
  		e.printStackTrace();
  	}
  }
  
  private void getFuelReplenish (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getReplenishByID((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void getFuelReplenishAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllReplenishes());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
   
  private void updateFuelReplenish (Op operation, ConnectionToClient client) {
	  FuelReplenish PPid = (FuelReplenish) operation.getEntity();
  	try{
  	db.changeFuelReplenish(PPid);
  	}
  	catch(SQLException e)
  	{
  		e.printStackTrace();
  	}
  }

   
  private void getReceipt (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getReceiptInfoByID((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void getReceiptAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllReceipts());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void updateReceipt (Op operation, ConnectionToClient client) {
	  Receipt PPid = (Receipt) operation.getEntity();
  	try{
  	db.ChangeReceiptInfo(PPid);
  	}
  	catch(SQLException e)
  	{
  		e.printStackTrace();
  	}
  }
  
  private void getCar (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getCarInfoByID((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void getCarAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllCars());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void updateCar (Op operation, ConnectionToClient client) {
	  Car PPid = (Car) operation.getEntity();
  	try{
  	db.changeCarInfo(PPid);
  	}
  	catch(SQLException e)
  	{
  		e.printStackTrace();
  	}
  }
  
  private void getSubscription (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getSubscriptionBySid((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void getSubscriptionAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllSubscriptions());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void updateSubscription (Op operation, ConnectionToClient client) {
	  Subscription PPid = (Subscription) operation.getEntity();
  	try{
  	db.changeSubInfo(PPid);
  	}
  	catch(SQLException e)
  	{
  		e.printStackTrace();
  	}
  }
  
  private void getMonthlySimple (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getMonthlySimpleByID((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  private void getMonthlySimpleAll (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getAllMonthlySimples());
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
   private void getGeneratedScoreReport (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getGeneratedScoreReportByID((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
 
  private void getDataPerCustomer (Op operation, ConnectionToClient client)
  {
		Op op = new Op(Operations.ANS,db.getDataPerCustomerTypeInfo((int)operation.getEntity()));
		try{
			client.sendToClient(op);
		}
		catch (IOException e){
			e.printStackTrace();
		}
  }
  
  

  
  
  public static void main(String[] args) 
  {
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    EchoServer sv = new EchoServer(port);
    //DBC.mySQLConnection();
    
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }


}
//End of EchoServer class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   