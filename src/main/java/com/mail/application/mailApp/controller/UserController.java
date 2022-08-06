package com.mail.application.mailApp.controller;

import com.mail.application.mailApp.model.Mail;
import com.mail.application.mailApp.model.User;
import com.mail.application.mailApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("all")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("inbox")
    public ResponseEntity<List<String>> getInbox(@RequestParam String id){
        return ResponseEntity.ok().body(userService.getInbox(id));
    }

    @GetMapping("sent")
    public ResponseEntity<List<String>> getSent(@RequestParam String id){
        return ResponseEntity.ok().body(userService.getSent(id));
    }
    @GetMapping("read")
    public ResponseEntity<Mail> read(@RequestParam String userId,@RequestParam String mailId){
        return ResponseEntity.ok().body(userService.readMail(userId,mailId));
    }

    @GetMapping("unread")
    public ResponseEntity<List<String>> unread(@RequestParam String id){
        return ResponseEntity.ok().body(userService.getUnreadMails(id));
    }




    @GetMapping("{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String id) {
        return ResponseEntity.ok().body(userService.get(id));
    }

    @PostMapping("new")
    public ResponseEntity<User> newUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.save(user));
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") String id) {
        userService.delete(id);
    }


}
