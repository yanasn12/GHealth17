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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controllers.Logout;
import jdbc.mysqlConnection;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dispather_Gui_main extends JFrame {
	private JButton button_2;
	private JButton button_1;
	private JPanel contentPane=null;
	private JTextField textField;
	public static ArrayList<String> quary2 = new ArrayList<String>();
	public static String quaryinfo1="";
	public JButton btnOk;
	private JButton button_3_1;
	private JPanel neWindow;
	public static int windows=0;
	private Login loginmain=null;
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
		setBounds(100, 100, 686, 499);
		this.setContentPane(into());
	}
		
	private JPanel into() {	
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setBackground(SystemColor.window);
			contentPane.setLayout(null);
		contentPane.setBackground(SystemColor.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new MigLayout("", "[][][grow][][48.00][][]", "[][][grow][][][][][][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Dispather_Gui_main.class.getResource("/javagui/resources/GHealthlogo.png")));
		contentPane.add(lblNewLabel, "cell 0 0,alignx center,aligny top");
		
		Icon AppointmentIcon = new ImageIcon("C:\\Users\\guy\\workspace\\OCSF_PRO_GUY\\src\\javagui\\resources\\MakeAppointment.png");	
		button_2 = new JButton("");
				
								
		JLabel lblPaintentId = new JLabel("Paintent ID:");
		contentPane.add(lblPaintentId, "flowx,cell 3 1,alignx left,aligny top");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 3 1,alignx left,aligny top");
		textField.setColumns(10);
		
		button_2.setIcon(AppointmentIcon);
		contentPane.add(button_2, "cell 0 3,alignx center,growy");
		
		button_1 = new JButton("");
		Icon FindADoctor = new ImageIcon("C:\\Users\\guy\\workspace\\OCSF_PRO_GUY\\src\\javagui\\resources\\FindADoctor.png");
		button_1.setIcon(FindADoctor);
		contentPane.add(button_1, "cell 0 4,alignx center");		
					
		btnOk = new JButton("OK");
		contentPane.add(btnOk, "cell 3 1,alignx right,aligny top");			
				
		button_3_1 = new JButton("");
		Icon LogOut = new ImageIcon("C:\\Users\\guy\\workspace\\OCSF_PRO_GUY\\src\\javagui\\resources\\logout.png");
		button_3_1.setIcon(LogOut);
		contentPane.add(button_3_1, "cell 0 7,alignx center,growy");

				createEvents();
				
		}
		return contentPane;
	}
	
	private void createEvents()
	{
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					windows=0;
		
					//if(quary2.size()==0)
					//	JOptionPane.showMessageDialog(null,"no open references where found", "",JOptionPane.ERROR_MESSAGE);
				//	else
					{

					}
				}
				
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windows=1;
				createEvents();
				

			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
	
	
	}
}
