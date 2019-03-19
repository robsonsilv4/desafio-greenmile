package com.robson.hours.exceptions;

public class AuthorizationException extends RuntimeException {

  private static final long serialVersionUID = -7588006799941552787L;

  public AuthorizationException(String msg) {
    super(msg);
  }

  public AuthorizationException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
