package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class weekly_report_server_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private String quaryWeeklyReport="";
	/**
	 * Create the panel.
	 */
	public weekly_report_server_Gui() {
		setLayout(new MigLayout("", "[][grow][][grow][][grow]", "[][][][][][]"));
		
		JLabel lblStartWeekDate = new JLabel("Start week date");
		add(lblStartWeekDate, "cell 0 0,alignx left");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		
		JLabel lblWeeklyPatientAmount_2 = new JLabel("Weekly patient amount deviation");
		add(lblWeeklyPatientAmount_2, "cell 2 0,alignx left");
		
		textField_5 = new JTextField();
		add(textField_5, "cell 3 0,growx");
		textField_5.setColumns(10);
	
		JLabel lblClinicNumber = DefaultComponentFactory.getInstance().createLabel("clinic number");
		add(lblClinicNumber, "cell 4 0,alignx left");
		
		textField_10 = new JTextField();
		add(textField_10, "cell 5 0,growx");
		textField_10.setColumns(10);
		
		JLabel lblWeekNumber = new JLabel("Week number");
		add(lblWeekNumber, "cell 0 1,alignx left");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		quaryWeeklyReport=quaryWeeklyReport+",Week_num,"+textField_1.getText().toString();
		
		JLabel lblWeeklyWaitingTime = new JLabel("Weekly waiting time avg");
		add(lblWeeklyWaitingTime, "cell 2 1,alignx left");
		
		textField_6 = new JTextField();
		add(textField_6, "cell 3 1,growx");
		textField_6.setColumns(10);
	
		JLabel lblWholePartial = new JLabel("Whole Partial");
		add(lblWholePartial, "cell 4 1,alignx left");
		
		textField_11 = new JTextField();
		add(textField_11, "cell 5 1,growx");
		textField_11.setColumns(10);
	
		JLabel lblWeeklyPatientAmount = new JLabel("Weekly patient amount avg");
		add(lblWeeklyPatientAmount, "cell 0 2,alignx left");
		
		textField_2 = new JTextField();
		add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
		
		
		JLabel lblWeeklyWaitingTime_1 = new JLabel("Weekly waiting time max");
		add(lblWeeklyWaitingTime_1, "cell 2 2,alignx left");
		
		textField_7 = new JTextField();
		add(textField_7, "cell 3 2,growx");
		textField_7.setColumns(10);
	
		JLabel lblWeeklyPatientamountMax = new JLabel("Weekly patient_amount max");
		add(lblWeeklyPatientamountMax, "cell 0 3,alignx trailing");
		
		textField_3 = new JTextField();
		add(textField_3, "cell 1 3,growx");
		textField_3.setColumns(10);
	
		JLabel lblWeeklyWaitingTime_2 = new JLabel("Weekly waiting time min");
		add(lblWeeklyWaitingTime_2, "cell 2 3,alignx left");
		
		textField_8 = new JTextField();
		add(textField_8, "cell 3 3,growx");
		textField_8.setColumns(10);
		
		JLabel lblWeeklyPatientAmount_1 = new JLabel("Weekly patient amount min");
		add(lblWeeklyPatientAmount_1, "cell 0 4,alignx left");
		
		textField_4 = new JTextField();
		add(textField_4, "cell 1 4,growx");
		textField_4.setColumns(10);
		
		JLabel lblWeeklyWaitingTime_3 = new JLabel("Weekly waiting time deviation");
		add(lblWeeklyWaitingTime_3, "cell 2 4,alignx left");
		
		textField_9 = new JTextField();
		add(textField_9, "cell 3 4,growx");
		textField_9.setColumns(10);
			
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryWeeklyReport="Start_week_date,"+textField.getText().toString();
				quaryWeeklyReport=quaryWeeklyReport+",Week_num,"+textField_1.getText().toString();
				quaryWeeklyReport=quaryWeeklyReport+",Weekly_patient_amount_avg,"+textField_2.getText().toString();
				quaryWeeklyReport=quaryWeeklyReport+",Weekly_patient_amount_max,"+textField_3.getText().toString();
				quaryWeeklyReport=quaryWeeklyReport+",Weekly_patient_amount_min,"+textField_4.getText().toString();
				quaryWeeklyReport=quaryWeeklyReport+",Weekly_patient_amount_deviation,"+textField_5.getText().toString();
				quaryWeeklyReport=quaryWeeklyReport+",Weekly_waiting_time_avg,"+textField_6.getText().toString();
				quaryWeeklyReport=quaryWeeklyReport+",Weekly_waiting_time_max,"+textField_7.getText().toString();
				quaryWeeklyReport=quaryWeeklyReport+",Weekly_waiting_time_min,"+textField_8.getText().toString();
				quaryWeeklyReport=quaryWeeklyReport+",Weekly_waiting_time_deviation,"+textField_9.getText().toString();
				quaryWeeklyReport=quaryWeeklyReport+",clinic_num,"+textField_10.getText().toString();
				quaryWeeklyReport=quaryWeeklyReport+",Whole_Partial,"+textField_11.getText().toString();
				
				ServerGui.textline=ServerGui.textline+quaryWeeklyReport.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
			
		});
		add(btnOk, "cell 5 5");

	}

}
