package cn.tim.commentbox.service.impl;

import cn.tim.commentbox.config.SendMailConfig;
import cn.tim.commentbox.service.SendMailService;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class SendMailServiceImpl implements SendMailService {
    @Resource
    private JavaMailSender emailSender;

    @Resource
    private SendMailConfig sendMailConfig;

    @Resource
    private FreeMarkerConfigurer markerConfigurer;

    private static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        fixedThreadPool.execute(()->{
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(to);
                message.setFrom(sendMailConfig.getSender());
                message.setSubject(subject);
                message.setText(text);
                emailSender.send(message);
            } catch (MailException exception) {
                log.error("SendMailServiceImpl sendSimpleMessage e = ", exception);
            }
        });
    }

    @Override
    public void sendTemplateMail(String to, Map<String, Object> params, String title, String templateName) {
        fixedThreadPool.execute(()->{
            try {
                MimeMessage mimeMessage = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom(sendMailConfig.getSender());
                helper.setTo(InternetAddress.parse(to));
                //邮件标题
                helper.setSubject("【" + title + "-" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "】");
                try {
                    Template template = markerConfigurer.getConfiguration().getTemplate(templateName);
                    //赋值后的模板邮件内容
                    String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
                    //邮件内容
                    helper.setText(text, true);
                    emailSender.send(mimeMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
