package com.blogarticle.app.services;

public interface EmailService {
    public void sendMessage(String from,String to,String subject,String text,String reply);
    public void sendMessageWithAttachment(String from,String to,String subject,String text,String reply,String pathToAttachment);
}
