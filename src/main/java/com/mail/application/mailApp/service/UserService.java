package com.mail.application.mailApp.service;

import com.mail.application.mailApp.model.Mail;
import com.mail.application.mailApp.model.User;
import com.mail.application.mailApp.repository.MailRepository;
import com.mail.application.mailApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MailRepository mailRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User get(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getByMail(String mail) {
        return userRepository.findByMailAddress(mail);
    }

    public User save(User user) {
        boolean alreadyTaken = userRepository.findByMailAddress(user.getMailAddress()) != null;
        if (!alreadyTaken) {

            user.setId(UUID.randomUUID().toString());
            userRepository.save(user);
            return user;

        }
        if (user.getId() != null) {
            userRepository.save(user);
        }
        return null;
    }





    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.findById(id).ifPresent(user -> {
            userRepository.deleteById(id);
        });
    }

    public List<String> getInbox(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user.getIncomingMailIds();
    }
    public List<String> getSent(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user.getSentMailIds();
    }

    public Mail readMail(String userId,String mailId){
        User user = userRepository.findById(userId).orElse(null);
        List<String> incomingMails = user.getIncomingMailIds();
        if (incomingMails.contains(mailId)){
            incomingMails.remove(mailId);
            user.setIncomingMailIds(incomingMails);
            List<String> temp = user.getRead() == null ? new ArrayList<String>() : user.getRead();
            temp.add(mailId);
            user.setRead(temp);
            userRepository.save(user);
            return mailRepository.findById(mailId).orElse(null);
        }
        return mailRepository.findById(mailId).orElse(null);
    }

    public List<String> getUnreadMails(String id) {
        User user = userRepository.findById(id).orElse(null);
        return user.getIncomingMailIds();

    }

}

