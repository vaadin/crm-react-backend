package com.vaadin.tutorial.crm.service;

import com.vaadin.tutorial.crm.entity.Role;
import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.repository.DealRepository;
import com.vaadin.tutorial.crm.repository.RoleRepository;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RoleService {
    private static final Logger LOGGER = Logger.getLogger(RoleService.class.getName());
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void populateTestData() {
    }
}
