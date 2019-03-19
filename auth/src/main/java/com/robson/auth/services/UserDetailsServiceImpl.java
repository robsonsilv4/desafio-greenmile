package com.robson.auth.services;

import com.robson.auth.security.UserSecurityDetails;
import com.robson.core.domains.Employee;
import com.robson.core.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private EmployeeRepository employeeRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Employee employee = employeeRepository.findByEmail(email);

    if (employee == null) {
      throw new UsernameNotFoundException(email);
    }

    log.info("Procurando nos registros pelo usuário: {}", employee.getEmail());
    return new UserSecurityDetails(
        employee.getId(), employee.getEmail(), employee.getPassword(), employee.getProfile());
  }
}
