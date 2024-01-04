package com.blogarticle.app.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String topic,String key,String value)
    {
        this.kafkaTemplate.send(topic,key,value);
    }

}
