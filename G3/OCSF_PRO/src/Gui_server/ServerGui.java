package Gui_server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc.mysqlConnection;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerGui extends JFrame {

	
	private JPanel contentPane;
	private JPanel newText;
	private JPanel panel_1;
	private JInternalFrame internalFrame;
	private JInternalFrame internalFrame_1;
	private JButton btnUpdate;
	private JButton btnPull;
	private JButton btnPullbykey;
	private JButton btnPush;
	private JButton btnDelete;
	private JButton btnCheckme;
	private JButton btnPullbydemand;
	private JButton SetTable;
	private JButton btnExperts;
	private JButton btnLabs;
	private JButton btnMedicalworker;
	private JButton btnOk;
	private String commend="pull";
	private String tables="labs";
	public static String textline="";
	private JTextField txtTextfiled;
	private JButton btnAppointments;
	private JButton btnAppointmentsfile;
	private JButton btnCalendar;
	private JButton btnClinic;
	private JButton btnExpertise;
	private JButton btnExpertsinsurancebyexpertise;
	private JButton btnInsurances;
	private JButton btnLabtestsfile;
	private JButton btnLabTest;
	private JButton btnLabsofclinc;
	private JButton btnMedicalfiles;
	private JButton btnPatients;
	private JButton btnPeople;
	private JButton btnReferencesfiles;
	private JButton btnReferrals;
	private JButton btnReportfile;
	private JButton btnTeststype;
	private JButton btnWeekinmonth;
	private JButton btnWeeklyreport;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGui frame = new ServerGui();
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
	
	public ServerGui() {
		this.setTitle("GuiServer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	
			
		panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		txtTextfiled = new JTextField();
		txtTextfiled.setBounds(0, 0, 557, 94);
		txtTextfiled.setText("Gudie: to begin please choose a funcsion and then a table (one or more)");
		panel_1.add(txtTextfiled);
		
		txtTextfiled.setColumns(10);
//>>		
		internalFrame_1 = new JInternalFrame("Data-Tables");
		internalFrame_1.setBounds(0, 234, 539, 284);
		panel_1.add(internalFrame_1);
		GridBagLayout gridBagLayout_1 = new GridBagLayout();
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		internalFrame_1.getContentPane().setLayout(gridBagLayout_1);
		
		btnAppointments = new JButton("appointments");
		btnAppointments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="appointments";
			}
		});
		GridBagConstraints gbc_btnAppointments = new GridBagConstraints();
		gbc_btnAppointments.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAppointments.insets = new Insets(0, 0, 5, 5);
		gbc_btnAppointments.gridx = 0;
		gbc_btnAppointments.gridy = 3;
		internalFrame_1.getContentPane().add(btnAppointments, gbc_btnAppointments);
		
		btnAppointmentsfile = new JButton("appointments_file");
		btnAppointmentsfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tables="appointments_file";
			}
		});
		
		btnMedicalfiles = new JButton("medical_files");
		btnMedicalfiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="medical_files";
			}
		});
		GridBagConstraints gbc_btnMedicalfiles = new GridBagConstraints();
		gbc_btnMedicalfiles.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMedicalfiles.insets = new Insets(0, 0, 5, 5);
		gbc_btnMedicalfiles.gridx = 1;
		gbc_btnMedicalfiles.gridy = 3;
		internalFrame_1.getContentPane().add(btnMedicalfiles, gbc_btnMedicalfiles);
		
		btnWeeklyreport = new JButton("weekly_report");
		btnWeeklyreport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="weekly_report";
			}
		});
		GridBagConstraints gbc_btnWeeklyreport = new GridBagConstraints();
		gbc_btnWeeklyreport.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnWeeklyreport.insets = new Insets(0, 0, 5, 5);
		gbc_btnWeeklyreport.gridx = 2;
		gbc_btnWeeklyreport.gridy = 3;
		internalFrame_1.getContentPane().add(btnWeeklyreport, gbc_btnWeeklyreport);
		GridBagConstraints gbc_btnAppointmentsfile = new GridBagConstraints();
		gbc_btnAppointmentsfile.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAppointmentsfile.insets = new Insets(0, 0, 5, 5);
		gbc_btnAppointmentsfile.gridx = 0;
		gbc_btnAppointmentsfile.gridy = 4;
		internalFrame_1.getContentPane().add(btnAppointmentsfile, gbc_btnAppointmentsfile);
		
		btnCalendar = new JButton("calendar");
		btnCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tables="calendar";
			}
		});
		
		btnPatients = new JButton("patients");
		btnPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="patients";
			}
		});
		GridBagConstraints gbc_btnPatients = new GridBagConstraints();
		gbc_btnPatients.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPatients.insets = new Insets(0, 0, 5, 5);
		gbc_btnPatients.gridx = 1;
		gbc_btnPatients.gridy = 4;
		internalFrame_1.getContentPane().add(btnPatients, gbc_btnPatients);
		
		btnLabtestsfile = new JButton("lab_tests_file");
		btnLabtestsfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="lab_tests_file";
			}
		});
		GridBagConstraints gbc_btnLabtestsfile = new GridBagConstraints();
		gbc_btnLabtestsfile.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLabtestsfile.insets = new Insets(0, 0, 5, 5);
		gbc_btnLabtestsfile.gridx = 2;
		gbc_btnLabtestsfile.gridy = 4;
		internalFrame_1.getContentPane().add(btnLabtestsfile, gbc_btnLabtestsfile);
		GridBagConstraints gbc_btnCalendar = new GridBagConstraints();
		gbc_btnCalendar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCalendar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalendar.gridx = 0;
		gbc_btnCalendar.gridy = 5;
		internalFrame_1.getContentPane().add(btnCalendar, gbc_btnCalendar);
		
		btnClinic = new JButton("clinic");
		btnClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tables="clinic";
			}
		});
		
		btnPeople = new JButton("people");
		btnPeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="people";
			}
		});
		GridBagConstraints gbc_btnPeople = new GridBagConstraints();
		gbc_btnPeople.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPeople.insets = new Insets(0, 0, 5, 5);
		gbc_btnPeople.gridx = 1;
		gbc_btnPeople.gridy = 5;
		internalFrame_1.getContentPane().add(btnPeople, gbc_btnPeople);
		
		btnWeekinmonth = new JButton("week_in_month");
		btnWeekinmonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="week_in_month";
			}
		});
		GridBagConstraints gbc_btnWeekinmonth = new GridBagConstraints();
		gbc_btnWeekinmonth.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnWeekinmonth.insets = new Insets(0, 0, 5, 5);
		gbc_btnWeekinmonth.gridx = 2;
		gbc_btnWeekinmonth.gridy = 5;
		internalFrame_1.getContentPane().add(btnWeekinmonth, gbc_btnWeekinmonth);
		GridBagConstraints gbc_btnClinic = new GridBagConstraints();
		gbc_btnClinic.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClinic.insets = new Insets(0, 0, 5, 5);
		gbc_btnClinic.gridx = 0;
		gbc_btnClinic.gridy = 6;
		internalFrame_1.getContentPane().add(btnClinic, gbc_btnClinic);
		
		btnExpertise = new JButton("expertise");
		btnExpertise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tables="expertise";
			}
		});
		
		btnReferencesfiles = new JButton("references_files");
		btnReferencesfiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="references_files";
			}
		});
		GridBagConstraints gbc_btnReferencesfiles = new GridBagConstraints();
		gbc_btnReferencesfiles.insets = new Insets(0, 0, 5, 5);
		gbc_btnReferencesfiles.gridx = 1;
		gbc_btnReferencesfiles.gridy = 6;
		internalFrame_1.getContentPane().add(btnReferencesfiles, gbc_btnReferencesfiles);
		
		btnTeststype = new JButton("tests_type");
		btnTeststype.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="tests_type";
			}
		});
		GridBagConstraints gbc_btnTeststype = new GridBagConstraints();
		gbc_btnTeststype.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTeststype.insets = new Insets(0, 0, 5, 5);
		gbc_btnTeststype.gridx = 2;
		gbc_btnTeststype.gridy = 6;
		internalFrame_1.getContentPane().add(btnTeststype, gbc_btnTeststype);
		GridBagConstraints gbc_btnExpertise = new GridBagConstraints();
		gbc_btnExpertise.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExpertise.insets = new Insets(0, 0, 5, 5);
		gbc_btnExpertise.gridx = 0;
		gbc_btnExpertise.gridy = 7;
		internalFrame_1.getContentPane().add(btnExpertise, gbc_btnExpertise);
		
		btnExpertsinsurancebyexpertise = new JButton("expertsinsurance_by_expertise");
		btnExpertsinsurancebyexpertise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="expertsinsurance_by_expertise";
			}
		});
		
		btnReferrals = new JButton("referrals");
		btnReferrals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="referrals";
			}
		});
		GridBagConstraints gbc_btnReferrals = new GridBagConstraints();
		gbc_btnReferrals.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReferrals.insets = new Insets(0, 0, 5, 5);
		gbc_btnReferrals.gridx = 1;
		gbc_btnReferrals.gridy = 7;
		internalFrame_1.getContentPane().add(btnReferrals, gbc_btnReferrals);
		
		btnInsurances = new JButton("insurances");
		btnInsurances.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="insurances";
			}
		});
		GridBagConstraints gbc_btnInsurances = new GridBagConstraints();
		gbc_btnInsurances.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInsurances.insets = new Insets(0, 0, 5, 5);
		gbc_btnInsurances.gridx = 2;
		gbc_btnInsurances.gridy = 7;
		internalFrame_1.getContentPane().add(btnInsurances, gbc_btnInsurances);
		GridBagConstraints gbc_btnExpertsinsurancebyexpertise = new GridBagConstraints();
		gbc_btnExpertsinsurancebyexpertise.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExpertsinsurancebyexpertise.insets = new Insets(0, 0, 5, 5);
		gbc_btnExpertsinsurancebyexpertise.gridx = 0;
		gbc_btnExpertsinsurancebyexpertise.gridy = 8;
		internalFrame_1.getContentPane().add(btnExpertsinsurancebyexpertise, gbc_btnExpertsinsurancebyexpertise);
		
		btnReportfile = new JButton("report_file");
		btnReportfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="report_file";
			}
		});
		GridBagConstraints gbc_btnReportfile = new GridBagConstraints();
		gbc_btnReportfile.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReportfile.insets = new Insets(0, 0, 5, 5);
		gbc_btnReportfile.gridx = 1;
		gbc_btnReportfile.gridy = 8;
		internalFrame_1.getContentPane().add(btnReportfile, gbc_btnReportfile);
		
		btnLabs = new JButton("labs");
		btnLabs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tables="labs";
			}
		});
		GridBagConstraints gbc_btnLabs = new GridBagConstraints();
		gbc_btnLabs.fill = GridBagConstraints.BOTH;
		gbc_btnLabs.insets = new Insets(0, 0, 5, 5);
		gbc_btnLabs.gridx = 2;
		gbc_btnLabs.gridy = 8;
		internalFrame_1.getContentPane().add(btnLabs, gbc_btnLabs);
		
		btnExperts = new JButton("experts");
		btnExperts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tables="experts";
			}
		});
		GridBagConstraints gbc_btnExperts = new GridBagConstraints();
		gbc_btnExperts.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExperts.insets = new Insets(0, 0, 5, 5);
		gbc_btnExperts.gridx = 0;
		gbc_btnExperts.gridy = 9;
		internalFrame_1.getContentPane().add(btnExperts, gbc_btnExperts);
		
		btnLabsofclinc = new JButton("labs_of_clinc");
		btnLabsofclinc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="labs_of_clinc";
			}
		});
		GridBagConstraints gbc_btnLabsofclinc = new GridBagConstraints();
		gbc_btnLabsofclinc.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLabsofclinc.insets = new Insets(0, 0, 5, 5);
		gbc_btnLabsofclinc.gridx = 1;
		gbc_btnLabsofclinc.gridy = 9;
		internalFrame_1.getContentPane().add(btnLabsofclinc, gbc_btnLabsofclinc);
		
		btnMedicalworker = new JButton("medical_worker");
		btnMedicalworker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tables="medical_worker";
			}
		});
		GridBagConstraints gbc_btnMedicalworker = new GridBagConstraints();
		gbc_btnMedicalworker.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMedicalworker.insets = new Insets(0, 0, 5, 5);
		gbc_btnMedicalworker.gridx = 2;
		gbc_btnMedicalworker.gridy = 9;
		internalFrame_1.getContentPane().add(btnMedicalworker, gbc_btnMedicalworker);
		
		btnLabTest = new JButton("lab test");
		btnLabTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="lab_test";
			}
		});
		GridBagConstraints gbc_btnLabTest = new GridBagConstraints();
		gbc_btnLabTest.fill = GridBagConstraints.BOTH;
		gbc_btnLabTest.insets = new Insets(0, 0, 5, 5);
		gbc_btnLabTest.gridx = 0;
		gbc_btnLabTest.gridy = 10;
		internalFrame_1.getContentPane().add(btnLabTest, gbc_btnLabTest);
		
		JButton btnLabtest = new JButton("lab tests file");
		btnLabtest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tables="lab_test_file";
			}
		});
		GridBagConstraints gbc_btnLabtest = new GridBagConstraints();
		gbc_btnLabtest.fill = GridBagConstraints.BOTH;
		gbc_btnLabtest.insets = new Insets(0, 0, 5, 5);
		gbc_btnLabtest.gridx = 1;
		gbc_btnLabtest.gridy = 10;
		internalFrame_1.getContentPane().add(btnLabtest, gbc_btnLabtest);
		if(tables!=null)
			

		
		internalFrame_1.setVisible(true);
		

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		internalFrame = new JInternalFrame("Functions");
		contentPane.add(internalFrame, BorderLayout.EAST);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{67, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		internalFrame.getContentPane().setLayout(gridBagLayout);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commend="update";
				

			}
		});
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.fill = GridBagConstraints.BOTH;
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 0);
		gbc_btnUpdate.gridx = 0;
		gbc_btnUpdate.gridy = 0;
		internalFrame.getContentPane().add(btnUpdate, gbc_btnUpdate);
		
		btnPull = new JButton("Pull");
		btnPull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commend="pull";
			}
		});
		GridBagConstraints gbc_btnPull = new GridBagConstraints();
		gbc_btnPull.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPull.insets = new Insets(0, 0, 5, 0);
		gbc_btnPull.gridx = 0;
		gbc_btnPull.gridy = 1;
		internalFrame.getContentPane().add(btnPull, gbc_btnPull);
		
		btnPullbykey = new JButton("pullByKey");
		btnPullbykey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commend="pullbykey";
			}
		});
		GridBagConstraints gbc_btnPullbykey = new GridBagConstraints();
		gbc_btnPullbykey.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPullbykey.insets = new Insets(0, 0, 5, 0);
		gbc_btnPullbykey.gridx = 0;
		gbc_btnPullbykey.gridy = 2;
		internalFrame.getContentPane().add(btnPullbykey, gbc_btnPullbykey);
		
		btnPush = new JButton("Push");
		btnPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commend="push";
			}
		});
		GridBagConstraints gbc_btnPush = new GridBagConstraints();
		gbc_btnPush.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPush.insets = new Insets(0, 0, 5, 0);
		gbc_btnPush.gridx = 0;
		gbc_btnPush.gridy = 3;
		internalFrame.getContentPane().add(btnPush, gbc_btnPush);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commend="delete";
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 4;
		internalFrame.getContentPane().add(btnDelete, gbc_btnDelete);
		
		btnCheckme = new JButton("checkme");
		btnCheckme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commend="checkme";
			}
		});
		GridBagConstraints gbc_btnCheckme = new GridBagConstraints();
		gbc_btnCheckme.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCheckme.insets = new Insets(0, 0, 5, 0);
		gbc_btnCheckme.gridx = 0;
		gbc_btnCheckme.gridy = 5;
		internalFrame.getContentPane().add(btnCheckme, gbc_btnCheckme);
		
		btnPullbydemand = new JButton("pullByDemand");
		btnPullbydemand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commend="pullByDemand";
			}
		});
		GridBagConstraints gbc_btnPullbydemand = new GridBagConstraints();
		gbc_btnPullbydemand.insets = new Insets(0, 0, 5, 0);
		gbc_btnPullbydemand.gridx = 0;
		gbc_btnPullbydemand.gridy = 6;
		internalFrame.getContentPane().add(btnPullbydemand, gbc_btnPullbydemand);
		
		SetTable = new JButton("Set");
		GridBagConstraints gbc_SetTable = new GridBagConstraints();
		gbc_SetTable.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTable.insets = new Insets(0, 0, 5, 0);
		gbc_SetTable.gridx = 0;
		gbc_SetTable.gridy = 13;
		internalFrame.getContentPane().add(SetTable, gbc_SetTable);
		
	
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 16;
		createEvents();
		internalFrame.setVisible(true);
