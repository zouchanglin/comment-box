package cn.tim.commentbox;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class CommentBoxApplicationTests {
    private AtomicInteger index = new AtomicInteger(0);
    /**
     * 线程池
     */
    public static final ExecutorService executor = new ThreadPoolExecutor(4, 8,
            10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1024));

    @Test
    public void contextLoads() {
    }

//    public static void main(String[] args) throws Exception {
//        for (int i = 181; i < 512; i++) {
//            Task task = new Task(getUrl(), i);
//            executor.submit(task);
//        }
//    }

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/changlinzou/Downloads/name.txt");

        Document document = Jsoup.parse(new URL("https://name.guguyu.com/keai/p-10.html"), 5000);
        Elements select = document.select("ul > li > a");
        for (Element element: select){
            if(element.text().length() == 1){
                break;
            }
            System.out.println(element.text());
            FileUtils.write(file, element.text()+"\n", "UTF-8", true);
        }
    }

    static class Task implements Runnable{
        URL url;
        int index;

        public Task(URL url, int index) {
            this.url = url;
            this.index = index;
        }

        @Override
        public void run() {
            try {
                byte[] byteArray = IOUtils.toByteArray(url);
                File file = new File("/Users/changlinzou/Downloads/img/" + index + ".jpg");
                FileUtils.writeByteArrayToFile(file, byteArray);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static URL getUrl() throws Exception{
        //URL url = new URL("http://api.btstu.cn/sjtx/api.php");
        URL url = new URL("https://api.uomg.com/api/rand.avatar");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();
        if (responseCode == 302) {
            String location = conn.getHeaderField("Location");
            //String str = "http:" + location.substring(location.indexOf("s") + 2);
            String str = location;
            System.out.println("url = " + str);
            return new URL(str);
        }
        return null;
    }
}