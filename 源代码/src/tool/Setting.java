
package tool;

public class Setting {
    public String password = "123456";
    public int rate=20;
    
    public void setpw(String str){
        password=str;
    }
    public void setrate(int r){
        rate=r;
    }
    public String getpw(){
        return password;
    }
    public int getrate(){
        return rate;
    }
}
