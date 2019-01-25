package com.greenfoxacademy.springherokutemplate.service.security;

import com.greenfoxacademy.springherokutemplate.model.dto.RegistrationForm;
import com.greenfoxacademy.springherokutemplate.model.security.AppUser;

import java.security.Principal;

public interface AppUserService {
    AppUser createDefaultUser(RegistrationForm regForm);
    AppUser fromPrincipal(Principal principal);
}
