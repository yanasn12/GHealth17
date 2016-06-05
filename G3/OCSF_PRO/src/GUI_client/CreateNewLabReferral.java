/*package javagui.views;

import javax.swing.JPanel;

public class CreateNewLabReferral extends JPanel {

	/**
	 * Create the panel.
	 
	public CreateNewLabReferral() {

	}

}*/

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

public class CreateNewLabReferral extends JPanel {
	private JTextField textField_1;
	public JButton btnBfd = null;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public CreateNewLabReferral() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Create New Referral", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 0, 505, 477);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblReportForMonth = new JLabel("Create new referral");
		lblReportForMonth.setBounds(146, 25, 168, 33);
		lblReportForMonth.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblReportForMonth);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 105, 168, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		btnBfd = new JButton("Back");
		btnBfd.setBounds(275, 375, 108, 41);
		/*btnBfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("ADIDUSH");	 /////////////////////////////////////	 לא נכון! איך חוזרים למסך הקודם???//////////////////////////////////////
			}
		});
		*/
		panel.add(btnBfd);
		btnBfd.setIcon(new ImageIcon(CreateNewLabReferral.class.getResource("/javagui/resources/Back.png")));
		
		textField = new JTextField();
		textField.setBounds(180, 136, 168, 20);
		textField.setColumns(10);
		panel.add(textField);
		
		JLabel lblPatientAdress = new JLabel("reason:");
		lblPatientAdress.setBounds(10, 304, 160, 14);
		panel.add(lblPatientAdress);
		
		textField_2 = new JTextField();
		textField_2.setBounds(180, 163, 168, 20);
		textField_2.setColumns(10);
		panel.add(textField_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(185, 305, 198, 59);
		panel.add(textArea);
		
		JButton btnApply = new JButton("Apply");
		btnApply.setBounds(176, 375, 89, 41);
		panel.add(btnApply);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(180, 74, 168, 20);
		panel.add(textField_3);
		
		JLabel label = new JLabel("Test ID:");
		label.setBounds(10, 77, 160, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Test Type:");
		label_1.setBounds(10, 108, 160, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Opening Date:");
		label_2.setBounds(10, 139, 160, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Closing Date:");
		label_3.setBounds(10, 166, 160, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Urgency:");
		label_4.setBounds(10, 194, 160, 14);
		panel.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(180, 194, 168, 20);
		panel.add(textField_4);
		
		JLabel label_5 = new JLabel("notes:");
		label_5.setBounds(10, 228, 160, 14);
		panel.add(label_5);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(185, 222, 198, 59);
		panel.add(textArea_1);

	}
}

