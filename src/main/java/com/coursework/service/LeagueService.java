package com.coursework.service;


import com.coursework.dao.GameRepository;
import com.coursework.dao.LeagueRepository;
import com.coursework.model.Game;
import com.coursework.model.League;
import com.coursework.model.Team;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LeagueService {

    LeagueRepository repository;


    GamesService gameService;

    public LeagueService(LeagueRepository repository, GamesService gameService) {
        this.repository = repository;
        this.gameService = gameService;
    }

    public List<League> getAllLeagues(){
        return repository.findAll();
    }


    public League getLeagueByName(String leagueName){
        return repository.findByName(leagueName);
    }

    public List<Game> getUpComingLeagueGames(int leagueID){
        return gameService.getUpComingGames(leagueID);
    }



}
