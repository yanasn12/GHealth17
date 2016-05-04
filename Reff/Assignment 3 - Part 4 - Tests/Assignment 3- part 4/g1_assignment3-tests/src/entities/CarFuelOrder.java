package entities;

import java.io.Serializable;

import controllers.CustomerControl;
import controllers.WorkerControl;

/**
 * 
 * An order with info about the car which refuelled as well as it's driver name and station where it happened.
 *
 */

public class CarFuelOrder extends Order implements Serializable{

	private static final long serialVersionUID = 422144264153150563L;

	String driverName;
	int stationID;
	Station station;
	int carID;
	Car car;
	
	// Constructor //
	/**
	 * Creates an empty CarCustomer and sets its refs to dummy refs.
	 */
	public CarFuelOrder(){
		station = WorkerControl.dummyStation;
		car = CustomerControl.dummyCar;
	}
	
	//setter and getter //

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	/**
	 * returns the Station where this order was made, fetches that info from the DB if the need arises.
	 * @return Station related to this class.
	 */
	public Station getStation() {
		if (station == WorkerControl.dummyStation)
			station = WorkerControl.getStationById(stationID);
		return station;
	}

	/**
	 * sets this order's purchase Station to the given Station and updates the stationID with the new value
	 * @param Station
	 */
	public void setStation(Station station) {
		this.station = station;
		if (station != WorkerControl.dummyStation)
			this.stationID = station.getStationID();
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}
	
	/**
	 * returns the Car that made the order, fetches that info from the DB if the need arises.
	 * @return Car related to this class.
	 */
	public Car getCar() {
		if (car == CustomerControl.dummyCar)
			car = CustomerControl.getCarById(carID);
		return car;
	}

	/**
	 * sets this order's Car to the given Car and updates the carID with the new value
	 * @param car
	 */
	public void setCar(Car car) {
		this.car = car;
		if (car != CustomerControl.dummyCar)
			this.carID = car.getCarID();
	}

	public int getCarFuelOrderID() {
		return orderID;
	}

	public void setCarFuelOrderID(int carFuelOrderID) {
		this.orderID=carFuelOrderID;
	}
	
	/**
	 * Calculates the overall price of this order after applying the discounts.
	 */
	public void calcPrice ()
	{
		double price = getFuelType().getBasePrice() * quantity;
		if (getCar().getCarCustomer().getPurchasePlan() != null)
			price = price - price * getCar().getCarCustomer().getPurchasePlan().getDiscount();
		if (getCar().getSubscription() != null)
			price = getCar().getSubscription().applyDiscount(price);
		if (periodicDiscountID > 0 && getPeriodicDiscount() != null)
			price = getPeriodicDiscount().applyDiscount(this,price);
		
		price = ((int) ((price+0.005) * 100))/100.0; // rounding the number
		this.price = price;
	}
	
	public String fToString (){
		
		return new String (toString()+"\nCarFuelOrderID:"+ getCarFuelOrderID()+"\nDriverName:"+ getDriverName()+"\nCarID:"+ getCarID() );	
	}
	

}
