package com.wg.base.backend.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaService {

    void send(String message);

    void receive(ConsumerRecord<?,?> consumerRecord);
}
