package com.coursework.service;

import com.coursework.dao.GameRepository;
import com.coursework.model.Game;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GamesService {


    GameRepository repository;

    public GamesService(GameRepository repository) {
        this.repository = repository;
    }

    public List<Game> getUpComingGames(int leagueID){
        List<Game> games = repository.findAllByLeagueIdOrderByGameDateAsc(leagueID);
        return games.subList(0, games.size() > 3 ? 3 : games.size());
    }

    public Game getGameById(long id){
        return repository.findById(id);
    }

    public void setGameResult(Game game, String number1, String number2){
        StringBuilder result = new StringBuilder();
        result.append(number1).append(" - ").append(number2);
        game.setResult(result.toString());
        repository.save(game);
    }
    public void addGame(Game game){
        repository.save(game);
    }

    @Transactional
    public void deleteGameById(Game game){
        game.getLeague().getGames().remove(game);
        game.getFirst_team().getGames().remove(game);
        game.getSecond_team().getGames().remove(game);
        game.setLeague(null);
        game.setFirst_team(null);
        game.setSecond_team(null);
        repository.deleteGameById(game.getId());
    }


}
