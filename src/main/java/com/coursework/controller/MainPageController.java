package com.coursework.controller;


import com.coursework.model.Team;
import com.coursework.securityModel.Permission;
import com.coursework.securityModel.User;
import com.coursework.service.LeagueService;
import com.coursework.service.TeamService;
import com.coursework.service.UserService;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mySport")
@SessionAttributes("user")
public class MainPageController {


    UserService userService;

    LeagueService leagueService;

    TeamService teamService;

    public MainPageController(UserService userService, LeagueService leagueService, TeamService teamService) {
        this.userService = userService;
        this.leagueService = leagueService;
        this.teamService = teamService;
    }

    @GetMapping
    public String mainPage(Model model, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()){
            User user = userService.FindByEmail(authentication.getName());
            if (user.getRole().name().equals("ADMIN")) model.addAttribute("admin", true);
            model.addAttribute("user", user);
        }

        model.addAttribute("leagues", leagueService.getAllLeagues());
        model.addAttribute("leagueService", leagueService);

        return "main";
    }


    @PostMapping
    public String searchTeamByName(@RequestParam String query, Model model){

        model.addAttribute("leagues", leagueService.getAllLeagues());
        model.addAttribute("leagueService", leagueService);
        Team team = teamService.searchTeamByName(query);
        if (team == null) {
            model.addAttribute("noTeamFound", "Team not found");
            return "main";
        }

        return "redirect:/mySport/team/"+team.getName();
    }


}
