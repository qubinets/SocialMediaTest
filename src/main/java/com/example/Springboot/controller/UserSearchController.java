package com.example.Springboot.controller;

import com.example.Springboot.domain.Message;
import com.example.Springboot.domain.User;
import com.example.Springboot.domain.UserInfo;
import com.example.Springboot.repos.UserInfoRepo;
import com.example.Springboot.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserSearchController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserInfoRepo userInfoRepo;

    @PostMapping("search")
    public String userSearch(@RequestParam String username, Model model){
        String standard = "https://pbs.twimg.com/profile_images/578844000267816960/6cj6d4oZ_400x400.png";
        List<User> foundUsers = userRepo.findUsersByUsername(username);
        if(!foundUsers.isEmpty() && foundUsers != null){
            model.addAttribute("foundusers",foundUsers);
            for(User foundUser : foundUsers){
                UserInfo userInfo = userInfoRepo.findByUserId(foundUser.getId());
                    if(userInfo != null){
                        model.addAttribute("logourl",userInfo.getLogoUrl());
                    }
            }
        }else
        {
            model.addAttribute("foundusers",foundUsers);
        }
        return "userSearch";
    }
}
