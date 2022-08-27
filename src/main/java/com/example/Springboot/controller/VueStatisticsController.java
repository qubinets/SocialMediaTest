package com.example.Springboot.controller;

import com.example.Springboot.domain.Message;
import com.example.Springboot.domain.User;
import com.example.Springboot.domain.VueStatistics;
import com.example.Springboot.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class VueStatisticsController {
    @Autowired
    StatisticsService statisticsService;


    @GetMapping("/vue-statistics")
    public Set<VueStatistics> getStatistics(@AuthenticationPrincipal User user){
        Set<VueStatistics> statistics = new HashSet<>();
        statistics.add(statisticsService.getStatistics(user));
        return statistics;
    }
}
