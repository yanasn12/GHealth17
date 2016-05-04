package Fixtures;

import java.util.LinkedList;

import client.ChatClient;
import controllers.CarFuelOrderControl;
import entities.CarFuelOrder;
import entities.FuelStock;
import fit.ActionFixture;

public class AddCarFuelOrder extends ActionFixture {
	CarFuelOrder tcfo = new CarFuelOrder();
	//CarFuelOrder tcfo1 = null;
	LinkedList <FuelStock> tsc ;
	FuelStock fst = new FuelStock();
	int CarFuelOrderID;
	int CarId;
	double Quantity;
	String DriverName;
	@SuppressWarnings("unused")
	private ChatClient client;
	
	public void startCarFuelOrder() {
		tcfo = new CarFuelOrder();
		
		//this.client  = LocalClient.setUp();
	}

	public void carFuelOrderCarID(int carID) {
		tcfo.setCarID(carID);
	}

	public void carFuelOrderQuantity(double quantity) {
		tcfo.setQuantity(quantity);
	}
	
	public void carFuelOrderName(String driverName) {
		tcfo.setDriverName(driverName);
	}
	
	public boolean CheckCarFuelOrder() {	
		LocalClient client  = new LocalClient();
		client.setUp("GET_CAR_FUEL_ORDER", tcfo);
		return (boolean)client.getRes();
		
		
		//tcfo1=CarFuelOrderControl.generateNewCarFuelOrder(0, tcfo.getCarID(), tcfo.getQuantity(), tcfo.getDriverName());

		//if (tcfo1 == null)
		//	return false;
		//else 
		//	return true;
	}
}
