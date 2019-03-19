package com.robson.hours.exceptions.commons;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldMessage implements Serializable {

  private static final long serialVersionUID = -3687763084990423280L;

  private String fieldName;
  private String message;
}
