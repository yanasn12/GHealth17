package javagui.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

public class hello extends JInternalFrame {
	private JButton btnAccept;
	private JTextField textField_TestId;
	private JTextField textField_closingDate;
	private JTextField textField_OpeninfDate;
	private JTextField textField_referringExpert;
	private JComboBox comboBox_TestType;
	private JTextField textField_Results;
	private JScrollPane scrollPane_Notes;
	private JTextArea textArea_Notes;
	private String inputLab;
	/**
	 * Create the frame.
	 */

	public hello() {
		setFrameIcon(new ImageIcon(hello.class.getResource("/javagui/resources/img16x16/laboratory-icon.png")));
		setTitle("Hello Lab worker");
		setClosable(true);
		setIconifiable(true);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(hello.class.getResource("/javagui/resources/GHealthlogo.png")));
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();	
			}
		});
		button.setIcon(new ImageIcon(hello.class.getResource("/javagui/resources/img16x16/logout5.png")));
		
		Box verticalBox = Box.createVerticalBox();
		
		JLabel lblPatientName = new JLabel("patient Name");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPatientName)
							.addGap(81)
							.addComponent(verticalBox, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addGap(97)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(label)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(67)
								.addComponent(verticalBox, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(29)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblPatientName))
					.addGap(28)
					.addComponent(tabbedPane)
					.addContainerGap())
		);
		
		JPanel view_referral = new JPanel();
		tabbedPane.addTab("view referral", null, view_referral, null);
		view_referral.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnNewButton = new JButton("eyes\t - Dr. Levi                20/5/16                  eye test");
	
		view_referral.add(btnNewButton, "4, 2, 23, 3");
		
		JButton btnCardiologyDrcohen = new JButton("Cardiology- Dr.Cohen       2/6/16                blood test");
		view_referral.add(btnCardiologyDrcohen, "4, 8, 23, 3");
		
		JButton btnOrthpedeDruziel = new JButton("Orthpede- Dr.Uziel              5/6/16                    urine test");
		view_referral.add(btnOrthpedeDruziel, "4, 14, 23, 3");
		
		JPanel referral = new JPanel();
		tabbedPane.addTab("referral", null, referral, null);
		tabbedPane.setEnabledAt(1, false);
		
		JLabel lblTypeOfExperties = new JLabel("Type of experties:");
		
		JLabel lblExpertName = new JLabel("Expert name:");
		
		JLabel lblDate = new JLabel("Date:");
		
		JLabel lblTestType_1 = new JLabel("Test type:");
		
		JLabel lblNewLabel_logo = new JLabel("");
		lblNewLabel_logo.setIcon(new ImageIcon(hello.class.getResource("/javagui/resources/labGHealthlogo.png")));
		GroupLayout gl_referral = new GroupLayout(referral);
		gl_referral.setHorizontalGroup(
			gl_referral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_referral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_referral.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_logo)
						.addComponent(lblTypeOfExperties)
						.addComponent(lblExpertName)
						.addComponent(lblDate)
						.addComponent(lblTestType_1))
					.addContainerGap(169, Short.MAX_VALUE))
		);
		gl_referral.setVerticalGroup(
			gl_referral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_referral.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_logo)
					.addGap(46)
					.addComponent(lblTypeOfExperties)
					.addGap(18)
					.addComponent(lblExpertName)
					.addGap(13)
					.addComponent(lblDate)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTestType_1)
					.addContainerGap(174, Short.MAX_VALUE))
		);
		referral.setLayout(gl_referral);
		
		JPanel insert_results = new JPanel();
		tabbedPane.addTab("insert results", null, insert_results, null);
		tabbedPane.setEnabledAt(2, false);
		
		JPanel panel_Data = new JPanel();
		panel_Data.setBorder(new TitledBorder(null, "Referral Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_buttons = new JPanel();
		GroupLayout gl_insert_results = new GroupLayout(insert_results);
		gl_insert_results.setHorizontalGroup(
			gl_insert_results.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_insert_results.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_insert_results.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_Data, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_buttons, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_insert_results.setVerticalGroup(
			gl_insert_results.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_insert_results.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_Data, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_buttons, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(11))
		);
		
		JLabel lblNewLabel = new JLabel("Test Id");
		
		JLabel lblOpeningDate = new JLabel("Opening date:");
		
		JLabel lblClosingDate = new JLabel("Closing date:");
		
		JLabel lblReferringExpert = new JLabel("Referring expert:");
		
		JLabel lblNotes = new JLabel("Results:");
		
		textField_TestId = new JTextField();
		textField_TestId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLab=textField_TestId.getText();
				inputLab=inputLab+",";
			}
		});
		textField_TestId.setColumns(10);
		
		textField_closingDate = new JTextField();
		textField_closingDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLab=inputLab+textField_closingDate.getText()+ ",";
			}
		});
		textField_closingDate.setColumns(10);
		
		textField_OpeninfDate = new JTextField();
		textField_OpeninfDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLab=inputLab + textField_OpeninfDate.getText() + ",";
			}
		});
		textField_OpeninfDate.setColumns(10);
		
		textField_referringExpert = new JTextField();
		textField_referringExpert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLab=inputLab + textField_referringExpert.getText() + ",";
			}
		});
		textField_referringExpert.setColumns(10);
		
		JLabel lblTestType = new JLabel("Test type:");
		
		comboBox_TestType = new JComboBox();
		
		textField_Results = new JTextField();
		textField_Results.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLab=inputLab + textField_Results.getText() + ",";
			}
		});
		textField_Results.setColumns(10);
		
		scrollPane_Notes = new JScrollPane();
		
		JLabel lblResult = new JLabel("Notes:");
		
		JPanel panel_Errors = new JPanel();
		
		JLabel lblOpen = new JLabel("open");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		
		GroupLayout gl_panel_Data = new GroupLayout(panel_Data);
		gl_panel_Data.setHorizontalGroup(
			gl_panel_Data.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Data.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Data.createParallelGroup(Alignment.LEADING)
						.addComponent(lblReferringExpert)
						.addComponent(lblNewLabel)
						.addComponent(lblTestType)
						.addComponent(lblOpeningDate)
						.addComponent(lblClosingDate)
						.addComponent(lblOpen)
						.addGroup(gl_panel_Data.createSequentialGroup()
							.addComponent(lblNotes)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(lblResult))
					.addGroup(gl_panel_Data.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_Data.createSequentialGroup()
							.addGap(46)
							.addGroup(gl_panel_Data.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_Data.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_referringExpert, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_closingDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_OpeninfDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_TestId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_Results, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(chckbxNewCheckBox))
								.addComponent(comboBox_TestType, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(panel_Errors, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Data.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_Notes, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_Data.setVerticalGroup(
			gl_panel_Data.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Data.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Data.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Data.createSequentialGroup()
							.addGroup(gl_panel_Data.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(textField_TestId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_Data.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTestType)
								.addComponent(comboBox_TestType, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_panel_Data.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblOpeningDate)
								.addComponent(textField_OpeninfDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_Data.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblClosingDate)
								.addComponent(textField_closingDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(13)
							.addGroup(gl_panel_Data.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblReferringExpert)
								.addComponent(textField_referringExpert, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_Data.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_Results, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNotes)))
						.addComponent(panel_Errors, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_Data.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_Data.createSequentialGroup()
							.addComponent(lblOpen)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblResult, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addGap(25))
						.addGroup(gl_panel_Data.createSequentialGroup()
							.addComponent(chckbxNewCheckBox)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane_Notes, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		textArea_Notes = new JTextArea();
		scrollPane_Notes.setColumnHeaderView(textArea_Notes);
		panel_Data.setLayout(gl_panel_Data);
		
		btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLab=inputLab + scrollPane_Notes.getViewport().getView();
			}
		});
		btnAccept.setIcon(new ImageIcon(hello.class.getResource("/javagui/resources/img16x16/Green_check.png")));
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.setIcon(new ImageIcon(hello.class.getResource("/javagui/resources/x1.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_panel_buttons = new GroupLayout(panel_buttons);
		gl_panel_buttons.setHorizontalGroup(
			gl_panel_buttons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_buttons.createSequentialGroup()
					.addGap(123)
					.addComponent(btnAccept)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		gl_panel_buttons.setVerticalGroup(
			gl_panel_buttons.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_buttons.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addGroup(gl_panel_buttons.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAccept))
					.addContainerGap())
		);
		panel_buttons.setLayout(gl_panel_buttons);
		insert_results.setLayout(gl_insert_results);
		getContentPane().setLayout(groupLayout);
		

	}
}
