package server;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;

public class EventReadThread extends Thread{

	private  ObjectInputStream objins;

	public EventReadThread(ObjectInputStream objins) {
		this.objins = objins;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Object eventobj = objins.readObject();
				InputEvent e = (InputEvent) eventobj;
				actionEvent(e);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	        //执行事件
		private void actionEvent(InputEvent e){
			Robot robot =null;
			try {
				robot = new Robot();
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
                        //判断是鼠标事件还是键盘事件
			if(e instanceof KeyEvent){
				KeyEvent ke=(KeyEvent) e;
				if(e.getID()==KeyEvent.KEY_PRESSED){  //按下
					robot.keyPress(ke.getKeyCode());
				}
				if(e.getID()==KeyEvent.KEY_RELEASED){  //松开
					robot.keyRelease(ke.getKeyCode());
				}
			}
                        if(e instanceof MouseEvent){
                                MouseEvent me = (MouseEvent)e;
                                int type = me.getID();
                                if(type==MouseEvent.MOUSE_PRESSED){  //按下
                                        robot.mousePress(getMouseClick(me.getButton()));
                                }
                                if(type==MouseEvent.MOUSE_RELEASED){  //松开
                                        robot.mouseRelease(getMouseClick(me.getButton()));
                                }
                                if(type==MouseEvent.MOUSE_MOVED) {  //移动
                                        robot.mouseMove(me.getX(), me.getY());
                                }
                                if(type==MouseEvent.MOUSE_DRAGGED) { //拖动
                                        robot.mouseMove(me.getX(), me.getY());
                                }
                                if(type==MouseEvent.MOUSE_WHEEL) {  //鼠标滚轮
                                        robot.mouseWheel(getMouseClick(me.getButton()));
                                }
                        }
			
		}
	
                //根据发送事的Mouse事件对象，转变为通用的Mouse按键代码
		private int getMouseClick(int button){
			  if(button==MouseEvent.BUTTON1){
			      return InputEvent.BUTTON1_MASK;
			   } 
			  if(button==MouseEvent.BUTTON2){
				  return InputEvent.BUTTON2_MASK;
			 } 
			 if(button==MouseEvent.BUTTON3){
				  return InputEvent.BUTTON3_MASK;
			 }
			return -1;
		}
}
