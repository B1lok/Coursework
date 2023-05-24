package com.coursework.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "player", uniqueConstraints = @UniqueConstraint(columnNames = {"jersey_number", "team_id"}))
public class Player {


    public Player(String firstName, String lastName, Long jerseyNumber, Team team, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jerseyNumber = jerseyNumber;
        this.team = team;
        this.position = position;
    }

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
