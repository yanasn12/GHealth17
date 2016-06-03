package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class appointments_File_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private String quaryAppointment="";
	/**
	 * Create the panel.
	 */
	public appointments_File_Gui() {
		setLayout(new MigLayout("", "[][217.00,grow][]", "[][][][]"));
		
		JLabel lblAppointmentFileNumber = new JLabel("Appointment File Number");
		add(lblAppointmentFileNumber, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		
		JLabel lblAppointmentNumber = new JLabel("Appointment Number");
		add(lblAppointmentNumber, "cell 0 1,alignx trailing");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
	
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quaryAppointment="appointment_file_num,"+textField.getText().toString();
				quaryAppointment=quaryAppointment+",appointment_num,"+textField_1.getText().toString();

				ServerGui.textline=ServerGui.textline+quaryAppointment.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 2 3");

	}

}
