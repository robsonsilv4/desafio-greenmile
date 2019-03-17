package com.robson.desafiogreenmile.service.impl;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.domain.enumeration.Perfil;
import com.robson.desafiogreenmile.dto.NovoUsuarioDTO;
import com.robson.desafiogreenmile.dto.UsuarioDTO;
import com.robson.desafiogreenmile.exception.AuthorizationException;
import com.robson.desafiogreenmile.exception.ObjectNotFoundException;
import com.robson.desafiogreenmile.repository.UsuarioRepository;
import com.robson.desafiogreenmile.security.UserService;
import com.robson.desafiogreenmile.security.UsuarioDetails;
import com.robson.desafiogreenmile.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired private UsuarioRepository usuarioRepository;

  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  public Usuario insert(Usuario usuario) {
    usuario.setId(null);
    usuario = usuarioRepository.save(usuario);
    return usuario;
  }

  public Usuario find(Long id) {
    UsuarioDetails user = UserService.authenticated();
    if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
      throw new AuthorizationException("Acesso negado!");
    }

    Optional<Usuario> usuario = usuarioRepository.findById(id);
    return usuario.orElseThrow(
        () ->
            new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + Usuario.class.getName()));
  }

  public List<Usuario> findAll() {
    return usuarioRepository.findAll();
  }

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

  public Usuario update(Usuario usuario) {
    Usuario novoUsuario = find(usuario.getId());
    updateData(novoUsuario, usuario);
    return usuarioRepository.save(novoUsuario);
  }

  public void delete(Long id) {
    find(id);
    usuarioRepository.deleteById(id);
  }
}
