package com.wg.base.backend.service.impl;

import com.wg.base.backend.common.Constant;
import com.wg.base.backend.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void send(String message) {
        kafkaTemplate.send(Constant.KAFKA_TOPIC,message);
    }

    @Override
    @KafkaListener(topics = Constant.KAFKA_TOPIC)
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        log.info("topic is: " + consumerRecord.topic() );
        log.info("key is: " + consumerRecord.key());
        log.info("partition is: " + consumerRecord.partition());
        log.info("offset is: " + consumerRecord.offset());
        log.info("message is: "+ consumerRecord.value());
    }
}
