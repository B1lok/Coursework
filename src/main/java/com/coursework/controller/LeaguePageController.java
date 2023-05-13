package com.coursework.controller;



import com.coursework.model.League;
import com.coursework.service.LeagueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mySport/{leagueName}")
@Controller
public class LeaguePageController {

    LeagueService leagueService;

    public LeaguePageController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping
    public String getLeaguePage(@PathVariable String leagueName, Model model){

        League league = leagueService.getLeagueByName(leagueName);
        model.addAttribute("league", league);
        model.addAttribute("teams", league.getTeams());

        return "leaguePage";
    }



}
