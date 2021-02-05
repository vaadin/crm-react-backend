package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.User;
import com.vaadin.tutorial.crm.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT DISTINCT NEW "
        + "com.vaadin.tutorial.crm.model.UserDTO("
        + "u.id, u.name) "
        + "FROM User u"
    )
    List<UserDTO> search();

    User findByName(String username);
}
