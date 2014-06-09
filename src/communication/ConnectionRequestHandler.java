package communication;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ConnectionRequestHandler implements Runnable{

	public ConnectionRequestHandler() {

	}

	public void run() {
		try
		{ 
			DatagramSocket serverSocket = new DatagramSocket(9876); 

			byte[] receiveData = new byte[1024]; 
			byte[] sendData  = new byte[1024]; 

			while(true) 
			{ 
				try
				{
					receiveData = new byte[1024]; 

					DatagramPacket receivePacket = 
							new DatagramPacket(receiveData, receiveData.length); 

					System.err.println ("Waiting for datagram packet");

					serverSocket.receive(receivePacket); 

					String sentence = new String(receivePacket.getData()); 

					InetAddress IPAddress = receivePacket.getAddress(); 

					int port = receivePacket.getPort(); 

					System.err.println ("From: " + IPAddress + ":" + port);
					System.err.println ("Message: " + sentence);

					String capitalizedSentence = sentence.toUpperCase(); 

					sendData = capitalizedSentence.getBytes(); 

					DatagramPacket sendPacket = 
							new DatagramPacket(sendData, sendData.length, IPAddress, 
									port); 

					serverSocket.send(sendPacket); 
				}catch(Exception e){}

			} 

		}
		catch (SocketException ex) {
			System.err.println("UDP Port 80 is occupied.");
			System.exit(1);
		}

	}



}
