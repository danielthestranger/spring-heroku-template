package com.greenfoxacademy.springherokutemplate.service.security;

import com.greenfoxacademy.springherokutemplate.model.security.AppUser;

public interface AppUserService {
    AppUser save(AppUser user);
}
