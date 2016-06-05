package GUI_client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class GeneralManegementReport extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopReports;
	private JButton button_1;
	private TypeOfMonthlyReports Rep;
	private WeeklyReport weekRep;
	private JButton button;
	//////////////////private MonthlyReports Rep;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneralManegementReport frame = new GeneralManegementReport();
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
	public GeneralManegementReport() {
		initComponent(); //// יצרתי את INIT שבו יש את כל איתחול המרכיבים
		createEvent1();/// הפעלת האירוע- הצגת המסך
		//createEvent2();
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
		label.setIcon(new ImageIcon(GeneralManegementReport.class.getResource("/javagui/resources/GHealthlogo.png")));
		label.setBounds(113, 25, 242, 68);
		contentPane.add(label);
		
		button = new JButton("View weekly reports");
		
		button.setIcon(new ImageIcon(GeneralManegementReport.class.getResource("/javagui/resources/img16x16/reports.png")));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setForeground(Color.BLUE);
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(Color.BLUE);
		button.setBounds(10, 130, 192, 39);
		contentPane.add(button);
		
		button_1 = new JButton("View monthiy report ");
		
		button_1.setIcon(new ImageIcon(GeneralManegementReport.class.getResource("/javagui/resources/img16x16/reports.png")));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setForeground(Color.BLUE);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_1.setBackground(Color.BLUE);
		button_1.setBounds(10, 183, 192, 39);
		contentPane.add(button_1);
		
		desktopReports = new JDesktopPane();
		desktopReports.setBackground(Color.WHITE);
		desktopReports.setBounds(212, 133, 572, 539);
		contentPane.add(desktopReports);
		desktopReports.setLayout(null);
	}
	
	//display the type of monthly report
	private void createEvent1() {
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Rep == null || Rep.isClosed())/////////
				Rep = new TypeOfMonthlyReports();  ////  לא לשכוח להצהיר על המשתנה כפרטי במחלקה 
				desktopReports.add(Rep);/// הצגה על המסך הקטן
				Rep.show(); // איפשור תצוגה
				
			}
		});
	}

	/// display weekly report
	/*private void createEvent2() {
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			weekRep = new WeeklyReport ();
			desktopReports.add(weekRep);
			weekRep.e
			setContentPane(weekRep);/// הצגה על המסך הקטן	
			
			//////////////////////////////////////////////////////////////////////////////
		}
	});
}
}*/
}
