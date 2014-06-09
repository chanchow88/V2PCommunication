package communication;
import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*Sender should address packages to an IP number in the 
		range between 224.0.0.1 and 239.255.255.254.*/
		final int sendingPort = 4002;
		//		final int receivingPort = 4002;
		//String msg = "Hello";
		//InetAddress mcastaddr = null;
		//MulticastSocket mSocketSend = null;
		//		MulticastSocket mSocketReceive = null;
		String ip1 = "192.168.2.255";
		ServerHandlerCom server = new ServerHandlerCom(ip1,sendingPort);
		server.start();
		String ip2 = "192.168.2.255";
		ClientHandlerCom client = new ClientHandlerCom(ip2, sendingPort);
		client.start();
	}
}




