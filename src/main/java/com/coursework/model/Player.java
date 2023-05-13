package com.coursework.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "player", uniqueConstraints = @UniqueConstraint(columnNames = {"jersey_number", "team_id"}))
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "jersey_number", nullable = false)
    private Long jerseyNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "position", nullable = false)
    @Enumerated(EnumType.STRING)
    private Position position;

}
