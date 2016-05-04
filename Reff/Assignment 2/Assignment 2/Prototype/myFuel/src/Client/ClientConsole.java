package Client;


import java.io.*;
import common.*;

public class ClientConsole implements ChatIF 
{
  //Class variables *************************************************
  
  final public static int DEFAULT_PORT = 5555;
  
  //Instance variables **********************************************

  ChatClient client;
  fuel temp = null;
  //Constructors ****************************************************

  public ClientConsole(String host, int port) 
  {
    try 
    {
      client= new ChatClient(host, port, this);
    } 
    catch(IOException exception) 
    {
      System.out.println("Error: Can't setup connection!"
                + " Terminating client.");
      System.exit(1);
    }
  }

  
  //Instance methods ************************************************

  public void accept() 
  {
	fuel f1 = null;
    try
    {
      BufferedReader fromConsole = 
      new BufferedReader(new InputStreamReader(System.in));
      String message;

      while (true) 
      {
        message = fromConsole.readLine();
        String command = message.substring(0,4);
    	message = message.substring(4);
        if(command.equals("get "))
        {
        	client.handleMessageFromClientUI(0,message);
        }
        else if(command.equals("set "))
        {
        	String[] arr = message.split(" ");
        	f1 = new fuel(Integer.parseInt(arr[0]),arr[1],Integer.parseInt(arr[2]));
        	client.handleMessageFromClientUI(1,f1);
        	
        }
  //      else if(message.equals("exit"))
  //      	client.handleMessageFromClientUI(9,message);
  //      else
  //      	this.display(message);
      }
    } 
    catch (Exception ex) 
    {
      System.out.println
        ("Unexpected error while reading from console!");
    }
  }


  public void display(String message) 
  {
    System.out.println("> " + message);
  }

  //Class methods ***************************************************

  public static void main(String[] args) 
  {
	
    String host = "";
    @SuppressWarnings("unused")
	int port = 0;  //The port number

    try
    {
      host = args[0];
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
      host = "localhost";
    }
    ClientConsole chat= new ClientConsole(host, DEFAULT_PORT);
    chat.accept();  //Wait for console data
  }
}
//End of ConsoleChat class
