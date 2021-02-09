package com.vaadin.tutorial.crm.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Company extends AbstractEntity {
  @NotEmpty
  private String name="";

  @NotEmpty
  private String country="";

  private String address="";
  private String zipcode="";
  private String state="";

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", fetch=FetchType.LAZY)
  private List<Deal> deals = new LinkedList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", fetch=FetchType.LAZY)
  private List<Contact> employees = new LinkedList<>();

  public Company() {
  }

  public Company(String name, String country, String address, String zipcode, String state) {
    setName(name);
    setCountry(country);
    setAddress(address);
    setZipcode(zipcode);
    setState(state);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public List<Contact> getEmployees() {
    return employees;
  }

  public List<Deal> getDeals() {
    return deals;
  }
}
