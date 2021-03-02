package com.vaadin.tutorial.crm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class DealContact extends AbstractEntity {

  public enum Roles {
    DecisionMaker, Consulted, Informed
  }

  @Enumerated(EnumType.STRING)
  @NotNull
  private DealContact.Roles role;

  @ManyToOne()
  private Deal deal;

  @OnDelete(action = OnDeleteAction.CASCADE)
  @OneToOne()
  private Contact contact;

  public DealContact() {
  }

  public DealContact(Contact contact, DealContact.Roles role, Deal deal) {
    setContact(contact);
    setRole(role);
    setDeal(deal);
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public Roles getRole() {
    return role;
  }

  public void setRole(Roles role) {
    this.role = role;
  }

  public Deal getDeal() {
    return deal;
  }

  public void setDeal(Deal deal) {
    this.deal = deal;
  }
}
