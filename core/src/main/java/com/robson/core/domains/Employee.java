package com.robson.core.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.robson.core.domains.enums.Profile;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee implements Serializable {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @ToString.Exclude @JsonIgnore private String password;

  @Builder.Default
  @Column(nullable = false)
  private String role = "USER";

  @JsonIgnore
  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
  private List<WorkedHours> workedHours = new ArrayList<>();

  public Employee(@NotNull Employee employee) {
    this.id = employee.getId();
    this.username = employee.getUsername();
    this.password = employee.getPassword();
    this.role = employee.getRole();
    // Manter como nulo, por enquanto.
    this.workedHours = null;
  }
}
