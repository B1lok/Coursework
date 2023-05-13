package com.coursework.service;

import com.coursework.dao.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }


}
