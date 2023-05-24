package com.coursework.controller;



import com.coursework.model.Game;
import com.coursework.model.League;
import com.coursework.model.Team;
import com.coursework.securityModel.User;
import com.coursework.service.GamesService;
import com.coursework.service.LeagueService;
import com.coursework.service.TeamService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("/mySport/{leagueName}")
@Controller
@SessionAttributes("user")
public class LeaguePageController {

    LeagueService leagueService;

    GamesService gamesService;

    TeamService teamService;

    EntityManager entityManager;

    public LeaguePageController(LeagueService leagueService, GamesService gamesService, TeamService teamService, EntityManager entityManager) {
        this.leagueService = leagueService;
        this.gamesService = gamesService;
        this.teamService = teamService;
        this.entityManager = entityManager;
    }

    @GetMapping
    public String getLeaguePage(@PathVariable String leagueName, Model model, @SessionAttribute User user){
        if (user.getRole().name().equals("ADMIN")) model.addAttribute("admin", true);
        League league = leagueService.getLeagueByName(leagueName);
        model.addAttribute("league", league);
        model.addAttribute("games", league.getGames());

        return "leaguePage";
    }



    @GetMapping("/createGame")
    public String getCreateGamePage(@PathVariable String leagueName, Model model){
        League league = leagueService.getLeagueByName(leagueName);
        model.addAttribute("league", league);
        model.addAttribute("newGame", new Game());


        return "createGame";
    }


    @PostMapping("/createGame")
    @PreAuthorize("hasAuthority('people:write')")
    public String createGame(Model model,
                             @PathVariable String leagueName,
                             @RequestParam("firstTeam") String firstTeamName,
                             @RequestParam("secondTeam") String secondTeamName,
                             @RequestParam("gameDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime gameDate){
        League league = leagueService.getLeagueByName(leagueName);
        model.addAttribute("league", league);
        if (firstTeamName.equals(secondTeamName)){
            model.addAttribute("sameTeamError", "You cant choose same teams");
            return "createGame";
        }
        Team firstTeam = teamService.getTeamByName(firstTeamName);
        Team secondTeam = teamService.getTeamByName(secondTeamName);
        gamesService.addGame(new Game(league, firstTeam, secondTeam, gameDate));
        return "redirect:/mySport/"+leagueName;
    }

    @PostMapping("/deleteGame")
    @PreAuthorize("hasAuthority('people:write')")
    @Transactional
    public String deleteGame(@PathVariable String leagueName,@RequestParam String gameId){

        gamesService.deleteGameById(gamesService.getGameById(Long.parseLong(gameId)));

        return "redirect:/mySport/"+leagueName;
    }


    @GetMapping("/createTeam")
    @PreAuthorize("hasAuthority('people:write')")
    public String getCreateTeam(@PathVariable String leagueName, Model model){

        League league = leagueService.getLeagueByName(leagueName);
        model.addAttribute("league", league);

        return "createTeam";
    }


    @PostMapping("/createTeam")
    @PreAuthorize("hasAuthority('people:write')")
    public String createTeam(@PathVariable String leagueName, @RequestParam String teamName, Model model){

        if (teamService.getTeamByName(teamName) != null) {
            model.addAttribute("teamAlreadyExist", "This team already exist");
            return "createTeam";
        }
        teamService.addTeam(new Team(teamName, leagueService.getLeagueByName(leagueName)));

        return "redirect:/mySport/"+leagueName;
    }



}
