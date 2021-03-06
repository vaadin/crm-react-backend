package com.vaadin.tutorial.crm.service;

import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.entity.DealContact;
import com.vaadin.tutorial.crm.entity.Company;
import com.vaadin.tutorial.crm.entity.User;
import com.vaadin.tutorial.crm.model.ComplexDealDTO;
import com.vaadin.tutorial.crm.model.DealContactDTO;
import com.vaadin.tutorial.crm.repository.DealRepository;
import com.vaadin.tutorial.crm.repository.UserRepository;
import com.vaadin.tutorial.crm.repository.CompanyRepository;
import com.vaadin.tutorial.crm.repository.ContactRepository;
import com.vaadin.tutorial.crm.repository.DealContactRepository;
// import com.vaadin.tutorial.crm.service.DealContactService;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;
import java.util.Optional;
import java.io.IOException;

@Service
public class DealService {
    private static final Logger LOGGER = Logger.getLogger(DealService.class.getName());
    private DealRepository dealRepository;
    private CompanyRepository companyRepository;
    private UserRepository userRepository;
    private ContactRepository contactRepository;
    private DealContactRepository dealContactRepository;
    private PasswordEncoder passwordEncoder;

    public DealService(
        DealRepository dealRepository,
        CompanyRepository companyRepository,
        UserRepository userRepository,
        ContactRepository contactRepository,
        DealContactRepository dealContactRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.dealRepository = dealRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.dealContactRepository = dealContactRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<ComplexDealDTO> findAll(
        List<Long> companies,
        List<Long> contacts,
        List<Long> users,
        Double minPrice,
        Double maxPrice,
        String isActive
    ) {
        List<ComplexDealDTO> result = dealRepository.search(companies, contacts, users, minPrice, maxPrice, isActive);
        return result;
    }

    public List<DealContactDTO> findDealContacts(String deal) {
        List<DealContactDTO> result = dealRepository.findDealContacts(deal);
        return result;
    }

    public void save(Deal d) throws IOException {
        if (d == null) {
            LOGGER.log(Level.SEVERE,
                "Deal is null. Are you sure you have connected your form to the application?");
            return;
        }

        Deal deal = new Deal();
        deal.setName(d.getName());
        deal.setPrice(d.getPrice());
        deal.setStatus(Deal.Status.New);
        deal.setUser(d.getUser());
        deal.setCompany(d.getCompany());

        Deal newDeal = dealRepository.save(deal);

        for (DealContact dc : d.getDealContacts()) {
            DealContact newDC = new DealContact();
            newDC.setDeal(newDeal);
            newDC.setContact(contactRepository.findById(dc.getId()).get());
            newDC.setRole(dc.getRole());
            dealContactRepository.save(newDC);
        }
    }

    public void delete(Long id) throws IOException {
        Optional<Deal> opt = dealRepository.findById(id);

        if (opt.isPresent()) {
            Deal deal = opt.get();
            dealRepository.delete(deal);
        } else {
            throw new IOException("Can not find the deal");
        }
    }

    public Map<String, Object> update(Long id, Deal d) throws IOException {
        Optional<Deal> opt = dealRepository.findById(id);

        if (opt.isPresent()) {
            Deal deal = opt.get();
            deal.setName(d.getName());
            deal.setPrice(d.getPrice());
            deal.setStatus(d.getStatus());
            deal.setUser(d.getUser());
            deal.setCompany(d.getCompany());

            dealContactRepository.deleteByDeal(d);
            for (DealContact dc : d.getDealContacts()) {
                DealContact newDC = new DealContact();
                newDC.setDeal(d);
                newDC.setContact(contactRepository.findById(dc.getId()).get());
                newDC.setRole(dc.getRole());
                dealContactRepository.save(newDC);
            }

            dealRepository.save(deal);

            Map<String, Object> updatedItem = new HashMap();
            updatedItem.put("id", deal.getId());
            updatedItem.put("status", deal.getStatus());
            return updatedItem;
        }
        Map<String, Object> orgItem = new HashMap();
        orgItem.put("id", "0");
        return orgItem;
    }

    @PostConstruct
    public void populateTestData() {
        if (userRepository.count() == 0) {
            userRepository.saveAll(
                Stream.of("user password", "admin password", "customer password", "other password",
                        "user1 password", "admin1 password", "customer1 password", "other1 password",
                        "user2 password", "admin2 password", "customer2 password", "other2 password",
                        "user3 password", "admin3 password", "customer3 password", "other3 password")
                    .map(name -> {
                        String[] split = name.split(" ");
                        User user = new User();
                        user.setName(split[0]);
                        user.setPassword(passwordEncoder.encode(split[1]));
                        return user;
                    }).collect(Collectors.toList()));
        }

        if (dealRepository.count() == 0) {
            Random r = new Random(0);
            List<Company> companies = companyRepository.findAll();
            List<User> users = userRepository.findAll();

            dealRepository.saveAll(
                IntStream.range(1, 61)
                    .mapToObj(idx -> {
                        Deal deal = new Deal();
                        deal.setName("deal" + idx);
                        double price = Math.round(r.nextFloat() * 1000000) / 100.0;
                        deal.setPrice(price);
                        deal.setStatus(Deal.Status.values()[r.nextInt(Deal.Status.values().length)]);
                        deal.setCompany(companies.get(r.nextInt(companies.size())));
                        deal.setUser(users.get(idx % users.size()));
                        return deal;
                    })
                    .collect(Collectors.toList()));
        }
    }
}
