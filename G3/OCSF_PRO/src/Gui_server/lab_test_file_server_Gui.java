package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class lab_test_file_server_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private String quaryLab_tests_file="";
	/**
	 * Create the panel.
	 */
	public lab_test_file_server_Gui() {
		setLayout(new MigLayout("", "[][grow][]", "[][][]"));
		
		JLabel lblLabTestFile = new JLabel("Lab Test File Number");
		add(lblLabTestFile, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
	
		JLabel lblLabTestNumber = new JLabel("Lab Test Number");
		add(lblLabTestNumber, "cell 0 1,alignx trailing");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryLab_tests_file="lab_test_file_num,"+textField.getText().toString();
				quaryLab_tests_file=quaryLab_tests_file+",lab_test_num,"+textField_1.getText().toString();
		
				
				ServerGui.textline=ServerGui.textline+quaryLab_tests_file.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 2 2");

	}

}
