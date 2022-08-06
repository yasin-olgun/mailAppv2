package com.mail.application.mailApp.repository;

import com.mail.application.mailApp.model.Mail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MailRepository extends MongoRepository<Mail, String> {

    List<Mail> findAllByDeliveryTimeBetween(LocalDateTime time1, LocalDateTime time2);

    List<Mail> findAllByDeliveryTimeLessThanEqual(LocalDateTime time);

}
