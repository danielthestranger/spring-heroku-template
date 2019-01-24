package com.greenfoxacademy.springherokutemplate.repository;

import com.greenfoxacademy.springherokutemplate.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

  List<Address> findAll();
}
