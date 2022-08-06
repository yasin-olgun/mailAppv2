package com.mail.application.mailApp.repository;

import com.mail.application.mailApp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByMailAddress(String mailAddress);

}
