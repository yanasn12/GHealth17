package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class insurances_server_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	public String  quaryInsueances="";
	/**
	 * Create the panel.
	 */
	public insurances_server_Gui() {
		setLayout(new MigLayout("", "[][grow][]", "[][][]"));
		
		JLabel lblInsuranceLevel = new JLabel("Insurance Level");
		add(lblInsuranceLevel, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblInsuranceName = new JLabel("Insurance Name");
		add(lblInsuranceName, "cell 0 1,alignx trailing");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryInsueances="insurance_level,"+textField.getText().toString();
				quaryInsueances=quaryInsueances+",insurance_name,"+textField_1.getText().toString();
				
				ServerGui.textline=ServerGui.textline+quaryInsueances.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 2 2");

	}

}
