package com.greenfoxacademy.springherokutemplate.service.security;

import com.greenfoxacademy.springherokutemplate.model.dto.RegistrationForm;
import com.greenfoxacademy.springherokutemplate.model.security.AppUser;

import java.security.Principal;

import java.util.List;

public interface AppUserService {
    AppUser createDefaultUser(RegistrationForm regForm);
    List<AppUser> allUsers();
    AppUser fromPrincipal(Principal principal);
}
