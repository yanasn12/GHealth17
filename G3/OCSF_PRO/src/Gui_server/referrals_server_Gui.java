package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class referrals_server_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private String quaryReferrals="";
	/**
	 * Create the panel.
	 */
	public referrals_server_Gui() {
		setLayout(new MigLayout("", "[][grow][grow][grow][][grow][]", "[][][][]"));
		
		JLabel lblReferralNumber = new JLabel("referral number");
		add(lblReferralNumber, "cell 0 0,alignx left");
		
		textField = new JTextField();
		add(textField, "flowx,cell 1 0,growx");
		textField.setColumns(10);
		
		
		JLabel lblReferringDoctorName = new JLabel("referring doctor name");
		add(lblReferringDoctorName, "cell 2 0,alignx left");
		
		textField_3 = new JTextField();
		add(textField_3, "cell 3 0,growx");
		textField_3.setColumns(10);
		
		JLabel lblReason = new JLabel("reason");
		add(lblReason, "cell 4 0,alignx trailing");
		
		textField_6 = new JTextField();
		add(textField_6, "cell 5 0,growx");
		textField_6.setColumns(10);
		
		JLabel lblClinicNumber = new JLabel("clinic number");
		add(lblClinicNumber, "cell 0 1,alignx left");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblReferringDoctorEmail = new JLabel("referring doctor email");
		add(lblReferringDoctorEmail, "cell 2 1,alignx left");
		
		textField_4 = new JTextField();
		add(textField_4, "cell 3 1,growx");
		textField_4.setColumns(10);

		
		JLabel lblExpertiseCode = new JLabel("expertise code");
		add(lblExpertiseCode, "cell 0 2,alignx left");
		
		textField_2 = new JTextField();
		add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
		
		JLabel lblUrgency = new JLabel("urgency");
		add(lblUrgency, "cell 2 2,alignx left");
		
		textField_5 = new JTextField();
		add(textField_5, "cell 3 2,growx");
		textField_5.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryReferrals="referral_num,"+textField.getText().toString();
				quaryReferrals=quaryReferrals+",clinic_num,"+textField_1.getText().toString();
				quaryReferrals=quaryReferrals+",expertise_code,"+textField_2.getText().toString();
				quaryReferrals=quaryReferrals+",referring_doctor_name,"+textField_3.getText().toString();
				quaryReferrals=quaryReferrals+",referring_doctor_email,"+textField_4.getText().toString();
				quaryReferrals=quaryReferrals+",urgency,"+textField_5.getText().toString();
				quaryReferrals=quaryReferrals+",reason,"+textField_6.getText().toString();

				ServerGui.textline=ServerGui.textline+quaryReferrals.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 6 3");

	}

}
