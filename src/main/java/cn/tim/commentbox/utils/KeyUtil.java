package cn.tim.commentbox.utils;

public class KeyUtil {
    public static synchronized String generateKey(){
        return String.valueOf(System.currentTimeMillis());
    }
}
