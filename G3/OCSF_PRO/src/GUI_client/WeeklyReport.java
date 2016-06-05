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
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WeeklyReport extends JInternalFrame {
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
	public JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeeklyReport frame = new WeeklyReport();
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
	public WeeklyReport() {
		setBounds(0, 0, 488, 500);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Weekly Report", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 440, 438);
		getContentPane().add(panel);
		
		JLabel label = new JLabel("Date : ");
		label.setBounds(10, 29, 46, 14);
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(51, 26, 86, 20);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("Weekly report ");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_1.setBounds(134, 62, 144, 33);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Number of patient:");
		label_2.setBounds(10, 140, 127, 14);
		panel.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(127, 137, 168, 20);
		panel.add(textField_1);
		
		JLabel label_3 = new JLabel("Waiting time for service:");
		label_3.setBounds(10, 165, 153, 14);
		panel.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(315, 169, 46, 14);
		panel.add(textField_2);
		
		JLabel label_4 = new JLabel("Waiting time for appointment:");
		label_4.setBounds(10, 239, 178, 14);
		panel.add(label_4);
		
		button = new JButton("Back");
	
	
		button.setIcon(new ImageIcon(WeeklyReport.class.getResource("/javagui/resources/Back.png")));
		button.setBounds(322, 386, 108, 41);
		panel.add(button);
		
		JLabel label_5 = new JLabel("- Average value:");
		label_5.setBounds(186, 169, 109, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("- Minimum value:");
		label_6.setBounds(186, 185, 109, 14);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("- Maximum value:");
		label_7.setBounds(186, 200, 109, 14);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("- standard deviation:");
		label_8.setBounds(186, 215, 127, 14);
		panel.add(label_8);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(315, 185, 46, 14);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(315, 200, 46, 14);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(315, 215, 46, 14);
		panel.add(textField_5);
		
		JLabel label_9 = new JLabel("- Maximum value:");
		label_9.setBounds(186, 271, 109, 14);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("- Average value:");
		label_10.setBounds(186, 240, 109, 14);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("- Minimum value:");
		label_11.setBounds(186, 256, 108, 14);
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("- standard deviation:");
		label_12.setBounds(186, 286, 127, 14);
		panel.add(label_12);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(315, 286, 46, 14);
		panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(315, 271, 46, 14);
		panel.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(315, 256, 46, 14);
		panel.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(315, 240, 46, 14);
		panel.add(textField_9);

	}

}
