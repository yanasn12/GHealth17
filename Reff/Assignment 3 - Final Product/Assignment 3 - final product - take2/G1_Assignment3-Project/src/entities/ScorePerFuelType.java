
package entities;
 
import java.io.Serializable;

import controllers.FuelTypeControl;
import controllers.GeneratedReportControl;
 
/**
 * 
 * This class represents a Score Per Fuel Type depending on the amount of use
 *
 */
 
public class ScorePerFuelType implements Serializable {
     
    private static final long serialVersionUID = 1995499230462368239L;
    
    int scorePerFuelTypeID;
    int score;
    int fuelTypeID;
    FuelType fuelType;
    int generatedScoreReportID;
    GeneratedScoreReport generatedScoreReport;
     
    // constructor //

	public ScorePerFuelType(){
		generatedScoreReport = GeneratedReportControl.dummyGeneratedScoreReport;
    	fuelType = FuelTypeControl.dummyFuelType;
    }
     
    // setter and getter //
     
    public int getScore() {
        return score;
    }
 
    public void setScore(int score) {
        this.score = score;
    }
    
    public FuelType getFuelType (){
        if (fuelType == FuelTypeControl.dummyFuelType)
        	fuelType = FuelTypeControl.getFuelTypeById(fuelTypeID);
        return fuelType;
    }
    
    public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
		if (fuelType != FuelTypeControl.dummyFuelType)
			this.fuelTypeID=fuelType.getFuelTypeID();
	}

	public int getScorePerFuelTypeID() {
		return scorePerFuelTypeID;
	}

	public void setScorePerFuelTypeID(int scorePerFuelTypeID) {
		this.scorePerFuelTypeID = scorePerFuelTypeID;
	}

	public int getFuelTypeID() {
		return fuelTypeID;
	}

	public void setFuelTypeID(int fuelTypeID) {
		this.fuelTypeID = fuelTypeID;
	}
	
    public int getGeneratedScoreReportID() {
		return generatedScoreReportID;
	}

	public void setGeneratedScoreReportID(int generatedScoreReportID) {
		this.generatedScoreReportID = generatedScoreReportID;
	}
	/**
	 * 
	 * This method is getting a generatedScoreReport information.
	 * the method checks if the user generatedScoreReport information exists is a dummy reference
	 * if it is, get the generatedScoreReport information from the DataBase.
	 * 
	 * @return generatedScoreReport  the Score report is needed
	 */
	public GeneratedScoreReport getGeneratedScoreReport() {
		if (generatedScoreReport == GeneratedReportControl.dummyGeneratedScoreReport)
			generatedScoreReport = GeneratedReportControl.getGeneratedScoreReportById(generatedScoreReportID);
		return generatedScoreReport;
	}
	/**
	 * 
	 * This method is setting a generatedScoreReport information.
	 * the method checks if generatedScoreReport information exists is a dummy reference
	 * if it is, get the generatedScoreReport information from the DataBase and sets it in the generatedScoreReport reference
	 * 
	 * @param generatedScoreReport the Score report is needed
	 */
	public void setGeneratedScoreReport(GeneratedScoreReport generatedScoreReport) {
		this.generatedScoreReport = generatedScoreReport;
		if (generatedScoreReport != GeneratedReportControl.dummyGeneratedScoreReport)
			generatedScoreReportID = generatedScoreReport.getReportID();
	}
 
}