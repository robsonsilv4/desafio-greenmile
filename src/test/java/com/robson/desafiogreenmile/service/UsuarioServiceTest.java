package com.robson.desafiogreenmile.service;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.repository.UsuarioRepository;
import com.robson.desafiogreenmile.service.command.UsuarioCommandService;
import com.robson.desafiogreenmile.service.query.UsuarioQueryService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioServiceTest {

  @Autowired private UsuarioRepository usuarioRepository;
  @Autowired private UsuarioQueryService usuarioQueryService;
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired private UsuarioCommandService usuarioCommandService;
  @Rule public ExpectedException thrown = ExpectedException.none();

  @Test
  public void insereUsuario() {
    Usuario usuario = new Usuario(null, "Gabriel", "gabriel@greenmile.com", "czar");

    usuarioCommandService.insert(usuario);
    Usuario usuario_buscado = usuarioQueryService.find(usuario.getId());

    assertThat(usuario_buscado).isNotNull();

    assertThat(usuario.getId()).isNotNull();
    assertThat(usuario.getNome()).isEqualTo("Gabriel");
    assertThat(usuario.getEmail()).isEqualTo("gabriel@greenmile.com");
    assertThat(bCryptPasswordEncoder.matches("czar", usuario.getSenha()));
  }

  @Test
  public void emailNaoNulo() {
    Usuario usuario = new Usuario(null, "Samuel", "samuel@greenmile.com", "samuel");

    usuarioCommandService.insert(usuario);
    Usuario usuario_buscado = usuarioQueryService.find(usuario.getId());

    assertThat(usuario_buscado).isNotNull();
    assertThat(usuario_buscado.getEmail()).isNotEmpty();
  }

  @Test
  public void buscaUsuario() {
    Usuario usuario = new Usuario(null, "Gabriel", "gabriel@greenmile.com", "czar");
    usuarioCommandService.insert(usuario);

    usuarioCommandService.insert(usuario);
    Usuario usuario_buscado = usuarioQueryService.find(usuario.getId());

    assertThat(usuario_buscado).isNotNull();
    assertThat(usuario_buscado.getId()).isNotNull();
  }

  @Test
  public void buscaTodosUsuarios() {
    Usuario usuario1 = new Usuario(null, "Gabriel", "gabriel@greenmile.com", "czar");
    Usuario usuario2 = new Usuario(null, "Samuel", "samuel@greenmile.com", "sam");
    Usuario usuario3 = new Usuario(null, "Robson", "robson@greenmile.com", "ss");
    Usuario usuario4 = new Usuario(null, "Emerson", "emerson@greenmile.com", "memeson");
    usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3, usuario4));

    Page<Usuario> usuarios = usuarioQueryService.findAll(0, 10, "nome", "ASC");

    assertThat(usuarios).isNotNull();
    assertThat(usuarios.getSize()).isGreaterThan(1);
  }
}
