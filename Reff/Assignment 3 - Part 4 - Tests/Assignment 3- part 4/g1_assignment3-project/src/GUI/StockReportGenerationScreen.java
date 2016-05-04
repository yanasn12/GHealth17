package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import controllers.LoginCont;
import controllers.StockControl;
import controllers.StockReportControl;
import entities.AmountDataPerFuel;
import entities.FuelStock;
import entities.StockReport;
import entities.Worker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
/**
 * Stock Report timing screen, collects information about every Fuel stock in the station and creates an AmountDataPerFuel Class
 * for every fueltype in the Stock which contains the amount of the fuel in every stock and creates a stock report.
 * all the report info is presented with the View Report button  and displays this information.
 * 
 *
 */
public class StockReportGenerationScreen extends JFrame {
	private static final long serialVersionUID = -747952778419715451L;
	private StockReport stockrep;
	private JFrame preScreen;
	public StockReportGenerationScreen(JFrame prevScreen, StockReport report) {
		getContentPane().setBackground(new Color(169, 169, 169));
		setTitle("Stock Report Generation");
		getContentPane().setLayout(null);
		this.stockrep=report;
		this.preScreen=prevScreen;
		JLabel lblGenerateStockReport = new JLabel("Generate Stock Report?");
		lblGenerateStockReport.setBounds(149, 11, 123, 39);
		getContentPane().add(lblGenerateStockReport);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPrevScreen().setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(24, 81, 103, 32);
		getContentPane().add(btnReturn);
		
		JButton button = new JButton("Generate");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Worker w= (Worker) LoginCont.getCurrUser();
				int nextAmountDataPerFuelID=0;
				AmountDataPerFuel fuelData;
				stockrep.setStationID(w.getLocationID());
				StockReportControl.createStockReport(stockrep);
				for (AmountDataPerFuel d :StockReportControl.getAllAmountDatasPerFuel() )
				{
					if (d.getAmountDataPerFuelID() > nextAmountDataPerFuelID)
						nextAmountDataPerFuelID = d.getAmountDataPerFuelID();
				}
				nextAmountDataPerFuelID++;
				for(FuelStock stock : StockControl.getAllFuelStocks())
				{
					fuelData=new AmountDataPerFuel();
					
					if(stock.getStationID()==w.getLocationID())
					{
						fuelData.setAmountDataPerFuelID(nextAmountDataPerFuelID);
						fuelData.setFuelTypeID(stock.getFuelTypeID());
						fuelData.setAmount(stock.getStockAmount());
						fuelData.setReportID(stockrep.getReportID());
						nextAmountDataPerFuelID++;
						StockReportControl.createAmountDataPerFuel(fuelData);
					}
					
					
				}
					
				JOptionPane.showMessageDialog(null,"Generate Successfull! ReportID:"+stockrep.getReportID());
				getPrevScreen().setVisible(true);
				dispose();
			}
			
		});
		this.setSize(449, 168);
		button.setBounds(308, 81, 103, 32);
		getContentPane().add(button);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	public JFrame getPrevScreen() {
		return this.preScreen;
	}
	
	

}
