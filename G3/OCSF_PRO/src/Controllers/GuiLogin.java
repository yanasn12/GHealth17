package Controllers;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Entitys.Medical_worker;
import GUI_client.Login;
public class GuiLogin {
	private static int wrongConter;
	public Medical_worker loginUser= new Medical_worker();
	public static void setWrongConter()
	
	{
		wrongConter=0;
	}
	public  static void LoginController(String inputLogin, String password)
	{
		ArrayList <String> data = new ArrayList<String>();
		String [] dataDB;
		System.out.println(inputLogin);
		data=jdbc.mysqlConnection.ActionMode(inputLogin.toString());
		System.out.println(data.get(0));
		if(data.isEmpty())
		{
				JOptionPane.showMessageDialog(null,"you have enter a wrong Username.", "Login Error",JOptionPane.ERROR_MESSAGE);
				 setWrongConter();
		}
		else
		{
			dataDB=data.get(0).split(",");
			if(!dataDB[4].equals("0"))
			{
				if(dataDB[4].equals("1"))
					JOptionPane.showMessageDialog(null,"The user is already in Login mode,\n please connect to system administrator ", "Login Error",JOptionPane.ERROR_MESSAGE);
				else
					if(dataDB[4].equals("2"))
				JOptionPane.showMessageDialog(null,"The user is locked out of the system due to suspicion of hacking attempt,\n please connect to system administrator ", "Login Error",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				
				if(!dataDB[2].equals(password))
					{
						System.out.println(wrongConter++);
						if(wrongConter<3)
							JOptionPane.showMessageDialog(null,"you have enter a wrong password.", "Login Error",JOptionPane.ERROR_MESSAGE);
						else
							{
								inputLogin="update:medical_worker:"+"worker_num,"+ dataDB[1].toString()+",is_connected,2";
								jdbc.mysqlConnection.ActionMode(inputLogin.toString());
								JOptionPane.showMessageDialog(null,"you have try too many time, please connect your Admin.", "Login Error",JOptionPane.ERROR_MESSAGE);
								
							}
						
					}
				else if(dataDB[2].equals(password))
					{
					inputLogin="update:medical_worker:"+"worker_num,"+ dataDB[1]+",is_connected,1";
					System.out.println(inputLogin);
					jdbc.mysqlConnection.ActionMode(inputLogin.toString());
					System.out.println(dataDB[6]);
					Login.typeOfWorker=Integer.parseInt(dataDB[6]);
					}
			}
		}

	}
}
