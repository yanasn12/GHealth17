package javagui.views;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.SystemColor;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblPassword;
	private JTextField txtPassword;
	private JButton btnNewButton;
	public static String inputLogin="";
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
					Main frame = new Main();
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
	public Main() {
		ArrayList <String> fillIn=new ArrayList<String>();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/javagui/resources/img16x16/loginicon.png")));
		setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		setBackground(Color.WHITE);
		setTitle("login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 394, 376);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Main.class.getResource("/javagui/resources/GHealthlogo.png")));
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inputLogin=inputLogin +"worker_num," + textField.getText().toString();
				inputLogin=inputLogin + ",";
				System.out.println(inputLogin);
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
				inputLogin=inputLogin+"password" + txtPassword.getText();
				lblPassword = new JLabel(inputLogin);
				lblPassword.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
			}
		});
		txtPassword.setColumns(10);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLogin="pullbykey:medical_worker:"+ inputLogin;
				inputLogin=inputLogin +"worker_num," + textField.getText().toString();
				inputLogin=inputLogin + ",";
				inputLogin=inputLogin+"password," + txtPassword.getText();
				ArrayList <String> data = new ArrayList<String>();
				String [] dataDB;
				System.out.println(inputLogin);
				data=jdbc.mysqlConnection.ActionMode(inputLogin.toString());
				inputLogin="";
				if(data.isEmpty())
				{
						JOptionPane.showMessageDialog(null,"you have enter a wrong Username.", "Login Error",JOptionPane.ERROR_MESSAGE);
						
				}
				else
				{
					dataDB=data.get(0).split(",");
					if(!dataDB[2].equals(txtPassword.getText().toString()))
						{
							JOptionPane.showMessageDialog(null,"you have enter a wrong password.", "Login Error",JOptionPane.ERROR_MESSAGE);
						}
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(Main.class.getResource("/javagui/resources/login3.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblUsername)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPassword)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(txtPassword)))))
					.addContainerGap(64, Short.MAX_VALUE))
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
					.addGap(31)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(55))
		);
		contentPane.setLayout(gl_contentPane);

	}
}
