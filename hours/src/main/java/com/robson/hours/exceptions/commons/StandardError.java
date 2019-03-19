package com.robson.hours.exceptions.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class StandardError implements Serializable {

  private static final long serialVersionUID = -7202215609023977490L;

  private Long timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;
}
