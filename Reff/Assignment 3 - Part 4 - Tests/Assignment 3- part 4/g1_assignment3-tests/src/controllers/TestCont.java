package controllers;

import GUI.TestGUI;
import client.ChatClient;
import common.ChatIF;
import common.Operations;
import common.Op;
import entities.DiscountTable;


public class TestCont implements ChatIF{
	
	public TestCont(ChatClient client)
	{	
		
	}

	public static DiscountTable handleTestingGUI(int id)
	{
		DiscountTable t1 = new DiscountTable();
		t1.setDiscountTableID(id);
		//t1.setDiscTable(25);
		//t1.setDiscTableChanged(8);
		Op top = new Op(Operations.TEST,t1);
		DiscountTable ret;
		
		LoginCont.client.handleMessageFromClientUI(top);
		ret = (DiscountTable) LoginCont.client.getMessage();
		return ret;
	}

	public void display(String message)
	{
	
	}
}
