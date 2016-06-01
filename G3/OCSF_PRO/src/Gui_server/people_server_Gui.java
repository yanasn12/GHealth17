package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class people_server_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private String quaryPeople="";
	/**
	 * Create the panel.
	 */
	public people_server_Gui() {
		setLayout(new MigLayout("", "[][grow][][grow][][grow][]", "[][][][]"));
		
		JLabel lblPersonId = new JLabel("person ID");
		add(lblPersonId, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		

		JLabel lblAddress = new JLabel("address");
		add(lblAddress, "cell 2 0,alignx trailing");
		
		textField_3 = new JTextField();
		add(textField_3, "cell 3 0,growx");
		textField_3.setColumns(10);
		

		JLabel lblBirthDate = new JLabel("birth date");
		add(lblBirthDate, "cell 4 0,alignx trailing");
		
		textField_6 = new JTextField();
		add(textField_6, "cell 5 0,growx");
		textField_6.setColumns(10);
		

		JLabel lblFirstName = new JLabel("first name");
		add(lblFirstName, "cell 0 1,alignx trailing");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		

		JLabel lblEmail = new JLabel("email");
		add(lblEmail, "cell 2 1,alignx trailing");
		
		textField_4 = new JTextField();
		add(textField_4, "cell 3 1,growx");
		textField_4.setColumns(10);
		
		
		JLabel lblLastName = new JLabel("last name");
		add(lblLastName, "cell 0 2,alignx trailing");
		
		textField_2 = new JTextField();
		add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);

		JLabel lblPhone = new JLabel("phone");
		add(lblPhone, "cell 2 2,alignx trailing");
		
		textField_5 = new JTextField();
		add(textField_5, "cell 3 2,growx");
		textField_5.setColumns(10);
		

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryPeople="person_id,"+textField.getText().toString();
				quaryPeople=quaryPeople+",first_name,"+textField_1.getText().toString();
				quaryPeople=quaryPeople+",last_name,"+textField_2.getText().toString();
				quaryPeople=quaryPeople+",address,"+textField_3.getText().toString();
				quaryPeople=quaryPeople+",email,"+textField_4.getText().toString();
				quaryPeople=quaryPeople+",phone,"+textField_5.getText().toString();
				quaryPeople=quaryPeople+",birth_date,"+textField_6.getText().toString();
				
				ServerGui.textline=ServerGui.textline+quaryPeople.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 6 3");

	}

}
