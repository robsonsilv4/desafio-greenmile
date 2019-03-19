package com.robson.desafiogreenmile.exceptions;

public class ObjectNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -2208161661009644035L;

  public ObjectNotFoundException(String msg) {
    super(msg);
  }

  public ObjectNotFoundException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
