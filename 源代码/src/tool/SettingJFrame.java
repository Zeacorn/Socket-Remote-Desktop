package tool;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.omg.CORBA.ORB;


public class SettingJFrame extends JFrame{
    JLabel jl1 = new JLabel("延迟大小：");
    JLabel jl2 = new JLabel("密码：");
    JButton submit = new JButton("应用");
    JTextArea jta1 = new JTextArea();
    JTextArea jta2 = new JTextArea();
    Setting setting =null;
    
    
    public SettingJFrame(Setting s){
        this.setTitle("系统设置");
        this.setSize(300, 200);
        this.setResizable(false);
        this.setLayout(null);
        setting=s;
        init();
        AddActionListener();
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void init(){
        jl1.setFont(new Font("微软雅黑", Font.BOLD, 15));
        jl1.setBounds(40,20,80,20);
        jl2.setFont(new Font("微软雅黑", Font.BOLD, 15));
        jl2.setBounds(40,60,80,20);
        
        jta1.setBounds(110,20,100,20);
        jta2.setBounds(110,60,100,20);
        jta1.setText(String.valueOf(setting.getrate()));
        jta2.setText(setting.getpw());
        
        submit.setBounds(120,120,60,30);
        
        this.add(jl1);
        this.add(jta1);
        this.add(jl2);
        this.add(jta2);
        this.add(submit);
    }
    
    public  void AddActionListener() {
        submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setting.setrate(Integer.valueOf(jta1.getText().trim()));
                    setting.setpw(jta2.getText().trim());
                    JOptionPane.showMessageDialog(null,"设置成功","成功",JOptionPane.INFORMATION_MESSAGE);
                }
        });
    }
    
//    public static void main(String[] args) {
//        SettingJFrame test = new SettingJFrame();
//    }
}
