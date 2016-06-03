package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class week_in_month_server_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private String quaryWeekInMonth="";
	/**
	 * Create the panel.
	 */
	public week_in_month_server_Gui() {
		setLayout(new MigLayout("", "[][grow][]", "[][][]"));
		
		JLabel lblStartWeekDate = new JLabel("Start week date");
		add(lblStartWeekDate, "cell 0 0,alignx left");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblMonthDate = new JLabel("Month date");
		add(lblMonthDate, "cell 0 1,alignx left");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryWeekInMonth="Start_week_date,"+textField.getText().toString();
				quaryWeekInMonth=quaryWeekInMonth+",Month_date,"+textField_1.getText().toString();

				ServerGui.textline=ServerGui.textline+quaryWeekInMonth.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 2 2");

	}

}
