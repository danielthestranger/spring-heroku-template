package com.greenfoxacademy.springherokutemplate.service;

import com.greenfoxacademy.springherokutemplate.model.ServiceType;

import java.util.List;

public interface ServiceTypeService {

  List<ServiceType> findAll();
  void saveServiceType(ServiceType serviceType);

  void deleteServiceType(Long id);

  ServiceType findById(Long id);

  ServiceType findCreateOrUpdate(String newName, Long id);
}
