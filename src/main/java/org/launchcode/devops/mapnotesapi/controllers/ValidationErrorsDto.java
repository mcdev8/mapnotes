package org.launchcode.devops.mapnotesapi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import lombok.Data;

@Data
public class ValidationErrorsDto {
  private Map<String, String> errors;

  public ValidationErrorsDto(Errors validationErrors) {
    this.errors = new HashMap<>();

    for (FieldError fieldError : validationErrors.getFieldErrors()) {
      this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
  }
}
