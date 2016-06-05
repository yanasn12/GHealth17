package GUI_client;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PeriodReport extends JPanel {
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	public JButton button = null;

	
	/**
	 * Create the panel.
	 */
	public PeriodReport() {
		setLayout(null);
		
		JPanel panelPeriod = new JPanel();
		panelPeriod.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Period Report", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPeriod.setBounds(10, 11, 440, 438);
		add(panelPeriod);
		panelPeriod.setLayout(null);
		
		JLabel label = new JLabel("Date : ");
		label.setBounds(10, 29, 46, 14);
		panelPeriod.add(label);
		
		textField = new JTextField();
		textField.setBounds(51, 26, 86, 20);
		textField.setColumns(10);
		panelPeriod.add(textField);
		
		JLabel lblReportForMonths = new JLabel("Report for months");
		lblReportForMonths.setBounds(26, 69, 135, 33);
		lblReportForMonths.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelPeriod.add(lblReportForMonths);
		
		button = new JButton("Back");
		
		button.setBounds(327, 386, 103, 41);
		
		button.setIcon(new ImageIcon(PeriodReport.class.getResource("/javagui/resources/Back.png")));
		panelPeriod.add(button,null);
		
		JLabel lblUntill = new JLabel("untill");
		lblUntill.setBounds(255, 69, 46, 33);
		lblUntill.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelPeriod.add(lblUntill);
		
		JLabel label_1 = new JLabel("Number of patient:");
		label_1.setBounds(11, 116, 126, 14);
		panelPeriod.add(label_1);
		
		JLabel label_2 = new JLabel("Waiting time for service:");
		label_2.setBounds(11, 141, 165, 14);
		panelPeriod.add(label_2);
		
		JLabel label_3 = new JLabel("Waiting time for appointment:");
		label_3.setBounds(11, 215, 169, 14);
		panelPeriod.add(label_3);
		
		JLabel label_4 = new JLabel("Number of patient who left:");
		label_4.setBounds(11, 286, 165, 14);
		panelPeriod.add(label_4);
		
		JLabel label_5 = new JLabel("Number of patient who not reached:");
		label_5.setBounds(11, 314, 212, 14);
		panelPeriod.add(label_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(220, 311, 85, 20);
		textField_2.setColumns(10);
		panelPeriod.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(176, 283, 129, 20);
		textField_3.setColumns(10);
		panelPeriod.add(textField_3);
		
		JLabel label_6 = new JLabel("- standard deviation:");
		label_6.setBounds(186, 261, 119, 14);
		panelPeriod.add(label_6);
		
		JLabel label_7 = new JLabel("- Maximum value:");
		label_7.setBounds(186, 246, 104, 14);
		panelPeriod.add(label_7);
		
		JLabel label_8 = new JLabel("- Minimum value:");
		label_8.setBounds(186, 231, 104, 14);
		panelPeriod.add(label_8);
		
		JLabel label_9 = new JLabel("- Average value:");
		label_9.setBounds(186, 215, 104, 14);
		panelPeriod.add(label_9);
		
		JLabel label_10 = new JLabel("- standard deviation:");
		label_10.setBounds(186, 190, 119, 14);
		panelPeriod.add(label_10);
		
		JLabel label_11 = new JLabel("- Maximum value:");
		label_11.setBounds(186, 175, 104, 14);
		panelPeriod.add(label_11);
		
		JLabel label_12 = new JLabel("- Minimum value:");
		label_12.setBounds(186, 160, 104, 14);
		panelPeriod.add(label_12);
		
		JLabel label_13 = new JLabel("- Average value:");
		label_13.setBounds(186, 144, 104, 14);
		panelPeriod.add(label_13);
		
		textField_4 = new JTextField();
		textField_4.setBounds(135, 113, 169, 20);
		textField_4.setColumns(10);
		panelPeriod.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(315, 144, 46, 14);
		textField_5.setColumns(10);
		panelPeriod.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setBounds(315, 175, 46, 14);
		textField_6.setColumns(10);
		panelPeriod.add(textField_6);
		
		textField_8 = new JTextField();
		textField_8.setBounds(315, 160, 46, 14);
		textField_8.setColumns(10);
		panelPeriod.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setBounds(315, 190, 46, 14);
		textField_9.setColumns(10);
		panelPeriod.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setBounds(315, 215, 46, 14);
		textField_10.setColumns(10);
		panelPeriod.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setBounds(315, 231, 46, 14);
		textField_11.setColumns(10);
		panelPeriod.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setBounds(315, 246, 46, 14);
		textField_12.setColumns(10);
		panelPeriod.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setBounds(315, 261, 46, 14);
		textField_13.setColumns(10);
		panelPeriod.add(textField_13);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		comboBox.setBounds(159, 77, 93, 20);
		panelPeriod.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		comboBox_1.setBounds(301, 77, 103, 20);
		panelPeriod.add(comboBox_1);

	}

}
