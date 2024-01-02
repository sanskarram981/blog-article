package com.blogarticle.app.services.impl;

import com.blogarticle.app.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.FileSystem;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendMessage(String from, String to, String subject, String text,String reply)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        simpleMailMessage.setReplyTo(reply);
        simpleMailMessage.setCc("satyarai7887@gmail.com","motigupta80855@gmail.com");
        try
        {
            this.javaMailSender.send(simpleMailMessage);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void sendMessageWithAttachment(String from, String to, String subject, String text, String reply, String pathToAttachment)
    {
        try
        {
            MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(new InternetAddress(from));
            mimeMessageHelper.setTo(new InternetAddress(to));
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text);
            mimeMessageHelper.setReplyTo(new InternetAddress(reply));
            mimeMessageHelper.setCc(new InternetAddress[]{new InternetAddress("satyarai7887@gmail.com"),new InternetAddress("motigupta80855@gmail.com")});

            FileSystemResource fileSystemResource = new FileSystemResource(new File(pathToAttachment));
            mimeMessageHelper.addAttachment("Purchase_Invoice.pdf",fileSystemResource);
            this.javaMailSender.send(mimeMessage);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


}
