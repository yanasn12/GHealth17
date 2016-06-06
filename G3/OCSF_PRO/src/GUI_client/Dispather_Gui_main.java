package GUI_client;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controllers.Logout;
import jdbc.mysqlConnection;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class Dispather_Gui_main extends JFrame {
	private JButton button_2;
	private JButton button_1;
	private JPanel contentPane=null;
	public static ArrayList<String> quary2 = new ArrayList<String>();
	public static String quaryinfo1="";
	private JButton button_3_1;
	private JPanel neWindow=null;
	public static int windows=0;
	private Login loginmain=null;
	private boolean firstFlag=true;
	private JTabbedPane tabbedPane;
	private Dispather_Client_info_Gui window1 = new Dispather_Client_info_Gui();
	private Dispather_Doctor window2 = new Dispather_Doctor();
	private Dispather_date_and_time window3 = new  Dispather_date_and_time();
	private JTextField textField;

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
	public  Dispather_Gui_main() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);
		this.setTitle("Dispather");
		setBounds(100, 100, 786, 599);
		this.setContentPane(into());
	}
		
	private JPanel into() {	
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setBackground(SystemColor.window);
			contentPane.setLayout(null);
		contentPane.setBackground(SystemColor.WHITE);
		contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
		
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow][][48.00][][]", "[][][grow][][][grow][][grow][][][][][][][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Dispather_Gui_main.class.getResource("/javagui/resources/GHealthlogo.png")));
		contentPane.add(lblNewLabel, "cell 0 0,alignx center,aligny top");
		
		Icon AppointmentIcon = new ImageIcon("C:\\Users\\guy\\workspace\\OCSF_PRO_GUY\\src\\javagui\\resources\\MakeAppointment.png");
		Icon FindADoctor = new ImageIcon("C:\\Users\\guy\\workspace\\OCSF_PRO_GUY\\src\\javagui\\resources\\FindADoctor.png");
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "cell 1 2 4 16,grow");
		tabbedPane.addTab("patient", null,window1,null);	
		tabbedPane.addTab("Doctors", null,window2,null);
		tabbedPane.addTab("Date and Time", null,window3,null);
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);
		
		button_2 = new JButton("");
		
		button_2.setIcon(AppointmentIcon);
		contentPane.add(button_2, "cell 0 3,alignx center,growy");
		
		button_1 = new JButton("");
		button_1.setIcon(FindADoctor);
		contentPane.add(button_1, "cell 0 4,alignx center");
		Icon LogOut = new ImageIcon("C:\\Users\\guy\\workspace\\OCSF_PRO_GUY\\src\\javagui\\resources\\logout.png");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 0 6 1 6,growx");
		textField.setColumns(10);
		textField.setVisible(false);
		
		button_3_1 = new JButton("");
		button_3_1.setIcon(LogOut);
		contentPane.add(button_3_1, "cell 0 17,alignx center,growy");

				createEvents();
				
		}
		return contentPane;
	}
	
private void createEvents()
	{
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{

				}
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				


			}
		});
		
		button_3_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(loginmain==null)
				{
					Logout.logUserOut(Login.userInSystem);
					dispose();
					
				}
			}
		});


		Dispather_Client_info_Gui.btnCommfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setEnabledAt(1, true);
			}
		});
		
		Dispather_Doctor.btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(2, true);
			}
		});
	
	}
}
