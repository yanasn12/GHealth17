package GUI_client;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class TypeOfMonthlyReports extends JInternalFrame {

		private CurrReport CurrRep;//// object of current
		private JButton button;
		private NMonthReport NRep;
		private JButton btnNLastMonth;
		private PeriodReport PerRep;
		private JButton btnPeriodReport;
		

	/**
	 * Create the frame.
	 */
		public JPanel FirstPanel = null;
	private JLabel lblSelectTheDesired;
		
		
	public TypeOfMonthlyReports() {
		super();
		initComponent();
	}

	private JPanel getFirstPanel(){
		
		if(FirstPanel == null){
			FirstPanel = new JPanel();
			FirstPanel.setLayout(null);
			
			JTextArea textArea = new JTextArea();
			textArea.setBounds(32, 23, 189, 26);
			getContentPane().add(textArea);
			textArea.setText("Select the desired report :");
			textArea.setForeground(new Color(0, 128, 0));
			textArea.setFont(new Font("Tahoma", Font.BOLD, 14));
			textArea.setBackground(SystemColor.menu);
			//FirstPanel.add(textArea);
			
			button = new JButton("Current Monthly Report");
			button.setBounds(52, 60, 192, 39);
			
			button.setIcon(new ImageIcon(TypeOfMonthlyReports.class.getResource("/javagui/resources/img16x16/nota.png")));
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setForeground(Color.BLUE);
			button.setFont(new Font("Tahoma", Font.BOLD, 11));
			button.setBackground(new Color(153, 255, 102));
			createEvents1();
			FirstPanel.add(button);
			
			btnPeriodReport = new JButton("Period Report");
			btnPeriodReport.setBounds(52, 110, 192, 39);
			
			btnPeriodReport.setIcon(new ImageIcon(TypeOfMonthlyReports.class.getResource("/javagui/resources/img16x16/nota.png")));
			btnPeriodReport.setHorizontalAlignment(SwingConstants.LEFT);
			btnPeriodReport.setForeground(Color.BLUE);
			btnPeriodReport.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnPeriodReport.setBackground(new Color(153, 255, 102));
			createEvents2();
			FirstPanel.add(btnPeriodReport);			
			
			btnNLastMonth = new JButton("N Last Month Report");
			btnNLastMonth.setBounds(52, 162, 192, 39);
			
			btnNLastMonth.setIcon(new ImageIcon(TypeOfMonthlyReports.class.getResource("/javagui/resources/img16x16/nota.png")));
			btnNLastMonth.setHorizontalAlignment(SwingConstants.LEFT);
			btnNLastMonth.setForeground(Color.BLUE);
			btnNLastMonth.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnNLastMonth.setBackground(new Color(153, 255, 102));
			createEvents3();
			FirstPanel.add(btnNLastMonth);
			
			lblSelectTheDesired = new JLabel("Select the desired report :");
			lblSelectTheDesired.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblSelectTheDesired.setBounds(52, 11, 244, 29);
			FirstPanel.add(lblSelectTheDesired);
			
		}
		return FirstPanel;
	}
	
	private void initComponent() {
		setBounds(0, 0, 488, 500);
		this.setContentPane(getFirstPanel());
		this.CurrRep = new CurrReport();
			CurrRep.btnBfd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setContentPane(getFirstPanel());	 
				}
			});
		
		this.PerRep = new PeriodReport();
			PerRep.button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setContentPane(getFirstPanel());
			}
		});
			
			this.NRep = new NMonthReport ();
			NRep.button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setContentPane(getFirstPanel());
				}
			});
	}


	private void createEvents1() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// להוסיף פונקציית איתחול לדוח
				setContentPane(CurrRep); // Display report
			}
		});
	}

	private void createEvents2() {
		btnPeriodReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// להוסיף פונקציית איתחול לדוח
				setContentPane(PerRep); // Display report
			}
		});
	}
	private void createEvents3() {
		btnNLastMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// להוסיף פונקציית איתחול לדוח
				setContentPane(NRep); // Display report
			}
		});
		
	}
	

	
	
	
}
