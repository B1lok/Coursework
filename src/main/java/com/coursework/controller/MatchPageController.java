package com.coursework.controller;


import com.coursework.model.Game;
import com.coursework.securityModel.User;
import com.coursework.service.GamesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/mySport/{leagueName}/{matchId}")
@SessionAttributes("user")
public class MatchPageController {


    GamesService gamesService;

    public MatchPageController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @GetMapping
    public String getMatchPage(@PathVariable String leagueName, @PathVariable String matchId, Model model, @SessionAttribute User user){

        model.addAttribute("game", gamesService.getGameById(Integer.parseInt(matchId)));
        if (user.getRole().name().equals("ADMIN")) model.addAttribute("admin", true);
        return "matchPage";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('people:write')")
    public String setMatchResult(Model model,@PathVariable String leagueName,
                                 @PathVariable String matchId, @RequestParam String number1,
                                 @RequestParam String number2, @SessionAttribute User user ){
        if (user.getRole().name().equals("ADMIN")) model.addAttribute("admin", true);
        Game game = gamesService.getGameById(Long.parseLong(matchId));
        model.addAttribute("game", game);
        gamesService.setGameResult(game, number1, number2);
        return "matchPage";
    }


    @PostMapping("/changeDate")
    @PreAuthorize("hasAuthority('people:write')")
    public String changeGameDate(@PathVariable String leagueName, @PathVariable String matchId,
                                 @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime gameDate){
        Game game = gamesService.getGameById(Long.parseLong(matchId));
        game.setGameDate(gameDate);
        gamesService.addGame(game);
        return "redirect:/mySport/"+leagueName+"/"+matchId;
    }

}
