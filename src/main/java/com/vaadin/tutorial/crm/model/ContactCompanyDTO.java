package com.vaadin.tutorial.crm.model;
import com.vaadin.tutorial.crm.entity.Contact;

public class ContactCompanyDTO {
  public Long id;
  public String firstName;
  public String lastName;
  public String email;
  public Contact.Status status;
  public Long companyID;
  public String companyName;

  public ContactCompanyDTO(
    final Long id,
    final String firstName,
    final String lastName,
    final String email,
    final Contact.Status status,
    final Long companyID,
    final String companyName
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.status = status;
    this.companyID = companyID;
    this.companyName = companyName;
  }
}
