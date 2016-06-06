package GUI_client;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.GuiLogin;

import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Login extends JFrame {
	private JTextField textField;
	private JLabel lblPassword;
	private JTextField txtPassword;
	private JButton btnNewButton;
	private String inputLogin;
	private GeneralLabWorker labWorker = null;
	private GeneralManegementReport netManager = null;
	private Dispather_Gui_main dispather = null;
	private GeneralDoctor doctorExpert =null;
	private ClinicManegementRrports ClincManegement = null;
	public JPanel FirstPanel = null;
	public static int typeOfWorker;
	public static String userInSystem;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
public Login()
{
	super();
	this.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/javagui/resources/img16x16/loginicon.png")));
	this.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 400, 400);
	this.setContentPane(into());
	this.setTitle("Login");
	

}
	/**
	 * Create the frame.
	 */
	private JPanel into() {
		if (FirstPanel == null) {
			FirstPanel = new JPanel();
			FirstPanel.setBackground(SystemColor.window);
			FirstPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/javagui/resources/GHealthlogo.png")));
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inputLogin= textField.getText();
				inputLogin=inputLogin + ",";

			}
		});
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		
		txtPassword = new JTextField();
		txtPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLogin=inputLogin + txtPassword.getText();
			}
		});
		txtPassword.setColumns(10);
		
		btnNewButton = new JButton("");

		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/javagui/resources/login3.png")));
		GroupLayout gl_contentPane = new GroupLayout(FirstPanel);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsername)
								.addComponent(lblPassword))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addComponent(textField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(82, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(90))
		);
	
		FirstPanel.setLayout(gl_contentPane);
		//getContentPane().setLayout(gl_contentPane);
		createEvents();
	}	
		return FirstPanel;
	}

	private void createEvents()
	{
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLogin="pullbykey:medical_worker:worker_num,"+textField.getText()+",password,"+txtPassword.getText();;
				GuiLogin.LoginController(inputLogin, txtPassword.getText());
				switch (typeOfWorker)
				{
				case 1: //labworker
					if(labWorker==null){
						labWorker=new GeneralLabWorker();
						labWorker.setSize(600,500);
						labWorker.setVisible(true);
						labWorker=null;
					}
					break;				
				case 2: //Network manager
					if(netManager==null){
						netManager=new GeneralManegementReport();
						netManager.setSize(600,500);
						netManager.setVisible(true);
						netManager=null;
					}
					break;
				case 3: //Dispatcher	
					if(dispather==null)
					{
						dispather=new Dispather_Gui_main();
						dispather.setSize(600,500);
						dispather.setVisible(true);
						dispather=null;
						
					}
					break;
				case 4: //Expert	
					if(doctorExpert==null)
					{
						doctorExpert=new GeneralDoctor();
						doctorExpert.setSize(600,500);
						doctorExpert.setVisible(true);
						doctorExpert=null;
	
					}
					break;
				case 5: //Clinc Manager	
					if(ClincManegement==null)
					{
						ClincManegement=new ClinicManegementRrports();
						ClincManegement.setSize(600,500);
						ClincManegement.setVisible(true);
						ClincManegement=null;
					}
					break;
				default:
				}// end switch
				textField.setText("");
				txtPassword.setText(" ");
			}
		});
	}


}
