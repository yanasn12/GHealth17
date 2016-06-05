package GUI_client;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ClinicManegementRrports extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopReports;
	private JButton button_1;
	private MonthlyReports Rep;
	public WeeklyReport WeekRep;
	private JButton button;
	private Login login;
	private JButton btnLogout;
	
	//////////////////private MonthlyReports Rep;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClinicManegementRrports frame = new ClinicManegementRrports();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClinicManegementRrports() {
		initComponent(); //// יצרתי את INIT שבו יש את כל איתחול המרכיבים
		createEvents1();/// הפעלת האירוע- הצגת המסך
		createEvents2();
		createEvents3();  // חזרת ל LOGIN
	}
	///////// init component of desktop reports
	private void initComponent() {
		//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setBounds(0, 0, 810, 775);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ClinicManegementRrports.class.getResource("/javagui/resources/GHealthlogo.png")));
		label.setBounds(113, 25, 242, 68);
		contentPane.add(label);
		
		button = new JButton("View weekly reports");
		
		
		
		button.setIcon(new ImageIcon(ClinicManegementRrports.class.getResource("/javagui/resources/img16x16/reports.png")));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setForeground(Color.BLUE);
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(Color.WHITE);
		button.setBounds(10, 130, 192, 39);
		contentPane.add(button);
		
		button_1 = new JButton("View monthiy report ");
		
		button_1.setIcon(new ImageIcon(ClinicManegementRrports.class.getResource("/javagui/resources/img16x16/reports.png")));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setForeground(Color.BLUE);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(10, 183, 192, 39);
		contentPane.add(button_1);
		
		desktopReports = new JDesktopPane();
		desktopReports.setBackground(Color.WHITE);
		desktopReports.setBounds(212, 133, 621, 596);
		contentPane.add(desktopReports);
		desktopReports.setLayout(null);
		
		btnLogout = new JButton("LogOut");////////////////////////////////////////////////////////////////
		
		
	
		btnLogout.setBounds(10, 685, 117, 32);
		contentPane.add(btnLogout);
		
	}
	
	
	//display  monthly report
	private void createEvents1() {
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (WeekRep != null )
					WeekRep.dispose();/// close week report if open
				if (Rep == null || Rep.isClosed())
					Rep = new MonthlyReports();  ////  לא לשכוח להצהיר על המשתנה כפרטי במחלקה 
					desktopReports.add(Rep);/// הצגה על המסך הקטן
					Rep.show(); // איפשור תצוגה
			
					Rep.button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Rep.dispose();//close whene pressed back
						}
					});
			}
		});
	}

	//for weekly report
	private void createEvents2() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Rep != null)
					Rep.dispose();/// close month report if open
				if (WeekRep == null || WeekRep.isClosed())
					WeekRep = new WeeklyReport();  ////  לא לשכוח להצהיר על המשתנה כפרטי במחלקה 
					desktopReports.add(WeekRep);/// הצגה על המסך הקטן
					WeekRep.show(); // איפשור תצוגה
					
					WeekRep.button.addActionListener(new ActionListener() { // for back-- not must
						public void actionPerformed(ActionEvent e) {
							WeekRep.dispose();//close whene pressed back
							 //
						}
					});
					
			}
		});
		
	}
	
	private void createEvents3() {
	btnLogout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			login = new Login();
			login.typeOfWorker=2;
			login.setVisible(true);
		}
	});
	}
}