//==>>
	}

private void createEvents()
{
	SetTable.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				redraw();			
		}
	});
}
private void redraw()
{
	this.setTitle("GuiServer");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 708, 567);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new BorderLayout(0, 0));
		
	panel_1 = new JPanel();
	contentPane.add(panel_1);
	panel_1.setLayout(null);
	
//>>
	textline=commend+":"+tables+":"+textline;
	switch(tables){
		case ("medical_worker"):{ newText = new medical_worker_Server(); break;	}
		case ("labs"):			{	newText = new labs_Server_Gui();	break;	}
		case ("appointments"):  { 	newText =new appointments_Gui();	break;}
		case ("appointments_file"):{newText =new appointments_File_Gui();	break;}
		case ("calendar"):		{newText = new calendar_Gui();	break;}
		case ("clinic"):		{newText = new clinic_Gui();	break;}
		case ("expertise"):		{newText = new expertise_Gui();	break;}
		case ("experts"):		{newText = new experts_server_Gui();	break;}
		case ("expertsinsurance_by_expertise"): {newText = new expertsinsurance_by_expertise_serverGui();	break;}
		case ("insurances"): {newText = new insurances_server_Gui();	break;}
		case ("lab_test_file"): {newText = new lab_test_file_server_Gui();	break;}
		case ("lab_test"): {newText = new lab_tests_server_Gui();	break;}
		case ("labs_of_clinc"): {newText = new labs_of_clinc_server_Gui();	break;}
		case ("medical_files"): {newText = new medical_files_server_Gui();	break;}
		case ("patients"): {newText = new patients_server_gui();	break;}
		case ("people"): {newText = new people_server_Gui();	break;}
		case ("references_files"): {newText = new references_files_server_Gui();	break;}
		case ("referrals"): {newText = new referrals_server_Gui();	break;}
		case ("report_file"): {newText = new report_file_server_Gui();	break;}
		case ("tests_type"): {newText = new tests_type_server_Gui();	break;}
		case ("week_in_month"): {newText = new week_in_month_server_Gui();	break;}
		case ("weekly_report"): {newText = new weekly_report_server_Gui();	break;}
		case ("lab_tests_file"): {newText = new  lab_tests_file_server_Gui();	break;}
	}
	newText.setBounds(0, 0, 557, 160);

	panel_1.add(newText);
