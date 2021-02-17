package com.vaadin.tutorial.crm.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class User extends AbstractEntity {
  private String name;
  private String password;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Deal> deals = new LinkedList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Note> notes = new LinkedList<>();

  public User() {
  }

  public User(String name, String password) {
    setName(name);
    setPassword(password);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
