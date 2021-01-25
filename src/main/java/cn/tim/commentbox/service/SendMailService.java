package cn.tim.commentbox.service;

import java.util.Map;

public interface SendMailService {
    void sendSimpleMessage(String to, String subject, String text);

    void sendTemplateMail(String to, Map<String, Object> params, String title, String templateName);
}
