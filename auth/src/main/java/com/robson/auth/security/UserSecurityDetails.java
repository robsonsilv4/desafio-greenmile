package com.robson.auth.security;

import com.robson.core.domains.enums.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSecurityDetails implements UserDetails {

  private static final long serialVersionUID = -5968531581852726455L;

  private Long id;
  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public UserSecurityDetails(Long id, String username, String password, Set<Profile> perfis) {
    super();
    this.id = id;
    this.username = username;
    this.password = password;
    this.authorities =
        perfis.stream()
            .map(x -> new SimpleGrantedAuthority(x.getDescription()))
            .collect(Collectors.toList());
  }

  public Long getId() {
    return id;
  }

  public boolean hasRole(Profile profile) {
    return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription()));
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
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
