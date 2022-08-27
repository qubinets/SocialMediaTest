package com.example.Springboot.controller;

import com.example.Springboot.domain.Message;
import com.example.Springboot.domain.PrivateMessage;
import com.example.Springboot.domain.User;
import com.example.Springboot.repos.PrivateMessageRepo;
import com.example.Springboot.repos.UserRepo;
import com.example.Springboot.service.PrivateMessageService;
import com.example.Springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class InboxController {

    @Autowired
    PrivateMessageRepo privateMessageRepo;

    @Autowired
    PrivateMessageService privateMessageService;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/inbox/{user}")
    public String getPrivateMessages(@PathVariable User user,@AuthenticationPrincipal User currUser, Model model){
        List<PrivateMessage> privateMessages = privateMessageRepo.findAllByTo(currUser);
        model.addAttribute("messages", privateMessages);
        return "userInbox";
    }

    @PostMapping("/inbox/{user}")
    public String sendPrivateMessageOld(@AuthenticationPrincipal User currUse,
                                                @RequestParam String username,
                                                @RequestParam String subject,
                                                @RequestParam  String body,
                                                Model model,
                                                @PathVariable("user") User userId){
        User user = userRepo.findByUsername(username);
        List<PrivateMessage> privateMessages = privateMessageRepo.findAllByTo(currUse);
        if (user == null) {
            model.addAttribute("usernameError", "User not exists!");
        }
        model.addAttribute("user",currUse);
        model.addAttribute("messages", privateMessages);
        boolean success = privateMessageService.saveMessage(user,subject,body,currUse);
        if(success) {
            model.addAttribute("success", "Message sent");
        }
        return "userInbox";

    }
    @PostMapping("/inbox/delete/{user}")
    public String deleteMessageDelete(@AuthenticationPrincipal User currUse,
                                      @PathVariable Long user,
                                      @RequestParam("id") PrivateMessage message) {
        if (message.getTo().equals(currUse)) {
            privateMessageRepo.delete(message);
        }
        return "redirect:/inbox/"+ currUse.getId();
    }
}
