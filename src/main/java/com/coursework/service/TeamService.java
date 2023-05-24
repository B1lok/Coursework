package com.coursework.service;

import com.coursework.dao.TeamRepository;
import com.coursework.model.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {


    TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }


    public Team getTeamByName(String teamName){
        List<Team> teams = repository.findAll();

        return teams.stream()
                .filter(team -> team.getName().equalsIgnoreCase(teamName))
                .findAny()
                .orElse(null);
    }

    public Team searchTeamByName(String teamName){
        StringBuilder name = new StringBuilder();
        String[] each = teamName.split(" ");
        for (String string : each) {
            if (!string.isEmpty()) name.append(Character.toUpperCase(string.charAt(0))).append(string.substring(1)).append(" ");
        }
        return getTeamByName(name.toString().strip());
    }

    public void addTeam(Team team){
        repository.save(team);
    }

    public boolean coachAlreadyExist(String coachName){
        for (Team team : repository.findAll()){
            if (team.getCoach() != null && team.getCoach().equalsIgnoreCase(coachName)) return true;
        }
        return false;
    }

    public void updateTeamChanges(Team team){
        repository.save(team);
    }

}
