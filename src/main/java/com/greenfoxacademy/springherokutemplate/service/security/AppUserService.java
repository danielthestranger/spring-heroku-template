package com.greenfoxacademy.springherokutemplate.service.security;

import com.greenfoxacademy.springherokutemplate.model.dto.RegistrationForm;
import com.greenfoxacademy.springherokutemplate.model.security.AppUser;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface AppUserService {
    AppUser save(AppUser user);
    AppUser createDefaultUser(RegistrationForm regForm);
}
