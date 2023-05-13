package com.coursework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.regex.Pattern;

@Getter
@Setter
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "second_team_id", nullable = false)
    private Team second_team;


    @ManyToOne(optional = false)
    @JoinColumn(name = "first_team_id", nullable = false)
    private Team first_team;

    @Column(name = "game_date", nullable = false)
    @DateTimeFormat(pattern = "dd-mm-yyyy hh-mm")
    private Instant date;



    @Column(name = "result")
    private String result;


}