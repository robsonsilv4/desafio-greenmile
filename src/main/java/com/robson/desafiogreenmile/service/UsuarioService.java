package com.robson.desafiogreenmile.service;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.dto.NovoUsuarioDTO;
import com.robson.desafiogreenmile.dto.UsuarioDTO;
import com.robson.desafiogreenmile.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario insert(Usuario usuario) {
        usuario.setId(null);
        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario buscar(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void updateData(Usuario novoUsuario, Usuario usuario) {
        novoUsuario.setNome(usuario.getNome());
        novoUsuario.setEmail(usuario.getEmail());
    }

    public Usuario fromDTO(UsuarioDTO usuarioDTO) {
        return new Usuario(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), null);
    }

    public Usuario fromDTO(NovoUsuarioDTO novoUsuarioDTO) {
        return new Usuario(null, novoUsuarioDTO.getNome(), novoUsuarioDTO.getEmail(), null);
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
