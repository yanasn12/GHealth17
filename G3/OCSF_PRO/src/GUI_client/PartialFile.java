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

public class PartialFile extends JPanel {
	private JTextField textField_1;
	public JButton btnBfd = null;
	private JTextField textField;
	private JTextField textField_7;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Create the panel.
	 */
	public PartialFile() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Partial File", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 0, 505, 477);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblReportForMonth = new JLabel("Partial File");
		lblReportForMonth.setBounds(146, 25, 168, 33);
		lblReportForMonth.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblReportForMonth);
		
		JLabel lblNumberOfPatient = new JLabel("patient ID:");
		lblNumberOfPatient.setBounds(10, 155, 160, 14);
		panel.add(lblNumberOfPatient);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 152, 168, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNumberOfPatient_1 = new JLabel("type of insurance:");
		lblNumberOfPatient_1.setBounds(10, 249, 160, 14);
		panel.add(lblNumberOfPatient_1);
		
		btnBfd = new JButton("Back");
		btnBfd.setBounds(178, 373, 108, 41);
		/*btnBfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("ADIDUSH");	 /////////////////////////////////////	 לא נכון! איך חוזרים למסך הקודם???//////////////////////////////////////
			}
		});
		*/
		panel.add(btnBfd);
		btnBfd.setIcon(new ImageIcon(PartialFile.class.getResource("/javagui/resources/Back.png")));
		
		JLabel lblPatientname = new JLabel("patient's Name:");
		lblPatientname.setBounds(10, 199, 160, 14);
		panel.add(lblPatientname);
		
		textField = new JTextField();
		textField.setBounds(180, 196, 168, 20);
		textField.setColumns(10);
		panel.add(textField);
		
		JLabel lblMedicalFileNumber = new JLabel("medical file number:");
		lblMedicalFileNumber.setBounds(10, 104, 160, 14);
		panel.add(lblMedicalFileNumber);
		
		textField_7 = new JTextField();
		textField_7.setBounds(180, 101, 168, 20);
		textField_7.setColumns(10);
		panel.add(textField_7);
		
		textField_4 = new JTextField();
		textField_4.setBounds(180, 246, 168, 20);
		textField_4.setColumns(10);
		panel.add(textField_4);
		
		JLabel lblLeavingDate = new JLabel("leaving date:");
		lblLeavingDate.setBounds(10, 300, 160, 14);
		panel.add(lblLeavingDate);
		
		textField_5 = new JTextField();
		textField_5.setBounds(180, 297, 168, 20);
		textField_5.setColumns(10);
		panel.add(textField_5);

	}
}
