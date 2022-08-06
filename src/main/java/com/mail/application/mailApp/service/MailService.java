package com.mail.application.mailApp.service;

import com.mail.application.mailApp.model.Mail;
import com.mail.application.mailApp.model.User;
import com.mail.application.mailApp.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailRepository mailRepository;
    private final UserService userService;

    public List<Mail> getAll() {
        return mailRepository.findAll();

    }

    public Mail getMail(String id) {

        return mailRepository.findById(id).orElse(null);
    }

    public List<Mail> getBySenderId(String id) {
        // return mailRepository.findBySenderId(id);
        return null;
    }

    public List<Mail> getByReceiverId(String id) {
        //   return mailRepository.findByReceiverIdsContains(id);
        return null;

    }

    public List<Mail> getUnread(String id) {
        //  return mailRepository.findByReceiverIdsContainsAndReadIs(id, false);
        return null;
    }

    //todo mongoOperations updateMulti var onu dene
    // impl icerisinde mongoOperations tanimli final
    // mongoop.updateMulti(query(where("age").is(Integer.valueOf(current))update.update("age",Integer.valueOf(n)),Person.class)
    // static olan fieldlar heap de değil, stack de saklaniyorlar, ilk yuklendiginde stackte yerini alip sonra degismiyor
    // static degiskenlere dikkat edilmeli thread safe degil, ona dikkat edilmeli
    // static method icin bu gecerli degil
    // spring scheduled ?
    // assertEquals(expected 8, result.getModifiedCount
    // request param list/?a=4&b=4
    // request required false
    // genelde request param kullan path variable cok kullanilmiyor
    // bulk operations, save insert binlerce defa kullanildiginde performans farki olur
    // ancak bu durumlarda bulk operations kullanilmalidir


    public Mail send(Mail mail) {

        mail.setId(UUID.randomUUID().toString());
        mail.setDeliveryTime(LocalDateTime.now());
        mail.setRead(true);
        mailRepository.save(mail);

        User sender = userService.getByMail(mail.getSenderAddress());
        List<String> sentIds = sender.getSentMailIds() == null ? new ArrayList<String>() : sender.getSentMailIds();
        sentIds.add(mail.getId());
        sender.setSentMailIds(sentIds);
        userService.save(sender);


        List<String> receiver = mail.getReceiverAddresses();
        mail.setRead(false);
        for (String address : receiver) {
            User user = userService.getByMail(address);
            List<String> incomingIds = user.getIncomingMailIds() == null ? new ArrayList<String>() : user.getIncomingMailIds();

            incomingIds.add(mail.getId());
            user.setIncomingMailIds(incomingIds);
            userService.save(user);
        }

        return mail;
    }

    public void deleteMail(String id) {
        mailRepository.findById(id).ifPresent(mail -> {
            mailRepository.deleteById(id);
        });

    }

}
