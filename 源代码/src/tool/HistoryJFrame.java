
package tool;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.omg.CORBA.ORB;

public class HistoryJFrame extends JFrame{
    JLabel titleJLabel = new JLabel("历史连接记录");
    JScrollPane jsp = new JScrollPane();
    DefaultTableModel tm = new DefaultTableModel();
    JTable tb;
    String [] strings=null;
    
    public HistoryJFrame(String [][] s,int tot){
        this.setTitle("历史连接");
        this.setSize(500, 400);
        this.setResizable(false);
        this.setLayout(null);
        
        init();
        add(s,tot);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void init(){
        titleJLabel.setFont(new java.awt.Font("宋体", 0, 27));
        titleJLabel.setBounds(160, 10, 200, 50);
        this.add(titleJLabel);
        
        bulidTable();
    }
    
    public void bulidTable(){
        tb = new JTable(){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        tb.getTableHeader().setReorderingAllowed(false);
        String[] header ={"序号","设备识别码","时间"};
        tm.setColumnIdentifiers(header);
        tb.setModel(tm);
        jsp.setViewportView(tb);
        jsp.setBounds(10,115,470,200);
        this.add(jsp);
    }
    
    public void add(String[][] str,int totol){
        for(int i=0;i<totol;i++){
            tm.addRow(str[i]);
        }
    }
    
//    public static void main(String[] args) {
//        String[][] str= {{"1","192.168.135.2",""},{"2","192.168.135.2",""}};
//        HistoryJFrame test = new HistoryJFrame(str);
//    }
}
