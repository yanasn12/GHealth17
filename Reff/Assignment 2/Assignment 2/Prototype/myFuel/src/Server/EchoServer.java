package Server;

import java.io.*;
import Server.ServerController;
import common.fuel;
import Server.op;
import ocsf.server.*;

public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************

  final public static int DEFAULT_PORT = 5555;
  //static mysqlConnection conn;
  //Constructors ****************************************************

  public EchoServer(int port) 
  {
    super(port);
  }
  //Instance methods ************************************************

  
  
  
  
  public void handleMessageFromClient(Object msg, ConnectionToClient client)
  {
	  op operation = (op)msg;
	 
	  switch(operation.getCom())
	  {
	  case 0:
		  ServerController.updateFuel((fuel)operation.getEntity());
	  case 1:
		  try{
		  client.sendToClient(ServerController.searchFuel(operation.getCon()));
		  }
		  catch (IOException e)
		  {
			  e.printStackTrace();
		  }
	  }
  }

  
  
  
  
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }

  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  //Class methods ***************************************************

  public static void main(String[] args) 
  {
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    EchoServer sv = new EchoServer(port);
    DBC.mySQLConnection();
    
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }


}
//End of EchoServer class
