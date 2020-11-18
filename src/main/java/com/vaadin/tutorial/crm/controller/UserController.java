package com.vaadin.tutorial.crm.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.vaadin.tutorial.crm.entity.User;
import com.vaadin.tutorial.crm.repository.UserRepository;

@RestController
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
	}

    @PostMapping("/signup")
    public void signUp(@RequestBody User user)
    {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
	}

	@PostMapping("/login")
    public void login(@RequestBody User user) {}
}
