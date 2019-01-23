package com.greenfoxacademy.springherokutemplate.model.dto;

import com.greenfoxacademy.springherokutemplate.model.security.Authority;
import com.greenfoxacademy.springherokutemplate.model.security.AppUser;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrationForm {
    private String username;
    private String password;

    public AppUser toAppUser(PasswordEncoder encoder) {
        AppUser user = new AppUser(
                username,
                encoder.encode(password),
                true
        );
        user.addAuthority(new Authority("ROLE_USER"));

        return user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
