package com.vaadin.tutorial.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.vaadin.tutorial.crm.entity.User;
import com.vaadin.tutorial.crm.repository.UserRepository;
import com.vaadin.tutorial.crm.service.UserService;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

	@PostMapping("/login")
    public void login(@RequestBody User user) {}
}
