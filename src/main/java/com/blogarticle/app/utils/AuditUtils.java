package com.blogarticle.app.utils;

import com.blogarticle.app.constants.SihaiConstants;
import com.blogarticle.app.entities.Audit;
import com.blogarticle.app.kafka.KafkaProducer;
import com.blogarticle.app.repositories.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class AuditUtils {
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private AuditRepository auditRepo;

    public void audit(String resource,String method,String path)
    {
        HashMap<String,Object> logRequestDto = new HashMap<>();
        logRequestDto.put("resource",resource);
        logRequestDto.put("method",method);
        logRequestDto.put("path", SihaiConstants.SIHAI_URI+path);
        logRequestDto.put("hitDate",new Date());
        this.kafkaProducer.sendMessage("sihai","audit-data", logRequestDto);
    }
    @KafkaListener(topics = {"sihai"},groupId = "sihai-consumer-group")
    public void consumeMessage(@Payload HashMap<String,Object> logRequestDto,
                               @Header(KafkaHeaders.RECEIVED_KEY) String key,
                               @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                               @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timeStamp)
    {
        saveAudit(logRequestDto);
        //System.out.println("key : "+key+" partition : "+partition+" topic : "+topic+" timeStamp : "+timeStamp+" message : "+ logRequestDto);
    }
    private void saveAudit(HashMap<String,Object> logRequestDto)
    {
        Audit audit = new Audit();
        audit.setResource(logRequestDto.get("resource").toString());
        audit.setPath(logRequestDto.get("path").toString());
        audit.setMethod(logRequestDto.get("method").toString());
        audit.setHitDate(new Date((long)logRequestDto.get("hitDate")));
        this.auditRepo.save(audit);
    }

}
