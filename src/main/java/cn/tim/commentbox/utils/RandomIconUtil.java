package cn.tim.commentbox.utils;

import java.util.Random;

public class RandomIconUtil {
    public static String getRandomIconUrl(){
        Random random = new Random();
        int v = random.nextInt(10000);
        return "https://www.thiswaifudoesnotexist.net/example-" + v + ".jpg";
    }
}
