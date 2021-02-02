package com.vaadin.tutorial.crm.service;

import com.vaadin.tutorial.crm.entity.Company;
import com.vaadin.tutorial.crm.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import com.vaadin.tutorial.crm.model.CompanyDealSummaryDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;

@Service
public class CompanyService {

    private static final Logger LOGGER = Logger.getLogger(CompanyService.class.getName());
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public List<CompanyDealSummaryDTO> findAll(String filterText) {
        return companyRepository.search(filterText);
    }

    public Map<String, Integer> getStats() {
        HashMap<String, Integer> stats = new HashMap<>();
        findAll().forEach(company ->
            stats.put(company.getName(), company.getEmployees().size()));
        return stats;
    }

    public String[] getCountries() {
        return new String[] {"United States", "Canada", "Finland", "Germany", "Switzerland", "Philippines"};
    }

    public void delete(Long id) {
        Optional<Company> opt = companyRepository.findById(id);

        if (opt.isPresent()) {
            Company company = opt.get();
            companyRepository.delete(company);
        }
    }

    public void save(Company company) {
        if (company == null) {
            LOGGER.log(Level.SEVERE,
                "Company is null. Are you sure you have connected your form to the application?");
            return;
        }
        companyRepository.save(company);
    }

    public void update(Long id, Company c) {
        Optional<Company> opt = companyRepository.findById(id);

        if (opt.isPresent()) {
            Company company = opt.get();
            company.setName(c.getName());
            company.setCountry(c.getCountry());
            company.setAddress(c.getAddress());
            company.setState(c.getState());
            company.setZipcode(c.getZipcode());
            companyRepository.save(company);
        }
    }
}
