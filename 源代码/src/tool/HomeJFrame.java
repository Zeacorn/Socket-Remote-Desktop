package tool;

import client.check;
import client.Cntroller;
import server.RemoteServer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
 

public class HomeJFrame extends JFrame{
        JLabel label1,label2,label3,label4,label5,label6;
        JTextField text1;
        JButton b1,b2,b3,b4,b5,b6,b7;
        JSeparator sep;
        Clipboard clipboard;
        JPanel jp1=new JPanel();   
        JPanel jp2=new JPanel();
        Hash hash = new Hash();
        static  Setting setting = new Setting();
        String[][]date = new String[100][3];
        int idx=0;


        public HomeJFrame(){
        this.setTitle("GoDesk");
        sep=new JSeparator();
        sep.setBounds(350, 155, 200, 65);
        sep.setOrientation(javax.swing.SwingConstants.VERTICAL);
        
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        
        
        label1 = new JLabel("此设备");
        label1.setFont(new Font("微软雅黑", Font.BOLD, 30));
        label1.setBounds(120, 70, 200, 30);
        
        label2 = new JLabel("设备识别码");
        label2.setFont(new Font("微软雅黑", Font.BOLD, 20));
        label2.setBounds(120, 150, 200, 30);
        
        
        label3 = new JLabel("远程控制设备");
        label3.setFont(new Font("微软雅黑", Font.BOLD, 30));
        label3.setBounds(120,310, 200, 30);
        
        label4 = new JLabel("密码");
        label4.setFont(new Font("微软雅黑", Font.BOLD, 20));
        label4.setBounds(385, 150, 200, 30);
        
        
        label5 = new JLabel();
        label5.setFont(new Font("微软雅黑", Font.BOLD, 20));
        label5.setBounds(120, 190, 200, 30);
        
        label6 = new JLabel();
        label6.setFont(new Font("微软雅黑", Font.BOLD, 20));
        label6.setBounds(385,190, 180, 30);
        
//        b1 = new JButton("远程控制");
//        b1.setFont(new Font("微软雅黑", Font.BOLD, 30));
//        b1.setBounds(90,280, 180, 40);
//        b1.setBorderPainted(false); 
        
        b2 = new JButton("历史连接");
        b2.setFont(new Font("微软雅黑", Font.BOLD, 30));
        b2.setBounds(90,250, 180, 40);

        
        b3 = new JButton("系统设置");
        b3.setFont(new Font("微软雅黑", Font.BOLD, 30));
        b3.setBounds(90,380, 180, 40);

        
        b4 = new JButton("连接");
        b4.setFont(new Font("微软雅黑", Font.BOLD, 30));
        b4.setBounds(120,420, 470, 45);

        
        b5 = new JButton("复制");
        b5.setFont(new Font("微软雅黑", Font.BOLD, 15));
        b5.setBounds(270, 185, 64, 35);

        
        b6 = new JButton("显示");
        b6.setFont(new Font("微软雅黑", Font.BOLD, 15));
        b6.setBounds(530, 185, 64, 35);
        
        b7 = new JButton("隐藏");
        b7.setFont(new Font("微软雅黑", Font.BOLD, 15));
        b7.setBounds(600, 185, 64, 35);
        
         
        text1 = new JTextField();
        text1.setPreferredSize(new Dimension (200,40));
        text1.setFont(new Font("仿宋", Font.BOLD,15));
        text1.setBounds(120,370, 470, 30);
        
       
        
        JSplitPane hSplitPane = new JSplitPane();
        hSplitPane.setLeftComponent(jp2);
        JSplitPane vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,false);
	hSplitPane.setRightComponent(jp1);
        hSplitPane.setDividerLocation(300);   
        hSplitPane.setDividerSize(1);
        
        jp1.setLayout(null);
        jp1.add(label1);
        jp1.add(label2);
        jp1.add(label3);
        jp1.add(text1);
        jp1.add(b4);
        jp1.add(sep);
        jp1.add(label4);
        jp1.add(label5);
        jp1.add(label6);
        jp1.add(b5); 
        jp1.add(b6);
        jp1.add(b7);
        
        jp2.setLayout(null);
        jp2.add(b2);
        jp2.add(b3);
        
        label5.setText(hash.getHash());
        label6.setText(setting.getpw());
        
        this.add(hSplitPane);
        this.setVisible(true);
        this.setSize(1100,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);        
        
        }
        private void AddActionListener() {
            //历史记录
            b2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   HistoryJFrame hjf= new HistoryJFrame(date,idx);
                }
            });
            //系统设置
             b3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   SettingJFrame sjf=new SettingJFrame(setting);
                }
            });
            
            //连接
            b4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {	
                        String number = text1.getText().trim();
                        adddate(number);
                        check ch = new check(number,setting);
                        ch.AddActionListener(ch);
                 
                    } catch (Exception ex) {
                            ex.printStackTrace();
                    }
                }
            });
            //复制功能
            b5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String textString=label5.getText().trim();
                    Transferable text = new StringSelection(textString);
                    clipboard.setContents(text, null);
                }
            });
            //显示密码
            b6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    label6.setText(setting.getpw());
                }
            });
            //隐藏密码
            b7.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    label6.setText("********");
                }
            });
        }
        //更新连接记录
        public void adddate(String str){
            Time time = new Time();
            String t = time.Time();
            date[idx][0]=String.valueOf(idx+1);
            date[idx][1]=str;
            date[idx][2]=t;
            idx++;
        }
        //获取当前时间
        public class Time{
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            public String Time() {
            String time = formatter.format(calendar.getTime());
            return time;
            }
        }
    public static void main(String[] args) {
        HomeJFrame test = new HomeJFrame();
        test.AddActionListener();
        try {
	    new RemoteServer().setupServer(9090,setting.getrate());
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
 }

