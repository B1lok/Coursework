package com.coursework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "game")
public class Game {


    public Game(League league, Team first_team, Team second_team, LocalDateTime gameDate) {
        this.league = league;
        this.first_team = first_team;
        this.second_team = second_team;
        this.gameDate = gameDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;



    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "league_id", nullable = false)
    private League league;

    @ManyToOne(optional = false)
    @JoinColumn(name = "first_team_id", nullable = false)
    private Team first_team;

    @ManyToOne(optional = false)
    @JoinColumn(name = "second_team_id", nullable = false)
    private Team second_team;


    @Column(name = "game_date", nullable = false)
    private LocalDateTime gameDate;



    @Column(name = "result")
    private String result;


}