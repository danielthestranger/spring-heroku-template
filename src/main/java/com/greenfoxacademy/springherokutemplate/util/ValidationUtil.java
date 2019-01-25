package com.greenfoxacademy.springherokutemplate.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ValidationUtil {
  public static String extractErrorStrings(BindingResult result) {
    StringBuilder sb = new StringBuilder();
    for (ObjectError error : result.getAllErrors()) {
      sb.append(error.getDefaultMessage());
      sb.append(System.lineSeparator());
    }

    return sb.toString();
  }

}
