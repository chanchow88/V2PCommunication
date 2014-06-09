package communication;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class ClientHandlerCom extends Thread{
	private int receivingPort;
	private String ip;
	InetAddress mcastaddr;
	MulticastSocket mSocketReceive;
	DatagramPacket dPacketrec;
	
	public ClientHandlerCom(String IP, int Port){
		receivingPort = Port;
		this.ip = IP;
		
		try{
			mcastaddr = InetAddress.getByName(ip);
			mSocketReceive = new MulticastSocket(receivingPort);
			mSocketReceive.joinGroup(mcastaddr);
			
		}catch(SocketException se1){
			System.out.println("Socket Exception at receiving:"+se1);}
		catch (IOException e1) {
			System.out.println("IOException at receiving:"+e1);}
	}
	@Override
	public void run() {
		while(true){
		byte[] b = new byte[256];
		dPacketrec = new DatagramPacket(b, b.length);
		try {
			mSocketReceive.receive(dPacketrec);
			String rc = new String(dPacketrec.getData(),0,dPacketrec.getLength());
			System.out.println("Received data from: " + dPacketrec.getAddress().toString() +
					":" + dPacketrec.getPort() + " with length: " +
					dPacketrec.getLength()+"message "+ rc);
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
		/*finally {
				 if (mSocketSend != null){
					 try {
						 mSocketSend.leaveGroup(mcastaddr);
						 mSocketSend.close();
					 }
					 catch (IOException e){ }
				 	}
				 }*/
	}
}
