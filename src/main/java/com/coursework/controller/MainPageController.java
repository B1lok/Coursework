package com.coursework.controller;


import com.coursework.securityModel.Permission;
import com.coursework.securityModel.User;
import com.coursework.service.LeagueService;
import com.coursework.service.UserService;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/mySport")
@SessionAttributes("user")
public class MainPageController {


    UserService userService;

    LeagueService leagueService;

    public MainPageController(UserService userService, LeagueService leagueService) {
        this.userService = userService;
        this.leagueService = leagueService;
    }

    @GetMapping
    public String mainPage(Model model, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()){
            User user = userService.FindByEmail(authentication.getName());
            model.addAttribute("user", user);
        }
        model.addAttribute("leagues", leagueService.getAllLeagues());
        return "main";
    }



}
