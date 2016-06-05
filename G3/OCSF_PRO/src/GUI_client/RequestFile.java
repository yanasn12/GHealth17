/*package javagui.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class RequestFile extends JInternalFrame {
	
	private FullFile full;
	public JPanel FirstPanel = null;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RequestFile frame = new RequestFile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 
	public RequestFile(){
		super();
		initComponent();
	}
	
	private JPanel getFirstPanel() {
		if(FirstPanel == null){
			FirstPanel = new JPanel();
			FirstPanel.setLayout(null);
		
			JTextArea textArea = new JTextArea();
			textArea.setText("Select type of file:");
			textArea.setForeground(new Color(0, 128, 0));
			textArea.setFont(new Font("Tahoma", Font.BOLD, 14));
			textArea.setBackground(SystemColor.menu);
			textArea.setBounds(32, 23, 189, 26);
			FirstPanel.add(textArea);
		//setBounds(100, 100, 524, 496);
		//getContentPane().setLayout(null);
		
		JPanel FirstPanel = new JPanel();
		FirstPanel.setLayout(null);
		FirstPanel.setBounds(10, 11, 489, 447);
		getContentPane().add(FirstPanel);
		
		JButton button = new JButton("New button");
		button.setBounds(60, 94, 89, 23);
		FirstPanel.add(button);
		

		
		JButton btnFullFile = new JButton("full file");
		btnFullFile.setHorizontalAlignment(SwingConstants.LEFT);
		btnFullFile.setForeground(Color.BLUE);
		btnFullFile.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFullFile.setBackground(new Color(153, 255, 102));
		btnFullFile.setBounds(52, 60, 192, 39);
		FirstPanel.add(btnFullFile);
		
		JButton  = new JButton("partial file");
		btnPartialFile.setHorizontalAlignment(SwingConstants.LEFT);
		btnPartialFile.setForeground(Color.BLUE);
		btnPartialFile.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPartialFile.setBackground(new Color(153, 255, 102));
		btnPartialFile.setBounds(52, 110, 192, 39);
		FirstPanel.add(btnPartialFile);
		
		}//end if
		return FirstPanel;
	}//end getFirstPanel
	
	private void initComponent() {
		setBounds(0, 0, 505, 477);
		this.setContentPane(getFirstPanel());
		this.full = new FullFile();
			full.btnBfd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setContentPane(getFirstPanel());	 
				}
			});
			
			
	}//end initComponent
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


public class RequestFile extends JInternalFrame {

		private FullFile Full;//// object of current
		private PartialFile Partial;
		private JButton button;
	//	private NMonthReport NRep;
	//	private JButton btnNLastMonth;
	//	private PeriodReport PerRep;
		private JButton btnPartialFile;

	/**
	 * Create the frame.
	 */
		public JPanel FirstPanel = null;
		
		
	public RequestFile() {
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
			textArea.setText("Select type of file :");
			textArea.setForeground(new Color(0, 128, 0));
			textArea.setFont(new Font("Tahoma", Font.BOLD, 14));
			textArea.setBackground(SystemColor.menu);
			FirstPanel.add(textArea);
			
			button = new JButton("Full file");
			button.setBounds(52, 60, 192, 39);
			
			button.setIcon(new ImageIcon(RequestFile.class.getResource("/javagui/resources/img16x16/nota.png")));
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setForeground(Color.BLUE);
			button.setFont(new Font("Tahoma", Font.BOLD, 11));
			button.setBackground(new Color(153, 255, 102));
			createEvents1();
			FirstPanel.add(button);
			
			btnPartialFile = new JButton("Partial file");

			btnPartialFile.setBounds(52, 110, 192, 39);
			
			btnPartialFile.setIcon(new ImageIcon(RequestFile.class.getResource("/javagui/resources/img16x16/nota.png")));
			btnPartialFile.setHorizontalAlignment(SwingConstants.LEFT);
			btnPartialFile.setForeground(Color.BLUE);
			btnPartialFile.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnPartialFile.setBackground(new Color(153, 255, 102));
			createEvents2();
			FirstPanel.add(btnPartialFile);			
			
		/*	btnNLastMonth = new JButton("N Last Month");
			btnNLastMonth.setBounds(52, 162, 192, 39);
			
			btnNLastMonth.setIcon(new ImageIcon(RequestFile.class.getResource("/javagui/resources/img16x16/nota.png")));
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
		this.Full = new FullFile();
			Full.btnBfd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setContentPane(getFirstPanel());	 
				}
			});
	
		this.Partial = new PartialFile();
			Partial.btnBfd.addActionListener(new ActionListener() {
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
				setContentPane(Full); // Display report
			}
		});
	}

	private void createEvents2() {
		btnPartialFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(Partial);
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

