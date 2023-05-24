package com.coursework.service;

import com.coursework.dao.PlayerRepository;
import com.coursework.model.Player;
import com.coursework.model.Team;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public boolean playerWithSuchJerseyNumberAlreadyExist(Team team, long jerseyNumber){
        for (Player player : team.getPlayers()){
            if (player.getJerseyNumber() == jerseyNumber) return true;
        }
        return false;
    }


    public void addPlayer(Player player){
        repository.save(player);
    }

    public void deletePlayer(int playerId){
        repository.deletePlayerById(playerId);
    }


}
