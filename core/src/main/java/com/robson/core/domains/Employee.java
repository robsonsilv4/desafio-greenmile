package com.robson.core.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee implements AbstractDomain {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  @NotEmpty(message = "O nome de usuário é obrigatório!")
  private String username;

  @JsonIgnore
  @ToString.Exclude
  @NotEmpty(message = "A senha é obrigatória!")
  private String password;

  @JsonIgnore
  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
  private List<WorkedHours> workedHours = new ArrayList<>();

  @JsonIgnore
  @Builder.Default
  @Column(nullable = false)
  private String role = "USER";
}
