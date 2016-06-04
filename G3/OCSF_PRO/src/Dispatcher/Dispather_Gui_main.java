package Dispatcher;
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

import jdbc.mysqlConnection;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;

public class Dispather_Gui_main extends JFrame {
	private JButton button;
	private JButton button_2;
	private JButton button_3;
	private JPanel contentPane;
	private JTextField textField;
	public static ArrayList<String> quary2 = new ArrayList<String>();
	public static String quaryinfo1="";
	public JButton btnOk;
	private JButton button_3_1;
	private JPanel neWindow;
	public int windows=0;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setTitle("Dispather");
		setBounds(100, 100, 686, 499);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow][][48.00][][]", "[][][grow][][][][][][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Dispather_Gui_main.class.getResource("/javagui/resources/GHealthlogo.png")));
		contentPane.add(lblNewLabel, "cell 0 0,alignx center,aligny top");


		
				Icon AppointmentIcon = new ImageIcon("C:\\Users\\guy\\workspace\\OCSF_PRO_GUY\\src\\javagui\\resources\\MakeAppointment.png");	
				JButton button = new JButton("");
				button_2 = new JButton("");
				button_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						{
							//System.out.println(Dispather_Gui_main.parts[5].toString());
							//quaryinfo1="pullbykey:medical_files:medical_file_num,"+Dispather_Gui_main.parts[5].toString();
							//quary2=mysqlConnection.ActionMode(quaryinfo1);
				
							if(quary2.size()==0)
								JOptionPane.showMessageDialog(null,"no open references where found", "",JOptionPane.ERROR_MESSAGE);
							else
							{
								//newText= new Dispather_Gui_ref();
								//newText.setBounds(0, 0, 210, 363);
								//getContentPane().add(newText);
							}
						}
						
					}
				});
								
								JLabel lblPaintentId = new JLabel("Paintent ID:");
								contentPane.add(lblPaintentId, "flowx,cell 3 1,alignx left,aligny top");
				
								textField = new JTextField();
								contentPane.add(textField, "cell 3 1,alignx left,aligny top");
								textField.setColumns(10);;
								button_2.setIcon(AppointmentIcon);
					getContentPane().add(button_2, "cell 0 3,alignx center,growy");
				
					
					
				
				JButton button_1 = new JButton("");
				Icon FindADoctor = new ImageIcon("C:\\Users\\guy\\workspace\\OCSF_PRO_GUY\\src\\javagui\\resources\\FindADoctor.png");
				
				
				JButton button_3 = new JButton("");
				Icon LogOut = new ImageIcon("C:\\Users\\guy\\workspace\\OCSF_PRO_GUY\\src\\javagui\\resources\\logout.png");
				
				switch(windows)
				{
					case(0):
					{
								neWindow = new Dispather_Client_info_Gui();
								neWindow.setBounds(0, 0, 100, 100);
								neWindow.setBackground(Color.WHITE);
								contentPane.add(neWindow, "cell 1 3 3 7");
								break;
					}
					case(1):
					{
						neWindow = new Dispather_DoctoerAndTime();
						neWindow.setBounds(0, 0, 100, 100);
						neWindow.setBackground(Color.WHITE);
						contentPane.add(neWindow, "cell 1 3 3 7");
						break;
					}
				}		
				button_1 = new JButton("");
				button_1.setIcon(FindADoctor);
				getContentPane().add(button_1, "cell 0 4,alignx center");		
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						windows=1;

					}
				});
				
				
				btnOk = new JButton("OK");

				contentPane.add(btnOk, "cell 3 1,alignx right,aligny top");
				
				
				button_3_1 = new JButton("");
				button_3_1.setIcon(LogOut);
				getContentPane().add(button_3_1, "cell 0 7,alignx center,growy");
				button_3_1.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
					}
				});
		}
	
	}
