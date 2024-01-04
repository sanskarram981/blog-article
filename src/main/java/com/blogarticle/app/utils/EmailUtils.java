package com.blogarticle.app.utils;

import com.blogarticle.app.constants.SihaiConstants;
import com.blogarticle.app.entities.Audit;
import com.blogarticle.app.kafka.KafkaProducer;
import com.blogarticle.app.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class EmailUtils {
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private EmailService emailService;

    public void sendMessage(String to,String subject,String text)
    {
        HashMap<String,Object> logRequestDto = new HashMap<>();
        logRequestDto.put("to",to);
        logRequestDto.put("subject",subject);
        logRequestDto.put("text",text);
        this.kafkaProducer.sendMessage("sihai-email","email-notification",logRequestDto);
    }

    @KafkaListener(topics = {"sihai-email"},groupId = "sihai-email-consumer-group")
    public void consumeMessage(@Payload HashMap<String,Object> logRequestDto,
                               @Header(KafkaHeaders.RECEIVED_KEY) String key,
                               @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                               @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timeStamp)
    {
         this.sendMessage(logRequestDto);
    }

    private void sendMessage(HashMap<String,Object> logRequestDto)
    {
        String to = logRequestDto.get("to").toString();
        String subject = logRequestDto.get("subject").toString();
        String text = logRequestDto.get("text").toString();
        this.emailService.sendMessage("sanskarram992@gmail.com",to,subject,text,"sanskarram992@gmail.com");
    }
}
