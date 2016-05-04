// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package Client;

import ocsf.client.*;
import common.*;
import java.io.*;
import Client.ClientContorller;


public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************

  ChatIF clientUI; 

  
  //Constructors ****************************************************

  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  //Instance methods ************************************************

  
  
  public void handleMessageFromServer(Object msg) 
  {
	  if(msg instanceof fuel)
		  System.out.println(msg.toString());
	  else
		  System.out.println("Update Completed!");
  }

  
  
  
  public void handleMessageFromClientUI(int comm,Object msg)
  {
	  switch(comm)
	  {
	  case 0://
		  try{
			  sendToServer(ClientContorller.searchFuel((String)msg));
		  }
		  catch(IOException e)
		  {
			  e.printStackTrace();
		  }
		  break;
	  case 1:
		  try{
			  sendToServer(ClientContorller.UpdateFuel((fuel)msg));
		  }
		  catch(IOException e)
		  {
			  e.printStackTrace();
		  }
		  
	  }
  }

  
  
  
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
