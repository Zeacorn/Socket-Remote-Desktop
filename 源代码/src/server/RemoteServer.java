package server;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteServer {

	private  ObjectInputStream objectInputStream;
	private OutputStream ous;
	public void setupServer(int port,int r) throws Exception { 
		ServerSocket serverSocket = new ServerSocket(port);
		 while(true){
			 Socket socket = serverSocket.accept();
			 InputStream ins = socket.getInputStream();
			 objectInputStream= new ObjectInputStream(ins);
			 ous=socket.getOutputStream();
		         DataOutputStream dous=new DataOutputStream(ous);
			 EventReadThread eventReadThread = new EventReadThread(objectInputStream);
			 eventReadThread.start();
			 CaptureThread captureThread = new CaptureThread(dous,r);
			 captureThread.start();
		 }
	}
	
//	public static void main(String[] args) {
//		try {
//			new RemoteServer().setupServer(9090);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
