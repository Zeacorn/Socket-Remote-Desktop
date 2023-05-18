package server;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


public class CaptureThread extends Thread{
	private DataOutputStream dataOutputStream;
	private Toolkit tk ;
	private  Dimension dm ;
	private Rectangle rec;
	private  Robot robot ;
        private int rate;
        
	public CaptureThread(DataOutputStream dataOutputStream,int r) throws AWTException {
		this.dataOutputStream = dataOutputStream;
                //初始化截图需要的参数
		tk = Toolkit.getDefaultToolkit();
		dm = tk.getScreenSize();
		rec = new Rectangle(0, 0, (int)dm.getWidth(), (int)dm.getHeight());
		robot  = new Robot();
                rate=r;
	}
	
	@Override
	public void run() {
		while(true){
			byte[] data = createCature();
			try {
                                //传输截图
				dataOutputStream.writeInt(data.length);
				dataOutputStream.write(data);
				dataOutputStream.flush();
				try {
                                        //设置延迟
					Thread.sleep(rate);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("网络有问题,截屏失败");
			}
			
		}
	
	}
        //获取屏幕截图并转成byte数组
	private byte[] createCature() {
		BufferedImage bimage = robot.createScreenCapture(rec);
		ByteArrayOutputStream byout = new ByteArrayOutputStream();
		try {
			ImageIO.write(bimage, "jpg", byout);
		} catch (IOException e) {
			System.out.println("截屏图片写入内存流中出现异常");
			e.printStackTrace();
		}
		return byout.toByteArray();
	}
	
	

	
}