//>>	
	internalFrame_1 = new JInternalFrame("Data-Tables");
	internalFrame_1.setBounds(0, 234, 539, 284);
	panel_1.add(internalFrame_1);
	GridBagLayout gridBagLayout_1 = new GridBagLayout();
	gridBagLayout_1.columnWidths = new int[]{0, 0, 0, 0, 0};
	gridBagLayout_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gridBagLayout_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	internalFrame_1.getContentPane().setLayout(gridBagLayout_1);
	
	btnAppointments = new JButton("appointments");
	btnAppointments.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="appointments";
		}
	});
	GridBagConstraints gbc_btnAppointments = new GridBagConstraints();
	gbc_btnAppointments.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnAppointments.insets = new Insets(0, 0, 5, 5);
	gbc_btnAppointments.gridx = 0;
	gbc_btnAppointments.gridy = 3;
	internalFrame_1.getContentPane().add(btnAppointments, gbc_btnAppointments);
	
	btnAppointmentsfile = new JButton("appointments_file");
	btnAppointmentsfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			tables="appointments_file";
		}
	});
	
	btnMedicalfiles = new JButton("medical_files");
	btnMedicalfiles.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="medical_files";
		}
	});
	GridBagConstraints gbc_btnMedicalfiles = new GridBagConstraints();
	gbc_btnMedicalfiles.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnMedicalfiles.insets = new Insets(0, 0, 5, 5);
	gbc_btnMedicalfiles.gridx = 1;
	gbc_btnMedicalfiles.gridy = 3;
	internalFrame_1.getContentPane().add(btnMedicalfiles, gbc_btnMedicalfiles);
	
	btnWeeklyreport = new JButton("weekly_report");
	btnWeeklyreport.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="weekly_report";
		}
	});
	GridBagConstraints gbc_btnWeeklyreport = new GridBagConstraints();
	gbc_btnWeeklyreport.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnWeeklyreport.insets = new Insets(0, 0, 5, 5);
	gbc_btnWeeklyreport.gridx = 2;
	gbc_btnWeeklyreport.gridy = 3;
	internalFrame_1.getContentPane().add(btnWeeklyreport, gbc_btnWeeklyreport);
	GridBagConstraints gbc_btnAppointmentsfile = new GridBagConstraints();
	gbc_btnAppointmentsfile.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnAppointmentsfile.insets = new Insets(0, 0, 5, 5);
	gbc_btnAppointmentsfile.gridx = 0;
	gbc_btnAppointmentsfile.gridy = 4;
	internalFrame_1.getContentPane().add(btnAppointmentsfile, gbc_btnAppointmentsfile);
	
	btnCalendar = new JButton("calendar");
	btnCalendar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			tables="calendar";
		}
	});
	
	btnPatients = new JButton("patients");
	btnPatients.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="patients";
		}
	});
	GridBagConstraints gbc_btnPatients = new GridBagConstraints();
	gbc_btnPatients.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnPatients.insets = new Insets(0, 0, 5, 5);
	gbc_btnPatients.gridx = 1;
	gbc_btnPatients.gridy = 4;
	internalFrame_1.getContentPane().add(btnPatients, gbc_btnPatients);
	
	btnLabtestsfile = new JButton("lab_tests_file");
	btnLabtestsfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="lab_tests_file";
		}
	});
	GridBagConstraints gbc_btnLabtestsfile = new GridBagConstraints();
	gbc_btnLabtestsfile.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnLabtestsfile.insets = new Insets(0, 0, 5, 5);
	gbc_btnLabtestsfile.gridx = 2;
	gbc_btnLabtestsfile.gridy = 4;
	internalFrame_1.getContentPane().add(btnLabtestsfile, gbc_btnLabtestsfile);
	GridBagConstraints gbc_btnCalendar = new GridBagConstraints();
	gbc_btnCalendar.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnCalendar.insets = new Insets(0, 0, 5, 5);
	gbc_btnCalendar.gridx = 0;
	gbc_btnCalendar.gridy = 5;
	internalFrame_1.getContentPane().add(btnCalendar, gbc_btnCalendar);
	
	btnClinic = new JButton("clinic");
	btnClinic.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			tables="clinic";
		}
	});
	
	btnPeople = new JButton("people");
	btnPeople.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="people";
		}
	});
	GridBagConstraints gbc_btnPeople = new GridBagConstraints();
	gbc_btnPeople.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnPeople.insets = new Insets(0, 0, 5, 5);
	gbc_btnPeople.gridx = 1;
	gbc_btnPeople.gridy = 5;
	internalFrame_1.getContentPane().add(btnPeople, gbc_btnPeople);
	
	btnWeekinmonth = new JButton("week_in_month");
	btnWeekinmonth.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="week_in_month";
		}
	});
	GridBagConstraints gbc_btnWeekinmonth = new GridBagConstraints();
	gbc_btnWeekinmonth.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnWeekinmonth.insets = new Insets(0, 0, 5, 5);
	gbc_btnWeekinmonth.gridx = 2;
	gbc_btnWeekinmonth.gridy = 5;
	internalFrame_1.getContentPane().add(btnWeekinmonth, gbc_btnWeekinmonth);
	GridBagConstraints gbc_btnClinic = new GridBagConstraints();
	gbc_btnClinic.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnClinic.insets = new Insets(0, 0, 5, 5);
	gbc_btnClinic.gridx = 0;
	gbc_btnClinic.gridy = 6;
	internalFrame_1.getContentPane().add(btnClinic, gbc_btnClinic);
	
	btnExpertise = new JButton("expertise");
	btnExpertise.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			tables="expertise";
		}
	});
	
	btnReferencesfiles = new JButton("references_files");
	btnReferencesfiles.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="references_files";
		}
	});
	GridBagConstraints gbc_btnReferencesfiles = new GridBagConstraints();
	gbc_btnReferencesfiles.insets = new Insets(0, 0, 5, 5);
	gbc_btnReferencesfiles.gridx = 1;
	gbc_btnReferencesfiles.gridy = 6;
	internalFrame_1.getContentPane().add(btnReferencesfiles, gbc_btnReferencesfiles);
	
	btnTeststype = new JButton("tests_type");
	btnTeststype.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="tests_type";
		}
	});
	GridBagConstraints gbc_btnTeststype = new GridBagConstraints();
	gbc_btnTeststype.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnTeststype.insets = new Insets(0, 0, 5, 5);
	gbc_btnTeststype.gridx = 2;
	gbc_btnTeststype.gridy = 6;
	internalFrame_1.getContentPane().add(btnTeststype, gbc_btnTeststype);
	GridBagConstraints gbc_btnExpertise = new GridBagConstraints();
	gbc_btnExpertise.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnExpertise.insets = new Insets(0, 0, 5, 5);
	gbc_btnExpertise.gridx = 0;
	gbc_btnExpertise.gridy = 7;
	internalFrame_1.getContentPane().add(btnExpertise, gbc_btnExpertise);
	
	btnExpertsinsurancebyexpertise = new JButton("expertsinsurance_by_expertise");
	btnExpertsinsurancebyexpertise.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="expertsinsurance_by_expertise";
		}
	});
	
	btnReferrals = new JButton("referrals");
	btnReferrals.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="referrals";
		}
	});
	GridBagConstraints gbc_btnReferrals = new GridBagConstraints();
	gbc_btnReferrals.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnReferrals.insets = new Insets(0, 0, 5, 5);
	gbc_btnReferrals.gridx = 1;
	gbc_btnReferrals.gridy = 7;
	internalFrame_1.getContentPane().add(btnReferrals, gbc_btnReferrals);
	
	btnInsurances = new JButton("insurances");
	btnInsurances.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="insurances";
		}
	});
	GridBagConstraints gbc_btnInsurances = new GridBagConstraints();
	gbc_btnInsurances.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnInsurances.insets = new Insets(0, 0, 5, 5);
	gbc_btnInsurances.gridx = 2;
	gbc_btnInsurances.gridy = 7;
	internalFrame_1.getContentPane().add(btnInsurances, gbc_btnInsurances);
	GridBagConstraints gbc_btnExpertsinsurancebyexpertise = new GridBagConstraints();
	gbc_btnExpertsinsurancebyexpertise.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnExpertsinsurancebyexpertise.insets = new Insets(0, 0, 5, 5);
	gbc_btnExpertsinsurancebyexpertise.gridx = 0;
	gbc_btnExpertsinsurancebyexpertise.gridy = 8;
	internalFrame_1.getContentPane().add(btnExpertsinsurancebyexpertise, gbc_btnExpertsinsurancebyexpertise);
	
	btnReportfile = new JButton("report_file");
	btnReportfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="report_file";
		}
	});
	GridBagConstraints gbc_btnReportfile = new GridBagConstraints();
	gbc_btnReportfile.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnReportfile.insets = new Insets(0, 0, 5, 5);
	gbc_btnReportfile.gridx = 1;
	gbc_btnReportfile.gridy = 8;
	internalFrame_1.getContentPane().add(btnReportfile, gbc_btnReportfile);
	
	btnLabs = new JButton("labs");
	btnLabs.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			tables="labs";
		}
	});
	GridBagConstraints gbc_btnLabs = new GridBagConstraints();
	gbc_btnLabs.fill = GridBagConstraints.BOTH;
	gbc_btnLabs.insets = new Insets(0, 0, 5, 5);
	gbc_btnLabs.gridx = 2;
	gbc_btnLabs.gridy = 8;
	internalFrame_1.getContentPane().add(btnLabs, gbc_btnLabs);
	
	btnExperts = new JButton("experts");
	btnExperts.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			tables="experts";
		}
	});
	GridBagConstraints gbc_btnExperts = new GridBagConstraints();
	gbc_btnExperts.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnExperts.insets = new Insets(0, 0, 5, 5);
	gbc_btnExperts.gridx = 0;
	gbc_btnExperts.gridy = 9;
	internalFrame_1.getContentPane().add(btnExperts, gbc_btnExperts);
	
	btnLabsofclinc = new JButton("labs_of_clinc");
	btnLabsofclinc.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="labs_of_clinc";
		}
	});
	GridBagConstraints gbc_btnLabsofclinc = new GridBagConstraints();
	gbc_btnLabsofclinc.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnLabsofclinc.insets = new Insets(0, 0, 5, 5);
	gbc_btnLabsofclinc.gridx = 1;
	gbc_btnLabsofclinc.gridy = 9;
	internalFrame_1.getContentPane().add(btnLabsofclinc, gbc_btnLabsofclinc);
	
	btnMedicalworker = new JButton("medical_worker");
	btnMedicalworker.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			tables="medical_worker";
		}
	});
	GridBagConstraints gbc_btnMedicalworker = new GridBagConstraints();
	gbc_btnMedicalworker.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnMedicalworker.insets = new Insets(0, 0, 5, 5);
	gbc_btnMedicalworker.gridx = 2;
	gbc_btnMedicalworker.gridy = 9;
	internalFrame_1.getContentPane().add(btnMedicalworker, gbc_btnMedicalworker);
	
	btnLabTest = new JButton("lab test");
	btnLabTest.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="lab_test";
		}
	});
	GridBagConstraints gbc_btnLabTest = new GridBagConstraints();
	gbc_btnLabTest.fill = GridBagConstraints.BOTH;
	gbc_btnLabTest.insets = new Insets(0, 0, 5, 5);
	gbc_btnLabTest.gridx = 0;
	gbc_btnLabTest.gridy = 10;
	internalFrame_1.getContentPane().add(btnLabTest, gbc_btnLabTest);
	
	JButton btnLabtest = new JButton("lab tests file");
	btnLabtest.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tables="lab_test_file";
		}
	});
	GridBagConstraints gbc_btnLabtest = new GridBagConstraints();
	gbc_btnLabtest.fill = GridBagConstraints.BOTH;
	gbc_btnLabtest.insets = new Insets(0, 0, 5, 5);
	gbc_btnLabtest.gridx = 1;
	gbc_btnLabtest.gridy = 10;
	internalFrame_1.getContentPane().add(btnLabtest, gbc_btnLabtest);
	if(tables!=null)
		

	
	internalFrame_1.setVisible(true);
	

	JPanel panel = new JPanel();
	contentPane.add(panel, BorderLayout.WEST);
	panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	
	
	internalFrame = new JInternalFrame("Functions");
	contentPane.add(internalFrame, BorderLayout.EAST);
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[]{67, 0};
	gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	internalFrame.getContentPane().setLayout(gridBagLayout);
	
	btnUpdate = new JButton("Update");
	btnUpdate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			commend="update";
			

		}
	});
	GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
	gbc_btnUpdate.fill = GridBagConstraints.BOTH;
	gbc_btnUpdate.insets = new Insets(0, 0, 5, 0);
	gbc_btnUpdate.gridx = 0;
	gbc_btnUpdate.gridy = 0;
	internalFrame.getContentPane().add(btnUpdate, gbc_btnUpdate);
	
	btnPull = new JButton("Pull");
	btnPull.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			commend="pull";
		}
	});
	GridBagConstraints gbc_btnPull = new GridBagConstraints();
	gbc_btnPull.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnPull.insets = new Insets(0, 0, 5, 0);
	gbc_btnPull.gridx = 0;
	gbc_btnPull.gridy = 1;
	internalFrame.getContentPane().add(btnPull, gbc_btnPull);
	
	btnPullbykey = new JButton("pullByKey");
	btnPullbykey.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			commend="pullbykey";
		}
	});
	GridBagConstraints gbc_btnPullbykey = new GridBagConstraints();
	gbc_btnPullbykey.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnPullbykey.insets = new Insets(0, 0, 5, 0);
	gbc_btnPullbykey.gridx = 0;
	gbc_btnPullbykey.gridy = 2;
	internalFrame.getContentPane().add(btnPullbykey, gbc_btnPullbykey);
	
	btnPush = new JButton("Push");
	btnPush.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			commend="push";
		}
	});
	GridBagConstraints gbc_btnPush = new GridBagConstraints();
	gbc_btnPush.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnPush.insets = new Insets(0, 0, 5, 0);
	gbc_btnPush.gridx = 0;
	gbc_btnPush.gridy = 3;
	internalFrame.getContentPane().add(btnPush, gbc_btnPush);
	
	btnDelete = new JButton("Delete");
	btnDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			commend="delete";
		}
	});
	GridBagConstraints gbc_btnDelete = new GridBagConstraints();
	gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
	gbc_btnDelete.gridx = 0;
	gbc_btnDelete.gridy = 4;
	internalFrame.getContentPane().add(btnDelete, gbc_btnDelete);
	
	btnCheckme = new JButton("checkme");
	btnCheckme.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			commend="checkme";
		}
	});
	GridBagConstraints gbc_btnCheckme = new GridBagConstraints();
	gbc_btnCheckme.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnCheckme.insets = new Insets(0, 0, 5, 0);
	gbc_btnCheckme.gridx = 0;
	gbc_btnCheckme.gridy = 5;
	internalFrame.getContentPane().add(btnCheckme, gbc_btnCheckme);
	
	btnPullbydemand = new JButton("pullByDemand");
	btnPullbydemand.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			commend="pullByDemand";
		}
	});
	GridBagConstraints gbc_btnPullbydemand = new GridBagConstraints();
	gbc_btnPullbydemand.insets = new Insets(0, 0, 5, 0);
	gbc_btnPullbydemand.gridx = 0;
	gbc_btnPullbydemand.gridy = 6;
	internalFrame.getContentPane().add(btnPullbydemand, gbc_btnPullbydemand);
	
	SetTable = new JButton("Set");
	GridBagConstraints gbc_SetTable = new GridBagConstraints();
	gbc_SetTable.fill = GridBagConstraints.HORIZONTAL;
	gbc_SetTable.insets = new Insets(0, 0, 5, 0);
	gbc_SetTable.gridx = 0;
	gbc_SetTable.gridy = 13;
	internalFrame.getContentPane().add(SetTable, gbc_SetTable);
	

	GridBagConstraints gbc_btnOk = new GridBagConstraints();
	gbc_btnOk.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnOk.gridx = 0;
	gbc_btnOk.gridy = 16;
	createEvents();
	internalFrame.setVisible(true);

	//==>>

}
}