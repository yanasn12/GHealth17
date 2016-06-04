package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class appointments_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private String quaryAppointment="";
	/**
	 * Create the panel.
	 */
	public appointments_Gui() {
		setLayout(new MigLayout("", "[][grow][90.00,grow][grow][33.00][68.00][]", "[][][][][][]"));
		
		JLabel lblAppointmentNumber = new JLabel("Appointment Number");
		add(lblAppointmentNumber, "cell 0 0,alignx left");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		
		JLabel lblAppointmentDate = new JLabel("Appointment Date");
		add(lblAppointmentDate, "cell 2 0,alignx left");
		
		textField_4 = new JTextField();
		add(textField_4, "cell 3 0,growx");
		textField_4.setColumns(10);
		
		
		JLabel lblStatus = new JLabel("Status");
		add(lblStatus, "cell 4 0,alignx left");
		
		textField_3 = new JTextField();
		add(textField_3, "cell 5 0,growx");
		
		textField_3.setColumns(10);
		
		JLabel lblClinicNumber = new JLabel("Clinic Number");
		add(lblClinicNumber, "cell 0 1,alignx left");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		
		JLabel lblCallDate = new JLabel("Call Date");
		add(lblCallDate, "cell 2 1,alignx left");
		
		textField_5 = new JTextField();
		add(textField_5, "cell 3 1,growx");
		textField_5.setColumns(10);
		
		
		JLabel lblWorkerNumber = new JLabel("Worker Number");
		add(lblWorkerNumber, "cell 0 2,alignx left");
		
		textField_2 = new JTextField();
		add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
		

		
		JLabel lblResults = new JLabel("results");
		add(lblResults, "cell 2 2,alignx left");
		
		textField_6 = new JTextField();
		add(textField_6, "cell 3 2,growx");
		textField_6.setColumns(10);
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					quaryAppointment="appointment_num,"+textField.getText().toString();
					quaryAppointment=quaryAppointment+",clinic_num,"+textField_1.getText().toString();	
					quaryAppointment=quaryAppointment+",worker_num,"+textField_2.getText().toString();
					quaryAppointment=quaryAppointment+",status,"+textField_3.getText().toString();
					quaryAppointment=quaryAppointment+",app_date,"+textField_4.getText().toString();	
					quaryAppointment=quaryAppointment+",call_date,"+textField_5.getText().toString();
					quaryAppointment=quaryAppointment+",results,"+textField_6.getText().toString();
				
				ServerGui.textline=ServerGui.textline+quaryAppointment.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 6 2");

	}

}
