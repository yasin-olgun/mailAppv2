package com.mail.application.mailApp.controller;

import com.mail.application.mailApp.model.Mail;
import com.mail.application.mailApp.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("mail")
public class MailController {

    private final MailService mailService;


    @GetMapping("list")
    public ResponseEntity<List<Mail>> getAll() {
        return ResponseEntity.ok().body(mailService.getAll());
    }


    @GetMapping("get")
    public ResponseEntity<Mail> getMail(@RequestParam String id) {
        return ResponseEntity.ok().body(mailService.getMail(id));
    }

    @PostMapping("send")
    public ResponseEntity<Mail> send(@RequestBody Mail mail) {
        return ResponseEntity.ok().body(mailService.send(mail));
    }

    @DeleteMapping("delete/{id}")
    public void deleteMail(@PathVariable("id") String id) {
        mailService.deleteMail(id);
    }


}
