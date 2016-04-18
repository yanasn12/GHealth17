package common;

import java.io.Serializable;

public class fuel implements Serializable{

	private static final long serialVersionUID = 5429339835049116228L;
	private int id;
	private String name;
	private int price;
	
	public fuel(int id, String name, int price) {
		this.setId(id);
		this.setName(name);
		this.setPrice(price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString()
	{
		String str = null;
		str = "Fuel_id:"+id+" Fuel_name:"+name+" Fuel_price:"+price;
		return str;
	}

}
