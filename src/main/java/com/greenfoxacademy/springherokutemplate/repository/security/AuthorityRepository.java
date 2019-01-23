package com.greenfoxacademy.springherokutemplate.repository.security;

import com.greenfoxacademy.springherokutemplate.model.security.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, String> {
}
