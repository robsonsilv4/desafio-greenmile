package com.robson.desafiogreenmile.service.command.impl;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.dto.NovoUsuarioDTO;
import com.robson.desafiogreenmile.dto.UsuarioDTO;
import com.robson.desafiogreenmile.repository.UsuarioRepository;
import com.robson.desafiogreenmile.service.command.UsuarioCommandService;
import com.robson.desafiogreenmile.service.query.UsuarioQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioCommandServiceImpl implements UsuarioCommandService {

  @Autowired private UsuarioRepository usuarioRepository;
  @Autowired private UsuarioQueryService usuarioQuery;
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public Usuario insert(Usuario usuario) {
    usuario.setId(null);
    usuario = usuarioRepository.save(usuario);
    return usuario;
  }

  @Override
  public Usuario update(Usuario usuario) {
    Usuario novoUsuario = usuarioQuery.find(usuario.getId());
    updateData(novoUsuario, usuario);
    return usuarioRepository.save(novoUsuario);
  }

  @Override
  public void delete(Long id) {
    usuarioQuery.find(id);
    usuarioRepository.deleteById(id);
  }

  // Métodos auxiliares
  public void updateData(Usuario novoUsuario, Usuario usuario) {
    novoUsuario.setNome(usuario.getNome());
    novoUsuario.setEmail(usuario.getEmail());
  }

  public Usuario fromDTO(UsuarioDTO usuarioDTO) {
    return new Usuario(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), null);
  }

  public Usuario fromDTO(NovoUsuarioDTO novoDTO) {
    return new Usuario(
        null,
        novoDTO.getNome(),
        novoDTO.getEmail(),
        bCryptPasswordEncoder.encode(novoDTO.getSenha()));
  }
}
