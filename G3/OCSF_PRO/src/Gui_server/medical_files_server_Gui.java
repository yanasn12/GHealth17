package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class medical_files_server_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private String quaryMedical="";
	/**
	 * Create the panel.
	 */
	public medical_files_server_Gui() {
		setLayout(new MigLayout("", "[][grow][][grow][]", "[][][][]"));
		
		JLabel lblMedicalFileNumber = new JLabel("medical file number");
		add(lblMedicalFileNumber, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		
		JLabel lblLabTestFile = new JLabel("lab test file number");
		add(lblLabTestFile, "cell 2 0,alignx trailing");
		
		textField_3 = new JTextField();
		add(textField_3, "cell 3 0,growx");
		textField_3.setColumns(10);
	
		JLabel lblAppointmentFileNumber = new JLabel("appointment file number");
		add(lblAppointmentFileNumber, "cell 0 1,alignx trailing");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblReportFileNumber = new JLabel("report file number");
		add(lblReportFileNumber, "cell 2 1,alignx trailing");
		
		textField_4 = new JTextField();
		add(textField_4, "cell 3 1,growx");
		textField_4.setColumns(10);
		
		JLabel lblReferencesFileNumber = new JLabel("references file number");
		add(lblReferencesFileNumber, "cell 0 2,alignx trailing");
		
		textField_2 = new JTextField();
		add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryMedical="medical_file_num,"+textField.getText().toString();
				quaryMedical=quaryMedical+",appointment_file_num,"+textField_1.getText().toString();
				quaryMedical=quaryMedical+",references_file_num,"+textField_2.getText().toString();
				quaryMedical=quaryMedical+",lab_test_file_num,"+textField_3.getText().toString();
				quaryMedical=quaryMedical+",report_file_num,"+textField_4.getText().toString();

				ServerGui.textline=ServerGui.textline+quaryMedical.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 4 3");

	}

}
