package GUI_client;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class NewReferral extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewReferral frame = new NewReferral();
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
	public NewReferral() {
		setBounds(100, 100, 538, 449);
		getContentPane().setLayout(null);
		
		JLabel lblReferralNumber = new JLabel("Referral Number :");
		lblReferralNumber.setBounds(40, 250, 90, 14);
		getContentPane().add(lblReferralNumber);
		
		JLabel lblDate = new JLabel("Date : ");
		lblDate.setBounds(10, 123, 46, 14);
		getContentPane().add(lblDate);
		
		textField = new JTextField();
		textField.setBounds(50, 120, 141, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 247, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDrName = new JLabel("Dr name :");
		lblDrName.setBounds(10, 22, 64, 14);
		getContentPane().add(lblDrName);
		
		JLabel lblExpertise = new JLabel("expertise :");
		lblExpertise.setBounds(10, 47, 64, 14);
		getContentPane().add(lblExpertise);
		
		JLabel lblLicenceNumber = new JLabel("License number :");
		lblLicenceNumber.setBounds(10, 72, 86, 14);
		getContentPane().add(lblLicenceNumber);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setBounds(10, 99, 46, 14);
		getContentPane().add(lblAddress);
		
		JLabel lblReferralFor = new JLabel("Referral for");
		lblReferralFor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReferralFor.setBounds(137, 148, 103, 20);
		getContentPane().add(lblReferralFor);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"check 1", "check 2", "check 3", "..."}));
		comboBox.setBounds(218, 151, 171, 19);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Patient detailes :");
		lblNewLabel.setBounds(38, 172, 90, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblFirstName = new JLabel("First name : ");
		lblFirstName.setBounds(115, 197, 77, 14);
		getContentPane().add(lblFirstName);
		
		textField_2 = new JTextField();
		textField_2.setBounds(176, 194, 86, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last name :");
		lblLastName.setBounds(272, 197, 70, 14);
		getContentPane().add(lblLastName);
		
		textField_3 = new JTextField();
		textField_3.setBounds(336, 194, 86, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblAge = new JLabel("Age :");
		lblAge.setBounds(115, 221, 46, 14);
		getContentPane().add(lblAge);
		
		textField_4 = new JTextField();
		textField_4.setBounds(150, 218, 112, 20);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblDob = new JLabel("DOB :");
		lblDob.setBounds(272, 221, 46, 14);
		getContentPane().add(lblDob);
		
		textField_5 = new JTextField();
		textField_5.setBounds(301, 218, 120, 20);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Referral cause :");
		lblNewLabel_1.setBounds(40, 275, 90, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField_6 = new JTextField();
		textField_6.setBounds(136, 272, 286, 55);
		getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Sender Dr Detailes :");
		lblNewLabel_2.setBounds(40, 340, 112, 14);
		getContentPane().add(lblNewLabel_2);
		
		textField_7 = new JTextField();
		textField_7.setBounds(64, 19, 128, 20);
		getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(74, 44, 118, 20);
		getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(95, 69, 97, 20);
		getContentPane().add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(66, 96, 126, 20);
		getContentPane().add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblFullName = new JLabel("Full name : ");
		lblFullName.setBounds(146, 341, 77, 14);
		getContentPane().add(lblFullName);
		
		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setBounds(303, 341, 70, 14);
		getContentPane().add(lblEmail);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(207, 338, 86, 20);
		getContentPane().add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(367, 338, 86, 20);
		getContentPane().add(textField_12);

	}
}
