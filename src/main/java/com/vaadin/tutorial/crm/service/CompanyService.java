package com.vaadin.tutorial.crm.service;

import com.vaadin.tutorial.crm.entity.Company;
import com.vaadin.tutorial.crm.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public List<Company> findAll(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return companyRepository.findAll();
        } else  {
            return  companyRepository.search(filterText);
        }
    }

    public Map<String, Integer> getStats() {
        HashMap<String, Integer> stats = new HashMap<>();
        findAll().forEach(company ->
            stats.put(company.getName(), company.getEmployees().size()));
        return stats;
    }
}
