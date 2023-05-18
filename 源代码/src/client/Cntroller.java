package client;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

import tool.RemoteJframe;

public class Cntroller extends Thread{

	private ObjectOutputStream ous;
	private DataInputStream ins;
	public void showui(){ //创建窗口
		RemoteJframe jf = new RemoteJframe("GoDesk",ous);
		addListener(jf);
		jf.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				System.out.println(arg0.getNewValue());
			}
		});
	}

		public void conServer(String ip,int port)
		throws Exception{
			Socket sc=new Socket(ip,port);
			ins=new DataInputStream(sc.getInputStream());
			ous=new ObjectOutputStream(sc.getOutputStream());
		}
	@Override
	public void run() {
		try{
			while(true){
				int len=ins.readInt();
				byte[] data=new byte[len];
				ins.readFully(data);
				ImageIcon ic=new ImageIcon(data);
				RemoteJframe.setImgLabel( ic,2);
			}
			}catch(Exception ef){
				System.out.println("获取数据流出现异常");
				ef.printStackTrace();
			}
	}
	
	public void addListener(RemoteJframe jf ){
        //鼠标点击事件
       jf.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				sentEvent(e);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				sentEvent(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
       
       //鼠标移动事件
       jf.addMouseMotionListener(new MouseMotionListener() {
		@Override
		public void mouseMoved(MouseEvent arg0) {
			sentEvent(arg0);
		}
		@Override
		public void mouseDragged(MouseEvent arg0) {
			sentEvent(arg0);
		}
		
	});
       //鼠标滚轮事件
       jf.addMouseWheelListener(new MouseWheelListener() {
		
		@Override
		public void mouseWheelMoved(MouseWheelEvent arg0) {
			sentEvent(arg0);
			
		}
	});
       
       //键盘事件
       jf.addKeyListener(new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent arg0) {
			sentEvent(arg0);
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) {
			sentEvent(arg0);
			
		}
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			sentEvent(arg0);
			
		}
	});
	}
	
	public void sentEvent(InputEvent e){
		try {
			ous.writeObject(e);
		} catch (IOException e1) {
			System.out.println("发送事件对象出现异常");
			e1.printStackTrace();
		}
	}
	
	
//	public static void main(String[] args) {
//	 try {
//		 Cntroller cn = new Cntroller();
//		 cn.showui();
//		cn.conServer("localhost", 9090);
//		 cn.start();
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	}
}
