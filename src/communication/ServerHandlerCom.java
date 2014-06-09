package communication;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class ServerHandlerCom extends Thread{
	private int SendingPort;
	private String ip;
	String msg = "Hello";
	DatagramPacket dPacketsend;
	InetAddress mcastaddr;
	MulticastSocket mSocketSend;
	int i=0;
	public ServerHandlerCom(String IP, int Port){
		SendingPort = Port;
		this.ip = IP;
		try{
			System.out.println("In server ...");
			mcastaddr = InetAddress.getByName(ip);
			mSocketSend = new MulticastSocket(SendingPort);
			mSocketSend.joinGroup(mcastaddr);
					}
		catch(SocketException se){
			System.out.println("Socket Exception at sending:"+se);}
		catch (IOException e) {
			System.out.println("IOException at sending:"+e);}
	}
	@Override
	public void run() {
		while(true){
			String tmp = msg.concat(Integer.toString(i));
			dPacketsend = new DatagramPacket(tmp.getBytes(), tmp.length(), 
				mcastaddr, SendingPort);
//		System.out.println("the sending msg: "+msg.concat(Integer.toString(i)));
		try {
			mSocketSend.send(dPacketsend);
			i++;
//			System.out.println(i++);
		} catch (Exception e) {
			System.out.println("error:"+e);// TODO: handle exception
		}
	}
	}
}