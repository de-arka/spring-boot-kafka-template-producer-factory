package com.refresher.kafka.template.producer.driver;

import com.refresher.kafka.template.model.CustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class MessageProducer {

    private static final String TOPIC_NAME = "kafka";
    private final KafkaTemplate<String, CustomMessage> kafkaTemplate;

    @Autowired
    public MessageProducer(KafkaTemplate<String, CustomMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendMessageToTopic(CustomMessage message) {

        final String[] status = {""};

        // the KafkaTemplate provides asynchronous send methods returning a Future
        ListenableFuture<SendResult<String, CustomMessage>> future = kafkaTemplate.send(TOPIC_NAME, message);

        // register a callback with the listener to receive the result of the send asynchronously
        future.addCallback(new ListenableFutureCallback<SendResult<String, CustomMessage>>() {

            @Override
            public void onSuccess(SendResult<String, CustomMessage> result) {
                log.info("Kafka sent message='{}' with offset={}", message,
                        result.getRecordMetadata().offset());
                status[0] = "SUCCESS";
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Kafka unable to send message='{}'", message, ex);
                status[0] = "FAILURE";
            }

        });

        return status[0];

    }

}
