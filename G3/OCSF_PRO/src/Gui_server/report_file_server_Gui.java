package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class report_file_server_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private String quaryReport="";
	/**
	 * Create the panel.
	 */
	public report_file_server_Gui() {
		setLayout(new MigLayout("", "[][grow][][grow][]", "[][][][]"));
		
		JLabel lblReportFileNumber = new JLabel("report file number");
		add(lblReportFileNumber, "cell 0 0,alignx left");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblDescription = new JLabel("description");
		add(lblDescription, "cell 2 0,alignx left");
		
		textField_3 = new JTextField();
		add(textField_3, "cell 3 0,growx");
		textField_3.setColumns(10);
		
		JLabel lblReportNumber = new JLabel("report number");
		add(lblReportNumber, "cell 0 1,alignx left");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblInternalextrnal = new JLabel("internal\\extrnal");
		add(lblInternalextrnal, "cell 2 1,alignx left");
		
		textField_4 = new JTextField();
		add(textField_4, "cell 3 1,growx");
		textField_4.setColumns(10);
		
		JLabel lblExpertiseCode = new JLabel("expertise code");
		add(lblExpertiseCode, "cell 0 2,alignx left");
		
		textField_2 = new JTextField();
		add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryReport="report_file_num,"+textField.getText().toString();
				quaryReport=quaryReport+",report_num,"+textField_1.getText().toString();
				quaryReport=quaryReport+",expertise_code,"+textField_2.getText().toString();
				quaryReport=quaryReport+",description,"+textField_3.getText().toString();
				quaryReport=quaryReport+",internal\\extrnal,"+textField_4.getText().toString();

				
				ServerGui.textline=ServerGui.textline+quaryReport.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 4 3");

	}

}
