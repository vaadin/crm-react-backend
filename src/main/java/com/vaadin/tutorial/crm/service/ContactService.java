package com.vaadin.tutorial.crm.service;

import com.vaadin.tutorial.crm.entity.Company;
import com.vaadin.tutorial.crm.entity.Contact;
import com.vaadin.tutorial.crm.model.ContactCompanyDTO;
import com.vaadin.tutorial.crm.repository.CompanyRepository;
import com.vaadin.tutorial.crm.repository.ContactRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.IOException;

@Service
public class ContactService {
    private static final Logger LOGGER = Logger.getLogger(ContactService.class.getName());
    private ContactRepository contactRepository;
    private CompanyRepository companyRepository;

    public ContactService(ContactRepository contactRepository,
                          CompanyRepository companyRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public List<ContactCompanyDTO> findAll(String filterText) {
        return  contactRepository.search(filterText);
    }

    public long count() {
        return contactRepository.count();
    }

    public void delete(Long id) {
        Optional<Contact> opt = contactRepository.findById(id);

        if (opt.isPresent()) {
            Contact contact = opt.get();
            contactRepository.delete(contact);
        }
    }

    public void save(Contact contact) throws IOException {
        if (contact == null) {
            LOGGER.log(Level.SEVERE,
                "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        boolean exists = contactRepository.existsByEmail(contact.getEmail().toLowerCase());
        if (exists) {
            throw new IOException("Contact is already exists");
        }
        contact.setEmail(contact.getEmail().toLowerCase());
        contactRepository.save(contact);
    }

    public void update(Long id, Contact c) throws IOException {
        Optional<Contact> opt = contactRepository.findById(id);
        String email = c.getEmail().toLowerCase();

        if (opt.isPresent()) {
            Contact contact = opt.get();

            boolean exists = contactRepository.existsByEmail(email);
            if (exists && !email.equals(contact.getEmail())) {
                throw new IOException("Contact is already exists");
            }
            contact.setEmail(email);
            contact.setFirstName(c.getFirstName());
            contact.setLastName(c.getLastName());
            contact.setStatus(c.getStatus());
            contact.setCompany(c.getCompany());
            contactRepository.save(contact);
        }
    }

    @PostConstruct
    public void populateTestData() {
        if (companyRepository.count() == 0) {
            companyRepository.saveAll(
                Stream.of("Aboitiz Equity Ventures, Inc.@Philippines@NAC Tower, 32nd St, Taguig@@1634",
                        "Aboitiz Power Corp@Philippines@110 Penthouse, Legazpi Street, Legaspi Village, Makati@@1224",
                        "Hilton@United States@7930 Jones Branch Dr, McLean@Virginia@22102",
                        "Ultimate Software@United States@2 Park Pl@New York@10007",
                        "Cisco@United States@111 8th Ave #5201@New York@10011",
                        "Workday@United States@1430 S Ashland Ave@Chicago@60608",
                        "Salesforce@United States@1430 S Ashland Ave@Chicago@60608",
                        "Stryker@United States@233 Broadway 24th floor@New York@10279",
                        "ABB Inc@Canada@201 Westcreek Blvd, Brampton, On@@L6T 5S6",
                        "Accenture Inc@Canada@3401 Schmon Parkway, Thorold, Canada, ON@@L2V 4Y6",
                        "ABC Inc@Canada@201 Westcreek Blvd, Brampton, On@@L6T 5S6",
                        "Jean@Canada@Nelson House, MB@@R0B 1A0",
                        "Bernard@Canada@799 Keefer St, Vancouver, BC@@V6A 1Y6",
                        "Leduc@Switzerland@Waisenhauspl. 29@@3011",
                        "Grenier SAS@Germany@Kronenstraße 43@@10117",
                        "Arhosuo OYj@Finland@Nokkolantie 154@@15880",
                        "Mattila Inc@Finland@Keskikankaantie 21@@15860",
                        "Virtala-Helmel@Finland@Muovitie 2@@15860",
                        "Juuti-Aavikkola@Finland@Arvi Hauvosentie 86@@16730",
                        "Kotanen Inc@Finland@Tikkakalliontie 29@@15820")
                    .map(name -> {
                        String[] data = name.split("@");
                        Company company = new Company();
                        company.setName(data[0]);
                        company.setCountry(data[1]);
                        company.setAddress(data[2]);
                        company.setState(data[3]);
                        company.setZipcode(data[4]);
                        return company;
                    })
                    .collect(Collectors.toList()));
        }

        if (contactRepository.count() == 0) {
            Random r = new Random(0);
            List<Company> companies = companyRepository.findAll();
            contactRepository.saveAll(
                Stream.of("Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
                    "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
                    "Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
                    "Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
                    "Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
                    "Gabrielle Andersson", "Brian Andersson", "Eduardo Carlsson", "Koen Olsen", "Alejandro Olsen",
                    "Ann Karlsson", "Remington Gustavsson", "Rene Svensson", "Elvis Stewart", "Solomon Davis",
                    "Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
                    "Jaydan Jackson", "Bernard Nilsen")
                    .map(name -> {
                        String[] split = name.split(" ");
                        Contact contact = new Contact();
                        contact.setFirstName(split[0]);
                        contact.setLastName(split[1]);
                        contact.setCompany(companies.get(r.nextInt(companies.size())));
                        contact.setStatus(Contact.Status.values()[r.nextInt(Contact.Status.values().length)]);
                        String email = (contact.getFirstName() + "." + contact.getLastName() + "@" + contact.getCompany().getName().replaceAll("[\\s,.-]", "") + ".com").toLowerCase();
                        contact.setEmail(email);
                        return contact;
                    }).collect(Collectors.toList()));
        }
    }
}
