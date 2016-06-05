package GUI_client;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CurrReport extends JPanel {
	private JTextField textField;
	private JTextField txtFd;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_3;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	public JButton btnBfd = null;

	/**
	 * Create the panel.
	 */
	public CurrReport() {
		setBackground(Color.PINK);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Current Report", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 440, 438);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblDate = new JLabel("Date : ");
		lblDate.setBounds(10, 29, 46, 14);
		panel.add(lblDate);
		
		textField = new JTextField();
		textField.setBounds(51, 26, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblReportForMonth = new JLabel("Report for month ");
		lblReportForMonth.setBounds(92, 57, 168, 33);
		lblReportForMonth.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblReportForMonth);
		
		txtFd = new JTextField();
		txtFd.setBounds(241, 61, 103, 28);
		txtFd.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(txtFd);
		txtFd.setColumns(10);
		
		JLabel lblNumberOfPatient = new JLabel("Number of patient:");
		lblNumberOfPatient.setBounds(10, 140, 103, 14);
		panel.add(lblNumberOfPatient);
		
		textField_1 = new JTextField();
		textField_1.setBounds(105, 137, 168, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Waiting time for service:");
		lblNewLabel.setBounds(10, 165, 126, 14);
		panel.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(282, 168, 46, 14);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblWaitingTimeFor = new JLabel("Waiting time for appointment:");
		lblWaitingTimeFor.setBounds(10, 239, 153, 14);
		panel.add(lblWaitingTimeFor);
		
		JLabel lblNumberOfPatient_1 = new JLabel("Number of patient who left:");
		lblNumberOfPatient_1.setBounds(10, 310, 153, 14);
		panel.add(lblNumberOfPatient_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(146, 307, 127, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNumberOfPatient_2 = new JLabel("Number of patient who not reached:");
		lblNumberOfPatient_2.setBounds(10, 338, 192, 14);
		panel.add(lblNumberOfPatient_2);
		
		textField_5 = new JTextField();
		textField_5.setBounds(190, 335, 83, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		btnBfd = new JButton("Back");
		btnBfd.setBounds(339, 386, 91, 41);
		/*btnBfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("ADIDUSH");	 /////////////////////////////////////	 לא נכון! איך חוזרים למסך הקודם???//////////////////////////////////////
			}
		});
		*/
		panel.add(btnBfd);
		btnBfd.setIcon(new ImageIcon(CurrReport.class.getResource("/javagui/resources/Back.png")));
		
		JLabel lblEverage = new JLabel("- Average value:");
		lblEverage.setBounds(167, 168, 82, 14);
		panel.add(lblEverage);
		
		JLabel lblMinimumValue = new JLabel("- Minimum value:");
		lblMinimumValue.setBounds(167, 184, 82, 14);
		panel.add(lblMinimumValue);
		
		JLabel lblMaximumValue = new JLabel("- Maximum value:");
		lblMaximumValue.setBounds(167, 199, 91, 14);
		panel.add(lblMaximumValue);
		
		JLabel lblStandardDeviation = new JLabel("- standard deviation:");
		lblStandardDeviation.setBounds(167, 214, 108, 14);
		panel.add(lblStandardDeviation);
		
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
		
		JLabel label = new JLabel("- Maximum value:");
		label.setBounds(167, 270, 91, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("- Average value:");
		label_1.setBounds(167, 239, 82, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("- Minimum value:");
		label_2.setBounds(167, 255, 82, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("- standard deviation:");
		label_3.setBounds(167, 285, 108, 14);
		panel.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(282, 285, 46, 14);
		panel.add(textField_3);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(282, 270, 46, 14);
		panel.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(282, 255, 46, 14);
		panel.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(282, 239, 46, 14);
		panel.add(textField_11);

	}
}
