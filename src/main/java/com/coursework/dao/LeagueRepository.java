package com.coursework.dao;

import com.coursework.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LeagueRepository extends JpaRepository<League, Integer> {

    League findByName(String leagueName);
}
