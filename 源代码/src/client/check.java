
package client;

import tool.Hash;
import tool.Setting;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import jdk.nashorn.internal.objects.NativeRegExp;

public class check extends JFrame{
    JLabel jl;
    JTextArea text;
    JButton signin;
    String str;
    Hash hash = new Hash();
    Setting setting = null;
    public check(String s,Setting set){
        str=s;
        setting = set;
        this.setTitle("连接验证");
        this.setSize(300, 200);
        this.setLayout(null);
        
        jl = new JLabel("请输入密码：");
        jl.setFont(new Font("微软雅黑", Font.BOLD, 15));
        jl.setBounds(30, 50, 90, 30);
        
        text = new JTextArea();
        text.setFont(new Font("微软雅黑", Font.BOLD, 15));
        text.setBounds(125,55,150,20);
        
        signin = new JButton("确定");
        signin.setFont(new Font("微软雅黑", Font.BOLD, 15));
        signin.setBounds(100,100,70,30);
        
        this.add(jl);
        this.add(text);
        this.add(signin);
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void AddActionListener(check jf) {
        signin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String input = text.getText().trim();
                    if(input.equals(setting.getpw())){
                        try {
                             jf.setVisible(false);
                             Cntroller cn = new Cntroller();
                             cn.showui();
                             cn.conServer(hash.DecryptHash(str), 9090);
                             cn.start();

                        } catch (Exception ex) {
                                ex.printStackTrace();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"密码错误","错误",JOptionPane.WARNING_MESSAGE);
                    }
                }
        });
    }
    
//    public static void main(String[] args) {
//        check test = new check();
//        test.AddActionListener(test);
//    }
}
