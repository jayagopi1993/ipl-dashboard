package com.rm.ipldashboard.controller;

import com.rm.ipldashboard.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/")
    public String dashboardHome(Model model){
        model.addAttribute("matchs",matchService.getAllMatchs());
        return "home";
    }

}
