package cn.tim.commentbox.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class ChineseNameUtils {
    private static List<String> nameList;

    public static String getChineseName() {
        if(nameList == null || nameList.size() == 0){
            ClassPathResource classPathResource = new ClassPathResource("static/name.txt");
            try {
                InputStream inputStream = classPathResource.getInputStream();
                nameList = IOUtils.readLines(inputStream, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Random random = new Random();
        return nameList.get(random.nextInt(nameList.size()));
    }
}
