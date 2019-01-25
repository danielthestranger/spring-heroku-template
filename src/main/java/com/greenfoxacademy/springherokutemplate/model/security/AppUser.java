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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, unique = true)
  private String username;

  private String password;
  private String fullName;
  private Boolean enabled;

  @Column(unique = true)
  private String email;
  
  @Column(unique = true)
  private String phoneNumber;

  @ManyToMany
  @JoinTable(
      name = "app_user_authority",
      joinColumns = {@JoinColumn(name = "id")},
      inverseJoinColumns = {@JoinColumn(name = "authority")}
  )
  private Set<Authority> authorities = new HashSet<>();

  private AppUser() {
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
