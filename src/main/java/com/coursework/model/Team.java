package com.coursework.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "team")
public class Team {


    public Team(String name, League league) {
        this.name = name;
        this.league = league;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "league_id", nullable = false)
    private League league;



    @OneToMany(mappedBy = "first_team", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Game> games = new ArrayList<>();


    @OneToMany(mappedBy = "second_team", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Game> awayGames = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Player> players = new ArrayList<>();


    @Column(name = "coach")
    private String coach;
}
