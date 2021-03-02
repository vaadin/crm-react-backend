package com.vaadin.tutorial.crm.service;

import com.vaadin.tutorial.crm.entity.Contact;
import com.vaadin.tutorial.crm.entity.DealContact;
import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.repository.DealRepository;
import com.vaadin.tutorial.crm.repository.DealContactRepository;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import java.util.List;

@Service
public class DealContactService {
    private static final Logger LOGGER = Logger.getLogger(DealContactService.class.getName());
    private DealContactRepository dealContactRepository;
    private DealRepository dealRepository;

    public DealContactService(DealContactRepository dealContactRepository, DealRepository dealRepository) {
        this.dealContactRepository = dealContactRepository;
        this.dealRepository = dealRepository;
    }

    @PostConstruct
    public void populateTestData() {
        if (dealContactRepository.count() == 0) {
            Random r = new Random(0);
            List<Deal> deals = dealRepository.findAll();

            for (Deal deal : deals) {
                DealContact dc = new DealContact();
                List<Contact> contacts = deal.getCompany().getEmployees();
                int size = contacts.size();
                if (size > 0) {
                    dc.setDeal(deal);
                    dc.setRole(DealContact.Roles.values()[r.nextInt(DealContact.Roles.values().length)]);
                    Contact contact = contacts.get(r.nextInt(size));
                    dc.setContact(contact);
                    dealContactRepository.save(dc);
                }
            }
        }
    }
}
