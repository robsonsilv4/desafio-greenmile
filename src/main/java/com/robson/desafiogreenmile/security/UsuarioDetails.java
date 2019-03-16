package com.robson.desafiogreenmile.security;

import com.robson.desafiogreenmile.domain.enumeration.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioDetails implements UserDetails {

  private static final long serialVersionUID = -5968531581852726455L;

  private Long id;
  private String email;
  private String senha;
  private Collection<? extends GrantedAuthority> authorities;

  public UsuarioDetails(Long id, String email, String senha, Set<Perfil> perfis) {
    super();
    this.id = id;
    this.email = email;
    this.senha = senha;
    this.authorities =
        perfis.stream()
            .map(x -> new SimpleGrantedAuthority(x.getDescricao()))
            .collect(Collectors.toList());
  }

  public Long getId() {
    return id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return senha;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
