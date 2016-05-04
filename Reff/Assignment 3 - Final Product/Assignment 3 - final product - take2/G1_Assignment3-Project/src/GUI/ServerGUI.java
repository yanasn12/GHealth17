package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import server.EchoServer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerGUI extends JFrame{

	private static final long serialVersionUID = -281343501492022845L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	public static JFrame mainframe;
	private String host,dbPort,dbName,dbUser,dbPass,serverPort;
	private EchoServer es;
	
	
	public ServerGUI() {
		mainframe=this;
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MySQL Server IP:");
		lblNewLabel.setBounds(10, 25, 100, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblMysqlServerPort = new JLabel("MySQL Server Port:");
		lblMysqlServerPort.setBounds(10, 50, 100, 14);
		getContentPane().add(lblMysqlServerPort);
		
		JLabel lblMysqlDatabaseName = new JLabel("MySQL Database Name:");
		lblMysqlDatabaseName.setBounds(10, 75, 116, 14);
		getContentPane().add(lblMysqlDatabaseName);
		
		textField = new JTextField("myfuel");
		textField.setBounds(139, 72, 166, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField("3306");
		textField_1.setBounds(139, 47, 166, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField("127.0.0.1");
		textField_2.setBounds(139, 22, 166, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblServerIp = new JLabel("MySQL User:");
		lblServerIp.setBounds(10, 125, 81, 14);
		getContentPane().add(lblServerIp);
		
		JLabel lblServerPort = new JLabel("MySQL Password:");
		lblServerPort.setBounds(10, 150, 100, 14);
		getContentPane().add(lblServerPort);
		
		textField_3 = new JTextField("root");
		textField_3.setBounds(139, 122, 166, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField("Braude");
		textField_4.setBounds(139, 147, 166, 20);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblServerPort_1 = new JLabel("Server Port:");
		lblServerPort_1.setBounds(10, 175, 81, 14);
		getContentPane().add(lblServerPort_1);
		
		textField_5 = new JTextField("5555");
		textField_5.setBounds(139, 172, 166, 20);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		final JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(10, 227, 89, 23);
		getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(213, 227, 89, 23);
		getContentPane().add(btnExit);
		
		
		final JButton btnLogout = new JButton("Logout");
		btnLogout.setEnabled(false);
		btnLogout.setBounds(114, 227, 89, 23);
		getContentPane().add(btnLogout);
		//pressed logout
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnLogin.setEnabled(true);
				textField.setEnabled(true);
				textField_1.setEnabled(true);
				textField_2.setEnabled(true);
				textField_3.setEnabled(true);
				textField_4.setEnabled(true);
				textField_5.setEnabled(true);
				btnLogout.setEnabled(false);
				if (es.isListening()) {
					try {

						es.close();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(ServerGUI.mainframe,
								"ERROR - Could not close connection.");

					}
				}
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogout.doClick();
				System.exit(0);
			}
		});
		
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//pressed Login
				setHost(textField_2.getText());
				setDbPort(textField_1.getText());
				setDbName(textField.getText());
				setDbUser(textField_3.getText());
				setDbPass(textField_4.getText());
				setServerPort(textField_5.getText());
				
				es = new EchoServer(Integer.parseInt(serverPort),host, Integer.parseInt(dbPort),dbUser, dbName,dbPass );
				if(es.db.conn==null)
					JOptionPane.showMessageDialog(ServerGUI.mainframe,"ERROR - Could not listen for clients!");
				else{
				try{
					es.listen();
					btnLogin.setEnabled(false);
					textField.setEnabled(false);
					textField_1.setEnabled(false);
					textField_2.setEnabled(false);
					textField_3.setEnabled(false);
					textField_4.setEnabled(false);
					textField_5.setEnabled(false);
					btnLogout.setEnabled(true);
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(ServerGUI.mainframe,"ERROR - Could not listen for clients!");
				}
				}
			}
		});
		this.setTitle("Server-Database Login");
		this.setSize(330, 300);
		this.setVisible(true);
	}



	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPass() {
		return dbPass;
	}

	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public static void main(String[] args) {

	@SuppressWarnings("unused")
	ServerGUI turnOnSrv = new ServerGUI();
	}
}