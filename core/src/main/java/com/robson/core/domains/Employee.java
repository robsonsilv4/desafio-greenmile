package com.robson.core.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.robson.core.domains.enums.Profile;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @ToString.Exclude @JsonIgnore private String password;

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

  public Employee(Long id, String username, String password) {
    this.id = id;
    this.username = username;
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
