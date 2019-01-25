package com.greenfoxacademy.springherokutemplate.repository;

import com.greenfoxacademy.springherokutemplate.model.ServiceType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceTypeRepository extends CrudRepository<ServiceType, Long> {

  List<ServiceType> findAll();

  ServiceType findAllById(Long id);

  ServiceType findByName(String newName);
  
}
