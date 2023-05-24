package com.coursework.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "league")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "league")
    @ToString.Exclude
    private List<Team> teams = new ArrayList<>();

    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Game> games = new ArrayList<>();



}
