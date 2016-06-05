package GUI_client;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

public class WeeklyReport extends JPanel {
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;

	/**
	 * Create the panel.
	 */
	public WeeklyReport() {
		setBackground(Color.BLUE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Weekly Report", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 440, 438);
		add(panel);
		
		JLabel label = new JLabel("Date : ");
		label.setBounds(10, 29, 46, 14);
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(51, 26, 86, 20);
		panel.add(textField);
		
		JLabel lblReportmonthly = new JLabel("Weekly report ");
		lblReportmonthly.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReportmonthly.setBounds(134, 62, 144, 33);
		panel.add(lblReportmonthly);
		
		JLabel label_2 = new JLabel("Number of patient:");
		label_2.setBounds(10, 140, 103, 14);
		panel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(105, 137, 168, 20);
		panel.add(textField_2);
		
		JLabel label_3 = new JLabel("Waiting time for service:");
		label_3.setBounds(10, 165, 126, 14);
		panel.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(282, 168, 46, 14);
		panel.add(textField_3);
		
		JLabel label_4 = new JLabel("Waiting time for appointment:");
		label_4.setBounds(10, 239, 153, 14);
		panel.add(label_4);
		
		JButton button = new JButton("Back");
		button.setBounds(339, 386, 91, 41);
		panel.add(button);
		
		JLabel label_7 = new JLabel("- Average value:");
		label_7.setBounds(167, 168, 82, 14);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("- Minimum value:");
		label_8.setBounds(167, 184, 82, 14);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("- Maximum value:");
		label_9.setBounds(167, 199, 91, 14);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("- standard deviation:");
		label_10.setBounds(167, 214, 108, 14);
		panel.add(label_10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(282, 184, 46, 14);
		panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(282, 199, 46, 14);
		panel.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(282, 214, 46, 14);
		panel.add(textField_8);
		
		JLabel label_11 = new JLabel("- Maximum value:");
		label_11.setBounds(167, 270, 91, 14);
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("- Average value:");
		label_12.setBounds(167, 239, 82, 14);
		panel.add(label_12);
		
		JLabel label_13 = new JLabel("- Minimum value:");
		label_13.setBounds(167, 255, 82, 14);
		panel.add(label_13);
		
		JLabel label_14 = new JLabel("- standard deviation:");
		label_14.setBounds(167, 285, 108, 14);
		panel.add(label_14);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(282, 285, 46, 14);
		panel.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(282, 270, 46, 14);
		panel.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(282, 255, 46, 14);
		panel.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(282, 239, 46, 14);
		panel.add(textField_12);

	}

}
