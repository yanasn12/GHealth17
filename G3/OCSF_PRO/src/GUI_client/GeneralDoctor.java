package GUI_client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

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
import javax.swing.JTabbedPane;

public class GeneralDoctor extends JFrame {

	private JPanel contentPane=null;
	private JTextField textField;
	private String pname="";
	private JTextField textField_1;
	private JButton btnViewDetails_1;
	private JPanel panel_1;
	private RecordAppointment window1 =new RecordAppointment();
	private RequestFile window2 = new RequestFile();
	private ReferralToLabratory window3 = new ReferralToLabratory();
	private ReferralToExpert window4 = new ReferralToExpert();
	
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);
		this.setTitle("Expert");
		setBounds(100, 100, 1000, 1000);
		this.setContentPane(into());
		
}
	private JPanel into()
	{
		if(contentPane==null){
			contentPane =new JPanel();
			contentPane.setBackground(SystemColor.window);
			contentPane.setLayout(null);
			contentPane.setBackground(SystemColor.WHITE);
		contentPane.setBorder(new EmptyBorder(6 , 6, 6, 6));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GeneralDoctor.class.getResource("/javagui/resources/GHealthlogo.png")));
		label.setBounds(86, 10, 192, 68);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Patient ID:");
		label_1.setBounds(129, 130, 52, 14);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(209, 127, 86, 20);
		contentPane.add(textField);
		
		JButton button_2 = new JButton("apply");
	
		button_2.setBounds(320, 126, 59, 23);
		contentPane.add(button_2);
		
		JButton btnCreateReferral = new JButton("Referral to expert");

		btnCreateReferral.setEnabled(false);
		btnCreateReferral.setHorizontalAlignment(SwingConstants.LEFT);
		btnCreateReferral.setForeground(Color.BLUE);
		btnCreateReferral.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreateReferral.setBackground(Color.WHITE);
		btnCreateReferral.setBounds(24, 311, 149, 23);
		contentPane.add(btnCreateReferral);
		
		JButton btnViewDetails_1 = new JButton("Record appointment");
		
				btnViewDetails_1.setEnabled(false);
				btnViewDetails_1.setHorizontalAlignment(SwingConstants.LEFT);
				btnViewDetails_1.setForeground(Color.BLUE);
				btnViewDetails_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnViewDetails_1.setBackground(Color.WHITE);
				btnViewDetails_1.setBounds(24, 177, 149, 23);
				contentPane.add(btnViewDetails_1);
				

		
		JButton btnRequestFile = new JButton("Request file");

		
				btnRequestFile.setEnabled(false);
				btnRequestFile.setHorizontalAlignment(SwingConstants.LEFT);
				btnRequestFile.setForeground(Color.BLUE);
				btnRequestFile.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnRequestFile.setBackground(Color.WHITE);
				btnRequestFile.setBounds(24, 269, 149, 23);
				contentPane.add(btnRequestFile);
		
		JButton btnLabratory = new JButton("Referral to labratory");


		btnLabratory.setEnabled(false);
		btnLabratory.setHorizontalAlignment(SwingConstants.LEFT);
		btnLabratory.setForeground(Color.BLUE);
		btnLabratory.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLabratory.setBackground(Color.WHITE);
		btnLabratory.setBounds(24, 226, 149, 23);
		contentPane.add(btnLabratory);
		
		JButton btnViewHistory = new JButton("View History");
		btnViewHistory.setHorizontalAlignment(SwingConstants.LEFT);
		btnViewHistory.setForeground(Color.BLUE);
		btnViewHistory.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewHistory.setEnabled(false);
		btnViewHistory.setBackground(Color.WHITE);
		btnViewHistory.setBounds(24, 355, 149, 23);
		contentPane.add(btnViewHistory);
		
		JLabel lblPatientDetails = new JLabel("patient details:" + pname);
		lblPatientDetails.setBounds(410, 130, 72, 14);
		
		contentPane.add(lblPatientDetails);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(513, 127, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBounds(257, 177, 487, 530);
		contentPane.add(tabbedPane);
		tabbedPane.addTab("Record appointment", null,window1,null);	
		tabbedPane.addTab("Request File", null,window2,null);
		tabbedPane.addTab("Referral to lab", null,window3,null);
		tabbedPane.addTab("Referral to Expert", null,window4,null);
	//	tabbedPane.addTab("history", null,window5,null);

		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_2.setVisible(true);
				pname="im here";
				textField_1.setText(pname);
				textField.setEnabled(true);
				btnViewDetails_1.setEnabled(true);
				btnRequestFile.setEnabled(true);
				btnCreateReferral.setEnabled(true);
				btnLabratory.setEnabled(true);
			}
		});


		
		btnViewDetails_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}});
		
		btnLabratory.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}});
		
		btnRequestFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}});
	
/*
		JFrameRequestFile.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("12");
				newWindow.setVisible(false);
				newWindow=new JFrameRequestFile();
				panel_1.add(newWindow);/// дцвд тм доск дчип
				panel_1.setVisible(true);
				into();}}); */
	}

	return contentPane;
	}	
}
