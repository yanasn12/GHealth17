package GUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import entities.PeriodicCustomerReport;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;
/**
 * Reports Menu Screen, a Menu generated to the user and allows him to pick which report he wants to generate.
 * the menu also contains a "View Report" button that allows him to see the reports by inserting the id of the report.
 *
 */
public class ReportsMenuGUI extends JFrame {
	private static final long serialVersionUID = -8761261275580185237L;
	private JFrame prevScreen;
	private JFrame mainframe;
	public ReportsMenuGUI(JFrame prevScreen) {
		getContentPane().setBackground(new Color(0, 102, 102));
		this.prevScreen = prevScreen;
		this.mainframe=this;
		setTitle("Report Options");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 102));
		panel.setBounds(10, 11, 464, 164);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnPurchase = new JButton("Purchases");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ReportGUI(5,mainframe);
				mainframe.setVisible(false);
			}
		});
		btnPurchase.setBounds(169, 90, 130, 50);
		panel.add(btnPurchase);
		
		JButton btnStock = new JButton("Stock");
		btnStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ReportGUI(6,mainframe);
				mainframe.setVisible(false);
			}
		});
		btnStock.setBounds(309, 90, 145, 50);
		panel.add(btnStock);
		
		JButton btnIncome = new JButton("Income");
		btnIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ReportGUI(3,mainframe);
				mainframe.setVisible(false);
			}
		});
		btnIncome.setBounds(309, 11, 145, 50);
		panel.add(btnIncome);
		
		
		JButton btnPeriodiccustomer = new JButton("PeriodicCustomer");
		btnPeriodiccustomer.setBounds(10, 90, 149, 50);
		panel.add(btnPeriodiccustomer);
		
		JButton btnGeneratedscore = new JButton("GeneratedScore");
		btnGeneratedscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ReportGUI(2,mainframe);
				mainframe.setVisible(false);
			}
		});
		btnGeneratedscore.setBounds(169, 11, 130, 50);
		panel.add(btnGeneratedscore);
		
		JButton btnNewButton = new JButton("DiscountFeedback ");
		btnNewButton.setBounds(10, 11, 149, 50);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReportGUI(1,mainframe);
				mainframe.setVisible(false);
			}
		});
		btnPeriodiccustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PeriodicCustomerReport c= new PeriodicCustomerReport();
				new ReportGUI(4,mainframe);
				mainframe.setVisible(false);
			}
		});
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPrevScreen().setVisible(true);
				dispose();
				
			}
		});
		btnReturn.setBounds(338, 198, 105, 30);
		getContentPane().add(btnReturn);
		
		JButton btnViewReports = new JButton("View Reports");
		btnViewReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new OpenReportGUI(mainframe);
				mainframe.setVisible(false);
			}
		});
		btnViewReports.setBounds(39, 198, 105, 30);
		getContentPane().add(btnViewReports);
		this.setSize(500, 288);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}
	public JFrame getPrevScreen() {
		return prevScreen;
	}
}
