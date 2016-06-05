package GUI_client;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MonthlyReports extends JInternalFrame {
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
	public JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonthlyReports frame = new MonthlyReports();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MonthlyReports() {
		setBounds(0, 0, 488, 501);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Monthly Report", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 457, 438);
		getContentPane().add(panel);
		
		JLabel label = new JLabel("Date : ");
		label.setBounds(10, 29, 46, 14);
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(51, 26, 86, 20);
		panel.add(textField);
		
		JLabel lblMonthlyReport = new JLabel("Monthly Report");
		lblMonthlyReport.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMonthlyReport.setBounds(134, 57, 168, 33);
		panel.add(lblMonthlyReport);
		
		JLabel label_2 = new JLabel("Number of patient:");
		label_2.setBounds(10, 140, 114, 14);
		panel.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(134, 137, 168, 20);
		panel.add(textField_1);
		
		JLabel label_3 = new JLabel("Waiting time for service:");
		label_3.setBounds(10, 165, 153, 14);
		panel.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(310, 168, 46, 14);
		panel.add(textField_2);
		
		JLabel label_4 = new JLabel("Waiting time for appointment:");
		label_4.setBounds(10, 239, 179, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Number of patient who left:");
		label_5.setBounds(10, 310, 168, 14);
		panel.add(label_5);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(175, 307, 127, 20);
		panel.add(textField_3);
		
		JLabel label_6 = new JLabel("Number of patient who not reached:");
		label_6.setBounds(10, 338, 212, 14);
		panel.add(label_6);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(219, 335, 83, 20);
		panel.add(textField_4);
		
		JLabel label_7 = new JLabel("- Average value:");
		label_7.setBounds(186, 168, 106, 14);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("- Minimum value:");
		label_8.setBounds(186, 184, 106, 14);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("- Maximum value:");
		label_9.setBounds(186, 199, 106, 14);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("- standard deviation:");
		label_10.setBounds(186, 214, 126, 14);
		panel.add(label_10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(310, 184, 46, 14);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(310, 199, 46, 14);
		panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(310, 214, 46, 14);
		panel.add(textField_7);
		
		JLabel label_11 = new JLabel("- Maximum value:");
		label_11.setBounds(186, 270, 106, 14);
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("- Average value:");
		label_12.setBounds(186, 239, 106, 14);
		panel.add(label_12);
		
		JLabel label_13 = new JLabel("- Minimum value:");
		label_13.setBounds(186, 255, 106, 14);
		panel.add(label_13);
		
		JLabel label_14 = new JLabel("- standard deviation:");
		label_14.setBounds(186, 285, 126, 14);
		panel.add(label_14);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(310, 285, 46, 14);
		panel.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(310, 270, 46, 14);
		panel.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(310, 255, 46, 14);
		panel.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(310, 239, 46, 14);
		panel.add(textField_11);
		
		button = new JButton("Back");
	
		button.setIcon(new ImageIcon(MonthlyReports.class.getResource("/javagui/resources/Back.png")));
		button.setBounds(322, 386, 108, 41);
		panel.add(button);

	}

}
