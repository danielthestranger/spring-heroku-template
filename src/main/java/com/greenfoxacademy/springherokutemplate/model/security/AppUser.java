package com.greenfoxacademy.springherokutemplate.model.security;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppUser {
    @Id
    @Column(length = 100)
    private String username;
    private String password;
    private Boolean enabled;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "app_user_authority",
            joinColumns = { @JoinColumn(name = "username") },
            inverseJoinColumns = { @JoinColumn(name = "authority") }
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
}
