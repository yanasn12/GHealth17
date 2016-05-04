package controllers;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI.LoginGUI;
import GUI.MenuGUI;
import GUI.AbstractMenuGUI;
import controllers.TestCont;
import entities.User;
import entities.UserLogin;
import entities.UserType;
import client.ChatClient;
import common.ChatIF;
import common.Operations;
import common.Op;

import java.io.IOException;
import java.util.Properties;

/**
 * contorller to the login GUI - does all the login functionality with the database
 * extends jframe for now to pop up messages of the done operations
 * @param loginGUI - the login gui window to get the information from
 * @param userLogin - the login user to query
 * @param mainframe - the window to pop up messages on
 * @param client - the connector
 */

public class LoginCont extends JFrame implements ChatIF{

	private static final long serialVersionUID = 503370073305142698L;
	
	public static LoginGUI loginGUI;
	public static LoginCont mainframe;
	public static ChatClient client;
	private static User currUser; 
	private static UserLogin userLogin;
	
	
	public static void updateLogin(String Name,String Password)
	{
		UserLogin ul = new UserLogin("-1", -1, Name, Password);
		Op op = new Op(Operations.CREATE_LOGIN,ul);
		client.handleMessageFromClientUI(op);
	}

	/**
	 * Sends a mail with the standard project mail address and the given password to the given recipient with the given message.
	 * @param pass the password of the mail account
	 * @param targetAddr the recipient
	 * @param subj the subject of the mail
	 * @param msg the body of the mail
	 */
	public static void sendMail(String fromAddr,String pass,String targetAddr,String subj,String msg) {

        final String username = fromAddr;
        final String password = pass;

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(targetAddr));
            message.setSubject(subj);
            message.setText(msg);
            
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
	
	/**
	 * handles the login request after the login button was pressed
	 * initialized the userLogin with information from the UserGUI window
	 * sets the operation to be performed in op class to Login and sends to the server from client.handlemessagefromclientUI
	 * the method will wait untill the server responds to continue progress and handle the login correspondingly to the answer
	 */
	public static void handleLoginGUI(UserLogin ul) {

		userLogin = ul;
		Op logOp = new Op(Operations.LOGIN,userLogin);
		Operations oper;
		
		try{
			client = new ChatClient(userLogin.getHost(), userLogin.getPort());
			client.handleMessageFromClientUI(logOp);
			logOp = (Op) client.getMessage();
			currUser=(User) logOp.getEntity();
			oper = logOp.getOp();
			if(oper == Operations.ALLOW_LOG)
			{
			logOp = new Op(Operations.GET_UT,currUser);
			client.handleMessageFromClientUI(logOp);
			logOp = (Op) client.getMessage();
			currUser=(User) logOp.getEntity();
			}
			handleLogin(oper);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * correspondinly to the servers answer after the login request the method will navigate the correct login across diffrenct user types
	 * displays a popup message for information on the action
	 * @param premmisionLevel
	 */
	
	private static void handleLogin(Operations o) {
		switch(o)
		{
		case ERROR:
			loginGUI.textField.setEnabled(true);
			loginGUI.textField_2.setEnabled(true);
			loginGUI.textField_1.setEnabled(true);
			loginGUI.EnablePassField();
			loginGUI.btnLogin.setEnabled(true);
			JOptionPane.showMessageDialog(LoginCont.mainframe, "Error:User and/or Password is incorrect!");
			loginGUI.btnExit.setEnabled(true);
			break;
		case ALLOW_LOG:
			loginGUI.btnLogin.setEnabled(false);
			loginGUI.btnLogout.setEnabled(true);
			loginGUI.setVisible(false);	
			MenuGUI mh1=new MenuGUI(loginGUI);
			String buttonNames[];
			buttonNames = new String[]{"Station Control","Purchase Plan","Modify Customer","Manage Sales", "NFC Module","Manage Fuel","Messages","Invoices","Reports", "House Fuel Orders"};
			mh1.setButtonNames(buttonNames);
			//get the user_type and user the string privilegeLevel to allow certain functions
			System.out.println(currUser.getUserType().toString()); // getting the usertype and user correctly
			boolean[] allowedButtons = currUser.getUserType().getPrivilegeLevels();
			for(int i=0;i<allowedButtons.length;i++)
			{
				mh1.functionButton[i].setEnabled(allowedButtons[i]);
				mh1.functionButton[i].setVisible(allowedButtons[i]);
			}
			break;  
		case USER_ALREADY_LOGGED:
			JOptionPane.showMessageDialog(LoginCont.mainframe, "Error:User Already Logged In!");//do something else after wards like - get the full User Details and display a corresponding GUI
			loginGUI.textField.setEnabled(true);
			loginGUI.textField_2.setEnabled(true);
			loginGUI.textField_1.setEnabled(true);
			loginGUI.EnablePassField();
			loginGUI.btnLogin.setEnabled(true);
			loginGUI.btnExit.setEnabled(true);
			break;
		}	
	}
	public boolean charToBool(char c)
	{
		if(c=='1') return true;
		return false;
	}
	
	/**
	 * handles a logout request after the logout button was pressed
	 * initiazlize a logou OP with the save userLogin details
	 * requests and waits for the server to change the state and logout the suer from the system
	 */
	public static void handleLogoutGUI()
	{
		Op logOp = new Op(Operations.LOGOUT,userLogin);
		client.handleMessageFromClientUI(logOp);
	}
	@Override
	//method to pop up messages with needed information
	public void display(String message) {
		JOptionPane.showMessageDialog(LoginCont.mainframe, message);
	}

	public static void print(String string) {
		System.out.println(string);
		// TODO Auto-generated method stub
		
	}
	 public static User getCurrUser() {
	        return currUser;
	    }
	 
	 public static void setCurrUser(User currUser) {
	        LoginCont.currUser = currUser;
	    }
}
