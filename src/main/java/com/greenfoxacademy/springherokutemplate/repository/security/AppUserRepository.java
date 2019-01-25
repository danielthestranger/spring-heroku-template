package com.greenfoxacademy.springherokutemplate.repository.security;

import com.greenfoxacademy.springherokutemplate.model.security.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
  Optional<AppUser> findByUsername(String username);
  List<AppUser> findAll();
}
