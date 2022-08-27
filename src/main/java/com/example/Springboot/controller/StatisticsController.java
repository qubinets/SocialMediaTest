package com.example.Springboot.controller;

import com.example.Springboot.domain.Message;
import com.example.Springboot.domain.User;
import com.example.Springboot.repos.MessageRepo;
import com.example.Springboot.repos.UserRepo;
import com.example.Springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class StatisticsController {
    @Autowired
    MessageRepo messageRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @GetMapping("/user-statistics/{user}")
    public String getStatistics(@AuthenticationPrincipal User currentUser, @RequestParam(required = false) Message message, @PathVariable User user, Model model){
        int totallikes = userService.getLikesAmount(currentUser);
        message = userService.getMostPopularMessage(currentUser);
        if(totallikes > 0 && message != null) {
            model.addAttribute("likesamount", message.getLikes().size());
            model.addAttribute("mostpopularmessage", message);
            model.addAttribute("totallikes", totallikes);
        }
        model.addAttribute("id",user.getId());
        model.addAttribute("idcurrentuser",currentUser.getId());
        return "userStatistics";
    }
    @GetMapping("/vue-stats")
    public String getStatistics(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user",user);

        return "vueStats";
    }
}
