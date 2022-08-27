package com.example.Springboot.controller;
import com.example.Springboot.domain.PrivateMessage;
import com.example.Springboot.domain.User;
import com.example.Springboot.repos.PrivateMessageRepo;
import com.example.Springboot.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class PrivateMessageController {
    @Autowired
    PrivateMessageRepo privateMessageRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/user-inbox")
    public List<PrivateMessage> getPrivateMessages(@AuthenticationPrincipal User currUser,Model model){

        return privateMessageRepo.findAllByTo(currUser);
    }
    @PostMapping("/user-inbox/save")
    public PrivateMessage sendPrivateMessage(@RequestBody PrivateMessage message,@AuthenticationPrincipal User currUse){
        if(message.getFrom() == null){
            message.setTo(currUse);
        }
        if(message.getTo() == null) {
            message.setFrom(currUse);
        }
        return privateMessageRepo.save(message);
    }
    @PostMapping("/user-inbox/saveOld")
    public PrivateMessage sendPrivateMessageOld(@RequestBody PrivateMessage message,
                                                @AuthenticationPrincipal User currUse,
                                                @RequestParam String username,
                                                @RequestParam String subject,
                                                @RequestParam  String body
    ){
            message.setTo(userRepo.findByUsername(username));
            message.setMessageSubject(subject);
            message.setMessageBody(body);
            message.setFrom(currUse);
            return privateMessageRepo.save(message);
    }
    @DeleteMapping("/user-inbox/{privateMessageId}")
    public void deletePrivateMessage (@PathVariable("privateMessageId") PrivateMessage privateMessage,@AuthenticationPrincipal User currUser)
    {
        privateMessageRepo.delete(privateMessage);
    }
}
