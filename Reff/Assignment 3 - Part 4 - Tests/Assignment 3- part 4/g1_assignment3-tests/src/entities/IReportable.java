package entities;

/**
 * Classes that implement this Interface return tables with report data in a string[][] format where the first row is always the col titles
 */
public interface IReportable {
	/**
	 * Returns the report table with all of the report information. First row is the col titles.
	 * @return String[][] report table.
	 */
	public String[][] getReportTable ();
	/**
	 * Returns the general report data which is a single row of values that related to the entire report table and not to a specific row within it
	 * First row is col titles, second is values. There are exactly 2 rows.
	 * @return String[][] general data row.
	 */
	public String[][]   getGeneralReportData();
}
