package com.coursework.service;

import com.coursework.dao.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService {


    TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }
}
