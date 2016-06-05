package GUI_client;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class NMonthReport extends JPanel {
	private JTextField textField;
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
	private JTextField textField_12;
	public JButton button = null;

	/**
	 * Create the panel.
	 */
	public NMonthReport() {
		setBackground(Color.ORANGE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("N");
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Last Months Report", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 440, 438);
		add(panel);
		
		JLabel label = new JLabel("Date : ");
		label.setBounds(10, 29, 46, 14);
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(51, 26, 86, 20);
		panel.add(textField);
		
		JLabel lblReportFor = new JLabel("Report for ");
		lblReportFor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReportFor.setBounds(94, 69, 91, 33);
		panel.add(lblReportFor);
		
		button = new JButton("Back");
		
		button.setIcon(new ImageIcon(NMonthReport.class.getResource("/javagui/resources/Back.png")));
		button.setBounds(327, 386, 103, 41);
		panel.add(button);
		
		JLabel lblLastMonths = new JLabel("last months");
		lblLastMonths.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastMonths.setBounds(233, 69, 103, 33);
		panel.add(lblLastMonths);
		
		JLabel label_1 = new JLabel("Number of patient:");
		label_1.setBounds(11, 116, 126, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Waiting time for service:");
		label_2.setBounds(11, 141, 153, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Waiting time for appointment:");
		label_3.setBounds(11, 215, 178, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Number of patient who left:");
		label_4.setBounds(11, 286, 174, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Number of patient who not reached:");
		label_5.setBounds(11, 314, 215, 14);
		panel.add(label_5);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(228, 311, 86, 20);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(184, 283, 130, 20);
		panel.add(textField_3);
		
		JLabel label_6 = new JLabel("- standard deviation:");
		label_6.setBounds(186, 261, 128, 14);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("- Maximum value:");
		label_7.setBounds(186, 246, 108, 14);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("- Minimum value:");
		label_8.setBounds(186, 231, 108, 14);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("- Average value:");
		label_9.setBounds(186, 215, 128, 14);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("- standard deviation:");
		label_10.setBounds(186, 190, 128, 14);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("- Maximum value:");
		label_11.setBounds(186, 175, 108, 14);
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("- Minimum value:");
		label_12.setBounds(186, 160, 108, 14);
		panel.add(label_12);
		
		JLabel label_13 = new JLabel("- Average value:");
		label_13.setBounds(186, 144, 108, 14);
		panel.add(label_13);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(143, 113, 169, 20);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(314, 144, 46, 14);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(314, 175, 46, 14);
		panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(314, 160, 46, 14);
		panel.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(314, 190, 46, 14);
		panel.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(314, 215, 46, 14);
		panel.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(314, 231, 46, 14);
		panel.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(314, 246, 46, 14);
		panel.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(314, 261, 46, 14);
		panel.add(textField_12);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("N");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11"}));
		comboBox.setBounds(186, 70, 37, 27);
		panel.add(comboBox);

	}

}
