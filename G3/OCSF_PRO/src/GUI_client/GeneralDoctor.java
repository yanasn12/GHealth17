package GUI_client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

public class GeneralDoctor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnViewDetails;
	private RecordAppointment record = null;
	private JDesktopPane desktopPane;
	private RequestFile req = null;
	private ReferralToExpert DocRef=null;
	private ReferralToLabratory LabRef=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneralDoctor frame = new GeneralDoctor();
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

	public GeneralDoctor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 779);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 949, 718);
		contentPane.add(panel);
		
		JButton btnViewDetails = new JButton("Record appointment");

		btnViewDetails.setEnabled(false);
		btnViewDetails.setHorizontalAlignment(SwingConstants.LEFT);
		btnViewDetails.setForeground(Color.BLUE);
		btnViewDetails.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewDetails.setBackground(Color.WHITE);
		btnViewDetails.setBounds(10, 130, 192, 39);
		panel.add(btnViewDetails);
		
		JButton btnRequestFile = new JButton("Request file");

		btnRequestFile.setEnabled(false);
		btnRequestFile.setHorizontalAlignment(SwingConstants.LEFT);
		btnRequestFile.setForeground(Color.BLUE);
		btnRequestFile.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRequestFile.setBackground(Color.WHITE);
		btnRequestFile.setBounds(10, 183, 192, 39);
		panel.add(btnRequestFile);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GeneralDoctor.class.getResource("/javagui/resources/GHealthlogo.png")));
		label.setBounds(86, 0, 249, 92);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Patient ID:");
		label_1.setBounds(10, 105, 59, 14);
		panel.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(79, 99, 86, 20);
		panel.add(textField);
		
		JButton button_2 = new JButton("apply");
	
		button_2.setBounds(185, 98, 76, 23);
		panel.add(button_2);
		
		JButton btnCreateReferral = new JButton("Referral to expert");

		btnCreateReferral.setEnabled(false);
		btnCreateReferral.setHorizontalAlignment(SwingConstants.LEFT);
		btnCreateReferral.setForeground(Color.BLUE);
		btnCreateReferral.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreateReferral.setBackground(Color.WHITE);
		btnCreateReferral.setBounds(10, 242, 192, 39);
		panel.add(btnCreateReferral);
		
		JButton btnLabratory = new JButton("Referral to labratory");

		btnLabratory.setEnabled(false);
		btnLabratory.setHorizontalAlignment(SwingConstants.LEFT);
		btnLabratory.setForeground(Color.BLUE);
		btnLabratory.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLabratory.setBackground(Color.WHITE);
		btnLabratory.setBounds(10, 298, 192, 39);
		panel.add(btnLabratory);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setLayout(null);
		desktopPane.setBackground(Color.WHITE);
		desktopPane.setBounds(230, 130, 719, 577);
		panel.add(desktopPane);
		
		JButton btnViewHistory = new JButton("View History");
		btnViewHistory.setHorizontalAlignment(SwingConstants.LEFT);
		btnViewHistory.setForeground(Color.BLUE);
		btnViewHistory.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewHistory.setEnabled(false);
		btnViewHistory.setBackground(Color.WHITE);
		btnViewHistory.setBounds(10, 347, 192, 39);
		panel.add(btnViewHistory);
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_2.setVisible(false);
				textField.setEnabled(false);
				btnViewDetails.setEnabled(true);
				btnRequestFile.setEnabled(true);
				btnCreateReferral.setEnabled(true);
				btnLabratory.setEnabled(true);
			}
		});
		
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (record == null || record.isClosed())/////////
				record = new RecordAppointment();  ////  לא לשכוח להצהיר על המשתנה כפרטי במחלקה 
				desktopPane.add(record);/// הצגה על המסך הקטן
				record.show(); // 
				record=null;
				
			}
		});
		
		btnRequestFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (req == null || req.isClosed())/////////
				req = new RequestFile();  ////  לא לשכוח להצהיר על המשתנה כפרטי במחלקה 
				desktopPane.add(req);/// הצגה על המסך הקטן
				req.show(); 
				req=null;
			}
		});
		
		btnCreateReferral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (DocRef == null || DocRef.isClosed())/////////
				DocRef = new ReferralToExpert();  ////  לא לשכוח להצהיר על המשתנה כפרטי במחלקה 
				desktopPane.add(DocRef);/// הצגה על המסך הקטן
				DocRef.show(); 
				DocRef=null;
			}
		});
		
		btnLabratory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (LabRef == null || LabRef.isClosed())/////////
					LabRef = new ReferralToLabratory();  ////  לא לשכוח להצהיר על המשתנה כפרטי במחלקה 
				desktopPane.add(LabRef);/// הצגה על המסך הקטן
				LabRef.show(); 
				LabRef=null;
			}
			
		});
	}

}
