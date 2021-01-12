package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
