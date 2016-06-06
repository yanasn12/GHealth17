package GUI_client;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dispather_Client_info_Gui extends JPanel {
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
	public static JButton btnCommfer;
	private JButton btnSave;
	/**
	 * Create the panel.
	 */
	public Dispather_Client_info_Gui() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[299.00,grow]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setBackground(Color.WHITE);
		panel.setLayout(new MigLayout("", "[][][][160px][][12px][118px][][4px][125px]", "[][][][20px][20px][20px][20px][20px][20px][20px][20px][14px][14px][][][23px][][][][][][][][][][][][][][][][][]"));
				
				JLabel label = new JLabel("Paintent ID:");
				panel.add(label, "cell 4 0");
				
				textField_9 = new JTextField();
				textField_9.setColumns(10);
				panel.add(textField_9, "cell 6 0,growx");
				
				JButton button = new JButton("find");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						textField.setEditable(true);
						textField_1.setEditable(true);
						textField_2.setEditable(true);
						textField_3.setEditable(true);
						textField_4.setEditable(true);
						textField_5.setEditable(true);
						textField_6.setEditable(true);
						textField_7.setEditable(true);
						textField_8.setEditable(true);
						textField_1.setEditable(true);
						btnSave.setVisible(true);
						
					}
				});
				panel.add(button, "cell 7 0");
				
				JLabel lblFirstName = new JLabel("First Name");
				panel.add(lblFirstName, "cell 3 4,alignx left,aligny center");
				
				textField = new JTextField();
				panel.add(textField, "cell 6 4,growx,aligny top");
				textField.setColumns(10);
				textField.setEditable(false);
				
				JLabel lblLastName = new JLabel("Last Name");
				panel.add(lblLastName, "cell 3 5,alignx left,aligny center");
				
				textField_1 = new JTextField();
				panel.add(textField_1, "cell 6 5,growx,aligny top");
				textField_1.setColumns(10);
				textField_1.setEditable(false);
				
				JLabel lblAddress = new JLabel("Address");
				panel.add(lblAddress, "cell 3 6,alignx left,aligny center");
				
				textField_2 = new JTextField();
				panel.add(textField_2, "cell 6 6,growx,aligny top");
				textField_2.setColumns(10);
				textField_2.setEditable(false);
				
				JLabel lblEmail = new JLabel("Email");
				panel.add(lblEmail, "flowx,cell 3 7,alignx left,aligny center");
				
				textField_3 = new JTextField();
				panel.add(textField_3, "cell 6 7,growx,aligny top");
				textField_3.setColumns(10);
				textField_3.setEditable(false);
				
				JLabel lblPhone = new JLabel("Phone");
				panel.add(lblPhone, "cell 3 8,alignx left,aligny center");
				
				textField_4 = new JTextField();
				panel.add(textField_4, "cell 6 8,growx,aligny top");
				textField_4.setColumns(10);
				textField_4.setEditable(false);
				
				JLabel lblBirthDate = new JLabel("Birth Date");
				panel.add(lblBirthDate, "cell 3 9,alignx left,aligny center");
				
				textField_5 = new JTextField();
				panel.add(textField_5, "cell 6 9,growx,aligny top");
				textField_5.setColumns(10);
				textField_5.setEditable(false);
		  
		  btnSave = new JButton("Save");
		  btnSave.setVisible(false);
		  btnSave.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	}
		  });
		  panel.add(btnSave, "cell 7 14");
		  
		  JLabel lblInsuranceValidity = new JLabel("Insurance Validity");
		  panel.add(lblInsuranceValidity, "cell 6 22,alignx left,aligny center");
		  
		  textField_6 = new JTextField();
		  panel.add(textField_6, "cell 9 22,growx,aligny top");
		  textField_6.setColumns(10);
		  textField_6.setEditable(false);
		  
		  JLabel lblInsuranceLevel = new JLabel("Insurance Level");
		  panel.add(lblInsuranceLevel, "cell 6 24,alignx left,aligny center");
		  
		  textField_7 = new JTextField();
		  panel.add(textField_7, "cell 9 24,growx,aligny top");
		  textField_7.setColumns(10);
		  textField_7.setEditable(false);
		  
		  JLabel lblReffralNumber = new JLabel("Reffral Number");
		  panel.add(lblReffralNumber, "cell 3 28,alignx left,aligny center");
		  
		  textField_8 = new JTextField();
		  panel.add(textField_8, "cell 6 28,growx,aligny center");
		  textField_8.setColumns(10);
		  textField_8.setEditable(false);
		  
		   btnCommfer = new JButton("Confirmation");
		   btnCommfer.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {

		   	}
		   });
		   		
		   		JLabel lblGuidanceEnterThe = new JLabel("Guidance: enter the patient's ID and click \"OK\",");
		   		panel.add(lblGuidanceEnterThe, "cell 0 31 10 1,alignx left,aligny top");
		   
		   		JLabel lblNewLabel = new JLabel("if the paintent exist in the system his information will appear");
		   		panel.add(lblNewLabel, "cell 1 32 8 1,alignx left,aligny top");
		   panel.add(btnCommfer, "cell 9 32,alignx left,aligny top");
		
	}

}
