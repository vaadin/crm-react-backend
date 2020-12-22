package com.vaadin.tutorial.crm.service;

import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.entity.Company;
import com.vaadin.tutorial.crm.repository.DealRepository;
import com.vaadin.tutorial.crm.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;

@Service
public class DealService {
    private static final Logger LOGGER = Logger.getLogger(DealService.class.getName());
    private DealRepository dealRepository;
    private CompanyRepository companyRepository;

    public DealService(DealRepository dealRepository, CompanyRepository companyRepository) {
        this.dealRepository = dealRepository;
        this.companyRepository = companyRepository;
    }

    public List<Deal> findAll() {
        return dealRepository.findAll();
    }

    @PostConstruct
    public void populateTestData() {
        if (dealRepository.count() == 0) {
            Random r = new Random(0);
            List<Company> companies = companyRepository.findAll();
            dealRepository.saveAll(
                IntStream.range(1, 61)
                    .mapToObj(idx -> {
                        Deal deal = new Deal();
                        deal.setName("deal" + idx);
                        double price = Math.round(r.nextFloat() * 1000000) / 100.0;
                        deal.setPrice(price);
                        deal.setStatus(Deal.Status.values()[r.nextInt(Deal.Status.values().length)]);
                        deal.setCompany4d(companies.get(r.nextInt(companies.size())));
                        return deal;
                    })
                    .collect(Collectors.toList()));
        }
    }
}
