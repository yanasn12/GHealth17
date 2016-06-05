package Controllers;

public class Logout {

	
	private static String input="update:medical_worker:worker_num,";
	
	public static void logUserOut (String userToLogOut)
	{
		input=input+userToLogOut+",is_connected,0";
		System.out.println(input);
		jdbc.mysqlConnection.ActionMode(input);
	}
}
