package com.vaadin.tutorial.crm.service;

import com.vaadin.tutorial.crm.entity.User;
import com.vaadin.tutorial.crm.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
@Service
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByName(username);
        if(user == null)
        {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), Collections.emptyList());
    }
}
