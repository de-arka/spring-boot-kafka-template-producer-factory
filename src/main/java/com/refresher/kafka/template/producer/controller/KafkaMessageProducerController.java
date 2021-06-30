package com.refresher.kafka.template.producer.controller;

import com.refresher.kafka.template.model.CustomMessage;
import com.refresher.kafka.template.producer.service.KafkaMessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class KafkaMessageProducerController {

    @Autowired
    private KafkaMessageProducerService service;

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessageToTopic(@RequestBody CustomMessage message) {
        return new ResponseEntity<>(service.sendMessageToTopic(message), HttpStatus.OK);
    }

}
