package com.robson.desafiogreenmile.service;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.domain.enumeration.Perfil;
import com.robson.desafiogreenmile.dto.NovoUsuarioDTO;
import com.robson.desafiogreenmile.dto.UsuarioDTO;
import com.robson.desafiogreenmile.exception.AuthorizationException;
import com.robson.desafiogreenmile.exception.ObjectNotFoundException;
import com.robson.desafiogreenmile.repository.UsuarioRepository;
import com.robson.desafiogreenmile.security.UserService;
import com.robson.desafiogreenmile.security.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

  @Autowired private UsuarioRepository usuarioRepository;

  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  public Usuario insert(Usuario usuario) {
    usuario.setId(null);
    usuario = usuarioRepository.save(usuario);
    return usuario;
  }

  public Usuario buscar(Long id) {
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
    Usuario novoUsuario = buscar(usuario.getId());
    updateData(novoUsuario, usuario);
    return usuarioRepository.save(novoUsuario);
  }

  public void delete(Long id) {
    buscar(id);
    usuarioRepository.deleteById(id);
  }

  public List<Usuario> findAll() {
    return usuarioRepository.findAll();
  }
}
