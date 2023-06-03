package com.coursework.controller;


import com.coursework.model.Player;
import com.coursework.model.Position;
import com.coursework.model.Team;
import com.coursework.securityModel.User;
import com.coursework.service.PlayerService;
import com.coursework.service.TeamService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mySport/team/{teamName}")
@SessionAttributes("user")
public class TeamPageController {

    TeamService teamService;

    PlayerService playerService;

    public TeamPageController(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping
    public String getTeamPage(@PathVariable String teamName, Model model, @SessionAttribute User user){
        if (user.getRole().name().equals("ADMIN")) model.addAttribute("admin", true);
        model.addAttribute("team", teamService.getTeamByName(teamName));

        return "teamPage";
    }

    @PostMapping("/setCoach")
    @PreAuthorize("hasAuthority('people:write')")
    public String setTeamCoach(@RequestParam String coachName, @PathVariable String teamName, Model model){
        if (teamService.coachAlreadyExist(coachName)) {
            model.addAttribute("coachAlreadyExist", "This coach is already exist");
            model.addAttribute("team", teamService.getTeamByName(teamName));
            return "teamPage";
        }
        Team team = teamService.getTeamByName(teamName);
        team.setCoach(coachName);
        teamService.updateTeamChanges(team);
        return "redirect:/mySport/team/"+teamName;
    }

    @GetMapping("/addPlayer")
    @PreAuthorize("hasAuthority('people:write')")
    public String addPlayerPage(@PathVariable String teamName, Model model){
        model.addAttribute("team", teamService.getTeamByName(teamName));
        return "addPlayer";
    }

    @PostMapping("/addPlayer")
    public String addPlayer(@PathVariable String teamName, Model model,
                            @RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String jerseyNumber,
                            @RequestParam String position){
        if (playerService.playerWithSuchJerseyNumberAlreadyExist(teamService.getTeamByName(teamName),Long.parseLong(jerseyNumber))){
            model.addAttribute("team", teamService.getTeamByName(teamName));
            model.addAttribute("playerWithSuchJerseyNumberAlreadyExist", "Player with this game number already exist in this team");
            return "addPlayer";
        }

        playerService.addPlayer(new Player(firstName,lastName, Long.parseLong(jerseyNumber), teamService.getTeamByName(teamName) ,Position.valueOf(position)));
        return "redirect:/mySport/team/"+teamName;
    }

    @PostMapping("/deletePlayer")
    @PreAuthorize("hasAuthority('people:write')")
    @Transactional
    public String deletePlayer(@PathVariable String teamName, @RequestParam String playerId){
        playerService.deletePlayer(Integer.parseInt(playerId));
        return "redirect:/mySport/team/"+teamName;
    }

}
