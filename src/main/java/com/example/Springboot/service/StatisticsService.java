package com.example.Springboot.service;

import com.example.Springboot.domain.User;
import com.example.Springboot.domain.VueStatistics;
import com.example.Springboot.repos.MessageRepo;
import com.example.Springboot.repos.UserInfoRepo;
import com.example.Springboot.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatisticsService {

    @Autowired
    MessageRepo messageRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserInfoRepo userInfoRepo;
    @Autowired
    UserService userService;


    public VueStatistics getStatistics(User user) {
       VueStatistics vueStatistics = new VueStatistics();
           if(user != null) {
               vueStatistics.setTotalLikes(userService.getLikesAmount(user));
               vueStatistics.setMostPopularMessage(userService.getMostPopularMessage(user));
               vueStatistics.setTotalMessages(messageRepo.findByUser(user).size());
               vueStatistics.setUserinfo(userInfoRepo.findByUserId(user.getId()));
               vueStatistics.setMessages(messageRepo.findByUser(user));
               vueStatistics.setTotalSubscribers(userRepo.findUserByUsername(user.getUsername()).getSubscribers().size());
               vueStatistics.setTotalSubscriptions(userRepo.findUserByUsername(user.getUsername()).getSubscriptions().size());
               vueStatistics.setMostPopularMessageLikes(userService.getMostPopularMessage(user).getLikes().size());
           }
        return vueStatistics;
    }
}
