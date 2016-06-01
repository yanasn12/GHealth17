package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class expertsinsurance_by_expertise_serverGui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	public String quaryexpertsinsurance="";
	/**
	 * Create the panel.
	 */
	public expertsinsurance_by_expertise_serverGui() {
		setLayout(new MigLayout("", "[][grow][]", "[][][]"));
		
		JLabel lblWorkerNum = new JLabel("Worker Number");
		add(lblWorkerNum, "cell 0 0,alignx trailing");
		
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblExpertiseCode = new JLabel("Expertise Code");
		add(lblExpertiseCode, "cell 0 1,alignx trailing");
		
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
	
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryexpertsinsurance="worker_num,"+textField.getText().toString();
				quaryexpertsinsurance=quaryexpertsinsurance+",expertise_code,"+textField_1.getText().toString();
				
				ServerGui.textline=ServerGui.textline+quaryexpertsinsurance.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 2 2");

	}

}
