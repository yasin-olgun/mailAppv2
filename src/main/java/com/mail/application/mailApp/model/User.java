package com.mail.application.mailApp.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@TypeAlias("User")
@Data
@Accessors(chain = true)
public class User extends Base {

    private String userName;
    private String firstName;
    private String lastName;
    private String mailAddress;


    private List<String> incomingMailIds;
    private List<String> sentMailIds;
    private List<String> read;


}
