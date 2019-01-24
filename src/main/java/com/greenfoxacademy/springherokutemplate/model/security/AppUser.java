package com.greenfoxacademy.springherokutemplate.model.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class AppUser {

  @Id
  private Long id;

  @Column(length = 100, unique = true)
  private String username;

  private String password;
  private String name;
  private Boolean enabled;
  private Boolean isAdmin;

  @Column(unique = true)
  private String email;
  
  @Column(unique = true)
  private String phoneNumber;

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(
      name = "app_user_authority",
      joinColumns = {@JoinColumn(name = "id")},
      inverseJoinColumns = {@JoinColumn(name = "authority")}
  )
  private Set<Authority> authorities;

  public AppUser() {
    this.authorities = new HashSet<>();
  }

  public AppUser(String username, String password, Boolean enabled) {
    this();
    this.username = username;
    this.password = password;
    this.enabled = enabled;
  }

  public void addAuthority(Authority authority) {
    this.authorities.add(authority);
  }
}
