package tool;


import java.lang.annotation.ElementType;
import java.net.InetAddress;


public class Hash {
    InetAddress ip = null;
    String str;
    public Hash(){
        try {
            ip=InetAddress.getLocalHost();
            str = ip.getHostAddress().toString();
//            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getHash(){
        String hash="";
        int now=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '.'){
                char c;
                if(now/26>0){
                    c=(char)('a'+(now/26-1));
                    hash+=c;
                }
                c=(char)('a'+(now%26)-1);
                hash+=c;
                hash+='Z';
                now=0;
            }
            else{
                now=now*10+(str.charAt(i)-'0');
            }
        }
        char c;
        if(now/26>0){
            c=(char)('a'+(now/26-1));
            hash+=c;
        }
        c=(char)('a'+(now%26)-1);
        hash+=c;
        return hash;
    }
    
    public String DecryptHash(String hash){
        String result="";
        int now=0;
        for(int i=0;i<hash.length();i++){
            if(hash.charAt(i) == 'Z'){
                String s = String.valueOf(now);
                result+=s;
                result+='.';
                now=0;
            }
            else{
                now=now*26+(int)(hash.charAt(i)-'a'+1);
            }
        }
        String s = String.valueOf(now);
        result+=s;
        return result;
    }
    
    
    //测试用
//    public static void main(String[] args) {
//        Hash test =new Hash();
//        System.out.println(test.getHash());
//        System.out.println(test.DecryptHash(test.getHash()));
//    }
}
