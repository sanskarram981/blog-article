package com.blogarticle.app.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public void sendMessage(String topic, String key, HashMap<String,Object> logRequestDto)
    {
        try
        {
            this.kafkaTemplate.send(topic,key, logRequestDto);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
