package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.Role;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String role){

        User user = new User();

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        if(role.equals("ADMIN")){
            user.setRole(Role.ROLE_ADMIN);
        }
        else if(role.equals("MANAGER")){
            user.setRole(Role.ROLE_MANAGER);
        }
        else{
            user.setRole(Role.ROLE_USER);
        }

        userRepository.save(user);


        return "redirect:/login";
    }
}