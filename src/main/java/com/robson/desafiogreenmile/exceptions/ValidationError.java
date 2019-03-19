package com.robson.desafiogreenmile.exceptions;

import com.robson.desafiogreenmile.exceptions.commons.FieldMessage;
import com.robson.desafiogreenmile.exceptions.commons.StandardError;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

  private static final long serialVersionUID = -5692582954655938329L;

  private List<FieldMessage> errors = new ArrayList<>();

  public ValidationError(
      Long timestamp, Integer status, String error, String message, String path) {
    super(timestamp, status, error, message, path);
  }

  public List<FieldMessage> getErrors() {
    return errors;
  }

  public void addError(String fieldName, String message) {
    errors.add(new FieldMessage(fieldName, message));
  }
}