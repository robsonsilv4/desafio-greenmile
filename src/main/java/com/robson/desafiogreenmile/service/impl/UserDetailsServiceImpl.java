package com.robson.desafiogreenmile.service.impl;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.repository.UsuarioRepository;
import com.robson.desafiogreenmile.security.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByEmail(email);

    if (usuario == null) {
      throw new UsernameNotFoundException(email);
    }

    return new UsuarioDetails(
        usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
  }
}
