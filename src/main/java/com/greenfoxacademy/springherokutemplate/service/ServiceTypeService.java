package com.greenfoxacademy.springherokutemplate.service;

import com.greenfoxacademy.springherokutemplate.model.ServiceType;

import java.util.List;

public interface ServiceTypeService {

  List<ServiceType> findAll();
  void saveServiceType(ServiceType serviceType);
}
