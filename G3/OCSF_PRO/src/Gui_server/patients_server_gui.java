package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class patients_server_gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private String quaryMedical="";
	/**
	 * Create the panel.
	 */
	public patients_server_gui() {
		setLayout(new MigLayout("", "[][grow][][grow][]", "[][][][][]"));
		
		JLabel lblPatientNumber = new JLabel("patient number");
		add(lblPatientNumber, "cell 0 0,alignx left");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);

		
		JLabel lblMedicalFileNum = new JLabel("medical file num");
		add(lblMedicalFileNum, "cell 2 0,alignx left");
		
		textField_4 = new JTextField();
		add(textField_4, "cell 3 0,growx");
		textField_4.setColumns(10);

		JLabel lblPersonId = new JLabel("person ID");
		add(lblPersonId, "cell 0 1,alignx left");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);


		JLabel lblExtensiveClinicEmail = new JLabel("extensive clinic email");
		add(lblExtensiveClinicEmail, "cell 2 1,alignx left");
		
		textField_5 = new JTextField();
		add(textField_5, "cell 3 1,growx");
		textField_5.setColumns(10);

		JLabel lblInsuranceLevel = new JLabel("insurance level");
		add(lblInsuranceLevel, "cell 0 2,alignx left");
		
		textField_2 = new JTextField();
		add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);

		JLabel lblLeaveDate = new JLabel("leave date");
		add(lblLeaveDate, "cell 2 2,alignx left");
		
		textField_6 = new JTextField();
		add(textField_6, "cell 3 2,growx");
		textField_6.setColumns(10);

		
		JLabel lblInsuranceValidity = new JLabel("insurance validity");
		add(lblInsuranceValidity, "cell 0 3,alignx left");
		
		textField_3 = new JTextField();
		add(textField_3, "cell 1 3,growx");
		textField_3.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryMedical="patient_num,"+textField.getText().toString();
				quaryMedical=quaryMedical+",person_id,"+textField_1.getText().toString();
				quaryMedical=quaryMedical+",insurance_level,"+textField_2.getText().toString();
				quaryMedical=quaryMedical+",insurance_validity,"+textField_3.getText().toString();
				quaryMedical=quaryMedical+",medical_file_num,"+textField_4.getText().toString();
				quaryMedical=quaryMedical+",extensive_clinic_email,"+textField_5.getText().toString();
				quaryMedical=quaryMedical+",leave_date,"+textField_6.getText().toString();

				ServerGui.textline=ServerGui.textline+quaryMedical.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 4 4");

	}

}
