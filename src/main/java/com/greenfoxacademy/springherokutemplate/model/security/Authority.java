package com.greenfoxacademy.springherokutemplate.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Authority {
    @Id
    @Column(length = 100)
    private String authority;
    @ManyToMany(mappedBy = "authorities")
    private Set<AppUser> users;


    public Authority() {
    }

    public Authority(String authority) {
        this();
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
