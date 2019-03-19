package com.robson.desafiogreenmile.services;

import com.robson.desafiogreenmile.domains.Employee;
import com.robson.desafiogreenmile.repositories.EmployeeRepository;
import com.robson.desafiogreenmile.security.UserSecurityDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private EmployeeRepository employeeRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Employee employee = employeeRepository.findByEmail(email);

    if (employee == null) {
      throw new UsernameNotFoundException(email);
    }

    return new UserSecurityDetails(
        employee.getId(), employee.getEmail(), employee.getPassword(), employee.getProfile());
  }
}
