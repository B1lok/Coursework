package com.coursework.dao;

import com.coursework.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {



        List<Game> findAllByLeagueIdOrderByGameDateAsc(int leagueId);
        Game findById(long id);

        void deleteGameById(Long id);



}
