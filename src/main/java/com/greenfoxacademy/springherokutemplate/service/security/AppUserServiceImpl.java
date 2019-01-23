package com.greenfoxacademy.springherokutemplate.service.security;

import com.greenfoxacademy.springherokutemplate.model.security.AppUser;
import com.greenfoxacademy.springherokutemplate.repository.security.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository userRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }
}
