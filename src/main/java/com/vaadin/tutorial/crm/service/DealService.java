package com.vaadin.tutorial.crm.service;

import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.entity.Company;
import com.vaadin.tutorial.crm.entity.User;
import com.vaadin.tutorial.crm.repository.DealRepository;
import com.vaadin.tutorial.crm.repository.UserRepository;
import com.vaadin.tutorial.crm.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;

@Service
public class DealService {
    private static final Logger LOGGER = Logger.getLogger(DealService.class.getName());
    private DealRepository dealRepository;
    private CompanyRepository companyRepository;
    private UserRepository userRepository;

    public DealService(DealRepository dealRepository, CompanyRepository companyRepository, UserRepository userRepository) {
        this.dealRepository = dealRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public List<Deal> findAll() {
        return dealRepository.findAll();
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
                        user.setPassword(new BCryptPasswordEncoder().encode(split[1]));
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
                        deal.setCompany4d(companies.get(r.nextInt(companies.size())));
                        deal.setUser(users.get(idx % users.size()));
                        return deal;
                    })
                    .collect(Collectors.toList()));
        }
    }
}
