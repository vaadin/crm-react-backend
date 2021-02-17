package com.vaadin.tutorial.crm.model;

import com.vaadin.tutorial.crm.entity.DealContact;
import com.vaadin.tutorial.crm.entity.User;
import java.util.Map;
import java.util.HashMap;

public class DealContactDTO {
  public Long contactId;
  public DealContact.Roles role;

  public DealContactDTO(
    final Long contactId,
    final DealContact.Roles role
  ) {
    this.contactId = contactId;
    this.role = role;
  }
}
