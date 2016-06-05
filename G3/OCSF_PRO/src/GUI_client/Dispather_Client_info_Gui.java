package GUI_client;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Dispather_Client_info_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Create the panel.
	 */
	public Dispather_Client_info_Gui() {
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][][][][][grow][grow]", "[][][][][][][][][][][][][]"));
		panel.setBackground(Color.WHITE);
		
		JLabel lblFirstName = new JLabel("First Name");
		panel.add(lblFirstName, "cell 4 2,alignx left");
		
		textField = new JTextField();
		panel.add(textField, "cell 5 2,growx");
		textField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		panel.add(lblLastName, "cell 4 3,alignx left");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "cell 5 3,growx");
		textField_1.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		panel.add(lblAddress, "cell 4 4,alignx left");
		
		textField_2 = new JTextField();
		panel.add(textField_2, "cell 5 4,growx");
		textField_2.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		panel.add(lblEmail, "cell 4 5,alignx left");
		
		textField_3 = new JTextField();
		panel.add(textField_3, "cell 5 5,growx");
		textField_3.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		panel.add(lblPhone, "cell 4 6,alignx left");
		
		textField_4 = new JTextField();
		panel.add(textField_4, "cell 5 6,growx");
		textField_4.setColumns(10);
		
		JLabel lblBirthDate = new JLabel("Birth Date");
		panel.add(lblBirthDate, "cell 4 7,alignx trailing");
		
		textField_5 = new JTextField();
		panel.add(textField_5, "cell 5 7,growx");
		textField_5.setColumns(10);
		
		JLabel lblInsuranceValidity = new JLabel("Insurance Validity");
		panel.add(lblInsuranceValidity, "cell 5 9,alignx left");
		
		textField_6 = new JTextField();
		panel.add(textField_6, "cell 6 9,growx");
		textField_6.setColumns(10);
		
		JLabel lblInsuranceLevel = new JLabel("Insurance Level");
		panel.add(lblInsuranceLevel, "cell 5 10,alignx left");
		
		textField_7 = new JTextField();
		panel.add(textField_7, "cell 6 10,growx");
		textField_7.setColumns(10);
		
		JLabel lblGuidanceEnterThe = new JLabel("Guidance: enter the patient's ID and click \"OK\",");
		panel.add(lblGuidanceEnterThe, "cell 1 11 6 1");

		JLabel lblNewLabel = new JLabel("if the paintent exist in the system his information will appear");
		panel.add(lblNewLabel, "cell 2 12 5 1");
		

	}

}
