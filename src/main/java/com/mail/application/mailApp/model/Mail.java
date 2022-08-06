package com.mail.application.mailApp.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@TypeAlias("Mail")
@Data
@Accessors(chain = true)
public class Mail extends Base {

    private String senderAddress;
    private List<String> receiverAddresses;
    private String subject;
    private String message;
    private LocalDateTime deliveryTime;
    private Boolean read;



}
