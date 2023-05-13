package com.coursework.service;


import com.coursework.dao.LeagueRepository;
import com.coursework.model.League;
import com.coursework.model.Team;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LeagueService {

    LeagueRepository repository;

    public LeagueService(LeagueRepository repository) {
        this.repository = repository;
    }


    public List<League> getAllLeagues(){
        return repository.findAll();
    }


    public League getLeagueByName(String leagueName){
        return repository.findByName(leagueName);
    }




}
