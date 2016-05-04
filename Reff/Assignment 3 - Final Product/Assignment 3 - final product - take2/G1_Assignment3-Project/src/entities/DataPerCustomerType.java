package entities;

import java.io.Serializable;

import controllers.CustomerControl;
import controllers.GeneratedReportControl;

/**
 * 
 * This class stores Customer Type for class - Generated Score Report
 * using this class an information about every customer type is available and is used in the GeneratedScoreReport.
 * this data is presented in the view Report part of the Generated report.
 */

public class DataPerCustomerType implements Serializable {
	
	private static final long serialVersionUID = 7242007003956517735L;

	int dataPerCustomerTypeID;
	int score;
	int[] hourScore;
	int userTypeID;
	UserType userType;
	int generatedScoreReportID;
	GeneratedScoreReport generatedScoreReport;
	
	// Constructor //
	
	public DataPerCustomerType (){
		userType = CustomerControl.dummyUserType;
		generatedScoreReport = GeneratedReportControl.dummyGeneratedScoreReport;
	}
	
	// setters and getters //
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int[] getHourScore() {
		return hourScore;
	}
	public void setHourScore(int[] hourScore) {
		this.hourScore = hourScore;
	}

	public int getUserTypeID() {
		return userTypeID;
	}

	public void setUserTypeID(int userTypeID) {
		this.userTypeID = userTypeID;
	}
	/**
	 * method to get the userType information for that customer type,
	 * checks if the usertype info is a dummy reference and if its not it returns the usertype information as a class.
	 * efficient to prevent multiple database access
	 * @return
	 */
	public UserType getUserType() {
		if (userType == CustomerControl.dummyUserType)
			userType = CustomerControl.getUserTypeById(userTypeID);
		return userType;
	}
	/**
	 * method to set a UserType information for that CustomerType.
	 * method checks that the Information stored for that customer type isnt a dummy reference and if it isnt, sets the correct reference.
	 * @param userType
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
		if (userType != CustomerControl.dummyUserType)
			this.userTypeID=userType.getUserTypeID();
	}

	public int getDataPerCustomerTypeID() {
		return dataPerCustomerTypeID;
	}

	public void setDataPerCustomerTypeID(int dataPerCustomerTypeID) {
		this.dataPerCustomerTypeID = dataPerCustomerTypeID;
	}

	public int getGeneratedScoreReportID() {
		return generatedScoreReportID;
	}

	public void setGeneratedScoreReportID(int generatedScoreReportID) {
		this.generatedScoreReportID = generatedScoreReportID;
	}
	/**
	 * method to get the Generated Score Report that is related to the information saved for the current customer.
	 * method checks for the report and if its not a dummy referece then the report is returned.
	 * efficient to prevent multiple database access.
	 * @return
	 */
	public GeneratedScoreReport getGeneratedScoreReport() {
		if (generatedScoreReport == GeneratedReportControl.dummyGeneratedScoreReport)
			generatedScoreReport = GeneratedReportControl.getGeneratedScoreReportById(generatedScoreReportID);
		return generatedScoreReport;
	}
	/**
	 * method to set a Generated ScoreReport for that DataPerCustomerType.
	 * method sets the report to the data and checks if the generated report given isnt a dummy reference.
	 * if it isnt, the method sets the report that relates to the data.
	 * @param generatedScoreReport
	 */
	public void setGeneratedScoreReport(GeneratedScoreReport generatedScoreReport) {
		this.generatedScoreReport = generatedScoreReport;
		if (generatedScoreReport != GeneratedReportControl.dummyGeneratedScoreReport)
			generatedScoreReportID = generatedScoreReport.getGeneratedScoreReportID();
	}
	
}
