package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class clinic_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private String quaryClinic;
	/**
	 * Create the panel.
	 */
	public clinic_Gui() {
		setLayout(new MigLayout("", "[][grow][][grow][]", "[][][][]"));
		
		JLabel lblClinicNumber = new JLabel("Clinic Number");
		add(lblClinicNumber, "cell 0 0,alignx left");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblClinicPhoneNumber = new JLabel("Clinic Phone Number");
		add(lblClinicPhoneNumber, "cell 2 0,alignx left");
		
		textField_3 = new JTextField();
		add(textField_3, "cell 3 0,growx");
		textField_3.setColumns(10);
		
		JLabel lblClinicName = new JLabel("Clinic Name");
		add(lblClinicName, "cell 0 1,alignx left");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblEreaCode = new JLabel("Erea Code");
		add(lblEreaCode, "cell 2 1,alignx left");
		
		textField_4 = new JTextField();
		add(textField_4, "cell 3 1,growx");
		textField_4.setColumns(10);
		
		JLabel lblClinicAddress = new JLabel("Clinic Address");
		add(lblClinicAddress, "cell 0 2,alignx left");
		
		textField_2 = new JTextField();
		add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quaryClinic="Clinic_num,"+textField.getText().toString();
				quaryClinic=quaryClinic+",Clinic_name,"+textField_1.getText().toString();
				quaryClinic=quaryClinic+",Clinic_address,"+textField_2.getText().toString();
				quaryClinic=quaryClinic+",Clinic_phone_number,"+textField_3.getText().toString();
				quaryClinic=quaryClinic+",Erea_code,"+textField_4.getText().toString();
				
				ServerGui.textline=ServerGui.textline+quaryClinic.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 4 2");

	}

}
