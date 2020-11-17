package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);
}
