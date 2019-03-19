package com.robson.desafiogreenmile.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.robson.desafiogreenmile.domain.enumeration.Profile;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee implements Serializable {

  private static final long serialVersionUID = 6269313493164561869L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @JsonIgnore private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "profiles")
  @JsonIgnore
  private Set<Integer> profiles = new HashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
  private List<WorkedHours> workedHours = new ArrayList<>();

  public Employee() {
    setProfile(Profile.USER);
  }

  public Employee(Long id, String name, String email, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    setProfile(Profile.USER);
  }

  public Set<Profile> getProfile() {
    return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
  }

  public void setProfile(Profile profile) {
    profiles.add(profile.getCode());
  }
}
