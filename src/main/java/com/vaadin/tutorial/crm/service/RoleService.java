package com.vaadin.tutorial.crm.service;

import com.vaadin.tutorial.crm.entity.Contact;
import com.vaadin.tutorial.crm.entity.Role;
import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.repository.DealRepository;
import com.vaadin.tutorial.crm.repository.RoleRepository;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import java.util.List;

@Service
public class RoleService {
    private static final Logger LOGGER = Logger.getLogger(RoleService.class.getName());
    private RoleRepository roleRepository;
    private DealRepository dealRepository;

    public RoleService(RoleRepository roleRepository, DealRepository dealRepository) {
        this.roleRepository = roleRepository;
        this.dealRepository = dealRepository;
    }

    @PostConstruct
    public void populateTestData() {
        if (roleRepository.count() == 0) {
            Random r = new Random(0);
            List<Deal> deals = dealRepository.findAll();

            for (Deal deal : deals) {
                Role role = new Role();
                List<Contact> contacts = deal.getCompany().getEmployees();
                int size = contacts.size();
                if (size > 0) {
                    role.setDeal(deal);
                    role.setContactRole(Role.Roles.values()[r.nextInt(Role.Roles.values().length)]);
                    Contact contact = contacts.get(r.nextInt(size));
                    role.setContactId(contact.getId());
                    role.setContactName(contact.toString());
                    roleRepository.save(role);
                }
            }
        }
    }
}
