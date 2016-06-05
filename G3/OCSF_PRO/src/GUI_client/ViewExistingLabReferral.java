/*package javagui.views;

import javax.swing.JPanel;

public class ViewExistingLabReferral extends JPanel {

	/**
	 * Create the panel.
	 
	public ViewExistingLabReferral() {

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

public class ViewExistingLabReferral extends JPanel {
	public JButton btnBfd = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public ViewExistingLabReferral() {
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
		btnBfd.setIcon(new ImageIcon(ViewExistingLabReferral.class.getResource("/javagui/resources/Back.png")));
		
		JLabel label = new JLabel("Test ID:");
		label.setBounds(10, 69, 160, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Opening Date:");
		label_1.setBounds(10, 122, 160, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Closing Date:");
		label_2.setBounds(10, 147, 160, 14);
		panel.add(label_2);
		
		JLabel lblNotes = new JLabel("notes:");
		lblNotes.setBounds(10, 197, 160, 14);
		panel.add(lblNotes);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(146, 94, 168, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(146, 122, 168, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(146, 147, 168, 20);
		panel.add(textField_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(146, 198, 198, 41);
		panel.add(textArea);
		
		JLabel lblDate = new JLabel("Test Type:");
		lblDate.setBounds(10, 94, 160, 14);
		panel.add(lblDate);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(146, 66, 168, 20);
		panel.add(textField_3);
		
		JLabel lblUrgency = new JLabel("Urgency:");
		lblUrgency.setBounds(10, 172, 160, 14);
		panel.add(lblUrgency);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(146, 172, 168, 20);
		panel.add(textField_4);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(10, 249, 160, 14);
		panel.add(lblResult);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(146, 250, 198, 41);
		panel.add(textArea_1);

	}
}


