package com.robson.desafiogreenmile.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.robson.desafiogreenmile.domain.enumeration.Perfil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @Column(unique = true)
  private String email;

  @JsonIgnore private String senha;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "PERFIS")
  private Set<Integer> perfis = new HashSet<>();

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
  private List<HoraTrabalhada> horasTrabalhadas = new ArrayList<>();

  public Usuario() {
    setPerfil(Perfil.USUARIO);
  }

  public Usuario(Long id, String nome, String email, String senha) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    setPerfil(Perfil.USUARIO);
  }

  public Set<Perfil> getPerfis() {
    return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
  }

  public void setPerfil(Perfil perfil) {
    perfis.add(perfil.getCodigo());
  }
}
