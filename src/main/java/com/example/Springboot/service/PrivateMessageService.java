package com.example.Springboot.service;

import com.example.Springboot.domain.PrivateMessage;
import com.example.Springboot.domain.User;
import com.example.Springboot.repos.PrivateMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivateMessageService {

    @Autowired
    PrivateMessageRepo privateMessageRepo;
    public boolean saveMessage(User user, String subject, String body, User currUse) {
        PrivateMessage message = new PrivateMessage();
        if(user != null) {
            message.setTo(user);
            message.setMessageSubject(subject);
            message.setMessageBody(body);
            message.setFrom(currUse);

            privateMessageRepo.save(message);
            return true;
        }
        return false;
    }
}
