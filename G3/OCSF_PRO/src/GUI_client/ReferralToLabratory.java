/*package javagui.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class ReferralToLabratory extends JInternalFrame {

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReferralToLabratory frame = new ReferralToLabratory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 
	public ReferralToLabratory() {
		setBounds(100, 100, 450, 300);

	}

}*/


package GUI_client;
import java.awt.EventQueue;

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


public class ReferralToLabratory extends JInternalFrame {

		private CreateNewLabReferral newReferral;//// object of current
		private ViewExistingLabReferral ExistingReferral;
		private JButton button;
	//	private NMonthReport NRep;
	//	private JButton btnNLastMonth;
	//	private PeriodReport PerRep;
		private JButton btnPartialFile;

	/**
	 * Create the frame.
	 */
		public JPanel FirstPanel = null;
		
		
	public ReferralToLabratory() {
		super();
		initComponent();
	}

	private JPanel getFirstPanel(){
		
		if(FirstPanel == null){
			FirstPanel = new JPanel();
			FirstPanel.setLayout(null);
			
			JTextArea textArea = new JTextArea();
			textArea.setBounds(32, 23, 189, 26);
			//getContentPane().add(textArea);
			textArea.setText("Please Select :");
			textArea.setForeground(new Color(0, 128, 0));
			textArea.setFont(new Font("Tahoma", Font.BOLD, 14));
			textArea.setBackground(SystemColor.menu);
			FirstPanel.add(textArea);
			
			button = new JButton("Create new referral");
			button.setBounds(52, 60, 192, 39);
			
			button.setIcon(new ImageIcon(ReferralToLabratory.class.getResource("/javagui/resources/img16x16/nota.png")));
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setForeground(Color.BLUE);
			button.setFont(new Font("Tahoma", Font.BOLD, 11));
			button.setBackground(new Color(153, 255, 102));
			createEvents1();
			FirstPanel.add(button);
			
			btnPartialFile = new JButton("View Existiong Referrals");

			btnPartialFile.setBounds(52, 110, 192, 39);
			
			btnPartialFile.setIcon(new ImageIcon(ReferralToLabratory.class.getResource("/javagui/resources/img16x16/nota.png")));
			btnPartialFile.setHorizontalAlignment(SwingConstants.LEFT);
			btnPartialFile.setForeground(Color.BLUE);
			btnPartialFile.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnPartialFile.setBackground(new Color(153, 255, 102));
			createEvents2();
			FirstPanel.add(btnPartialFile);			
			
		/*	btnNLastMonth = new JButton("N Last Month");
			btnNLastMonth.setBounds(52, 162, 192, 39);
			
			btnNLastMonth.setIcon(new ImageIcon(DoctorReferral.class.getResource("/javagui/resources/img16x16/nota.png")));
			btnNLastMonth.setHorizontalAlignment(SwingConstants.LEFT);
			btnNLastMonth.setForeground(Color.BLUE);
			btnNLastMonth.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnNLastMonth.setBackground(new Color(153, 255, 102));
		//	createEvents3();
			FirstPanel.add(btnNLastMonth);*/
			
		}
		return FirstPanel;
	}
	
	private void initComponent() {
		setBounds(0, 0, 505, 477);
		this.setContentPane(getFirstPanel());
		this.newReferral = new CreateNewLabReferral();
			newReferral.btnBfd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setContentPane(getFirstPanel());	 
				}
			});
	
		this.ExistingReferral = new ViewExistingLabReferral();
			ExistingReferral.btnBfd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setContentPane(getFirstPanel());
			}
		});
			
	/*		this.NRep = new NMonthReport ();
			NRep.button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setContentPane(getFirstPanel());
				}
			});*/
	}


	private void createEvents1() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// להוסיף פונקציית איתחול לדוח
				setContentPane(newReferral); // Display report
			}
		});
	}

	private void createEvents2() {
		btnPartialFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(ExistingReferral);
			}
		});
	}
/*	private void createEvents3() {
		btnNLastMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// להוסיף פונקציית איתחול לדוח
				setContentPane(NRep); // Display report
			}
		});

	}
		*/	
	
	
}


