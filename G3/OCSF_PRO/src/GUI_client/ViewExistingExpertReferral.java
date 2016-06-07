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
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class ViewExistingExpertReferral extends JPanel {
	public JButton btnBfd = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public ViewExistingExpertReferral() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "View Existing Referrals", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 0, 505, 477);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblReportForMonth = new JLabel("View Existing Referrals");
		lblReportForMonth.setBounds(146, 25, 248, 33);
		lblReportForMonth.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblReportForMonth);
		
		btnBfd = new JButton("Back");
		btnBfd.setBounds(178, 373, 108, 41);
		/*btnBfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("ADIDUSH");	 /////////////////////////////////////	 לא נכון! איך חוזרים למסך הקודם???//////////////////////////////////////
			}
		});
		*/
		panel.add(btnBfd);
		btnBfd.setIcon(new ImageIcon(ViewExistingExpertReferral.class.getResource("/javagui/resources/Back.png")));
		
		JLabel label = new JLabel("Referral number:");
		label.setBounds(10, 135, 160, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Referres to expertiese:");
		label_1.setBounds(10, 177, 160, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Referred to expert:");
		label_2.setBounds(10, 217, 160, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("reason:");
		label_3.setBounds(10, 255, 160, 14);
		panel.add(label_3);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(146, 132, 168, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(146, 174, 168, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(146, 214, 168, 20);
		panel.add(textField_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(146, 256, 198, 59);
		panel.add(textArea);
		
		JLabel lblDate = new JLabel("date:");
		lblDate.setBounds(10, 104, 160, 14);
		panel.add(lblDate);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(146, 101, 168, 20);
		panel.add(textField_3);
		
		JLabel lblChooseReferral = new JLabel("choose referral:");
		lblChooseReferral.setBounds(10, 69, 108, 14);
		panel.add(lblChooseReferral);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(146, 66, 168, 20);
		panel.add(comboBox);

	}
}

