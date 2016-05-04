// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import java.io.*;
import common.ChatIF;
import controllers.LoginCont;
/**
 * 
 * This class handles the connection with the server. it receives an object for from the client and passes it to the server .
 * It also listens to the server for any messages that may be return
 *
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  Object msg;

  
  //Constructors ****************************************************

  public ChatClient(String host, int port) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    openConnection();
  }

  //Instance methods ************************************************

  /**
   * method that handles the recived msg from the server
   * saves it in the clients local var msg and notifies the waiting method
   * for the information requested
   */
  
  public synchronized void handleMessageFromServer(Object msg) 
  {
	  this.msg = msg;
	  notify();
  }

  
  /**
   * Method to send a message from the client UI to the server
   * @param message - composed as a OP
   */
  
  public void handleMessageFromClientUI(Object message)
  {
	  try {
			sendToServer(message);
		} catch (IOException e) {
			e.printStackTrace();
			LoginCont.print("Could not send message to server.  Terminating client.");
			quit();
		}
		msg = null;
  }
/**
 * method to pause client work time untill the server finished proccessing the information
 * and returns a message back to the client which will be saved at a local parameter msg
 * @return msg - the answer from the server
 */
 
  synchronized public Object getMessage() {
	while (msg == null) {
		try {
			wait();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	return msg;
	}
  /**
   * methood to close the connection to the server and exit the system
   */
  
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
