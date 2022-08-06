package com.mail.application.mailApp;

import com.mail.application.mailApp.model.Mail;
import com.mail.application.mailApp.model.User;
import com.mail.application.mailApp.repository.MailRepository;
import com.mail.application.mailApp.repository.UserRepository;
import com.mail.application.mailApp.service.MailService;
import com.mail.application.mailApp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserRepositoryTest {


    @Autowired
    private UserService userService;


    @Autowired
    private MailService mailService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailRepository mailRepository;

    @Test
    public void saveUser() {
        /*
        User user = new User();
        user.setUserName("ysn")
                .setFirstName("ysn")
                .setLastName("blb")
                .setMailAddress(List.of(new MailProvider().setName("ysn@ysn.com")))
                .setId(UUID.randomUUID().toString());

        userRepository.save(user);
         */

    }


    @Test
    public void getUser() {
        List<User> users = userRepository.findAll();
    }

    @Test
    public void getMailService() {
        PageRequest of = PageRequest.of(0, 1);
        List<Mail> mails = mailService.getAll();
    }

    @Test
    public void getMailPageRequest() {
        PageRequest of = PageRequest.of(0, 1);
        PageRequest of2 = PageRequest.of(0, 1, Sort.by("subject").descending());
        Page<Mail> mailList =  mailRepository.findAll(of);

    }

    //         PageRequest of = PageRequest.of(0, 1);

    @Test
    public void sendMail() {
       /*
        Mail mail = new Mail();
        mail.setMessage("selam")
                .setSenderId("05cc2f06-0690-45fe-871f-ebcd4fc33153")
                .setReceiverIds(List.of("539c9e21-7a1d-4ce5-80a8-2ffd6cfed513"))
                .setSubject("subject")
                .setDeliveryTime(LocalDateTime.now());
        mail.setId(UUID.randomUUID().toString());

        mailService.newMail(mail);


        */
    }

    @Test
    public void newMail() {
        /*
        User user1 = new User();
        User user2 = new User();
        Mail mail1 = new Mail();

        user1.setFirstName("yusuf")
                .setLastName("lastName")
                .setMailAddress(List.of(new MailProvider().setName("yusuf@yusuf.com")))
                .setId(UUID.randomUUID().toString());
        user2.setFirstName("ahmet")
                .setLastName("lastName")
                .setMailAddress(List.of(new MailProvider().setName("ahmet@ahmet.com")))
                .setId(UUID.randomUUID().toString());

        mail1.setSenderId(user1.getId())
                .setReceiverIds(List.of(user2.getId()))
                .setSubject("temp Subject")
                .setMessage("message")
                .setDeliveryTime(LocalDateTime.now());


        mailService.newMail(mail1);
*/
    }

    @Test
    public void newUser() {
        User user1 = new User();
        user1.setUserName("userName")
                .setFirstName("veli");
        user1.setId(UUID.randomUUID().toString());
    }

}
