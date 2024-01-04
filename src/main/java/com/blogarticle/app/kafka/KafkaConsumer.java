package com.blogarticle.app.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = {"sihai"},groupId = "sihai-consumer-group")
    public void consumeMessage(@Payload String message,
                               @Header(KafkaHeaders.RECEIVED_KEY) String key,
                               @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                               @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timeStamp)
    {
        System.out.println("key : "+key+" partition : "+partition+" topic : "+topic+" timeStamp : "+timeStamp+" message : "+message);
    }

}
