package cn.tim.commentbox.utils;

import java.util.Random;

public class RandomIconUtil {
    public static String getRandomIconUrl(){
        Random random = new Random();
        int v = random.nextInt(180);
        return "/api/img/" + v + ".jpg";
    }
}
