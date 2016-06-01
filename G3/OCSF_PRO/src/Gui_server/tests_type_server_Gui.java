package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class tests_type_server_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private String quaryTestType="";
	/**
	 * Create the panel.
	 */
	public tests_type_server_Gui() {
		setLayout(new MigLayout("", "[][grow][]", "[][][][]"));
		
		JLabel lblTestNumber = new JLabel("Test number");
		add(lblTestNumber, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);


		
		JLabel lblTestName = new JLabel("Test name");
		add(lblTestName, "cell 0 1,alignx trailing");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);


		JLabel lblTestDescription = new JLabel("Test description");
		add(lblTestDescription, "cell 0 2,alignx trailing");


		textField_2 = new JTextField();
		add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryTestType="Test_num,"+textField.getText().toString();
				quaryTestType=quaryTestType+",Test_name,"+textField_1.getText().toString();
				quaryTestType=quaryTestType+",Test_description,"+textField_2.getText().toString();
				
				ServerGui.textline=ServerGui.textline+quaryTestType.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 2 3");

	}

}
