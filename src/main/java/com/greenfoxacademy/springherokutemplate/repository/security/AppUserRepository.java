package com.greenfoxacademy.springherokutemplate.repository.security;

import com.greenfoxacademy.springherokutemplate.model.security.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, String> {
  Optional<AppUser> findByUserName(String username);
}
