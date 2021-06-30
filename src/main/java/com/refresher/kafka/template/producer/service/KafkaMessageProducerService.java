package com.refresher.kafka.template.producer.service;

import com.refresher.kafka.template.model.CustomMessage;
import com.refresher.kafka.template.producer.driver.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducerService {

    @Autowired
    private MessageProducer producer;

    public String sendMessageToTopic(CustomMessage message) {
        return producer.sendMessageToTopic(message);
    }

}
