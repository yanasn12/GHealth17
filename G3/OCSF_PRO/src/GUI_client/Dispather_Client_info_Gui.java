package GUI_client;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controllers.Dispatcher;
import Entitys.patient;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	public static patient WorkUser = new patient();
	private String quary;
	private String newquary;
	public static String globletextField=null;
	public static ArrayList<String> array;

	/**
	 * Create the panel.
	 */
	public Dispather_Client_info_Gui() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[299.00,grow]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setBackground(Color.WHITE);
		panel.setLayout(new MigLayout("", "[][][][160px][][12px][118px][][4px][125px][]", "[][][][20px][20px][20px][20px][20px][20px][20px][20px][14px][14px][][][23px][][][][][][][][][][][][][][][][][][][][][]"));
				
				JLabel label = new JLabel("Paintent ID:");
				panel.add(label, "cell 4 0");
				
				textField_9 = new JTextField();
				textField_9.setColumns(10);
				panel.add(textField_9, "cell 6 0,growx");
				
				JButton button = new JButton("find");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Dispatcher.iDInfo(textField_9.getText(),WorkUser)==true)
							{
								textField.setText(WorkUser.getFirst_name());
								textField_1.setText(WorkUser.getLast_name());
								textField_2.setText(WorkUser.getAddress());
								textField_3.setText(WorkUser.getEmail());
								textField_4.setText(WorkUser.getPhone());
								textField_5.setText(WorkUser.getBirth_date());
								if(WorkUser.getInsurance_level().equals("-1"))
									{
										textField_6.setEditable(true);
										textField_6.setText("enter new Insurance Level");
									}
								else
									textField_6.setText(WorkUser.getInsurance_level());
								
								if(WorkUser.getInsurance_validity().equals("-1"))
									{
										textField_7.setEditable(true);
										textField_7.setText("enter new Insurance validity");
									}
								else
									textField_7.setText(WorkUser.getInsurance_validity());
								
								btnCommfer.setEnabled(true);
								textField_8.setEditable(true);
								globletextField=textField_8.getText().toString();
								
								
							}
						else
							{
							JOptionPane.showMessageDialog(null,"New patient file been create, enter new info.", "new patient",JOptionPane.PLAIN_MESSAGE);
								textField.setEditable(true);
								textField.setText("");
								textField_1.setEditable(true);
								textField_1.setText("");
								textField_2.setEditable(true);
								textField_2.setText("");
								textField_3.setEditable(true);
								textField_3.setText("");
								textField_4.setEditable(true);
								textField_4.setText("");
								textField_5.setEditable(true);
								textField_5.setText("");
								textField_6.setEditable(true);	
								textField_6.setText("");
								textField_7.setEditable(true);									
								textField_7.setText("");
								
								textField_1.setEditable(true);
								btnSave.setVisible(true);
								btnSave.setVisible(true);
							}
						
					}
				});
				panel.add(button, "cell 7 0");
				
				JLabel lblFirstName = new JLabel("First Name");
				panel.add(lblFirstName, "cell 3 4,alignx left,aligny center");
				
				textField = new JTextField();
				panel.add(textField, "cell 6 4 3 1,growx,aligny top");
				textField.setColumns(10);
				textField.setEditable(false);
				
				
				
				JLabel lblLastName = new JLabel("Last Name");
				panel.add(lblLastName, "cell 3 5,alignx left,aligny center");
				
				textField_1 = new JTextField();
				panel.add(textField_1, "cell 6 5 3 1,growx,aligny top");
				textField_1.setColumns(10);
				textField_1.setEditable(false);
				
				JLabel lblAddress = new JLabel("Address");
				panel.add(lblAddress, "cell 3 6,alignx left,aligny center");
				
				textField_2 = new JTextField();
				panel.add(textField_2, "cell 6 6 3 1,growx,aligny top");
				textField_2.setColumns(10);
				textField_2.setEditable(false);
				
				JLabel lblEmail = new JLabel("Email");
				panel.add(lblEmail, "flowx,cell 3 7,alignx left,aligny center");
				
				textField_3 = new JTextField();
				panel.add(textField_3, "cell 6 7 3 1,growx,aligny top");
				textField_3.setColumns(10);
				textField_3.setEditable(false);
				
				JLabel lblPhone = new JLabel("Phone");
				panel.add(lblPhone, "cell 3 8,alignx left,aligny center");
				
				textField_4 = new JTextField();
				panel.add(textField_4, "cell 6 8 3 1,growx,aligny top");
				textField_4.setColumns(10);
				textField_4.setEditable(false);
				
				JLabel lblBirthDate = new JLabel("Birth Date");
				panel.add(lblBirthDate, "cell 3 9,alignx left,aligny center");
				
				textField_5 = new JTextField();
				panel.add(textField_5, "cell 6 9 3 1,growx,aligny top");
				textField_5.setColumns(10);
				textField_5.setEditable(false);
		  
		  JLabel lblInsuranceValidity = new JLabel("Insurance Validity");
		  panel.add(lblInsuranceValidity, "cell 6 18,alignx left,aligny center");
		  
		  textField_6 = new JTextField();
		  panel.add(textField_6, "cell 7 18 3 1,growx,aligny top");
		  textField_6.setColumns(10);
		  textField_6.setEditable(false);
		  
		  JLabel lblInsuranceLevel = new JLabel("Insurance Level");
		  panel.add(lblInsuranceLevel, "cell 6 21,alignx left,aligny center");
		  
		  textField_7 = new JTextField();
		  panel.add(textField_7, "cell 7 21 3 1,growx,aligny top");
		  textField_7.setColumns(10);
		  textField_7.setEditable(false);
		  
		  btnSave = new JButton("Save");
		  btnSave.setVisible(false);
		  btnSave.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  	/**creating the info to fil in people table*/
		  	quary="person_id,"+textField_9.getText().toString();
		  	quary=quary+",first_name,"+textField.getText().toString();
		  	quary=quary+",last_name,"+textField_1.getText().toString();
		  	for(int i=0;i<+textField_2.getText().toString().length();i++)
		  		if((textField_2.getText().toString().indexOf(',', i))!=-1)
		  		{
		  			StringBuilder newtext = new StringBuilder(textField_2.getText().toString());
		  			newtext.setCharAt(i, '-');
		  			textField_2.setText(newtext.toString());
		  		}	
		  	quary=quary+",address,"+textField_2.getText().toString();
		  	quary=quary+",email,"+textField_3.getText().toString();
		  	quary=quary+",phone,"+textField_4.getText().toString();		  	
		  	quary=quary+",birth_date,"+textField_5.getText().toString();
		  	newquary=quary;
		  	/**creating the info to fil in patients table + takeing the info from person_id	*/
			if(textField_6.getText().toString().equals(""))
			  	quary=quary+",insurance_level,1";	
			else
				quary=quary+",insurance_level,"+textField_6.getText().toString();
			
			if(textField_7.getText().toString().equals(""))
				quary=quary+",insurance_validity,1";
			else
				quary=quary+",insurance_validity,"+textField_7.getText().toString();			

			if(Dispatcher.newPatient(quary, WorkUser)==true)
				{
				  	textField_8.setEditable(true);
				  	globletextField=textField_8.getText().toString();
					btnCommfer.setEnabled(true);
				}
		  	else
			  	{
					JOptionPane.showMessageDialog(null,"System could not enter new data to DB", "Error : Dispatcher",JOptionPane.ERROR_MESSAGE);
			  	}
		  	
		  	if(WorkUser.getInsurance_validity().equals("-1") || WorkUser.getInsurance_level().equals("-1"))
		  	{
		  		quary="update:patients:"+newquary+",insurance_level,"+textField_6.getText().toString()+",insurance_validity,"+textField_7.getText().toString();
		  		Dispatcher.updateInsurance(quary, WorkUser);
		  	}
		  	}
		  });
		  panel.add(btnSave, "cell 10 23,alignx right");
		  
		  JLabel lblReffralNumber = new JLabel("Reffral Number");
		  panel.add(lblReffralNumber, "cell 3 32,alignx left,aligny center");
		  
		  textField_8 = new JTextField();
		  panel.add(textField_8, "cell 6 32,growx,aligny center");
		  textField_8.setColumns(10);
		  textField_8.setEditable(false);
		  
		   btnCommfer = new JButton("Confirmation");
		   btnCommfer.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		globletextField=textField_8.getText().toString();
				if(Dispatcher.findReff(Dispather_Client_info_Gui.globletextField, Dispather_Client_info_Gui.WorkUser)==true)
				{
					Dispather_Gui_main.tabbedPane.setEnabledAt(1, true);
					 array= Dispatcher.listOfExperts(Dispather_Client_info_Gui.WorkUser.getReferral_num(), Dispather_Client_info_Gui.WorkUser);

					 
				}
				else
				JOptionPane.showMessageDialog(null,"patients doesnt have "+globletextField+" in is medical file", "Dispatcher",JOptionPane.PLAIN_MESSAGE);

		   			
		   	}
		   });
		   btnCommfer.setEnabled(false);
		   		JLabel lblGuidanceEnterThe = new JLabel("Guidance: enter the patient's ID and click \"OK\",");
		   		panel.add(lblGuidanceEnterThe, "cell 0 35 10 1,alignx left,aligny top");
		   
		   		JLabel lblNewLabel = new JLabel("if the paintent exist in the system his information will appear");
		   		panel.add(lblNewLabel, "cell 1 36 8 1,alignx left,aligny top");
		   panel.add(btnCommfer, "cell 9 36,alignx left,aligny top");
		
	}

}
