package com.robson.core.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Profile {
  ADMIN(1, "ROLE_ADMIN"),
  USER(2, "ROLE_USER");

  private int code;
  private String description;

  public static Profile toEnum(Integer code) {
    if (code == null) {
      return null;
    }

    for (Profile x : Profile.values()) {
      if (code.equals(x.getCode())) {
        return x;
      }
    }

    throw new IllegalArgumentException("Id inválido: " + code);
  }
}
