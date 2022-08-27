package com.example.Springboot.controller;


import com.example.Springboot.config.JDBCtemplate;
import com.example.Springboot.domain.Message;
import com.example.Springboot.domain.Role;
import com.example.Springboot.domain.User;
import com.example.Springboot.domain.UserInfo;
import com.example.Springboot.repos.MessageRepo;
import com.example.Springboot.repos.UserInfoRepo;
import com.example.Springboot.repos.UserRepo;
import com.example.Springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepo userRepository;
    @Autowired
    UserInfoRepo userInfoRepo;

    @Autowired
    MessageRepo messageRepo;
    @Autowired
    UserService userService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model)
    {
        model.addAttribute("users",userService.findAll());
        return "userList";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String,String> form,
            @RequestParam("userId") User user
                ){
            userService.saveUser(user,username,form);
            return "redirect:/user";


    }
    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        UserInfo userInform = userInfoRepo.findByUserId(user.getId());

        model.addAttribute("username",user.getUsername());
        model.addAttribute("email",user.getEmail());
        model.addAttribute("id",user.getId());
        model.addAttribute("user",user);

        if(userInform != null && userInform.getUserId().equals(user))
        {
        model.addAttribute("userInform",userInform);
        model.addAttribute("userName", userInform.getUserName());
        model.addAttribute("userLastName", userInform.getUserLastName());
        model.addAttribute("country", userInform.getCountry());
        model.addAttribute("aboutme", userInform.getAboutMe());
        model.addAttribute("logourl", userInform.getLogoUrl());
        model.addAttribute("dateOfBirth",userInform.getDateOfBirth());
       }
        return "profile";
    }
    @PostMapping("profile/update")
    public String setUserInfo(
            @AuthenticationPrincipal User currentuser,
            @RequestParam String userName,
            @RequestParam String userLastName,
            @RequestParam String country,
            @RequestParam  String aboutme,
            @RequestParam("dateOfBirth")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirth,
            @RequestParam String logourl
    ) throws IOException {

        UserInfo userInfo = userInfoRepo.findByUserId(currentuser.getId());
        userService.saveUserInfo(userInfo,userName,userLastName,country,aboutme,dateOfBirth,logourl,currentuser);

       return "redirect:/user/profile";
    }


    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user,@RequestParam String password,@RequestParam String email){
        userService.updateProfile(user,password,email);
        return "redirect:/user/profile";
    }
    @GetMapping("delete")
    public String getDel(Model model, @AuthenticationPrincipal User user){

        model.addAttribute("id",user.getId());
        return "delete";
    }
    @PostMapping("delete")
    public String deleteAccount(@AuthenticationPrincipal User currentUser,@RequestParam("id") User user){
        if(currentUser.equals(user) && !user.isAdmin())
        {
            String sql = "delete from message_likes where user_id = ?";
            String sql2 = "delete from user_info where user_id = ?";
            String sql3 = "delete from user_private_message where message_from = ? OR message_to = ?";
            int numberOfRowsAffected = jdbcTemplate.update(sql,currentUser.getId());
            int numberOfRowsAffected2 = jdbcTemplate.update(sql2,currentUser.getId());
            int numberOfRowsAffected3 = jdbcTemplate.update(sql3,currentUser.getId(),currentUser.getId());
            userRepository.delete(currentUser);
        }
        return "redirect:/login";
    }
    @GetMapping("subscribe/{user}")
    public String subscribe(@AuthenticationPrincipal User currentUser,
            @PathVariable User user){
        userService.subscribe(currentUser,user);
        return "redirect:/user-messages/" + user.getId();
    }
    @GetMapping("unsubscribe/{user}")
    public String unsubscribe(@AuthenticationPrincipal User currentUser,
                            @PathVariable User user){
        userService.unsubscribe(currentUser,user);
        return "redirect:/user-messages/" + user.getId();
    }
    @GetMapping("{type}/{user}/list")
    public String userList(Model model,
                           @PathVariable User user,
                           @PathVariable String type
                           ){
        model.addAttribute("userChannel",user);
        model.addAttribute("type",type);
        if("subscriptions".equals(type)){
            model.addAttribute("users",user.getSubscriptions());
        }else
            model.addAttribute("users",user.getSubscribers());
        return "subscriptions";
    }

    }
