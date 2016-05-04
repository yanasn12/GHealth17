package entities;

import java.io.Serializable;

import controllers.FuelTypeControl;
import controllers.PurchasesReportControl;
import controllers.StockReportControl;

/**
 *  Represents a line in a report table where for each FuelType an amount is given. The amount may represent various things depending on context, for example amount of fuel stock left or amonut of fuel bought
 * 
 */
public class AmountDataPerFuel implements Serializable {
	
	private static final long serialVersionUID = 455116888319073325L;
	
	int amountDataPerFuelID;
	int amount;
	int fuelTypeID;
	FuelType fuelType;
	PurchasesReport purchaseReport;
	StockReport stockReport;
	int reportID;
	/**
	 * Creates an empty AmountDataPerFuel and sets its refs to dummy refs.
	 */
	public AmountDataPerFuel(){
		fuelType = FuelTypeControl.dummyFuelType;
		purchaseReport = PurchasesReportControl.dummyPurchaseReport;
		stockReport = StockReportControl.dummyStockReport;
	}
	
	// setter and getter //

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getFuelTypeID() {
		return fuelTypeID;
	}

	public void setFuelTypeID(int fuelTypeID) {
		this.fuelTypeID = fuelTypeID;
	}

	/**
	 * returns FuelType of this report, fetches that info from the DB if the need arises.
	 * @return FuelType related to this report.
	 */
	public FuelType getFuelType() {
		if (fuelType == FuelTypeControl.dummyFuelType)
			fuelType = FuelTypeControl.getFuelTypeById(fuelTypeID);
		return fuelType;
	}

	/**
	 * sets this class's fuelType to the given FuelType and updates the fuelTypeID with the new value
	 * @param fuelType
	 */
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
		if (fuelType != FuelTypeControl.dummyFuelType)
			this.fuelTypeID = fuelType.getFuelTypeID();
	}

	public int getPurchaseReportID() {
		return reportID;
	}

	public void setPurchaseReportID(int purchaseReportID) {
		this.reportID = purchaseReportID;
	}
	
	/**
	 * returns PurchasesReport of this report, fetches that info from the DB if the need arises.
	 * @return PurchasesReport related to this report.
	 */
	public PurchasesReport getPurchaseReport() {
		if (purchaseReport == PurchasesReportControl.dummyPurchaseReport)
			purchaseReport = PurchasesReportControl.getPurchasesReportById(reportID);
		return purchaseReport;
	}
	
	/**
	 * sets this class's PurchasesReport to the given FuelType and updates the purchasesReportID with the new value
	 * @param PurchasesReport
	 */
	public void setPurchaseReport(PurchasesReport purchaseReport) {
		this.purchaseReport = purchaseReport;
		if (purchaseReport != PurchasesReportControl.dummyPurchaseReport)
			this.reportID = purchaseReport.getReportID ();
	}

	public int getStockReportID() {
		return reportID;
	}

	public void setStockReportID(int stockReportID) {
		this.reportID = stockReportID;
	}
	
	public int getReportID()
	{
		return this.reportID;
	}
	
	public void setReportID(int id)
	{
		this.reportID=id;
	}

	/**
	 * returns StockReport of this report, fetches that info from the DB if the need arises.
	 * @return StockReport related to this report.
	 */
	public StockReport getStockReport() {
		if (stockReport == StockReportControl.dummyStockReport)
			stockReport = StockReportControl.getStockReportById(reportID);
		return stockReport;
	}

	/**
	 * sets this class's StockReport to the given FuelType and updates the stockReportID with the new value
	 * @param StockReport
	 */
	public void setStockReport(StockReport stockReport) {
		this.stockReport = stockReport;
		if (stockReport != StockReportControl.dummyStockReport)
			this.reportID = stockReport.getReportID();
	}

	 public int getAmountDataPerFuelID() {
		return amountDataPerFuelID;
     }

	 public void setAmountDataPerFuelID(int amountDataPerFuelID) {
		this.amountDataPerFuelID = amountDataPerFuelID;
	 }

}
