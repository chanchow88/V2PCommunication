package communication;


import java.net.*;
import java.io.*;

/**
 * Demo Server: Contains a multi-threaded socket server sample code.
 */
public class V2PCommunication
{
	final static int _portNumber = 80; //Arbitrary port number

	public static void main(String[] args) 
	{
		ConnectionRequestHandler conn = new ConnectionRequestHandler();
		Thread t = new Thread(conn);
		t.start();
		
		SenderHandler sender = new SenderHandler();
		Thread senderThread = new Thread(sender);
		senderThread.start();
	}
	
}
