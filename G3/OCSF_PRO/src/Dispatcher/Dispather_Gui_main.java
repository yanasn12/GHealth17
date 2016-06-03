package Dispatcher;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Gui_server.medical_worker_Server;
import jdbc.mysqlConnection;

import javax.swing.ImageIcon;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Dispather_Gui_main extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JButton btnOk;
	private String quaryinfo="";
	private String text;
	public ArrayList<String> quary = new ArrayList<String>();
	private String [] parts;
	private JPanel newText;
	private JLabel lblGuidanceEnterThe;
	private JPanel panel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dispather_Gui_main frame = new Dispather_Gui_main();
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
	public Dispather_Gui_main() {
		setBackground(Color.WHITE);
		setTitle("Dispather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 496);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[213.00px,grow][23.00px][51px][80px][85px][4px][153px][47px]", "[54.00px][43.00px][22.00px][20px][20px][20px][20px][20px][20px][20px][14px][][]"));
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 0 1 1 5,grow");

		panel_1.setLayout(null);
		
		newText= new Dispather_Gui_buttons();
		newText.setBounds(0, 0, 210, 363);
		
		panel_1.add(newText);
		

		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Dispather_Gui_main.class.getResource("/javagui/resources/GHealthlogo.png")));
		contentPane.add(lblNewLabel, "cell 0 0,alignx center,aligny top");
		
		
		JLabel lblPaintentId = new JLabel("Paintent ID:");
		contentPane.add(lblPaintentId, "cell 4 0,alignx left,aligny center");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 6 0,growx,aligny center");
		textField.setColumns(10);
		
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//change to after getting info
				quaryinfo="pullbykey:people:person_id,"+textField.getText().toString();
				quary=mysqlConnection.ActionMode(quaryinfo);
				if(quary!=null)
				{
					setTitle("Dispather-new client");
					parts=quary.get(0).split(",");
					
					text=parts[2];
					textField_3.setText(text);
					textField_3.setEnabled(false);
	
					text=parts[3];
					textField_4.setText(text);
					textField_4.setEnabled(false);

					text=parts[4];
					textField_5.setText(text);
					textField_5.setEnabled(false);

					text=parts[5];
					textField_6.setText(text);		
					textField_6.setEnabled(false);
					
					text=parts[6];
					textField_7.setText(text);		
					textField_7.setEnabled(false);
					
					text=parts[7];
					textField_8.setText(text);		
					textField_8.setEnabled(false);

				quaryinfo="pull:patients:person_id,"+quary.get(0);
				quary=mysqlConnection.ActionMode(quaryinfo);
				parts=quary.get(0).split(",");
				System.out.println(parts.length);
				text=parts[3];
				textField_1.setText(text);
				textField_1.setEnabled(false);

				text=parts[4];
				textField_2.setText(text);
				textField_2.setEnabled(false);
				}
				else
				{
					textField_1.setText("");
					textField_1.setEnabled(true);
					textField_2.setText("");
					textField_2.setEnabled(true);
					textField_3.setText("");
					textField_3.setEnabled(true);
					textField_4.setText("");
					textField_4.setEnabled(true);
					textField_5.setText("");
					textField_5.setEnabled(true);
					textField_6.setText("");
					textField_6.setEnabled(true);
					textField_7.setText("");
					textField_7.setEnabled(true);
					textField_8.setText("");
					textField_8.setEnabled(true);
					
				}
			}
		});
		
		contentPane.add(btnOk, "cell 7 1,alignx left,aligny top");		

		JLabel lblFirstName = new JLabel("First Name");
		contentPane.add(lblFirstName, "cell 2 2,alignx left,aligny center");
		
		textField_3 = new JTextField();
		contentPane.add(textField_3, "cell 4 2 3 1,growx,aligny center");
		textField_3.setColumns(10);
		
		
		JLabel lblLastName = new JLabel("Last Name");
		contentPane.add(lblLastName, "cell 2 3,alignx left,aligny center");
		
		textField_4 = new JTextField();
		contentPane.add(textField_4, "cell 4 3 3 1,growx,aligny top");
		textField_4.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		contentPane.add(lblAddress, "cell 2 4,alignx left,aligny center");
		
		textField_5 = new JTextField();
		contentPane.add(textField_5, "cell 4 4 3 1,growx,aligny top");
		textField_5.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		contentPane.add(lblEmail, "cell 2 5,alignx left,aligny center");
		
		textField_6 = new JTextField();
		contentPane.add(textField_6, "cell 4 5 3 1,growx,aligny top");
		textField_6.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		contentPane.add(lblPhone, "cell 2 6,alignx left,aligny center");
		
		textField_7 = new JTextField();
		contentPane.add(textField_7, "cell 4 6 3 1,growx,aligny top");
		textField_7.setColumns(10);
		
		JLabel lblBirthDate = new JLabel("Birth Date");
		contentPane.add(lblBirthDate, "cell 2 7,alignx left,aligny center");
		
		textField_8 = new JTextField();
		contentPane.add(textField_8, "cell 4 7 3 1,growx,aligny top");
		textField_8.setColumns(10);
		
		JLabel lblInsuranceValidity = new JLabel("Insurance Validity");
		contentPane.add(lblInsuranceValidity, "cell 4 8,alignx left,aligny center");
		
		textField_2 = new JTextField();
		contentPane.add(textField_2, "cell 6 8,growx,aligny top");
		textField_2.setColumns(10);
		
		JLabel lblInsuranceLevel = new JLabel("Insurance Level");
		contentPane.add(lblInsuranceLevel, "cell 4 9,alignx left,aligny center");
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 6 9,growx,aligny top");
		textField_1.setColumns(10);
		
		lblGuidanceEnterThe = new JLabel("Guidance: enter the patient's ID and click \"OK\",\n if the paintent exist in the system his information will appear ");
		
		contentPane.add(lblGuidanceEnterThe, "cell 0 12 8 1,alignx left,aligny top");
		lblGuidanceEnterThe.setForeground(Color.BLUE);


		
	}

}
