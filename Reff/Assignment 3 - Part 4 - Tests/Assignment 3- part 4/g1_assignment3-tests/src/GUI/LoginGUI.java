package GUI;

import javax.swing.JFrame;


import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import controllers.*;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import entities.UserLogin;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame{

	private static final long serialVersionUID = -7812454713722940062L;
	public JTextField textField;
	private JPasswordField passwordField;
	public JTextField textField_1;
	public JTextField textField_2;

	public  JButton btnLogout = new JButton("Logout");
	public  JButton btnLogin = new JButton("Login");
	public JButton btnExit = new JButton("Exit");
	
	public LoginGUI() {
		
		CarFuelOrderControl.initControl(); // ============================== INIT CONTROLS ================================
		CustomerControl.initControl();
		DiscountFeedbackReportControl.initControl();
		DiscountTableControl.initControl();
		FuelTypeControl.initControl();
		GeneratedReportControl.initControl();
		HouseFuelOrderControl.initControl();
		IncomeReportControl.initControl();
		InvoiceControl.initControl();
		OrderControl.initControl();
		PaymentInfoControl.initControl();
		PeriodicCustomerReportControl.initControl();
		PeriodicDiscountsControl.initControl();
		PurchasePlanControl.initControl();
		PurchasesReportControl.initControl();
		ReportControl.initControl();
		StockControl.initControl();
		StockReportControl.initControl();
		SubscriptionControl.initControl();
		TrackingControl.initControl();
		WorkerControl.initControl();

		getContentPane().setBackground(new Color(0, 153, 204));
		getContentPane().setLayout(null);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Georgia", Font.BOLD, 13));
		lblUser.setBounds(10, 11, 46, 14);
		getContentPane().add(lblUser);
		
		JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setBounds(20, 36, 65, 19);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(20, 69, 72, 14);
		getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(85, 35, 174, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(85, 66, 174, 20);
		getContentPane().add(passwordField);
		
		JLabel lblServer = new JLabel("Server:");
		lblServer.setFont(new Font("Georgia", Font.BOLD, 13));
		lblServer.setBounds(10, 111, 65, 27);
		getContentPane().add(lblServer);
		
		JLabel lblNewLabel = new JLabel("Host Addr:");
		lblNewLabel.setBounds(20, 141, 65, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblHostPort = new JLabel("Host Port:");
		lblHostPort.setBounds(20, 163, 65, 27);
		getContentPane().add(lblHostPort);
		
		textField_1 = new JTextField("127.0.0.1");
		textField_1.setColumns(10);
		textField_1.setBounds(85, 138, 174, 20);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField("5555");
		textField_2.setColumns(10);
		textField_2.setBounds(85, 166, 174, 20);
		getContentPane().add(textField_2);
		//login pressed
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setEnabled(false);
				textField_2.setEnabled(false);
				textField_1.setEnabled(false);
				passwordField.setEnabled(false);
				btnLogin.setEnabled(false);
				btnExit.setEnabled(false);
				UserLogin userLogin = new UserLogin(getHost(),getPort(),getUserName(),getPass());
				LoginCont.handleLoginGUI(userLogin);
			}
		});
		btnLogin.setBounds(10, 208, 75, 23);
		getContentPane().add(btnLogin);
		
		//exit pressed
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(202, 208, 72, 23);
		getContentPane().add(btnExit);
		//logout pressed
		btnLogout.setEnabled(false);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setEnabled(true);
				textField_2.setEnabled(true);
				textField_1.setEnabled(true);
				passwordField.setEnabled(true);
				btnLogin.setEnabled(true);
				btnLogout.setEnabled(false);
				btnExit.setEnabled(true);
				LoginCont.handleLogoutGUI();
			}
		});
		btnLogout.setBounds(95, 208, 97, 23);
		getContentPane().add(btnLogout);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("Client-Server Login");
		this.setSize(300, 280);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {

		LoginGUI lg1 = new LoginGUI();
		LoginCont.loginGUI=lg1;
	}
	//GETTERS//
	
	public String getHost() {
		return textField_1.getText();
	}

	public int getPort() {
			return Integer.parseInt(textField_2.getText());
	}

	public String getUserName() {
			return textField.getText();
	}
	public String getPass() {
		return new String(passwordField.getPassword());
	}
	public void EnablePassField()
	{
		this.passwordField.setEnabled(true);
	}
	public void DisablePassField()
	{
		this.passwordField.setEnabled(false);
	}
}
