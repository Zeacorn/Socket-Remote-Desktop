package tool;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class RemoteJframe extends JFrame{


	private static final long serialVersionUID = 1L;

	private ObjectOutputStream ous;
	private static JLabel img_jLabel=new JLabel();
	public RemoteJframe(String title,ObjectOutputStream ous){
		super(title);
		this.ous=ous;

		  Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());

		  int taskBarHeight = screenInsets.bottom; 
		  Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		  double ratio = screenDimension.getWidth()/screenDimension.getHeight();
		  setSize((int)(screenDimension.getWidth()-taskBarHeight*ratio), (int)(screenDimension.getHeight()-taskBarHeight));  
		  setVisible(true);  
		  setAlwaysOnTop(true);
		  //setDefaultCloseOperation(3);
		  try {  
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
	         } catch (Exception e) {  
	           
	         }   
		 add(img_jLabel);
	}
	
	

	public static void setImgLabel(ImageIcon icon,int type) {
		switch (type) {
		case 1:
			
			break;
		case 2:
		        showImagByRatio(icon);
			break;
		case 3:
			
			break;
		default:
			break;
		}
	}
	private static void showImagByRatio(ImageIcon icon) {
		 int imgWidth=icon.getIconWidth();
         int imgHeight=icon.getIconHeight();
         int conWidth=img_jLabel.getWidth();
         int conHeight=img_jLabel.getHeight();
         int reImgWidth;
         int reImgHeight;
         if(imgWidth/imgHeight>=conWidth/conHeight){
             if(imgWidth>conWidth){
                 reImgWidth=conWidth;
                 reImgHeight=imgHeight*reImgWidth/imgWidth;
             }else{
                 reImgWidth=imgWidth;
                 reImgHeight=imgHeight;
             }
         }else{
             if(imgWidth>conWidth){
                 reImgHeight=conHeight;
                 reImgWidth=imgWidth*reImgHeight/imgHeight;
             }else{
                 reImgWidth=imgWidth;
                 reImgHeight=imgHeight;
             }
         }
         icon=new ImageIcon(icon.getImage().getScaledInstance(reImgWidth,reImgHeight, Image.SCALE_DEFAULT));
         img_jLabel.setIcon(icon);
         img_jLabel.repaint();
		
	}

	
	
}
