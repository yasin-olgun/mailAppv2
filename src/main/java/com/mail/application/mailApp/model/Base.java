package com.mail.application.mailApp.model;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Base {

    @Id
    String id;

}
