package Gui_server;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class lab_tests_server_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private String quaryApointmentNum="";
	/**
	 * Create the panel.
	 */
	public lab_tests_server_Gui() {
		setLayout(new MigLayout("", "[][grow][][grow][]", "[][][][][][][][]"));
		
		JLabel lblLabTestNum = new JLabel("lab test num");
		add(lblLabTestNum, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblAppointmentNumber = new JLabel("appointment number");
		add(lblAppointmentNumber, "cell 2 0,alignx trailing");

		textField_3 = new JTextField();
		add(textField_3, "cell 3 0,growx");
		textField_3.setColumns(10);

		
		JLabel lblLabTestType = new JLabel("lab test type");
		add(lblLabTestType, "cell 0 1");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblResults = new JLabel("results");
		add(lblResults, "cell 2 1");
		
		textField_4 = new JTextField();
		add(textField_4, "cell 3 1,growx");
		textField_4.setColumns(10);

		
		JLabel lblNotes = new JLabel("notes");
		add(lblNotes, "cell 0 2");
		
		textField_2 = new JTextField();
		add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
	
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryApointmentNum="lab_test_num,"+textField.getText().toString();
				quaryApointmentNum=quaryApointmentNum+",lab_test_type,"+textField_1.getText().toString();
				quaryApointmentNum=quaryApointmentNum+",notes,"+textField_2.getText().toString();
				quaryApointmentNum=quaryApointmentNum+",appointment_num,"+textField_3.getText().toString();
				quaryApointmentNum=quaryApointmentNum+",results,"+textField_4.getText().toString();
				ServerGui.textline=ServerGui.textline+quaryApointmentNum.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
				
			}
		});
		add(btnOk, "cell 4 3");

	}

}
