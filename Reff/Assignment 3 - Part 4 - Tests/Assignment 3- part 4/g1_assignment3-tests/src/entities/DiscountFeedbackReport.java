package entities;

import java.io.Serializable;
import controllers.PeriodicDiscountsControl;

/**
 * 
 *  Feedback Report for marketing operation. The information provided is the number of customers who purchased a particular operation
 *  
 */

public class DiscountFeedbackReport extends Report implements Serializable {
	
	private static final long serialVersionUID = -1856885389589448640L;
	
	int discountFeedbackReportID;
	int overallOrders;
	int periodicDiscountID;
	PeriodicDiscount periodicDiscount;


	
	public DiscountFeedbackReport(){
		periodicDiscount = PeriodicDiscountsControl.dummyPeriodicDiscount;
	}
	
	
	// setter getter //
	
	public int getOverallOrders() {
		return overallOrders;
	}

	public void setOverallOrders(int overallOrders) {
		this.overallOrders = overallOrders;
	}


	public int getDiscountFeedbackReportID() {
		return discountFeedbackReportID;
	}


	public void setDiscountFeedbackReportID(int discountFeedbackReportID) {
		this.discountFeedbackReportID = discountFeedbackReportID;
	}


	public int getPeriodicDiscountID() {
		return periodicDiscountID;
	}


	public void setPeriodicDiscountID(int periodicDiscountID) {
		this.periodicDiscountID = periodicDiscountID;
	}

	/**
	 * method to get the relevant periodic Discount for this discount Feedback Report.
	 * checks for the information thats already here, is it a dummy? if it is, gets the relevant information from the database
	 * if it isn't, returns the  information.
	 * @return
	 */
	public PeriodicDiscount getPeriodicDiscount() {
		if (periodicDiscount == PeriodicDiscountsControl.dummyPeriodicDiscount)
			periodicDiscount = PeriodicDiscountsControl.getPeriodicDiscountById(periodicDiscountID);
		return periodicDiscount;
	}
	/**
	 * method to set a periodic discount for the current periodic Discount Report.
	 * sets the periodic discount for the report and if its not a dummy sets the report id also.
	 * @param periodicDiscount
	 */
	public void setPeriodicDiscount(PeriodicDiscount periodicDiscount) {
		this.periodicDiscount = periodicDiscount;
		if (periodicDiscount != PeriodicDiscountsControl.dummyPeriodicDiscount)
		this.periodicDiscountID=periodicDiscount.getPeriodicDiscountID();
	}
	/**
	 * gets the report table in the shape of string matrix.
	 * 
	 */
	public String[][] getReportTable() {
		String[][] table = new String [2][1];
		
		table[0][0] = "Empty";
		table[1][0] = "";

		return table;
		
	}
	/**
	 * gets the peridoic Discount Report General Data. Table Representation
	 */
	public String [][] getGeneralReportData() {
		
		String[][] table = new String[2][5];
		
		table[0][0] = "PeriodicDiscountID";
		table[0][1] = "PeriodicDiscountTemplateID";
		table[0][2] = "Start Date";
		table[0][3] = "End Date";
		table[0][4] = "Order Count";
		
		table[1][0] = ""+periodicDiscountID;
		table[1][1] = ""+getPeriodicDiscount().getPeriodicDiscountTemplateID();
		table[1][2] = getPeriodicDiscount().getStartDate();
		table[1][3] = getPeriodicDiscount().getEndDate();
		table[1][4] = ""+overallOrders;
		
		return table;
	}
}
