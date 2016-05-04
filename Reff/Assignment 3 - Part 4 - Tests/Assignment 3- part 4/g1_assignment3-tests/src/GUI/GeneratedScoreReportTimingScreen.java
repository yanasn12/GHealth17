package GUI;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import controllers.CustomerControl;
import controllers.FuelTypeControl;
import controllers.GeneratedReportControl;
import controllers.OrderControl;
import controllers.PeriodicDiscountsControl;
import entities.DataPerCustomerType;
import entities.FuelType;
import entities.GeneratedScoreReport;
import entities.Order;
import entities.ScorePerFuelType;
import entities.UserType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;
/**
 * this window generates Report that ranks the various Customer Types by number of orders as well as fuel types by number of orders
 * Scores are Between 0 and 10.
 * this Screen can be replaced with a different analitical system to produce different score results.
 * 
 */
public class GeneratedScoreReportTimingScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JFrame prevScreen;
	GeneratedScoreReport gcr;
	long startValue;
	long endValue;
	long ordertime;
	public GeneratedScoreReportTimingScreen(JFrame prevScreen, GeneratedScoreReport report) {
		this.prevScreen=prevScreen;
		this.gcr=report;
		setTitle("Generated Report Screen");
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(50, 49, 71, 20);
		getContentPane().add(lblStartDate);
		
		textField = new JTextField();
		textField.setBounds(120, 49, 211, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(50, 104, 71, 20);
		getContentPane().add(lblEndDate);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(120, 104, 211, 20);
		getContentPane().add(textField_1);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPrevScreen().setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(32, 186, 97, 30);
		getContentPane().add(btnReturn);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				 int max;
				 int cnt=0;
				 int nextScorePerFuelTypeID;
				String startTime=new String(textField.getText());
				String endTime=new String(textField_1.getText());
				if(PeriodicDiscountsControl.checkDate(startTime)==false||PeriodicDiscountsControl.checkDate(endTime)==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid Input For Date");
					return;
				}
				int [] ordersCnt;
				
				 nextScorePerFuelTypeID = 0;
				for (ScorePerFuelType d : GeneratedReportControl.getAllScoresPerFuelType())
				{
					if (d.getScorePerFuelTypeID() > nextScorePerFuelTypeID)
						nextScorePerFuelTypeID = d.getScorePerFuelTypeID();
				}
				nextScorePerFuelTypeID++;
				try{
					 startValue=PeriodicDiscountsControl.getDateNumericValue(startTime);
					 endValue=PeriodicDiscountsControl.getDateNumericValue(endTime);
					}
					catch(StringIndexOutOfBoundsException e1)
					{
						JOptionPane.showMessageDialog(null, "Wrong Date Input!");
						return;
					}
			GeneratedReportControl.createGeneratedScoreReport(gcr);
			List<FuelType> fuelTypes = FuelTypeControl.getAllFuelTypes();	
			ordersCnt=new int [fuelTypes.size()];
			for(FuelType f : fuelTypes)
			{
				
				for(Order or: OrderControl.getAllOrders())
				{
					ordertime=PeriodicDiscountsControl.getDateNumericValue(or.getOrderTime().substring(0,10));
					if((ordertime>=startValue&&ordertime<=endValue)&&or.getFuelTypeID()==f.getFuelTypeID())
					{
						ordersCnt[cnt]++;
					}
					
				}
				cnt++;
			}
			
			max = -1;
			for(int i=0;i<ordersCnt.length;i++)
			{
				if (ordersCnt[i] > max)
					max = ordersCnt[i];
			}
			
			ScorePerFuelType currScore;	
			int i = 0;
			for (FuelType type : fuelTypes)
			{
				currScore = new ScorePerFuelType ();
				currScore.setScorePerFuelTypeID(nextScorePerFuelTypeID);
				currScore.setFuelTypeID(type.getFuelTypeID());
				currScore.setGeneratedScoreReportID(gcr.getReportID());
				currScore.setScore((int)((ordersCnt[i]/(double)max)*10));
				GeneratedReportControl.createScorePerFuelType(currScore);
				nextScorePerFuelTypeID++;
				i++;
			}
			
			
			LinkedList<UserType> customerTypes = new LinkedList<UserType> ();
			for (UserType userType : CustomerControl.getAllUserTypes())
			{
				if (userType.isCustomer())
					customerTypes.add(userType);
			}
			int[] orderCnt = new int[customerTypes.size()];
			int[][] hourCnt = new int[customerTypes.size()][24];
			
			i = 0;
			
			for(UserType custType : customerTypes)
			{
				for(Order or: OrderControl.getAllOrders())
				{
					ordertime=PeriodicDiscountsControl.getDateNumericValue(or.getOrderTime().substring(0,10));
					if((ordertime>=startValue&&ordertime<=endValue)&&(or.getInvoice().getCustomer().getUserTypeID()==custType.getUserTypeID()))
					{
						int hour = Integer.parseInt(or.getOrderTime().substring(11, 13));
						orderCnt[i]++;
						hourCnt[i][hour]++;
					}
				}
				i++;
			}
			
			max = 0;
			int[] hourMax = new int[24];
			for (i = 0; i < orderCnt.length; i++)
			{
				if (orderCnt[i]>max)
					max = orderCnt[i];
				for (int j = 0; j < 24; j++)
				{
					if (hourMax[j] < hourCnt[i][j])
						hourMax[j]  = hourCnt[i][j];
				}
			}
			
			DataPerCustomerType data;
			int maxDataPerCustomerTypeID = 0;
			for (DataPerCustomerType d : GeneratedReportControl.getAllDatasPerCustomerType())
			{
				if (d.getDataPerCustomerTypeID() > maxDataPerCustomerTypeID)
					maxDataPerCustomerTypeID = d.getDataPerCustomerTypeID();
			}
			
			maxDataPerCustomerTypeID++;
			int[] hourScore;
			i = 0;
			for (UserType type : customerTypes)
			{
				data = new DataPerCustomerType ();
				data.setDataPerCustomerTypeID(maxDataPerCustomerTypeID);
				data.setGeneratedScoreReportID(gcr.getReportID());
				data.setUserTypeID(type.getUserTypeID());
				data.setScore((int)((orderCnt[i]/(double)max)*10));
				hourScore = new int[24];
				for (int j =0; j < 24; j++)
				{
					hourScore[j] = (int)((hourCnt[i][j]/(double)hourMax[j])*10);
				}
				data.setHourScore(hourScore);
				
				GeneratedReportControl.createDataPerCustomerType(data);			
				maxDataPerCustomerTypeID++;
				i++;
			}
			JOptionPane.showMessageDialog(null,"Generated Report Successfully Generated!ReportID:"+gcr.getReportID());
			getPrevScreen().setVisible(true);
			dispose();
		}
			
			
		});
		btnGenerate.setBounds(316, 186, 97, 30);
		getContentPane().add(btnGenerate);
		
		setSize (445,295);
		setVisible(true);
	}
	public JFrame getPrevScreen() {
		return this.prevScreen;
	}
}
