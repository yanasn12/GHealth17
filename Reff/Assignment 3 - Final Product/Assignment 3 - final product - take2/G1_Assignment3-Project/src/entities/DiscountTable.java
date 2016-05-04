package entities;

import java.io.Serializable;

/**
 * 
 * This class represents a Discount Table
 *
 */

public class DiscountTable implements Serializable {
	
	private static final long serialVersionUID = 4563381490560442827L;
	int discountTableID;
	double[] discountTable;
	double[] discountTableChanges;
	String discounts;
	String discount_change;



	//setters and getters //
	
	public int getDiscountTableID() {
		return discountTableID;
	}
	public void setDiscountTableID(int discountTableID) {
		this.discountTableID = discountTableID;
	}
	public double[] getDiscountTable() {
		return discountTable;
	}
	public void setDiscountTable(double[] discountTable) {
		this.discountTable = discountTable;
	}
	public double[] getDiscountTableChanges() {
		return discountTableChanges;
	}
	public void setDiscountTableChanges(double[] discountTableChanges) {
		this.discountTableChanges = discountTableChanges;
	}
	public String getDiscounts()
	{
		return this.discounts;	
	}
	public void setDiscounts(String discounts)
	{
		this.discounts=discounts;
	}
	public String getDiscountChanges()
	{
		return this.discount_change;
	}
	public void setDiscTableChanged(String changed)
	{
		this.discount_change=changed;
	}
	/*
	  public  double[] fromStrToDisc(String discount)
	{
		String[] split = discount.split("|");
		double[] ret = new double[split.length];
		for(int i=0;i<split.length;i++)
			ret[i]=(Double.parseDouble(split[i])/100.0);
		return ret;
	}
	public String DoubleToStr(double[] Arr)
	{
		int temp,i;
		String ret = new String();
		for(i=0;i<Arr.length-1;i++)
		{
			temp = (int)(Arr[i]*100);
			ret+=temp+"|";
		}
		temp = (int)(Arr[i]*100);
		ret+=temp;
		return ret;
	}
	 */
		/**
	 * method that receives a string and returns a Double Array of variables that are the discounts.
	 * @param discount
	 * @return
	 */
	public  double[] fromStrToDisc(String discount)
	{
		int size;
		discount = discount.trim();
		String temp;
		size=discount.length();
		double[] discs=new double[size/3];
		int i=0,j=2,t=0;
		
		while(j<=(size))
		{
			temp=discount.substring(i, j);
			discs[t]=Double.parseDouble(temp);
			discs[t]/=100.0;
			i+=3;
			j+=3;
			t++;
			
		}
		return discs;
	}
	/**
	 * method to convert from a double array to a String in order to store the Discounts info in the Database.
	 * @param Arr
	 * @return
	 */
	public String DoubleToStr(double[] Arr)
	{
		String str = new String();
		String temp;
		int size,i,num;
		size=Arr.length;
		for(i=0;i<size;i++)
		{
			num=(int)(Arr[i]*100);
			temp=Integer.toString(num);
			if(num<10)
				str=str+"0"+temp;
			else
				str=str+temp;
			str+="|";
		}
		
		return str;
	}
	public double ArrayI(double[] arr,int i)
	{
		return arr[i];
	}
	
	/**
	 * Returns the discount at the given discountTable index.
	 * @param i index of the discount.
	 * @return actual discount value.
	 */
	public double getDiscountAt (int i)
	{
		return discountTable[i];
	}
	
	/**
	 * Approves the discountTableChange at the given index and turns it into the active discountChange.
	 * @param i index of the discount.
	 */
	public void approveDiscountAt (int i)
	{
		discountTable[i] = discountTableChanges[i];
	}
	
	public String toString()
	{
		return new String("discountTbaleID:"+getDiscountTableID()+"Discounts:"+ArrayI(discountTable,0)+ArrayI(discountTable,1)+ArrayI(discountTable,2)+ArrayI(discountTable,3)+"DiscountChanges:"+ArrayI(discountTableChanges,0)+ArrayI(discountTableChanges,1)+ArrayI(discountTableChanges,2)+ArrayI(discountTableChanges,3));
		
	}
}
	



