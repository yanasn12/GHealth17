package Gui_server;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class medical_worker_Server extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	public static String quaryM="";

	/**
	 * Create the panel.
	 */
	public medical_worker_Server() {
		setLayout(new MigLayout("", "[][][80.00,grow][grow][]", "[][][][]"));
		
		JLabel lblWorkerNumber = new JLabel("Worker Number");
		add(lblWorkerNumber, "cell 0 0,alignx center");
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		

		JLabel lblClincNumber = new JLabel("Clinc number");
		add(lblClincNumber, "flowx,cell 2 0,alignx center");
		textField_4 = new JTextField();
		add(textField_4, "cell 3 0,growx");
		textField_4.setColumns(10);

		
		JLabel lblPassword = new JLabel("Password");
		add(lblPassword, "cell 0 1,alignx center,growy");
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		
		JLabel lblWorkerType = new JLabel("Worker Type");
		add(lblWorkerType, "flowx,cell 2 1,alignx center");
		textField_5 = new JTextField();
		add(textField_5, "cell 3 1,growx");
		textField_5.setColumns(10);
		
		
		JLabel lblPersonId = new JLabel("Person ID");
		add(lblPersonId, "cell 0 2,alignx center");
		textField_2 = new JTextField();
		add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
		

		
		JLabel lblIsConnected = new JLabel("Is Connected");
		add(lblIsConnected, "cell 2 2,alignx center");
		textField_3 = new JTextField();
		add(textField_3, "cell 3 2,growx");
		textField_3.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryM="worker_num,"+textField.getText().toString();
				quaryM=quaryM+",password,"+textField_1.getText().toString();
				quaryM=quaryM+",person_id,"+textField_2.getText().toString();
				quaryM=quaryM+",is_Connected,"+textField_3.getText().toString();
				quaryM=quaryM+",clinic_num,"+textField_4.getText().toString();
				quaryM=quaryM+",worker_type,"+textField_5.getText().toString();
				
				
				ServerGui.textline=ServerGui.textline+quaryM.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 4 3");


	}

}
