package com.vaadin.tutorial.crm.model;

public class UserDTO {
  public Long id;
  public String name;

  public UserDTO(
    final Long id,
    final String name
  ) {
    this.id = id;
    this.name = name;
  }
}
