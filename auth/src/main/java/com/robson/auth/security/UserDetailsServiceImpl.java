package com.robson.auth.security;

import com.robson.core.domains.Employee;
import com.robson.core.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

  private final EmployeeRepository employeeRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    log.info("Procurando pelo registro do usuário '{}'...", username);

    Employee employee = employeeRepository.findByUsername(username);

    log.info("Funcionário encontrado '{}'.", employee);

    if (employee == null)
      throw new UsernameNotFoundException(
          String.format("Funcionário'%s' não encontrado!", username));

    return new CustomUserDetails(employee);
  }

  private static final class CustomUserDetails extends Employee implements UserDetails {
    CustomUserDetails(Employee employee) {
      super(employee);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return commaSeparatedStringToAuthorityList("ROLE_" + this.getRole());
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
}
