package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class calendar_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private String quaryCalender="";
	/**
	 * Create the panel.
	 */
	public calendar_Gui() {
		setLayout(new MigLayout("", "[][grow][grow][grow][]", "[][][]"));
		
		JLabel lblCalendarNumber = new JLabel("Calendar Number");
		add(lblCalendarNumber, "cell 0 0,alignx left");
		
		textField = new JTextField();
		add(textField, "flowx,cell 1 0,growx");
		textField.setColumns(10);
		
		
		JLabel lblH_1 = new JLabel("h9");
		add(lblH_1, "cell 2 0");
		
		textField_3 = new JTextField();
		add(textField_3, "cell 3 0,growx");
		textField_3.setColumns(10);
		
		
		JLabel lblDate = new JLabel("Date");
		add(lblDate, "cell 0 1,alignx left");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
	
		
		JLabel lblH_2 = new JLabel("h19");
		add(lblH_2, "cell 2 1,alignx left");
		
		textField_4 = new JTextField();
		add(textField_4, "cell 3 1,growx");
		textField_4.setColumns(10);
		


		JLabel lblH = new JLabel("h8");
		add(lblH, "cell 0 2,alignx left");
		
		textField_2 = new JTextField();
		add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quaryCalender="Calendar_num,"+textField.getText().toString();
				quaryCalender=quaryCalender+",Date,"+textField_1.getText().toString();
				quaryCalender=quaryCalender+",h8,"+textField_2.getText().toString();
				quaryCalender=quaryCalender+",h9,"+textField_3.getText().toString();
				quaryCalender=quaryCalender+",h19,"+textField_4.getText().toString();
				
				ServerGui.textline=ServerGui.textline+quaryCalender.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 4 2");

	}

}
