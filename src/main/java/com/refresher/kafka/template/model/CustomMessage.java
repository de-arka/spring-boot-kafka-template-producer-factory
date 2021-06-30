package com.refresher.kafka.template.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomMessage implements Serializable {
    private String messageId;
    private String messageContent;
}
