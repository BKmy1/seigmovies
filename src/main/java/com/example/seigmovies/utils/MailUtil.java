package com.example.seigmovies.utils;

import com.example.seigmovies.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailUtil {

    //注入QQ发送邮件的bean
//    @Autowired
//    private JavaMailSender javaMailSender;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Async
    public void sendSimpleMail(Mail mail) throws MessagingException {
        // 简单发送
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom(mail.getSendername());
//        simpleMailMessage.setTo(mail.getRecipient());
//        //设置抄送邮箱
//        //simpleMailMessage.setCc(cc);
//        simpleMailMessage.setSubject(mail.getSubject());
//        simpleMailMessage.setText(mail.getContent());

        // 复杂发送
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(mail.getSendername());
        messageHelper.setTo(mail.getRecipient());
        messageHelper.setText(mail.getContent());
        messageHelper.setSubject(mail.getSubject());
        messageHelper.addAttachment(mail.getName(),mail.getFile());
        javaMailSender.send(mimeMessage);
    }
}
