package com.coursework.controller;

import com.coursework.securityModel.User;
import com.coursework.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {



    PasswordEncoder passwordEncoder;

    UserService userService;

    public AuthController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.passwordEncoder = encoder;
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage(Model model){
        model.addAttribute("user", new User());
        return "signUp";
    }

    @PostMapping("/sign-up")
    public String postSignUpPage(@ModelAttribute("user") User user, Model model){

        if(userService.FindByEmail(user.getEmail()) != null){
            model.addAttribute("emailExist", "User with this email already exist");
            return "signUp";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);

        return "login";
    }

}
