package cn.tim.commentbox.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.HashMap;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class SendMailServiceTest {

    @Resource
    private SendMailService sendMailService;

    @Test
    public void sendSimpleMessage() {
        sendMailService.sendSimpleMessage("1610984228@qq.com", "这是一封测试邮件", "消息通知的形式也有很多，比如：短信、邮件、app推送等，本文主要给大家描述一下邮件通知的形式，因为邮件相比较其他通知渠道更方便实用（免费），除了简单文本邮件（已经满足大多数情形），本文还会重点说一下集成Thymeleaf模版引擎，使用HTML的形式发送邮件，尽管HTML内容不是标准化的消息格式，但是许多邮件客户端至少支持标记语言的子集，这种方式相比较纯文本展现形式更加友好。\n" +
                "\n" +
                "作者：Tim\n" +
                "链接：https://zouchanglin.cn\n" +
                "著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。");
    }

    @Test
    public void sendTemplateMessage() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userName", "邹长林");
        map.put("commentContent", "本篇文章主要是记录一下高级控件ListView和CardView的使用方式和注意事项，虽然目前都已经用RecyclerView替代了ListView但是了解其中的原理和优化还是有必要的，关于ListView的原理和真正意义上的优化在后面会专门写一片文章来讲述，本篇只谈其具体使用与必须优化的方式。至于CardView其实用的还是比较多的，可以看到我的小Demo实现的效果还是很不错！最后涉及到了一些Android屏幕适配问题的解决方案。");
        map.put("hrefUrl", "http://localhost:4000/2020/11/19/58995.html");
        sendMailService.sendTemplateMail("1610984228@qq.com", map, "回复", "index.ftl");
    }
}
