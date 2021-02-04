package com.vaadin.tutorial.crm.entity;

// import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

@Entity
public class DealContact extends AbstractEntity {

  public enum Roles {
    DecisionMaker, Consulted, Informed
  }

  // @NotNull
  // private Long contact_id;

  // @NotNull
  // @NotEmpty
  // private String contact_name;

  @Enumerated(EnumType.STRING)
  @NotNull
  private DealContact.Roles role;

  @ManyToOne()
  private Deal deal;

  @OneToOne()
  private Contact contact;

  // @EmbededCollection
  // private List<Roles> roles;

  public DealContact() {
  }

  public DealContact(Contact contact, DealContact.Roles role, Deal deal) {
    setContact(contact);
    setContactRole(role);
    setDeal(deal);
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public Roles getContactRole() {
    return role;
  }

  public void setContactRole(Roles role) {
    this.role = role;
  }

  // @JsonIgnore
  public Deal getDeal() {
    return deal;
  }

  public void setDeal(Deal deal) {
    this.deal = deal;
  }
}
