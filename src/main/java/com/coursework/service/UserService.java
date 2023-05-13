package com.coursework.service;


import com.coursework.dao.UserRepository;
import com.coursework.securityModel.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User FindByEmail(String email){
        return repository.findByEmail(email).orElse(null);
    }

    public void addUser(User user) {
        repository.save(user);
    }


}
