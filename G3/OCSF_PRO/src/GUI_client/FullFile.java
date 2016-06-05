package GUI_client;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FullFile extends JPanel {
	private JTextField textField_1;
	public JButton btnBfd = null;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Create the panel.
	 */
	public FullFile() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Full File", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 0, 505, 477);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblReportForMonth = new JLabel("Full File");
		lblReportForMonth.setBounds(146, 25, 168, 33);
		lblReportForMonth.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblReportForMonth);
		
		JLabel lblNumberOfPatient = new JLabel("patient ID:");
		lblNumberOfPatient.setBounds(10, 140, 160, 14);
		panel.add(lblNumberOfPatient);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 137, 168, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNumberOfPatient_1 = new JLabel("type of insurance:");
		lblNumberOfPatient_1.setBounds(10, 296, 160, 14);
		panel.add(lblNumberOfPatient_1);
		
		btnBfd = new JButton("Back");
		btnBfd.setBounds(206, 375, 108, 41);
		/*btnBfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("ADIDUSH");	 /////////////////////////////////////	 לא נכון! איך חוזרים למסך הקודם???//////////////////////////////////////
			}
		});
		*/
		panel.add(btnBfd);
		btnBfd.setIcon(new ImageIcon(FullFile.class.getResource("/javagui/resources/Back.png")));
		
		JLabel lblPatientname = new JLabel("patient's Name:");
		lblPatientname.setBounds(10, 171, 160, 14);
		panel.add(lblPatientname);
		
		textField = new JTextField();
		textField.setBounds(180, 168, 168, 20);
		textField.setColumns(10);
		panel.add(textField);
		
		JLabel lblPatientAdress = new JLabel("patient's address:");
		lblPatientAdress.setBounds(10, 202, 160, 14);
		panel.add(lblPatientAdress);
		
		textField_2 = new JTextField();
		textField_2.setBounds(180, 199, 168, 20);
		textField_2.setColumns(10);
		panel.add(textField_2);
		
		JLabel lblPatientsPhoneNumber = new JLabel("patient's phone number:");
		lblPatientsPhoneNumber.setBounds(10, 234, 160, 14);
		panel.add(lblPatientsPhoneNumber);
		
		textField_3 = new JTextField();
		textField_3.setBounds(180, 231, 168, 20);
		textField_3.setColumns(10);
		panel.add(textField_3);
		
		JLabel lblPatientsEmail = new JLabel("patient's email:");
		lblPatientsEmail.setBounds(10, 265, 160, 14);
		panel.add(lblPatientsEmail);
		
		textField_6 = new JTextField();
		textField_6.setBounds(180, 262, 168, 20);
		textField_6.setColumns(10);
		panel.add(textField_6);
		
		JLabel lblMedicalFileNumber = new JLabel("medical file number:");
		lblMedicalFileNumber.setBounds(10, 104, 160, 14);
		panel.add(lblMedicalFileNumber);
		
		textField_7 = new JTextField();
		textField_7.setBounds(180, 101, 168, 20);
		textField_7.setColumns(10);
		panel.add(textField_7);
		
		textField_4 = new JTextField();
		textField_4.setBounds(180, 293, 168, 20);
		textField_4.setColumns(10);
		panel.add(textField_4);
		
		JLabel lblLeavingDate = new JLabel("leaving date:");
		lblLeavingDate.setBounds(10, 326, 160, 14);
		panel.add(lblLeavingDate);
		
		textField_5 = new JTextField();
		textField_5.setBounds(180, 324, 168, 20);
		textField_5.setColumns(10);
		panel.add(textField_5);

	}
}
